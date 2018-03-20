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

	private Model model;


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
    	
    	model.newGame();
    	// LA PARTITA E' INIZIATA !!!
    	
    	btnNuova.setDisable(true);
    	boxGioco.setDisable(false);
    	txtCur.setText(String.format("%d", model.getTentativi()));
    	txtMax.setText(String.format("%d", model.getTMAX()));
    	txtLog.clear();
    	txtTentativo.clear();
    	
    	txtLog.setText(
    			String.format("Indovina un numero tra %d e %d\n", 1, model.getNMAX()));
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
    		
    		//fuori dall'intervallo
    		if (!model.valoreValido(num)) {
    			txtLog.appendText("Valore fuori dall'intervallo consentito\n");
    			return ;
    		}
    		
    		int risultato = model.tentativo(num);
    		txtCur.setText(String.format("%d", model.getTentativi()));
    		
    		if (risultato == 0)
    			txtLog.appendText("Hai vinto!\n");
    		else if (risultato < 0)
    			txtLog.appendText("Troppo basso\n");
    		else
    			txtLog.appendText("Troppo alto\n");
    		
    		if (!model.isInGame()) {
    			// la partita si è conclusa
    			if (risultato != 0)
    				txtLog.appendText("Hai perso!\n");
    				txtLog.appendText(String.format("Il numero segreto era: %d\n",
    												model.getSegreto()));

    			// CHIUSURA PARTITA: per garantire all'utente di giocare nuovamente 
    			btnNuova.setDisable(false);
    			boxGioco.setDisable(true);
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
    
	
    public void setModel(Model model) {
		this.model = model;
	}
    
}
