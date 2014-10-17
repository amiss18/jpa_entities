/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peche.pkg2;
import beans.BookBean;
import beans.ClientBean;
import java.util.Date;
import javax.persistence.*;
import model.Author;
import model.Book;
import model.*;
import org.eclipse.persistence.jpa.jpql.Assert;
import outils.Dates;

/**
 *
 * @author armel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

       // LOGGER.info("Debut de l'application");
        
        Date dateFr=Dates.convertDateFrToUs("18-06-2009"),
             dateUs = Dates.convertStringToDate("2014-09-27")  ;
         System.out.println("date du jour = "+ Dates.convertDateToDateFr(new Date()));
       
           // Creates an instance of book
        Book book = new Book();
        book.setTitle("The Hitchhiker's Guide to the Galaxy");
        book.setPrice(12.5d);
        book.setDescription("Science fiction comedy series created by Douglas Adams.");
        book.setIsbn("1-84023-742-2");
        book.setNbOfPage(354);
        book.setIllustrations(false);
        book.setDate(new Date());
        
        Book book1 = new Book();
        book1.setTitle("un bon but de klose");
        book1.setPrice(55.50d);
        book1.setDescription("demi finale de la coupe du monde 3 pour lallemagne.");
        book1.setIsbn("1-84023-742-2");
        book1.setNbOfPage(450);
        book1.setIllustrations(true);
        book1.setDate(dateUs);

	Book book2 = new Book();
        book2.setTitle("un titre :pêche");
        book2.setPrice(100.85d);
        book2.setDescription("projet sur la pêche55555.");
        book2.setIsbn("0-84023-742-88");
        book2.setNbOfPage(350);
        book2.setIllustrations(false);
        book2.setDate(new Date());
        
        //livre3
        Book book3 = new Book();
        book3.setTitle("le mapping objet-rélationnel");
        book3.setPrice(45.99d);
        book3.setDescription("très bon livre pour aprendre à les bds et java.");
        book3.setIsbn("9-8423-742-88");
        book3.setNbOfPage(650);
        book3.setIllustrations(false);
        book3.setDate(null);

        Author author = new Author("dupond", "jean", "Paris");
         Author author1 = new Author("durand", "jacques", "Lille");
        author.addBook(book);
        author.addBook(book1);
        author.addBook(book2);
        
        author1.addBook(book3);
 
        //un client
        Client client = new Client();
        client.setAdresse("24 rue de belle épine");
         client.setEmail("amel@jj.fr");
         client.setFax("033 1 88 77 44 11");
         client.setPortable("06 88 475 444");
         client.setPortable2("06 88 475 444");
         client.setRaisonSociale("impot");
         client.setTel("033 1 88 77 55 44");
         client.setVille("paris");
         client.setNom("Dupond Jean");
         
         //une autorisation
        Autorisation auto=new Autorisation();
        auto.setNumero("CG-78");
        auto.setDateAutorisation(dateUs);
       // auto.setClient(client);
        
        
        //une espèce
       Espece espece=new Espece();
       espece.setNom("harang");
        Espece espece1=new Espece();
       espece1.setNom("Truite");
       Espece espece2=new Espece();
       espece2.setNom("Saumon");
        Espece espece3=new Espece();
       espece3.setNom("Merlu");
        Espece espece4=new Espece();
       espece4.setNom("Morue");
        Espece espece5=new Espece();
       espece5.setNom("Dorade");
     
       
       //un type client
       TypeClient t,t2, t3;
       t = new TypeClient();
       t.setType("Importateur");
       t2 = new TypeClient();
       t2.setType("Armateur");
     //  client.addType(t);
       //client.addType(t2);
        t3 = new TypeClient();
        t3.setType("Exportateur");
        
       //une tournée de pêche
       Peche p=new Peche();
       Peche p2=new Peche();
       p.setDatePeche(Dates.convertStringToDate("2014-10-02"));
       p.setQuantite(50L);
    
         
       try{
          // ClientBean clu=new ClientBean();
         ClientBean clu=new ClientBean();
          clu.creerTypeClient(t);
          // ClientBean clu2=new ClientBean();
          clu.creerTypeClient(t2);
          clu.creerTypeClient(t3);
          
          //un client
          clu.creerClient(client, t.getId());
           clu.creerClient(client, t2.getId());
           
           //une espèce
         clu.creerEspece(espece);
          clu.creerEspece(espece1);
          clu.creerEspece(espece2);
          clu.creerEspece(espece3);
          clu.creerEspece(espece4);
          clu.creerEspece(espece5);
          
          //une autorisation
          clu.creerAutorisation(client.getId(), auto, espece.getId());
          clu.creerAutorisation(client.getId(), auto, espece1.getId());
          clu.creerAutorisation(client.getId(), auto, espece2.getId());
          
          //créer une tournée de pêche
         // clu.creerUneTourneeDePeche(client.getId(), espece.getId(), p);
         // clu.creerUneTourneeDePeche(client.getId(), espece1.getId(), p);
           clu.creerUneTourneeDePeche( espece.getId(),p, auto.getId());
          clu.creerUneTourneeDePeche( espece1.getId(),p, auto.getId());
        
        //  clu.supprimerClient(54L);
        //clu.supprimerClient(4L);
        //  clu.suppAutorisation(11L);
       //  clu.supprimerTypeClient(1L);
         // System.out.println("type = " + clu.getClient(4L).getNom());
         int    a=1;
       //  assertTrue(!(a > 3));
        // Assert.isTrue(a>3, "1 nest pas plus grand que 3");
           Assert.isTrue(a==1, "les 2 nombres sont égaux");
        System.out.print("------> après client:");
    
     
   
    
    }catch(Exception e){
    System.out.print("###### erreur SQL:");
    e.getStackTrace();
    System.err.println(e.getMessage());
}
//LOGGER.info("Fin de l'application");
}
}