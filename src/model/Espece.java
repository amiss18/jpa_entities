/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author armel
 */
@Entity
@Table(name="p_espece")

@NamedQueries({
    @NamedQuery( name=Espece.FIND_ALL_ESPECES, query="SELECT e FROM Espece e")
})
public class Espece implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean active;
    private String nom; //sert à désactiver l'affichage d'une espèce supprimée
     @Transient
    public final static String FIND_ALL_ESPECES="findAllEspeces";

    public Espece(){
     //  this.peches = new ArrayList<>(); 
        this.active = true;
    }
   
    
     public Espece(Long id, String nom){
         this.id = id;
        this.nom = nom;
     }
    public Long getId() {
        return id;
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
        if (!(object instanceof Espece)) {
            return false;
        }
        Espece other = (Espece) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Espece[ id=" + id + " ]";
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

   
/*
    public List<Peche> getPeches() {
        return peches;
    }

    public void setPeches(List<Peche> peches) {
        this.peches = peches;
    }
    

    public void addPeche(Peche peche ){
        this.peches.add(peche);
    }
    */

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
