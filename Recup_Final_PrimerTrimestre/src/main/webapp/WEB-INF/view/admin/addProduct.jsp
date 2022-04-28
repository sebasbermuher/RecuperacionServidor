<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Añadir Product</title>
</head>
<body>
	<fmt:setBundle basename="interface" />
	<jsp:include page="../include/bootstrap.jsp" />
	<jsp:include page="../include/barra.jsp" />
	<div class="container mt-5">


		 <div class="row justify-content-center mt-3">
			<div class="col-6">
				<form method="post">		
				
					<label for="productCode" class="form-label mt-3">Product Code</label>
		    		<input type="text" class="form-control" id="productCode" name="productCode" required >
		    		
		    		<label for="productName" class="form-label mt-3">Product Name</label>
		    		<input type="text" class="form-control" id="productName" name="productName" required >
		    		
		    		<%-- <label for="productLine" class="form-label mt-3">Product Line</label>
		    		<select name="productLine" class="form-select">
    					<c:forEach  items="${pl}" var="proLine">
							<option value="${eproLine.productLine}"> ${proLine.productLine }</option>
						</c:forEach>
    				</select> --%>
		    		
		    		<label for="productScale" class="form-label mt-3">Product Scale</label>
		    		<input type="text" class="form-control" id="productScale" name="productScale" required >
		    		
		    		<label for="productVendor" class="form-label mt-3">Product Vendor</label>
		    		<input type="text" class="form-control" id="productVendor" name="productVendor" >
		    		
		    		<label for="productDescription" class="form-label mt-3">Product Description</label>
		    		<input type="text" class="form-control" id="productDescription" name="productDescription" required >
		    		
		    		<label for="quantityInStock" class="form-label mt-3">Stock</label>
		    		<input type="number" class="form-control" id="quantityInStock" name="quantityInStock" required >
		    		
		    		<label for="buyPrice" class="form-label mt-3">Buy Price</label>
		    		<input type="number" class="form-control" id="buyPrice" name="buyPrice" required >
		    		
		    		<label for="MSRP" class="form-label mt-3">MSRP</label>
		    		<input type="number" class="form-control" id="MSRP" name="MSRP" required >
		    		
		    				    		
		    		<%-- <label for="salesRepEmployeeNumber" class="form-label mt-3">Empleado</label>
		    		<select name="salesRepEmployeeNumber" class="form-select">
    					<c:forEach  items="${empleados}" var="e">
							<option value="${e.employeeNumber}"> ${e.lastName } , ${e.firstName }</option>
						</c:forEach>
    				</select> --%>
		    		
		    							
					<input type="submit" class="btn btn-primary w-100 mt-3" value="CREATE PRODUCT" />
					
					
					
					
				</form>
			</div>
		</div>
		
	</div>
</body>
</html>