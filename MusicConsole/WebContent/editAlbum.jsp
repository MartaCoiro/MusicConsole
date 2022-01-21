<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"  import="java.util.*,it.unisa.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Album</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script> <!-- per l'icona e aprire e chiudere -->
<link rel="stylesheet" href="css/styleNav.css">
<link rel="stylesheet" href="css/styleAmm.css">
<link rel="stylesheet" href="css/styleProva.css">
<link rel="stylesheet" href="css/styleListProd.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script>
function showDiv(divId, divId1, divId2, divId3, divId4, divId5, divId6, element){
	document.getElementById(divId).style.display = element.value == "Brano" ? 'block' : 'none';
	document.getElementById(divId1).style.display = element.value == "Brano" ? 'block' : 'none';
	document.getElementById(divId2).style.display = element.value == "Brano" ? 'block' : 'none';
	document.getElementById(divId3).style.display = element.value == "Brano" || "Podcast" ? 'none' : 'block';
	document.getElementById(divId4).style.display = element.value == "Brano" || "Podcast" ? 'none' : 'block';
	document.getElementById(divId5).style.display = element.value == "Brano" || "Podcast" ? 'none' : 'block';
	document.getElementById(divId3).style.display = element.value == "Album" ? 'block' : 'none';
	document.getElementById(divId4).style.display = element.value == "Album" ? 'block' : 'none';
	document.getElementById(divId5).style.display = element.value == "Album" ? 'block' : 'none';
	document.getElementById(divId6).style.display = element.value == "Brano" || "Podcast" ? 'block' : 'none';
	document.getElementById(divId6).style.display = element.value == "Album" ? 'none' : 'block';
	}
</script>

<script>
function checkPrezzo(inputtxt){
	var price = /^([0-9])+.([0-9]{1,2})$/;
	var vuoto = /^$|\s+/ ;
	 if(inputtxt.value.match(price)){
		 return true;
	 }
	 else if(inputtxt.value.match(vuoto)){
		 return true;
	 }else{
		 alert('prezzo non corretto');
		 return false; 
	 }
}

function validate(obj){
	var cod = document.form1.prezzo;
	var cod1 = document.form1.prezzoS;
	var cod2 = document.form1.prezzoV;
	var cod3 = document.form1.prezzoC;
	
	 var valid = true;
	 
	 if(!checkPrezzo(cod)){
		 valid=false;
	 }
	 if(!checkPrezzo(cod1)){
		 valid=false;
	 }
	 if(!checkPrezzo(cod2)){
		 valid=false;
	 }
	 if(!checkPrezzo(cod3)){
		 valid=false;
	 }
	 
	 if(valid){
			document.getElementById("fo").submit();
			}
} 
</script>

</head>
<body>

<!-- 												Sfondo																	 -->

<body  background="./imgs/sfondoAmm.png">

<% 
if(request.getAttribute("pre")!=null){
%>
 <div class="alert alert-success alert-dismissible alert-danger">
   <button type="button" class="close" data-dismiss="alert">&times;</button>
	<strong>Attenzione!</strong> Un campo è vuoto!
	</div>
<%
} 
%>

<% 
if(request.getAttribute("ercod")!=null){
%>
 <div class="alert alert-success alert-dismissible alert-danger">
   <button type="button" class="close" data-dismiss="alert">&times;</button>
	<strong>Attenzione!</strong> Il codice inserito è già in uso!
	</div>
<%
} 
%>

<% 
if(request.getAttribute("ercod1")!=null){
%>
 <div class="alert alert-success alert-dismissible alert-danger">
   <button type="button" class="close" data-dismiss="alert">&times;</button>
	<strong>Attenzione!</strong> Il nome inserito è già in uso!
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

<%
Album alb = (Album)session.getAttribute("alb");
%>
<div class="container rounded bg-white mt-5">
    <div class="row">
        <div class="col-md-8">
            <div class="p-3 py-5">
            <form action="ServletModAlbum" method="POST" id="fo" name="form1" enctype="multipart/form-data" onsubmit="event.preventDefault(); validate(document.form1);">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h6 class="text-right">Modifica Album</h6>
                </div>
                <div class="row mt-2">
                
                    <div class="col-md-6">Nome Album:<input name="nome" type="text" class="form-control" placeholder="Nome Album" value="<%=alb.getNomeAlbum()%>"></div>
                    <div class="col-md-6">Nome Artista:<input name="artista" type="text" class="form-control" value="<%=alb.getNartista()%>" placeholder="Nome Artista"></div>
               </div>
                <div class="row mt-3">
                    <div class="col-md-6">Immagine:<%=alb.getImgAlbum()%><br><input name="immagine" type="file" class="form-control" placeholder="Immagine" value=""></div>
                    <div class="col-md-6" >Tipo:<select id="test" name="tipo" class="form-control"  onchange="showDiv('hidden_div', 'hidden_div1', 'hidden_div2', 'hidden_div4', 'hidden_div5', 'hidden_div6', 'hidden_div3', this)">
                    	<option value="Album">Album</option>
                    	<option value="Brano">Brano</option>
                    	<option value="Podcast">Podcast</option>
                    </select></div>
                    
                    <div id="hidden_div" class="col-md-6">Genere:<select name="genere" class="form-control">
							<option value="pop">Pop</option>
							<option value="indie">Indie</option>
							<option value="rap">Rap</option>
							<option value="neomelodico">Neomelodico</option>
							<option value="classico">Classico</option>
							<option value="freestyle">Freestyle</option>
							<option value="hip-hop">Hip-Hop</option>
					</select><br></div>
					
                	<div id="hidden_div1" class="col-md-6">Codice:<input id="li" name="codice" type="number" class="form-control" placeholder="Codice" value=""></div>
               		<div id="hidden_div2" class="col-md-6">Suono:<input name="suono" type="file" class="form-control" placeholder="Brano" value=""></div>
                 	 <div class="col-md-6" id="hidden_div3">Prezzo:<input type="text" name="prezzo" style="border:solid #f06" value=""></div><br>
                
                <div class="col-md-6">Descrizione:<%=alb.getDescrizione()%><textarea rows="3" cols="30" name="descrizione" style="border:solid #f06"></textarea></div><br>
				<div class="col-md-6" id="hidden_div4">Prezzo Streaming:<input type="text" name="prezzoS"  style="border:solid #f06" value="<%=alb.getPrezzoS()%>"></div><br>
				<div class="col-md-6" id="hidden_div5">Prezzo Vinile:<input type="text" name="prezzoV"  style="border:solid #f06" value="<%=alb.getPrezzoV()%>"></div><br>
				<div class="col-md-6" id="hidden_div6">Prezzo CD:<input type="text" name="prezzoC" style="border:solid #f06" value="<%=alb.getPrezzoC()%>"></div><br>
                
                </div>
                
                <div class="mt-5 text-right"><input type="submit" class="btn btn-primary profile-button" value="Salva Prodotto"></div>
            	
         

            	</form>
            </div>
        </div>
    </div>
    </div>

</body>
</html>