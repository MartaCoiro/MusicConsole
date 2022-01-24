<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/styleAmmHome.css">
<link rel="stylesheet" href="css/styleNav.css">
<link rel="stylesheet" href="css/styleAmmHome.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/a076d05399.js"></script> <!-- per l'icona e aprire e chiudere -->
</head>

<!-- 												Sfondo																	 -->

<body  background="./imgs/sfondoAmm.png">

<!-- 											Barra di navigazione														 -->

<div>
<nav class="navbar navbar-expand-sm bg-dark justify-content-center navbar-dark" >
	
	<input type="checkbox" id="check">
  <label for="check" class="checkbtn">
   	<i class="fas fa-bars" id="btn"></i>
   	<i class="fas fa-times" id="cancel"></i>
  </label>
 
   <ul class="menu" id="nav" >
    <li class="nav-item active">
    <a class="nav-link" href="${pageContext.request.contextPath}/ServletLogout">Logout</a>
    </li>
    <li class="nav-item active">
 <%
 String ruolo = (String)request.getAttribute("ruolo");
		  if(ruolo.equals("ordini")){
%>
 	<a class="nav-link" href="${pageContext.request.contextPath}/ServletOrdini">Ordini</a>
<%
}else if(ruolo.equals("catalogo")){
%>
	<a class="nav-link" href="${pageContext.request.contextPath}/ServletProd?tipo=catalogo">Prodotti</a>
<%
} else if(ruolo.equals("magazzino")){
%>
	<a class="nav-link" href="${pageContext.request.contextPath}/ServletProd?tipo=magazzino">Magazzino</a>
	</li>
	<li class="nav-item active">
      <a class="nav-link" href="${pageContext.request.contextPath}/ServletMagazzino">Quantità</a>
<%
}
%>   
    </li>
</ul>
</nav>
</div>
  
<div class="container-fluid" >
	<div class="titolo">
	<h1 class="display-1" id="tit" >Music Console</h1>
	</div>
</div>

<h1>Benvenuto</h1>

</body>
</html>