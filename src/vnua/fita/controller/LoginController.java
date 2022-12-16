package vnua.fita.controller;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

import vnua.fita.bean.User;
import vnua.fita.model.LoginDao;

@SuppressWarnings("serial")
public class LoginController extends SelectorComposer<Div> {
	@Wire
	private Textbox username;
	@Wire
	private Textbox password;
	@Wire
	private Label errorMsg;
	private LoginDao loginDao = new LoginDao("jdbc:mysql://localhost:3306/ngoxuanthanh_642925", "root", "xuanthanh");

	@Listen("onClick = #resetButton")
	public void reset() {
		username.setRawValue(null);
		password.setRawValue(null);
		errorMsg.setValue(null);
	}

	@Listen("onClick = #submitButton")
	public void submit() {

		User loginBean = new User(username.getValue(), password.getValue());
		if (loginDao.login(loginBean)) {
			Executions.sendRedirect("/home.zul");
		} else {
			errorMsg.setValue("incorrect username or password");
		}
	}

	@Listen("onOK = #formGrid")
	public void onOK() {
		submit();
	}
}