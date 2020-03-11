<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Recharge Planner</title>
<link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>
	
	<div id="container">
		<div id="content">
			<h1>listing all packages</h1>
			<!--  add our html table here -->
			<table>
				<tr>
					<th>id</th>
					<th>PackageName</th>
					<th>CarrierType</th>
					<th>Validity</th>
					<th>Price</th>
				</tr>
				<!-- loop over and print our packages -->
				<c:forEach var="pack" items="${packages}">
					<tr>
						<td> ${pack.id} </td>
						<td> ${pack.packageName} </td>
						<td> ${pack.carrierType} </td>
						<td> ${pack.validity} </td>
						<td> ${pack.price} </td>
						<td> <a class="choose-btn" href="./detail/${pack.id}">Choose</a></td>
					</tr>
				</c:forEach>	
			</table>
		</div>
	</div>
</body>
</html>