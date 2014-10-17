/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import exceptions.ValidationException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import model.*;

/**
 *
 * @author armel
 *
 * manipule les entities beans
 */
public class ClientBean {

    private final EntityManagerFactory emf;
    private final EntityManager em;
    private EntityTransaction tx;

    public ClientBean() {
        emf = Persistence.createEntityManagerFactory("peche-2PU");
        em = emf.createEntityManager();

    }

    /* création d'un client à partir des entities Client et TypeClient
     *
     */
    public TypeClient creerTypeClient(TypeClient typeClient) {
        if (typeClient == null) {
            throw new IllegalArgumentException("objet typeClient pointe à null");
        }
        tx = em.getTransaction();
        tx.begin();
        em.persist(typeClient);
        tx.commit();
        return typeClient;
    }

    /* création d'un client à partir des entities Client et TypeClient
     *
     */
    public Client creerClient(Client client, Long typeClientId) {
        if (client == null) {
            throw new IllegalArgumentException("objet client pointe à null");
        }

        if (typeClientId == null) {
            throw new IllegalArgumentException("objet typeClient pointe à null");
        }
        TypeClient typeClient = em.find(TypeClient.class, typeClientId);
        tx = em.getTransaction();
        tx.begin();
        client.addType(typeClient);
        em.persist(client);
        tx.commit();
        return client;
    }

    public Espece creerEspece(Espece espece) {
        if (espece == null) {
            throw new IllegalArgumentException("objet Espece pointe à null");
        }

        tx = em.getTransaction();
        tx.begin();
        em.persist(espece);
        tx.commit();
        return espece;
    }

    
    public Importer creerImportion(Importer importer, Long autorisationId) {
        if (importer == null) {
            throw new IllegalArgumentException("objet Espece pointe à null");
        }
        if ( autorisationId == null) {
            throw new IllegalArgumentException("autorisationId pointe à null");
        }
        Autorisation autorisation = this.getAutorisation(autorisationId);
        tx = em.getTransaction();
        tx.begin();
        importer.setAutorisation(autorisation);
        em.persist(importer);
        tx.commit();
        return importer;
    }
    
    
     public Exporter creerExportation(Exporter exporter, Long autorisationId) {
        if (exporter == null) {
            throw new IllegalArgumentException("objet Exporter pointe à null");
        }
        if ( autorisationId == null) {
            throw new IllegalArgumentException("autorisationId pointe à null");
        }
        Autorisation autorisation = this.getAutorisation(autorisationId);
        tx = em.getTransaction();
        tx.begin();
        exporter.setAutorisation(autorisation);
        em.persist(exporter);
        tx.commit();
        return exporter;
    }
    
    /* Retourne un entity bean Espece à partir de l'identifiant passé en param
     *
     */
    public Espece getEspece(Long especeId) {
        if (especeId == null) {
            throw new IllegalArgumentException("le especeId pointe à null!");
        }
        Espece espece = em.find(Espece.class, especeId);

        return espece;
    }

    /* Retourne un entity bean Client à partir de  son identifiant passé en param
     *
     */
    public Client getClient(Long clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("le cliendId pointe à null!");
        }
        Client client = em.find(Client.class, clientId);
        if (client == null) {
            throw new EntityNotFoundException("aucun client trouvé pour ID=" + clientId);
        }
        return client;

    }

    /* Retourne un entity bean Client à partir de  son identifiant passé en param
     *
     */
    public Autorisation getAutorisation(Long autorisationId) {
        if (autorisationId == null) {
            throw new IllegalArgumentException("autorisationId pointe à null!");
        }
        Autorisation autorisation = em.find(Autorisation.class, autorisationId);
        if (autorisation == null) {
            throw new EntityNotFoundException("aucune autorisation trouvé epour ID=" + autorisationId);
        }
        return autorisation;

    }

    /*retourne le typeClient
     *
     */
    public TypeClient getTypeClient(Long typeClientId) {
        if (typeClientId == null) //on s'assure que l'entité bean Espece existe,si elle nexiste pas on lance une exception
        {
            throw new IllegalArgumentException("le typeClientId pointe à null!");
        }

        TypeClient typeClient = em.find(TypeClient.class, typeClientId);
        if (typeClient == null) {
            throw new EntityNotFoundException("Impossible de trouver le typ client dont l'ID = "
                    + typeClientId);
        }

        return typeClient;
    }

    
    public Exporter getExport(Long exportId) {
        if (exportId == null) {
            throw new IllegalArgumentException("le cliendId pointe à null!");
        }
        Exporter exporter = em.find(Exporter.class, exportId);
      /*  if (exporter == null) {
            throw new EntityNotFoundException("aucun client trouvé pour ID=" + exportId);
        }
        */
        return exporter;

    }
    
    
    public Importer getImport(Long importId) {
        if ( importId == null) {
            throw new IllegalArgumentException("le cliendId pointe à null!");
        }
        Importer importer = em.find(Importer.class, importId);
       /* if (importer == null) {
            throw new EntityNotFoundException("aucun client trouvé pour ID=" + importId);
        }*/
        return importer;

    }
    
    
     public Peche getPeche(Long pecheId) {
        if (pecheId == null) {
            throw new IllegalArgumentException("le pecheId pointe à null!");
        }
        Peche peche = em.find(Peche.class, pecheId);
        return peche;
    }
    
    /*on crée une autorisation à partir des entities beans Client, Espece, Autorisation
     *
     */
    public Autorisation creerAutorisation(Long clientId, Autorisation autorisation,
            Long especeId) {
        if (clientId == null) {
            throw new IllegalArgumentException("cliendId pointe à null!");
        }
        if (especeId == null) {
            throw new IllegalArgumentException("especeId pointe à null!");
        }
        if (autorisation == null) {
            throw new IllegalArgumentException("Autorisation pointe à null!");
        }
        Client client = this.getClient(clientId);//on recupère le client
        Espece espece = this.getEspece(especeId);//on recupère l'espece
        autorisation.addEspece(espece);//on ajoute l'entity Espece à l'entity Autorisation
        autorisation.setClient(client);
        tx = em.getTransaction();
        tx.begin();
        em.persist(autorisation);//l'EM persiste le client dans la bd
        //   em.persist(espece);
        // em.persist(client);
        tx.commit();
        return autorisation;
    }

    /*on crée une tournée de pêche à partir des entities beans Client, Espece, Peche
     *
     */
    //  public Peche creerUneTourneeDePeche(Long clientId, Long especeId, Peche peche ) {
  /*  public Peche creerUneTourneeDePeche(Long clientId, Long especeId, Peche peche, Long autorisationId) {
        if (autorisationId == null) {
            throw new IllegalArgumentException("autorisationId pointe à null!");
        }

        if (especeId == null) {
            throw new IllegalArgumentException("especeId pointe à null!");
        }

        if (peche == null) {
            throw new IllegalArgumentException("Peche pointe à null!");
        }
        Client client = this.getClient(clientId);//on recupère le client
        Espece espece = this.getEspece(especeId);//on recupère l'espece
        Autorisation autorisation = this.getAutorisation(autorisationId);
        
        client.addPeche(peche);
        peche.addEspece(espece);
        peche.setAutorisation(autorisation);
        tx = em.getTransaction();
        tx.begin();
        em.persist(peche);//l'EM persiste peche dans la bd
        tx.commit();
        return peche;
    }
    */
    
    
      public Peche creerUneTourneeDePeche( Peche peche, Long autorisationId) {
        if (autorisationId == null) {
            throw new IllegalArgumentException("autorisationId pointe à null!");
        }

       
        if (peche == null) {
            throw new IllegalArgumentException("Peche pointe à null!");
        }
        Autorisation autorisation = this.getAutorisation(autorisationId);
        
       // client.addPeche(peche);
        //peche.addEspece(espece);
        peche.setAutorisation(autorisation);
        tx = em.getTransaction();
        tx.begin();
        em.persist(peche);//l'EM persiste peche dans la bd
        tx.commit();
        return peche;
    }

    //------------------------------------//
    // Suppression d'entities beans
    //-----------------------------------//
    /*suppression d'un client à partir de son id
     *cette supp => supp de la tournée de peche, supp de son autorisation
     */
    public void supprimerClient(Long clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("cliendId pointe à null!");
        }
        Client client = this.getClient(clientId);//on recupère le client
        tx = em.getTransaction();
        tx.begin();
        em.remove(client);
        tx.commit();
    }

    /* supprime le typeClient client passé en paramètre
     *  suppression dans les tables type_client, type_client_type
     */
    public void supprimerTypeClient(Long typeClientId) {
        if (typeClientId == null) {
            throw new IllegalArgumentException("cliendId pointe à null!");
        }
        TypeClient typeClient = this.getTypeClient(typeClientId);
        // Client client=new Client();
        tx = em.getTransaction();
        tx.begin();
        em.remove(typeClient);
        // em.clear();
        tx.commit();
    }

    /* supprime le typeClient client passé en paramètre
     *
     */
    public void supprimerAutorisation(Long clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("clientId pointe à null!");
        }
        Client client = this.getClient(clientId);
        Autorisation autorisation = client.getAutorisation();

        tx = em.getTransaction();
        tx.begin();
        //em.remove(em.merge(autorisation));
        em.remove(autorisation);
        tx.commit();
    }

    public void suppAutorisation(Long autorisationId) {
        if (autorisationId == null) {
            throw new IllegalArgumentException("clientId pointe à null!");
        }
        Autorisation autorisation = this.getAutorisation(autorisationId);

        tx = em.getTransaction();
        tx.begin();
        //em.remove(em.merge(autorisation));
        em.remove(autorisation);
        tx.commit();
    }
    
      public void supprimerImportation(Long importId) {
        if (importId == null) {
            throw new IllegalArgumentException("importId pointe à null!");
        }
        //Autorisation autorisation = this.getAutorisation(autorisationId);
        Importer importer=em.find(Importer.class, importId);
        tx = em.getTransaction();
        tx.begin();
        em.remove(importer);
        tx.commit();
    }
      
   
      /*
      supprimer une tournée de pêche: supprime un enreg dans la table p_peche
      */
     public void supprimerPeche(Long pecheId) {
        if (pecheId == null) {
            throw new IllegalArgumentException("exportId pointe à null!");
        }
        //Autorisation autorisation = this.getAutorisation(autorisationId);
        Peche peche=this.getPeche(pecheId);
        
        tx = em.getTransaction();
        tx.begin();
        em.remove(peche);
        tx.commit();
    }


    /* modifie les infos d'un client à partir des entities Client et TypeClient
     *
     */
    public Client modifierClient(Client client, TypeClient typeClient) {
        if (client == null) {
            throw new IllegalArgumentException("objet client pointe à null");
        }

        if (typeClient == null) {
            throw new IllegalArgumentException("objet typeClient pointe à null");
        }
        //Client client =this.getClient(clientId);
        //  TypeClient typeClient=em.find(TypeClient.class, typeClientId);
        tx = em.getTransaction();
        tx.begin();
        client.addType(typeClient);
        em.merge(client);
       // em.merge(typeClient);
        // em.detach(typeClient);
        tx.commit();
        return client;
    }

    /**
     * le total de toutes les quantités pêchées pour une autorisation
     *
     * @param autorisationId id de l'autorisation
     * @return Double total pêché
     */
    public Double totalPeche(Long autorisationId) {
        if (autorisationId == null) {
            throw new IllegalArgumentException("autorisationId pointe à null");
        }
        Query query;
        query = em.createNamedQuery(Peche.TOTAL_PECHE);
        query.setParameter("autorisationId", autorisationId);
        Double totalPechee = (Double) query.getSingleResult();
        return totalPechee;
    }

     /**
     * le total de toutes les quantités pêchées pour une autorisation
     *
     * @param autorisationId id de l'autorisation
     * @return Double total pêché
     */
    public boolean VerifierQuota(Long autorisationId, Double quantite ) {
        if (autorisationId == null) {
            throw new IllegalArgumentException("autorisationId pointe à null");
        }
        
         if (quantite == null) {
            throw new IllegalArgumentException("quantité non renseignée");
        }
        Query query;
        query = em.createNamedQuery(Peche.VERIFIER_QUOTA);
        query.setParameter("autorisationId", autorisationId);
        List<Peche> pe=query.getResultList();
        Double totalPechee=0d, quota=0d;
        for(Peche p: pe){
            totalPechee+=p.getQuantite();
            quota =p.getAutorisation().getQuota();
        //System.out.println("quota = " + p.getAutorisation().getQuota() +" q="+totalPechee);
        
        }
        System.out.println("quota = " + quota + "\t ,cumul qté="+totalPechee);
        if( quantite>=quota ){
          // throw new ValidationException("quota atteint");           
             System.out.println("quota atteint");
             return false;
        }
        Double quantiteRestante= quota-totalPechee;
           if( quantiteRestante>0 && quantiteRestante<quantite ){
            System.out.println("votre quantité est superieure à votre quota restant qui est de ="+quantiteRestante);
           return false;

        }
        Double reste = quota-(totalPechee+quantite);
        System.out.println("ajout possible, votre quota restant est de = " + reste);
        
        return true;
    }

    /*
    modifier une espèce à partir de son enity
    */
    public void modifierEspece( Espece espece ){
        if ( espece == null) {
            throw new IllegalArgumentException("espece est invalide");
        }
       // Espece espece = this.getEspece(especeId);
       // espece.setId(especeId);
        tx = em.getTransaction();
       //  espece.setId(espece.getId());
        tx.begin();
        em.merge(espece);
        tx.commit();
        
    }
    
    /*fertmeture de connection
    
     */
    public void closeConnection() {
        em.close();
        emf.close();
    }
    
}//end class
