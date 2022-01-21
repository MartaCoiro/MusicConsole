<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8" import="java.util.*,it.unisa.model.*"%>
<meta charset="UTF-8">
<title>Ordini</title>
<meta name="viewport" content="width=device-width, initial-scale=1 shrink-to-fit=no">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/styleOrdini.css">
<link rel="stylesheet" href="css/styleNav.css">
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

<%
if(request.getAttribute("conf")!=null){ 
String conf = (String)request.getAttribute("conf");
 if(conf.equals("true")){ 
 %>
  $(window).on('load',function(){ //apre il poup al caricamento della pagina
	  $('#exampleModal').modal({backdrop: 'static',keyboard: true, show:true});
	   })
<%}
request.setAttribute("conf", "false");
}
%>;
</script>

<%
Collection<?> lordini = (Collection<?>)session.getAttribute("lordini");
%>

</head>
<!-- 												Sfondo																	 -->

<body background="./imgs/sfondoAmm.png">

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
      <a class="nav-link" href="${pageContext.request.contextPath}/ServletOrdini">Ordini</a>
    </li>
</ul>
</nav>
</div>
  
<div class="container-fluid" >
	<div class="titolo">
	<h1 class="display-1" id="tit" >Music Console</h1>
	</div>
</div>

<h2>Lista ordini</h2>

<div>
<ul id="qui" class="it-list" >

<%
int in=0, i=0;
ArrayList<String> lprod = new ArrayList<String>();
if(lordini != null && lordini.size()>0){
	Iterator<?> it = lordini.iterator();
	while(it.hasNext()){
		Ordini bean = (Ordini)it.next();
		
	if(bean.getIndice()!=in){
		ArrayList<Ordini> lprod1;
		in=bean.getIndice();
		lprod = new ArrayList<String>();
		i=0;
%>
<div class="contenitore">
		<li style="margin-left:40%; margin-top:5%; font-size:xx-large; font-family:maiandra gd;"><%=bean.getIndice()%></li>
		<div class="sez">
			Utente: <%=bean.getUtente()%><br>
			Prezzo Totale: <%=bean.getTot()%>&euro;<br>
			Data: <%=bean.getData()%><br>
			

<%
Iterator<?> iii = lordini.iterator();
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
<%			
if(bean.getStato().equals("true")){			
%>
			<h5 id="sped" style=color:#f2171d>Ordine spedito</h5>
<%
			}else{
%>
			<a href="ServletPreparaOrdine?indice=<%=bean.getIndice()%>&numProd=<%=i%>"><button id="bb">Emetti ordine</button></a>
<%} %>			
			<div class="dropdown">
				<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"></button>
				<ul class="dropdown-menu ap dropbtn" id="myDropdown" aria-labelledby="dropdownMenu1">
<% 
					Iterator<?> ii = lordini.iterator();
					while(ii.hasNext()){
					Ordini beann = (Ordini)ii.next();
							if(beann.getIndice()==in){
								Float pre = beann.getCosto();
								String s = String.valueOf(pre);
								lprod.add(beann.getAutore());
								lprod.add(beann.getNome());
								lprod.add(s);
								lprod.add(beann.getTipo());
								i++;
							}
	}
   				if(lprod.size()>0){
 				for(int j=0;j<lprod.size();j=j+4){
%>
				<li style="color:black"><%=lprod.get(j)%>, <%=lprod.get(j+1)%>, <%=lprod.get(j+2)%>&euro;, <%=lprod.get(j+3)%></li>
 <%
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
<h3>Nessun Ordine</h3>
<%
	}
%>
</ul>
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
        L'ordine è stato spedito con successo!
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Chiudi</button>
      </div>
    </div>
  </div>
</div> 

</body>
</html>