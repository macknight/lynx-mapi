<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>sum</title>
</head>
<body>
	sum
	<h1>
		<s:property value="sum" />
	</h1>
	<s:form action="demo/sum">
		<select name="sel_rollback_reason" size="1"
			onchange="onRollbackReason(this.options[this.options.selectedIndex].value)">
			<option value="" selected style="display: none;">请选择原因</option>
			<option value="电话打不通">电话打不通</option>
			<option value="找不到对应人员">找不到对应人员</option>
			<option value="商户拒绝合作">商户拒绝合作</option>
			<option value="商户不清楚合作">商户不清楚合作</option>
			<option value="其他">其他</option>
		</select>
		<button type="button" class="btn-txt-rollback_dirtyshop btn-txt"
			data-ajax-url="<s:url action='rollback_dirtyshop' namespace='/demo/ajax'/>"
			shop-id="<s:property value="shopID"/>" onclick="onRollback()">打回</button>
		<br />
		<input id="tf_rollback_reason" type="text"
			style="position: absolute; display: none; color: gray" value="请输入原因"
			onfocus="style.color='black';if(value=='请输入原因'){value =''}"
			onblur="pre(this)" />

	</s:form>

	<script type="text/javascript">
		function onRollbackReason(value) {
			var tf_rollback_reason = document
					.getElementById("tf_rollback_reason");
			if (value == "其他") {
				tf_rollback_reason.style.display = "";
			} else {
				tf_rollback_reason.style.display = "none";
				tf_rollback_reason.value = value;
			}
		}

		function onRollback() {
			var tfReason = document.getElementById("tf_rollback_reason");
			alert(tfReason.value);
		}
	</script>
</body>
</html>