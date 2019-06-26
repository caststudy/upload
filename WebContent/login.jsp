<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<STYLE type="text/css">
	.errorMessage{
	list-style-type: square;
	
	}
	
	</STYLE>
	
  </head>

  <body>
  		<s:debug></s:debug> 
  
 
  <font color="red">
   
             <s:if test="hasActionErrors()">
                        <s:actionerror/>
             </s:if> 
                
              <s:if test="hasFieldErrors()">
                         fielderror:  <s:fielderror></s:fielderror>
             </s:if>  
              
  
  </font>  

 
  
  </body>
</html>
