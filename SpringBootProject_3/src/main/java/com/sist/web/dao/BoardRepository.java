package com.sist.web.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.entity.Board;

@Repository
public interface BoardRepository extends ElasticsearchRepository<Board, Integer>{
	public List<Board> findByName(String name);
	
}
