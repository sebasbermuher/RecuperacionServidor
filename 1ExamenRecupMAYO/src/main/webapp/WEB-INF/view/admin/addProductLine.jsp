<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Añadir Categoria</title>
</head>
<body>
	<fmt:setBundle basename="interface" />
	<jsp:include page="../include/bootstrap.jsp" />
	<jsp:include page="../include/barra.jsp" />
	<div class="container mt-5">


		 <div class="row justify-content-center mt-3">
		 <h2><fmt:message key="addCat" /></h2>
			<div class="col-6">
				<form method="post">
				
					<c:if test="${añadido!=null}">
						<p class="text-success text-small mt-3">${añadido}</p>
					</c:if>	
				
					<label for="productLine" class="form-label mt-3"><fmt:message key="cat" /></label>
		    		<input type="text" class="form-control" id="productLine" name="productLine" required >
		    		
		    		<label for="textDescription" class="form-label mt-3"><fmt:message key="descrip" /></label>
		    		<input type="text" class="form-control" id="textDescription" name="textDescription" required >
		    		
		    		<c:if test="${error!=null}">
						<p class="text-danger text-small mt-3">${error}</p>
					</c:if>
		    		
		    		
					<c:if test="${añadido!=null}">
						<p class="text-success text-small mt-3">${añadido}</p>
					</c:if>	
								    							
					<input type="submit" class="btn btn-primary w-100 mt-3" value="AÑADIR PRODUCTLINE" />
					
					
					
					
				</form>
			</div>
		</div>
		
	</div>
</body>
</html>