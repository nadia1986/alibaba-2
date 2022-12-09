/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooitec1.alibaba2.entity.repository;

import com.pooitec1.alibaba2.entity.Sale;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.pooitec1.alibaba2.entity.SaleLine;
import com.pooitec1.alibaba2.entity.repository.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nadia
 */
public class SaleRepository implements Serializable {

    public SaleRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sale sale) {
        if (sale.getSaleLines() == null) {
            sale.setSaleLines(new ArrayList<SaleLine>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SaleLine> attachedSaleLines = new ArrayList<SaleLine>();
            for (SaleLine saleLinesSaleLineToAttach : sale.getSaleLines()) {
                saleLinesSaleLineToAttach = em.getReference(saleLinesSaleLineToAttach.getClass(), saleLinesSaleLineToAttach.getId());
                attachedSaleLines.add(saleLinesSaleLineToAttach);
            }
            sale.setSaleLines(attachedSaleLines);
            em.persist(sale);
            for (SaleLine saleLinesSaleLine : sale.getSaleLines()) {
                Sale oldSaleOfSaleLinesSaleLine = saleLinesSaleLine.getSale();
                saleLinesSaleLine.setSale(sale);
                saleLinesSaleLine = em.merge(saleLinesSaleLine);
                if (oldSaleOfSaleLinesSaleLine != null) {
                    oldSaleOfSaleLinesSaleLine.getSaleLines().remove(saleLinesSaleLine);
                    oldSaleOfSaleLinesSaleLine = em.merge(oldSaleOfSaleLinesSaleLine);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sale sale) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sale persistentSale = em.find(Sale.class, sale.getSaleId());
            List<SaleLine> saleLinesOld = persistentSale.getSaleLines();
            List<SaleLine> saleLinesNew = sale.getSaleLines();
            List<SaleLine> attachedSaleLinesNew = new ArrayList<SaleLine>();
            for (SaleLine saleLinesNewSaleLineToAttach : saleLinesNew) {
                saleLinesNewSaleLineToAttach = em.getReference(saleLinesNewSaleLineToAttach.getClass(), saleLinesNewSaleLineToAttach.getId());
                attachedSaleLinesNew.add(saleLinesNewSaleLineToAttach);
            }
            saleLinesNew = attachedSaleLinesNew;
            sale.setSaleLines(saleLinesNew);
            sale = em.merge(sale);
            for (SaleLine saleLinesOldSaleLine : saleLinesOld) {
                if (!saleLinesNew.contains(saleLinesOldSaleLine)) {
                    saleLinesOldSaleLine.setSale(null);
                    saleLinesOldSaleLine = em.merge(saleLinesOldSaleLine);
                }
            }
            for (SaleLine saleLinesNewSaleLine : saleLinesNew) {
                if (!saleLinesOld.contains(saleLinesNewSaleLine)) {
                    Sale oldSaleOfSaleLinesNewSaleLine = saleLinesNewSaleLine.getSale();
                    saleLinesNewSaleLine.setSale(sale);
                    saleLinesNewSaleLine = em.merge(saleLinesNewSaleLine);
                    if (oldSaleOfSaleLinesNewSaleLine != null && !oldSaleOfSaleLinesNewSaleLine.equals(sale)) {
                        oldSaleOfSaleLinesNewSaleLine.getSaleLines().remove(saleLinesNewSaleLine);
                        oldSaleOfSaleLinesNewSaleLine = em.merge(oldSaleOfSaleLinesNewSaleLine);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = sale.getSaleId();
                if (findSale(id) == null) {
                    throw new NonexistentEntityException("The sale with id " + id + " no longer exists.");
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
            Sale sale;
            try {
                sale = em.getReference(Sale.class, id);
                sale.getSaleId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sale with id " + id + " no longer exists.", enfe);
            }
            List<SaleLine> saleLines = sale.getSaleLines();
            for (SaleLine saleLinesSaleLine : saleLines) {
                saleLinesSaleLine.setSale(null);
                saleLinesSaleLine = em.merge(saleLinesSaleLine);
            }
            em.remove(sale);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sale> findSaleEntities() {
        return findSaleEntities(true, -1, -1);
    }

    public List<Sale> findSaleEntities(int maxResults, int firstResult) {
        return findSaleEntities(false, maxResults, firstResult);
    }

    private List<Sale> findSaleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sale.class));
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

    public Sale findSale(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sale.class, id);
        } finally {
            em.close();
        }
    }

    public int getSaleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sale> rt = cq.from(Sale.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
