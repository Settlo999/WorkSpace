package model;

import dao.UsersDAO;
import javaBeans.User;

//ログイン処理
public class LoginLogic {
	
	//Singleton
	private static LoginLogic loginLogic = new LoginLogic();
	
	private LoginLogic() {
	}
	
	public static LoginLogic getInstance() {
		return loginLogic;
	}
	
	//UsersDAOに登録済みか調べさせる
	public boolean execute(User user) {
		UsersDAO UD = UsersDAO.getInstance();
		boolean isSuccessed = UD.isFind(user);
		
		if(isSuccessed) {
			return true;
		}
		else {
			return false;
		}
	}
}
