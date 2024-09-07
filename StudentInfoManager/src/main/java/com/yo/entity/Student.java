package com.yo.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	@Id
	private String _id;
	private String name;
	private String address;
	private Integer pincode;
	private List<String> contactDetails;

}
