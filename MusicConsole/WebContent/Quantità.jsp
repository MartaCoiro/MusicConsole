<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8" import="java.util.*,it.unisa.model.*, Class.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quantità</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/styleAmm.css">
<link rel="stylesheet" href="css/styleNav.css">
<link rel="stylesheet" href="css/styleListProd.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/a076d05399.js"></script> <!-- per l'icona e aprire e chiudere -->

<script>
<%
if(session.getAttribute("noP")!=null){ 
String conf = String.valueOf(session.getAttribute("noP"));
 if(conf.equals("true")){ 
 %>
  $(window).on('load',function(){ //apre il poup al caricamento della pagina
	  $('#exampleModal').modal({backdrop: 'static',keyboard: true, show:true});
	   })
<%}
session.setAttribute("noP", false);
}
%>;
</script>
</head>

<!-- 												Sfondo																	 -->

<body  background="./imgs/sfondoAmm.png">

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
      <a class="nav-link" href="${pageContext.request.contextPath}/ServletProd?tipo=magazzino">Magazzino</a>
    </li>
    <li class="nav-item active">
      <a class="nav-link" href="${pageContext.request.contextPath}/ServletMagazzino">Quantità</a>
    </li>
    
  </ul>
</nav>
</div>

<div class="container-fluid" >
	<div class="titolo">
	<h1 class="display-1" id="tit" >Music Console</h1>
	</div>
</div>

<div id="wrapper">
	<h2>Lista Prodotti</h2>
  	<ul id="qui" class="it-list" >
  	<h3>CD</h3>
<% 
 Collection<Magazzino> lprod = (Collection<Magazzino>)session.getAttribute("lprod"); 
	if((lprod!=null)&&(lprod.size()>0)){
		Iterator<?> it = lprod.iterator();
		while(it.hasNext()){
		Magazzino bean = (Magazzino)it.next();
		if(bean.getTipo().equals("CD")){
			if(bean.getQuantità()<=5){
%>
<li style="color:black; font-size:large;"><%=bean.getNome()%>-<%=bean.getAutore()%>&nbsp;&nbsp;Costo:<%=bean.getCosto()%>&euro;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="ServletQuantità?op=piu&&cod=<%=bean.getCod()%>"><button class="elimina">+</button></a>&nbsp;<p style="color:red; display:inline"><%=bean.getQuantità()%></p>&nbsp;</li>
<%}else{ %>
<li style="color:black; font-size:large;"><%=bean.getNome()%>-<%=bean.getAutore()%>&nbsp;&nbsp;Costo:<%=bean.getCosto()%>&euro;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="ServletQuantità?op=piu&&cod=<%=bean.getCod()%>"><button class="elimina">+</button></a>&nbsp;<%=bean.getQuantità()%>&nbsp;</li>
<%}
}
}
%>		
<h3>Vinili</h3>
<% Iterator<?> itt = lprod.iterator();
	while(itt.hasNext()){
		Magazzino bean = (Magazzino)itt.next();
		if(bean.getTipo().equals("Vinile")){
			if(bean.getQuantità()<=5){
%>
<li style="color:black; font-size:large;"><%=bean.getNome()%>-<%=bean.getAutore()%>&nbsp;&nbsp;Costo:<%=bean.getCosto()%>&euro;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="ServletQuantità?op=piu&&cod=<%=bean.getCod()%>"><button class="elimina">+</button></a>&nbsp;<p style="color:red; display:inline"><%=bean.getQuantità()%></p>&nbsp;</li>
<%} else{ %>
<li style="color:black; font-size:large;"><%=bean.getNome()%>-<%=bean.getAutore()%>&nbsp;&nbsp;Costo:<%=bean.getCosto()%>&euro;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="ServletQuantità?op=piu&&cod=<%=bean.getCod()%>"><button class="elimina">+</button></a>&nbsp;<%=bean.getQuantità()%>&nbsp;</li>
<% }
		}
	}
}
else{
%>
		<h3>Nessun Prodotto</h3>
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
       Non è possibile aumentare la disponibilità!
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Chiudi</button>
      </div>
    </div>
  </div>
</div> 

</body>
</html>