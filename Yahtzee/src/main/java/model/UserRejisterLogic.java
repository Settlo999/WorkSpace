package model;

import dao.UsersDAO;
//ユーザー登録処理
public class UserRejisterLogic {
	//UsersDAOに登録させる
	public boolean rejister(User user) {
		UsersDAO UD = new UsersDAO();
		boolean isSuccessed = UD.create(user);
		
		if(isSuccessed) {
			return true;
		}
		else {
			return false;
		}
	}
}
