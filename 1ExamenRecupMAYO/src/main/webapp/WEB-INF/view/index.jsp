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
			<div class="col-3">
				<h3>Lista de Categorias</h3>
			</div>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
					
						<th>Categoria</th>
						<th>Descripcion</th>
					<c:if test="${sessionScope.role eq 'user'}">
						<th>Opciones</th>
					</c:if>
					
					
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${productLine}" var="proLine">
						<tr>
							<td>${proLine.productLine}</td>
							<td>${proLine.textDescription}</td>
							<td><c:if test="${sessionScope.role eq 'user'}">
									<a
										href="${pageContext.request.contextPath}/ProductLineReport?pl=${proLine.productLine}"><i
										class="bi bi-eye-fill"></i></a>
								</c:if> 
								
							</td> 
							
							<%-- <td>${o.shippedDate}</td>
							<td>${o.status}</td>
							<td><c:if test="${sessionScope.role eq 'user'}">
									<a
										href="${pageContext.request.contextPath}/OrderReport?orderNumber=${o.orderNumber}"><i
										class="bi bi-eye-fill"></i></a>
								</c:if> <c:if test="${sessionScope.role eq 'admin'}">
									<a
										href="${pageContext.request.contextPath}/Admin/ChangeOrderCustomer?orderNumber=${o.orderNumber}"><i
										class="bi bi-person-circle"></i></a>
								</c:if>
							</td> --%>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>