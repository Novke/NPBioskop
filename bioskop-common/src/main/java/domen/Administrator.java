package domen;

import java.io.Serializable;

public class Administrator implements Serializable{
	
	String user;
	String pass;
	
	public Administrator() {
		
	}
	
	public Administrator(String user, String pass) {
		super();
		this.user = user;
		this.pass = pass;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	

}
