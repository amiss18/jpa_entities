
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package gui.peche;

//~--- non-JDK imports --------------------------------------------------------

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

//~--- JDK imports ------------------------------------------------------------

import java.net.URL;

import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Espece;

/**
 * FXML Controller class
 *
 * @author armel
 */
public class ModalViewController {    // implements Initializable {

   private Stage dialogStage;
	private Espece espece;
	private boolean okClicked = false;
        @FXML
        private Label res;
        
          @FXML
        private Label row;
        private MainApp mainApp;

    public MainApp getMainApp() {
        return mainApp;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public Label getRes() {
        return res;
    }

    public void setRes(Label res) {
        this.res = res;
    }
    
    /**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		
	}
        
        
	/**
	 * Sets the stage of this dialog.
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
                
	}
        
        /**
	 * Returns true if the user clicked OK, false otherwise.
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}
	
	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
            System.out.println("cliqué sur OK**.");
            okClicked = true;
      //      especeTable.getItems().remove(selectedIndex);
           int ligne = Integer.parseInt(row.getText());
          mainApp.getListeEspece().remove(ligne);
	 dialogStage.close();
		
	}
	
	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
              System.out.println("Not supported yet.");
		dialogStage.close();
	}

    public Espece getEspece() {
        return espece;
    }

    public void setEspece(Espece espece, int ii) {
        this.espece = espece;
        this.res.setText(""+espece.getId());
                row.setText(""+ii);
        
    }

    public Label getRow() {
        return row;
    }

    public void setRow(Label row) {
        this.row = row;
    }
        
   

    
    
    
    
    
}


