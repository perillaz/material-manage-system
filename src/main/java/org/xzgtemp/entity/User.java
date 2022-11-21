package org.xzgtemp.entity;

public class User {
    
	private String id;
	private String name;
	private String password;

	public User(){
		
	}

	public User(String id,String name,String password){
		setId(id);
		setName(name);
		setPassword(password);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if(id == ""){
			throw new IllegalArgumentException("invalid id value");
		}
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name == ""){
			throw new IllegalArgumentException("invalid name value");
		}
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(password == ""){
			throw new IllegalArgumentException("invalid password value");
		}
		this.password = password;
	}
}
