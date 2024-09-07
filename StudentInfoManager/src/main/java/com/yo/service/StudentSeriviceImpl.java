package com.yo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yo.entity.LoginData;
import com.yo.entity.Student;
import com.yo.reposiory.IAuthRepositry;
import com.yo.reposiory.IStudentRepository;

@Service
public final class StudentSeriviceImpl implements IStudentService {

	@Autowired
	private IStudentRepository repository;
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private IAuthRepositry authRepo;
	
	@Override
	public String register(Student student) {
		repository.insert(student);
		return "Student name-:"+student.getName()+" has saved successfully.";
	}

	@Override
	public String modifyStudentData(String name,Student student) {
		Student std=repository.findByName(name);
		if (std!=null) {
			std.setName(student.getName());
			std.setContactDetails(student.getContactDetails());
			std.setAddress(student.getAddress());
			std.setPincode(student.getPincode());	
			repository.save(std);
			
			return "Record Updated Successfully.";
		}
		
		
		return "name:"+student.getName()+" Doesn't exist! ";
	}

	@Override
	public Student findByStudentName(String name) {
		return repository.findByName(name);
	}
	
	@Override
	public List<Student> getAllStudents() {
		return repository.findAll();
	}

	@Override
	public String removeStudentData(String name) {
		Student std=repository.findByName(name);
		if (std!=null) {
			repository.delete(std);
			return "Student name-:"+name+" has been remove from the db";
		}
		return "name:"+name+" Doesn't exist!";
	}
	@Override
	 public String addUser(LoginData data) {
	        data.setPassword(encoder.encode(data.getPassword()));
	        authRepo.save(data);
	        return "user added to system ";
	    }
}
