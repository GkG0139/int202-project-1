package sit.int202.classicmodels.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import sit.int202.classicmodels.models.Office;
import sit.int202.classicmodels.utils.PersistenceUtils;

public class OfficeRepository {

    private static EntityManager entityManager;

    private static EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = PersistenceUtils.getEntityManager();
        }
        return entityManager;
    }

    public List<Office> findAll() {
        TypedQuery<Office> query = getEntityManager().createNamedQuery(
            "Office.findAll",
            Office.class);
        return query.getResultList();
    }

    public Office find(String officeCode) {
        return getEntityManager().find(Office.class, officeCode);
    }

    public boolean insert(Office office) {
        try {
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            em.persist(office);
            em.getTransaction().commit();
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public boolean delete(Office office) {
        if (office == null || !getEntityManager().contains(office)) {
            return false;
        }

        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.remove(office);
        em.getTransaction().commit();
        return true;
    }

    public boolean delete(String officeCode) {
        Office foundOffice = find(officeCode);
        if (foundOffice == null) {
            return false;
        }

        return delete(foundOffice);
    }

    public boolean update(Office updatedOffice) {
        if (updatedOffice == null) {
            return false;
        }

        Office foundOffice = find(updatedOffice.getOfficeCode());
        if (foundOffice == null) {
            return false;
        }

        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        foundOffice.setAddressLine1(updatedOffice.getAddressLine1());
        foundOffice.setAddressLine2(updatedOffice.getAddressLine2());
        foundOffice.setCity(updatedOffice.getCity());
        foundOffice.setState(updatedOffice.getState());
        foundOffice.setCountry(updatedOffice.getCountry());
        foundOffice.setOfficeCode(updatedOffice.getOfficeCode());
        foundOffice.setPostalCode(updatedOffice.getPostalCode());
        foundOffice.setPhone(updatedOffice.getPhone());
        foundOffice.setTerritory(updatedOffice.getTerritory());
        em.getTransaction().commit();
        return true;
    }

    public Integer getNextId() {
        TypedQuery<String> query = getEntityManager().createNamedQuery(
            "Office.findMaxId", String.class);
        String maxOfficeCode = query.getSingleResult();
        if (maxOfficeCode == null || maxOfficeCode.isEmpty()) {
            return 1;
        }
        return Integer.parseInt(maxOfficeCode) + 1;
    }
}
