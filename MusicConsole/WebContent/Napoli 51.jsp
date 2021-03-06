<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
<title>Napoli 51</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/styleAlbum.css">
<link rel="stylesheet" href="css/styleNav.css">
<link rel="stylesheet" href="css/StyleMedia.css">
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
	$.getJSON("ServletInf?prezzo=6.50&autore=Nicola%20Siciliano&nome=Napoli%2051&tipo=Streaming",function(result){
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
	$.getJSON("ServletInf?prezzo=25.50&autore=Nicola%20Siciliano&nome=Napoli%2051&tipo=Vinile",function(result){
		if(result.flag){
		alert("Elemento aggiunto al carrello");
		}else{
		alert("Elemento gia' presente nel carrello");
		}
	})
}
</script>

<script>
function passa2(){
	var xhttp = new XMLHttpRequest();
	$.getJSON("ServletInf?prezzo=17.50&autore=Nicola%20Siciliano&nome=Napoli%2051&tipo=CD",function(result){
		if(result.flag){
		alert("Elemento aggiunto al carrello");
		}else{
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
  
  <form class="navbar-form navbar-left" role="search">
  <div id="cerca">
 <input type="text" id="cerca"  placeholder="Ricerca"> 
  </div>
   <a href="#"> <button type="submit" id="cerca">Cerca</button></a>
</form>
<a href="${pageContext.request.contextPath}/ServletCarrello"><button id="carrello" onclick=""><img src="./imgs/carrello.png" height="45px" alt="Foto carrello"></button></a> 

   </nav>
 </div>
 
  <!-- 						Titolo 									-->
  
 <div class="container-fluid" >
	<div class="titolo">
	<h1 class="display-1" id="tit" >Music Console</h1>
</div>
 
 <!-- 						Album								-->

 <h2 id="testo">Napoli51 - Nicola Siciliano</h2>
 <hr>
 
  <div class="riempimento2">
 <div  class="costo" >
 <h3> Acquista in :</h3>
 <button id="bott" onClick="passa()">Aggiungi al Carrello</button>
 <button id="bott2" onClick="passa1()">Aggiungi al Carrello</button>
 
 <p class="streaming"> Formato <br>
 	STREAMING <br>
 	  6,50&euro;
 </p>
 
  <button id="bott1" onClick="passa2()">Aggiungi al Carrello</button>
  
 <p class="cd"> Formato <br>
 	   CD    <br>
 	  17,50&euro;
 </p>

 <p class="vinile"> Formato <br>
 	  VINILE    <br>
 	  25,50&euro;
 </p>
 </div>
 
<div class="Nicola img-thumbnail" style="background-image: url(./imgs/img5.png)"> 
</div>  
</div>

	<h4><hr> Brani </h4>
	<h5 class="inf"> L'Album ha una durata di 35:14 min ed &egrave; stato pubblicato il 30 Ottobre 2020. </h5>
	<p class="brani b">
    1) Aurora Boreale 3:23
    <br>
    2) Collane 3:42
    <br>
    3) Resta cu me 3:38
    <br>
    4) Sona 3:09
     <br>
    5) Collera 2:54
    <br>
    6) Zen 2:29
    <br>
    7) Ora d'aria 3:28
    <br>
    8) Rimm che succies 3:36
     <br>
    9) Stasera 2:49
    <br>
    10) Aushwitz 2:19
    <br>
    11) Ragazzi fuori 3:52
    <br>
</p>
</div>
</body>
</html>