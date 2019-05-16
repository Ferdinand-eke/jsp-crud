<%-- 
    Document   : editProductView
    Created on : Apr 26, 2019, 12:26:10 PM
    Author     : Zinachi Computer
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Edit Product</title>
   </head>
   <body>
 
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
 
      <h3>Add Product Image Result</h3>
 
      <div id="result">
		<h3>${requestScope["message"]}</h3>
		<br>
	</div>
	File name : ${requestScope["name"]}
	<br> File size : ${requestScope["size"]}
	<br> File type : ${requestScope["type"]}
 
      <jsp:include page="_footer.jsp"></jsp:include>
 
   </body>
</html>
