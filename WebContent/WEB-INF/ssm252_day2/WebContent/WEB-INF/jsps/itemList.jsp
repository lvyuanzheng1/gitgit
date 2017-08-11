<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//http://localhost:8080/ssm252_day2/
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<base href="<%=basePath%>">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>查询商品列表</title>
		<script type="text/javascript" src="resources/js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript">
			function voidTest(){
				$.post("item/voidTest.action",{name:"abc"},function(data){
					alert(data["name"]);
				},"json")
			}
			
			function jsonTest(){
				$.ajax({
					type:"post",
					url:"item/editItemSubmit_RequestJson.action",
					contentType:"application/json;charset=utf-8",
					data:'{"name":"测试商品","price":99.9}',
					success:function(data){
						var jsonstr=JSON.stringify(data);
						var jsonObj=JSON.parse(jsonstr);
						alert(jsonObj["name"]);
					}
				});
			}
			
		</script>
		</head>
	<body> 
		<form action="item/updateitems.action" method="post">
			查询条件：
			<table width="100%" border=1>
				<tr>
				<td>
				商品名称:<input type="text" name="items.name">
				商品价格:<input type="text" name="items.price">
				<input type="submit" value="批量修改"/>
				<input type="button" value="void测试" onclick="voidTest()">
				<input type="button" value="jsonTest" onclick="jsonTest()">
				</td>
				</tr>
			</table>
			商品列表：
			<table width="100%" border=1>
				<tr>
					<td>删除</td>
					<td>商品名称</td>
					<td>商品价格</td>
					<td>生产日期</td>
					<td>商品描述</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${itemList }" var="item" varStatus="status">
					<tr>
						<td><input type="checkbox" name="ids" value="${item.id}">
						<input type="hidden" name="itemList[${status.index }].id" value="${item.id }">
						</td>
						<td><input type="text" name="itemList[${status.index }].name" value="${item.name }"></td>
						<td><input type="text" name="itemList[${status.index }].price" value="${item.price }"> </td>
						<td><input type="text" name="itemList[${status.index }].createtime" value="<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"></td>
						<td><input type="text" name="itemList[${status.index }].detail" value="${item.detail }"></td>
						<td><a href="item/itemEdit/${item.id}">修改</a></td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</body>
</html>