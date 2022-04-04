<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="include/bootstrap.jsp" />
<title>Oficina</title>
</head>
<body>
	<div class="container mt-5">
		<jsp:include page="include/barra.jsp"></jsp:include>
		<div class="row justify-content-center mt-5">
			<form method="post">
				<h2>Selecciona la ciudad de la oficina</h2>
				<select class="form-select" id="codeOficina" name="codeOficina">
					<c:forEach items="${varOficinas}" var="o">
						<option value="${o.officeCode}">${o.city}</option>
					</c:forEach>
				</select> <br> <input type="submit" class="btn btn-primary"
					value="Enviar">
			</form>

			<div class="col-6">
				<h4>Datos de la oficina de ${oficinas.city}</h4>
				<p>Code: ${oficinas.officeCode}</p>
				<p>Ciudad: ${oficinas.city}</p>
				<p>Telefono: ${oficinas.phone}</p>
				<p>Direccion 1: ${oficinas.addressLine1}</p>
				<p>Direccion 2: ${oficinas.addressLine2}</p>
				<p>Estado: ${oficinas.state}</p>
				<p>País: ${oficinas.country}</p>
				<p>Codigo Postal: ${oficinas.postalCode}</p>
				<p>Territorio: ${oficinas.territory}</p>
			</div>
		</div>
	</div>
</body>
</html>