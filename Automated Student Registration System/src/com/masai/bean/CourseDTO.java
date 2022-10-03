package com.masai.bean;

public class CourseDTO {

	private String cname;
	private int fee;
	private String bname;
	private int avalSeats;

	public CourseDTO() {

	}

	public CourseDTO(String cname, int fee, String bname, int avalSeats) {
		super();
		this.cname = cname;
		this.fee = fee;
		this.bname = bname;
		this.avalSeats = avalSeats;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public int getAvalSeats() {
		return avalSeats;
	}

	public void setAvalSeats(int avalSeats) {
		this.avalSeats = avalSeats;
	}

	@Override
	public String toString() {
		return "CourseDTO [cname=" + cname + ", fee=" + fee + ", bname=" + bname + ", avalSeats=" + avalSeats + "]";
	}

}
