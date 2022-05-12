<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Report</title>
</head>
<body>
	<fmt:setBundle basename="interface" />
	<jsp:include page="include/bootstrap.jsp" />
	<jsp:include page="include/barra.jsp" />
	<div class="container mt-5">


		<c:if test="${requestScope.order!=null}">

			<div class="row justify-content-center">
				<div class="col-3">
					<h2>Order Report</h2>
				</div>
			</div>

			<div class="row justify-content-center">
				<div class="col-12">
					<h4>OrderCode: ${order.orderNumber}</h4>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Product Name</th>
								<th scope="col">Quantity</th>
								<th scope="col">Price Each</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${orderDetails}" var="o">
								<tr>
									<td>${o.productName}</td>
									<td>${o.quantityOrdered}</td>
									<td>${o.priceEach}</td>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

		</c:if>

		<c:if test="${requestScope.order eq null}">
			<h2 class="text-danger">Pedido NO SELECCIONADO</h2>
			<h4>
				<a href="${pageContext.request.contextPath}/Home">Volver al Inicio</a>
			</h4>
		</c:if>


	</div>
</body>
</html>
