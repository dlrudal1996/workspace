<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 게시판</title>
</head>
<body>
<!-- 데이터가 있는경우 -->
<c:if test="${!empty lists }">
<table width=50% border="0" cellpadding="0" cellspacing="0" align="center">
	<tr align="center" valign="middle">
		<td colspan="4">댓글 게시판</td>
		<td align=right>
			<font size=2>글 개수 :${count }</font>
		</td>
	</tr>
	
	<tr align="center" valign="middle" bordercolor="#333333">
		<td width="8%" height="26">
			<div align="center">번호</div>
		</td>
		<td width="50%">
			<div align="center">제목</div>
		</td>
		<td width="14%">
			<div align="center">작성자</div>
		</td>
		<td width="20%">
			<div align="center">날짜</div>
		</td>

	</tr>
<c:forEach items="${lists }" var="dto" varStatus="cnt">				
	<tr align="center" valign="middle">
		<td height="23">${cnt.count }</td>	<!-- 1,2,3,.... -->
											<!-- index : 0,1,2,,, -->
		<td><a href="commentDetail?commentNo=${dto.commentNo }">${dto.commentSubject }</a></td>
		<td>${dto.cuserId }</td>
		<td><fmt:formatDate value="${dto.regDate }" type="date" pattern="yyyy-MM-dd"/> </td>	
	</tr>
</c:forEach>

	<tr align=center height=20>
		<td colspan=7 style=font-family:Tahoma;font-size:10pt;>
			<%@ include file = "../include/includePage.jsp" %>
		</td>
	</tr>
</c:if>
<!-- 데이터가 있는경우 -->
<!-- 데이터가 없는경우 -->
<c:if test="${empty lists }">
	<tr align="center" valign="middle">
		<td colspan="4">댓글 게시판</td></tr>
	<tr>	
		<td align=right>
			<font size=2>등록된 글이 없습니다.</font>
		</td>
	</tr>
</c:if>
<!-- 데이터가 없는 경우 -->

	<tr align="right">
		<td colspan="5">
	   		<a href="/comment/commentForm">[글쓰기]</a>
		</td>
	</tr>
</table>
</body>
</html>