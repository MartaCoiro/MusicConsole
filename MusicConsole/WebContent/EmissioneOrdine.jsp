<!DOCTYPE html>
<html lang="it">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,it.unisa.model.*"%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script> <!-- per l'icona e aprire e chiudere -->
<link rel="stylesheet" href="css/styleNav.css">
<link rel="stylesheet" href="css/styleEmisOrdine.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="css/styleEdit.css">

<title>Emissione Ordine</title>

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

function confronta_data(data1, data2){
	//trasformo le date nel formato aaaammgg (es. 20081103)
		annoC = data1.substr(0,4);
		anno = data2.substr(0,4);
        data1str = data1.substr(5,2)+data1.substr(8,2)+data1.substr(3, 1)+data1.substr(0, 2);
        data2str = data2.substr(5,2)+data2.substr(8,2)+data2.substr(3, 1)+data2.substr(0, 2);
		//controllo se la seconda data è successiva alla prima
        if ((data2str-data1str<=0)||(anno<annoC)) {
            alert("Questa data non è disponibile");
            return false;
        }else{
        	return true;
        }
 }

function validate(obj){
	let today = new Date().toISOString().slice(0, 10);
	var data = document.form1.expmonth.value;
	console.log(data);
	var valid = true;
	 if(!confronta_data(today,data)){
		 valid=false;
	 }
	 if(valid){
			document.getElementById("fo").submit();
			}
}
</script>
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
 
 <!-- 											Titolo																		 -->
 
<div class="container-fluid" >
	<div class="titolo">
	<h1 class="display-1" id="tit" >Music Console</h1>
	</div>
</div>

<!-- 											Emissione Ordine 																-->

<h2>Emissione Ordine</h2>
<%
int indice = (int)session.getAttribute("indice");
String utente = (String)request.getAttribute("utente");
float tot = (float)request.getAttribute("tot");
int numProd = (int)request.getAttribute("numProd");
ArrayList<String> lprocar = (ArrayList<String>)request.getAttribute("lprocar");
%>

<div class="container rounded bg-white mt-5">
     <div class="row">
          <div class="col-md-8">
             <div class="p-3 py-5">
            <form action="ServletAggStato" method="POST" name="form1" id="fo" onsubmit="event.preventDefault(); validate(document.form1);">
            	
            	<div class="d-flex justify-content-between align-items-center mb-3">
                    <h6 class="text-right">Emissione Ordine</h6>
                </div>
            	
            	<div class="info">
            	Numero Ordine: <%=indice%><br>
            	Utente: <%=utente%><br>
            	Prezzo Totale: <%=tot%>&euro;<br>
				Numero Prodotti: <%=numProd%><br>
				<ul class="it-list">
				<%
				if(lprocar.size()>0){
	 				for(int j=0;j<lprocar.size();j=j+5){
	 					if((lprocar.get(j+4).equals("-"))||(lprocar.get(j+4).equals("1"))){
				%>
				<li style="color:black"><%=lprocar.get(j)%>, <%=lprocar.get(j+1)%>, <%=lprocar.get(j+2)%>&euro;, <%=lprocar.get(j+3)%>, Quantità: <%=lprocar.get(j+4) %></li>
<%}else{ 
	int q = Integer.parseInt(lprocar.get(j+4));
	float p = Float.parseFloat(lprocar.get(j+2));
%>				
				<li style="color:black"><%=lprocar.get(j)%>, <%=lprocar.get(j+1)%>, <%=lprocar.get(j+2)%>&euro;, <%=lprocar.get(j+3)%>, Quantità: <%=lprocar.get(j+4)%>, Tot: <%=q*p%>&euro;</li>
<%
}
	}
}
%>
				</ul>
            	</div>
            
                <div class="row mt-2">
                
                    <div class="col-md-6">Data Spedizione: <input id="expmonth" name="expmonth" type="date"  placeholder="Data"></div>
                   
                
               
               <div id="dd" class="col-md-6">Corriere:<select name="corriere" class="form-control">
							<option value="bartolini">Bartolini</option>
							<option value="SDA">SDA Express Courier</option>
							<option value="GLS">GLS</option>
					</select><br></div>
                </div>
                <div class="mt-5 text-right"><input type="submit" class="btn btn-primary profile-button" value="Spedisci Ordine"></div>
            	
          </form>
            	</div>
            </div>
        </div>
    </div>

</body>
</html>