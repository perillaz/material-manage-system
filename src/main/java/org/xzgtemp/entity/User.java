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

	public String getType() {  return usertype;  }

	public void setAdmini(){
		this.usertype="admini";
	}
	public void setStudent(){
		this.usertype="student";
	}

	public boolean getPermission_setAdmini(){  return setadmini;  }
	public boolean getPermission_setStudent(){  return setstudent;  }

	public void setPermission_setAdmini(){  this.setadmini = true;  }
	public void setPermission_setStudent(){
		this.setstudent = true;
	}
}
