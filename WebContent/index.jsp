<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	</head>

	<body>
	<form action="serv/upload.servlet" method="post"
			enctype="multipart/form-data">
			<input name="dx" value="100">			
			<input type="file" name="myFile" />
			<input type="submit" value="servletfileupload" />
		</form><hr/>
		
	     <form action="serv/upload.servlet3" method="post"
			enctype="multipart/form-data">
		 	<input name="dx" value="100">		
			<input type="file" name="myFile" />
			<input type="submit" value="servlet30upload" style="color: red"/>
		</form>
		
		<hr/>
		<br />
		<form action="action3/s2upload.action" method="post"
			enctype="multipart/form-data">
			<input name="dx" value="100">
			
			<input type="file" name="myFile" />
			<input type="submit" value="s2upload" style="color: red"/>
		</form>
		<hr/>
			<form action="sp/spupload1.php" method="post"
			enctype="multipart/form-data">
			<input name="dx" value="100">
			
			<input type="file" name="myFile" />
			<input type="submit" value="spupload1" />
		</form>
			<form action="sp/spupload2.php" method="post"
			enctype="multipart/form-data">
			<input name="dx" value="100">
			
			<input type="file" name="myFile" />
			<input type="submit" value="spupload2" />
		</form>
			<form action="sp/spupload3.php" method="post"
			enctype="multipart/form-data">
			<input name="dx" value="100">
			
			<input type="file" name="myFile" />
			<input type="submit" value="spupload3"  style="color: red"/>
		</form>
	</body>
</html>
