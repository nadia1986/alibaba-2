/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooitec1.alibaba2.entity.repository;

import com.pooitec1.alibaba2.entity.Purchase;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.pooitec1.alibaba2.entity.PurchaseLine;
import com.pooitec1.alibaba2.entity.repository.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nadia
 */
public class PurchaseRepository implements Serializable {

    public PurchaseRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Purchase purchase) {
        if (purchase.getPurchaseLines() == null) {
            purchase.setPurchaseLines(new ArrayList<PurchaseLine>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PurchaseLine> attachedPurchaseLines = new ArrayList<PurchaseLine>();
            for (PurchaseLine purchaseLinesPurchaseLineToAttach : purchase.getPurchaseLines()) {
                purchaseLinesPurchaseLineToAttach = em.getReference(purchaseLinesPurchaseLineToAttach.getClass(), purchaseLinesPurchaseLineToAttach.getId());
                attachedPurchaseLines.add(purchaseLinesPurchaseLineToAttach);
            }
            purchase.setPurchaseLines(attachedPurchaseLines);
            em.persist(purchase);
            for (PurchaseLine purchaseLinesPurchaseLine : purchase.getPurchaseLines()) {
                Purchase oldPurchaseOfPurchaseLinesPurchaseLine = purchaseLinesPurchaseLine.getPurchase();
                purchaseLinesPurchaseLine.setPurchase(purchase);
                purchaseLinesPurchaseLine = em.merge(purchaseLinesPurchaseLine);
                if (oldPurchaseOfPurchaseLinesPurchaseLine != null) {
                    oldPurchaseOfPurchaseLinesPurchaseLine.getPurchaseLines().remove(purchaseLinesPurchaseLine);
                    oldPurchaseOfPurchaseLinesPurchaseLine = em.merge(oldPurchaseOfPurchaseLinesPurchaseLine);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Purchase purchase) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Purchase persistentPurchase = em.find(Purchase.class, purchase.getPurchaseId());
            List<PurchaseLine> purchaseLinesOld = persistentPurchase.getPurchaseLines();
            List<PurchaseLine> purchaseLinesNew = purchase.getPurchaseLines();
            List<PurchaseLine> attachedPurchaseLinesNew = new ArrayList<PurchaseLine>();
            for (PurchaseLine purchaseLinesNewPurchaseLineToAttach : purchaseLinesNew) {
                purchaseLinesNewPurchaseLineToAttach = em.getReference(purchaseLinesNewPurchaseLineToAttach.getClass(), purchaseLinesNewPurchaseLineToAttach.getId());
                attachedPurchaseLinesNew.add(purchaseLinesNewPurchaseLineToAttach);
            }
            purchaseLinesNew = attachedPurchaseLinesNew;
            purchase.setPurchaseLines(purchaseLinesNew);
            purchase = em.merge(purchase);
            for (PurchaseLine purchaseLinesOldPurchaseLine : purchaseLinesOld) {
                if (!purchaseLinesNew.contains(purchaseLinesOldPurchaseLine)) {
                    purchaseLinesOldPurchaseLine.setPurchase(null);
                    purchaseLinesOldPurchaseLine = em.merge(purchaseLinesOldPurchaseLine);
                }
            }
            for (PurchaseLine purchaseLinesNewPurchaseLine : purchaseLinesNew) {
                if (!purchaseLinesOld.contains(purchaseLinesNewPurchaseLine)) {
                    Purchase oldPurchaseOfPurchaseLinesNewPurchaseLine = purchaseLinesNewPurchaseLine.getPurchase();
                    purchaseLinesNewPurchaseLine.setPurchase(purchase);
                    purchaseLinesNewPurchaseLine = em.merge(purchaseLinesNewPurchaseLine);
                    if (oldPurchaseOfPurchaseLinesNewPurchaseLine != null && !oldPurchaseOfPurchaseLinesNewPurchaseLine.equals(purchase)) {
                        oldPurchaseOfPurchaseLinesNewPurchaseLine.getPurchaseLines().remove(purchaseLinesNewPurchaseLine);
                        oldPurchaseOfPurchaseLinesNewPurchaseLine = em.merge(oldPurchaseOfPurchaseLinesNewPurchaseLine);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = purchase.getPurchaseId();
                if (findPurchase(id) == null) {
                    throw new NonexistentEntityException("The purchase with id " + id + " no longer exists.");
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
            Purchase purchase;
            try {
                purchase = em.getReference(Purchase.class, id);
                purchase.getPurchaseId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The purchase with id " + id + " no longer exists.", enfe);
            }
            List<PurchaseLine> purchaseLines = purchase.getPurchaseLines();
            for (PurchaseLine purchaseLinesPurchaseLine : purchaseLines) {
                purchaseLinesPurchaseLine.setPurchase(null);
                purchaseLinesPurchaseLine = em.merge(purchaseLinesPurchaseLine);
            }
            em.remove(purchase);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Purchase> findPurchaseEntities() {
        return findPurchaseEntities(true, -1, -1);
    }

    public List<Purchase> findPurchaseEntities(int maxResults, int firstResult) {
        return findPurchaseEntities(false, maxResults, firstResult);
    }

    private List<Purchase> findPurchaseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Purchase.class));
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

    public Purchase findPurchase(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Purchase.class, id);
        } finally {
            em.close();
        }
    }

    public int getPurchaseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Purchase> rt = cq.from(Purchase.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
