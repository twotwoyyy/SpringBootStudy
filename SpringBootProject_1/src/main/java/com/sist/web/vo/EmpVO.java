package com.sist.web.vo;
import java.util.*;

import lombok.Data;
// Spring => Spring-Boot
// 최대한 XML 파일이 없게 Annotation 이용
// Framework => Spring-Boot에 포함 
// => 소스를 최소화 : 서버 역할만 수행 ===> Front-End 별도로 작성
@Data
public class EmpVO {
	private int empno,sal;
	private String ename,job,dbday;
	private Date hiredate;
}
