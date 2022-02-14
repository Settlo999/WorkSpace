package model;

import dao.UsersDAO;
import javaBeans.User;

/*
 * ログイン処理クラス　※Singleton
 */
public class LoginLogic {
	
	//Singleton
	private static LoginLogic loginLogic = new LoginLogic();
	
	private LoginLogic() {
	}
	
	public static LoginLogic getInstance() {
		return loginLogic;
	}
	
	/*
	 * ユーザーを受け取って、UsersDAOに登録済みか調べさせて、その成否を返す
	 * @param User user ユーザー
	 * @return trueかfalse 成否
	 */
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
