package org.xzgtemp.entity;

public class User {
    
	private String id;
	private String name;
	private String password;

	public User(){
		
	}

	public User(String id,String name,String password){
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public String GetID() {
		return id;
	}

	public void SetID(String id) {
		this.id = id;
	}

	public String GetName() {
		return name;
	}

	public void SetName(String name) {
		this.name = name;
	}

	public String GetPassword() {
		return password;
	}

	public void SetPassword(String password) {
		this.password = password;
	}
}
