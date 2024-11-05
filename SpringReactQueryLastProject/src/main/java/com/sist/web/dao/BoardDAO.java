package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.entity.*;
import com.sist.web.entity.ReactBoardEntity;

@Repository
public interface BoardDAO extends JpaRepository<ReactBoardEntity, Integer>{
	@Query(value="SELECT * FROM reactBoard ORDER BY no DESC LIMIT :start,10", nativeQuery = true)
	public List<ReactBoardEntity> boardListData(@Param("start") int start);
	
	public ReactBoardEntity findByNo(int no);
	
	// 총 페이지 구하기 (count())
	
	// save()
	
	// delete()
}
