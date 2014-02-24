<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set name="menu" value="330" />
<head>
<meta charset="utf-8" />
<title>财务管理-催款管理</title>
</head>
<body>
	<div class="container">
		<div class="mod-main">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="offline-table" text-align="center" id="dunning-records-table">
				<tr>
					<th width="6%" scope="col">MCC</th>
					<th width="8%" scope="col">MNC</th>
					<th width="8%" scope="col">LAC</th>
					<th width="8%" scope="col">CID</th>
					<th width="12%" scope="col">Lat</th>
					<th width="12%" scope="col">Lng</th>
					<th width="20%" scope="col">Addr</th>
				</tr>
				<s:iterator value="shopDunningRecordVOs">
				<tr>
					<td><p class="clor-gray"><s:property value="shopId" /></td>
					<td>
						<p><s:property value="shopName" /></p>
						<p class="clor-gray"><s:property value="city" /></p>
					</td>
					<td><p><s:property value="smsNum"/>/<s:property value="callNum"/></td>
					<td><p><s:property value="id"/></p></td>
					<td><p><s:property value="shopStatus"/></p></td>
					<td><p><s:property value="shopStatus"/></p></td>
					<td><p><s:property value="shopStatus"/></p></td>
				</tr>
				</s:iterator>
			</table>
			<input type="hidden" id="recordCount" data=<s:property value="recordCount"/> />
			<input type="hidden" id="pageNumber" data=<s:property value="pageNumber"/> />
			<script language="JavaScript">
            	var pg = new showPages('pg');
            	pg.pageCount = document.getElementById("recordCount");
            	pg.printHtml();
            </script>
		</div>
	</div>

</body>