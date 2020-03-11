<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recharge Planner</title>
<link rel="stylesheet" href="/resources/css/detail.css">
</head>
<body>
	<div class="container">
	<h1>The recharge details are</h1>
	<div class="content">
		<div class="recharge_detail">
			<h3>The plan Name is : ${pack.packageName}</h3>
			<h3>The carrierType is :${pack.carrierType}</h3>
			<h4>The plan validity is:${pack.validity}</h4>
			<h5>The Cost of plan including Tax is :${pack.price}</h5>
			
		</div>
	</div>
	<br><br>
	<a class="home" href="/">Go To Home</a>
	</div>
</body>
</html>