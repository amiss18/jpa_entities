/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peche.pkg2;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import model.Author;
import model.Book;

/**
 *
 * @author armel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         // java.util.Date utilDate = new java.util.Date();
           // java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
          //  System.exit(1);
  //  System.out.println("utilDate:" + utilDate);
   // System.out.println("sqlDate:" + sqlDate);
    //    System.out.println("non compilé");
        String date_se = "2013-11-25 12:30:00 ", ladate3 = "2014-09-18 12:35:55";
        SimpleDateFormat sformat=null;
         Date d=null, date3 = null;
        try
 {

     sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //  sformat = new SimpleDateFormat("yyyy-MM-dd");
     
    
     
     d=sformat.parse(date_se);
     date3 = sformat.parse(ladate3);
     
      String currentTime =sformat.format(date3);
     
      System.out.println("******");
      System.out.println("utilDate = " + currentTime);
       System.out.println("******");
  } catch (ParseException ex) 
  {
        System.out.println("Parse Exception");
  }

           // Creates an instance of book
        Book book = new Book();
        book.setTitle("The Hitchhiker's Guide to the Galaxy");
        book.setPrice(12.5d);
        book.setDescription("Science fiction comedy series created by Douglas Adams.");
        book.setIsbn("1-84023-742-2");
        book.setNbOfPage(354);
        book.setIllustrations(false);
        book.setDate(d);
        
        Book book1 = new Book();
        book1.setTitle("un bon but de klose");
        book1.setPrice(55.50d);
        book1.setDescription("demi finale de la coupe du monde 3 pour lallemagne.");
        book1.setIsbn("1-84023-742-2");
        book1.setNbOfPage(450);
        book1.setIllustrations(true);
        book1.setDate(d);

	Book book2 = new Book();
        book2.setTitle("un titre :pêche");
        book2.setPrice(100.85d);
        book2.setDescription("projet sur la pêche55555.");
        book2.setIsbn("0-84023-742-88");
        book2.setNbOfPage(350);
        book2.setIllustrations(false);
        book2.setDate(date3);
        
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
   
        // Gets an entity manager and a transaction
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("peche-2PU");
        EntityManager em = emf.createEntityManager();
        
/*        
        Query query = em.createQuery("Select b FROM Author b ");
List<Author> books = query.getResultList();
for( Author o : books ){
           System.out.println("author = " + o.getFirstname());
           for( Book b : o.getBooks()){
                 System.out.println("\t titre = " + b.getTitle());
           }

}
*/
        
Query q=em.createNamedQuery("findAllBooks");
List<Book> bk=q.getResultList();

 for( Book b : bk){
                 System.out.println("titre = " + b.getTitle());
                  System.out.println("\t prix = " + b.getPrice()+"€");
           }
 
 
         
Query query2=em.createNamedQuery(Book.BOOK_BY_PRICE).setParameter("price", 50d);
List<Book> books2=query2.getResultList();

System.out.println("\n########## Livres dont le prix est >50 ############");
 for( Book b : books2){
                 System.out.println("titre = " + b.getTitle());
                  System.out.println("\t prix = " + b.getPrice()+"€");
           }
 
 
        /*
        // Persists the book to the database
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(author);
        em.persist(author1);
        tx.commit();
*/
        em.close();
        emf.close();
    }
    
}