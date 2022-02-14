package model;

import dao.UsersDAO;
import javaBeans.User;

/*
 * ユーザー登録処理クラス ※Singleton
 */
public class UserRejisterLogic {
	
	//Singleton
	private static UserRejisterLogic userRejisterLogic = new UserRejisterLogic();
	
	private UserRejisterLogic() {
	}
	
	public static UserRejisterLogic getInstance() {
		return userRejisterLogic;
	}
	
	/*
	 * ユーザーを受け取って、UsersDAOに登録させて、その成否を返す
	 * @param User user ユーザー
	 * @return trueかfalse 成否
	 */
	public boolean rejister(User user) {
		UsersDAO UD = UsersDAO.getInstance();
		boolean isSuccessed = UD.create(user);
		
		if(isSuccessed) {
			return true;
		}
		else {
			return false;
		}
	}
}
