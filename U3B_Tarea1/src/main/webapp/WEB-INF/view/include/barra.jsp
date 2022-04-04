<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.usuario!=null}">
	<nav class="navbar navbar-expand-lg navbar-light bg-primary">
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav">
				<c:if test="${role=='user'}">
				<li class="nav-item active"><a class="nav-link text-white"
					href="${pageContext.request.contextPath}/">Inicio</a></li>
				<li class="nav-item"><a class="nav-link text-white" href="${pageContext.request.contextPath}/Oficinas">Buscar Oficinas</a>
				</li>
				</c:if>
				
				<c:if test="${role=='admin'}">
				<li class="nav-item active"><a class="nav-link text-white"
					href="${pageContext.request.contextPath}/Admin/Inicio">Inicio</a></li>
				</c:if>
			</ul>
			<span class="navbar-text d-flex">
					
						<p class="text-white">
							Está usted logeado como:
							<c:out value="${sessionScope.usuario}"></c:out>
						</p>
				
					<a class="text-white"
						href="${pageContext.request.contextPath}/LogOut"><i
							class="bi bi-box-arrow-left"></i></a>
				</span>
		</div>
	</nav>
</c:if>