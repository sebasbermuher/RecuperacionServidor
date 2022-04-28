<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Añadir Customer</title>
</head>
<body>
	<fmt:setBundle basename="interface" />
	<jsp:include page="../include/bootstrap.jsp" />
	<jsp:include page="../include/barra.jsp" />
	<div class="container mt-5">


		 <div class="row justify-content-center mt-3">
			<div class="col-6">
				<form method="post">
					
					<c:if test="${error!=null}">
						<p class="text-danger text-small mt-3">${error}</p>
					</c:if>
				
					<label for="customerNumber" class="form-label mt-3">Numero de cliente</label>
		    		<input type="number" class="form-control" id="customerNumber" name="customerNumber" required >
		    		
		    		<label for="customerName" class="form-label mt-3">Nombre del cliente</label>
		    		<input type="text" class="form-control" id="customerName" name="customerName" required >
		    		
		    		<label for="contactFirstName" class="form-label mt-3">Nombre de la persona de contacto</label>
		    		<input type="text" class="form-control" id="contactFirstName" name="contactFirstName" required >
		    		
		    		<label for="contactLastName" class="form-label mt-3">Apellido de la persona de contacto</label>
		    		<input type="text" class="form-control" id="contactLastName" name="contactLastName" required >
		    		
		    		<label for="phone" class="form-label mt-3">Telefono</label>
		    		<input type="text" class="form-control" id="phone" name="phone" >
		    		
		    		<label for="addressLine1" class="form-label mt-3">Direccion 1</label>
		    		<input type="text" class="form-control" id="addressLine1" name="addressLine1" required >
		    		
		    		<label for="addressLine2" class="form-label mt-3">Direccion 2</label>
		    		<input type="text" class="form-control" id="addressLine2" name="addressLine2" required >
		    		
		    		<label for="city" class="form-label mt-3">Ciudad</label>
		    		<input type="text" class="form-control" id="city" name="city" required >
		    		
		    		<label for="state" class="form-label mt-3">Estado</label>
		    		<input type="text" class="form-control" id="state" name="state" required >
		    		
		    		<label for="postalCode" class="form-label mt-3">CP:</label>
		    		<input type="text" class="form-control" id="postalCode" name="postalCode" required >
		    		
		    		<label for="country" class="form-label mt-3">País</label>
		    		<input type="text" class="form-control" id="country" name="country" required >
		    		
		    		<label for="salesRepEmployeeNumber" class="form-label mt-3">Empleado</label>
		    		<select name="salesRepEmployeeNumber" class="form-select">
    					<c:forEach  items="${empleados}" var="e">
							<option value="${e.employeeNumber}"> ${e.lastName } , ${e.firstName }</option>
						</c:forEach>
    				</select>
		    		
		    		<label for="creditLimit" class="form-label mt-3">Límite de credito</label>
		    		<input type="number" class="form-control" id="creditLimit" name="creditLimit" required >
		    		
		    		
		    		<c:if test="${error!=null}">
						<p class="text-danger text-small mt-3">${error}</p>
					</c:if>
		    							
					<input type="submit" class="btn btn-primary w-100 mt-3" value="AÑADIR" />
					
					
					
					
				</form>
			</div>
		</div>
		
	</div>
</body>
</html>