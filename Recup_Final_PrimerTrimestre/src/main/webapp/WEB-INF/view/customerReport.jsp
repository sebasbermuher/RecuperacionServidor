<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mostrar Productos</title>
</head>
<body>
	<fmt:setBundle basename="interface" />
	<jsp:include page="include/bootstrap.jsp" />
	<jsp:include page="include/barra.jsp" />
	<div class="container mt-5">
		
		<div class="row justify-content-center">
			<div class="col-8">
				<h2><fmt:message key="nombreCliente"/> ${cliente.customerName}</h2>
				<h2><fmt:message key="totalPagos"/></h2>
				<h2><fmt:message key="numPedidos"/> ${order.orderNumber}</h2>
			</div>
		</div>
		
		
		<%-- <c:if test="${requestScope.cliente eq null}">
			<h2 class="text-danger">Cliente no seleccionado</h2>
			<h4><a href="${pageContext.request.contextPath}/Home">Volver al Inicio</a></h4>
		</c:if> --%>
		
	</div>
</body>
</html>
