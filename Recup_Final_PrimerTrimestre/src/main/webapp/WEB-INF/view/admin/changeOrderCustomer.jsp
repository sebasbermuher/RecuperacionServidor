<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Order Customer</title>
</head>
<body>
	<fmt:setBundle basename="interface" />
	<jsp:include page="../include/bootstrap.jsp" />
	<jsp:include page="../include/barra.jsp" />
	<div class="container mt-5">


		<c:if test="${requestScope.order!=null}">

			<div class="row justify-content-center mt-3">
				<div class="col-8">
					<h1>CHANGE PRODUCT CUSTOMER</h1>

					<h4>Order Code: ${order.orderNumber}</h4>


					<form method="post">
						<label for="customer" class="form-label mt-3">CUSTOMER</label> 
						
						<select	name="customer" class="form-select">
							<c:forEach items="${customers}" var="c">
								<c:choose>
									<c:when test="${c.customerNumber == order.customerNumber}">
										<option value="${c.customerNumber}" selected>${c.customerName}</option>
									</c:when>
									<c:otherwise>
										<option value="${c.customerNumber}">${c.customerName}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select> 
						
						 <c:if test="${error!=null}">
							<p class="text-danger text-small mt-3">${error}</p>
						</c:if>	
												
				<input type="submit" class="btn btn-primary w-100 mt-3" value="CHANGE CUSTOMER" />
					</form>
				</div>
			</div>

		</c:if>

		<c:if test="${requestScope.order eq null}">
			<h2 class="text-danger">Pedido NO SELECCIONADO</h2>
			<h4>
				<a href="${pageContext.request.contextPath}/Home">Volver al
					Inicio</a>
			</h4>
		</c:if>


	</div>
</body>
</html>