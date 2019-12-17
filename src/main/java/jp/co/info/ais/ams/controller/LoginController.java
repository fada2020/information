package jp.co.info.ais.ams.controller;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.info.ais.ams.domain.Login;
import jp.co.info.ais.ams.service.LoginService;

@Controller
@RequestMapping("/")
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
	@RequestMapping("")
	public String index(Model model) {
		logger.info(model);
		logger.info(model.toString());
		model.addAttribute("loginErr", "g");
		logger.info("LOGIN PAGE START ===================");
		if (session.getAttribute("id") == null) {

			// sessionに値がなかったら
			return "login.html";
		} else {

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
	@RequestMapping(value = "/loginprocess", method = RequestMethod.POST)
	@ResponseBody
	public int loginProcess(@RequestBody Login login, Model model) {
		int result = 0;
		logger.info("LOGIN PROCESS START : " + login.getEmpId());
		try {
			//ID存在チェック
			if (loginservice.selectLoginId(login.getEmpId()) < 1) {
				result = 1;
			} else {
				Login user = loginservice.selectLogin(login.getEmpId(), login.getPasswd());
				//パスワード有効性チェック
				if (user == null) {
					result = 2;
				} else {
					session.setAttribute("name", user.getEmpName());
					session.setAttribute("id", user.getEmpId());
					session.setAttribute("organizationCode", user.getOrganizationCode());
				}
			}
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		return result;
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
