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

		<div class="row justify-content-center mt-3">
			<div class="col-8">
				<h1><fmt:message key="asig" /></h1>

				<form method="post">
					<label for="productName" class="form-label mt-3"><fmt:message key="prod" /></label><br>
					<select class="form-select" name="productName" id="productName">
						<c:forEach items="${product}" var="p">
							<option value="${p.productName}" selected>${p.productName}</option>
						</c:forEach>
					</select> <label for="productLine2" class="form-label mt-3"><fmt:message key="cat" /></label><br>
					<select class="form-select" name="productLine2" id="productLine2">
						<c:forEach items="${productLine}" var="pl">
							<option value="${pl.productLine}" selected>${pl.productLine}</option>
						</c:forEach>
					</select>

					<c:if test="${correcto!=null}">
						<p class="text-success text-small mt-3">${correcto}</p>
					</c:if>

					<c:if test="${error!=null}">
						<p class="text-danger text-small mt-3">${error}</p>
					</c:if>

					<input type="submit" class="btn btn-primary w-100 mt-3"
						value="CHANGE PRODUCT" />
				</form>
			</div>
		</div>


	</div>
</body>
</html>