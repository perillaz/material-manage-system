package org.xzgtemp.entity;

public class User {
    
	private String id;
	private String name;
	private String password;
	private String usertype = "visitor";
	private boolean setadmini = false;
	private boolean setstudent = false;

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

	public String getUsertype() {  return usertype;  }

	public void setUsertype(String usertype){
		if(usertype == ""){
			throw new IllegalArgumentException("invalid usertype value");
		}
		this.usertype=usertype;
	}

	public boolean getSetadmini(){  return setadmini;  }
	public boolean getSetstudent(){  return setstudent;  }

	public void setSetadmini(Boolean bool){
		if(bool == null){
			throw new IllegalArgumentException("invalid set value");
		}
		this.setadmini = bool;
	}
	public void setSetstudent(Boolean bool){
		if(bool == null){
			throw new IllegalArgumentException("invalid set value");
		}
		this.setstudent = bool;
	}
}
