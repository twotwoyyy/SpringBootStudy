package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name="seoul_location") // @Document(indexname="")
@Data
// table => index
// row => document
public class SeoulEntity {
	@Id
	private int no;
	private String title,poster,msg,address;
}
