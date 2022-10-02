package com.masai.dao;

import java.util.List;

import com.masai.bean.Course;
import com.masai.bean.Student;
import com.masai.bean.StudentDTO;
import com.masai.exceptions.BatchException;
import com.masai.exceptions.CourseException;
import com.masai.exceptions.StudentException;

public interface AdminDao {
	
   //Admin methods --->	
	
   //Method to add new course in course table ->
   public String addCourse(String cname,int fee);	
   
   //Update fees of course ->
   public String updateFee(int fee,int cid) throws CourseException; 
	
   //Deleting a course from course table ->
   public String deleteCourse(int cid); // user defined course exception needed or not??
   
   //Search Information about a course ->	
   public Course courseDetails(String cname) throws CourseException;
   
   //Create Batch under a Course -> (Course & Batch table relationship)
   public String createBatch(String bname,int totalCount,int studCount,int cid);
   
   //Allocate students in a Batch under a Course -> (Student & Batch table relationship)
   public String allocateBatch(int roll,String bname);
   
   //Update total seats of a Batch ->
   public String updateTotalSeats(int totalCount,String bname);
   
   //View the students of every batch ->
   public List<StudentDTO> viewStudentsOfAllBatch() throws StudentException;
    
   
}
