/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Author;
import model.Book;

/**
 *
 * @author armel
 */
public class BookBean {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public BookBean() {
        emf = Persistence.createEntityManagerFactory("peche-2PU");
        em = emf.createEntityManager();
    }

    public List<Author> getAllAuthors() {
        Query query = em.createQuery("Select a FROM Author a ");
        List<Author> authors = query.getResultList();
        return authors;
    }

    /* livres dont les prix sont superieurs à xeuros
    *
    */
    public List<Book> findBookByPrice(double price) {
        Query query = em.createNamedQuery(Book.BOOK_BY_PRICE).setParameter("price", price);
        List<Book> books = query.getResultList();
        return books;
    }

}
