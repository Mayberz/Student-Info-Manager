package com.yo.service;

import java.util.List;

import com.yo.entity.LoginData;
import com.yo.entity.Student;

public interface IStudentService {
	
	public String register(Student student);
	public String modifyStudentData(String name,Student student);
	public Student findByStudentName(String name);
	public List<Student> getAllStudents();
	public String removeStudentData(String name);
	 public String addUser(LoginData data);
	        
}
