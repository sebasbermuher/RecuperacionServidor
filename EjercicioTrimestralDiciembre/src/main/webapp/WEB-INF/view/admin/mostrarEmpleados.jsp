<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mostrar Empleados</title>
</head>
<body>

	<fmt:setBundle basename="interface" />
	<jsp:include page="../include/bootstrap.jsp" />
	<jsp:include page="../include/barra.jsp" />
	
	<div class="container mt-5">
		

		<div class="row justify-content-end">
			<div class="col-3">
				<a class="btn btn-primary w-100" href="AddEmpleado">AÑADIR EMPLEADO</a>
			</div>
		</div>
		
		<div class="col-12">
			<h1 class="text-center">EMPLEADOS</h1>
		</div>
		<div class="row justify-content-center">
			<div class="col-10">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>Numero</th>
							<th>Apellidos</th>
							<th>Nombre</th>
							<th>Puesto</th>
							<th>Oficina</th>
							<th>Jefe</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${employee}" var="e">
							<tr>
								<td>${e.employeeNumber}</td>
								<td>${e.lastName}</td>
								<td>${e.firstName}</td>
								<td>${e.jobTitle}</td>
								<%-- <td>${e.officeCode}</td>
								<td>${e.reportsTo}</td>  --%>
								
								<td>
									<c:forEach items="${offices}" var="ofi">
									<c:if test="${e.officeCode == ofi.officeCode}">
										${ofi.city}
									</c:if>
								</c:forEach>
								</td>
								<td>
									<c:forEach items="${employee }" var="emplo">
									<c:if test="${e.reportsTo == emplo.employeeNumber }">
										${emplo.firstName } ${emplo.lastName }
									</c:if>
								</c:forEach>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>