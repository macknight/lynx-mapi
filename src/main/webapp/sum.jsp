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
	<br />
	<s:form action="demo/sum">
		<s:textfield name="arg1" label="arg1" />
		<s:textfield name="arg2" label="arg2" />
		<s:submit value="submit" />
	</s:form>
</body>
</html>