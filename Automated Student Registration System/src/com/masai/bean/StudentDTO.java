package com.masai.bean;

public class StudentDTO {

	private int roll;
	private String name;
	private String bname;

	public StudentDTO() {

	}

	public StudentDTO(int roll, String name, String bname) {
		super();
		this.roll = roll;
		this.name = name;
		this.bname = bname;
	}

	public int getRoll() {
		return roll;
	}

	public void setRoll(int roll) {
		this.roll = roll;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	@Override
	public String toString() {
		return "StudentDTO [roll=" + roll + ", name=" + name + ", bname=" + bname + "]";
	}

}
