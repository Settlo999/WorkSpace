package model;

import java.io.Serializable;
//ユーザー(name, pass)
public class User implements Serializable {
	
	private String name;
	private String pass;
	
	public User(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}

	public String getName() { return name; }

	public String getPass() { return pass; }
	
}
