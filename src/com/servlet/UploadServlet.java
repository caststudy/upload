package com.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet(urlPatterns = { "/serv/upload.servlet" }, initParams = { @WebInitParam(name = "test", value = "testvalue") })
public class UploadServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		out.print(req.getParameter("dx"));
		
		try {
			// ����һ������������
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// �õ�������
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("utf-8");
			// if(upload.isMultipartContent(request))
			// ����Ӧ��������н������м�����������м���FileItem����

			List<FileItem> list = upload.parseRequest(req);
			// Ҫ����list���ϣ���ȡlist������ÿһ��������
			for (FileItem item : list) {
				// �ж����������
				if (item.isFormField()) {
					// ��ͨ������
					String inputName = item.getFieldName();
					String inputValue = item.getString();
					out.print(inputName + "::" + inputValue);
				} else {
					// �ϴ��ļ�������
					String filename = item.getName();// �ϴ��ļ����ļ���1.txt
					filename = filename.substring(filename.lastIndexOf("\\") + 1);
					InputStream is = item.getInputStream();

					String sPath = req.getServletContext().getRealPath("/");
					mkdir(sPath);

					FileOutputStream fos = new FileOutputStream(sPath + "servlet_" + filename);
					out.print("<img src='"+req.getContextPath()+"/upload/servlet_"+filename+"'>");
					
					byte[] buff = new byte[1024];
					int len = 0;
					while ((len = is.read(buff)) > 0) {
						fos.write(buff);
					}
					is.close();
					fos.close();
				}

			}
			out.println(this);
			out.print("<font color=red>success</font>");
		} catch (FileUploadException e) {
			out.print("<font color=red>failure</font>");
		}
	}

	public void mkdir(String sFile) {
		File f = new File(sFile);
		if (!f.exists())
			f.mkdir();
	}
}
