
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package beans;

//~--- non-JDK imports --------------------------------------------------------

import model.Espece;

//~--- JDK imports ------------------------------------------------------------

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Peche;

/**
 *
 * @author armel
 */
public class EspeceBean {
    private final EntityManagerFactory emf;
    private final EntityManager        em;
    private EntityTransaction          tx;

    public EspeceBean() {
        emf = Persistence.createEntityManagerFactory("peche-2PU");
        em  = emf.createEntityManager();
    }

    @SuppressWarnings("unchecked")
    public List<Espece> findAllEspeces() {
        List<Espece> especes;
        Query        query = em.createNamedQuery(Espece.FIND_ALL_ESPECES);

        especes = query.getResultList();

        return especes;
    }
    
    /*
    toutes prises de pêches ou de ventes
    */
    @SuppressWarnings({"unchecked", "unchecked"})
    public List<Peche> findRelevesByAutorisation(Long autorisationId) {
      /*  if (autorisationId == null) {
            throw new IllegalArgumentException("autorisationId pointe à null");
        }*/
        List<Peche> peches;
        Query        query = em.createNamedQuery(Peche.RELEVEE_DU_CLIENT);
        query.setParameter("autorisationId", autorisationId);

        peches = query.getResultList();

        return peches;
    }

    /*
     * fertmeture de connection
     *
     */
    public void closeConnection() {
        em.close();
        emf.close();
    }
}


