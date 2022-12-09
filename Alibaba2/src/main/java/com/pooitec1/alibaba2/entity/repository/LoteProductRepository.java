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
import com.pooitec1.alibaba2.entity.Sector;
import com.pooitec1.alibaba2.entity.LoteProduct;
import com.pooitec1.alibaba2.entity.repository.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nadia
 */
public class LoteProductRepository implements Serializable {

    public LoteProductRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LoteProduct stockProduct) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sector sector = stockProduct.getSector();
            if (sector != null) {
                sector = em.getReference(sector.getClass(), sector.getId());
                stockProduct.setSector(sector);
            }
            em.persist(stockProduct);
            if (sector != null) {
                sector.getStocksProcucts().add(stockProduct);
                sector = em.merge(sector);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LoteProduct stockProduct) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LoteProduct persistentStockProduct = em.find(LoteProduct.class, stockProduct.getIdLote());
            Sector sectorOld = persistentStockProduct.getSector();
            Sector sectorNew = stockProduct.getSector();
            if (sectorNew != null) {
                sectorNew = em.getReference(sectorNew.getClass(), sectorNew.getId());
                stockProduct.setSector(sectorNew);
            }
            stockProduct = em.merge(stockProduct);
            if (sectorOld != null && !sectorOld.equals(sectorNew)) {
                sectorOld.getStocksProcucts().remove(stockProduct);
                sectorOld = em.merge(sectorOld);
            }
            if (sectorNew != null && !sectorNew.equals(sectorOld)) {
                sectorNew.getStocksProcucts().add(stockProduct);
                sectorNew = em.merge(sectorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = stockProduct.getIdLote();
                if (findStockProduct(id) == null) {
                    throw new NonexistentEntityException("The stockProduct with id " + id + " no longer exists.");
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
            LoteProduct stockProduct;
            try {
                stockProduct = em.getReference(LoteProduct.class, id);
                stockProduct.getIdLote();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The stockProduct with id " + id + " no longer exists.", enfe);
            }
            Sector sector = stockProduct.getSector();
            if (sector != null) {
                sector.getStocksProcucts().remove(stockProduct);
                sector = em.merge(sector);
            }
            em.remove(stockProduct);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LoteProduct> findStockProductEntities() {
        return findStockProductEntities(true, -1, -1);
    }

    public List<LoteProduct> findStockProductEntities(int maxResults, int firstResult) {
        return findStockProductEntities(false, maxResults, firstResult);
    }

    private List<LoteProduct> findStockProductEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LoteProduct.class));
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

    public LoteProduct findStockProduct(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LoteProduct.class, id);
        } finally {
            em.close();
        }
    }

    public int getStockProductCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LoteProduct> rt = cq.from(LoteProduct.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
