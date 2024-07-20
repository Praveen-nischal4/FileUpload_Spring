<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="saveDetails" method="post" enctype="multipart/form-data">

 <label> Name :</label>
 <input type="text" name="name" placeholder="enter name"/> <br/>
 
  <label> Age :</label>
 <input type="text" name="age" placeholder="enter age"/> </br>
 

 
 Photo: <input type="file" name="photo"/> <br>
  <input type="submit" value="Save"/>
 </form>
</body>
</html>