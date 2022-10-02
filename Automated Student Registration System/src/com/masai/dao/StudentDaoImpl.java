package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.bean.CourseDTO;
import com.masai.exceptions.CourseException;
import com.masai.exceptions.StudentException;
import com.masai.utility.DBUtil;

public class StudentDaoImpl implements StudentDao{

	@Override
	public String registerStudentInCourse(String username, String password, String cname) throws StudentException {
		
		
		 String msg = "Course not found";	
			
		  try(Connection conn = DBUtil.provideConnection()){
			  
		    PreparedStatement ps = conn.prepareStatement("Select roll from student where email = ? AND password = ?");  

		    ps.setString(1, username);
		    ps.setString(2, password);
		    
		    ResultSet rs = ps.executeQuery();
		    
		    if(rs.next()) {
		    	
		    	int r = rs.getInt("roll");
		    	
		    	PreparedStatement ps2 = conn.prepareStatement("Select cid from course where cname = ?");
		    	
		    	ps2.setString(1, cname);
		    	
		    	ResultSet rs2 = ps2.executeQuery();
		    	
		    	if(rs2.next()) {
		    		
		    	    int c = rs2.getInt("cid");
		    		
		    		PreparedStatement ps3 = conn.prepareStatement("Insert into student_course values(?,?)");
		    	    
		    	    ps3.setInt(1, r);
		    	    ps3.setInt(2, c);
		    	    
		    	    int x = ps3.executeUpdate();
		    	    
		    	    if(x>0)
		    	    	msg = "Student has been enrolled in "+ cname;
		    	    
		    		
		    	}
		    	else {
		    		throw new StudentException("No course id associated with course "+cname);
		    	}
		    	
		    }
		    else {
		    	throw new StudentException("Invalid username or password");	
		    }
		    
		    
		  }
		  catch(SQLException e) {
			 msg = e.getMessage(); 
		  }
		  

		return msg;	
	}

	@Override
	public String updateCourse(String username, String password, String cname) throws StudentException {
	
	  String msg = "Course not found";
		
	  try(Connection conn = DBUtil.provideConnection()){
		  
		 PreparedStatement ps = conn.prepareStatement("Select roll from student where email = ? AND password = ?");  

		 ps.setString(1, username);
		 ps.setString(2, password);
		    
		 ResultSet rs = ps.executeQuery();
		 
		 if(rs.next()) {
		    	
		    int r = rs.getInt("roll");
		    
		    PreparedStatement ps2 = conn.prepareStatement("Delete from student_course where roll = ?");
		   
		    ps2.setInt(1, r);
		    
		    int x = ps2.executeUpdate();
		    	
		    if(x>0) {
		    	
		      String result = registerStudentInCourse(username, password, cname);	
		    	
		      msg = result;	
		      
		    }
		    else {
		       throw new StudentException("Nothing Removed"); 	
		    }
		    	
		 } 
		 else {
		    throw new StudentException("Invalid Username or Password");	 
		 }
		
	  }
	  catch(SQLException e) {
		throw new StudentException(e.getMessage());
	  }
	  
		
	  return msg;	
	}

	@Override
	public List<CourseDTO> allCourseDetails() throws CourseException{
	 	
	  List<CourseDTO> dtoc = new ArrayList<>();
	  
	  try(Connection conn = DBUtil.provideConnection()){
		  
		PreparedStatement ps = conn.prepareStatement(" Select c.cname,c.fee,b.bname,b.totalCount-b.studCount avalSeats from course c Inner Join batch b Inner Join course_batch cb ON c.cid = cb.cid AND b.bname = cb.bname");  
		  
		ResultSet rs = ps.executeQuery();
		
		 while(rs.next()) {
		
		   String cn = rs.getString("cname");
		   int f = rs.getInt("fee");
		   String bn = rs.getString("bname");
		   int as = rs.getInt("avalSeats");
		
		   CourseDTO dto = new CourseDTO(cn, f, as);
		
		   dtoc.add(dto);	
		   
		 }
		  
	   }
	   catch(SQLException e) {
		  throw new CourseException(e.getMessage());
	   }
	  
		
	  return dtoc;	
	}

	
	
}
