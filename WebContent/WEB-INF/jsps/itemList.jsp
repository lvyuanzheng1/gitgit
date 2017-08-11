<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<base href="<%=basePath%>">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>查询商品列表</title>
		</head>
	<body> 
		<form action="item/queryitem.action" method="post">
			查询条件：
			<table width="100%" border=1>
				<tr>
				<td><input type="submit" value="查询"/></td>
				</tr>
			</table>
			商品列表：
			<table width="100%" border=1>
				<tr>
					<td>商品名称</td>
					<td>商品价格</td>
					<td>生产日期</td>
					<td>商品描述</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${itemList }" var="items">
<tr>
	<td><input name="ids" value="${items.id}" type="checkbox"></td>
	<td>${items.name }</td>
	<td>${items.price }</td>
	<td><fmt:formatDate value="${items.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	<td>${items.detail }</td>
	<td><a href="item/itemEdit.action?items=${items}">修改</a></td>
</tr>
</c:forEach>
			</table>
		</form>
	</body>
</html>