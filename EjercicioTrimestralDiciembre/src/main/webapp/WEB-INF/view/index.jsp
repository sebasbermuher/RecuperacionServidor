<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<jsp:include page="include/bootstrap.jsp"/>
<fmt:setBundle basename="interface" />
<title>Home</title>
</head>
<body>
	<jsp:include page="include/barra.jsp"/>
	<div class="container mt-5">
		<div class="row justify-content-center mt-5">
			<div class="col-6">
			<!-- Solo saco el formulario de login si no tengo sesion -->
			<c:if test="${sessionScope.role eq 'admin'}">
				<h1>Operaciones disponibles</h1>
					<li><a href="${pageContext.request.contextPath}/Admin/Empleados">Mostrar Empleados</a></li>	
					<li><a href="${pageContext.request.contextPath}/Admin/AddEmpleado">Añadir Empleados</a></li>	
			</c:if>
			
			
			<c:if test="${sessionScope.role eq 'user'}">
				
				<h1><fmt:message key="categorias"/></h1>
				
				<ul>
					<c:forEach items="${categorias}" var="c">
						<li><a href="${pageContext.request.contextPath}/MostrarProductos?productLine=${c.productLine}">${c.productLine}</a></li>		
					</c:forEach>
				</ul>
			</c:if>
			</div>
		</div>
	</div>
</body>
</html>