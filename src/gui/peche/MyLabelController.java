/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.peche;

import beans.ClientBean;
import beans.EspeceBean;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.Autorisation;
import model.Client;
import model.Espece;
import model.TypeClient;
import outils.Nombre;

/**
 *
 * @author armel
 */
public class MyLabelController {
    
    private MainApp mainApp;
    @FXML
    private Label myLabel;
    @FXML
    private ListView<String> listView;
    @FXML
    private Button btnNewQuota;
    
    @FXML
    private TableView<Client> clientTable;
    @FXML
    private TableColumn<Client, String> societeColumn;
     @FXML
    private TableColumn<Client, String> autorisationColumn;
      @FXML
    private TableColumn<Client, String> quotaColumn;
   @FXML
   private Label firstNameLabel;
   @FXML
   private ProgressIndicator progressBar;
   
   @FXML
   private Label quotaLabel;
     @FXML
   private Label autorisationLabel;
    @FXML
   private Label typeSocieteLabel;
      @FXML
   private Label villeLabel;
   
   @FXML
   private Label titreSociete;
    @FXML
   private Label laDateAutorisationLabel;
   @FXML
   private Label dateAutorisationLabel;
    @FXML
   private Label quantiteCumuleeLabel;
     @FXML
   private Label dernierReleveLabel;
   
     private ObservableList<String> list = FXCollections.observableArrayList();  
    
     public MyLabelController(){
        
     }
     
    public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
            //  listView.setItems(mainApp.getList());
                this.clientTable.setItems(mainApp.getListeClients());
	}
    
    @FXML
    private void initialize() {
        
        remplirTable();
           selection();

      this.myLabel.setText("encore du texte");
      this.btnNewQuota.setText("new");
       listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
       listView.setOnMouseClicked( new EventHandler<Event>() {
            @Override
                    public void handle(Event event) {
                        ObservableList<String> selectedItems =  listView.getSelectionModel().getSelectedItems();

                        for(String s : selectedItems){
                            System.out.println("selected item " + s);
                        }
                        
                    } 
       });
       
       
       //selection
       
        clientTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
   
   
       
    }
    
    
    public void remplirTable(){
       // quotaColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        // Initialize the person table
     societeColumn.setCellValueFactory(new PropertyValueFactory<>("raisonSociale"));
    autorisationColumn.setCellValueFactory(new PropertyValueFactory<>("autorisation"));
 // quotaColumn.setCellValueFactory(new PropertyValueFactory<>(c.getAutorisation().getNumero()));
  //    //nom.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
   //   quotaColumn.setCellValueFactory(new PropertyValueFactory<>("autorisation") );
   
    
quotaColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Client,String>, ObservableValue<String>>() {

    public ObservableValue<String> call(CellDataFeatures<Client, String> cell) {
        final Client record = cell.getValue();

     final SimpleObjectProperty<String> simpleObject = new SimpleObjectProperty<String>(""+record.getAutorisation().getQuota() );
    return simpleObject;
    }
});
		// Auto resize columns
  
   
 }
    
        public void selection() {
            ClientBean clientBean=new ClientBean();
            EspeceBean especeBean=new EspeceBean();
            this.titreSociete.setText(null);
            firstNameLabel.setText(null);
           this.quotaLabel.setText(null);
           // laDateAutorisationLabel.setText(null);
             dateAutorisationLabel.setText(null);
          this.autorisationLabel.setText(null);
          quantiteCumuleeLabel.setText(null);
          this.typeSocieteLabel.setText(null);
          dernierReleveLabel.setText(null);
        clientTable.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Client> observableValue, Client oldValue, Client newValue) -> {
            //Check whether item is selected and set value of selected item to Label
            if (clientTable.getSelectionModel().getSelectedItem() != null) {
               // nomEspece.setText(null);
                firstNameLabel.setText(newValue.getNom());
                this.titreSociete.setText(newValue.getRaisonSociale());
                quotaLabel.setText(""+newValue.getAutorisation().getQuota());
                this.titreSociete.autosize();
                this.autorisationLabel.setText(newValue.getAutorisation().getNumero());
                this.villeLabel.setText(newValue.getVille());
                //un seul type de client
                if(newValue.getTypeClients().size() == 1){
                    this.typeSocieteLabel.setText(newValue.getTypeClients().get(0).getType());  
                }
                
                if(newValue.getTypeClients().size() > 1){
                    String affiche="";
                    // affiche = newValue.getTypeClients().stream().map((c) -> c.getType() + "|").reduce(affiche, String::concat);
                    for(TypeClient c : newValue.getTypeClients() ){
                       affiche+=c.getType() + " | ";
                    }
                  //  if(affiche.endsWith("|"))
                     this.typeSocieteLabel.setText( affiche.substring(0,affiche.length()-2 ));  
                       
                }
             
               
                dateAutorisationLabel.setText(outils.Dates.convertDateToDateFr(newValue.getAutorisation().getDateAutorisation()));
               Double quantityPerQuota=Nombre.arrondir(clientBean.totalPeche(newValue.getAutorisation().getId())/newValue.getAutorisation().getQuota(),2);
               this.progressBar.setProgress(quantityPerQuota);
            
               
               
               quantiteCumuleeLabel.setText(""+clientBean.totalPeche(newValue.getAutorisation().getId()));
               
               dernierReleveLabel.setText(""+ outils.Dates.convertDateToDateFr(especeBean.findRelevesByAutorisation(newValue.getAutorisation().getId()).get(0).getDatePeche()));
                System.out.println(" quantité pêchée = " + Nombre.arrondir(clientBean.totalPeche(newValue.getAutorisation().getId())/newValue.getAutorisation().getQuota(),1));
              //  nomEspece.setText(newValue.getNom());
               // this.setId(newValue.getId());
            }
        });

    }

        @FXML
	private void handleVoirReleve() {
            Label sLabel;
         if (clientTable.getSelectionModel().getSelectedItem() != null) {
        Client client=   this.clientTable.getSelectionModel().getSelectedItem();
         mainApp.setListPeche(client);
            this.mainApp.voirReleves(client);
           
            System.out.println("size liste peche.="+mainApp.getListePeche().size());
             }else{
                System.out.println("aucune societé selectionnée");  
             }
        }
    
    
       @FXML
	private void handleNewQuota() {
             throw new UnsupportedOperationException("Not supported yet.");
             
        }
        
        @FXML
	private void handleSupprimerClient() {
             throw new UnsupportedOperationException("Not supported yet.");
             
        }
    
    
}//end class
