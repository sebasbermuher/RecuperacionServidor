<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicio</title>
<jsp:include page="bootstrap.jsp"></jsp:include>

</head>
<body>
	<div class="container">

		<h1 style="color: navy; text-align: center">CENTRAL DE VIAJES</h1>
		<div class="row justify-content-center mt-5">

			<%
			if (!session.isNew() && session.getAttribute("LOGEADO") != null && (boolean) session.getAttribute("LOGEADO")) {
			%>
			<jsp:include page="barra.jsp"></jsp:include>
			<div class="col-8">
				<img style="width: 800px; heigth: 800px;" alt="error"
					src="https://www.imghoteles.com/wp-content/uploads/sites/1709/nggallery/desktop-pics/fott1.jpg">
			</div>

			<%
			} else {
			%>

			<div class="col-3">
				<form method="post">
					<label class="form-label">Usuario</label> <input
						class="form-control" type="text" name="nombre" required /> <label
						class="form-label">Contraseña</label> <input class="form-control"
						type="password" name="pass" required /> <label
						class="form-label">Confirmar contraseña</label> <input
						class="form-control" type="password" name="pass2" required /> <label class="form-label">Email</label> <input
						class="form-control" type="email" name="email" required /> <br>
					<br> <input name="registrar" type="submit"
						class="btn btn-primary w-100" value="REGISTRARSE" />
				</form>
			</div>
			<%
			}
			%>

		</div>
	</div>
</body>
</html>