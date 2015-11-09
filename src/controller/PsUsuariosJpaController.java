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
import model.PsTareasCalificadas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.PsTareas;
import model.PsUsuarios;

/**
 *
 * @author Juan Camilo Giron
 */
public class PsUsuariosJpaController implements Serializable {

    public PsUsuariosJpaController() {
       emf = Persistence.createEntityManagerFactory("Poker_ScrumPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PsUsuarios psUsuarios) {
        if (psUsuarios.getPsTareasCalificadasList() == null) {
            psUsuarios.setPsTareasCalificadasList(new ArrayList<PsTareasCalificadas>());
        }
        if (psUsuarios.getPsTareasList() == null) {
            psUsuarios.setPsTareasList(new ArrayList<PsTareas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PsTareasCalificadas> attachedPsTareasCalificadasList = new ArrayList<PsTareasCalificadas>();
            for (PsTareasCalificadas psTareasCalificadasListPsTareasCalificadasToAttach : psUsuarios.getPsTareasCalificadasList()) {
                psTareasCalificadasListPsTareasCalificadasToAttach = em.getReference(psTareasCalificadasListPsTareasCalificadasToAttach.getClass(), psTareasCalificadasListPsTareasCalificadasToAttach.getNumero());
                attachedPsTareasCalificadasList.add(psTareasCalificadasListPsTareasCalificadasToAttach);
            }
            psUsuarios.setPsTareasCalificadasList(attachedPsTareasCalificadasList);
            List<PsTareas> attachedPsTareasList = new ArrayList<PsTareas>();
            for (PsTareas psTareasListPsTareasToAttach : psUsuarios.getPsTareasList()) {
                psTareasListPsTareasToAttach = em.getReference(psTareasListPsTareasToAttach.getClass(), psTareasListPsTareasToAttach.getNumero());
                attachedPsTareasList.add(psTareasListPsTareasToAttach);
            }
            psUsuarios.setPsTareasList(attachedPsTareasList);
            em.persist(psUsuarios);
            for (PsTareasCalificadas psTareasCalificadasListPsTareasCalificadas : psUsuarios.getPsTareasCalificadasList()) {
                PsUsuarios oldTbUsuariosNumeroOfPsTareasCalificadasListPsTareasCalificadas = psTareasCalificadasListPsTareasCalificadas.getTbUsuariosNumero();
                psTareasCalificadasListPsTareasCalificadas.setTbUsuariosNumero(psUsuarios);
                psTareasCalificadasListPsTareasCalificadas = em.merge(psTareasCalificadasListPsTareasCalificadas);
                if (oldTbUsuariosNumeroOfPsTareasCalificadasListPsTareasCalificadas != null) {
                    oldTbUsuariosNumeroOfPsTareasCalificadasListPsTareasCalificadas.getPsTareasCalificadasList().remove(psTareasCalificadasListPsTareasCalificadas);
                    oldTbUsuariosNumeroOfPsTareasCalificadasListPsTareasCalificadas = em.merge(oldTbUsuariosNumeroOfPsTareasCalificadasListPsTareasCalificadas);
                }
            }
            for (PsTareas psTareasListPsTareas : psUsuarios.getPsTareasList()) {
                PsUsuarios oldTbUsuariosCodigoOfPsTareasListPsTareas = psTareasListPsTareas.getTbUsuariosCodigo();
                psTareasListPsTareas.setTbUsuariosCodigo(psUsuarios);
                psTareasListPsTareas = em.merge(psTareasListPsTareas);
                if (oldTbUsuariosCodigoOfPsTareasListPsTareas != null) {
                    oldTbUsuariosCodigoOfPsTareasListPsTareas.getPsTareasList().remove(psTareasListPsTareas);
                    oldTbUsuariosCodigoOfPsTareasListPsTareas = em.merge(oldTbUsuariosCodigoOfPsTareasListPsTareas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PsUsuarios psUsuarios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PsUsuarios persistentPsUsuarios = em.find(PsUsuarios.class, psUsuarios.getNumero());
            List<PsTareasCalificadas> psTareasCalificadasListOld = persistentPsUsuarios.getPsTareasCalificadasList();
            List<PsTareasCalificadas> psTareasCalificadasListNew = psUsuarios.getPsTareasCalificadasList();
            List<PsTareas> psTareasListOld = persistentPsUsuarios.getPsTareasList();
            List<PsTareas> psTareasListNew = psUsuarios.getPsTareasList();
            List<String> illegalOrphanMessages = null;
            for (PsTareasCalificadas psTareasCalificadasListOldPsTareasCalificadas : psTareasCalificadasListOld) {
                if (!psTareasCalificadasListNew.contains(psTareasCalificadasListOldPsTareasCalificadas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PsTareasCalificadas " + psTareasCalificadasListOldPsTareasCalificadas + " since its tbUsuariosNumero field is not nullable.");
                }
            }
            for (PsTareas psTareasListOldPsTareas : psTareasListOld) {
                if (!psTareasListNew.contains(psTareasListOldPsTareas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PsTareas " + psTareasListOldPsTareas + " since its tbUsuariosCodigo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<PsTareasCalificadas> attachedPsTareasCalificadasListNew = new ArrayList<PsTareasCalificadas>();
            for (PsTareasCalificadas psTareasCalificadasListNewPsTareasCalificadasToAttach : psTareasCalificadasListNew) {
                psTareasCalificadasListNewPsTareasCalificadasToAttach = em.getReference(psTareasCalificadasListNewPsTareasCalificadasToAttach.getClass(), psTareasCalificadasListNewPsTareasCalificadasToAttach.getNumero());
                attachedPsTareasCalificadasListNew.add(psTareasCalificadasListNewPsTareasCalificadasToAttach);
            }
            psTareasCalificadasListNew = attachedPsTareasCalificadasListNew;
            psUsuarios.setPsTareasCalificadasList(psTareasCalificadasListNew);
            List<PsTareas> attachedPsTareasListNew = new ArrayList<PsTareas>();
            for (PsTareas psTareasListNewPsTareasToAttach : psTareasListNew) {
                psTareasListNewPsTareasToAttach = em.getReference(psTareasListNewPsTareasToAttach.getClass(), psTareasListNewPsTareasToAttach.getNumero());
                attachedPsTareasListNew.add(psTareasListNewPsTareasToAttach);
            }
            psTareasListNew = attachedPsTareasListNew;
            psUsuarios.setPsTareasList(psTareasListNew);
            psUsuarios = em.merge(psUsuarios);
            for (PsTareasCalificadas psTareasCalificadasListNewPsTareasCalificadas : psTareasCalificadasListNew) {
                if (!psTareasCalificadasListOld.contains(psTareasCalificadasListNewPsTareasCalificadas)) {
                    PsUsuarios oldTbUsuariosNumeroOfPsTareasCalificadasListNewPsTareasCalificadas = psTareasCalificadasListNewPsTareasCalificadas.getTbUsuariosNumero();
                    psTareasCalificadasListNewPsTareasCalificadas.setTbUsuariosNumero(psUsuarios);
                    psTareasCalificadasListNewPsTareasCalificadas = em.merge(psTareasCalificadasListNewPsTareasCalificadas);
                    if (oldTbUsuariosNumeroOfPsTareasCalificadasListNewPsTareasCalificadas != null && !oldTbUsuariosNumeroOfPsTareasCalificadasListNewPsTareasCalificadas.equals(psUsuarios)) {
                        oldTbUsuariosNumeroOfPsTareasCalificadasListNewPsTareasCalificadas.getPsTareasCalificadasList().remove(psTareasCalificadasListNewPsTareasCalificadas);
                        oldTbUsuariosNumeroOfPsTareasCalificadasListNewPsTareasCalificadas = em.merge(oldTbUsuariosNumeroOfPsTareasCalificadasListNewPsTareasCalificadas);
                    }
                }
            }
            for (PsTareas psTareasListNewPsTareas : psTareasListNew) {
                if (!psTareasListOld.contains(psTareasListNewPsTareas)) {
                    PsUsuarios oldTbUsuariosCodigoOfPsTareasListNewPsTareas = psTareasListNewPsTareas.getTbUsuariosCodigo();
                    psTareasListNewPsTareas.setTbUsuariosCodigo(psUsuarios);
                    psTareasListNewPsTareas = em.merge(psTareasListNewPsTareas);
                    if (oldTbUsuariosCodigoOfPsTareasListNewPsTareas != null && !oldTbUsuariosCodigoOfPsTareasListNewPsTareas.equals(psUsuarios)) {
                        oldTbUsuariosCodigoOfPsTareasListNewPsTareas.getPsTareasList().remove(psTareasListNewPsTareas);
                        oldTbUsuariosCodigoOfPsTareasListNewPsTareas = em.merge(oldTbUsuariosCodigoOfPsTareasListNewPsTareas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = psUsuarios.getNumero();
                if (findPsUsuarios(id) == null) {
                    throw new NonexistentEntityException("The psUsuarios with id " + id + " no longer exists.");
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
            PsUsuarios psUsuarios;
            try {
                psUsuarios = em.getReference(PsUsuarios.class, id);
                psUsuarios.getNumero();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The psUsuarios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<PsTareasCalificadas> psTareasCalificadasListOrphanCheck = psUsuarios.getPsTareasCalificadasList();
            for (PsTareasCalificadas psTareasCalificadasListOrphanCheckPsTareasCalificadas : psTareasCalificadasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PsUsuarios (" + psUsuarios + ") cannot be destroyed since the PsTareasCalificadas " + psTareasCalificadasListOrphanCheckPsTareasCalificadas + " in its psTareasCalificadasList field has a non-nullable tbUsuariosNumero field.");
            }
            List<PsTareas> psTareasListOrphanCheck = psUsuarios.getPsTareasList();
            for (PsTareas psTareasListOrphanCheckPsTareas : psTareasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PsUsuarios (" + psUsuarios + ") cannot be destroyed since the PsTareas " + psTareasListOrphanCheckPsTareas + " in its psTareasList field has a non-nullable tbUsuariosCodigo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(psUsuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PsUsuarios> findPsUsuariosEntities() {
        return findPsUsuariosEntities(true, -1, -1);
    }

    public List<PsUsuarios> findPsUsuariosEntities(int maxResults, int firstResult) {
        return findPsUsuariosEntities(false, maxResults, firstResult);
    }

    private List<PsUsuarios> findPsUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PsUsuarios.class));
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

    public PsUsuarios findPsUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PsUsuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getPsUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PsUsuarios> rt = cq.from(PsUsuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
