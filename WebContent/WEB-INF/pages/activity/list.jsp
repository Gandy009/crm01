<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>交往记录管理</title>
	<script type="text/javascript">
		$(function(){
			$(".delButton").click(function(){
				var custName = $(this).next(":hidden").val();
				var flag = confirm("确定要删除" + custName + "的信息吗?");
				if(flag) {
					var action = $(this).attr("href");
					$("#hiddenForm").attr("action",action).submit();
				}
			});
		})
	</script>
</head>
<body>
	
	<div class="page_title">
		交往记录管理
	</div>
	<div class="button_bar">
		<button class="common_button"
			onclick="window.location.href='${ctp}/activity/create/${customer.id }'">
			新建
		</button>
		<button class="common_button" onclick="javascript:history.go(-1);"> 
			返回
		</button>
	</div>
	
	<form id="hiddenForm" action="" method="post">
		<input type="hidden" name="_method" value="DELETE"/>
	</form>
	
	<form action="${ctp}/activity/list" method="POST">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					客户编号
				</th>
				<td>${customer.no}</td>
				<th>
					客户名称
				</th>
				<td>${customer.name}</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
		
		<c:if test="${page != null && page.totalElements > 0 }">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>
						时间
					</th>
					<th>
						地点
					</th>
					<th>
						概要
					</th>
					<th>
						详细信息
					</th>
					<th>
						操作
					</th>
				</tr>
				<c:forEach var="activity" items="${page.content }">
					<tr>
						<td class="list_data_text">
							<fmt:formatDate value="${activity.date }" pattern="yyyy-MM-dd"/>
						</td>
						<td class="list_data_ltext">
							${activity.place}
						</td>
						<td class="list_data_ltext">
							${activity.title}
						</td>
						<td class="list_data_ltext">
							${activity.description}
						</td>
						<td class="list_data_op">
							<img onclick="window.location.href='${ctp}/activity/create?id=${activity.id }'" 
								title="编辑" src="${ctp}/static/images/bt_edit.gif" class="op_button" />
							<img href="${ctp}/activity/delete/${activity.id }?customerid=${activity.customer.id}"
								class="delButton" title="删除" src="${ctp}/static/images/bt_del.gif" class="op_button" />
							<input type="hidden" value="${activity.title}"/>
						</td>
					</tr>
				</c:forEach>
			</table>	
		<tags:page page="${page }" queryString="${queryString }"></tags:page>
		</c:if>
		<c:if test="${page == null || page.totalElements == 0 }">
			没有任何数据
		</c:if>
	</form>
</body>
</html>