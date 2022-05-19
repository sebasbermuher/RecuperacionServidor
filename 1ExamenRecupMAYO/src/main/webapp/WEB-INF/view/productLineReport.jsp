<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ProductLine Report</title>
</head>
<body>
	<fmt:setBundle basename="interface" />
	<jsp:include page="include/bootstrap.jsp" />
	<jsp:include page="include/barra.jsp" />
	<div class="container mt-5">
		
		<div class="row justify-content-center">
			<div class="col-8">
				<h2>Informe de categoria:/> ${proLine.productLine}</h2>
			</div>
		</div>
		
	</div>
</body>
</html>
