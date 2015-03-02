<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SHOP</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/index.css" />" />
</head>
<body>
	<div id="header">
		<c:import url="/header.jsp" />
	</div>

	<div id="content">
		<c:import url="/sidebarLeft.jsp" />
		<c:import url="/center.jsp" />
		<c:import url="/sidebarRight.jsp" />
	</div>
</body>
</html>