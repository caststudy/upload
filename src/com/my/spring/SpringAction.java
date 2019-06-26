package com.my.spring;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.FileUploadInterceptor;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.my.aop.TestAop;
import com.my.entity.MyEntity;
import com.my.service.MyService;


@Controller
@RequestMapping("/sp/")
public class SpringAction {
	@Autowired
	MyService myService;

	@RequestMapping(value = "spupload1", method = RequestMethod.POST)
	public String upload1(HttpServletRequest req, MyEntity enty) {

		DiskFileItemFactory dfif = new DiskFileItemFactory();
		dfif.setSizeThreshold(5 * 1024 * 1024);

		dfif.setRepository(new File("d:/tmp/"));
		// �����ļ��ϴ�������
		ServletFileUpload upload = new ServletFileUpload(dfif);

		Map<String, MultipartFile> fileItem = ((DefaultMultipartHttpServletRequest) req)
				.getFileMap();

		CommonsMultipartFile mf = (CommonsMultipartFile) fileItem.get("myFile");
		FileItem item = mf.getFileItem();
		try {
			if (!item.isFormField()) {
				System.out.println(item.getFieldName());
				System.out.println(item.getContentType());
				System.out.println(item.getName());
				// ���ļ��ϴ����󣬻�ȡ�ϴ��ļ���������
				InputStream in = item.getInputStream();
				/* ���ϴ��ļ������������д��������ص��ļ�������ʽ��ͬ */
				// �ļ�������ʾ
				String sPath = req.getRealPath("/") + "/upload/";
				mkdir(sPath);
				String sName = item.getName();
				sName = sName.substring(sName.lastIndexOf("/") + 1);
				sName = sName.substring(sName.lastIndexOf("\\") + 1);
				FileOutputStream destFile = new FileOutputStream(sPath + "sp1_"
						+ sName);
				int i = 0;
				while ((i = in.read()) != -1) {
					destFile.write(i);
				}
				in.close();
				destFile.close();

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			return "login.jsp";
		}

		myService.insert(enty);
		return "../index.jsp";

	}

	public void mkdir(String sFile) {
		File f = new File(sFile);
		if (!f.exists())
			f.mkdir();
	}

	@RequestMapping(value = "spupload2", method = RequestMethod.POST)
	public String upload2(HttpServletRequest req, MyEntity enty,
			BindingResult errors) {

		System.out.println(errors.hasErrors());
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
		Iterator<String> fileNameIte = multipartRequest.getFileNames();
		String realFileName = null; // ��ʵ�ϴ��ļ���
		String fileType = null; // �ļ�����
		try {

			while (fileNameIte.hasNext()) {
				String fileName = fileNameIte.next();
				MultipartFile mr = multipartRequest.getFile(fileName);

				realFileName = mr.getOriginalFilename();

				System.out.println("�ϴ����ļ���Ϊ-----:" + realFileName);
				fileType = realFileName
						.substring(realFileName.lastIndexOf(".") + 1);
				realFileName = realFileName.substring(0, realFileName
						.lastIndexOf("."))
						+ "-" + System.nanoTime() + "." + fileType; // ���ϴ��ļ�����������
				String sPath = req.getRealPath("/") + "/upload/";
				mkdir(sPath);

				File localFile = new File(sPath + "sp2_" + realFileName);
				mr.transferTo(localFile);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "login.jsp";
		}
		myService.insert(enty);
		return "../index.jsp";

	}

	@RequestMapping(value = "spupload3", method = RequestMethod.POST)
	public String upload3(HttpServletRequest req,HttpServletResponse resp,
			@RequestParam("myFile") MultipartFile file, MyEntity enty) throws IOException {
		// �ж��ļ��Ƿ�Ϊ��
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		out.println(enty.getDx());
		if (!file.isEmpty()) {
			try {
				// �ļ�����·��
				String filePath = req.getServletContext().getRealPath("/") + "/upload/";
				mkdir(filePath);
				filePath = filePath + "sp3_" + file.getOriginalFilename();
				// ת���ļ�
				file.transferTo(new File(filePath));
				out.print("<br><img src='"+req.getContextPath()+"/upload/sp3_"+file.getOriginalFilename()+"'>");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		out.println(myService);
		out.print(this);
		myService.insert(enty);
		return null;

	}

}