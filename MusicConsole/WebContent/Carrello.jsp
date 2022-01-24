<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8" import="java.util.*,it.unisa.model.*"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<title>Carrello</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Sofia&effect=neon|outline|emboss|shadow-multiple">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/styleNav.css">
<link rel="stylesheet" href="css/styleHome.css">
<link rel="stylesheet" href="css/styleCarrello.css">
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

</head>
<body>

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
  <div id="cerca" class="ricerca">
 <input type="text" id="cerca" name="parola" placeholder="Ricerca"> 
  </div>
   <a href="#"> <button class="ricerca" type="submit" value="Cerca" id="cerca">Cerca</button></a>
</form>
<a href="${pageContext.request.contextPath}/ServletCarrello"><button id="carrello" onClick=""><img src="./imgs/carrello.png" height="45px" alt="Foto carrello"></button></a> 
   </nav>
 </div>
 
 <!-- 						Titolo 									-->
 
 <div class="container-fluid" >
	<div class="titolo">
	<h1 class="display-1" id="tit">Music Console</h1>
	</div>
 
  <!-- 						Carrello									-->
  
 <h2 class="font-effect-neon">Il tuo Carrello</h2>
 <div class="container">
 <table border=1 class="table">
 <thead>
 <tr>
 <th style="color:white">Descrizione</th>
 <th style="color:white">Formato</th>
 <th style="color:white">Quantità</th>
 <th style="color:white">Prezzo</th>
</tr>
 </thead>
 <tbody>
 <% 
 boolean no = false;
Collection<?> car = (Collection<?>)session.getAttribute("carrello"); //collection di carrello dell'utente in sessione
 	if(car!=null && car.size()>0){
 		Iterator<?> it = car.iterator();
 		while(it.hasNext()){
 			Carrello bean = (Carrello)it.next();
 			if((bean.getTipo().equals("CD"))||(bean.getTipo().equals("Vinile"))) {
 				request.setAttribute("sip", true);
 			}
 			if(session.getAttribute("noD")!=null){
 				String btt = String.valueOf(session.getAttribute("noD"));
 				if(btt.equals("true")){
 					no = true;
  %>
 <tr>
 <td style="color:white; text-align=center;">
 <p style="color:red">Prodotto non disponibile</p>
 <a href="ServletElimina?codprod=<%=bean.getCod()%>"><button style="font-weight:bold; background-color:transparent; color:white; font-size:1rem;">X</button></a> &nbsp;<%=bean.getNome()%>-<%=bean.getAutore() %></td>
 <td style="color:white; text-align=center;"><%=bean.getTipo()%></td>
 <%
 session.setAttribute("noD", false);
 }else{
 %>
 <td style="color:white; text-align=center;"><a href="ServletElimina?codprod=<%=bean.getCod()%>"><button style="font-weight:bold; background-color:transparent; color:white; font-size:1rem;">X</button></a> &nbsp;<%=bean.getNome()%>-<%=bean.getAutore() %></td>
 <td style="color:white; text-align=center;"><%=bean.getTipo()%></td>
 <%
 }}
 if(bean.getQuantità()==0){
 %>
 <td style="color:white; text-align=center;">-</td>
 <%
 }else{
 %>
 <td style="color:white; text-align=center;"><%=bean.getQuantità()%></td>
 <%} %>
 <td style="color:white; text-align=center;"><%=bean.getTotq()%>&euro;</td>
 </tr>
 <%
 		}
 	}
 %>
 </tbody>
<tr>
<% 
Float tot = (Float)session.getAttribute("tot"); 

if(request.getAttribute("sip")!=null){
	tot=tot+5;
	session.setAttribute("tot", tot);
%>
<td style="color:white; text-align=center;">Spedizione:5&euro;</td>
</tr>
<%
}
%>
<tr>
<td style="color:white; text-align=center;">Importo Ordine:</td>
<td style="color:white; text-align=center;" colspan=3><%=tot%>&euro;</td>
</tr>
  </table>
</div>

 <hr>

  <p class="menu"><a class="car" href="home.jsp">Torna allo shopping</a> 
  <br> <a class="car" href="ServletStorico">Storico Ordini</a>
 <%if(car.size()>0){ %> 
  <br> <a class="car" href="ServletSvuota">Svuota il carrello</a>
<%
if(no==false){
%>
  <br> <a class="car" href="ServletCarta">Completa l'ordine</a>
<%
		}
session.setAttribute("noD", false);
}
%>
  </p>

</div>
</body>
</html>