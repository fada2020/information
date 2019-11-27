package jp.co.info.ais.ams.controller;



import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.info.ais.ams.domain.Login;
import jp.co.info.ais.ams.service.LoginService;


@Controller
public class LoginController {

	private static final Logger logger = LogManager.getLogger(LoginController.class);

	@Autowired
	LoginController LoginController;

	@Autowired
	HttpSession session;

	@Autowired
	private LoginService loginservice;
	/**
	 * Sessionの有無で画面移動
	 * @return String login画面, dashboard画面
	 */
	@RequestMapping("/")
    public String index() {

		logger.info("LOGIN PAGE START ===================");
		if (session.getAttribute("id") == null) {
			// sessionに値がなかったら
			return "login.html";
		}else {
			//sessionに値があったら
			return "redirect:/dashboard";
		}


    }



	/**
	 * 社員番号とパスワード認証処理
	 * @param assetId
	 * @param assetPass
	 * @param model
	 * @return String login画面
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
	 * @return　String インデックス
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String Logout() {
		logger.info("LOGOUT START ====");
		try {
			//Session終了
			session.invalidate();
		} catch (Exception e) {
			//エラーメッセージを送る
			logger.error("ログアウト処理エラーが発生 :" + e.toString());
		}
		return "redirect:/";
	}





}
