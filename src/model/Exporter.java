/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author armel
 */
@Entity
@Table(name="p_exporter")
public class Exporter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name="date_export")
    private java.util.Date dateExport;

    @Column(columnDefinition="Decimal(10,4)")
     private Double quantite;
  
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="autorisation_fk")
    private Autorisation autorisation;
    
    
    public Exporter() {
        this.dateExport = new Date();
        this.quantite = 0d;

    }

    public Date getDateExport() {
        return dateExport;
    }

    public void setDateExport(Date _dateExport) {
        //this.dateExport = (_dateExport==null)?this.dateExport:_dateExport;
        this.dateExport = _dateExport;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Autorisation getAutorisation() {
        return autorisation;
    }

    public void setAutorisation(Autorisation autorisation) {
        this.autorisation = autorisation;
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
        if (!(object instanceof Exporter)) {
            return false;
        }
        Exporter other = (Exporter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Exporter[ id=" + id + " ]";
    }
    
}
