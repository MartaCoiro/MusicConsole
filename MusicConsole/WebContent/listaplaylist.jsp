<!DOCTYPE html>
<html lang="it">
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8" import="java.util.*,it.unisa.model.*"%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Sofia&effect=neon|outline|emboss|shadow-multiple">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Sofia">
<script
            src="https://code.jquery.com/jquery-3.2.1.js"
            integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
            crossorigin="anonymous">
</script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<!--  <link rel="stylesheet" href="css/styleHome.css">
<link rel="stylesheet" href="css/styleNav.css">-->
<link rel="stylesheet" href="css/styleListPlaylist.css">

<script src="https://kit.fontawesome.com/a076d05399.js"></script> <!-- per l'icona e aprire e chiudere -->
<!--  script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> <!-- per la nav fissa -->

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
        $(document).ready(function(){
            /*questo bottone apre la modale che attualmente è chiusa vede il selettore con classe"modal_open"*/
            var btn_click=$('.modal_open');

//quando clicco sul bottone farò qualcosa
            btn_click.on("click",function(){
//in questo caso apro la modale
                $('#myModal').modal('show');
//quando salvo succederà qualcosa
                $('#save').click(function(){
//nascondo la modale
                    $('#myModal').modal('hide');
                });
            });
        });
    </script>
 
<title>Playlist</title>
</head>

<body background=./imgs/sfondo6.jpg>
<% if(request.getAttribute("si")!=null){ 
	  boolean p=(boolean)request.getAttribute("si");
	  if(p==true){
%>
   <div class="alert alert-success alert-dismissible alert-danger">
   <button type="button" class="close" data-dismiss="alert">&times;</button>
	<strong>Attenzione!</strong> Esiste già una playlist con questo nome!
	</div>
<%}else{%>
	<div class="alert alert-success alert-dismissible alert-danger">
   <button type="button" class="close" data-dismiss="alert">&times;</button>
	<strong>Attenzione!</strong> Non è stato inserito nessun nome!
	</div>
<%}} %>


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
   <a href="#"> <button type="submit" value="Cerca" id="cerca" style="color:black;">Cerca</button></a>
</form>
	<a href="${pageContext.request.contextPath}/ServletCarrello"><button id="carrello"><img src="./imgs/carrello.png" height="45px" alt="Foto carrello"></button></a>
   </nav>
    
 </div>
 
 <!--											Titolo 								-->
  
  <div class="container-fluid" >
	<div class="titolo">
	<h1 class="display-1" id="tit">Music Console</h1>
	</div>

<!-- 									Playlist 									-->

<div id="wrapper">
	 <h2 class="playlist">Le tue playlist</h2>
	 			
 <div class='container'>
            <div class='row'>
                <div class='col-md-12 text-center'>
                    <a class='bottone btn-outline-primary modal_open'>Crea una nuova playlist</a>
                </div>
            </div>
 </div> 
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel">Inserisci il nome della playlist</h4>
                    </div>
                    <form action="ServletNewPlaylist" method="GET">
                    <div class="modal-body">
                        <input id='inserisci_valore' name="nome" class='form-control' value='' placeholder='inserisci un valore'>
                    </div>
                    <div class="modal-footer">
                    	<input type="submit" class="btn btn-primary" id='save' value="Salva">
                    </div>
                    </form>
                </div>
            </div>
        </div>

 <ul id="qui" class="it-list" >
    
 <% 			
 ArrayList<String> lplay = (ArrayList<String>)session.getAttribute("namep");
 if(lplay!=null){
if(lplay.size()!=0){
 for(int i=0;i<lplay.size();i++){
%>
	 <li style="color:white"><a id="tt"  style="color:white" href="ServletSend?plist=<%=lplay.get(i)%>"><%=lplay.get(i)%></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="text-decoration:none; color:white; font-size:2rem;" href="ServletRimuovip?nomep=<%=lplay.get(i)%>">X</a></li>
	
 <%
 					}
 			} 
 else {
 %>
 
 <h4 class="testo">Non è stata creata ancora nessuna playlist</h4>
 
 <%}} %>
</ul>
</div>
</div>

</body>
</html>