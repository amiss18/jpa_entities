/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junit;

import beans.ClientBean;
import beans.EspeceBean;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import model.*;
import outils.DateValidator;
import outils.Dates;
import outils.Nombre;

/**
 *
 * @author armel
 */
public class PecheTest {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;
    private DateValidator dateValidator = new DateValidator();

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @BeforeClass
    public static void initEntityManager() throws Exception {
        emf = Persistence.createEntityManagerFactory("peche-2PU");
        em = emf.createEntityManager();
        //   dateValidator = new DateValidator();
    }

    @AfterClass
    public static void closeEntityManager() throws SQLException {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    @Before
    public void initTransaction() {
        tx = em.getTransaction();
    }

    public PecheTest() {
        ClientBean c = new ClientBean();

    }

    @Test
    public void bookAuthot() {
        double val = 0.870d;
        //  System.out.println("val = " + Nombre.arrondir(val,1));
        Date dateFr = Dates.convertDateFrToUs("18-06-2009"),
                dateUs = Dates.convertStringToDate("2014-09-27");
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
    }

    @Test
    public void findAllEspeces() {
        ClientBean clientBean = new ClientBean();
     //   assertNotNull("la liste espece ne peut être null", clientBean.findAllCustumers());
        //  assertEquals(1, clientBean.findAllCustumers().size());
    }

    @Test
    public void relevesByAutorisation() throws ParseException {
        EspeceBean especeBean = new EspeceBean();
     //   assertNotNull("les relevés ne peuventt être null", especeBean.findRelevesByAutorisation(11L));
        //  assertEquals(2, especeBean.findRelevesByAutorisation(11L).size());
        //  NumberFormat nf = NumberFormat.getInstance(Locale.FRENCH);
      /* 
         NumberFormat nf = DecimalFormat.getInstance(Locale.FRENCH);
         NumberFormat nf_fr = NumberFormat.getInstance(Locale.FRENCH);
         Double de=5008.98;
         DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
         //affiche un objet date au format aaa-mm-dd
      
        
         
         NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
         Number number = format.parse("908,78");
         Double d = number.doubleValue();
         System.out.println("dec=" + d);//900.850
    
         System.out.println( "Current Locale is " + Locale.getDefault().toString() );   */
            //    DecimalFormat ft = new DecimalFormat("#.000");
        //  String outpout=ft.format(143.5080);
        // number to string
        System.out.println("nb en string=: " + outils.Nombre.outPutNumber(2000.879));

        // string to number
        String montantTexte = outils.Nombre.outPutNumber(2000.879);
        //  Double montant = ft.parse(montantTexte).doubleValue();
        System.out.println("nb en double : " + outils.Nombre.intPutNumber(montantTexte));
 //System.out.println( "outpout="+output);//345 987,246

    }

    @Test
    public void findAllCustumers() {
        EspeceBean especeBean = new EspeceBean();
        //  assertEquals(12, especeBean.findAllEspeces().size());
    }

    @Test
    public void updatePeche() {
        ClientBean c = new ClientBean();
    //  c.modifierQuantiteCumulee(11l, 78.50d, Dates.convertStringToDate("2014-11-20"));
        //System.out.println("tot cum = " +c.totalPeche(11l));

    //assertNotNull(c);
    }

    //quota pas encore atteint ?false=>on peut ajouter 
    @Test
    public void quotaAtteintTest() {
        ClientBean c = new ClientBean();

        System.out.println("quota atteint = " + c.quotaAtteint(11l));
        assertFalse(c.quotaAtteint(11l));
    }

    //quantité à ajouter ne doit pas dépasser son quota
    //ajout possible quand c'est true=>on peut ajouter
    @Test
    public void ajoutPossibleTest() {
        ClientBean c = new ClientBean();

        System.out.println("ajout possible = " + c.ajoutPossible(11l, 382.20d));
        // assertTrue(c.ajoutPossible(11l, 382.20d));
    }

    @Test
    public void nbEspeces() {
        EspeceBean especeBean = new EspeceBean();
        assertEquals(12, especeBean.findAllEspeces().size());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    /*
     @Test
     public void peche() {
     Date dateFr=Dates.convertDateFrToUs("18-06-2009"),
     dateUs = Dates.convertStringToDate("2014-09-27")  ;
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
     auto.setQuota(550.75D);
        
     Autorisation auto2=new Autorisation();
     auto2.setNumero("CG-79");
     auto2.setDateAutorisation(new Date());
     auto2.setQuota(600D);
     //autorisation3
     Autorisation auto3=new Autorisation();
     auto3.setNumero("CG-80");
     auto3.setDateAutorisation(new Date());
     auto3.setQuota(1000.75D);
     // auto.setClient(client);
        
        
     //une espèce
     Espece espece=new Espece();
     // espece.setId(51l);
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
     p.setDatePeche(Dates.convertStringToDate("2014-10-10"));
     p.setQuantite(50d);
    
     p2.setDatePeche(Dates.convertStringToDate("2014-10-5"));
     p2.setQuantite(90d);
           
     ClientBean clu=new ClientBean();
     */
    /*  clu.creerTypeClient(t);
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
     //   clu.creerUneTourneeDePeche( client.getId(),espece.getId(),p, auto.getId());
     clu.creerUneTourneeDePeche(p, auto.getId());
     //  clu.creerUneTourneeDePeche( client.getId(),espece1.getId(),p, auto.getId());
         
     //  clu.creerUneTourneeDePeche( client.getId(),espece1.getId(),p2, auto.getId());
     clu.creerUneTourneeDePeche(p2, auto.getId());
         
     Importer importer=new Importer();
     importer.setDateImportation(Dates.convertStringToDate("2014-10-01"));
     importer.setQuantite(20.05d);
     clu.creerImportion(importer, auto.getId());
           
     Exporter ex=new Exporter();
     ex.setQuantite(145.50d);
     ex.setDateExport(Dates.convertStringToDate("2014-10-11"));
     clu.creerExportation(ex, auto.getId());
     */
         //clu.VerifierQuota(11l, 400.59d)
     /*    String s_id="11";
     Long id=Long.parseLong(s_id);
     assertNotNull("ID should not be null", clu.getAutorisation(id) );
     //  assertTrue("**quota atteint*** ",clu.VerifierQuota(11l, 10.59d));
     //clu.supprimerExportation(101l);
     //  assertNotNull("Id export ne doit pas être null", clu.getExport(101l));
     //clu.supprimerPeche(51l);
     //  assertNotNull("Id peche ne doit pas être null", clu.getPeche(51l));
     }
     */
    @Test
    public void ValidDateProvider() {
        DateValidator dateValidator = new DateValidator();
        String date = "22-11-2014";
        boolean valid = dateValidator.validate(date);
        System.out.println("Date is valid : " + date + " , " + valid);
        assertEquals(true, valid);

        String date1 = "32-11-2014";
        boolean valid1 = dateValidator.validate(date1);
        System.out.println("Date is not valid : " + date1 + " , " + valid1);
        assertEquals(false, valid1);
    }
    
     @Test
    public void ValidDateUsTest() {
        System.out.println("----Date us is  valid : " );
         String date = "2014-11-20";
         boolean valid=outils.Dates.isThisDateValid(date, outils.Dates.paternUS);
          System.out.println("Date is valid : " + date + " , " + valid);
         assertEquals(true, valid);
         
         System.out.println("----Date us is not valid : " );
            String date1 = "30-11-2014";
           boolean valid1 = Dates.isThisDateValid(date1, outils.Dates.paternUS);
            System.out.println("Date is not valid : " + date1 + " , " + valid1);
           assertEquals(false, valid1);
    }
    
      @Test
    public void creerUneTourneeTest() {
          //une tournée de pêche
   /*  Peche p=new Peche();
     Peche p2=new Peche();
     p.setDatePeche(Dates.convertStringToDate("2014-10-10"));
     p.setQuantite(50d);*/
       System.out.println("***création d'une tournée de pêche******");
    // ClientBean c = new ClientBean();
    // c.creerUneTourneeDePeche(11l, 25.550, new Date());
       
  //   assertNotNull(c);
  //   boolean valid1= "525.55".matches("^\\d+\\.\\d{2}$");
   String nb="1 888 555 850 ,", res;
    System.out.println("chiffre ?="+ outils.Nombre.validateNumber(nb));
    //  System.out.println("chiffre ?="+ nb.replaceAll("\\s+", ""));
     //assertEquals(true, outils.Nombre.validateNumber(nb));
        assertNotNull(outils.Nombre.validateNumber(nb));

    }
}
