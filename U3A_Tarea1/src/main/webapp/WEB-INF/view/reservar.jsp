<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reservar</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
</head>
<body>

	<jsp:include page="barra.jsp"></jsp:include>
	<div class="container">

		<h1 style="color: navy; text-align: center">DATOS DE LA RESERVA</h1>
		<div class="row justify-content-center mt-5">

			<div class="col-3">
				<form method="post">
				
				<label class="form-label" for="fInicio">Fecha de Inicio:</label>
					<input class="form-control" type="date" name="fInicio" required/>
					<label class="form-label mt-3" for="fFin">Fecha de Finalizacion:</label>
					<input class="form-control" type="date" name="fFin" required />
					<label class="form-label mt-3" for="numPer">Numero de Personas:</label>
					<input class="form-control mb-3" name="numPer" type="number" required/>
					
					<label
						class="form-label">SERVICIOS</label>
					<div class="row">
						<div class="col">
							<input type="checkbox" name="checkbox" value="Wifi"> Wifi<br>
							<input type="checkbox" name="checkbox" value="Toallas">
							Toallas<br> <input type="checkbox" name="checkbox"
								value="Desayuno"> Desayuno
						</div>
						<div class="col">
							<input type="checkbox" name="checkbox" value="Limpieza">
							Limpieza<br> <input type="checkbox" name="checkbox"
								value="Ruta guiada"> Ruta guiada<br> <input
								type="checkbox" name="checkbox" value="Sauna"> Sauna
						</div>
					</div>
					<br>
					<input name="reservar" type="submit" class="btn btn-primary w-100" value="RESERVAR" />

				</form>
			</div>

		</div>
	</div>


</body>
</html>