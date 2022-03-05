DROP DATABASE IF EXISTS musicdb;

CREATE DATABASE musicdb;
USE musicdb;

DROP USER IF EXISTS 'airuser'@'%localhost' ;
CREATE USER 'airuser'@'%localhost' IDENTIFIED BY 'airuser';
GRANT ALL ON musicdb. * TO 'airuser'@'%localhost';

CREATE TABLE profilo(
id int not null auto_increment,
nome varchar(20) not null,
cognome varchar(30) not null,
citta varchar(30) not null,
indirizzo varchar(50) not null,
telefono varchar(10) not null,
email varchar(50) not null,
username varchar(20) not null,
password varchar(8) not null,
primary key (id)
);

/*INSERT INTO profilo values (1,"Katia","Buonocore","Firenze","via dei fiori","1234567894","k.b@gmail.com","k.buonocore",123);
INSERT INTO profilo values (2,"Marta","Coiro","Milano","via delle fontane","1234567895","m.c@gmail.com","m.coiro",456);
INSERT INTO profilo values (3,"Rita","Cuccaro","Bologna","via del sole","1234567896","r.c@gmail.com","r.cuccaro",789);
*/
CREATE TABLE Carta(
utente varchar(50) not null,
cvv int not null,
nome varchar(20) not null,
cognome varchar(30) not null,
numero varchar(20) not null,
dataScadenza varchar(50) not null,
primary key (cvv)
);

CREATE TABLE carrello(
utente varchar(20) not null, 
cod int not null auto_increment,
nome varchar(50) not null,
autore varchar(50) not null,
tipo varchar(20) not null,
costo float not null,
totq float,
quantità int,
primary key(cod)
);

CREATE TABLE Magazzino(
cod int not null auto_increment,
nome varchar(50) not null,
autore varchar(50) not null,
tipo varchar(20) not null,
costo float not null,
quantità int not null,
primary key(cod)
);

INSERT INTO Magazzino values(1,	"La Solitudine", "Laura Pausini",	"CD", 10.50, 200);
INSERT INTO Magazzino values(2,	"La Solitudine", "Laura Pausini",	"Vinile", 45.50, 200);
INSERT INTO Magazzino values(3,	"Completamente Tour", "TheGiornalisti",	"CD", 9.50, 200);	
INSERT INTO Magazzino values(4,	"Completamente Tour", "TheGiornalisti",	"Vinile", 35.50, 200);	
INSERT INTO Magazzino values(5,	1969, "Achille Lauro", "CD", 14.50, 200);
INSERT INTO Magazzino values(6,	1969, "Achille Lauro", "Vinile", 25.50, 200);
INSERT INTO Magazzino values(7,	"Jova beach party", "Jovanotti", "CD",	14.50, 200);
INSERT INTO Magazzino values(8,	"Jova beach party", "Jovanotti", "Vinile",	25.50, 200);
INSERT INTO Magazzino values(9,	"Tu non hai la piu pallida idea dell amore", "Marcella Bella",	"CD", 22.50, 200);	
INSERT INTO Magazzino values(10, "Tu non hai la piu pallida idea dell amore", "Marcella Bella",	"Vinile", 57.50, 200);	
INSERT INTO Magazzino values(11, "Gemelli", "Ernia", "CD",	22.50, 200);
INSERT INTO Magazzino values(12, "Gemelli", "Ernia", "Vinile",	57.50, 200);
INSERT INTO Magazzino values(13,	"Emanuele", "Geolier",	"CD", 22.50, 200);
INSERT INTO Magazzino values(14,	"Emanuele", "Geolier",	"Vinile",57.50, 200);
INSERT INTO Magazzino values(15,	"Crepe", "Irama",	"CD",	19.50, 200);
INSERT INTO Magazzino values(16,	"Crepe", "Irama",	"Vinile",	27.50, 200);
INSERT INTO Magazzino values(17,	"Napoli 51", "Nicola Siciliano",	"CD",	17.50, 200);
INSERT INTO Magazzino values(18,	"Napoli 51", "Nicola Siciliano",	"Vinile",	25.50, 200);
INSERT INTO Magazzino values(19,	"Stanza Singola", "Franco126",	"CD",	18.50, 200);
INSERT INTO Magazzino values(20,	"Stanza Singola", "Franco126",	"Vinile",	27.50, 200);


CREATE TABLE Ordini(
stato varchar(8) not null,
data date,
quantità int,
utente varchar(20) not null, 
indice int not null,
cod int not null auto_increment,
nome varchar(50) not null,
autore varchar(50) not null,
tipo varchar(20) not null,
costo float not null,
tot float not null,
primary key(cod)
);

CREATE TABLE image(
imageId int not null auto_increment,
imageFileName varchar(50),
primary key (imageId)
);

INSERT INTO image values (1,"carrello.png");
INSERT INTO image values (2,"foto1.jpg");
INSERT INTO image values (3,"foto10.jpg");
INSERT INTO image values (4,"foto11.jpg");
INSERT INTO image values (5,"foto12.jpg");
INSERT INTO image values (6,"foto13.jpg");
INSERT INTO image values (7,"foto14.jpg");
INSERT INTO image values (8,"foto15.jpg");
INSERT INTO image values (9,"foto16.jpg");
INSERT INTO image values (10,"foto17.jpg");
INSERT INTO image values (11,"foto18.jpg");
INSERT INTO image values (12,"foto19.jpg");
INSERT INTO image values (13,"foto2.jpg");
INSERT INTO image values (14,"foto20.png");
INSERT INTO image values (15,"foto21.jpg");
INSERT INTO image values (16,"foto22.jpg");
INSERT INTO image values (17,"foto23.jpg");
INSERT INTO image values (18,"foto24.jpg");
INSERT INTO image values (19,"foto25.jpg");
INSERT INTO image values (20,"foto26.jpg");
INSERT INTO image values (21,"foto27.jpg");
INSERT INTO image values (22,"foto28.jpg");
INSERT INTO image values (23,"foto29.jpg");
INSERT INTO image values (24,"foto3.jpeg");
INSERT INTO image values (25,"foto30.jpg");
INSERT INTO image values (26,"foto4.jpg");
INSERT INTO image values (27,"foto5.jpg");
INSERT INTO image values (28,"foto6.jpg");
INSERT INTO image values (29,"foto7.jpg");
INSERT INTO image values (30,"foto8.jpg");
INSERT INTO image values (31,"foto9.jpg");
INSERT INTO image values (32,"img1.png");
INSERT INTO image values (33,"img10.png");
INSERT INTO image values (34,"img11.png");
INSERT INTO image values (35,"img12.png");
INSERT INTO image values (36,"img13.png");
INSERT INTO image values (37,"img14.png");
INSERT INTO image values (38,"img15.png");
INSERT INTO image values (39,"img17.png");
INSERT INTO image values (40,"img18.png");
INSERT INTO image values (41,"img19.png");
INSERT INTO image values (42,"img2.png");
INSERT INTO image values (43,"img20.png");
INSERT INTO image values (44,"img21.png");
INSERT INTO image values (45,"img22.png");
INSERT INTO image values (46,"img3.png");
INSERT INTO image values (47,"img4.png");
INSERT INTO image values (48,"img5.png");
INSERT INTO image values (49,"img6.png");
INSERT INTO image values (50,"img7.png");
INSERT INTO image values (51,"img8.png");
INSERT INTO image values (52,"img9.png");
INSERT INTO image values (53,"onda.gif");
INSERT INTO image values (54,"sfondo1.png");
INSERT INTO image values (55,"sfondo6.jpg");
INSERT INTO image values (56,"sfondo al contrario.jpg");
INSERT INTO image values (57,"sfondoAmministartore.png");
INSERT INTO image values (58,"sfondoLogin.png");

CREATE TABLE AccountUtente  (
  Nickname varchar(50) not null,
  Password char(40) not null,
  primary key (Nickname)
);

/*INSERT INTO AccountUtente values ("k.buonocore","UCryptoJSU2FsdGVkX1%2BggxG5d69E4XVd5QHmr%2Fo%2BOMUXqC4uimtoLq%2BrXRmoPNH73swuGYLbGC1Tv0zfAtcpjEI7YYdB9Tpc0KqkP6J7PCJNXfiO0oMg6p6xNbmwyIAxEl8fSCY7nU7p5bR6rqPMa49trZVurg%3D%3D%28U2FsdGVkX1%2B8E3w3mbaHW9KwvA%2BGNXNsxpB3%2Bpfg1QZCh7QPjTTediGbu70%2FetDO%29");
INSERT INTO AccountUtente values ("m.coiro",456);
INSERT INTO AccountUtente values ("r.cuccaro",789);*/

CREATE TABLE Amm(
	Utente varchar(50) not null,
	Password varchar(8) not null,
    ruolo varchar(50) not null,
	primary key(Utente)
);

INSERT INTO Amm values ("k.buonocore",123,"gestore ordini");
INSERT INTO Amm values ("m.coiro",456,"gestore catalogo");
INSERT INTO Amm values ("r.cuccaro",789,"gestore magazzino");

CREATE TABLE Playlist (
	id int not null auto_increment,
	nomeUtente varchar(50),
    Nome varchar(50) not null,
    nomeBrano varchar(50),
    numBrani int,
    nomeArtista varchar(50),
    primary key(id)
    );
  
/*INSERT INTO Playlist values (2,"Jack",NULL,3);
INSERT INTO Playlist values (7,"Mhh","kb",5);
INSERT INTO Playlist values (10,"Hel","jk",1);
INSERT INTO Playlist values (19,"MC","bho",2);
INSERT INTO Playlist values (22,"Rita","rc",6);
INSERT INTO Playlist values (23,"Emis","gs",4);
INSERT INTO Playlist values (28,"KB","kiss",7);
INSERT INTO Playlist values (29,"Come","stai",8);*/


/*CREATE TABLE Creare (
	Nickname varchar(50),
    CodicePlaylist int not null,
    primary key(Nickname,CodicePlaylist),
    foreign key(Nickname) references AccountUtente (Nickname),
    foreign key(CodicePlaylist) references Playlist (CodicePlaylist)
);*/

/*INSERT INTO Creare values("k.buonocore",	7);
INSERT INTO Creare values("m.coiro",	29);
INSERT INTO Creare values("r.cuccaro",	23);
INSERT INTO Creare values("g.aniello",	2);
INSERT INTO Creare values("k.buonocore",	28);
INSERT INTO Creare values("m.coiro",	19);
INSERT INTO Creare values("r.cuccaro",	22);
INSERT INTO Creare values("g.aniello",	10);*/
    
/*CREATE TABLE Pubblica (
	NumeroFollowerPlaylist int not null,
    CodicePlaylist int not null,
    primary key(CodicePlaylist),
    foreign key(CodicePlaylist) references Playlist (CodicePlaylist)
    );*/
    
/*INSERT INTO Pubblica values(130,2);
INSERT INTO Pubblica values(150,19);*/


CREATE TABLE Album (
	CodiceAlbum int not null,
    NomeAlbum varchar(50) not null,
    imgAlbum varchar(100) not null, 
    nArtista varchar(50) not null,
    tipo varchar(20) not null,
    dataa date,
    descrizione varchar(1000),
    prezzoS float,
    prezzoV float,
    prezzoC float,
    primary key(CodiceAlbum)
    );
    
INSERT INTO Album values(701,	"La Solitudine", "img17.png", "Laura Pausini",	"Album",	NULL,	"L'Album ha una durata di 33:09 min ed è stato pubblicato il 26 Aprile 1994.",	7.50,	45.50,	10.50);
INSERT INTO Album values(905,	"Completamente Tour", "img10.png", "TheGiornalisti",	"Album",	NULL,	"L'Album ha una durata di 39:42 min ed è stato pubblicato il 21 Ottobre 2016.",	9.50,	35.50,	9.50);
INSERT INTO Album values(100,	1969, "img18.png", "Achille Lauro",	"Album",	NULL,	"L'Album ha una durata di 30:08 min ed è stato pubblicato il 12 Aprile 2019.",	8.00,	25.50,	14.50);
INSERT INTO Album values(120,	"Jova beach party", "img19.png", "Jovanotti",	"Album",	NULL,	"L'Album ha una durata di 30:08 min ed è stato pubblicato il 7 Giugno 2019.",	7.50,	47.50,	16.50);
INSERT INTO Album values(160,	"Tu non hai la piu pallida idea dell amore", "img20.png", "Marcella Bella",	"Album",	NULL,	"L'Album ha una durata di 30:08 min ed è stato pubblicato il 12 Aprile 1972.",	10.50,	57.50,	2.50);
INSERT INTO Album values(200,	"Gemelli", "img21.png", "Ernia",	"Album",	NULL,	"L'Album ha una durata di 39:13 min ed è stato pubblicato il 19 Giugno 2019.",	10.50,	57.50,	22.50);
INSERT INTO Album values(400,	"Emanuele", "img22.png", "Geolier",	"Album",	NULL,	"L'Album ha una durata di 47:14 min ed è stato pubblicato il 10 Ottobre 2019.",	10.50,	57.50,	22.50);
INSERT INTO Album values(302,	"Crepe", "img8.png", "Irama",	"Album",	NULL,	"L'Album ha una durata di 23:33 min ed è stato pubblicato il 28 Agosto 2020.",	8.50,	27.50,	19.50);
INSERT INTO Album values(670,	"Napoli 51", "img5.png", "Nicola Siciliano",	"Album",	NULL,	"L'Album ha una durata di 35:14 min ed è stato pubblicato il 30 Ottobre 2020.",	6.50,	22.50,	17.50);
INSERT INTO Album values(170,	"Stanza Singola", "img7.png", "Franco126",	"Album",	NULL,	"L'Album ha una durata di 36:00 min ed è stato pubblicato il 25 Gennaio 2019.",	5.50,	27.50,	18.50);

CREATE TABLE Brano (
	Codice int not null,
    Titolo varchar(50) not null,
    Durata float,
    Cantante varchar(50) not null,
    imgBrano varchar(100) not null,
    genere varchar(50) not null,
    tipo varchar(20) not null,
    suono varchar(100),
    dataa date,
    prezzo float,
    descrizione varchar(2000),
    primary key(Codice)
    );
    
INSERT INTO Brano values(3,	"16 Marzo",	3.53,	"Achille Lauro", "img1.png", "pop",	"Brano",	"16 Marzo.mp3",	NULL,	0.80,	"Achille Lauro, pseudonimo di Lauro De Marinis (Verona, 11 luglio 1990), è un cantautore e rapper italiano. Noto per i suoi lavori nell'hip hop,
ha preso parte alla 69ª edizione del Festival di Sanremo con il brano Rolls Royce
e alla 70ª edizione con il brano Me ne frego. Il cantante prende il suo nome d'arte dall'omonimo armatore partenopeo,
una scelta dettata semplicemente dall'associazione che in molti,
quando era più giovane, erano soliti fare tra il suo nome di battesimo, Lauro, e il cognome, appunto, dell’armatore");
INSERT INTO Brano values(7,	"Moncler",	2.70,	"Geolier", "img2.png", "rap",	"Brano",	"Moncler.mp3",	NULL,	1.25,	"Si è avvicinato alla musica con il freestyle ed è cresciuto attraverso i dischi dei Co'Sang, Club Dogo, Michael Jackson, Nas e Rocco Hunt. Il suo singolo 
di debutto è stato P Secondigliano, uscito nel 2018 e realizzato con Nicola Siciliano; Nel 2019 Geolier ha firmato per BFM Music, etichetta indipendente fondata da Luchè, con la quale ha pubblicato nell'ottobre dello stesso anno l'album di debutto Emanuele Il disco si caratterizza
 per la presenza di alcune collaborazioni con vari artisti, tra cui lo stesso Luchè, Emis Killa, Gué Pequeno, Lele Blade e MV Killa. In meno di un anno, il disco è stato certificato disco di platino dalla FIMI per le oltre 50.000 unità vendute a livello nazionale; per la sua promozione 
 sono stati pubblicati i singoli Como te, Narcos e Yacht.");
INSERT INTO Brano values(4,	"L inizio di una nuova era",	3.20,	"Jovanotti", "img3.png",	"pop",	"Brano",	"L inizio di una nuova era.mp3",	NULL,	1.50,	"Jovanotti, talvolta accreditato come Lorenzo Jovanotti, pseudonimo di Lorenzo Cherubini (Roma, 27 settembre 1966), è un cantautore, 
rapper e disc jockey italiano. Diventa famoso alla fine degli anni ottanta, lanciato da Claudio Cecchetto. Dalla commistione di hip hop dei primi successi, tuttavia, Jovanotti si discosta ben presto avvicinandosi gradualmente al modello della world music (sempre interpretata in chiave hip hop e funky). 
All'evoluzione musicale corrisponde un mutare dei testi dei suoi brani, che, nel corso degli anni, tendono a toccare temi sempre più personali, più tipici dello stile cantautorale italiano.");
INSERT INTO Brano values(5,	"Montagne Verdi",	3.40,	"Marcella Bella", "img4.png", "classico",	"Brano",	"Montagne Verdi.mp3",	NULL,	1.20,	"Nel 1965, a Misterbianco, vince le selezioni per partecipare al Festival degli sconosciuti di Ariccia, rassegna canora organizzata da Teddy Reno, ma la vittoria 
non le viene convalidata in quanto ha solo tredici anni, due in meno di quelli previsti dal regolamento del concorso. Risulta sempre prima a numerosi altri concorsi cui partecipa ma, a causa del suo accento spiccatamente siciliano, stenta a trovare una casa discografica disposta a puntare su di lei. L'incontro nel 1968 
con Ivo Callegari, già produttore di Caterina Caselli, procura a Marcella l'opportunità di firmare un contratto con la CGD. Incide così il primo 45 giri nel maggio del 1969: sul lato A troviamo Un ragazzo nel cuore, scritta da Mogol e Roberto Soffici, sul retro Il pagliaccio, con cui partecipa al girone B del Cantagiro.
 Nello stesso anno la casa discografica propone alla cantante di incidere Bocca dolce, versione italiana di Sugar sugar, un hit internazionale degli Archies, con cui partecipa alla V Mostra Internazionale di Musica Leggera che si tiene a Venezia: il brano viene accolto tiepidamente dal pubblico.");
INSERT INTO Brano values(9,	"Collera",	3.50,	"Nicola Siciliano", "img5.png", "neomelodico",	"Brano",	"Collera.mp3",	NULL,	0.90,	"Nicola nasce nel 2002 a Napoli e cresce nel quartiere di Secondigliano. Si avvicina alla musica all'età di sette anni e appena un anno dopo pubblica in rete la sua prima canzone, Rint’ a sti vicol. 
Inizialmente si dedica anche al videomaking, attività che gli permetterà di dirigere i videoclip dei suoi primi brani. Nella maggior parte dei suoi lavori Nicola ricopre anche la figura di produttore e beatmaker.");
INSERT INTO Brano values(6,	"Superclassico",	3.20,	"Ernia", "img6.png", "hip-hop",	"Brano",	"Superclassico.mp3",	NULL,	1.00,	"Matteo Professione, meglio conosciuto con lo pseudonimo di Ernia, nasce a Milano il 29 Novembre del 1993. Ernia è bisnipote di immigrati, il bisnonno materno
 infatti è un esodato roviginese (originario del Montenegro), il bisnonno paterno invece ha origini svizzere. Il rapper trascorre la sua infanzia e la sua adolescenza nel quartiere QT8. I genitori sono distanti anni luce dalla professione del figlio, la madre infatti è una docente di lettere e di latino,
 il padre invece lavora in banca ed è laureato in Economia e Commercio. Molto probabilmente sono loro a spingere Matteo ad iscriversi all’Università dopo aver conseguito la maturità, qui il cantante prima segue il corso di Lingue e letteratura moderna e successivamente quello di Letteratura straniera.
 La sua formazione traspare anche nei testi delle sue canzoni, molte volte ricche di riferimenti a poesie di autori del calibro di Baudelaire o del cantautorato italiano.");
INSERT INTO Brano values(10, "Stanza Singola",	3.60,	"Franco126", "img7.png", "indie",	"Brano",	"Stanza Singola.mp3",	NULL,	1.30,	"Inizialmente, Franco126 si era proposto come artista hip hop, facendo ampio uso dell'Auto-Tune. In Polaroid ha intrapreso uno stile che unisce l'indie pop con il trap, 
mentre con l'album di debutto Stanza singola l'artista ha abbandonato l'Auto-Tune e il trap per avvicinarsi alla canzone d'autore. Ha dichiarato di ispirarsi a Franco Califano, Lucio Dalla, Francesco De Gregori, Claudio Baglioni, E duardo De Crescenzo e Luca Carboni.Viene considerato l'erede naturale di Califano, 
oltre ad essere «uno dei più interessanti songwriter del panorama musicale contemporaneo».");
INSERT INTO Brano values(8,	"Crepe",	3.30,	"Irama", "img8.png",	"pop",	"Brano",	"Crepe.mp3",	NULL,	1.45,	"Irama, pseudonimo di Filippo Maria Fanti (Carrara, 20 dicembre 1995), è un cantautore e rapper italiano. Ha esordito ufficialmente nel panorama discografico partecipando al 66º Festival 
di Sanremo nel 2016 con il singolo Cosa resterà nella categoria Nuove Proposte, ma è salito alla ribalta nel 2018 in seguito alla vittoria della diciassettesima edizione del talent show Amici di Maria De Filippi. Successivamente, ha partecipato altre volte a Sanremo, 
all’edizione 2019, con la canzone La ragazza con il cuore di latta e a quella 2021 con La genesi del tuo colore.");
INSERT INTO Brano values(1,"La Solitudine",	3.57,	"Laura Pausini", "img9.png",	"pop",	"Brano",	"La Solitudine.mp3",	NULL,	1.50,	"Laura Pausini nasce il 16 maggio 1974 a Faenza, in provincia di Ravenna,e cresce a Solarolo. È la primogenita del cantante di pianobar 
Fabrizio Pausini (insieme a Renzo Casadio nel duo chiamato Les Copains Music Show) e della maestra d'asilo Gianna Ballardini, e ha una sorella minore di nome Silvia (nata il 25 gennaio 1977). Da ragazzina, inizia ad affiancare il padre nelle serate di pianobar lungo la riviera romagnola
 e con lui registra tra il 1987 e il 1992 tre demo: I sogni di Laura, Laura e L'immenso. Nel 1991, al Festival di Castrocaro, supera le selezioni con il brano New York, New York ma non accede alla finale. mentre l'anno dopo vince il concorso televisivo Sanremo famosi 
 con il brano Si sta così aggiudicandosi la possibilità di partecipare al Festival di Sanremo 1992, al quale però non verrà chiamata. Tuttavia, grazie ai suoi primi produttori (Marco Marati e Angelo Valsiglio), la Warner Music Italy diretta da Fabrizio Giannini
 le offre un contratto e la possibilità di lavorare a un progetto discografico, sotto il marchio CGD.");
INSERT INTO Brano values(2,	"Completamente",	3.10,	"TheGiornalisti", "img10.png", "indie",	"Brano",	"Completamente.mp3",	NULL,	1.15,	"I Thegiornalisti sono un gruppo musicale pop italiano formatosi a Roma nel 2009 e composto dal batterista Marco Primavera e dal polistrumentista Marco Antonio Musella.
 Del gruppo è stato membro fondatore anche il cantante Tommaso Paradiso, che ha abbandonato la formazione nel 2019.");

/*CREATE TABLE Costituire (
	CodicePlaylist int not null,
    CodiceBrano int not null,
    primary key(CodicePlaylist,CodiceBrano),
    foreign key(CodicePlaylist) references Playlist(CodicePlaylist),
    foreign key(CodiceBrano) references Brano(Codice)
    );*/
    
/*INSERT INTO Costituire values(2,1);
INSERT INTO Costituire values(2,5);
INSERT INTO Costituire values(2,8);
INSERT INTO Costituire values(7,1);
INSERT INTO Costituire values(7	,8);
INSERT INTO Costituire values(7	,3);
INSERT INTO Costituire values(7,	6);
INSERT INTO Costituire values(7	,7);
INSERT INTO Costituire values(10,7);
INSERT INTO Costituire values(19,4);
INSERT INTO Costituire values(19,5);
INSERT INTO Costituire values(22,3);
INSERT INTO Costituire values(22,5);
INSERT INTO Costituire values(22,8);
INSERT INTO Costituire values(22,2);
INSERT INTO Costituire values(22,9);
INSERT INTO Costituire values(22,10);
INSERT INTO Costituire values(23,2);
INSERT INTO Costituire values(23,5);
INSERT INTO Costituire values(23,10);
INSERT INTO Costituire values(23,1);
INSERT INTO Costituire values(28,3);
INSERT INTO Costituire values(28,4);
INSERT INTO Costituire values(28,6);
INSERT INTO Costituire values(28,9);
INSERT INTO Costituire values(28,10);
INSERT INTO Costituire values(28,2);
INSERT INTO Costituire values(28,5);
INSERT INTO Costituire values(29,6);
INSERT INTO Costituire values(29,7);
INSERT INTO Costituire values(29,9);
INSERT INTO Costituire values(29,2);
INSERT INTO Costituire values(29,1);
INSERT INTO Costituire values(29,3);
INSERT INTO Costituire values(29,4);
INSERT INTO Costituire values(29,8);*/


CREATE TABLE Collaborazioni (
	IdCollaborazioni varchar(30),
	NomeArtista varchar(20) not null,
    primary key(IdCollaborazioni)
    );
    
INSERT INTO Collaborazioni values("345","Gow	Tribe");
INSERT INTO Collaborazioni values("110","Dardust");
INSERT INTO Collaborazioni values("231","Rocco	Hunt");
INSERT INTO Collaborazioni values("678","Tommaso	Paradiso");
INSERT INTO Collaborazioni values("NULL","nessuno");
    

    CREATE TABLE Artista (
	NomeArtista varchar(20) not null,
    Descrizione varchar(200),
    Offerta char(10),
    IdCollaborazioni varchar(30),
    NumeroAscoltatori int not null,
    primary key(NomeArtista),
    foreign key(IdCollaborazioni) references Collaborazioni(IdCollaborazioni)
    );
    
INSERT INTO Artista values("Laura Pausini","Il 1993 Ã¨ l'anno in cui Laura Pausini vince il Festival di Sanremo con La Solitudine.","CD",	"NULL",	5000000	); 
INSERT INTO Artista values("TheGiornalisti","I TheGiornalisti sono la piÃ¹ importante rivelazione della musica italiana, hanno dato il via al nuovo pop italiano.",	"VINILE",	"NULL",2500000); 
INSERT INTO Artista values("Achille Lauro",	"Achille Lauro: icona della musica e della moda.",	"NULL",	"345",	1000000); 
INSERT INTO Artista values("Jovanotti",	"Dagli anni 80 Ã¨ la figura piÃ¹ importante che emerge nella musica pop italiana.",	"VINILE",	"110",	1000000 ); 
INSERT INTO Artista values("Marcella Bella",	"NULL",	"VINILE",	"NULL",	100000); 
INSERT INTO Artista values("Ernia",	"Ernia dall eta' di 12 anni frequenta l ambiente musicale hip-hop facendosi conoscere per le sue doti di freestyle.",	"CD",	"NULL",	200000); 
INSERT INTO Artista values("Geolier",	"NULL",	"CD",	"NULL",	2000000 ); 
INSERT INTO Artista values("Irama",	"NULL",	"CD",	"NULL",	2000000); 
INSERT INTO Artista values("Nicola Siciliano",	"NULL",	"CD",	"231",	700000); 
INSERT INTO Artista values("Franco126",	"E' un autore di canzoni romane.",	"VINILE",	"678",	1000000); 
 
 CREATE TABLE Effettuare (
	NomeArtista varchar(20) not null,
    IdCollaborazioni varchar(30),
    primary key(NomeArtista,IdCollaborazioni)
    );
    
INSERT INTO Effettuare values("Laura	Pausini",	"NULL");
INSERT INTO Effettuare values("TheGiornalisti",	"NULL");
INSERT INTO Effettuare values("Achille	Lauro",	"345");
INSERT INTO Effettuare values("Jovanotti",	"110");
INSERT INTO Effettuare values("Marcella	Bella",	"NULL");
INSERT INTO Effettuare values("Ernia",	"NULL");
INSERT INTO Effettuare values("Geolier",	"NULL");
INSERT INTO Effettuare values("Irama",	"NULL");
INSERT INTO Effettuare values("Nicola	Siciliano",	"231");
INSERT INTO Effettuare values("Franco126",	"678");


CREATE TABLE Scrivere (
	CodiceBrano int not null,
    NomeArtista varchar(20) not null,
    primary key(NomeArtista,CodiceBrano),
    foreign key(NomeArtista) references Artista(NomeArtista),
    foreign key(CodiceBrano) references Brano(Codice)
    );

INSERT INTO Scrivere values(1,	"Laura Pausini");
INSERT INTO Scrivere values(2,	"TheGiornalisti");
INSERT INTO Scrivere values(3,	"Achille Lauro");
INSERT INTO Scrivere values(4,	"Jovanotti");
INSERT INTO Scrivere values(5,	"Marcella Bella");
INSERT INTO Scrivere values(6,	"Ernia");
INSERT INTO Scrivere values(7,	"Geolier");
INSERT INTO Scrivere values(8,	"Irama");
INSERT INTO Scrivere values(9,	"Nicola Siciliano");
INSERT INTO Scrivere values(10,	"Franco126");


CREATE TABLE Seguire (
	NomeArtista varchar(20) not null,
    Nickname varchar(50) not null,
    primary key(NomeArtista,Nickname),
    foreign key(NomeArtista) references Artista(NomeArtista),
    foreign key(Nickname) references AccountUtente(Nickname)
    );
    
/*INSERT INTO Seguire values("TheGiornalisti",	"m.coiro");
INSERT INTO Seguire values("Achille Lauro","r.cuccaro");
INSERT INTO Seguire values("Ernia",	"k.buonocore");
INSERT INTO Seguire values("Geolier",	"k.buonocore");
INSERT INTO Seguire values("Franco126",	"m.coiro");*/
    
CREATE TABLE Produce (
	NomeArtista varchar(20) not null,
    CodiceAlbum int not null,
    primary key(NomeArtista,CodiceAlbum),
    foreign key(NomeArtista) references Artista(NomeArtista),
    foreign key(CodiceAlbum) references Album(CodiceAlbum)
    );
    
INSERT INTO Produce values("Laura Pausini",	701);
INSERT INTO Produce values("TheGiornalisti",	905);
INSERT INTO Produce values("Achille Lauro",	100);
INSERT INTO Produce values("Jovanotti",	120);
INSERT INTO Produce values("Marcella Bella",	160);
INSERT INTO Produce values("Ernia",	200);
INSERT INTO Produce values("Geolier",	400);
INSERT INTO Produce values("Irama",	302);
INSERT INTO Produce values("Nicola Siciliano",	670);
INSERT INTO Produce values("Franco126",	170);

CREATE TABLE GenereMusicale (
	IDGenere int not null,
    Categoria char(20) not null,
    primary key(IDGenere)
    );
    
INSERT INTO GenereMusicale values(1	,"pop");
INSERT INTO GenereMusicale values(2	,"indie");
INSERT INTO GenereMusicale values(3,	"rap");
INSERT INTO GenereMusicale values(4	,"neomelodico");
INSERT INTO GenereMusicale values(5	,"classico");
INSERT INTO GenereMusicale values(6	,"freestyle");
INSERT INTO GenereMusicale values(7	,"hip-hop");


CREATE TABLE Suona (
	NomeArtista varchar(20) not null,
    IDGenere int not null,
    primary key(NomeArtista,IDGenere),
    foreign key(NomeArtista) references Artista(NomeArtista),
    foreign key(IDGenere) references GenereMusicale(IDGenere)
    );
    
INSERT INTO Suona values("Laura Pausini",	1);
INSERT INTO Suona values("TheGiornalisti",	2);
INSERT INTO Suona values("TheGiornalisti",	1);
INSERT INTO Suona values("Achille Lauro",	1);
INSERT INTO Suona values("Jovanotti",	1);
INSERT INTO Suona values("Jovanotti",	3);
INSERT INTO Suona values("Marcella Bella",	5);
INSERT INTO Suona values("Ernia",	6);
INSERT INTO Suona values("Ernia",	7);
INSERT INTO Suona values("Geolier",	3);
INSERT INTO Suona values("Irama",	1);
INSERT INTO Suona values("Nicola Siciliano",	4);
INSERT INTO Suona values("Franco126",	2);

CREATE TABLE StazioneRadio (
	Frequenza float not null,
    NomeRadio varchar(20) not null,
    Nickname varchar(50) not null,
    primary key(Frequenza),
    foreign key(Nickname) references AccountUtente(Nickname)
    );
    
/*INSERT INTO StazioneRadio values(102.05,	"RTL",	"g.aniello");*/
    
    
CREATE TABLE Contiene (
	CodiceBrano int not null,
    FrequenzaRadio float not null,
    primary key(CodiceBrano,FrequenzaRadio),
    foreign key(CodiceBrano) references Brano(Codice),
    foreign key(FrequenzaRadio) references StazioneRadio(Frequenza)
    );
    
/*INSERT INTO Contiene values(1,	102.05);
INSERT INTO Contiene values(7,	102.05);
INSERT INTO Contiene values(10,	102.05);*/

CREATE TABLE Podcast (
	NomePodcast varchar(30) not null,
    Ideatore char(60) not null,
    Descrizione varchar(500),
    Durata float,
    NumeroEpisodi int,
    imgPod varchar(100) not null,
    tipo varchar(20) not null,
    dataa date,
    prezzo float,
    primary key(NomePodcast)
    );
    
INSERT INTO Podcast values("Audio Racconti",	"Marina Galatioto",	"Ad ogni lettera dell'alfabeto è stata associata una fiaba tutte allegre e a lieto fine.
Un mondo in cui tutto può succedere,in cui la diversità può anche essere un punto
di forza e i draghi non sempre sono cattivi.",	10.00,	20	,	"img14.png",	"Podcast",	NULL,	7.25);
INSERT INTO Podcast values("Rilassamento",	"Gaia Nozza",	"Alleggerisci la tua mente, diminuisci lo stress e impara a governare le tue emozioni. Il contenuto dei podcast è indicato per tutti coloro che si sentono appesantiti da pensieri e preoccupazioni. Io sono Gaia, Counselor laureata in filosofia ex ansiosa, e ho pensato a come potevo essere utile a tutti quelli che non hanno ancora cacciato la loro ansia, ecco perché è nato il podcast.Scrivimi su gaianozza.itseguimi su instagram @gazzanoia",	10.00,	10	,	"img15.png",	"Podcast",	NULL,	5.15);
INSERT INTO Podcast values("Muschio Selvaggio",	"Fedez e Luis Sal",	"Podcast dedicato a temi di cultura e società con ospiti diversi ad ogni puntata. È caratterizzato da momenti di approfondimento, serietà, gioco e imprevedibilità.",	70.00	,41	,	"img13.png",	"Podcast",	NULL,	6.25);
INSERT INTO Podcast values("The Essential",	"Will Media e Mia Ceran",	"The Essential è il podcast quotidiano di Will. In un mondo in cui spazio e tempo
sono stati stravolti e ne abbiamo sempre meno di entrambi, esploriamo il mondo un podcast di 5 minuti.
Notizie scelte e raccontate ogni mattina da Mia Ceran. The Essential mira a fornire l'essenziale del mondo
delle notizie, condensato in pochi minuti. Per gli studenti che non hanno tempo da perdere e per i giovani
professionisti che vogliono arricchire la loro giornata con il tempo di un commuting o di un caffè.",	50.00,	10,	"img11.png",	"Podcast",	NULL,	3.15);
INSERT INTO Podcast values("Serial Killers", "Parcast Network",	"Ogni lunedì e giovedì , Serial Killers adotta un approccio psicologico e divertente per fornire uno sguardo raro nella mente, nei metodi e nella follia dei serial killer più famosi con la speranza di comprendere meglio il loro profilo psicologico. Con l'aiuto di ricerche approfondite, approfondiamo le loro vite e le loro storie. Serial Killers è un originale Spotify di Parcast.", 50.00, 20,	"img12.png",	"Podcast",	NULL,	5.25);

CREATE TABLE Ascolta (
	Nickname varchar(50) not null,
    NomePodcast varchar(30) not null,
    primary key(Nickname,NomePodcast),
    foreign key(Nickname) references AccountUtente(Nickname),
    foreign key(NomePodcast) references Podcast(NomePodcast)
    );
    

/*INSERT INTO Ascolta values("m.coiro",	"Audio Racconti");