<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, it.unisa.model.*"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Sofia&effect=neon|outline|emboss|shadow-multiple">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Sofia&effect=neon|outline|emboss|shadow-multiple">
<script src="https://kit.fontawesome.com/a076d05399.js"></script> <!-- per l'icona e aprire e chiudere -->
<link rel="stylesheet" href="css/styleNav.css">
<link rel="stylesheet" href="css/styleHome.css">
<link rel="stylesheet" href="css/styleCheckout.css">
<!--  script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> <!-- per la nav fissa -->

<title>Checkout</title>
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
function chekNome(inputtxt) { 
    var letters = /^[A-Za-z]+$/;
    if(inputtxt.value.match(letters)) {
    return true;
    }else{
   alert('Il nome della carta può contenere solo caratteri');
   return false;
    }
}

function chekCognome(inputtxt) { 
    var letters = /^[A-Za-z]+$/;
    if(inputtxt.value.match(letters)) {
    return true;
    }else{
   alert('Il cognome della carta può contenere solo caratteri');
   return false;
    }
}

function checknumbercard(inputtxt){
	 var numbc = /^([0-9]{4})-([0-9]{4})-([0-9]{4})-([0-9]{4})$/;
	 if(inputtxt.value.match(numbc)){
		 return true;
	 }else{
		 alert('Numero della carta non corretto');
		 return false;
	 }
}

function checkData(data1, data2){
	console.log(data1);
	console.log(data2);
		annoC = data1.substr(0,4);
		anno = data2.substr(0,4);
	    data1str = data1.substr(5,2)+data1.substr(3, 1)+data1.substr(0, 2);
	     data2str = data2.substr(5,2)+data2.substr(3, 1)+data2.substr(0, 2);
			//controllo se la seconda data è successiva alla prima
	        if ((data2str-data1str<=0)||(anno<annoC)) {
	            alert("Questa data non è disponibile");
	            return false;
	        }else{
	        	return true;
	        }
}
	
function checkcod(inputtxt){
	var cod =/^([0-9]{3})$/;
	 if(inputtxt.value.match(cod)){
		 return true;
	 }else{
		 alert('Codice non corretto');
		 return false; 
	 }
}

function validate(obj){
	let today = new Date().toISOString().slice(0, 10);
	var nomecar = document.form1.cardname;
	var cogncar = document.form1.cardsurname;
	var numcard = document.form1.cardnumber;
	var data = document.form1.expmonth.value;
	var cod = document.form1.cvv;
	 var valid = true;
	 
	 if(!chekNome(nomecar)){
		 valid=false;
		}
	 
	 if(!chekCognome(cogncar)){
		 valid=false;
		}
	 
	 if(!checknumbercard(numcard)){
		 valid=false;
	 }
	 
	 if(!checkData(today,data)){
		 valid=false;
	 }
	 
	 if(!checkcod(cod)){
		 valid=false;
	 }
	 
	if(valid){
	document.getElementById("fo").submit();
	}
}
	
<%
if(request.getAttribute("error")!=null){
%>
 	$(window).on('load',function(){ //apre il poup al caricamento della pagina
	  $('#exampleModal').modal({backdrop: 'static',keyboard: true, show:true});
	   })
<%
request.setAttribute("error", false);
}
%>
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
      <a class="nav-link" href="ServletLogout">Logout</a>
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
  <!-- 									Barra di Ricerca													 -->
<form action="ServletCerca" name="ricerca" class="navbar-form navbar-left" role="search">
  <div id="cerca">
 <input type="text" id="cerca" name="parola" placeholder="Ricerca"> 
  </div>
   <a href="#"> <button type="submit" value="Cerca" id="cerca">Cerca</button></a>
</form>
	<a href="${pageContext.request.contextPath}/ServletCarrello"><button id="carrello"><img src="./imgs/carrello.png" height="45px" alt="Foto carrello"></button></a>
   </nav>
 </div>
<!-- 											Titolo																		 -->
	<div class="container-fluid" >
	<div class="titolo">
	<h1 class="display-1" id="tit">Music Console</h1>
	</div> 
	<!-- 										Riepilogo 													-->
<%
Carta card = (Carta)session.getAttribute("cart");
Profilo p = (Profilo)session.getAttribute("p");

if(p!=null){
%>
<form method="POST" id="fo" name="form1" action="ServletSped" onsubmit="event.preventDefault(); validate(document.form1);" >
          
<div class="col-50">
<h3 class="font-effect-neon">Indirizzo Fatturazione e Spedizione</h3>
<label for="fname"><i class="fa fa-user"></i> Nome e Cognome</label>
<input value="<%=p.getNome()%>&nbsp;<%=p.getCognome() %>" type="text" class="ck" id="fname" name="firstname" placeholder="Katia Buonocore"><br>
<label for="email"><i class="fa fa-envelope"></i> Email</label>
<input value="<%=p.getEmail()%>" type="text" class="ck" id="email" name="email" placeholder="rita@example.com"><br>
<label for="adr"><i class="fa fas fa-address-card"></i> Via e numero civico</label>
<input value="<%=p.getIndirizzo()%>" type="text" class="ck" id="adr" name="address" placeholder="Via Roma 13"><br>
<label for="city"><i class="fa fa-institution"></i> Città</label>
<input value="<%=p.getCitta()%>" type="text" class="ck" id="city" name="city" placeholder="Nocera Inferiore"><br>
</div>
<%} 
/*if(session.getAttribute("posCard")!=null){*/
boolean posCard = (boolean)session.getAttribute("posCard");
if(posCard==true){
%>            
<div class="col-40">
<h3 class="font-effect-neon">Pagamento</h3>
<label for="cardname">Nome presente sulla carta</label>
<input value=<%=card.getNome()%> type="text" class="ck" id="nname" name="cardname" placeholder="Nome" required autofocus><br>
<label for="cardsurname">Cognome presente sulla carta</label>
<input value=<%=card.getCognome()%> type="text" class="ck" id="cname" name="cardsurname" placeholder="Cognome" required><br>
<label for="ccnum">Numero carta di credito</label>
<input value=<%=card.getNumero()%> type="text" class="ck" id="ccnum" name="cardnumber" placeholder="****-****-****-****" required><br>
<label for="expmonth">Mese e anno di scadenza</label>
<input value=<%=card.getDataa()%> type="month" class="ck" id="expmonth" name="expmonth" placeholder="Settembre" required><br>
<label for="cvv">CVV</label>
<input value=<%=card.getCvv()%> type="text" class="ck" id="cvv" name="cvv" placeholder="352" required><br> 
<p style="color:white; font-size:20px;"><input type="checkbox" name="conserva" value="Memorizza Carta"/>Memorizza Carta</p>
<%
}else{ 
%>
<div class="col-40">
<h3 class="font-effect-neon">Pagamento</h3>
<label for="cardname">Nome presente sulla carta</label>
<input type="text" class="ck" id="nname" name="cardname" placeholder="Nome" required autofocus><br>
<label for="cardsurname">Cognome presente sulla carta</label>
<input type="text" class="ck" id="cname" name="cardsurname" placeholder="Cognome" required><br>
<label for="ccnum">Numero carta di credito</label>
<input type="text" class="ck" id="ccnum" name="cardnumber" placeholder="****-****-****-****" required><br>
<label for="expmonth">Mese e anno di scadenza</label>
<input type="month" class="ck" id="expmonth" name="expmonth" placeholder="Settembre" required><br>
<label for="cvv">CVV</label>
<input type="text" class="ck" id="cvv" name="cvv" placeholder="352" required><br> 
<p style="color:white; font-size:20px;"><input type="checkbox" name="conserva" value="Memorizza Carta"/>Memorizza Carta</p>
<%
}%>
<div class="pay">
<input type="submit" value="Concludi l'ordine" class="bt">
</div>

</div>
</form>
</div>

<!-- Modal -->
 <div class="modal fade" id="exampleModal" data-toggle="modal" tabindex="-1" role="dialog" aria-hidden="true" aria-labelledby="exampleModalLabel" >
  <div class="modal-dialog" role="document">
    <div class="modal-content"> 
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel" style="color:black">ATTENZIONE!</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
<span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Codice carta non corretto!
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Chiudi</button>
      </div>
    </div>
  </div>
</div> 

</body>
</html>