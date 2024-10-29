package com.sist.web.entity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
 * Table: board
	Columns:
	no int AI PK 
	name varchar(50) 
	subject varchar(1000) 
	content text 
	pwd varchar(10) 
	regdate datetime 
	hit int
 */

@Entity(name="board")
@Data
@NoArgsConstructor // 생성자 : 매개변수 없는 default 생성자
@AllArgsConstructor // 생성자 : 매개변수 있는 생성자 
public class BoardEntity {
	@Id
	private int no;
	private int hit;
	private String name;
	private String subject;
	private String content;
	@Column(insertable = true, updatable = false)
	private String pwd; // 비밀번호는 insert는 되는데 update는 불가하게 만들어야 한다 
	@Column(insertable = true, updatable = false)
	private String regdate;
	
	// 날짜 변환
	@PrePersist 
	public void regdate() {
		this.regdate=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
	}
}
