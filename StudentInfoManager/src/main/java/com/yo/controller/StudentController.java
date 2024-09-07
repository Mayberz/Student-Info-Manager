package com.yo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yo.DTO.AuthRequest;
import com.yo.entity.LoginData;
import com.yo.entity.Student;
import com.yo.security.JwtService;
import com.yo.service.IStudentService;

@RestController
@RequestMapping("/student/api/v1")
public class StudentController {
	
	
	@Autowired
	private IStudentService service;
	
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtService jwtService;
	
	@PreAuthorize("hasRole('PRINCIPLE') or hasRole('TEACHER')")
	@PostMapping("/add")
	public ResponseEntity<String> AddStudent(@RequestBody Student student){
		String addMsg=service.register(student);
		return addMsg !=null ? new ResponseEntity<> (addMsg,HttpStatus.CREATED)
				: new ResponseEntity<> (HttpStatus.BAD_REQUEST);	
	}
	@PreAuthorize("hasRole('PRINCIPLE')")
	@PutMapping("/update/{name}")
	public ResponseEntity<String> ModifyData(@PathVariable String name,@RequestBody Student student){
		String modifyMsg=service.modifyStudentData(name, student);
		return modifyMsg !=null ? new ResponseEntity<>(modifyMsg,HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_MODIFIED);		
	}
	@PreAuthorize("hasRole('PRINCIPLE') or hasRole('TEACHER')")
	@GetMapping("/findByName/{name}")
	public ResponseEntity<Student> findByStudentName(@PathVariable String name){
		Student student=service.findByStudentName(name);
		return student !=null ? new ResponseEntity<>(student,HttpStatus.FOUND)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasRole('PRINCIPLE') or hasRole('TEACHER')")
	@GetMapping("/all")
	public ResponseEntity<List<Student>> getAll(){
		List<Student> listOfStudents=service.getAllStudents();
	return listOfStudents !=null ? new ResponseEntity<>(listOfStudents,HttpStatus.FOUND)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@PreAuthorize("hasRole('PRINCIPLE')")
	@DeleteMapping("/delete/{name}")
	public ResponseEntity<String> deleteStudentData(@PathVariable String name){
		String deleteMsg=service.removeStudentData(name);
		return deleteMsg !=null ? new ResponseEntity<>(deleteMsg,HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@PostMapping("/signup")
	public  ResponseEntity<String> signUp(@RequestBody LoginData data){
		String addMsg=service.addUser(data);
		return addMsg !=null ? new ResponseEntity<> (addMsg,HttpStatus.CREATED)
				: new ResponseEntity<> (HttpStatus.BAD_REQUEST);	
	}
	@PostMapping("/authenticate")
	public String AuthAndGetToken(@RequestBody AuthRequest request) {
		Authentication authentication=authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwtService.genToken(request.getUsername());
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}
		
	}

}
