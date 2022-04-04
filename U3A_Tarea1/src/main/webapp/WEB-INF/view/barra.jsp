<%@page import="java.util.*" %>
<ul class="nav justify-content-end bg-light">
  <li class="nav-item">
    <a class="nav-link" aria-current="page" href="<%= request.getContextPath() %>"><i class="bi bi-house-fill"></i>Volver</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="<%= request.getContextPath()+"/Logout" %>"><i class="bi bi-x-octagon-fill"></i>Salir</a>
  </li>  
  <li class="nav-item">
    <p>Usuario: <%= (String) session.getAttribute("nombre") %></p>
  </li>
  <li class="nav-item">
    <p> Sesion: <%= new Date(session.getCreationTime()) %></p>
  </li> 
</ul>