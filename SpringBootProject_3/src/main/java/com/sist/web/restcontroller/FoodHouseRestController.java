package com.sist.web.restcontroller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.*;
import com.sist.web.entity.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*") // ip http://localhost:3000
// port가 같은 경우에만 접근이 가능 
// => react는 3000, boot = 80, => 이런 접근 포트 차이 해제 하는 것 => CrossOrigin(origins = "*")
public class FoodHouseRestController {
	@Autowired
	private FoodHouseDAO fDao;
	@Autowired
	private RecipeDAO rDao;
	
	@Autowired
	private ChefDAO cDao;
	
	@GetMapping("food/main_react")
	public Map foodMainTopData() {
		Map map=new HashMap();
		List<FoodHouseVO> fList=fDao.foodHitTop9();
		List<RecipeEntity> rList=rDao.recipeMainData();
		ChefEntity vo=cDao.findByChef("핑콩이");
		map.put("fList", fList);
		map.put("rList", rList);
		map.put("cvo", vo);
		return map;
	}
	
}
