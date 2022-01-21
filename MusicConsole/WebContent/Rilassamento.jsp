<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
<title>Rilassamento</title>
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
	$.getJSON("ServletInf?prezzo=5.15&autore=Gaia%20Nozza&nome=Rilassamento%20Training%20Autogeno&tipo=Streaming",function(result){
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
 
 <!-- 						Podcast									-->

 <h2 id="testo">Rilassamento Training Autogeno - Gaia Nozza</h2>
 <hr>
  <div class="riempimento1">
 <div  class="costo" >
 <h3> Acquista a : 5,15 &euro;</h3>
 <h4>  invece di 6,20 &euro;</h4>
 <button id="bott" onClick="passa()">Aggiungi al Carrello</button>
 
 
 </div>
 
 <div class="Rilassamento img-thumbnail"  style="background-image: url(./imgs/img15.png)"> 
</div>  
</div>
	
 <h5 class="inf"> <hr> Descrizione</h5>	
 
 <p class="descrizione">Alleggerisci la tua mente, diminuisci lo stress e impara a governare le tue emozioni.
 Il contenuto dei podcast &egrave; indicato per tutti coloro che si sentono appesantiti da pensieri e preoccupazioni.
 Io sono Gaia, Counselor laureata in filosofia ex ansiosa, e ho pensato a come potevo essere utile a tutti quelli che non hanno ancora cacciato la loro ansia, 
 ecco perch&egrave; &egrave; nato il podcast.Scrivimi su gaianozza.itseguimi su instagram @gazzanoia
 </p>
 
 <h6><hr> Episodi </h6>
 
 <p class="episodi">
    1)  Metamorfosi [25 June - 12 min]
	<br>
    2)  Il treno dei pensieri [8 June - 12 min]
    <br>
    3)  Oltre lo specchio [17 May - 11 min]
    <br>
    4)  L'anima in fiore [2 May - 9 min]
    <br>
   	5)	Finchè il caffè è caldo [24 April - 8 min]
   	<br>
   	6)	Si accende la luce [10 April - 8 min]
   	<br>
   	7)	Un fiore nuovo [28 March - 5 min]
   	<br>
   	8)	Dentro le nuvole [23 March - 9 min]
   	<br>
   	9)	Un caffè a Central Park [1 March - 9 min]
   	<br>
   	10)	La magia del ghiaccio [27 February - 9 min]
   	<br>
</p>
</div>
</body>
</html>