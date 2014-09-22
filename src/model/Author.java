/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exceptions.ValidationException;
import java.io.Serializable;
import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
/**
 *
 * @author armel
 */
@Entity
@Table(name = "t_author")
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * *****************
     * attributs
     *
     ******************
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 50)
    private String firstname;
    @Column(nullable = false, length = 50)
    private String lastname;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Book> books;
   
    @Column(nullable = false, length = 50)
   private String city;

    public Author() {
      books=new ArrayList<>();
    }

    public Author(String _firstname, String _lastname, String _city) {
        this.firstname = _firstname;
        this.lastname = _lastname;
        this.city = _city;
        books=new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Author)) {
            return false;
        }
        Author other = (Author) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Author[ id=" + id + " ]";
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCity() {
        return city;
    }
    
    
    /******
     * settes
     */
     public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    /*
    * validation du de la ville
    */
    
    @PrePersist
    @PreUpdate
    public ValidationException validateCity(){
        ValidationException validationException=null;
        if( this.city==null  || "".equals(this.city) ){
            validationException = new ValidationException("le nom de la ville est requise");
        }
        return validationException;
    }

    public List<Book> getBooks() {
        return books; 
    }
    
    public Book getBook(){
        Book b=new Book();
        return b;
    }
    
      

    public void setBooks(List<Book> books) {
        this.books = books;
    }

     public void addBook(Book book ){
         this.books.add(book);
     }
}
