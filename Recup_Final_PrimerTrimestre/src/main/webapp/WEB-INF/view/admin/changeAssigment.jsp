<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Assigment</title>
</head>
<body>
	<fmt:setBundle basename="interface" />
	<jsp:include page="../include/bootstrap.jsp" />
	<jsp:include page="../include/barra.jsp" />
	<div class="container mt-5">

		 <div class="row justify-content-center mt-3">
			<div class="col-8">
				<h2>CLIENTE: ${cliente.customerName}</h2>
				<h2>CONTACTO EN CLIENTE: ${cliente.contactFirstName} ${cliente.contactLastName}</h2>
				
				<form method="post">	    				    		
		    		<label for="salesRepEmployeeNumber" class="form-label mt-3">Empleado Asignado</label>
		    		<select name="salesRepEmployeeNumber" class="form-select">
    					<c:forEach items="${empleados}" var="em">
    						<c:choose>
    							<c:when test="${em.employeeNumber == cliente.salesRepEmployeeNumber}">
    								<option value="${em.employeeNumber }" selected>${em.firstName} ${em.lastName}</option>
    							</c:when>
    							<c:otherwise>
    								<option value="${em.employeeNumber }">${em.firstName} ${em.lastName}</option>
    							</c:otherwise>
    						</c:choose>
    					</c:forEach>
    				</select>
		    			    							
					<input type="submit" class="btn btn-primary w-100 mt-3" value="ASIGNAR EMPLEADO" />	
				</form>
			</div>
		</div>
		
				
		<%-- <c:if test="${requestScope.cn eq null}">
			<h2 class="text-danger">Cliente no seleccionado</h2>
			<h4><a href="${pageContext.request.contextPath}/Home">Volver al Inicio</a></h4>
		</c:if> --%>
		
	</div>
</body>
</html>