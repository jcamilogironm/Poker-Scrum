/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.PsUsuarios;
import model.PsTareasCalificadas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.PsTareas;

/**
 *
 * @author Juan Camilo Giron
 */
public class PsTareasJpaController implements Serializable {

    public PsTareasJpaController() {
        emf = Persistence.createEntityManagerFactory("Poker_ScrumPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PsTareas psTareas) {
        if (psTareas.getPsTareasCalificadasList() == null) {
            psTareas.setPsTareasCalificadasList(new ArrayList<PsTareasCalificadas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PsUsuarios tbUsuariosCodigo = psTareas.getTbUsuariosCodigo();
            if (tbUsuariosCodigo != null) {
                tbUsuariosCodigo = em.getReference(tbUsuariosCodigo.getClass(), tbUsuariosCodigo.getNumero());
                psTareas.setTbUsuariosCodigo(tbUsuariosCodigo);
            }
            List<PsTareasCalificadas> attachedPsTareasCalificadasList = new ArrayList<PsTareasCalificadas>();
            for (PsTareasCalificadas psTareasCalificadasListPsTareasCalificadasToAttach : psTareas.getPsTareasCalificadasList()) {
                psTareasCalificadasListPsTareasCalificadasToAttach = em.getReference(psTareasCalificadasListPsTareasCalificadasToAttach.getClass(), psTareasCalificadasListPsTareasCalificadasToAttach.getNumero());
                attachedPsTareasCalificadasList.add(psTareasCalificadasListPsTareasCalificadasToAttach);
            }
            psTareas.setPsTareasCalificadasList(attachedPsTareasCalificadasList);
            em.persist(psTareas);
            if (tbUsuariosCodigo != null) {
                tbUsuariosCodigo.getPsTareasList().add(psTareas);
                tbUsuariosCodigo = em.merge(tbUsuariosCodigo);
            }
            for (PsTareasCalificadas psTareasCalificadasListPsTareasCalificadas : psTareas.getPsTareasCalificadasList()) {
                PsTareas oldTbTareasNumeroOfPsTareasCalificadasListPsTareasCalificadas = psTareasCalificadasListPsTareasCalificadas.getTbTareasNumero();
                psTareasCalificadasListPsTareasCalificadas.setTbTareasNumero(psTareas);
                psTareasCalificadasListPsTareasCalificadas = em.merge(psTareasCalificadasListPsTareasCalificadas);
                if (oldTbTareasNumeroOfPsTareasCalificadasListPsTareasCalificadas != null) {
                    oldTbTareasNumeroOfPsTareasCalificadasListPsTareasCalificadas.getPsTareasCalificadasList().remove(psTareasCalificadasListPsTareasCalificadas);
                    oldTbTareasNumeroOfPsTareasCalificadasListPsTareasCalificadas = em.merge(oldTbTareasNumeroOfPsTareasCalificadasListPsTareasCalificadas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PsTareas psTareas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PsTareas persistentPsTareas = em.find(PsTareas.class, psTareas.getNumero());
            PsUsuarios tbUsuariosCodigoOld = persistentPsTareas.getTbUsuariosCodigo();
            PsUsuarios tbUsuariosCodigoNew = psTareas.getTbUsuariosCodigo();
            List<PsTareasCalificadas> psTareasCalificadasListOld = persistentPsTareas.getPsTareasCalificadasList();
            List<PsTareasCalificadas> psTareasCalificadasListNew = psTareas.getPsTareasCalificadasList();
            List<String> illegalOrphanMessages = null;
            for (PsTareasCalificadas psTareasCalificadasListOldPsTareasCalificadas : psTareasCalificadasListOld) {
                if (!psTareasCalificadasListNew.contains(psTareasCalificadasListOldPsTareasCalificadas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PsTareasCalificadas " + psTareasCalificadasListOldPsTareasCalificadas + " since its tbTareasNumero field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tbUsuariosCodigoNew != null) {
                tbUsuariosCodigoNew = em.getReference(tbUsuariosCodigoNew.getClass(), tbUsuariosCodigoNew.getNumero());
                psTareas.setTbUsuariosCodigo(tbUsuariosCodigoNew);
            }
            List<PsTareasCalificadas> attachedPsTareasCalificadasListNew = new ArrayList<PsTareasCalificadas>();
            for (PsTareasCalificadas psTareasCalificadasListNewPsTareasCalificadasToAttach : psTareasCalificadasListNew) {
                psTareasCalificadasListNewPsTareasCalificadasToAttach = em.getReference(psTareasCalificadasListNewPsTareasCalificadasToAttach.getClass(), psTareasCalificadasListNewPsTareasCalificadasToAttach.getNumero());
                attachedPsTareasCalificadasListNew.add(psTareasCalificadasListNewPsTareasCalificadasToAttach);
            }
            psTareasCalificadasListNew = attachedPsTareasCalificadasListNew;
            psTareas.setPsTareasCalificadasList(psTareasCalificadasListNew);
            psTareas = em.merge(psTareas);
            if (tbUsuariosCodigoOld != null && !tbUsuariosCodigoOld.equals(tbUsuariosCodigoNew)) {
                tbUsuariosCodigoOld.getPsTareasList().remove(psTareas);
                tbUsuariosCodigoOld = em.merge(tbUsuariosCodigoOld);
            }
            if (tbUsuariosCodigoNew != null && !tbUsuariosCodigoNew.equals(tbUsuariosCodigoOld)) {
                tbUsuariosCodigoNew.getPsTareasList().add(psTareas);
                tbUsuariosCodigoNew = em.merge(tbUsuariosCodigoNew);
            }
            for (PsTareasCalificadas psTareasCalificadasListNewPsTareasCalificadas : psTareasCalificadasListNew) {
                if (!psTareasCalificadasListOld.contains(psTareasCalificadasListNewPsTareasCalificadas)) {
                    PsTareas oldTbTareasNumeroOfPsTareasCalificadasListNewPsTareasCalificadas = psTareasCalificadasListNewPsTareasCalificadas.getTbTareasNumero();
                    psTareasCalificadasListNewPsTareasCalificadas.setTbTareasNumero(psTareas);
                    psTareasCalificadasListNewPsTareasCalificadas = em.merge(psTareasCalificadasListNewPsTareasCalificadas);
                    if (oldTbTareasNumeroOfPsTareasCalificadasListNewPsTareasCalificadas != null && !oldTbTareasNumeroOfPsTareasCalificadasListNewPsTareasCalificadas.equals(psTareas)) {
                        oldTbTareasNumeroOfPsTareasCalificadasListNewPsTareasCalificadas.getPsTareasCalificadasList().remove(psTareasCalificadasListNewPsTareasCalificadas);
                        oldTbTareasNumeroOfPsTareasCalificadasListNewPsTareasCalificadas = em.merge(oldTbTareasNumeroOfPsTareasCalificadasListNewPsTareasCalificadas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = psTareas.getNumero();
                if (findPsTareas(id) == null) {
                    throw new NonexistentEntityException("The psTareas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PsTareas psTareas;
            try {
                psTareas = em.getReference(PsTareas.class, id);
                psTareas.getNumero();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The psTareas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<PsTareasCalificadas> psTareasCalificadasListOrphanCheck = psTareas.getPsTareasCalificadasList();
            for (PsTareasCalificadas psTareasCalificadasListOrphanCheckPsTareasCalificadas : psTareasCalificadasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PsTareas (" + psTareas + ") cannot be destroyed since the PsTareasCalificadas " + psTareasCalificadasListOrphanCheckPsTareasCalificadas + " in its psTareasCalificadasList field has a non-nullable tbTareasNumero field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            PsUsuarios tbUsuariosCodigo = psTareas.getTbUsuariosCodigo();
            if (tbUsuariosCodigo != null) {
                tbUsuariosCodigo.getPsTareasList().remove(psTareas);
                tbUsuariosCodigo = em.merge(tbUsuariosCodigo);
            }
            em.remove(psTareas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PsTareas> findPsTareasEntities() {
        return findPsTareasEntities(true, -1, -1);
    }

    public List<PsTareas> findPsTareasEntities(int maxResults, int firstResult) {
        return findPsTareasEntities(false, maxResults, firstResult);
    }

    private List<PsTareas> findPsTareasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PsTareas.class));
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

    public PsTareas findPsTareas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PsTareas.class, id);
        } finally {
            em.close();
        }
    }

    public int getPsTareasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PsTareas> rt = cq.from(PsTareas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
