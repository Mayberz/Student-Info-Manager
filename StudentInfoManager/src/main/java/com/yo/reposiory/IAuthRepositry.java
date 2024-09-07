package com.yo.reposiory;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yo.entity.LoginData;

public interface IAuthRepositry extends MongoRepository<LoginData, String>{

  LoginData findByUsername(String username);

}
