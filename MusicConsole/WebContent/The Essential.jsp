<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
<title>The Essential</title>
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
	$.getJSON("ServletInf?prezzo=3.15&autore=Will%20Media%20e%20Mia%20Ceran&nome=The%20Essential&tipo=Streaming",function(result){
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
 <input type="text" id="cerca" name="parola"  placeholder="Ricerca"> 
  </div>
   <a href="#"> <button type="submit" value="Cerca" id="cerca">Cerca</button></a>
</form>
<a href="${pageContext.request.contextPath}/ServletCarrello"><button id="carrello" onclick=""><img src="./imgs/carrello.png" height="45px" alt="Foto carrello"></button></a> 

   </nav>
 </div>
 
<!-- 						Titolo 									-->
 
 <div class="container-fluid" >
	<div class="titolo">
	<h1 class="display-1" id="tit" >Music Console</h1>
</div>
 
  <!-- 						Podcast									-->

 <h2 id="testo">The Essential - Will Media, Mia Ceran-</h2>
 <hr> 
  <div class="riempimento1">
 <div class="costo" >
 <h3> Acquista a : 3,15 &euro;</h3>
 <h4>  invece di 5,00 &euro;</h4>
 <button id="bott" onClick="passa()">Aggiungi al Carrello</button>
 </div>
 
 <div class="TheEssential img-thumbnail" style="background-image: url(./imgs/img11.png)"> 
</div>  
</div>
	
<h5 class="inf"> <hr> Descrizione</h5>	
 
 <p class="descrizione">The Essential &egrave; il podcast quotidiano di Will. 
 In un mondo in cui spazio e tempo <br> sono stati stravolti e ne abbiamo sempre
  meno di entrambi, esploriamo il mondo un podcast di 5 minuti. <br> Notizie 
  scelte e raccontate ogni mattina da Mia Ceran. The Essential mira a fornire l'essenziale 
  del mondo <br> delle notizie, condensato in pochi minuti. Per gli studenti che non hanno 
  tempo da perdere e per i giovani <br> professionisti che vogliono arricchire la 
  loro giornata con il tempo di un commuting o di un caff&egrave;.
 </p>
 
 <h6><hr> Episodi </h6>
 
 <p class="episodi">
    1) La situazione in India. Lo sciopero della fame di un genitore francese in Giappone. [7 Ago-7 min]
    <br>
    2) Parole d'odio online. Vendite d'armi globali. [6 Ago-6 min]
    <br>
    3) Il controverso party di Obama. Greenpass dal mondo. [5 Ago-6 min]
    <br>
    4) Gli atleti e i loro messaggi politici alle Olimpiadi. I videogiochi in Cina. [4 Ago-6 min]
    <br>
    5) 3 storie di valori dai Giochi di Tokyo. [4 Ago-6 min]
    <br>
    6) Gli accordi Italia-libia. La riforma della giustizia. [3 Ago-5 min]
    <br>
    7) La sfida dell'istruzione e Covid. Una scoperta contro la scarsit&agrave; di organi per trapianit. [3 Ago-6 min]
    <br>
    8) Come sconfiggere i produttori di armi. Una nuova leadership in Libano. [3 Ago-6 min]
    <br>
    9) Le parole di Simone Biles. Primmo processo con la National Security law di Hong Kong. [2 Ago-6 min]
    <br>
    10) I no vax russi. Un mondo senza fumo. [1 Ago-6 min]
 </p>

</div>
</body>
</html>