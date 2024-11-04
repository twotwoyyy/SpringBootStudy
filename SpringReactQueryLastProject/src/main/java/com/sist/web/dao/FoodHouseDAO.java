package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
	
	@Query(value="SELECT fno,name,poster,score,hit,jjimcount,type,content,theme "
			+"FROM food_house ORDER BY fno ASC "
			+"LIMIT :start,12",nativeQuery = true)
	// nativeQuery = true => 자체 SQL 문장을 제작 (지금 작성된 SQL문장을 변경하지 말고 그대로 수행하라 JPQL => Entity 클래스 중심)
	public List<FoodHouseVO> foodListData(@Param("start") int start);
	// count() => 총페이지 구할 필요 없이 이미 count()라는 함수가 존재
	// 이미 존재하는 메소드 이용 => 복잡한 쿼리 : SELECT 
	// 메소드 패턴을 이용 => SELECT에서 검색
	
	// 검색 => LIKE => 오라클하고 다르다 '%'||#{}||'%'
	// JPA => JOIN
	@Query(value="SELECT fno,name,poster,score,hit,jjimcount,type,content,theme "
			+"FROM food_house WHERE address LIKE CONCAT('%',:address,'%') ORDER BY fno ASC "
			+"LIMIT :start,12",nativeQuery = true)
	public List<FoodHouseVO> foodFindData(@Param("start") int start,@Param("address") String address);
	
	@Query(value="SELECT CEIL(COUNT(*)/12.0) "
			+"FROM food_house "
			+"WHERE address LIKE CONCAT('%',:address,'%')")
	public int foodFindTotalPage(@Param("address") String address);
}
