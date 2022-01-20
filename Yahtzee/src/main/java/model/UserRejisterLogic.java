package model;

import dao.UsersDAO;
import javaBeans.User;
//ユーザー登録処理
public class UserRejisterLogic {
	
	//Singleton
	private static UserRejisterLogic userRejisterLogic = new UserRejisterLogic();
	
	private UserRejisterLogic() {
	}
	
	public static UserRejisterLogic getInstance() {
		return userRejisterLogic;
	}
	
	//UsersDAOに登録させる
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
