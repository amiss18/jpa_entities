
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package gui.peche;

//~--- non-JDK imports --------------------------------------------------------

import beans.ClientBean;
import beans.EspeceBean;
import javafx.application.Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;

//~--- JDK imports ------------------------------------------------------------

import java.io.IOException;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import model.Autorisation;
import model.Client;
import model.Espece;
import model.Peche;

/**
 *
 * @author armel
 */

public class MainApp extends Application {
    

   // private ObservableList<String> list = FXCollections.observableArrayList();
    private Stage                  primaryStage;
    private BorderPane             rootLayout;
    private ObservableList<Espece> listeEspece; //= FXCollections.observableArrayList();
     private ObservableList<Client> listeClients;
     private ObservableList<Peche> listePeche;
    private EspeceBean especeBean;
    private ClientBean clientBean;
    
    public MainApp() {
       /* for (int i = 1; i < 35; i++) {
            list.add("item " + i);
        }*/
         especeBean=new EspeceBean();
         clientBean = new ClientBean();
        listeEspece = FXCollections.observableArrayList(especeBean.findAllEspeces());
        listeClients=  FXCollections.observableArrayList(clientBean.findAllCustumers());
        //this.listePeche =
       /*
        for(Espece e : especeBean.findAllEspeces()){
            System.out.println(e.getNom());
        }
        */
    }

    @Override
    public void start(Stage _primaryStage) {
        this.primaryStage = _primaryStage;
        this.primaryStage.setTitle("Relevé de pêche");
       

        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("views/menuFXML.fxml"));

            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);

            MenuController menuController = loader.getController();

            menuController.setMainApp(this);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("impossible de charger le fic menu");
        }

        showMsg();
    }

    public void showMsg() {
        try {
            // Load the fxml file and set into the center of the main layout
            FXMLLoader loader       = new FXMLLoader(MainApp.class.getResource("views/myLabel.fxml"));
           AnchorPane overviewPage = (AnchorPane) loader.load();
           
          rootLayout.setCenter(overviewPage);
             //
            // Give the controller access to the main app
            MyLabelController controller = loader.getController();

            controller.setMainApp(this);
        } catch (IOException e) {

            System.out.println("impossible de charger le fic label");
        }
    }
    
    
    
    
    
    
    
    public void especes() {
        try {
            // Load the fxml file and set into the center of the main layout
            FXMLLoader loader       = new FXMLLoader(MainApp.class.getResource("views/EspeceView.fxml"));
            AnchorPane overviewPage = (AnchorPane) loader.load();

            rootLayout.setCenter(overviewPage);
           // overviewPage.getChildren().add(new Text("fenêtre espèce"));
           primaryStage.setTitle("autre fenêtre");
            // Give the controller access to the main app
            EspeceViewController controller = loader.getController();

            controller.setMainApp(this);
            
          //  primaryStage.show();
        } catch (IOException e) {

            // e.printStackTrace();
            System.out.println("impossible de charger le fic espece");
        }
    }

    
    /**
	 * Opens a dialog to edit details for the specified person. If the user
	 * clicks OK, the changes are saved into the provided person object and
	 * true is returned.
	 * 
	 * @param person the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showPersonEditDialog(Espece espece, int row ) {
		try {
			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("views/ModalView.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Person");
			dialogStage.initModality(Modality.WINDOW_MODAL);
		//	dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			//listeEspece.remove(espece);
			// Set the person into the controller
			ModalViewController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setEspece(espece,row);
			
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			
			return controller.isOkClicked();
			
		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			//e.printStackTrace();
			return false;
		}
	}
        
    
        
   //details sur les relevés d'activités(pêche, importation, exportation...)
     public void voirReleves(Client client) {
        try {
                 //    setListPeche(client) ;
                   // System.out.println("view main");

            // Load the fxml file and set into the center of the main layout
            FXMLLoader loader       = new FXMLLoader(MainApp.class.getResource("views/detailsReleve.fxml"));
           AnchorPane overviewPage = (AnchorPane) loader.load();
          rootLayout.setCenter(overviewPage);
                      primaryStage.setTitle("Details des activités");

            // Give the controller access to the main app
            DetailsReleveController controller = loader.getController();
             controller.releves(client);            

            controller.setMainApp(this);           

        } catch (IOException e) {

            System.out.println("impossible de charger le fichier detailsRelevé");
        }
    }
     
    public static void main(String[] args) {
        launch(args);
        
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /*
    public ObservableList<String> getList() {
        return list;
    }

    public void setList(ObservableList<String> list) {
        this.list = list;
    }
*/
     public ObservableList<Peche> getListePeche() {
         return listePeche;
     }
    public void setListPeche(Client client) {
        this.listePeche = FXCollections.observableArrayList( 
                especeBean.findRelevesByAutorisation(client.getAutorisation().getId()
                     ));
    }
    
    public ObservableList<Espece> getListeEspece() {
        return listeEspece;
    }

    public void setListeEspece(ObservableList<Espece> listeEspece) {
        this.listeEspece = listeEspece;
    }

    public EspeceBean getEspeceBean() {
        return especeBean;
    }

    public void setEspeceBean(EspeceBean especeBean) {
        this.especeBean = especeBean;
    }

    public ObservableList<Client> getListeClients() {
        return listeClients;
    }

    public void setListeClients(ObservableList<Client> listeClients) {
        this.listeClients = listeClients;
    }
    
    
}


