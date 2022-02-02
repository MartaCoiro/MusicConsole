package it.unisa.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import Class.Carrello;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import Class.Carta;
import it.unisa.model.CarrelloModelDS;
import Class.Magazzino;
import it.unisa.model.CartaModelDS;
import Class.Ordini;
import it.unisa.model.MagazzinoModelDS;
import it.unisa.model.OrdiniModelDS;
import it.unisa.utils.Utility;

@WebServlet("/ServletSped")
public class ServletSped extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		DataSource ds = (DataSource)getServletContext().getAttribute("DataSource");
		
		response.setContentType("text/html");//tipo di file
		
		CarrelloModelDS model = new CarrelloModelDS(ds);
		OrdiniModelDS model1 = new OrdiniModelDS(ds);
		CartaModelDS model2 = new CartaModelDS(ds);
		MagazzinoModelDS model3 = new MagazzinoModelDS(ds);
		
		HttpSession currentSession = request.getSession();
		String ut = (String)currentSession.getAttribute("acc");
		
		ArrayList<String> ca = new ArrayList<String>();
		Ordini o = new Ordini();
		int in;
		int max=0;
		
		String memCart = request.getParameter("conserva");//recupero la deci sione dell'utente se vuole o meno memorizzare i dati della carta
		String nome = request.getParameter("cardname");
		String cognome = request.getParameter("cardsurname");
		String numeroC = request.getParameter("cardnumber");
		String data = request.getParameter("expmonth");
		String vv = request.getParameter("cvv");
		int cvv = Integer.parseInt(vv);
		
		Carta card = new Carta();
		card.setNome(nome);
		card.setCognome(cognome);
		card.setNumero(numeroC);
		card.setDataa(data);
		card.setCvv(cvv);
		card.setUtente(ut);
		
		try {
			
			//memorizzazione dati carta
			if(memCart!=null) {
				Collection <Carta> c = model2.doRetrieveAll();
				for(Iterator<Carta> i = c.iterator();i.hasNext();) {
					Carta ele = (Carta)i.next();
					if(ele.getUtente().equals(ut)) {
						model2.doDelete(ele.getCvv());
					}
					if(ele.getCvv()==cvv) {
						request.setAttribute("error", true);
						getServletContext().getRequestDispatcher("/ServletCarta").forward(request, response);
						return;
					}
				}
				
				model2.doSave(card);
			}
			else {
				Collection <Carta> c = model2.doRetrieveAll();
				for(Iterator<Carta> i = c.iterator();i.hasNext();) {
					Carta ele = (Carta)i.next();
					if((ele.getCvv()==cvv)&&(!ele.getUtente().equals(ut))) {
						request.setAttribute("error", true);
						getServletContext().getRequestDispatcher("/ServletCarta").forward(request, response);
						return;
					}
				}
			}
			
			//aggiunta del primo ordine alla tabella e svuotiamo il carrello
			Collection<Ordini> or = model1.doRetrieveAll();
				if(or.size()==0) {
					Collection<Carrello> el = model.RestXUtente(ut);//restituisce tutti i carrelli dell'utente in sessione
					for(Iterator<Carrello> i = el.iterator();i.hasNext();) {
						Carrello ele = (Carrello)i.next();
						o.setUtente(ut);
						o.setAutore(ele.getAutore());
						o.setNome(ele.getNome());
						o.setTipo(ele.getTipo());
						o.setCosto(ele.getCosto());
						o.setIndice(1);
						o.setStato("false");
						o.setQuantità(ele.getQuantità());
						float tot = (float)currentSession.getAttribute("tot");
						o.setTot(tot);
						model1.doSave(o);
						model.doDelete(ele.getCod());
						//diminuizione della disponibilità
						if((ele.getTipo().equals("CD"))||(ele.getTipo().equals("Vinile"))) {
						Collection<Magazzino> mag = model3.doRetrieveAll();
						for(Iterator<Magazzino> ii = mag.iterator();ii.hasNext();) {
							Magazzino m = (Magazzino)ii.next();
							if((m.getNome().equals(ele.getNome()))&&(m.getAutore().equals(ele.getAutore()))&&(m.getTipo().equals(ele.getTipo()))){
								model3.doUpdate((m.getQuantità()-ele.getQuantità()), m.getCod());
							}
						}
					}
				}
			}
				else {//aggiunta di altri ordini con svuotamento del carrello
					Collection<Ordini> indici = model1.getIndici();//assegnazione degli indici che indicano quali prodotti appartengono a quali ordini
					for(Iterator<Ordini> ii = indici.iterator();ii.hasNext();) {
						Ordini oo = (Ordini)ii.next();
						in = oo.getIndice();
						if(in>=max) {
						max=in;
						}
					}
					max++;
						Collection<Carrello> el = model.RestXUtente(ut);//restituisce tutti i carrelli dell'utente in sessione
						for(Iterator<Carrello> i = el.iterator();i.hasNext();) {
							Carrello ele = (Carrello)i.next();
							o.setIndice(max);
							o.setUtente(ut);
							o.setAutore(ele.getAutore());
							o.setNome(ele.getNome());
							o.setTipo(ele.getTipo());
							o.setCosto(ele.getCosto());
							o.setStato("falso");
							o.setQuantità(ele.getQuantità());
							float tot = (float)currentSession.getAttribute("tot");
							o.setTot(tot);
							model1.doSave(o);
							model.doDelete(ele.getCod());
							//diminuizione della disponibilità
							if((ele.getTipo().equals("CD"))||(ele.getTipo().equals("Vinile"))) {
							Collection<Magazzino> mag = model3.doRetrieveAll();
							for(Iterator<Magazzino> ii = mag.iterator();ii.hasNext();) {
								Magazzino m = (Magazzino)ii.next();
								if((m.getNome().equals(ele.getNome()))&&(m.getAutore().equals(ele.getAutore()))&&(m.getTipo().equals(ele.getTipo()))){
									model3.doUpdate((m.getQuantità()-ele.getQuantità()), m.getCod());
								}
							}
						}
					}
				}
				currentSession.setAttribute("carrello", ca);
				currentSession.setAttribute("conf", true);
				getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
			
		}
		catch(SQLException e){
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
			}
	
	}

}
