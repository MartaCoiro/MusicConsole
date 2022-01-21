<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
<title>Jova Beach Party</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/styleAlbum.css">
<link rel="stylesheet" href="css/styleNav.css">
<link rel="stylesheet" href="css/styleHome.css">
<script src="https://kit.fontawesome.com/a076d05399.js"></script> <!-- per l'icona e aprire e chiudere -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> <!-- per la nav fissa -->

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

<script>
function passa(){
	var xhttp = new XMLHttpRequest();
	$.getJSON("ServletInf?prezzo=7.50&autore=Jovanotti&nome=Jova%20Beach%20Party&tipo=Streaming",function(result){
		if(result.flag){
		alert("Elemento aggiunto al carrello");
		} else{
		alert("Elemento gia' presente nel carrello");
		}
	})
}
</script>

<script>
function passa1(){
	var xhttp = new XMLHttpRequest();
	$.getJSON("ServletInf?prezzo=47.50&autore=Jovanotti&nome=Jova%20Beach%20Party&tipo=Vinile",function(result){
		if(result.flag){
		alert("Elemento aggiunto al carrello");
		} else{
		alert("Elemento gia' presente nel carrello");
		}
	})
}
</script>

<script>
function passa2(){
	var xhttp = new XMLHttpRequest();
	$.getJSON("ServletInf?prezzo=16.50&autore=Jovanotti&nome=Jova%20Beach%20Party&tipo=CD",function(result){
		if(result.flag){
		alert("Elemento aggiunto al carrello");
		} else{
		alert("Elemento gia' presente nel carrello");
		}
	})
}
</script>

</head>

<!-- 							Sfondo	 							-->

<body background=./imgs/sfondo6.jpg>

<!-- 					Barra di navigazione 						-->

<div>
<nav class="navbar navbar-expand-sm bg-dark justify-content-center navbar-dark">

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
    </li>
    
  </ul>
  
  <form action="ServletCerca" name="ricerca" class="navbar-form navbar-left" role="search">
  <div id="cerca">
 <input type="text" id="cerca" name="parola"  placeholder="Ricerca"> 
  </div>
   <a href="#"> <button type="submit" value="Cerca" id="cerca">Cerca</button></a>
</form>
<a href="${pageContext.request.contextPath}/ServletCarrello"><button id="carrello" onClick=""><img src="./imgs/carrello.png" height="45px" alt="Foto carrello"></button></a> 
  
   </nav>
 </div>
 
 <!-- 						Titolo 									-->
 
 <div class="container-fluid" >
	<div class="titolo">
	<h1 class="display-1" id="tit" >Music Console</h1>
</div>
 
  <!-- 						Album								-->

 <h2 id="testo">Jova Beach Party - Jovanotti</h2>
 <hr>
 
  <div class="riempimento2">
 <div  class="costo" >
 <h3> Acquista in :</h3>
 <button id="bott" onClick="passa()">Aggiungi al Carrello</button>
 <button id="bott2" onClick="passa1()">Aggiungi al Carrello</button>
 
 <p class="streaming"> Formato <br>
 	STREAMING <br>
 	  7,50&euro;
 </p>
 
  <button id="bott1" onclick="passa2()">Aggiungi al Carrello</button>
  
 <p class="cd"> Formato <br>
 	   CD    <br>
 	  16,50&euro;
 </p>

 <p class="vinile"> Formato <br>
 	  VINILE    <br>
 	  47,50&euro;
 </p>
 </div>
 
<div class="Jova img-thumbnail" style="background-image: url(./imgs/img19.png)"> 
</div>  
</div>	
	<h4><hr> Brani </h4>
	<h5 class="inf"> L'Album ha una durata di 30:08 min ed &egrave; stato pubblicato il 7 Giugno 2019. </h5>
	<p class="brani">
    1) Prima che diventi giorno 4:59
    <br>
    2) Nuova era 3:47
    <br>
    3) Vado 2:42
    <br>
    4) Fiesta 3:12
     <br>
    5) Tutto un fuoco 3:11
    <br>
    6) Il sole sorge di sera 6:45
    <br>
    7) XCHETUC6 3:23
    <br>
    8) PCDG Reprise 3:51
    <br>
   
    </p>
    </div>
</body>
</html>