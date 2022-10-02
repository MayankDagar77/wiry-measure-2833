package com.masai.dao;

import java.util.List;

import com.masai.bean.CourseDTO;
import com.masai.exceptions.CourseException;
import com.masai.exceptions.StudentException;

public interface StudentDao {
	
	//Student methods --->	
	   
    //Register yourself(student) in a course with a username(email) and password ->
	public String registerStudentInCourse(String username,String password,String cname) throws StudentException;
	   
	//Student can update the course allocation details ->
	public String updateCourse(String username,String password,String cname) throws StudentException;

	//Student can see all the available course list and their seat availability ->
	public List<CourseDTO> allCourseDetails() throws CourseException;
	
	
}
