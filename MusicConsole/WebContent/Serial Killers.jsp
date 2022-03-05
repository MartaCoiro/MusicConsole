<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
<title>Serial Killer</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/stylePodcast.css">
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
	$.getJSON("ServletInf?prezzo=5.25&autore=Percast%20Network&nome=Serial%20Killer&tipo=Streaming",function(result){
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
    </li>
    
  </ul>
  
  <form action="ServletCerca" name="ricerca" class="navbar-form navbar-left" role="search">
  <div id="cerca">
 <input type="text" id="cerca" name="parola" placeholder="Ricerca"> 
  </div>
   <a href="#"> <button type="submit" id="cerca">Cerca</button></a>
</form>
<a href="${pageContext.request.contextPath}/ServletCarrello"><button id="carrello" onClick=""><img src="./imgs/carrello.png" height="45px" alt="Foto carrello"></button></a> 

   </nav>
 </div>
 
 <!-- 						Titolo 									-->
 
 <div class="container-fluid" >
	<div class="titolo">
	<h1 class="display-1" id="tit" >Music Console</h1>
</div>
 
   <!-- 						Podcast									-->

 <h2 id="testo">Serial Killers - Percast Network-</h2>
 <hr>
   <div class="riempimento1">
 <div  class="costo" >
 <h3> Acquista a : 5,25 &euro;</h3>
 <h4>  invece di 6,10 &euro;</h4>
 <button id="bott" onClick="passa()">Aggiungi al Carrello</button>
 </div>
 
 <div class="SerialKiller img-thumbnail" style="background-image: url(./imgs/img12.png)"> 
</div>  
</div>
	
 <h5 class="inf"> <hr> Descrizione</h5>	
 
 <p class="descrizione">Ogni luned&igrave; e gioved&igrave; , Serial Killers adotta un approccio psicologico e divertente 
 per fornire uno sguardo raro nella mente, 
 nei metodi e nella follia dei serial killer pi&ugrave; famosi con la speranza di comprendere meglio il loro profilo psicologico. 
 
 Con l'aiuto di ricerche approfondite, approfondiamo le loro vite e le loro storie. Serial Killers &egrave; un originale Spotify di Parcast.
 </p>
 
 <h6><hr> Episodi </h6>
 
 <p class="episodi">
    1) Welcome Trailer. [30 June - 2:09 min]
    <br>
    2) Lody Rotten pt1 [30 June - 30 min]
    <br>
    3) The Butcher of Hanover pt1 [29 June - 45:03 min]
    <br>
    4) The Butcher of Hanover pt2 [29 June - 43:00 min]
    <br>
    5) The Spokane Serial Killer pt 1 [28 June - 42:32 min]
    <br>
    6) The Spokane Serial Killer pt 2 [28 June - 42:32 min]
    <br>
 </p>
 </div>
</body>
</html>