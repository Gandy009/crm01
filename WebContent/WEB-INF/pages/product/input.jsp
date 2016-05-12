<%@ page language="java" pageEncoding="UTF-8"%>

<%@ include file="/commons/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>编辑产品信息</title>
	<script type="text/javascript">
		$(function(){
			$("#save_button").click(function(){
				var flag = $("input:hidden").val();
				if(flag > 0) {
					var action = $("#formId").attr("action") + "?_method=PUT";
					$("#formId").attr("action",action).submit();
				} else {
					$("#formId").submit();
				}
				 
			});
		});
	</script>
</head>

<body class="main">
	
	<span class="page_title">编辑产品信息</span>
	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
		<button id="save_button">
			保存
		</button>
	</div>
	
	<form:form action="${ctp }/product/create" method="POST" modelAttribute="product" id="formId">
		<form:hidden path="id"/>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					名称
				</th>
				<td>
					<form:input path="name"/>
				</td>
				<th>
					型号
				</th>
				<td>
					<form:input path="type"/>
				</td>
			</tr>
			<tr>
				<th>
					等级/批次
				</th>
				<td>
					<form:input path="batch"/>
				</td>
				<th>
					单位
				</th>
				<td>
					<form:input path="unit"/>
				</td>
			</tr>
			<tr>
				<th>
					单价
				</th>
				<td>
					<form:input path="price" />
				</td>
				<th>
					备注
				</th>
				<td>
					<form:input path="memo"/>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>
