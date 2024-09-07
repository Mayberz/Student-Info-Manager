package com.yo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yo.controller.StudentController;
import com.yo.entity.LoginData;
import com.yo.entity.Role;
import com.yo.entity.Student;
import com.yo.security.CustomUserDetailsService;
import com.yo.security.JwtService;
import com.yo.service.StudentSeriviceImpl;


@WebMvcTest(controllers = StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class StudentInfoManagerApplicationTests {
	
	
	@Autowired
	MockMvc mockMvc;
	@MockBean
	private StudentSeriviceImpl service;
	
	@MockBean
	private CustomUserDetailsService customUserDetailsService;

	  @MockBean private JwtService jwtService;
	  
	  @MockBean private AuthenticationManager authenticationManager;
	 

	@Test
	public void testaddStudetTest() throws JsonProcessingException, Exception {

		Student std = new Student(
				"A12", "John Locke","Califonia" ,
				464333, List.of("8485783793", "john@gmail.com"));
		String expectedString = "Data Added with the ID ->" + std.getName();
		when(service.register(std)).thenReturn(expectedString);
		mockMvc.perform(post("/student/api/v1/add").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(std))).andExpect(status().isCreated());
	}
	
	  @Test
	    public void testModifyData() throws Exception {
		  Student std = new Student(
					"A12", "John Locke","Califonia" ,
					464333, List.of("8485783793", "john@gmail.com"));
	        String modifyMsg = "Student data updated successfully!";
	        
	        when(service.modifyStudentData("John Locke", std)).thenReturn(modifyMsg);
	        
	        mockMvc.perform(put("/student/api/v1/update/John Locke")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(std)))
	                .andExpect(status().isOk())
	                .andExpect(content().string(modifyMsg));
	    }

	    @Test
	    public void testFindByStudentName() throws Exception {
	    	Student std = new Student(
					"A12", "John Locke","Califonia" ,
					464333, List.of("8485783793", "john@gmail.com"));
	        when(service.findByStudentName("John Locke")).thenReturn(std);
	        
	        mockMvc.perform(get("/student/api/v1/findByName/John Locke"))
	                .andExpect(status().isFound())
	                .andExpect(jsonPath("$.name").value("John Locke"))
	                .andExpect(jsonPath("$.address").value("Califonia"));
	    }

	    @Test
	    public void testGetAllStudents() throws Exception {
	        List<Student> students = Arrays.asList(
	                new Student("A11", "Subrat", "Odisha", 455563, List.of("343532225", "subrat@gmail.com")),
	                new Student("A12", "Ronit", "Odisha", 344551, List.of("3434342356", "ronit@gmail.com"))
	        );
	        when(service.getAllStudents()).thenReturn(students);
	        
	        mockMvc.perform(get("/student/api/v1/all"))
	                .andExpect(status().isFound())
	                .andExpect(jsonPath("$[0].name").value("Subrat"))
	                .andExpect(jsonPath("$[1].name").value("Ronit"));
	    }

	    @Test
	    public void testDeleteStudentData() throws Exception {
	    	
	        String deleteMsg = "Student data deleted successfully!";
	        
	        when(service.removeStudentData("John Wick")).thenReturn(deleteMsg);
	        
	        mockMvc.perform(delete("/student/api/v1/delete/John Wick"))
	                .andExpect(status().isOk())
	                .andExpect(content().string(deleteMsg));
	    }

	    @Test
	    public void testSignUp() throws Exception {
	        LoginData loginData = new LoginData("A100","Naruto", "sasuke", Role.PRINCIPLE);
	        String signUpMsg = "User registered successfully!";
	        
	        when(service.addUser(loginData)).thenReturn(signUpMsg);
	        
	        mockMvc.perform(post("/student/api/v1/signup")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(loginData)))
	                .andExpect(status().isCreated())
	                .andExpect(content().string(signUpMsg));
	    }

	   
	 

	

}
