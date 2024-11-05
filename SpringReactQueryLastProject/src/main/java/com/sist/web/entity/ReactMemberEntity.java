package com.sist.web.entity;
/*
 * Table: reactmember
Columns:
no int AI PK 
id varchar(20) 
name varchar(50) 
pwd varchar(20) 
sex varchar(20)
 */

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Entity(name="reactmember")
@Data
public class ReactMemberEntity {
	@Id
	private int no;
	private String id,name,sex,pwd;
}
