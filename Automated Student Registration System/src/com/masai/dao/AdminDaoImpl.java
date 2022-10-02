package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.bean.Course;
import com.masai.bean.StudentDTO;
import com.masai.exceptions.BatchException;
import com.masai.exceptions.CourseException;
import com.masai.exceptions.StudentException;
import com.masai.utility.DBUtil;

public class AdminDaoImpl implements AdminDao{

	@Override
	public String addCourse(String cname, int fee){
		
	   String msg = "No Course Added";
	   
	   try(Connection conn = DBUtil.provideConnection()){
		   
		 PreparedStatement ps = conn.prepareStatement("Insert into course(cname,fee) values(?,?)");
		  
		 ps.setString(1,cname);
		 ps.setInt(2, fee);
		 
		 int x = ps.executeUpdate();
		  
		 if(x>0)
			 msg = "Course Added Successfully";
		 
	   }
	   catch(SQLException e){
		  msg = e.getMessage();
	   }
	  
	   
		
	   return msg;	
	}

	@Override
	public String updateFee(int fee, int cid) throws CourseException {
		
		 String msg = "No fee updated";
		  
		 try(Connection conn = DBUtil.provideConnection()){
			 
			PreparedStatement ps = conn.prepareStatement("Update course set fee = ? where cid = ?");
			
			ps.setInt(1, fee);
			ps.setInt(2, cid);
			
			int x = ps.executeUpdate();
			  
			if(x>0)
			    msg = "Course Fee Updated";
			else {
				//msg = "Course Id doesn't match record";
				//or
				throw new CourseException("Course Id doesn't match record"); //Or don't use any
			}
			   
		 }
		 catch(SQLException e){
			 msg = e.getMessage(); 
		 }
		  
			
		 return msg;	
	}

	@Override
	public String deleteCourse(int cid) {
		
	   String msg = "Course Id doesn't match record";
	   
	   try(Connection conn = DBUtil.provideConnection()){
			 
		  PreparedStatement ps = conn.prepareStatement("Delete from course where cid = ?");
		 
		  ps.setInt(1, cid);
		 
		  int x = ps.executeUpdate();
		  
		  if(x>0)
			 msg = "Course deleted";
		 
	   }
	   catch(SQLException e) {
		  msg = e.getMessage(); 
	   }
	   
		
	   return msg;	
	}

	@Override
	public Course courseDetails(String cname) throws CourseException {
		
	  Course course = null;
	  
	  try(Connection conn = DBUtil.provideConnection()){
			 
		PreparedStatement ps = conn.prepareStatement("Select * from course where cname = ?");
		
		ps.setString(1, cname);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			
			int c = rs.getInt("cid");
			String cn = rs.getString("cname"); 
			int f = rs.getInt("fee");
			
			course = new Course(c,cn,f);
			
		}
		else
			throw new CourseException("There is no course with the name "+cname);
		
	  }
	  catch(SQLException e) {
		    throw new CourseException(e.getMessage());
	  }
	   
	  
	  return course;	
	}

	@Override
	public String createBatch(String bname,int totalCount,int studCount,int cid) {
		
		//first create batch into batch table
	
	   String msg = "Course Id "+cid+"is not in records";	
		
	   try(Connection conn = DBUtil.provideConnection()){
		   
		 PreparedStatement ps1 = conn.prepareStatement("Insert into batch values(?,?,?)");
		 
		 ps1.setString(1,bname);
		 ps1.setInt(2,totalCount);
		 ps1.setInt(3,studCount);
		 
         int x = ps1.executeUpdate();
		  
        if(x>0) { 
		 PreparedStatement ps2 = conn.prepareStatement("Insert into course_batch values(?,?)"); 
		   
		 ps2.setInt(1,cid);
		 ps2.setString(2,bname);
		   
		 int y = ps2.executeUpdate();
		 
		 if(y>0)
			 msg = "Course Id "+cid+" added a Batch "+bname;
		 
		 
        }
   
		 
	   }
	   catch(SQLException e) {
		  msg = e.getMessage(); 
	   }
	  
	   
	   return msg;	
	}

	@Override
	public String allocateBatch(int roll, String bname) {
		
	  String msg = "No student allocated";	
		
	  try(Connection conn = DBUtil.provideConnection()){
		  
		PreparedStatement ps = conn.prepareStatement("Insert into student_batch values(?,?)");  
		  
		ps.setInt(1, roll);
		ps.setString(2, bname);
		
		int x = ps.executeUpdate();
		
		if(x>0) {
			PreparedStatement ps2 = conn.prepareStatement("Update batch set studCount = studCount + 1 where bname = ?");
			
			ps2.setString(1,bname);
			
			int y = ps2.executeUpdate();
			
			if(y>0) {
			    msg = "Student allocated to batch "+bname;
			}
		}
		
	  }
	  catch(SQLException e){
		 msg = e.getMessage(); 
	  }
	  
	  
	  return msg;
	}

	@Override
	public String updateTotalSeats(int totalCount,String bname) {

	   String msg = "Nothing Updated";
		  
	   try(Connection conn = DBUtil.provideConnection()){
			  
	     PreparedStatement ps = conn.prepareStatement("Update batch set totalCount = ? where bname = ?");
		   
		 ps.setInt(1, totalCount);
		 ps.setString(2, bname);
		 
		 int x = ps.executeUpdate();
		 
		 if(x>0)
			 msg = "Total Seats Updated";
		 
		 
	   }
	   catch(SQLException e) {
		   msg = e.getMessage();
	   }
		  

	   return msg;	   		
	}

	@Override
	public List<StudentDTO> viewStudentsOfAllBatch() throws StudentException{
		
	   List<StudentDTO> dtos = new ArrayList<>();
	   
	   try(Connection conn = DBUtil.provideConnection()){
		   
		 PreparedStatement ps = conn.prepareStatement("Select s.roll,s.name,b.bname from student s Inner join batch b Inner join student_batch sb ON s.roll = sb.roll AND b.bname = sb.bname");  
		   
		 ResultSet rs = ps.executeQuery();  
		   
		 while(rs.next()) {
			 
			 int r = rs.getInt("roll");
			 String sn = rs.getString("name");
			 String bn = rs.getString("bname");
			 
			 StudentDTO dto = new StudentDTO(r, sn, bn);
			 
			 dtos.add(dto);
			 
		 } 
		   
	   }
	   catch(SQLException e){
		  throw new StudentException(e.getMessage());
	   }
		
	   if(dtos.isEmpty())
		     throw new StudentException("No student is allocated to any batch");
	   
		
	   return dtos;	
	}

	
}
