<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value='/UploadServlet'/>" method="post" enctype="multipart/form-data">
	<tr>
		<td>上传者</td>
		<td><input type="text" name="name"/></td>
	</tr>
		<tr>
		<td>上传文件</td>
		<td><input type="file" name="file"/></td>
	</tr>
		<tr>
		<td></td>
		<td><input type="submit" value="上传"/></td>
	</tr>
		
	</form>
</body>
</html>