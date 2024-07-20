<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1> Hello ${message}</h1>
 Your uplaoded image is  :
 <br>
 <input type="hidden" name="rollno" value="${rollno}" />
<img src="${pageContext.request.contextPath}/getPhoto?rollno=${rollno}"  alt=" User Image"/>
</body>
</html>