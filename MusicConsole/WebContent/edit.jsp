<!DOCTYPE html>
<html lang="it">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,gestioneAccount.*, gestioneAcquisti.*, gestioneCarrello.*, gestioneProdotti.*"%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script> <!-- per l'icona e aprire e chiudere -->
<link rel="stylesheet" href="css/styleNav.css">
<link rel="stylesheet" href="css/styleHome.css">
<link rel="stylesheet" href="css/styleMyAccount.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="css/styleEdit.css">

<title>MyAccountEdit</title>

<script>//per vedere e nascondere password
function showPwd() {
        var input = document.getElementById('pwd');
        if (input.type === "password") {
          input.type = "text";
        } else {
          input.type = "password";
        }
      }
</script>

<script>//barra di navigazione fissa
$(document).ready(function() {
var stickyNavTop = $('nav').offset().top;

var stickyNav = function(){
var scrollTop = $(window).scrollTop();

if (scrollTop > stickyNavTop) {
$('nav').addClass('sticky');
} else {
$('nav').removeClass('sticky');
}
};

stickyNav();

$(window).scroll(function() {
stickyNav();
});
});
</script>

</head>

<!-- 												Sfondo																	 -->

<body background=./imgs/sfondo6.jpg>

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
      <a class="nav-link" href="MyAccount.jsp">My Account</a>
    </li>
    <li class="nav-item active">
      <a class="nav-link" href="home.jsp">Home</a>
    </li>
    <li class="nav-item active">
      <a class="nav-link" href="listaplaylist.jsp">Playlist</a>
    
  </ul>
  
  <!-- 									Barra di Ricerca													 -->
  
  <form action="ServletCerca" name="ricerca" class="navbar-form navbar-left" role="search">
  <div id="cerca">
 <input type="text" id="cerca" name="parola" placeholder="Ricerca"> 
  </div>
   <a href="#"> <button type="submit" value="Cerca" id="cerca">Cerca</button></a>
</form>
<a href="${pageContext.request.contextPath}/ServletCarrello"><button id="carrello" onClick=""><img src="./imgs/carrello.png" height="45px" alt="Foto carrello"></button></a> 
   </nav>
 </div>
 
 <!-- 											Titolo																		 -->
 
<div class="container-fluid" >
	<div class="titolo">
	<h1 class="display-1" id="tit" >Music Console</h1>
	</div>
</div>

<!-- 											Account Edit 																-->

<%
Profilo p = (Profilo)session.getAttribute("p");
%>

<div class="container rounded bg-white mt-5">
    <div class="row">
        <div class="col-md-8">
            <div class="p-3 py-5">
            <form action="ServletMyAccount" method="POST">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h6 class="text-right">Modifica Profilo</h6>
                </div>
                <div class="row mt-2">
                
                    <div class="col-md-6">Nome:<input name="nome" type="text" class="form-control" placeholder="Nome" value="<%=p.getNome()%>"></div>
                    <div class="col-md-6">Cognome:<input name="cognome" type="text" class="form-control" value="<%=p.getCognome()%>" placeholder="Cognome"></div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-6">Email:<input name="email" type="text" class="form-control" placeholder="Email" value="<%=p.getEmail()%>"></div>
                    <div class="col-md-6">Telefono:<input name="telefono" type="text" class="form-control" value="<%=p.getTelefono()%>" placeholder="Telefono"></div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-6">Indirizzo:<input name="indirizzo" type="text" class="form-control" placeholder="Indirizzo" value="<%=p.getIndirizzo()%>"></div>
                    <div class="col-md-6">Città:<input name="citta" type="text" class="form-control" value="<%=p.getCitta()%>" placeholder="Città"></div>
                </div>
                <div class="row mt-3">
               		<div class="col-md-6">Password:<input id="pwd" name="password" type="password" maxlength="8" class="form-control" placeholder="password" value="<%=p.getPassword()%>"></div>
               	 </div>
               	  <div class="row mt-3">	
               		<div class="col-md-6"><input type="button" onclick="showPwd()" value="Mostra/nascondi password"></div>
              	</div>
                
                <div class="mt-5 text-right"><input type="submit" class="btn btn-primary profile-button" value="Salva Profilo"></div>
            	
            

            	</form>
            </div>
        </div>
    </div>
</div>
</body>
</html>