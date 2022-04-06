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

		<c:if test="${requestScope.categoria!=null}">
			<div class="row justify-content-center">
				<div class="col-10">
				<h2><fmt:message key="productosCategorias"/> ${categoria.toUpperCase()}</h2>
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th><fmt:message key="nombre" /></th>
								<th><fmt:message key="desc" /></th>
								<th><fmt:message key="fabricante" /></th>
								<th><fmt:message key="price" /></th>
								<th><fmt:message key="quantityInStock" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${productos}" var="product">
								<tr>
									<td>${product.productName}</td>
									<td>${product.productDescription}</td>
									<td>${product.productVendor}</td>
									<td>${product.buyPrice}</td>
									<td>${product.quantityInStock}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:if>
		<c:if test="${requestScope.categoria eq null}">
			<h2 class="text-danger">Categoría NO SELECCIONADA</h2>
			<h4><a href="${pageContext.request.contextPath}/Home">Volver al Inicio</a></h4>
		</c:if>
	</div>
</body>
</html>
