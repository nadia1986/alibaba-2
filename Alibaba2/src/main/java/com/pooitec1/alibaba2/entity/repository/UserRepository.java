/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooitec1.alibaba2.entity.repository;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.pooitec1.alibaba2.entity.Rol;
import com.pooitec1.alibaba2.entity.User;
import com.pooitec1.alibaba2.entity.repository.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nadia
 */
public class UserRepository implements Serializable {

    public UserRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) {
        if (user.getRols() == null) {
            user.setRols(new ArrayList<Rol>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Rol> attachedRols = new ArrayList<Rol>();
            for (Rol rolsRolToAttach : user.getRols()) {
                rolsRolToAttach = em.getReference(rolsRolToAttach.getClass(), rolsRolToAttach.getId());
                attachedRols.add(rolsRolToAttach);
            }
            user.setRols(attachedRols);
            em.persist(user);
            for (Rol rolsRol : user.getRols()) {
                rolsRol.getUsers().add(user);
                rolsRol = em.merge(rolsRol);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User user) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User persistentUser = em.find(User.class, user.getId());
            List<Rol> rolsOld = persistentUser.getRols();
            List<Rol> rolsNew = user.getRols();
            List<Rol> attachedRolsNew = new ArrayList<Rol>();
            for (Rol rolsNewRolToAttach : rolsNew) {
                rolsNewRolToAttach = em.getReference(rolsNewRolToAttach.getClass(), rolsNewRolToAttach.getId());
                attachedRolsNew.add(rolsNewRolToAttach);
            }
            rolsNew = attachedRolsNew;
            user.setRols(rolsNew);
            user = em.merge(user);
            for (Rol rolsOldRol : rolsOld) {
                if (!rolsNew.contains(rolsOldRol)) {
                    rolsOldRol.getUsers().remove(user);
                    rolsOldRol = em.merge(rolsOldRol);
                }
            }
            for (Rol rolsNewRol : rolsNew) {
                if (!rolsOld.contains(rolsNewRol)) {
                    rolsNewRol.getUsers().add(user);
                    rolsNewRol = em.merge(rolsNewRol);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = user.getId();
                if (findUser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            List<Rol> rols = user.getRols();
            for (Rol rolsRol : rols) {
                rolsRol.getUsers().remove(user);
                rolsRol = em.merge(rolsRol);
            }
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
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

    public User findUser(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
