package com.sist.web.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.entity.*;
import com.sist.web.dao.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*")
public class FoodHouseRestController {
	@Autowired // spring에서 메모리 할당이 된 경우에만 사용이 가능 
	private FoodHouseDAO fDao;
	/*
	 *	클래스는 반드시 메모리 할당 후에 사용 => new (결합성이 강한 프로그램) => 영향
	 *	==========================
	 *  1. <bean> : 라이브러리 클래스를 메모리 할당하는 경우 => 공통으로 사용되는 경우 
	 *     @Bean
	 *  2. Annotation 이용
	 *     @Component : 일반 클래스 => ~Manager : OpenApi
	 *     @Repository : DAO 데이터베이스 연결 
	 *                   Oracle, MySql, ElasticSearch(MongoDB) => NoSQL
	 *                   => 한 개의 테이블 연동 
	 *                   => 라이브러리 : MyBatis / JPA 
	 *     @Service : BI (통합 => DAO가 여러 개일 때)
	 *     @Controller : 웹 파일 제어 => 최근에는 사라짐 => 최근은 Front / Back 나눠서 처리 
	 *                   Back => 데이터를 JSON 변경해서 전송 
	 *     @RestController : JSON으로 전송 => 다른 언어와 연동
	 *                       SpringBoot <===> Kotlin, Flutter(Dart)
	 *     @ControllerAdvice : 공통 예외 처리 할 때 사용 
	 *     @RestControllerAdvice : 공통 예외 처리 할 때 사용
	 *     
	 *     1. web.xml / server.xml : 경로 확인 <Context> => SpringFramework
	 *        | 어떤 프레임 워크 사용 중인지 확인 => Spring
	 *        | 연결 파일 => application_*.xml
	 *     =================================== SpringBoot : 임베디드 tomcat 자체 처리 
	 *     2. 동작 
	 *              요청
	 *              /
	 *        사용자 ======> DispatcherServlet ======> HandlerMapping
	 *                                                   |
	 *                                              @Controller / @RestController
	 *                                                   |
	 *                                               사용자가 요청한 URI를 찾는다
	 *                                                          ====
	 *                                                        @GetMapping
	 *                                                        @PostMapping
	 *                                                        @RequestMapping(spring 6.0에서는 제외)
	 *       RestApi
	 *       =======
	 *       1. Get ===> SELECT
	 *       2. Post ===> INSERT
	 *       3. Put ===> UPDATE
	 *       4. Delete ===> DELETE
	 *            |
	 *       return을 전송 => JSON
	 *       => 파일명 ===> Forward (데이터를 전송)
	 *       => redirect ==> Redirect (이전 화면으로 이동, 데이터 전송이 불가)
	 *	
	 *  => 최근에는 ?을 사용하지 않고 /10
	 *  => board/list/{page}
	 *  => pathVariable
	 *  => 에러와 동시에 데이터를 전송하는 방식 
	 *     => ResponseEntity => 실무 
	 *     => react-query
	 *        {isLoading, error, data, reflush:함수명}=useQuery()
	 */
	// 맛집 목록 
	@GetMapping("/food/list/{page}")
	public ResponseEntity<Map> food_list(@PathVariable("page") int page){
		Map map=new HashMap();
		try {
			int rowSize=12;
			int start=(rowSize*page)-rowSize;
			List<FoodHouseVO> fList=fDao.foodListData(start);
			int count=(int)fDao.count();
			int totalpage=(int)(Math.ceil(count/12.0));
			
			final int BLOCK=10;
			int startPage=((page-1)/BLOCK*BLOCK)+1;
			int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalpage)
				endPage=totalpage;
			
			map.put("fList", fList);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			
		}catch(Exception ex) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	// /food/find/${page}/${address}
	@GetMapping("/food/find/{page}/{address}")
	public ResponseEntity<Map> food_find(@PathVariable("page") int page, @PathVariable("address") String address) {
		Map map=new HashMap();
		try {
			int rowSize=12;
			int start=(page-1)*rowSize;
			List<FoodHouseVO> fList=fDao.foodFindData(start, address);
			int totalpage=fDao.foodFindTotalPage(address);
			final int BLOCK=10;
			int startPage=((page-1)/BLOCK*BLOCK)+1;
			int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalpage)
				endPage=totalpage;
			
			map.put("fList", fList);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			
		}catch(Exception ex) {
			// {isLoading, isError, error, data}
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			/*   500 : 소스 에러
			 *   404 : 파일이 없는 경우
			 *   400 : Bad Request => 데이터 전송이 틀렸을 경우
			 *   415 : 한글 변환 문제
			 *   403 : 접근 거부 
			 */
		}
		return new ResponseEntity<>(map,HttpStatus.OK); // 정상 수행 (200)
	}
	
	@GetMapping("food/detail/{fno}")
	public ResponseEntity<FoodHouseEntity> food_detail(@PathVariable("fno") int fno){
		FoodHouseEntity vo=new FoodHouseEntity();
		try {
			// 조회수 증가
			vo=fDao.findByFno(fno);
			vo.setHit(vo.getHit()+1);
			fDao.save(vo);
			
			vo=fDao.findByFno(fno);
		}catch(Exception ex) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(vo,HttpStatus.OK);
	}
	
	
}
