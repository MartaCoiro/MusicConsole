<!DOCTYPE html>
<html lang="it">
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8" import="java.util.*,gestioneAccount.*, gestioneAcquisti.*, gestioneCarrello.*, gestioneProdotti.*"%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Sofia&effect=neon|outline|emboss|shadow-multiple">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/a076d05399.js"></script> <!-- per l'icona e aprire e chiudere -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> <!-- per la nav fissa -->
<!--  link rel="stylesheet" href="css/styleHome.css">-->
<!--  link rel="stylesheet" href="css/styleNav.css">-->
<link rel="stylesheet" href="css/styleListBrani.css">
<link rel="stylesheet" href="css/styleListPlaylist.css"> 

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
 
<title>Playlist</title>
</head>

<body background=./imgs/sfondo6.jpg onload="">

<!--								Barra di navigazione							-->

<div>
<nav class="navbar navbar-expand-sm bg-dark justify-content-center navbar-dark" >

 
<input type="checkbox" id="check">
  <label for="check" class="checkbtn">
   	<i class="fas fa-bars" id="btn"></i>
   	<i class="fas fa-times" id="cancel"></i>
  </label>
 
   <ul class="menu" id="nav" >
    <li class="nav-item active">
 <a class="nav-link" style="font-size:1.1rem; padding: 7px 13px;" text-transform: uppercase;" href="${pageContext.request.contextPath}/ServletLogout">Logout</a>
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
	<a href="${pageContext.request.contextPath}/ServletCarrello"><button id="carrello"><img src="./imgs/carrello.png" height="45px" alt="Foto carrello"></button></a>
   </nav>
    
 </div>
 
 <!--											Titolo 								-->
  
  <div class="container-fluid" >
	<div class="titolo" style="height:9rem">
	<h1 class="display-1" id="tit" style="font-size: 7.5rem; margin-top:3%">Music Console</h1>
	</div>

<!-- 									Playlist 									-->

<%String nn = (String)session.getAttribute("nn"); %>
<div id="wrapper">
	 			<h2 class="playlist" style="left:-2%; position:relative; line-height:3"><%=nn%></h2>
  				<ul id="qui" class="it-list" style="margin-top:0%;">
<% 		
ArrayList<String> namep = (ArrayList<String>)session.getAttribute("namep");
for(int i=0;i<namep.size();i++){
	if(namep.get(i).equals(nn)){
		ArrayList<String> blist = (ArrayList<String>)session.getAttribute("blist1"+i);
		 if(blist!=null){
			 if(blist.size()!=2){
				 for(int j=2;j<=blist.size()-2;j=j+2){
				if(blist.get(j)!=" "){
					
%>
<li style="color:white; left:-20%; position:relative; line-height:2; font-size:2rem"><%=blist.get(j)%>-<%=blist.get(j+1)%><a style="text-decoration:none;" href="ServletRimuovi?nomeb=<%=blist.get(j)%>&nomea=<%=blist.get(j+1)%>&nomep=<%=nn%>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<botton style="font-weight:bold; color:white; font-size=1.2rem;">X</botton></a></li>




<%
 										}
				 					}
								 }
 else{
%>

			 <h4 class="testo1" style="left:-12%; position:relative; line-height:3; font-size:2rem">La playlist è vuota</h4>
<% 
							}
						}
		 		}
		}

%>


    					
</ul>
</div>

		
	
	

</div>
</body>
</html>

    

