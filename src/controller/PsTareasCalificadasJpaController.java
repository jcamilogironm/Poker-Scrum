/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.PsTareas;
import model.PsTareasCalificadas;
import model.PsUsuarios;

/**
 *
 * @author sistemas
 */
public class PsTareasCalificadasJpaController implements Serializable {

    public PsTareasCalificadasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PsTareasCalificadas psTareasCalificadas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PsTareas tbTareasNumero = psTareasCalificadas.getTbTareasNumero();
            if (tbTareasNumero != null) {
                tbTareasNumero = em.getReference(tbTareasNumero.getClass(), tbTareasNumero.getNumero());
                psTareasCalificadas.setTbTareasNumero(tbTareasNumero);
            }
            PsUsuarios tbUsuariosNumero = psTareasCalificadas.getTbUsuariosNumero();
            if (tbUsuariosNumero != null) {
                tbUsuariosNumero = em.getReference(tbUsuariosNumero.getClass(), tbUsuariosNumero.getNumero());
                psTareasCalificadas.setTbUsuariosNumero(tbUsuariosNumero);
            }
            em.persist(psTareasCalificadas);
            if (tbTareasNumero != null) {
                tbTareasNumero.getPsTareasCalificadasList().add(psTareasCalificadas);
                tbTareasNumero = em.merge(tbTareasNumero);
            }
            if (tbUsuariosNumero != null) {
                tbUsuariosNumero.getPsTareasCalificadasList().add(psTareasCalificadas);
                tbUsuariosNumero = em.merge(tbUsuariosNumero);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PsTareasCalificadas psTareasCalificadas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PsTareasCalificadas persistentPsTareasCalificadas = em.find(PsTareasCalificadas.class, psTareasCalificadas.getNumero());
            PsTareas tbTareasNumeroOld = persistentPsTareasCalificadas.getTbTareasNumero();
            PsTareas tbTareasNumeroNew = psTareasCalificadas.getTbTareasNumero();
            PsUsuarios tbUsuariosNumeroOld = persistentPsTareasCalificadas.getTbUsuariosNumero();
            PsUsuarios tbUsuariosNumeroNew = psTareasCalificadas.getTbUsuariosNumero();
            if (tbTareasNumeroNew != null) {
                tbTareasNumeroNew = em.getReference(tbTareasNumeroNew.getClass(), tbTareasNumeroNew.getNumero());
                psTareasCalificadas.setTbTareasNumero(tbTareasNumeroNew);
            }
            if (tbUsuariosNumeroNew != null) {
                tbUsuariosNumeroNew = em.getReference(tbUsuariosNumeroNew.getClass(), tbUsuariosNumeroNew.getNumero());
                psTareasCalificadas.setTbUsuariosNumero(tbUsuariosNumeroNew);
            }
            psTareasCalificadas = em.merge(psTareasCalificadas);
            if (tbTareasNumeroOld != null && !tbTareasNumeroOld.equals(tbTareasNumeroNew)) {
                tbTareasNumeroOld.getPsTareasCalificadasList().remove(psTareasCalificadas);
                tbTareasNumeroOld = em.merge(tbTareasNumeroOld);
            }
            if (tbTareasNumeroNew != null && !tbTareasNumeroNew.equals(tbTareasNumeroOld)) {
                tbTareasNumeroNew.getPsTareasCalificadasList().add(psTareasCalificadas);
                tbTareasNumeroNew = em.merge(tbTareasNumeroNew);
            }
            if (tbUsuariosNumeroOld != null && !tbUsuariosNumeroOld.equals(tbUsuariosNumeroNew)) {
                tbUsuariosNumeroOld.getPsTareasCalificadasList().remove(psTareasCalificadas);
                tbUsuariosNumeroOld = em.merge(tbUsuariosNumeroOld);
            }
            if (tbUsuariosNumeroNew != null && !tbUsuariosNumeroNew.equals(tbUsuariosNumeroOld)) {
                tbUsuariosNumeroNew.getPsTareasCalificadasList().add(psTareasCalificadas);
                tbUsuariosNumeroNew = em.merge(tbUsuariosNumeroNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = psTareasCalificadas.getNumero();
                if (findPsTareasCalificadas(id) == null) {
                    throw new NonexistentEntityException("The psTareasCalificadas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PsTareasCalificadas psTareasCalificadas;
            try {
                psTareasCalificadas = em.getReference(PsTareasCalificadas.class, id);
                psTareasCalificadas.getNumero();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The psTareasCalificadas with id " + id + " no longer exists.", enfe);
            }
            PsTareas tbTareasNumero = psTareasCalificadas.getTbTareasNumero();
            if (tbTareasNumero != null) {
                tbTareasNumero.getPsTareasCalificadasList().remove(psTareasCalificadas);
                tbTareasNumero = em.merge(tbTareasNumero);
            }
            PsUsuarios tbUsuariosNumero = psTareasCalificadas.getTbUsuariosNumero();
            if (tbUsuariosNumero != null) {
                tbUsuariosNumero.getPsTareasCalificadasList().remove(psTareasCalificadas);
                tbUsuariosNumero = em.merge(tbUsuariosNumero);
            }
            em.remove(psTareasCalificadas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PsTareasCalificadas> findPsTareasCalificadasEntities() {
        return findPsTareasCalificadasEntities(true, -1, -1);
    }

    public List<PsTareasCalificadas> findPsTareasCalificadasEntities(int maxResults, int firstResult) {
        return findPsTareasCalificadasEntities(false, maxResults, firstResult);
    }

    private List<PsTareasCalificadas> findPsTareasCalificadasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PsTareasCalificadas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PsTareasCalificadas findPsTareasCalificadas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PsTareasCalificadas.class, id);
        } finally {
            em.close();
        }
    }

    public int getPsTareasCalificadasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PsTareasCalificadas> rt = cq.from(PsTareasCalificadas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
