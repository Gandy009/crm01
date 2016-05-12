<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="atguigu" tagdir="/WEB-INF/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>联系人管理</title>
	<script type="text/javascript">
		$(function(){
			
			//删除的 JS 代码
			$(".delete").click(function(){
				var contactName = $(this).prev(":hidden").val();
				var flag = confirm("确定要删除 [" + contactName + "] 的信息吗?");
				var $form = $("#hiddenForm");
				var ids=$(this).next(":hidden").val();
				var id = ids.split("-")[0];
				var customerid=ids.split("-")[1];								
				if(!flag){
					return false;
				}				
				$("#_method").val("DELETE");
				$form.attr("action", "${ctp}/contact/delete/" + id+"/"+customerid).submit();				
				
			});
			//删除的 JS ajax请求
			$(".delete2").click(function(){
				var contactName = $(this).prev(":hidden").val();
				var flag = confirm("确定要删除 [" + contactName + "] 的信息吗?");
				var ids=$(this).next(":hidden").val();
				var id = ids.split("-")[0];
				var customerid=ids.split("-")[1];								
				
				var url="${ctp}/contact/deleteAjax/" + id+"/"+customerid;
				var args={"_method":"DELETE", "time":new Date()};
				if(flag){					
					$.post(url, args, function(data){
						if(data == "1"){
							alert("删除成功!");
							//把 a 节点所在的 td 所在的 tr 给移除
							//$tr.remove();
							window.location.href="${ctp}/contact/list?customerid="+customerid;
						}else{
							alert("删除失败,联系人个数不能小于1！");
						}
					});				
				}
			});
		})
	</script>
</head>
	<form action="" method="POST" id="hiddenForm">
		<input type="hidden" name="_method" value="" id="_method"/>
	</form>
<body>

	<div class="page_title">
		联系人管理
	</div>
	<div class="button_bar">

		<button class="common_button" onclick="window.location.href='${ctp}/contact/create?customerid=${customer.id }'">
			新建
		</button>
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
	</div>
	
	<form action="${ctp}/contact/list" method="post">
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
						姓名
					</th>
					<th>
						性别
					</th>
					<th>
						职位
					</th>
					<th>
						办公电话
					</th>
					<th>
						手机
					</th>
					<th>
						备注
					</th>
					<th>
						操作
					</th>
				</tr>
	
				<c:forEach var="contact" items="${page.content }">
					<tr>
						<td class="list_data_text">
							${contact.name}
						</td>
						<td class="list_data_text">
							${contact.sex}
						</td>
						<td class="list_data_text">
							${contact.position}
						</td>
						<td class="list_data_text">
							${contact.tel}
						</td>
						<td class="list_data_text">
							${contact.mobile}
						</td>

						<td class="list_data_ltext">
							${contact.memo}
						</td>
						<td class="list_data_op">
							<img onclick="window.location.href='${ctp}/contact/${contact.id }/${customer.id }'" 
								title="编辑" src="${ctp }/static/images/bt_edit.gif" class="op_button" />
							<input type="hidden" value="${contact.name}"/>
							<%-- <img title="删除" src="${ctp}/static/images/bt_del.gif" class="delete" /> --%>
							<img title="删除" src="${ctp}/static/images/bt_del.gif" class="delete2" />
							<input type="hidden" value="${contact.id}-${customer.id}"/>							
						</td>
					</tr>
				</c:forEach>
			</table>
			<atguigu:page page="${requestScope.page }" queryString="${requestScope.queryString }"></atguigu:page>
		</c:if>
		<c:if test="${page == null || page.totalElements == 0 }">
			没有任何数据
		</c:if>
	</form>
</body>
</html>