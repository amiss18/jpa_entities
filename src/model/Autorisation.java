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
import static javax.persistence.FetchType.LAZY;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author armel
 */
@Entity
@Table(name="p_autorisation")
public class Autorisation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    
    private String numero;

    @Column(columnDefinition="Decimal(10,4)")
    private Double quota;
    
    @OneToOne
    @JoinColumn(name="client_fk")
    private Client client;
    
    @OneToMany(cascade ={CascadeType.PERSIST})
    @JoinTable( name="p_autorisation_espece",
            joinColumns = @JoinColumn(name="autorisation_fk"),
            inverseJoinColumns = @JoinColumn( name="espece_fk")
    
    )
    private final List<Espece> especes;
    
    @Temporal(TemporalType.DATE)
    private Date dateAutorisation;
  
  //  @OneToMany(mappedBy="autorisation")
  //  private List<Importer> imports;

    public Autorisation() {
      this.especes= new ArrayList<>();
    //  this.imports = new ArrayList<>();
      this.quota = 0d;
    }
    
    /*
    public List<Importer> getImports() {
        return imports;
    }

    public void setImports(List<Importer> imports) {
        this.imports = imports;
    }
    
    public void addImport(Importer importer) {
        this.imports.add(importer);
    }
    
    */
    
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
        if (!(object instanceof Autorisation)) {
            return false;
        }
        Autorisation other = (Autorisation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Autorisation[ id=" + id + " ]";
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    
    public List<Espece> getEspeces() {
        return especes;
    }

    public void addEspece(Espece espece) {
        this.especes.add(espece);
    }

    public Date getDateAutorisation() {
        return dateAutorisation;
    }

    public void setDateAutorisation(Date dateAutorisation) {
        this.dateAutorisation = dateAutorisation;
    }

    public Double getQuota() {
        return quota;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
