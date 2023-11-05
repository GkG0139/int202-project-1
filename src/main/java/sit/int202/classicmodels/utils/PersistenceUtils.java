package sit.int202.classicmodels.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class PersistenceUtils {

    private static final String PERSISTENCE_UNIT_NAME = "classic-models";
    private static EntityManagerFactory entityManagerFactory;

    private PersistenceUtils() {
    }

    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null || !entityManagerFactory.isOpen()) {
            entityManagerFactory = createEntityManagerFactory();
        }
        return entityManagerFactory.createEntityManager();
    }

    private static EntityManagerFactory createEntityManagerFactory() {
        return Persistence.createEntityManagerFactory(
            PERSISTENCE_UNIT_NAME,
            getPersistenceProperties());
    }

    private static Map<String, String> getPersistenceProperties() {
        Map<String, String> properties = new HashMap<>();
        properties.put("jakarta.persistence.jdbc.user", EnvironmentUtils.getDatabaseUsername());
        properties.put("jakarta.persistence.jdbc.password", EnvironmentUtils.getDatabasePassword());
        return properties;
    }
}
