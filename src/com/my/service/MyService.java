package com.my.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.my.entity.MyEntity;
@Service("myService")
@Scope(value="singleton")
public class MyService {
      public boolean insert(MyEntity enty) {
    	 System.out.println(enty.getDx());
		return true;
	}
}