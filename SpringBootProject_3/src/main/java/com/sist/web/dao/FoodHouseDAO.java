package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.sist.web.entity.*;

@Repository
public interface FoodHouseDAO extends JpaRepository<FoodHouseEntity, Integer>{
	// 9개의 데이터 => 메인에 출력
	@Query(value="SELECT fno,name,poster,score,hit,jjimcount,type,content,theme "
			+"FROM food_house ORDER BY hit DESC "
			+"LIMIT 0,9", nativeQuery = true) // 이 쿼리 문장 그대로 가져와라
	public List<FoodHouseVO> foodHitTop9();
	
	// 자동으로 SELECT * FROM food_house WHERE fno=? => 자동 SQL 문장 이용 
	public FoodHouseEntity findByFno(int fno);
	
}
