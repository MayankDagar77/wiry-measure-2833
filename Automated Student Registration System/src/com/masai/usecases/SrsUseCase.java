package com.masai.usecases;

import java.util.List;
import java.util.Scanner;

import com.masai.app.ExitApp;
import com.masai.bean.Course;
import com.masai.bean.CourseDTO;
import com.masai.bean.Student;
import com.masai.bean.StudentDTO;
import com.masai.dao.AdminDao;
import com.masai.dao.StudentDao;
import com.masai.dao.StudentDaoImpl;
import com.masai.dao.AdminDaoImpl;
import com.masai.dao.RegisterStudentDao;
import com.masai.dao.RegisterStudentDaoImpl;
import com.masai.exceptions.CourseException;
import com.masai.exceptions.StudentException;

public class SrsUseCase {
	
	static Scanner sc = new Scanner(System.in); 
	
	static AdminDao dao = new AdminDaoImpl();
	
	static StudentDao dao2 = new StudentDaoImpl();
	
	static RegisterStudentDao dao3 = new RegisterStudentDaoImpl();
	
	static String adminUserName = "Ratan";
	static int adminPass = 1234;
	
	static String studentUserName = "Mayank";
	static int studentPass = 4786;
	
	
	
	public static void selectOption() {
		
		System.out.println("Select an option to Continue");
		System.out.println("1: Login as Admin"+"\n2: Login as Student"+"\n3: Register as new student"+"\n4: Exit");
		
		int option = sc.nextInt();
		
		while(option < 5) {
			
			if(option == 1) {
				
			 System.out.println("Enter the Admin username");
			 String uName = sc.next();
			 
			 System.out.println("Enter the Admin password");
			 int pass = sc.nextInt();
			 
			 if(adminUserName.equalsIgnoreCase(uName) && adminPass==pass){
				//Admin panel functionality 
				System.out.println("Welcome to Admin Panel");
				adminPanel();
				break;
			 }
			 
			 System.out.println("Wrong admin username or password");
			 selectOption();
			 
			}
			else if(option == 2) {
					
			  System.out.println("Enter the Admin username");
			  String uName = sc.next();
					 
			  System.out.println("Enter the Admin password");
			  int pass = sc.nextInt();
					 
			  if(studentUserName.equalsIgnoreCase(uName) && studentPass==pass){
			    //Student panel functionality
			    System.out.println("Welcome to Student Panel");
			    studentPanel();		
			    break;
			  }
			  
			  System.out.println("Wrong student username or password");
			  selectOption();
			  
			}
			else if(option == 3) {
				System.out.println("Register as a new Student");
			    registerStudent();
			    break;
			}
			else if(option == 4) {
			    ExitApp.exit();
			    break;
			}
			
		}
	}
		
    public static void adminPanel() {
		
			System.out.println("Select an option to Continue");
			System.out.println("1: Add a new Course"+"\n2: Update Fees of Course"
			                   +"\n3: Delete a Course"+"\n4: Search Information about a Course"
					           +"\n5: Create Batch under a Course"
			                   +"\n6: Allocate Students in a Batch under a Course"
					           +"\n7: Update total seats of a Batch"
			                   +"\n8: View the Students of every Batch"
					           +"\n9: Go Back to Main");
			int choice = sc.nextInt();
			
			switch(choice) {
			 
			  case 1: //Add course method
				 System.out.println("Enter the Course name:");
				 String cname = sc.next();
				
				 System.out.println("Enter the Course Fee:");
				 int fee = sc.nextInt();
				
				 String result = dao.addCourse(cname, fee);
				
				 System.out.println(result);
				
				 adminPanel();
				 break;
				
			  case 2:
				 System.out.println("Enter Fee:"); 
				 int fees = sc.nextInt();
				 
				 System.out.println("Enter Course Id:");
				 int cid = sc.nextInt();
				 
				 try {
					 
					String result2 = dao.updateFee(fees, cid);
					System.out.println(result2);
					System.out.println();
					
				 } 
				 catch (CourseException e) {
					System.out.println(e.getMessage());
					System.out.println();
				 }
				 
				 adminPanel();
				 break;
				
			  case 3:
				 System.out.println("Enter Course Id:");
				 int cid2 = sc.nextInt(); 
				 
				 String result3 = dao.deleteCourse(cid2);
				 System.out.println(result3);
				 System.out.println();
		
				 adminPanel();
				 break;
				  
			  case 4:
				 System.out.println("Enter the Course Name:");
				 String cname2 = sc.next();
				 
				 try {
				    Course course = dao.courseDetails(cname2);
					
					System.out.println(course);
				 } 
				 catch (CourseException e) {
					System.out.println(e.getMessage());
					System.out.println();
				 }
				 
				 adminPanel();
				 break;
				 
			  case 5:  
				 System.out.println("Enter the Batch Name to be added:");
				 String bname = sc.next();
				 
				 System.out.println("Enter total seats of Batch");
				 int totalCount = sc.nextInt();
				 
				 System.out.println("Enter students enrolled in Batch");
				 int studCount = sc.nextInt();
				 
				 System.out.println("Enter the Course Id:");
				 int cid3 = sc.nextInt();
				 
				 String result4 = dao.createBatch(bname, totalCount, studCount, cid3);
				 
				 System.out.println(result4);
				 System.out.println();
				 
				 adminPanel();
				 break;
				  
			  case 6:
				 System.out.println("Enter Student's roll no:");
				 int roll = sc.nextInt();
				 
				 System.out.println("Enter Batch name to be allocated:");
				 String bname2 = sc.next();
				 
				 String result5 = dao.allocateBatch(roll, bname2);
				 
				 System.out.println(result5);
				 System.out.println();
				 
			     adminPanel(); 
			     break;
				  
			  case 7:
				 System.out.println("Enter the new total seats for batch:");
				 int totalCount2 = sc.nextInt();
				 
				 System.out.println("Enter the batch name for total seat updation:");
				 String bname3 = sc.next(); 
				 
				 String result6 = dao.updateTotalSeats(totalCount2, bname3);
			     
				 System.out.println(result6);
				 
				 adminPanel(); 
				 break;
				 
			  case 8:   
				 try {
					
				    List<StudentDTO> dtos = dao.viewStudentsOfAllBatch();
					
					dtos.forEach(dto -> System.out.println(dto));
					
				 } 
				 catch (StudentException e) {
					System.out.println(e.getMessage());
				 }
				     
				 adminPanel(); 
				 break;
			     
			  case 9:
				 SrsUseCase.selectOption();   
				 break; 
				
			  default:
				 adminPanel();
				 break; 
			
			}	
			
	}
    
    public static void studentPanel() {
    	System.out.println("Select an option to Continue");
    	System.out.println("1: Register in a Course"+"\n2: Update Course Allocation"
    	                   +"\n3: All courses list and seat availability"
    			           +"\n4: Go Back to Main");
    	int choice = sc.nextInt();
		
		switch(choice) {
    	 
		  case 1:
			 System.out.println("Enter Username:");
			 String uname = sc.next();
				
			 System.out.println("Enter Password:");
			 String password = sc.next();
			 
			 System.out.println("Enter the Course name to register:");
			 String cname = sc.next();
			 
			 try {
				
				String result = dao2.registerStudentInCourse(uname, password, cname);
				
				System.out.println(result);
				
			 } 
			 catch (StudentException e) {
				System.out.println(e.getMessage());
			 }
			 
			 studentPanel();
			 break;
		
		  case 2:
			 System.out.println("Enter Username:");
			 String uname2 = sc.next();
					
			 System.out.println("Enter Password:");
			 String password2 = sc.next();
				 
			 System.out.println("Enter the Course name to register:");
			 String cname2 = sc.next();
			 
			 try {
				 
				String result2 = dao2.updateCourse(uname2, password2, cname2);
				
				System.out.println(result2);
				
			 } 
			 catch (StudentException e) {
				System.out.println(e.getMessage());
			 }
			  
			 studentPanel(); 
			 break;
			 
		  case 3:
			 try {
			    
			   List<CourseDTO> dtoc = dao2.allCourseDetails();
				
			   dtoc.forEach(dto -> System.out.println(dto));
				
			 } 
			 catch (CourseException e) {
				System.out.println(e.getMessage());
			 }
			  
			 studentPanel(); 
			 break;
			 
		  case 4:
			 SrsUseCase.selectOption();
			 break;
			 
		  default:
			 studentPanel(); 
			 break;
		
		}
    	
    }
    
    public static void registerStudent() {
    	
       System.out.println("Enter Student Name");
	   String sname = sc.next();
		
	   System.out.println("Enter Student Marks");
	   int marks = sc.nextInt();  
	   System.out.println("Enter Student Email");
	   String email = sc.next();
                 
	   System.out.println("Enter Student Password");
	   String password = sc.next();
    	                     
       Student student = new Student(marks, sname, marks, email, password); 	          
	  
       String result = dao3.registerStudent(student);
	  
       System.out.println(result);
       
       SrsUseCase.selectOption();
       
       return;
    	
    }
   
  
}
