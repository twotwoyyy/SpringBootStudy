package com.sist.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
/*
 *   EMPNO int 
ENAME text 
JOB text 
MGR text 
HIREDATE text 
SAL int 
COMM text 
DEPTNO int
 */
@Entity
@Data
public class Emp {
  @Id
  private int empno;
  private String ename,job,hiredate,comm,mgr;
  private int sal;
  @Column(insertable = false,updatable = false)
  private int deptno;
  // csv => 날짜 , 값중에 null 값이 있는 경우 => 문자열 (text)
  @ManyToOne(fetch = FetchType.EAGER)
  // LAZE(지연) , EAGER(즉시 로딩)
  @JoinColumn(name="deptno",referencedColumnName = "deptno")
  private Dept dept;
}