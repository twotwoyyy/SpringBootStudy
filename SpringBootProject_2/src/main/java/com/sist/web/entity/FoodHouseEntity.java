package com.sist.web.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
 *   VO
 *   DTO
 *   Entity : 다른 데이터를 첨부할 수 없다, 테이블의 컬럼만 추가해야 한다 
 *            컬럼명과 반드시 일치해야 한다 
 *            => 자동으로 insert/update/delete 문장을 만들어주기 때문에 
 *            => select : SQL 문장 / 자동 SQL 문장 생성 
 *            => 검색 
 *               findBy컬럼명()
 *               => findByFno(int fno)
 *               => WHERE fno=
 *               => 메소드로 SQL 문장을 처리
 *               => 이 과정을 JPA라고 한다 (Hibernate) => 자동 SQL문장 제작 : method 패턴 
 *               => address="" AND type="" 
 *               => findByAddressANDType(String address,String type)
 *                  ================================================
 *                  SQL문장 제작도 가능 (동시 처리 가능) 
 *	 
		 *Table: food_house
		Columns:
		FNO int 
		NAME text 
		TYPE text 
		PHONE text 
		ADDRESS text 
		SCORE double 
		THEME text 
		POSTER text 
		IMAGES text 
		TIME text 
		PARKING text 
		CONTENT text 
		RDAYS text 
		JJIMCOUNT int 
		LIKECOUNT int 
		HIT int 
		REPLYCOUNT int
 */
@Entity(name = "food_house")
@Data
public class FoodHouseEntity {
	@Id // 시퀀스 insert 할 때 자동 증가 
	private int fno;
	private int jjimcount,likecount,hit,replycount;
	private String name,type,phone,address,theme,poster,images,time,parking,content,rdays;
	private Double score;
	
}
