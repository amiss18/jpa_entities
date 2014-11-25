
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package gui.peche;

//~--- non-JDK imports --------------------------------------------------------

import beans.ClientBean;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

//~--- JDK imports ------------------------------------------------------------

import java.net.URL;

import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Espece;

/**
 * FXML Controller class
 *
 * @author armel
 */
public class EspeceViewController  {//implements Initializable {

    /**
     * Initializes the controller class.
     */

    // Reference to the main application
    private MainApp mainApp;
    @FXML
     private TableView<Espece> especeTable;
    @FXML
    private TableColumn<Espece, String> nom;

    
    
    @FXML
    private TextField nomEspece;
    
    private Long id; //id de l'espece
    private ClientBean clientBean;
    
    
    public EspeceViewController() {
       clientBean =new ClientBean();
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        this.especeTable.setItems(mainApp.getListeEspece());
    }

  
    @FXML
    private void handleAccueil() {

        // System.out.println("Not supported yet.");
        this.mainApp.start(this.mainApp.getPrimaryStage());
    }

    @FXML
    private void handleSaveEspece() {
       
        if( !nomEspece.getText().equals("") &&  nomEspece.getText()!=null){
            Espece  espece=new Espece();
            espece.setNom( nomEspece.getText());
            clientBean.creerEspece(espece);
            clientBean.closeConnection();
            refreshPersonTable();
            mainApp.getListeEspece().add(espece);
            nomEspece.setText("");
            System.out.println("enregistrement de + " + nomEspece.getText() + " effectué");
        }else{
          //  System.out.println("maj de + " + nomEspece.getText() + ", " + getId() + " effectué");
              System.out.println(" Ce champ est requis");
        }
            
    }

    @FXML
    private void handleDeleteEspece() {
        System.out.println("Not supported yet.");
   int selectedIndex = especeTable.getSelectionModel().getSelectedIndex();
        Espece selectedEspece= especeTable.getSelectionModel().getSelectedItem();
    if (selectedEspece!= null) {
      
        mainApp.showPersonEditDialog(selectedEspece, selectedIndex);
			//especeTable.getItems().remove(selectedIndex);
		}
    }
  
   @FXML
   private void initialize() {
       this.remplirCellule();
       selection();
   }
    
    
     public void remplirCellule() {

   //  nom.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
           
       nom.setCellValueFactory(
                new PropertyValueFactory<>("nom")
        );

        
      createSimpleFirstNameColumn();

       
       

    }//fin
     
     
     /*
     * modification du nom de l'espèce à partir de la cellule
     */
      private TableColumn createSimpleFirstNameColumn() {
     
        nom.setMinWidth(100);
        this.especeTable.setEditable(true);
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nom.setCellFactory(TextFieldTableCell.forTableColumn());
        nom.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Espece, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Espece, String> t) {
                t.getRowValue().setNom(t.getNewValue());
                clientBean.modifierEspece(t.getRowValue().getId(),t.getNewValue());
               // System.out.println("modifié, nvelle val = " +t.getRowValue().getId());
            }
        });

        return nom;
    }

    
     
      public void selection() {
          Espece espece=null;
        especeTable.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Espece> observableValue, Espece oldValue, Espece newValue) -> {
            //Check whether item is selected and set value of selected item to Label
            if (especeTable.getSelectionModel().getSelectedItem() != null) {
                nomEspece.setText(null);
              //  System.out.println(newValue.getNom() +" , id = " + newValue.getId());
              //  nomEspece.setText(newValue.getNom());
                this.setId(newValue.getId());
            }
        });

    }
     
      
   /**
	 * Refreshes the table. This is only necessary if an item that is already in
	 * the table is changed. New and deleted items are refreshed automatically.
	 * 
	 * This is a workaround because otherwise we would need to use property
	 * bindings in the model class and add a *property() method for each
	 * property. Maybe this will not be necessary in future versions of JavaFX
	 * (see http://javafx-jira.kenai.com/browse/RT-22599)
	 */
	private void refreshPersonTable() {
		int selectedIndex = especeTable.getSelectionModel().getSelectedIndex();
		especeTable.setItems(null);
		especeTable.layout();
		this.especeTable.setItems(mainApp.getListeEspece());
		
		especeTable.getSelectionModel().select(selectedIndex);
	}  
      
    
}


