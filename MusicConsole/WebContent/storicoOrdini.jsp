<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8" import="java.util.*,it.unisa.model.*, Class.*"%>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<title>Storico Ordini</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Sofia&effect=neon|outline|emboss|shadow-multiple">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/styleNav.css">
<link rel="stylesheet" href="css/styleHome.css">
<link rel="stylesheet" href="css/styleStorico.css">
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
<%
Collection<Ordini> ord = (Collection<Ordini>)session.getAttribute("ord");
%>
</head>
<body>
<!-- 												Sfondo																	 -->

<body background="./imgs/sfondo6.jpg">

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
	<h1 class="display-1" id="tit" >Music Console</h1>
	</div>
</div>

<h2 class="font-effect-neon t">Lista ordini</h2>

<div>
<ul id="qui" class="it-list" >
<%
int in=0, i=0;
ArrayList<String> lprod = new ArrayList<String>();
if(ord != null && ord.size()>0){
	Iterator<?> it = ord.iterator();
	while(it.hasNext()){
		Ordini bean = (Ordini)it.next();
		
	if(bean.getIndice()!=in){
		ArrayList<Ordini> lprod1;
		in=bean.getIndice();
		lprod = new ArrayList<String>();
		i=0;
%>
<div class="cont">
		<li style="margin-left:40%; color:white; margin-top:5%; font-size:xx-large; font-family:maiandra gd;"></li>
		<div class="sez">
			Utente: <%=bean.getUtente()%><br>
			Prezzo Totale: <%=bean.getTot()%>&euro;<br>
			Data: <%=bean.getData()%><br>
			

<%
Iterator<?> iii = ord.iterator();
while(iii.hasNext()){
Ordini beann = (Ordini)iii.next();
		if(beann.getIndice()==in){
			if((beann.getQuantità()==0)||(beann.getQuantità()==1)){
				i++;
			}
			if(beann.getQuantità()>1){
				i=i+beann.getQuantità();
			}
	}
}
%>
			Numero Prodotti: <%=i%><br>
	
			<div class="dropdown">
				<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"></button>
				<ul class="dropdown-menu ap dropbtn" id="myDropdown" aria-labelledby="dropdownMenu1">
<% 
					Iterator<?> ii = ord.iterator();
					while(ii.hasNext()){
					Ordini beann = (Ordini)ii.next();
							if(beann.getIndice()==in){
								Float pre = beann.getCosto();
								String s = String.valueOf(pre);
								lprod.add(beann.getAutore());
								lprod.add(beann.getNome());
								lprod.add(s);
								lprod.add(beann.getTipo());
								int q = beann.getQuantità();
								String qu = String.valueOf(q);
								lprod.add(qu);
								i++;
							}
	}
   				if(lprod.size()>0){
 				for(int j=0;j<lprod.size();j=j+5){
 					if(lprod.get(j+3).equals("Streaming")){
%>
				<li style="color:black; text-align:left"><%=lprod.get(j)%>, <%=lprod.get(j+1)%>, <%=lprod.get(j+2)%>&euro;, <%=lprod.get(j+3)%>, Quantità: - </li>
<%}else{ %>
				<li style="color:black; text-align:left"><%=lprod.get(j)%>, <%=lprod.get(j+1)%>, <%=lprod.get(j+2)%>&euro;, <%=lprod.get(j+3)%>, Quantità: <%=lprod.get(j+4) %> </li>
 <%
}
 }
   				}
	}
 %>
   </ul>
 </div>
 </div>
 </div> 		

<% 
		}
	} else{
%>
<h3 style="color:white">Nessun Ordine</h3>
<%
	}
%>
</ul>
</div>
</body>
</html>