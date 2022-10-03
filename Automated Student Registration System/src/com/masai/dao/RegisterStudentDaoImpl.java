package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.masai.bean.Student;
import com.masai.utility.DBUtil;

public class RegisterStudentDaoImpl implements RegisterStudentDao{

	@Override
	public String registerStudent(Student student) {
		
	  String msg = "No Student Registered";
	  
	  try(Connection conn = DBUtil.provideConnection()){
		  
		PreparedStatement ps = conn.prepareStatement("Insert into student(name,marks,email,password)"
				               + "values(?,?,?,?)");
		  
		ps.setString(1,student.getName());
		ps.setInt(2,student.getMarks());
		ps.setString(3,student.getEmail());
	    ps.setString(4,student.getPassword());
		
	    int x = ps.executeUpdate();
	    
	    if(x>0)
	    	msg = "Student Registered Successfully";
	    
	  }
	  catch(SQLException e){
		msg = e.getMessage();
	  }
	  
	  
	  return msg;	
	}

	
}
