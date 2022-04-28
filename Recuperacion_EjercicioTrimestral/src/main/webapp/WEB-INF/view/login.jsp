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
<title>Login</title>
</head>
<body>
<jsp:include page="include/barra.jsp"></jsp:include>
	<div class="container mt-5">
		<div class="row justify-content-center mt-5">
			<div class="col-6">
				<!-- Solo saco el formulario de login si no tengo sesion -->
				<c:if test="${sessionScope.usuario==null}">
					<form method="post">
						<label for=email class="form-label"><fmt:message key="email"/></label> 
						<input type="email" class="form-control" id="email" name="usuario" required> 
						
						<label for="password" class="form-label mt-3"><fmt:message key="password"/></label>
						<input type="password" class="form-control" id="password" name="password" required>
						
						<c:if test="${error!=null}">
							<p class="text-danger text-small mt-3">${error}</p>
						</c:if>
						
						<input type="submit" class="btn btn-primary w-100 mt-3" value="<fmt:message key="enter"/>" />
					</form>
				</c:if>
				<c:if test="${sessionScope.usuario!=null}">
					<img class="img-fluid"
						src="http://3.bp.blogspot.com/-bvI4KRtlzAg/UH2hG7_k_zI/AAAAAAAAAEs/nTYaNhMDUZM/s1600/bienvenido.png">
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>