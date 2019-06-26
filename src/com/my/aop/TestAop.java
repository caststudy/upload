package com.my.aop;

import org.aspectj.lang.JoinPoint;

public class TestAop {

	
	
	public void dx(JoinPoint jp) {

		String classAndMethod = jp.getTarget().getClass().getName()
		+ "类的" + jp.getSignature().getName();
		System.out.println("前置通知:" + classAndMethod + "方法开始执行！");
	}

	
	public void AfterThrowingAdvice(JoinPoint joinpoint, Exception e) {
		String classAndMethod = joinpoint.getTarget().getClass().getName()
		+ "类的" + joinpoint.getSignature().getName();
		System.out.println("异常通知:" + classAndMethod + "方法抛出异常："
		+ e.getMessage());
		}
	
	public void abc(JoinPoint jp) {

		String classAndMethod = jp.getTarget().getClass().getName()
		+ "类的" + jp.getSignature().getName();
		System.out.println(classAndMethod + "方法执行正常结束!");
	}
	
	
}