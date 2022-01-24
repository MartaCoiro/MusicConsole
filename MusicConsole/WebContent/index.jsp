<!DOCTYPE html>
<html lang="it">
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8" import="java.util.*,it.unisa.model.*"%>
<title>MUSICCONSOLE</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1 shrink-to-fit=no">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/styleHome.css">
<link rel="stylesheet" href="css/styleMedia.css">
<link rel="stylesheet" href="css/styleNav.css">

<script src="https://kit.fontawesome.com/a076d05399.js"></script> <!-- per l'icona e aprire e chiudere -->

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

<%
Collection<?> prB = (Collection<?>)request.getAttribute("prB");
%>
<script type="text/javascript">
		function play(i){
			<%
			if(prB != null && prB.size()>0){
				Iterator<?> it = prB.iterator();
				while(it.hasNext()){
					Brano bean = (Brano)it.next();
					if(bean.getDat()!=null){
			%>
			if(i==<%=bean.getCodice()%>){
				var audio=document.getElementById("<%=bean.getCodice()%>");
					if(playing==false){
						audio.play();
						playing=true;
						}else{
							audio.pause();
							playing=false;
							}
			}
			<%
			}
				}
			}
			%>
			if(i==1){
		var audio=document.getElementById("audio1");
			if(playing==false){
				audio.play();
				playing=true;
				}else{
					audio.pause();
					playing=false;
					}
			}if(i==2){
				var audio=document.getElementById("audio2");
				if(playing==false){
					audio.play();
					playing=true;
					}else{
						audio.pause();
						playing=false;
						}
				}if(i==3){
					var audio=document.getElementById("audio3");
					if(playing==false){
						audio.play();
						playing=true;
						}else{
							audio.pause();
							playing=false;
							}
					}
				if(i==4){
					var audio=document.getElementById("audio4");
					if(playing==false){
						audio.play();
						playing=true;
						}else{
							audio.pause();
							playing=false;
							}
					}if(i==5){
						var audio=document.getElementById("audio5");
						if(playing==false){
							audio.play();
							playing=true;
							}else{
								audio.pause();
								playing=false;
								}
						}if(i==6){
							var audio=document.getElementById("audio6");
							if(playing==false){
								audio.play();
								playing=true;
								}else{
									audio.pause();
									playing=false;
									}
							}if(i==7){
								var audio=document.getElementById("audio7");
								if(playing==false){
									audio.play();
									playing=true;
									}else{
										audio.pause();
										playing=false;
										}
								}if(i==8){
									var audio=document.getElementById("audio8");
									if(playing==false){
										audio.play();
										playing=true;
										}else{
											audio.pause();
											playing=false;
											}
									}if(i==9){
										var audio=document.getElementById("audio9");
										if(playing==false){
											audio.play();
											playing=true;
											}else{
												audio.pause();
												playing=false;
												}
										}if(i==10){
											var audio=document.getElementById("audio10");
											if(playing==false){
												audio.play();
												playing=true;
												}else{
													audio.pause();
													playing=false;
													}
											}
			}
		
	var playing=false;

</script>

<script>
	function avviso(){
	$('#exampleModal').modal({backdrop: 'static',keyboard: true, show:true});
	}; 
	
</script>

<script>
	function avvisoc(){
	$('#ModalCar').modal({backdrop: 'static',keyboard: true, show:true});
	}; 
	
</script>
</head>

<!-- 												Sfondo																	 -->
<body background=./imgs/sfondo6.jpg >

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
      <a class="nav-link" href="login.jsp">Login</a>
    </li>
    <li class="nav-item active">
      <a class="nav-link" href="ServletIndex">Home</a>
    </li>
    
  </ul>
  
  <!-- 									Barra di Ricerca													 -->
  
  <form action="ServletCerca" name="ricerca" class="navbar-form navbar-left" role="search">
  <div id="cerca">
 <input type="text" id="cerca" name="parola" placeholder="Ricerca"> 
  </div>
  <a href=""><button type="submit" value="Cerca" id="cerca">Cerca</button></a>
</form>
<a><button id="carrello" onclick="avvisoc()"><img src="./imgs/carrello.png" height="45px" alt="Foto carrello"></button></a> 
   </nav>
 </div>

<!-- 											Titolo																		 -->

<div class="container-fluid" >
	<div class="titolo">
	<h1 class="display-1" id="tit" >Music Console</h1>
</div>

<!-- 											Sotto menù															 		-->

<p class="t">
	<a class="sezione" href="#section1">Brani</a> &nbsp; &nbsp; &nbsp;
	<a class="sezione" href="#section2">Podcast</a> &nbsp; &nbsp; &nbsp;
	<a class="sezione" href="#section3">Album</a>
</p>

<!-- 											Brani																		 -->

<section id="sb" style="z-index:-1">
	<h2><a id="sect1" name="section1">Brani</a></h2>
	<div class="contenitore">
<%
if(prB != null && prB.size()>0){
	Iterator<?> it = prB.iterator();
	while(it.hasNext()){
		Brano bean = (Brano)it.next();
		if(bean.getDat()!=null){
%>
<div class="blocco">
	<audio id="<%=bean.getCodice()%>" onended="avviso()">
	<source src="./brani/<%=bean.getSuono()%>" type="audio/mpeg">
	</audio>
	
	<a href="ServletpageInfH?nome=<%=bean.getTitolo()%>&tipo=<%=bean.getTipo()%>&artista=<%=bean.getCantante()%>"><input class="info" type="button" value="&#128712;"></a>
	
	<img class="rounded-circle brano" src="./imgs/<%=bean.getImgBrano()%>" alt="foto brano" hspace="30">
	
	<p class="t"><%=bean.getTitolo()%>-<%=bean.getCantante()%>
	<input class="play" type="button" value="&#5125;" onClick="play(<%=bean.getCodice()%>)">
	</p>
</div>

<% 		
		}
	}
}

%>
	<div class="blocco" >
		<audio id="audio1" onended="avviso()">
		<source src="./brani/16 Marzo.mp3" type="audio/mpeg">
		</audio>
		
 <a href="Achille Lauro.html"> <input class="info" type="button" value="&#128712;"></a>

	<img class="rounded-circle brano" src="./imgs/img1.png" alt="foto brano" hspace="30" >

	<p class="t">16 Marzo - Achille Lauro 
    <input class="play" type="button" value="&#5125;" onClick="play(1);">
	</p>
</div>

	<div class="blocco">
		<audio id="audio2" onended="avviso()">
		<source src="./brani/Moncler.mp3" type="audio/mpeg">
		</audio>
	<a href="Geolier.html"><input class="info" type="button" value="&#128712;"></a>
	
	<img class="rounded-circle brano" src="./imgs/img2.png" alt="foto brano" hspace="30">
	
	<p class="t">Moncler - Geolier
	<input class="play" type="button" value="&#5125;" onClick="play(2);">
	</p>
</div>

	<div class="blocco">
		<audio id="audio3" onended="avviso()">
		<source src="./brani/L inizio di una nuova era.mp3" type="audio/mpeg">
		</audio>
	<a href="Jovanotti.html"><input class="info" type="button" value="&#128712;"></a>
	
	<img class="rounded-circle brano" src="./imgs/img3.png" alt="foto brano" hspace="30">
	<p class="t">L'inizio di una nuova era - Jovanotti
	<input class="play" type="button" value="&#5125;" onClick="play(3);">
	</p>
</div>

	<div class="blocco">
		<audio id="audio4" onended="avviso()">
		<source src="./brani/Montagne Verdi.mp3" type="audio/mpeg">
		</audio>
	<a href="Marcella Bella.html"><input class="info" type="button" value="&#128712;"></a>
	
	<img class="rounded-circle brano" src="./imgs/img4.png" alt="foto brano" hspace="30">
	<p class="t">Montagne Verdi - Marcella Bella
	<input class="play" type="button" value="&#5125;" onClick="play(4);">
	</p>
</div>

	<div class="blocco">
		<audio id="audio5" onended="avviso()">
		<source src="./brani/Collera.mp3" type="audio/mpeg">
		</audio>
	<a href="Nicola Siciliano.html"><input class="info" type="button" value="&#128712;"></a>
	
	<img class="rounded-circle brano" src="./imgs/img5.png" alt="foto brano" hspace="30">
	<p class="t">Collera - Nicola Siciliano
	<input class="play" type="button" value="&#5125;" onClick="play(5);">
	</p>
</div>

	<div class="blocco">
		<audio id="audio6" onended="avviso()">
		<source src="./brani/Superclassico.mp3" type="audio/mpeg">
		</audio>
	<a href="Ernia.html"><input class="info" type="button" value="&#128712;"></a>
	
	<img class="rounded-circle brano" src="./imgs/img6.png" alt="foto brano" hspace="30">
	<p class="t">Superclassico - Ernia
	<input class="play" type="button" value="&#5125;" onClick="play(6);">
	</p>
</div>

	<div class="blocco">
		<audio id="audio7" onended="avviso()">
		<source src="./brani/Stanza Singola.mp3" type="audio/mpeg">
		</audio>
	<a href="Franco126.html"><input class="info" type="button" value="&#128712;"></a>
	
	<img class="rounded-circle brano" src="./imgs/img7.png" alt="foto brano" hspace="30">
	<p class="t">Stanza Singola - Franco126
	<input class="play" type="button" value="&#5125;" onClick="play(7);">
	</p>
</div>

	<div class="blocco">
		<audio id="audio8" onended="avviso()">
		<source src="./brani/Crepe.mp3" type="audio/mpeg">
		</audio>
	<a href="Irama.html"><input class="info" type="button" value="&#128712;"></a>
	
	<img class="rounded-circle brano" src="./imgs/img8.png" alt="foto brano" hspace="30">
	<p class="t">Crepe - Irama
	<input class="play" type="button" value="&#5125;" onClick="play(8);">
	</p>
</div>

	<div class="blocco">
		<audio id="audio9" onended="avviso()">
		<source src="./brani/La Solitudine.mp3" type="audio/mpeg">
		</audio>
	<a href="Laura Pausini.html"><input class="info" type="button" value="&#128712;"></a>
	
	<img class="rounded-circle brano" src="./imgs/img9.png" alt="foto brano" hspace="30">
	<p class="t">La Solitudine - Laura Pausini
	<input class="play" type="button" value="&#5125;" onClick="play(9);">
	</p>
</div>

	<div class="blocco">
		<audio id="audio10" onended="avviso()">
		<source src="./brani/Completamente.mp3" type="audio/mpeg">
		</audio>
	<a href="TheGiornalisti.html"><input class="info" type="button" value="&#128712;"></a>
	
	<img class="rounded-circle brano" src="./imgs/img10.png" alt="foto brano" hspace="30">
	<p class="t">Completamente - TheGiornalisti
	<input class="play" type="button" value="&#5125;" onClick="play(10);">
	</p>
	</div>
</div>
</section>

<!-- 											Podcast																		 -->

<section>
	<h2><a id="sect2" name="section2">Podcast</a></h2>
	<div class="contenitore1">
<%
Collection<?> prP = (Collection<?>)request.getAttribute("prP");
if(prP != null && prP.size()>0){
	Iterator<?> it = prP.iterator();
	while(it.hasNext()){
		Podcast bean = (Podcast)it.next();
		if(bean.getDat()!=null){
%>	
<div class="blocco pod">
	<a href="ServletpageInfH?nome=<%=bean.getNomePodcast()%>&tipo=<%=bean.getTipo()%>&artista=<%=bean.getIdeatore()%>""><input class="info" type="button" value="&#128712;"></a>
	<img class="rounded-circle"  id="noeffet" src="./imgs/<%=bean.getImgPod()%>" alt="foto podcast" hspace="30">
	<p class="t"><%=bean.getNomePodcast()%>-<%=bean.getIdeatore()%></p>
	</div>
<%
		}
	}
}
%>	

	<div class="blocco pod">
	<a href="The Essential.html"><input class="info" type="button" value="&#128712;"></a>
	<img class="rounded-circle" id="noeffet" src="./imgs/img11.png" alt="foto podcast" hspace="30">
	<p class="t">The essential - Will Media e Mia Ceran</p>
	</div>
	<div class="blocco pod">
	<a href="Serial Killers.html"><input class="info" type="button" value="&#128712;"></a>
	<img class="rounded-circle" src="./imgs/img12.png" alt="foto podcast" hspace="30">
	<p class="t">Serial Killers - Parcast Network</p>
	</div>
	<div class="blocco pod">
	<a href="Muschio Selvaggio.html"><input class="info" type="button" value="&#128712;"></a>
	<img class="rounded-circle" src="./imgs/img13.png" alt="foto podcast" hspace="30">
	<p class="t">Muschio Selvaggio - Fedez e Luis Sal</p>
	</div>
	<div class="blocco pod">
	<a href="Audio Racconti.html"><input class="info" type="button" value="&#128712;"></a>
	<img class="rounded-circle" src="./imgs/img14.png" alt="foto podcast" hspace="30">
	<p class="t">Audio Racconti - Marina Galatioto</p>
	</div>
	<div class="blocco pod">
	<a href="Rilassamento.html"><input class="info" type="button" value="&#128712;"></a>
	<img class="rounded-circle" src="./imgs/img15.png" alt="foto podcast" hspace="30">
	<p class="t">Rilassamento Training autogeno - Gaia Nozza </p>
	</div>
</div>
</section>

<!-- 											Album																		 -->

<section>
	<h2><a id="sect3" name="section3">Album</a></h2>
	<div class="contenitore2">
<%
Collection<?> prA = (Collection<?>)request.getAttribute("prA");
if(prA != null && prA.size()>0){
	Iterator<?> it = prA.iterator();
	while(it.hasNext()){
		Album bean = (Album)it.next();
		if(bean.getDat()!=null){
%>
<div class="blocco al">
	<a href="ServletpageInfH?nome=<%=bean.getNomeAlbum()%>&tipo=<%=bean.getTipo()%>&artista=<%=bean.getNartista()%>""><input class="info" type="button" value="&#128712;"></a>
	<img class="rounded-circle" src="./imgs/<%=bean.getImgAlbum()%>" alt="foto album" hspace="30">
	<p class="t"><%=bean.getNomeAlbum()%>-<%=bean.getNartista()%></p>
	</div>
<%
		}
	}
}
%>	
	
	<div class="blocco al">
	<a href="Completamente Tour.html"><input class="info" type="button" value="&#128712;"></a>
	<img class="rounded-circle" src="./imgs/img10.png" alt="foto album" hspace="30">
	<p class="t">Completamente Tour - TheGiornalisti</p>
	</div>
	<div class="blocco al">
	<a href="La Solitudine.html"><input class="info" type="button" value="&#128712;"></a>
	<img class="rounded-circle" src="./imgs/img17.png" alt="foto album" hspace="30">
	<p class="t">La Solitudine - Laura Pausini</p>
	</div>
	<div class="blocco al">
	<a href="1969.html"><input class="info" type="button" value="&#128712;"></a>
	<img class="rounded-circle" src="./imgs/img18.png" alt="foto album" hspace="30">
	<p class="t">1969 - Achille Lauro</p>
	</div>
	<div class="blocco al">
	<a href="Jova beach party.html"><input class="info" type="button" value="&#128712;"></a>
	<img class="rounded-circle" src="./imgs/img19.png" alt="foto album" hspace="30">
	<p class="t">Jova Beach Party - Jovanotti</p>
	</div>
	<div class="blocco al">
	<a href="Tu non hai la piu pallida idea dell amore.html"><input class="info" type="button" value="&#128712;"></a>
	<img class="rounded-circle" src="./imgs/img20.png" alt="foto album" hspace="30">
	<p class="t">Tu non hai la più pallida idea dell amore - Marcella Bella </p>
	</div>
	<div class="blocco al">
	<a href="Gemelli.html"><input class="info" type="button" value="&#128712;"></a>
	<img class="rounded-circle" src="./imgs/img21.png" alt="foto album" hspace="30">
	<p class="t">Gemelli - Ernia</p>
	</div>
	<div class="blocco al">
	<a href="Emanuele.html"><input class="info" type="button" value="&#128712;"></a>
	<img class="rounded-circle" src="./imgs/img22.png" alt="foto album" hspace="30">
	<p class="t">Emanuele - Geolier</p>
	</div>
	<div class="blocco al">
	<a href="Crepe.html"><input class="info" type="button" value="&#128712;"></a>
	<img class="rounded-circle" src="./imgs/img8.png" alt="foto album" hspace="30">
	<p class="t">Crepe - Irama</p>
	</div>
	<div class="blocco al">
	<a href="Napoli 51.html"><input class="info" type="button" value="&#128712;"></a>
	<img class="rounded-circle" src="./imgs/img5.png" alt="foto album" hspace="30">
	<p class="t">Napoli 51 - Nicola Siciliano</p>
	</div>
	<div class="blocco al">
	<a href="Stanza Singola.html"><input class="info" type="button" value="&#128712;"></a>
	<img class="rounded-circle" src="./imgs/img7.png" alt="foto album" hspace="30">
	<p class="t">Stanza Singola - Franco 126</p>
	</div>
</div>
</section>
	
	<!-- Modal -->

 <div class="modal fade" id="exampleModal" data-toggle="modal" tabindex="-1" role="dialog" aria-hidden="true" aria-labelledby="exampleModalLabel" >
  <div class="modal-dialog" role="document">
    <div class="modal-content"> 
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">ATTENZIONE!</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
<span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Per continuare ad ascoltare accedi o registrati!
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Chiudi</button>
      </div>
    </div>
  </div>
</div> 
	
	<!-- Modal -->

 <div class="modal fade" id="ModalCar" data-toggle="modal" tabindex="-1" role="dialog" aria-hidden="true" aria-labelledby="exampleModalLabel" >
  <div class="modal-dialog" role="document">
    <div class="modal-content"> 
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">ATTENZIONE!</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
<span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Per accedere al carrello accedi o registrati!
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