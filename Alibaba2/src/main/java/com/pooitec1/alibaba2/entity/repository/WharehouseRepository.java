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
import com.pooitec1.alibaba2.entity.Employee;
import java.util.ArrayList;
import java.util.List;
import com.pooitec1.alibaba2.entity.Sector;
import com.pooitec1.alibaba2.entity.Wharehouse;
import com.pooitec1.alibaba2.entity.repository.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nadia
 */
public class WharehouseRepository implements Serializable {

    public WharehouseRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Wharehouse wharehouse) {
        if (wharehouse.getEmployees() == null) {
            wharehouse.setEmployees(new ArrayList<Employee>());
        }
        if (wharehouse.getSectors() == null) {
            wharehouse.setSectors(new ArrayList<Sector>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Employee> attachedEmployees = new ArrayList<Employee>();
            for (Employee employeesEmployeeToAttach : wharehouse.getEmployees()) {
                employeesEmployeeToAttach = em.getReference(employeesEmployeeToAttach.getClass(), employeesEmployeeToAttach.getId());
                attachedEmployees.add(employeesEmployeeToAttach);
            }
            wharehouse.setEmployees(attachedEmployees);
            List<Sector> attachedSectors = new ArrayList<Sector>();
            for (Sector sectorsSectorToAttach : wharehouse.getSectors()) {
                sectorsSectorToAttach = em.getReference(sectorsSectorToAttach.getClass(), sectorsSectorToAttach.getId());
                attachedSectors.add(sectorsSectorToAttach);
            }
            wharehouse.setSectors(attachedSectors);
            em.persist(wharehouse);
            for (Employee employeesEmployee : wharehouse.getEmployees()) {
                Wharehouse oldWharehouseOfEmployeesEmployee = employeesEmployee.getWharehouse();
                employeesEmployee.setWharehouse(wharehouse);
                employeesEmployee = em.merge(employeesEmployee);
                if (oldWharehouseOfEmployeesEmployee != null) {
                    oldWharehouseOfEmployeesEmployee.getEmployees().remove(employeesEmployee);
                    oldWharehouseOfEmployeesEmployee = em.merge(oldWharehouseOfEmployeesEmployee);
                }
            }
            for (Sector sectorsSector : wharehouse.getSectors()) {
                Wharehouse oldWharehouseOfSectorsSector = sectorsSector.getWharehouse();
                sectorsSector.setWharehouse(wharehouse);
                sectorsSector = em.merge(sectorsSector);
                if (oldWharehouseOfSectorsSector != null) {
                    oldWharehouseOfSectorsSector.getSectors().remove(sectorsSector);
                    oldWharehouseOfSectorsSector = em.merge(oldWharehouseOfSectorsSector);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Wharehouse wharehouse) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Wharehouse persistentWharehouse = em.find(Wharehouse.class, wharehouse.getId());
            List<Employee> employeesOld = persistentWharehouse.getEmployees();
            List<Employee> employeesNew = wharehouse.getEmployees();
            List<Sector> sectorsOld = persistentWharehouse.getSectors();
            List<Sector> sectorsNew = wharehouse.getSectors();
            List<Employee> attachedEmployeesNew = new ArrayList<Employee>();
            for (Employee employeesNewEmployeeToAttach : employeesNew) {
                employeesNewEmployeeToAttach = em.getReference(employeesNewEmployeeToAttach.getClass(), employeesNewEmployeeToAttach.getId());
                attachedEmployeesNew.add(employeesNewEmployeeToAttach);
            }
            employeesNew = attachedEmployeesNew;
            wharehouse.setEmployees(employeesNew);
            List<Sector> attachedSectorsNew = new ArrayList<Sector>();
            for (Sector sectorsNewSectorToAttach : sectorsNew) {
                sectorsNewSectorToAttach = em.getReference(sectorsNewSectorToAttach.getClass(), sectorsNewSectorToAttach.getId());
                attachedSectorsNew.add(sectorsNewSectorToAttach);
            }
            sectorsNew = attachedSectorsNew;
            wharehouse.setSectors(sectorsNew);
            wharehouse = em.merge(wharehouse);
            for (Employee employeesOldEmployee : employeesOld) {
                if (!employeesNew.contains(employeesOldEmployee)) {
                    employeesOldEmployee.setWharehouse(null);
                    employeesOldEmployee = em.merge(employeesOldEmployee);
                }
            }
            for (Employee employeesNewEmployee : employeesNew) {
                if (!employeesOld.contains(employeesNewEmployee)) {
                    Wharehouse oldWharehouseOfEmployeesNewEmployee = employeesNewEmployee.getWharehouse();
                    employeesNewEmployee.setWharehouse(wharehouse);
                    employeesNewEmployee = em.merge(employeesNewEmployee);
                    if (oldWharehouseOfEmployeesNewEmployee != null && !oldWharehouseOfEmployeesNewEmployee.equals(wharehouse)) {
                        oldWharehouseOfEmployeesNewEmployee.getEmployees().remove(employeesNewEmployee);
                        oldWharehouseOfEmployeesNewEmployee = em.merge(oldWharehouseOfEmployeesNewEmployee);
                    }
                }
            }
            for (Sector sectorsOldSector : sectorsOld) {
                if (!sectorsNew.contains(sectorsOldSector)) {
                    sectorsOldSector.setWharehouse(null);
                    sectorsOldSector = em.merge(sectorsOldSector);
                }
            }
            for (Sector sectorsNewSector : sectorsNew) {
                if (!sectorsOld.contains(sectorsNewSector)) {
                    Wharehouse oldWharehouseOfSectorsNewSector = sectorsNewSector.getWharehouse();
                    sectorsNewSector.setWharehouse(wharehouse);
                    sectorsNewSector = em.merge(sectorsNewSector);
                    if (oldWharehouseOfSectorsNewSector != null && !oldWharehouseOfSectorsNewSector.equals(wharehouse)) {
                        oldWharehouseOfSectorsNewSector.getSectors().remove(sectorsNewSector);
                        oldWharehouseOfSectorsNewSector = em.merge(oldWharehouseOfSectorsNewSector);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = wharehouse.getId();
                if (findWharehouse(id) == null) {
                    throw new NonexistentEntityException("The wharehouse with id " + id + " no longer exists.");
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
            Wharehouse wharehouse;
            try {
                wharehouse = em.getReference(Wharehouse.class, id);
                wharehouse.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The wharehouse with id " + id + " no longer exists.", enfe);
            }
            List<Employee> employees = wharehouse.getEmployees();
            for (Employee employeesEmployee : employees) {
                employeesEmployee.setWharehouse(null);
                employeesEmployee = em.merge(employeesEmployee);
            }
            List<Sector> sectors = wharehouse.getSectors();
            for (Sector sectorsSector : sectors) {
                sectorsSector.setWharehouse(null);
                sectorsSector = em.merge(sectorsSector);
            }
            em.remove(wharehouse);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Wharehouse> findWharehouseEntities() {
        return findWharehouseEntities(true, -1, -1);
    }

    public List<Wharehouse> findWharehouseEntities(int maxResults, int firstResult) {
        return findWharehouseEntities(false, maxResults, firstResult);
    }

    private List<Wharehouse> findWharehouseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Wharehouse.class));
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

    public Wharehouse findWharehouse(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Wharehouse.class, id);
        } finally {
            em.close();
        }
    }

    public int getWharehouseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Wharehouse> rt = cq.from(Wharehouse.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
