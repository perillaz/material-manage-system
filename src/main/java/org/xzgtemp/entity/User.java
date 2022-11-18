package org.xzgtemp.entity;

public class User {
    
	private String id;
	private String name;
	private String password;


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


    @Override
	public String toString() {
		return String.format("User[id=%s, name=%s, password=%s]", 
			GetID(),GetName(), GetPassword());
	}
}
