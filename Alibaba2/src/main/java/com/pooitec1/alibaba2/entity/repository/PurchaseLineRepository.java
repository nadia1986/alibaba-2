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
import com.pooitec1.alibaba2.entity.Purchase;
import com.pooitec1.alibaba2.entity.PurchaseLine;
import com.pooitec1.alibaba2.entity.repository.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nadia
 */
public class PurchaseLineRepository implements Serializable {

    public PurchaseLineRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PurchaseLine purchaseLine) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Purchase purchase = purchaseLine.getPurchase();
            if (purchase != null) {
                purchase = em.getReference(purchase.getClass(), purchase.getPurchaseId());
                purchaseLine.setPurchase(purchase);
            }
            em.persist(purchaseLine);
            if (purchase != null) {
                purchase.getPurchaseLines().add(purchaseLine);
                purchase = em.merge(purchase);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PurchaseLine purchaseLine) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PurchaseLine persistentPurchaseLine = em.find(PurchaseLine.class, purchaseLine.getId());
            Purchase purchaseOld = persistentPurchaseLine.getPurchase();
            Purchase purchaseNew = purchaseLine.getPurchase();
            if (purchaseNew != null) {
                purchaseNew = em.getReference(purchaseNew.getClass(), purchaseNew.getPurchaseId());
                purchaseLine.setPurchase(purchaseNew);
            }
            purchaseLine = em.merge(purchaseLine);
            if (purchaseOld != null && !purchaseOld.equals(purchaseNew)) {
                purchaseOld.getPurchaseLines().remove(purchaseLine);
                purchaseOld = em.merge(purchaseOld);
            }
            if (purchaseNew != null && !purchaseNew.equals(purchaseOld)) {
                purchaseNew.getPurchaseLines().add(purchaseLine);
                purchaseNew = em.merge(purchaseNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = purchaseLine.getId();
                if (findPurchaseLine(id) == null) {
                    throw new NonexistentEntityException("The purchaseLine with id " + id + " no longer exists.");
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
            PurchaseLine purchaseLine;
            try {
                purchaseLine = em.getReference(PurchaseLine.class, id);
                purchaseLine.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The purchaseLine with id " + id + " no longer exists.", enfe);
            }
            Purchase purchase = purchaseLine.getPurchase();
            if (purchase != null) {
                purchase.getPurchaseLines().remove(purchaseLine);
                purchase = em.merge(purchase);
            }
            em.remove(purchaseLine);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PurchaseLine> findPurchaseLineEntities() {
        return findPurchaseLineEntities(true, -1, -1);
    }

    public List<PurchaseLine> findPurchaseLineEntities(int maxResults, int firstResult) {
        return findPurchaseLineEntities(false, maxResults, firstResult);
    }

    private List<PurchaseLine> findPurchaseLineEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PurchaseLine.class));
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

    public PurchaseLine findPurchaseLine(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PurchaseLine.class, id);
        } finally {
            em.close();
        }
    }

    public int getPurchaseLineCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PurchaseLine> rt = cq.from(PurchaseLine.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
