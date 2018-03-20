package it.polito.tdp.indonumero;

import java.security.InvalidParameterException;

public class Model {
	
	private int NMAX = 100 ;
	private int TMAX = 7 ;  //log in base 2 di 100 = 7 circa 
	
	private int segreto ; // numero da indovinare
	private int tentativi ; // tentativi già fatti

	// STATO DEL GIOCO DA GESTIRE
	private boolean inGame; // partita in corso o no?
	
	//COSTRUTTORE che deve inizializzare le variabili
	public Model () {
		
		this.inGame = false ;
	}

	/**
	 * Avvia una nuova partita, generando un nuovo numero segreto
	 */
	
	public void newGame() {
    	
    	// TRONCARE IL NUMERO DOUBLE CHE VA DA 0 A 1, PERCIO' 
    	// MOLTIPLICARE PER NMAX E AGGIUNGO 1 PER PARTIRE DA 1
    	this.segreto = (int) (Math.random() * NMAX) + 1 ;
    	
    	this.tentativi = 0 ;
    	this.inGame = true;
	}
	
	 /**
	  * Fai un tentativo di indovinare il numero segreto
	  * @param t valore numerico del tentativo
	  * @return 0 se indovinato, +1 se troppo alto, -1 se troppo basso
	  */
	
	public int tentativo(int t) {
		// ci pensa il controller a stabilire se siamo in gioco o no
		if (!inGame)
			throw new IllegalStateException ("Partita non attiva") ;
	
		// regola di validità del dato
		if (!valoreValido(t))
			throw new InvalidParameterException("Tentativo fuori range") ;
		
		this.tentativi ++ ;
		if (this.tentativi == TMAX)
			this.inGame = false; // la partita si è conclusa
		
		if (t == this.segreto) {
			this.inGame = false; // la partita si è conclusa
			return 0;
		}
		
		if (t < this.segreto)
			return -1;
		
		return 1;
	}
	
	/**
	 * Controlla se il tentantivo fornito rispetta le regole formali del gioco.
	 * cioè è nel range [1, NMAX]
	 * @param tentativo
	 * @return {@code true} se il tentativo è valido
	 */
	public boolean valoreValido (int tentativo) {
		return tentativo >= 1 && tentativo <= this.NMAX;
	}
	
	public boolean isInGame() {
		return inGame;
	}

	public int getTentativi () {
		return tentativi;
	}

	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	public int getSegreto () {
		return segreto;
	}
	
	
}
