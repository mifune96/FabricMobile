package com.fabric.apps.mobile.model.signupModel;


import com.google.gson.annotations.SerializedName;


public class CustomerSignup {

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("image")
	private Object image;

	@SerializedName("password")
	private String password;

	@SerializedName("phoneNumber")
	private String phoneNumber;

	@SerializedName("salt")
	private String salt;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("username")
	private String username;

	@SerializedName("updatedAt")
	private String updatedAt;

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setImage(Object image){
		this.image = image;
	}

	public Object getImage(){
		return image;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setSalt(String salt){
		this.salt = salt;
	}

	public String getSalt(){
		return salt;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	@Override
 	public String toString(){
		return 
			"CustomerSignup{" +
			"createdAt = '" + createdAt + '\'' + 
			",image = '" + image + '\'' + 
			",password = '" + password + '\'' + 
			",phoneNumber = '" + phoneNumber + '\'' + 
			",salt = '" + salt + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",username = '" + username + '\'' + 
			",updatedAt = '" + updatedAt + '\'' + 
			"}";
		}
}