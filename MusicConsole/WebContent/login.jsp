<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/styleLogin.css">

<title>Login </title>

</head>
<body background="./imgs/sfondoLogin.png">

<% if(request.getAttribute("presente")!=null){ 
	  boolean p=(boolean)request.getAttribute("presente"); %>
   <div class="alert alert-danger alert-dismissible fade show">
   <button type="button" class="close" data-dismiss="alert">&times;</button>
	<strong>Attenzione!</strong> Username o password errati.
	</div>
<%}%>
   	
<div class="container-fluid" >
	<div class="titolo">
	<h1 class="display-1" id="tit" >Music Console</h1>
	</div>
</div>

 <h1 id="accedi" > Login </h1>
 
 <div class="riempimento3">
 
<div class="campi"> 
<form action="select" method="POST">
 		<fieldset>
	<label><b> Username:</b>  <input class="rett" type="text" name="nickname" placeholder="k.buonocore"autofocus></label><br>
   <br>
   <label><b>Password:</b> <input class="rett" type="password" name="password" maxlength="8" placeholder="max 8 chars"></label><br>
   <br>
   <p>
  <input class="bottoni" type="submit" value="Accedi"></p></fieldset></form><a href="registrazione.jsp"><input id="reg" class="bottoni" type="submit" value="Registrati"></a>
   <br>
   <br>
   
  <a href="amministratore.jsp"><input id="bott" type="reset" value="Accedi come Amministratore"></a>
   
   
</div>	
</div>

</body>
</html>