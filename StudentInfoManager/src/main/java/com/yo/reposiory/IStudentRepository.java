package com.yo.reposiory;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yo.entity.Student;

public interface IStudentRepository extends MongoRepository<Student, String> {

	public Student findByName(String name);

}
