<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="include/bootstrap.jsp" />
<fmt:setBundle basename="interface" />
<title>Home</title>
</head>
<body>
	<fmt:setBundle basename="interface" />
	<jsp:include page="include/barra.jsp" />
	<div class="container mt-5">
		<div class="row justify-content-center mt-5">

			<c:if test="${sessionScope.role eq 'admin'}">
				<div class="row justify-content-end">
					<div class="col-3">
						<a class="btn btn-primary w-100"
							href="${pageContext.request.contextPath}/Admin/AddCustomer">AÑADIR
							CUSTOMER</a>
					</div>
				</div>
			</c:if>

			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th><fmt:message key="numCliente" /></th>
						<th><fmt:message key="nombreCliente" /></th>
						<th><fmt:message key="nombreContacto" /></th>
						<th><fmt:message key="apellidosContacto" /></th>
						<th><fmt:message key="telefono" /></th>
						<th><fmt:message key="opciones" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${customer}" var="cust">
						<tr>
							<td>${cust.customerNumber}</td>
							<td>${cust.customerName}</td>
							<td>${cust.contactFirstName}</td>
							<td>${cust.contactLastName}</td>
							<td>${cust.phone}</td>
							<td><c:if test="${sessionScope.role eq 'user'}">
									<a
										href="${pageContext.request.contextPath}/CustomerReport?cn=${cust.customerNumber}"><i
										class="bi bi-eye-fill"></i></a>
								</c:if> <c:if test="${sessionScope.role eq 'admin'}">
									<a
										href="${pageContext.request.contextPath}/Admin/ChangeAssigment?cn=${cust.customerNumber}"><i
										class="bi bi-person-circle"></i></a>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<c:if test="${sessionScope.role eq 'admin'}">
				<div class="row justify-content-end">
					<div class="col-3">
						<a class="btn btn-primary w-100" href="AddEmpleado">AÑADIR
							CUSTOMER</a>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>