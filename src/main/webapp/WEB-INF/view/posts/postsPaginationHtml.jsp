<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<li>
	<c:choose>
		<c:when test="${page eq 1}">
			<a href="javascript:getPostsListHtml(1);"
				aria-label="Previous" id="disabled"><span aria-hidden="true">&laquo;</span></a>
		</c:when>
		<c:otherwise>
			<a href="javascript:getPostsListHtml(1);"
				aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
		</c:otherwise>
	</c:choose>
	
</li>

<li>
	<c:choose>
		<c:when test="${page > 1}">
			<a href="javascript:getPostsListHtml(${page-1});"
				aria-label="Previous"><span aria-hidden="true">&lt;</span></a>
		</c:when>
		<c:otherwise>
			<a href="javascript:getPostsListHtml(1);"
				aria-label="Previous" id="disabled"><span aria-hidden="true">&lt;</span></a>
		</c:otherwise>
	</c:choose>
</li>

<c:forEach begin="1" end="${postsCnt}" var="i">
	<li><a href="javascript:getPostsListHtml(${i});">${i}</a></li>
</c:forEach>

<li>
	<c:choose>
		<c:when test="${page eq postsCnt}">
			<a href="javascript:getPostsListHtml(${postsCnt});"
				aria-label="Next" id="disabled"><span aria-hidden="true">&gt;</span></a>
		</c:when>
		<c:otherwise>
			<a href="javascript:getPostsListHtml(${page+1});"
				aria-label="Next"><span aria-hidden="true">&gt;</span></a>
		</c:otherwise>
	</c:choose>
</li>

<li>
	<c:choose>
		<c:when test="${page eq postsCnt}">
			<a href="javascript:getPostsListHtml(${postsCnt});"
				aria-label="Next" id="disabled" ><span aria-hidden="true">&raquo;</span></a>
		</c:when>
		<c:otherwise>
			<a href="javascript:getPostsListHtml(${postsCnt});"
				aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
		</c:otherwise>
	</c:choose>
</li>