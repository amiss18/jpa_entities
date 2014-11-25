
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package gui.peche;

//~--- non-JDK imports --------------------------------------------------------

import beans.ClientBean;
import beans.EspeceBean;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.util.Callback;

import model.Client;
import model.Peche;
import model.TypeClient;

//~--- JDK imports ------------------------------------------------------------

import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.DateCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import outils.DateValidator;

/**
 * FXML Controller class
 *
 * @author armel
 */
public class DetailsReleveController implements Initializable {

    // Reference to the main application
    private MainApp                    mainApp;
    @FXML
    private Label                      dateReleve;
    @FXML
    private Label                      releve;
    @FXML
    private Label                      telLabel;
    @FXML
    private Text                       societe;
    @FXML
    private AnchorPane                 activitePane;
    @FXML
    private TableView<Peche>           releveeTable;
    @FXML
    private TableColumn<Peche, Date> dateColumn;
    @FXML
    private TableColumn<Peche, Double> quantiteColumn;
    @FXML
    private Text                       numAutorisationText;
    @FXML
    private Text                       contactText;
    @FXML
    private Label                      dateAutorisationLabel;
    @FXML
    private DatePicker                 dateRelveLabel;
    private ObservableList<Peche>      listePeche;
    private ClientBean cliBean;
    private Peche pe;
    @FXML
     private Label      msgErreur;
     @FXML
     private Label      dateErreur;
      @FXML
     private Label      quantiteErreur;
        private final String pattern = "dd-MM-yyyy";
    
@FXML
private TextField quantiteTextField;
    @FXML
    private Label                      typeSociete;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        this.releveeTable.setItems(mainApp.getListePeche());

        for (Peche p : mainApp.getListePeche()) {
          //  System.out.println("auto ID= " + p.getAutorisation().getId());
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        remplirCellule();
       // dateRelveLabel.setPromptText("dd/mm/aaaa");
        //  dateRelveLabel.getConverter().fromString("yyyy-MM-dd");
           selection() ;
           
        formatteDate();
        
    }
    
    
    

    public void remplirCellule() {
        this.releveeTable.setEditable(true);
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("datePeche"));
        /*dateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Peche, String>,
                ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Peche, String> cell) {
                final Peche                        record       = cell.getValue();
                final SimpleObjectProperty<String> simpleObject =
                   new SimpleObjectProperty<String>("" + outils.Dates.convertDateToDateFr(record.getDatePeche()));

                return simpleObject;
            }
        });*/
        dateFr();

        
                quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
             //createSimpleFirstNameColumn();
                          getQota();
                           
/*
      quantiteColumn.setCellValueFactory(new Callback<CellDataFeatures<Peche, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Peche, String> p) {
         System.out.println("edit : "+p.getValue().getQuantite());
         return new ReadOnlyObjectWrapper(p.getValue().getQuantite());
     }
  });
        */             
    }    
    
     private TableColumn createSimpleFirstNameColumn() {
        quantiteColumn.setMinWidth(100);
        quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
      //  quantiteColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        quantiteColumn.setCellFactory(TextFieldTableCell.<Peche, Double>forTableColumn(new DoubleStringConverter()));
        quantiteColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Peche, Double>>() {
            
            @Override
            public void handle(TableColumn.CellEditEvent<Peche, Double> t) {
              //  t.getRowValue().setNom(t.getNewValue());
             //   clientBean.modifierEspece(t.getRowValue().getId(),t.getNewValue());
                System.out.println("modifié, nvelle val = " +t.getNewValue());
                            
                
            }
        });

        return quantiteColumn;
    }
    
   
     public void inputFormatDate(){
       // dateRelveLabel.setDayCellFactory( new CallBack<DatePicker, DateCell> value)
      //  dateRelveLabel.setDayCellFactory(null);
     }
     
   public void dateFr(){
          this.cliBean = new ClientBean();
       dateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Date>() {

            @Override
            public String toString(Date t) {
                if (t==null) {
                       System.out.println("vide");
                 //   msgErreur.setText("Veuillez entrer une date valide, exemble:" + outils.Dates.convertDateToDateFr(new Date()));
                    return "" ;
                } else {
                    
            //  System.out.println("A modifier="+ outils.Dates.convertDateToDateFr(t));
		return outils.Dates.convertDateToDateFr(t);
                }
            }
            /*@param string la date en fr
             @return Date 
            */
            @Override
            public Date fromString(String dateFr) {
               if(verifierDate(dateFr)){ 
                   cliBean.modifierDatePeche( selection().getId(), outils.Dates.convertDateFrToUs(dateFr));
                System.out.println("**A été modifié="+dateFr);
            return outils.Dates.convertDateFrToUs(dateFr);
               }
             return null;

            }

        }));


   }
     
     
   public void getQota(){
 NumberFormat nf = NumberFormat.getInstance(Locale.FRENCH);
   this.cliBean = new ClientBean();
 //dateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Date>()
 quantiteColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
//retourne un decimal formatté fr
     @Override
     public String toString(Double nb) {
         if (nb ==null || nb==0){
             
             return "0";
         }else{
           
         return ""+outils.Nombre.outPutNumber(nb);//
         }
     }
  //nombre(double) à édité
     @Override
     public Double fromString(String string) {
    //     System.out.println("new val = " + outils.Nombre.formatNumber(string));
         
     //  System.out.println(" *** Id = " + selection() .getAutorisation().getId());
       Double q = null;
       if( verifierQuota( selection() .getAutorisation().getId(),outils.Nombre.intPutNumber(string) )) { 
         q=outils.Nombre.intPutNumber(string);
         //mise à jour de la quantité issue d'une activité(pêche,import,vente)
         cliBean.modifierQuantiteCumulee(selection().getId(), q);
          System.out.println(" *** val modifiée = " + q);
       }
     return q;
      //return outils.Nombre.intPutNumber(string);
     }
 }));
 /*
 quantiteColumn.setEditable(true);
       quantiteColumn.setCellFactory(column -> {
            return new TableCell<Peche, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                        
                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                              setText("" + nf.format(item));
                      // setText("" + item);
                    
                    }
                }
            };
        });
*/

   }
    public void releves(Client client) {
        Label       sLabel     = new Label();
        Peche       peche      = new Peche();
        EspeceBean  especeBean = new EspeceBean();
        List<Peche> liste      = especeBean.findRelevesByAutorisation(client.getAutorisation().getId());
        VBox        vbox       = new VBox();

        societe.setText("" + societe.getText() + "" + client.getRaisonSociale());

        // le client a 1 seul statut: armateur, ou importateur,...
        if (client.getTypeClients().size() == 1) {
            typeSociete.setText(client.getTypeClients().get(0).getType());
        }

        // le client jouit de plusieurs statust
        if (client.getTypeClients().size() > 1) {
            String affiche = "";

            for (TypeClient c : client.getTypeClients()) {
                affiche += c.getType() + " | ";
            }

            // affiche du statut de la société, on enlève 2 carct. à la fin(espace,|)
            typeSociete.setText(affiche.substring(0, affiche.length() - 2));
        }

        this.numAutorisationText.setText(client.getAutorisation().getNumero());
        this.dateAutorisationLabel.setText(
            "" + outils.Dates.convertDateToDateFr(client.getAutorisation().getDateAutorisation()));
        contactText.setText(client.getNom());
        telLabel.setText(printPhone(client));
        sLabel.setText(client.getVille());
        this.dateReleve.setText(client.getVille());
    }
/*
    private TableColumn editionColumn() {
        this.releveeTable.setEditable(true);
quantiteColumn.setMinWidth(100);
        
        
        quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
      //  quantiteColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        
       
        return quantiteColumn;
    }
*/
    // affiche les numeros d'un client
    public String printPhone(Client client) {
        String phone;

        if ((client.getTel() != null) && (client.getPortable() != null) && (client.getPortable2() != null)) {
            phone = client.getTel() + "|" + client.getPortable() + "|" + client.getPortable2();
        } else if ((client.getTel() == null) && (client.getPortable() != null) && (client.getPortable2() != null)) {
            phone = client.getPortable() + "|" + client.getPortable2();
        } else if ((client.getTel() == null) && (client.getPortable() != null) && (client.getPortable2() == null)) {
            phone = client.getPortable();
        } else if ((client.getTel() == null) && (client.getPortable() == null) && (client.getPortable2() != null)) {
            phone = client.getPortable2();
        } else {
            phone = client.getPortable();
        }

        return phone;
    }

    // enregistrer la quantité et la date relevé
    @FXML
    private void handleReleveSave() {}

    @FXML
    private void handleSave()  {
        System.out.println("action save");
       
        if(outils.Nombre.validateNumber(quantiteTextField.getText()) !=null 
                && (dateRelveLabel.getValue().toString().length()!=0 || dateRelveLabel.getValue().toString()!=null               )
                ){
             ClientBean client = new ClientBean();
         //  if(verifierEntrees(dateRelveLabel.getValue().toString(), quantiteTextField.getText())){
               System.out.println("datas is valide");
               String date=dateRelveLabel.getValue().toString();
              System.out.println(date+"\t dateSQL="+outils.Dates.convertStringToDate(date));
               String quantite=outils.Nombre.validateNumber(quantiteTextField.getText());
               System.out.println(outils.Nombre.intPutNumber(quantite));
               Long idAutorisation = mainApp.getListePeche().get(0).getAutorisation().getId();
                System.out.println("ID auto = " + idAutorisation);
                //la quantité à ajouter doit être <= à son quota restant
                if(this.verifierQuota(idAutorisation, outils.Nombre.intPutNumber(quantite) )) {
                //créeation d'une activité(pêche, ou importation, ou vente) 
                  Peche p =client.creerUneTourneeDePeche(idAutorisation, outils.Nombre.intPutNumber(quantite), outils.Dates.convertStringToDate(date));
                    mainApp.getListePeche().add(p);
                }
            }else{
            System.out.println("datas is not valide");
        }
        
        
    }

    @FXML
    private void handleDelete() {
    System.out.println("action delete");
    }
    
    public boolean verifierQuota( Long autorisationId, Double quantite ){
        this.cliBean = new ClientBean();
        msgErreur.setText(null);
        //quota pas encore atteint
        if(!cliBean.quotaAtteint(autorisationId)){
           //la quantité à ajouter depasse-t-elle son quota?
            if(cliBean.ajoutPossible(autorisationId, quantite)){//on peut ajouter
                //ajout possible
                //  cliBean.modifierQuantiteCumulee(11l, 78.50d, Dates.convertStringToDate("2014-11-20"));
                return true;
            }else{
                msgErreur.setText("Quantité superieure au quota");
                return false;
            }
            
        }else{
            this.msgErreur.setText("Vous avez déjà atteint votre quota");
            return false;
        }
    }
    
    public boolean verifierDate(String  d ){
          msgErreur.setText(null);
        DateValidator date = new DateValidator();
        if(!date.validate(d)){
           msgErreur.setText("Veuillez entrer une date valide, exemble:" + outils.Dates.convertDateToDateFr(new Date()));  
           return false;
        }
        return true;
    }
     public Peche selection() {
        releveeTable.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Peche> observableValue, Peche oldValue, Peche newValue) -> {
            //Check whether item is selected and set value of selected item to Label
            if (releveeTable.getSelectionModel().getSelectedItem() != null) {
              //  nomEspece.setText(null);
              //  System.out.println("#####peche id "+" , idPeche = " + newValue.getId() +"id auto =" +newValue.getAutorisation().getId());
              //  nomEspece.setText(newValue.getNom());
              //  this.setId(newValue.getId());
                pe= newValue;
            }
        });
        return pe;
    }
     //formatte la date de datepicker en format dd-mm-aaaa
    @SuppressWarnings({"unchecked", "unchecked"})
     public void formatteDate(){
        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = 
                DateTimeFormatter.ofPattern(pattern);
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                      
                    return null;
                }
            }
        };             
        dateRelveLabel.setConverter(converter);
        dateRelveLabel.setPromptText("jj-mm-aaaa"); 
     }
     
     public boolean verifierEntrees( String date, String nombre ){
          this.dateErreur.setText(null);
      /*   if( (!outils.Dates.isThisDateValid(date, outils.Dates.paternUS) ||date==null)
                 && outils.Nombre.intPutNumber(nombre)==null
                 ){*/
          if( (date==null || date.equals(""))
                 && (nombre==null || nombre.equals(""))
                 ){
             this.dateErreur.setText("Veuillez entrer une date valide");
             this.quantiteErreur.setText("Veullez entrer un nombre valide");
             return false;
         }
             
         return true;
     }
    
}


//~ Formatted by Jindent --- http://www.jindent.com
