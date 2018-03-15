/**
 * Sample Skeleton for 'IndoNumero.fxml' Controller Class
 */

package it.polito.tdp.indonumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class IndoNumeroController {
	
	private int NMAX = 100 ;
	
	// numero minimo di tentativi per indovinare un numero da 1 a 200
	private int TMAX = 7 ;  //log in base 2 di 100 = 7 circa 
	
	private int segreto ; // numero da indovinare
	private int tentativi ; // tentativi già fatti

	// STATO DEL GIOCO DA GESTIRE
	private boolean inGame = false ; // partita in corso o no?
	
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnNuova"
    private Button btnNuova; // Value injected by FXMLLoader

    @FXML // fx:id="txtCur"
    private TextField txtCur; // Value injected by FXMLLoader

    @FXML // fx:id="txtMax"
    private TextField txtMax; // Value injected by FXMLLoader

    @FXML // fx:id="boxGioco"
    private HBox boxGioco; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativo"
    private TextField txtTentativo; // Value injected by FXMLLoader

    @FXML // fx:id="txtLog"
    private TextArea txtLog; // Value injected by FXMLLoader

    @FXML
    void handleNuova(ActionEvent event) {
    	
    	// TRONCARE IL NUMERO DOUBLE CHE VA DA 0 A 1, PERCIO' 
    	// MOLTIPLICARE PER NMAX E AGGIUNGO 1 PER PARTIRE DA 1
    	this.segreto = (int) (Math.random() * NMAX) + 1 ;
    	
    	this.tentativi = 0 ;
    	this.inGame = true;
    	// LA PARTITA E' INIZIATA !!!
    	
    	btnNuova.setDisable(true);
    	boxGioco.setDisable(false);
    	txtCur.setText(String.format("%d", this.tentativi));
    	txtMax.setText(String.format("%d", this.TMAX));
    	txtLog.clear();
    	txtTentativo.clear();
    	
    	txtLog.setText(
    			String.format("Indovina un numero tra %d e %d\n", 1, NMAX));
    }

    @FXML
    void handleProva(ActionEvent event) {
    	
    	// Estrazione del numero inserito nel textField
    	String numS = txtTentativo.getText().trim() ;
    	
    	// Controllo se è stato inserito un numero, altrimenti termino il programma
    	if (numS.length() == 0) {
    		txtLog.appendText("Errore: occorre inserire un numero! \n");
    		return ;
    	}

    	
    	try {
    		
    		int num = Integer.parseInt(numS) ;	
    		// il numero era un intero
    		
    		if (num == segreto) {
    			// utente ha indovinato, terminare la partita
    			txtLog.appendText("Hai vinto!\n") ;
    			
    			// CHIUSURA PARTITA: per garantire all'utente di giocare nuovamente 
    			btnNuova.setDisable(false);
    			boxGioco.setDisable(true);
    			this.inGame = false;
    			
    		}else {
    			// utente ha sbagliato
    			
    			// incremento il numero di tentativi e aggiorno l'intefaccia
    			this.tentativi ++ ;
    			txtCur.setText(String.format("%d", this.tentativi));
    			
    			if (tentativi == TMAX) {
    				// PARTITA CONCLUSA CON UNA SCONFITTA
    				txtLog.appendText(
    						String.format("Hai perso! Il numero era: %d\n", segreto)) ;
    				
    				// CHIUSURA PARTITA: per garantire all'utente di giocare nuovamente 
    				btnNuova.setDisable(false);
        			boxGioco.setDisable(true);
        			this.inGame = false;
    			}
    			
    			else {
    				
    				// PARTITA CONTINUA CON UNA NUOVA PROVA, CON DEI SUGGERIMENTI
    				if (num < segreto) 
    					txtLog.appendText("Troppo basso\n");
    				else 
    					txtLog.appendText("Troppo alto\n");
    			
    			}
    		}
    		
    	}
    	catch (NumberFormatException e) {
    		txtLog.appendText("Il dato inserito non è numerico\n");
    		return ; 
    	}
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtCur != null : "fx:id=\"txtCur\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtMax != null : "fx:id=\"txtMax\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert boxGioco != null : "fx:id=\"boxGioco\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtLog != null : "fx:id=\"txtLog\" was not injected: check your FXML file 'IndoNumero.fxml'.";

    }
}
