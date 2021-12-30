package javaBeans;

import java.io.Serializable;

//ユーザー(名前、パスワード、ユーザーID) sessionスコープに保存する用
public class User implements Serializable {
	
	private String name;
	private String pass;
	private int userId;
	
	public User(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}

	public User(String name, String pass, int userId) {
		this.name = name;
		this.pass = pass;
		this.userId = userId;
	}

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public String getPass() { return pass; }

	public void setPass(String pass) { this.pass = pass; }

	public int getUserId() { return userId; }

	public void setUserId(int userId) { this.userId = userId; }

}
