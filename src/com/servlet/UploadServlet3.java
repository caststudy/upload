package com.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.my.s2action.MyAction;
import com.my.service.MyService;


/**
 * Servlet implementation class UploadServlet
 */

@WebServlet(urlPatterns = { "/serv/upload.servlet3" }, initParams = {
		@WebInitParam(name = "test", value = "testvalue") })
@MultipartConfig(location="", fileSizeThreshold=1024,maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class UploadServlet3 extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
	  MyService myService=   	(MyService) WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getBean("myService"); 
//	  MyAction myAction=   	(MyAction) WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getBean("myAction"); 
		
	  String sPath = request.getServletContext().getRealPath("/");
		System.out.println(sPath);
		sPath=sPath+ "/upload/";
		
		mkdir(sPath);
		PrintWriter out = resp.getWriter();
	    resp.setContentType("text/html");
//	    out.print("myAction:"+myAction);
	    out.print(myService);
	    out.print("<h1>"+request.getParameter("dx")+"</h1>");
		for (Part p : request.getParts()) {	
			if(p.getContentType()==null ) continue;
			out.write("Part name: " + p.getName() + "<br/>\n");
			out.write("Size: " + p.getSize() + "<br/>\n");
			out.write("Content Type: " + p.getContentType() + "<br/>\n");
			out.write("Header Names:"+p.getHeaderNames());
			String fileName = p.getHeader("content-disposition");
			String finfo[]=fileName.split(";");			
			fileName=finfo[2].split("=")[1];
			fileName=FilenameUtils.getExtension(fileName);	 
			fileName="servlet3_."+  fileName.substring(0,fileName.length()-1);
			out.print("<img src='"+request.getContextPath()+"/upload/"+fileName+"'>");
			p.write(sPath+fileName);
//			for (String name : p.getHeaderNames()) {
//				out.write(name + ": " + p.getHeader(name) + "<br>");
//
//			}
			out.print(myService);
			out.print(this);
			out.write("<br/><br/>\n");
		}

	}

	public void mkdir(String sFile) {
		File f = new File(sFile);
		if (!f.exists())
			f.mkdir();
	}
}
