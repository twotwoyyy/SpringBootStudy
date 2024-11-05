package com.sist.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
/*
 * Table: reactboard
	Columns:
	no int AI PK 
	name varchar(100) 
	subject varchar(2000) 
	content text 
	pwd varchar(30) 
	regdate datetime 
	hit int
	
	create table reactBoard(
	no int auto_increment,
    name varchar(100) not null,
    subject varchar(2000) not null,
    content text not null,
    pwd varchar(30) not null,
    regdate datetime default now(),
    hit int default 0,
    primary key(no)
);

 */

@Entity(name="reactboard")
@Data
public class ReactBoardEntity {
	@Id
	private int no;
	private String name;
	private String subject;
	private String content;
	@Column(insertable = true, updatable = false)
	private String pwd;
	@Column(insertable = true, updatable = false )
	private String regdate;
	private int hit;

	@PrePersist
	public void regdate() {
		this.regdate=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}
