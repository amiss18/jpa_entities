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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author armel
 */
@Entity
@Table( name="p_client")
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

   
    /************************************
     * attributs
     **************************************/
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private String raisonSociale;
    private String adresse;
    private String ville;
    private String tel;
    private String fax;
    private String portable;
    private String portable2;
    private String email;
   @OneToMany( cascade={CascadeType.DETACH,CascadeType.PERSIST})
    @JoinTable( name="p_client_type_client",
            joinColumns = @JoinColumn(name="client_fk"),
            inverseJoinColumns = @JoinColumn( name="type_client_fk")
    )
    private List<TypeClient> typeClients;
  /*  
    @OneToMany(cascade=CascadeType.REMOVE)
    @JoinColumn(name="client_fk", nullable=true)
    private List<Peche> peches;*/

   @OneToOne(mappedBy = "client", cascade=CascadeType.REMOVE)
    private Autorisation autorisation;

    
    // ======================================
    // =            Constructors            =
    // ======================================
     public Client(){
        typeClients = new ArrayList<>();
      //  peches = new ArrayList<>();
     }
     
    public Client( String nom, String raisonSociale, String adresse, String ville, String tel, String fax, String portable, String portable2, String email) {
        this.nom = nom;
        this.raisonSociale = raisonSociale;
        this.adresse = adresse;
        this.ville = ville;
        this.tel = tel;
        this.fax = fax;
        this.portable = portable;
        this.portable2 = portable2;
        this.email = email;
      //  typeClients = new ArrayList<>();

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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

   
    
    // ======================================
    // =            getttes                  =
    // ======================================
    
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getVille() {
        return ville;
    }

    public String getTel() {
        return tel;
    }

    public String getFax() {
        return fax;
    }

    public String getPortable() {
        return portable;
    }

    public String getPortable2() {
        return portable2;
    }

    public String getEmail() {
        return email;
    }

    // ======================================
    // =            setters                 =
    // ======================================
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setPortable(String portable) {
        this.portable = portable;
    }

    public void setPortable2(String portable2) {
        this.portable2 = portable2;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TypeClient> getTypeClients() {
        return typeClients;
    }

    public void setTypeClients(List<TypeClient> typeClients) {
        this.typeClients = typeClients;
    }
    
    /* ajout d'un type client dans client
    *
    */
    public void addType(TypeClient type ){
        this.typeClients.add(type);
    }

    
    public void removeType( TypeClient type ){
        this.typeClients.remove(type);
    }
    
    public Autorisation getAutorisation() {
        return autorisation;
    }

    public void setAutorisation(Autorisation autorisation) {
        this.autorisation = autorisation;
    }
    
    
    
    
     @Override
    public String toString() {
        //return "model.Client[ id=" + id + " ]";
        
          final StringBuilder sb = new StringBuilder();
        sb.append("*** Client ***** ");
        sb.append("{id=").append(id);
        sb.append(", nom ='").append(nom).append('\'');
        sb.append(", raison sociale=").append(raisonSociale);
        sb.append('}');
        return sb.toString();
    }

    /*
    public List<Peche> getPeches() {
        return peches;
    }

    public void setPeches(List<Peche> peches) {
        this.peches = peches;
    }
    */
    
    /* ajout d'un relevé de pêche
    *
    */
    /*
    public void addPeche( Peche peche ){
        this.peches.add(peche);
    }
*/
    public void setId(Long id) {
        this.id = id;
    }

  
    
}
