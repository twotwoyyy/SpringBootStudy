package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
// findByFno(int fno)
@Entity(name = "food_house")
@Data
public class FoodHouseEntity {
	@Id
	private int fno;
	private int jjimcount,likecount,hit,replycount;
	private String name,type,phone,address,theme,poster,images,time,parking,content,rdays;
	private Double score;
}
