<%-- 
    Document   : productListView
    Created on : Apr 26, 2019, 10:10:02 AM
    Author     : Zinachi Computer
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Product List</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Product List</h3>
 
    <p style="color: red;">${errorString}</p>
    <div id="result">
		<h3>${requestScope["message"]}</h3>
		<br>
    </div>
 
    <table border="1" cellpadding="5" cellspacing="1" width="100%">
       <tr>
          <th>Code</th>
          <th>Name</th>
          <th>Price</th>
          <th>Image</th>
          <th colspan="3">Action</th>
       </tr>
       
       <c:forEach items="${productList}" var="product" >
          <tr>
             <td>${product.code}</td>
             <td>${product.name}</td>
             <td>${product.price}</td>
             <td style="text-align: center">
                 <c:if test="${not empty product.filename}">
                     <img src="${pageContext.request.contextPath}/resources/upload/${product.filename} " height="100" width="100"/>
                 </c:if>
             </td>
             <td>
                <a href="addProductImage?code=${product.code}">Add Image</a>
             </td>
             <td>
                <a href="editProduct?code=${product.code}">Edit</a>
             </td>
             <td>
                <a href="deleteProduct?code=${product.code}">Delete</a>
             </td>
          </tr>
     
          
       </c:forEach>
    </table>
    <br/>
    <a href="createProduct" >Create Product</a>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>