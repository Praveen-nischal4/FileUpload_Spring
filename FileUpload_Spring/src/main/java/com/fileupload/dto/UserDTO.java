package com.fileupload.dto;

public class UserDTO {

	private int rollno;
	private String name;
	private int age;
	private String photo_path;
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhoto_path() {
		return photo_path;
	}
	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}
	@Override
	public String toString() {
		return "UserDTO [rollno=" + rollno + ", name=" + name + ", age=" + age + ", photo_path=" + photo_path + "]";
	}
	
	
}
