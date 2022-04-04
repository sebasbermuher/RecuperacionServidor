<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../include/bootstrap.jsp"/>
<title>Editar Producto</title>
</head>
<body>
	<div class="container mt-5">
	<jsp:include page="../include/barra.jsp"></jsp:include>
		<div class="row justify-content-center mt-3">
			<div class="col-6">
				<form method="post">
				 	<label for="productCode" class="text-danger form-label mt-3">ProductCode</label>
                    <input value="${product.productCode}" type="text" class="form-control" id="productCode" name="productCode" required readonly>
                    
                    <label for="productName" class="text-danger form-label mt-3">ProductName</label>
                    <input value="${product.productName}" type="text" class="form-control" id="productName" name="productName" required>
                    
                    <%-- <label for="productLine" class="text-danger form-label mt-3">ProductLine</label>
                    <input value="${product.productLine}" type="text" class="form-control" id="productLine" name="productLine" required>
                      --%>
                    
                    <label for="productLine" class="form-label mt-3">ProductLine PRUEBA</label>
                    <select class="form-control" name="productLine">
						<c:forEach items="${productLine}" var="pro">
							<option value="${pro.productLine}" id="productLine">${pro.getProductLine()}</option>
						</c:forEach>
					</select> 
                    
                    
                    <label for="productScale" class="form-label mt-3">ProductScale</label>
                    <input value="${product.productScale}" type="text" class="form-control" id="productScale" name="productScale" required>
                    
                    <label for="productVendor" class="form-label mt-3">ProductVendor</label>
                    <input value="${product.productVendor}" type="text" class="form-control" id="productVendor" name="productVendor" required>
                    
                    <label for="productDescription" class="form-label mt-3">ProductDescription</label>
                    <textarea class="form-control" id="productDescription" name="productDescription" required>
                    ${product.productDescription}
                    </textarea>
                                        
                    <label for="quantityInStock" class="text-danger form-label mt-3">quantityInStock</label>
                    <input value="${product.quantityInStock}" type="text" class="form-control" id="quantityInStock" name="quantityInStock" required>
                    
                    <label for="buyPrice" class="text-danger form-label mt-3">buyPrice</label>
                    <input value="${product.buyPrice}" type="text" class="form-control" id="buyPrice" name="buyPrice" required>
					
					<label for="msrp" class="form-label mt-3">MSRP</label>
                    <input value="${product.msrp}" type="text" class="form-control" id="msrp" name="msrp" required>
										
					<input type="submit" class="btn btn-primary w-100 mt-3" value="MODIFICAR" />					
				</form>
			</div>
		</div>
	</div>
</body>
</html>