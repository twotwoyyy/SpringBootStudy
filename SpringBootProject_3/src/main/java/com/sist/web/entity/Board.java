package com.sist.web.entity;

import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.persistence.Id;
import lombok.Data;
@Document(indexName = "reactboard")
@Data
public class Board {
	@Id
	private int no;
	private int hit;
	private String name,subject,content,pwd,regdate;
}
