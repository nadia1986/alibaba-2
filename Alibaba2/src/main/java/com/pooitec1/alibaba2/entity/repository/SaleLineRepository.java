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
import com.pooitec1.alibaba2.entity.Sale;
import com.pooitec1.alibaba2.entity.SaleLine;
import com.pooitec1.alibaba2.entity.repository.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nadia
 */
public class SaleLineRepository implements Serializable {

    public SaleLineRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SaleLine saleLine) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sale sale = saleLine.getSale();
            if (sale != null) {
                sale = em.getReference(sale.getClass(), sale.getSaleId());
                saleLine.setSale(sale);
            }
            em.persist(saleLine);
            if (sale != null) {
                sale.getSaleLines().add(saleLine);
                sale = em.merge(sale);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SaleLine saleLine) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SaleLine persistentSaleLine = em.find(SaleLine.class, saleLine.getId());
            Sale saleOld = persistentSaleLine.getSale();
            Sale saleNew = saleLine.getSale();
            if (saleNew != null) {
                saleNew = em.getReference(saleNew.getClass(), saleNew.getSaleId());
                saleLine.setSale(saleNew);
            }
            saleLine = em.merge(saleLine);
            if (saleOld != null && !saleOld.equals(saleNew)) {
                saleOld.getSaleLines().remove(saleLine);
                saleOld = em.merge(saleOld);
            }
            if (saleNew != null && !saleNew.equals(saleOld)) {
                saleNew.getSaleLines().add(saleLine);
                saleNew = em.merge(saleNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = saleLine.getId();
                if (findSaleLine(id) == null) {
                    throw new NonexistentEntityException("The saleLine with id " + id + " no longer exists.");
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
            SaleLine saleLine;
            try {
                saleLine = em.getReference(SaleLine.class, id);
                saleLine.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The saleLine with id " + id + " no longer exists.", enfe);
            }
            Sale sale = saleLine.getSale();
            if (sale != null) {
                sale.getSaleLines().remove(saleLine);
                sale = em.merge(sale);
            }
            em.remove(saleLine);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SaleLine> findSaleLineEntities() {
        return findSaleLineEntities(true, -1, -1);
    }

    public List<SaleLine> findSaleLineEntities(int maxResults, int firstResult) {
        return findSaleLineEntities(false, maxResults, firstResult);
    }

    private List<SaleLine> findSaleLineEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SaleLine.class));
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

    public SaleLine findSaleLine(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SaleLine.class, id);
        } finally {
            em.close();
        }
    }

    public int getSaleLineCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SaleLine> rt = cq.from(SaleLine.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
