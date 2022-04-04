<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="interface" />  
<jsp:include page="../include/bootstrap.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Productos</title>
</head>
<body>
	<div class="container mt-5">
	<jsp:include page="../include/barra.jsp"></jsp:include>
		<div class="row justify-content-end">
			
		</div>
		<div class="row justify-content-center">
			<div class="col-10">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Codigo</th>
							<th>Nombre</th>
							<th>Categoria</th>
							<th>Stock</th>
							<th>Precio</th>
							<th>Accion</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listaProductos}" var="p">
							<tr>
								<td>${p.productCode}</td>
								<td>${p.productName}</td>
								<td>${p.productLine}</td>
								<td>${p.quantityInStock}</td>
								<td>${p.buyPrice}</td>
								<%-- <td><a href="EditarProduct?cn=${pago.customerNumber}&checkn=${pago.checkNumber}"><i class="bi bi-pencil-fill"></i></a> --%>
								 <td><a href="EditarProduct?productCode=${p.productCode}"><i class="bi bi-pencil-fill"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>