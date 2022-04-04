<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Confirmar</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="barra.jsp"></jsp:include>
	<div class="container">
		<div class="row mt-5 justify-content-center">
			<div class="col-4 fs-2">
			
			
			<p>Fechita <%= (session.getAttribute("fInicio")) %>  </p>
						
			<jsp:useBean id="userBean" scope="session" class="org.iesalixar.servidor.model.Reserva" /> 
		
			<h2>RESERVA CONFIRMADA</h2>
			<p>FECHA DE INICIO: <jsp:getProperty name="userBean" property="fInicio"/></p>
			<p>FECHA DE FIN: <%= (userBean.getfFin()) %></p>
			<p>NUMERO DE PERSONAS: <%= (userBean.getNumPer()) %></p>
<%-- 			<p>SERVICIOS RESERVADOS: <%= (userBean.getServicios()) %> </p>
 --%>					
 
 			
			
			</div>
		</div>
	</div>



</body>
</html>