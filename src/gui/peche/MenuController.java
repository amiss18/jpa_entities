/*
 *controlleur principal :menu de l'appli
 */
package gui.peche;

import javafx.fxml.FXML;

/**
 *
 * @author armel
 */
public class MenuController {
    
    // Reference to the main application
	private MainApp mainApp;

	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
        
      
        
        @FXML
	private void handleNouveauClient() {
            System.out.println("nouveau clien");
            // throw new UnsupportedOperationException("Not supported yet.");
        }
    
         @FXML
	private void handleListerClients() {
            System.out.println("Not supported yet.");
        }
        
        @FXML
	private void handleCreerEspece() {
         //   System.out.println("Not supported yet.");
            this.mainApp.especes();
           //  throw new UnsupportedOperationException("Not supported yet.");
        }
        
          @FXML
	private void handleListerEspece() {
           //System.out.println("Not supported yet.");
              this.mainApp.especes();
        }
        
        @FXML
	private void handleFaq() {
            System.out.println("Not supported yet.");
        }
        
           @FXML
	private void handleListerFaq() {
           System.out.println("Not supported yet.");
        }
      
}
