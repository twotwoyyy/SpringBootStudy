package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sist.web.entity.ChefEntity;
// ID의 데이터형을 써주어야 한다
public interface ChefDAO extends JpaRepository<ChefEntity, String>{
	public ChefEntity findByChef(String chef);
}
