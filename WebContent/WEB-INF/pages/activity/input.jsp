<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Date"%>

<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>编辑/新建交往记录</title>
    <script type="text/javascript">
		$(function(){
			$("#edit_button").click(function(){
				$("#form").submit();
			});
		})
	</script>
  </head>

  <body class="main">
  
 	<span class="page_title">编辑/新建交往记录</span>
	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.go(-1);">返回</button>
		<c:if test="${requestScope.activity.id == null }">
  			<button class="common_button" onclick="document.forms[0].submit();">保存</button>
  		</c:if>
  		<c:if test="${requestScope.activity.id != null }">
			<button id="edit_button">修改</button>
		</c:if>
	</div>
  
 	<form:form id="form" action="${ctp}/activity/create" method="POST" modelAttribute="activity">
  		<c:if test="${requestScope.activity.id != null }">
			<input type="hidden" name="_method" value="PUT"/>
		</c:if>
  		<form:hidden path="id"/>
		  		
		<form:hidden path="customer.id"/>
  		
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>时间&nbsp;(格式: yyyy-mm-dd)</th>
				<td>
					<input type="text" name="date" id="date" value="<fmt:formatDate value="${activity.date }" pattern="yyyy-MM-dd"/>" />&nbsp;
					<span class="red_star">*</span>
				</td>
				<th>地点</th>
				<td><form:input path="place"/><span class="red_star">*</span></td>
			</tr>
			<tr>
				<th>概要</th>
				<td colspan="3"><form:input path="title"/><span class="red_star">*</span></td>
			</tr>
			<tr>		
				<th>详细信息</th>
				<td colspan="3"><form:textarea path="description"/></td>
			</tr>
		</table>
  	</form:form>
  </body>
</html>
