<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"  import="java.util.*,it.unisa.model.*"%>
 
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<link rel="stylesheet" href="css/styleLogin.css">
<link rel="stylesheet" href="css/styleReg.css">
 
<script>
  <% if(request.getAttribute("presente")!=null){ 
	  boolean p=(boolean)request.getAttribute("presente"); %>
  $(window).on('load',function(){ //apre il poup al caricamento della pagina
	  $('#exampleModal').modal({backdrop: 'static',keyboard: true, show:true});
	   })<%}%>; 
</script>

<script>
function allLetter(inputtxt) { 
    var letters = /^[A-Za-z]+$/;
    if(inputtxt.value.match(letters)) {
    return true;
    }else{
   alert('Il nome può contenere solo caratteri');
   return false;
    }
}

function checkNamesurname(inputtxt){
	var name = /^[A-Za-z]+$/;
	if(inputtxt.value.match(name)){
		 return true;
	}else{
	alert('Il cognome può contenere solo caratteri');
		return false;
	}
}

function checkEmail(inputtxt){
	 var email = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	 if(inputtxt.value.match(email)){
		 return true;
	 }else{
		 alert('Email non corretta');
		 return false;
	 }
}

function checkPhonenumber(inputtxt){
	var phoneno =/^([0-9]{10})$/;
	 if(inputtxt.value.match(phoneno)){
		 return true;
	 }else{
		 alert('Numero di telefono non corretto');
		 return false; 
	 }
}

function validate(obj){
	var uid = document.form1.nome;
	var uname = document.form1.cognome;
	var uemail = document.form1.email;
	var tel = document.form1.telefono;
	 var valid = true;
	 
	 /*var name = document.getElementsByName("nome");*/
	 if(!allLetter(uid)){
		 valid=false;
		}
	 
	/* var surname = document.getElementsByName("cognome");*/
	 if(!checkNamesurname(uname)){
		  valid=false;
		}
	 
	 if(!checkEmail(uemail)){
		 valid=false;
	 }
	 
	 if(!checkPhonenumber(tel)){
		 valid=false;
	 }
	 
	if(valid){
	document.getElementById("fo").submit();
	}
}
	
</script>

<title>Registrazione </title>	
</head>

<body background="./imgs/sfondoLogin.png" style="overflow:hidden" >

<div class="container-fluid" >
	<div class="titolo">
	<h1 class="display-1" id="tit" >Music Console</h1>
	</div>
</div>

<h1 id="accedi" > Registrazione </h1>
<div class="campi"> 

	<form name="form1" id="fo" action="ServletReg" method="POST"  onsubmit="event.preventDefault(); validate(document.form1);  ">
<fieldset>
   <label for="nom"><b>Nome:</b> <input class="rett" type="text" name="nome" placeholder="Katia" required autofocus></label><br>
   <label for="cognome"><b> Cognome:</b> <input class="rett" type="text" name="cognome" placeholder="Buonocore" required autofocus></label><br>
   <label for="citta'"><b> Citta':</b> <input class="rett" type="text" name="citta'" placeholder="Angri" required></label> <br>
   <label for="indirizzo"><b> Indirizzo:</b> <input class="rett" type="text" name="indirizzo" placeholder="via... n.." required></label> <br>
   <label for="telefono"><b> Telefono:</b> <input class="rett" type="tel" name="telefono" placeholder="123456789" required></label> <br>
   <label for="email"><b> Email:</b> <input class="rett" type="text" name="email" placeholder="nomecognome@.com" required ></label> <br>
   <label for="nickname"><b> Username:</b> <input class="rett" type="text" name="nickname" placeholder="k.buonocore" required ></label> <br>
   <label for="password"><b> Password:</b> <input class="rett" type="password" name="password" maxlength="8" placeholder="max 8 chars" required ></label>
   
   <p>
  <input class="bottoni" type="submit" data-target="#exampleModal" value="Registrati">
   </p>
  
 </fieldset>
 </form>
 
<!-- Modal -->

 <div class="modal fade" id="exampleModal" data-toggle="modal" tabindex="-1" role="dialog" aria-hidden="true" aria-labelledby="exampleModalLabel" >
  <div class="modal-dialog" role="document">
    <div class="modal-content"> 
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">ERRORE!</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
<span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        L'username inserito è già in uso
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Chiudi</button>
      </div>
    </div>
  </div>
</div> 

</div>
</body>
</html>




