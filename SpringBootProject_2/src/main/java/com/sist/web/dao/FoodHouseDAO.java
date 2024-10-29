package com.sist.web.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.FoodHouseData;
import com.sist.web.entity.FoodHouseEntity;
import java.util.*;
/*
 *	지원 자격 
 *	  Java,Spring,MySql
 *
 *	MySql
 *   1. 페이징 => LIMIT 시작, 개수 
 *   2. LIKE => '%'||?||'%' => CONCAT('%',?,'%')
 *   3. DATE => DATETIME => sysdate : now()
 *   4. NVL => isnull
 *   
 *   => 오라클     MySql(mariaDB) => 3306, 포트번호와 driver 동일
 *     NUMBER      int, double
 *     VARCHAR2    varchar
 *     CLOB        text
 *     DATE        datetime 
 */
@Repository
public interface FoodHouseDAO extends JpaRepository<FoodHouseEntity, Integer>{
	// 목록 출력 
	@Query(value="SELECT fno,poster,name FROM food_house ORDER BY fno ASC "
			+"LIMIT :start,12",nativeQuery = true) // 페이지 나눌 때 (#{start} 대신 :start
	public List<FoodHouseData> foodListData(@Param("start") int start);
	// 상세 보기 
	public FoodHouseEntity findByFno(int fno);
		// => SELECT * FROM food_house WHERE fno=?
	// 조회수 증가 (Hit) => Update => (save()) 함수 
	
	// 검색
	
	// CRUD => 게시판 	
}
