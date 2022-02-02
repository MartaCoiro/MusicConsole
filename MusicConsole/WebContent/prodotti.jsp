<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8" import="java.util.*,it.unisa.model.*, Class.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Prodotti</title>
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
</head>
<body>

<%
if(request.getAttribute("buon")!=null){
%>
 <div class="alert alert-success alert-dismissible alert-danger">
   <button type="button" class="close" data-dismiss="alert">&times;</button>
	<strong>Attenzione!</strong> Il prodotto è stato aggiornato con successo!
	</div>
<%
} 
%>
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
      <a class="nav-link" href="${pageContext.request.contextPath}/ServletProd?tipo=catalogo">Prodotti</a>
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
  	<h3>Album</h3>
<%
ArrayList<String> lprod = (ArrayList<String>)session.getAttribute("lprod"); 
if(lprod!=null){
	if(lprod.size()!=0){
		 for(int i=0;i<lprod.size();i=i+3){
			 if(lprod.get(i+2).toLowerCase().equals("album")){
%>			 
<li style="color:black; font-size:large;"><%=lprod.get(i)%>-<%=lprod.get(i+1)%>&nbsp;&nbsp;Tipo:<%=lprod.get(i+2)%>&nbsp;&nbsp;<a href="ServletPass?nome=<%=lprod.get(i)%>&autore=<%=lprod.get(i+1)%>&tipo=<%=lprod.get(i+2)%>"><button class="elimina" type="submit">Modifica</button></a></li>
<% 	
		 }
		}
%>
<h3>Brani</h3>
<% 
		 	for(int i=0;i<lprod.size();i=i+3){
			  if(lprod.get(i+2).toLowerCase().equals("brano")){
%>

<li style="color:black; font-size:large;"><%=lprod.get(i)%>-<%=lprod.get(i+1)%>&nbsp;&nbsp;Tipo:<%=lprod.get(i+2) %>&nbsp;&nbsp;<a href="ServletPass?nome=<%=lprod.get(i)%>&autore=<%=lprod.get(i+1)%>&tipo=<%=lprod.get(i+2)%>"><button class="elimina" type="submit">Modifica</button></a></li>
<% 
	}
}
%>
<h3>Podcast</h3>
<% 
			for(int i=0;i<lprod.size();i=i+3){
			  if(lprod.get(i+2).toLowerCase().equals("podcast")){
%>

<li style="color:black; font-size:large;"><%=lprod.get(i)%>-<%=lprod.get(i+1)%>&nbsp;&nbsp;Tipo:<%=lprod.get(i+2) %>&nbsp;&nbsp;<a href="ServletPass?nome=<%=lprod.get(i)%>&autore=<%=lprod.get(i+1)%>&tipo=<%=lprod.get(i+2)%>"><button class="elimina" type="submit">Modifica</button></a></li>
<% 
			 }	
		}
	}
	else{
%>
		<h3>Nessun Prodotto</h3>
<% 
	}
}
%>

</ul>
</div>


</body>
</html>