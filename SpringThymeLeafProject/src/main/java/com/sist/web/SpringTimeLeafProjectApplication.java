package com.sist.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.sist.web.entity.Emp;
@SpringBootApplication
public class SpringTimeLeafProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTimeLeafProjectApplication.class, args);
	}

}
