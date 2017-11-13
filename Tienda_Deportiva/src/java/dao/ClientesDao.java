package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import JpaController.ClientesJpaController;
import modelo.Clientes;

public class ClientesDao extends ClientesJpaController{
    EntityManagerFactory emf;

    public ClientesDao(EntityManagerFactory emf) {
        super(emf);
        this.emf = emf;
    }

    public Clientes getClientes(int key) {
        EntityManager em = this.emf.createEntityManager();
        Query q = em.createNamedQuery("Clientes.findByIdclientes");
        q.setParameter("idclientes", key);
        try {
            return (Clientes) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    public List<Clientes> getClientesName(String key) {
        EntityManager em = this.emf.createEntityManager();
        Query q = em.createNamedQuery("Clientes.findByNombres");
        q.setParameter("nombres", key);
        try {
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Clientes> getClientesLastName(String key) {
        EntityManager em = this.emf.createEntityManager();
        Query q = em.createNamedQuery("Clientes.findByApellidos");
        q.setParameter("apellidos", key);
        try {
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Clientes> getClientesCedula(String key) {
        EntityManager em = this.emf.createEntityManager();
        Query q = em.createNamedQuery("Clientes.findByCedula");
        q.setParameter("cedula", key);
        try {
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
