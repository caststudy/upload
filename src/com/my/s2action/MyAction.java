package com.my.s2action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.my.entity.MyEntity;
import com.my.service.MyService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import com.opensymphony.xwork2.util.ValueStack;


@Namespace("/action3")
@ParentPackage("abc")
@ExceptionMappings({ @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") })
public class MyAction extends ActionSupport implements ModelDriven<MyEntity>, ServletRequestAware ,ServletResponseAware{
	@Autowired
	MyService myService;

	HttpServletRequest req;
	HttpServletResponse resp;
	private File myFile;
	private String myFileContentType;
	private String myFileFileName;

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public MyService getMyService() {
		return myService;
	}

	public void setMyService(MyService myService) {
		this.myService = myService;
	}

	public void validateUpload() throws IOException {

		if (myFile == null)
			addActionError(getText("struts.messages.error.uploading"));

	}

	@Action(value = "s2upload",
			results={ 
					@Result(name = "success", location = "/index.jsp"),
					@Result(name = "error", location = "/login.jsp"),
		            @Result(name = "input", location = "/login.jsp") })
	public String upload() throws IOException {
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		out.println(enty.getDx());
		// 值栈演示
		ValueStack valueStck = ActionContext.getContext().getValueStack();
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("key1", new String("This is key1"));
		map.put("key2", new String("This is key2"));
		valueStck.push(map);
		// 文件传输演示
		String sPath = req.getServletContext().getRealPath("/") + "/upload/";
		mkdir(sPath);
		try {
			out.println("Src File name: " + myFile);
			out.println("<br>Dst File name: " + myFileFileName);

			File destFile = new File(sPath + "struts2_" + myFileFileName);

			FileUtils.copyFile(myFile, destFile);
			out.print("<img src='"+req.getContextPath()+"/upload/struts2_"+myFileFileName+"'><br>");

		} catch (Exception e) {
			return ERROR;
		}
		out.println(myService);
		out.print(this);
		myService.insert(enty);
		return null;

	}

	MyEntity enty = new MyEntity();

	public MyEntity getModel() {

		// TODO Auto-generated method stub
		return enty;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		req = request;
	}

	// @Override
	// public void addActionError(String anErrorMessage) {
	//
	// super.addActionError(anErrorMessage);
	// if (anErrorMessage
	// .startsWith("the request was rejected because its size")) {
	//
	// super
	// .addActionError(getText("struts.messages.error.file.too.large"));
	//
	// } else {
	// super.addActionError(anErrorMessage);
	// }
	// }

	public void mkdir(String sFile) {
		File f = new File(sFile);
		if (!f.exists())
			f.mkdir();
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		resp=arg0;
	}
}