/*
 *classe gérant les données d'un client
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
import javax.persistence.Table;

/**
 *
 * @author armel
 */
@Entity
@Table( name="p_type_client")
public class TypeClient implements Serializable {
    private static final long serialVersionUID = 1L;
    
    //********************************
    //* attributs                       
    //********************************
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="type_client")
    private String type;

    
    public TypeClient(){
     //clients = new ArrayList<>();
    }
    public TypeClient( String type) {
        this.type = type;
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
        if (!(object instanceof TypeClient)) {
            return false;
        }
        TypeClient other = (TypeClient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

   


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
     @Override
    public String toString() {
        return "model.TypeClient[ id=" + id + ", " + type +" ]";
    }

  /* public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
    
     public void addClient( Client client ){
         this.clients.add(client);
        
     }*/
/*
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    */

    public void setId(Long id) {
        this.id = id;
    }
}
