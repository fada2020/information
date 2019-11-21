package jp.co.info.ais.asm.controller;


import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.info.ais.asm.domain.Login;
import jp.co.info.ais.asm.service.LoginService;


@Controller
public class LoginController {

	private static final Logger logger = LogManager.getLogger(LoginController.class);

	@Autowired
	MainController mainController;

	@Autowired
	HttpSession session;

	@Autowired
	private LoginService loginservice;

	@RequestMapping("/")
    public String index() {

		logger.info("LOGIN PAGE START ===================");
		if (session.getAttribute("id") == null) {
			//sessionに値があったら
			return "login.html";
		}else {
			//sessionに値がなかったら
			return "redirect:/dashboard";
		}


    }


	//Login button id & pass check
	//매핑할때는 소문자로
	/**
	 * 社員番号とパスワード認証処理
	 * @param assetId
	 * @param assetPass
	 * @param model
	 * @return
	 */
	@RequestMapping(value= "/loginprocess", method = RequestMethod.POST)
	public String loginProcess(@RequestParam String assetId, @RequestParam String assetPass, Model model)  {

		logger.info("LOGIN PROCESS START : " + assetId);
		try {
			int userCheck = loginservice.selectLoginId(assetId);
			//ID存在チェック
			if (userCheck < 1) {
				model.addAttribute("errorMessage", "社員番号が存在しません。");
				return "login";
			}

			Login user = loginservice.selectLogin(assetId, assetPass);
			//パスワード有効性チェック
			if (user == null) {
				model.addAttribute("errorMessage", "パスワードが一致しないです。");
				return "login";
			}

			session.setAttribute("name", user.getEmpName());
			session.setAttribute("id", user.getEmpId());
			session.setAttribute("organizationCode", user.getOrganizationCode());

			return "redirect:/";
		}catch(Exception e) {
			logger.error("認証処理エラーが発生 :" + e.toString());
			return  "login";
		}
	}
	/**
	 * ログアウト処理を行う。
	 *
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String Logout() {
		logger.info("LOGOUT START ====");
		try {
			session.invalidate();
		} catch (Exception e) {
			logger.error("ログアウト処理エラーが発生 :" + e.toString());
		}
		return "redirect:/";
	}





}
