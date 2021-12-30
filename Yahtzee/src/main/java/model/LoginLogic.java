package model;

import dao.UsersDAO;
import javaBeans.User;

//ログイン処理
public class LoginLogic {
	//UsersDAOに登録済みか調べさせる
	public boolean execute(User user) {
		UsersDAO UD = new UsersDAO();
		boolean isSuccessed = UD.isFind(user);
		
		if(isSuccessed) {
			return true;
		}
		else {
			return false;
		}
	}
}
