<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${postsList}" var="posts">
	<tr class="postsClick">
		<td>${posts.posts_no}</td>
		<td>${posts.posts_title}</td>
		<td>${posts.userid}</td>
		<td>${posts.posts_date}</td>
		<td style="display:none;">${posts.posts_use}</td>
	</tr>
</c:forEach>