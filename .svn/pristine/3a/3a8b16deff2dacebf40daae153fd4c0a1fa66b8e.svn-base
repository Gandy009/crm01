<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="atguigu" tagdir="/WEB-INF/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript">
	$(function(){
		
		//删除的 JS ajax请求
		$(".delete").click(function(){
			var item = $(this).prev(":hidden").val();
			var flag = confirm("确定要删除 【" + item + "】的信息吗?");
			var id=$(this).next(":hidden").val();
			
			var url="${ctp}/dict/delete?id=" + id;
			var args={"_method":"DELETE"};
			if(flag){
				$.post(url, args, function(data){
					if(data == "1" ){
						alert("删除成功!");
						
						window.location.href="${ctp}/dict/list";
					}else{
						alert("删除失败!");
					}
				});				
			}
		});
		
	})
</script>

<title>管理</title>	
</head>
<body>
	<div class="page_title">
		基础数据管理
	</div>
	<div class="button_bar">
		<button class="common_button" onclick="window.location.href='${ctp}/dict/create'">
			新建
		</button>
		<button class="common_button" onclick="document.forms[0].submit();">
			查询
		</button>
	</div>
	
	<form action="${ctp }/dict/list" >
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					类别
				</th>
				<td>
					<input type="text" name="search_LIKES_type" />
				</td>
				<th>
					条目
				</th>
				<td>
					<input type="text" name="search_LIKES_item" />
				</td>
				<th>
					值
				</th>
				<td>
					<input type="text" name="search_LIKES_value" />
				</td>
			</tr>
		</table>
	</form>
	<!-- 列表数据 -->
	<br />
	
	<c:if test="${page != null && page.totalElements > 0 }">
		<table class="data_list_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					编号
				</th>
				<th>
					类别
				</th>
				<th>
					条目
				</th>
				<th>
					值
				</th>
				<th>
					操作
				</th>
			</tr>
			<c:forEach var="dict" items="${page.content }">
				<tr>
					<td class="list_data_number">
						${dict.id}
					</td>
					<td class="list_data_text">
						${dict.type}
					</td>
					<td class="list_data_text">
						${dict.item}
					</td>
					<td class="list_data_text">
						${dict.value}
					</td>

					<td class="list_data_op">
						<c:if test="${dict.editable}">
							<img onclick="window.location.href='${ctp}/dict/create?id=${dict.id }'" 
								title="编辑" src="${ctp }/static/images/bt_edit.gif" class="op_button" />
							<input type="hidden" value="${dict.item}"/>
							<img title="删除" src="${ctp}/static/images/bt_del.gif" class="delete" />
							<input type="hidden" value="${dict.id}"/>	
						</c:if>
					</td>
				</tr>
			</c:forEach>			
		</table>
		<atguigu:page page="${requestScope.page }" queryString="${requestScope.queryString }"></atguigu:page>
	</c:if>
	<c:if test="${page == null || page.totalElements == 0 }">
		没有任何数据
	</c:if>
	
</body>
</html>