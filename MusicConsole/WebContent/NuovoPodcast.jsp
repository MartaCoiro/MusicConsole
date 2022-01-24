<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nuovo Podcast</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/styleAmm.css">
<link rel="stylesheet" href="css/styleNav.css">
<link rel="stylesheet" href="css/styleNuovoProd.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/a076d05399.js"></script> <!-- per l'icona e aprire e chiudere -->

<script>
function checkPrezzo(inputtxt){
	var phoneno = /^([0-9])+.([0-9]{1,2})$/;
	 if(inputtxt.value.match(phoneno)){
		 return true;
	 }else{
		 alert('prezzo non corretto');
		 return false; 
	 }
}
function validate(obj){
	var cod = document.form1.prezzo;
	 var valid = true;
	 
	 if(!checkPrezzo(cod)){
		 valid=false;
	 }
	 if(valid){
			document.getElementById("fo").submit();
			}
} 
</script>

</head>
<!-- 												Sfondo																	 -->
<body  background="./imgs/sfondoAmm.png" style="overflow:auto !important">

<%
if(request.getAttribute("err")!=null){
%>
	<div class="alert alert-danger alert-dismissible fade show">
	   <button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong>Attenzione!</strong> Esiste già un prodotto con questo nome.
		</div>
<% 
}
%>

<%
if(request.getAttribute("sb")!=null){
%>
	<div class="alert alert-danger alert-dismissible fade show">
	   <button type="button" class="close" data-dismiss="alert">&times;</button>
		<strong>Attenzione!</strong> Il "tipo" inserito non è corretto.
		</div>
<% 
}
%>

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
      <a class="nav-link" href="${pageContext.request.contextPath}/ServletUtenti">Utenti</a>
    </li>
    <li class="nav-item active">
      <a class="nav-link" href="${pageContext.request.contextPath}/ServletProd">Prodotti</a>
    </li>
    
</ul>
</nav>
</div>
  
<div class="container-fluid" >
	<div class="titolo">
	<h1 class="display-1" id="tit" >Music Console</h1>
	</div>
</div>

<h2 class="p">Nuovo Podcast:</h2>
<div class="new">
<form action="ServletAggP" method="POST"  id="fo" name="form1" enctype="multipart/form-data" onsubmit="event.preventDefault(); validate(document.form1);">


<label for="nome" class="lab">Nome:<input type="text" name="nome" required></label><br>
<label for="image" class="lab">Immagine:<input type="file" name="image" required></label><br>
<label for="artista" class="lab">Ideatore:<input type="text" name="artista" required></label><br>
<label for="tipo" class="lab">Tipo:<input type="text" name="tipo" placeholder="Podcast" required></label><br>
<label for="descrizione" class="lab">Descrizione:<textarea rows="5" cols="30" name="descrizione" id="txt" style="border:solid #f06"></textarea></label><br>
<label for="prezzo" class="lab">Prezzo:<input type="text" name="prezzo" required></label><br>

<input type="submit" value="Aggiungi prodotto">

</form>
</div>

</body>
</html>