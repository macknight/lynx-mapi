<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<link href="<s:url value='/s/css/customer.css'/>" rel="stylesheet" type="text/css" />
	<title><decorator:title /></title>
	<script src="../script/js/lib/seajs-2.1.1/sea.js"></script>
	<script>
		// Set configuration
		seajs.config({
			base: "../../",
			alias: {
				"jquery": "./lib/jquery-1.10.2.min.js"
			}
		});
		seajs.use("./biz/parenting/hello.js");
	</script>
</head>
<body>
	<input type='button' text="ok" />
</body>
</html>