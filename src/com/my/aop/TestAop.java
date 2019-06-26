package com.my.aop;

import org.aspectj.lang.JoinPoint;

public class TestAop {

	
	
	public void dx(JoinPoint jp) {

		String classAndMethod = jp.getTarget().getClass().getName()
		+ "���" + jp.getSignature().getName();
		System.out.println("ǰ��֪ͨ:" + classAndMethod + "������ʼִ�У�");
	}

	
	public void AfterThrowingAdvice(JoinPoint joinpoint, Exception e) {
		String classAndMethod = joinpoint.getTarget().getClass().getName()
		+ "���" + joinpoint.getSignature().getName();
		System.out.println("�쳣֪ͨ:" + classAndMethod + "�����׳��쳣��"
		+ e.getMessage());
		}
	
	public void abc(JoinPoint jp) {

		String classAndMethod = jp.getTarget().getClass().getName()
		+ "���" + jp.getSignature().getName();
		System.out.println(classAndMethod + "����ִ����������!");
	}
	
	
}