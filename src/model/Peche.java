/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * classe mappant en table associative entre client et espece
 * @author armel
 */
@Entity
@Table(name="p_peche")
@NamedQueries({
   @NamedQuery( name=Peche.VERIFIER_QUOTA, query="SELECT  p FROM Peche p WHERE p.autorisation.id=:autorisationId"),
  @NamedQuery( name=Peche.TOTAL_PECHE, query="SELECT SUM(p.quantite) as totalPeche FROM Peche p WHERE p.autorisation.id=:autorisationId")

})
public class Peche implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name="date_peche")
    private Date datePeche;

    @Transient
    public final static String TOTAL_PECHE="totalPeche";
     @Transient
    public final static String VERIFIER_QUOTA="verifierQuota";
    
    //l'entité peche utilise un chargement differé car pas besoin de charger ttes les espèces
 /*   @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
   @JoinTable( name="tournee_peche",
            joinColumns = @JoinColumn(name="peche_fk"),
            inverseJoinColumns = @JoinColumn( name="espece_fk")
    )
    private List<Espece> especes;
    */
   @Column(columnDefinition="Decimal(10,4)")
    private Double quantite; //quté de poissons pêchés
    
    @OneToOne
    @JoinColumn(name="autorisation_fk")
    private Autorisation autorisation;
    
    public Peche() {
        this.quantite = 0d;
     //  this.especes=new ArrayList<>();
       this.datePeche = new Date();
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
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
        if (!(object instanceof Peche)) {
            return false;
        }
        Peche other = (Peche) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Peche[ id=" + "" + " ]";
    }

    public Date getDatePeche() {
        return datePeche;
    }

    public void setDatePeche(Date datePeche) {
        this.datePeche = datePeche;
    }

    
    
/*
    
    public List<Espece> getEspeces() {
        return especes;
    }

    public void setEspeces(List<Espece> especes) {
        this.especes = especes;
    }
    
     public void addEspece( Espece espece ){
         this.especes.add(espece);
     }
*/
    public Autorisation getAutorisation() {
        return autorisation;
    }

    public void setAutorisation(Autorisation autorisation) {
        this.autorisation = autorisation;
    }
}
