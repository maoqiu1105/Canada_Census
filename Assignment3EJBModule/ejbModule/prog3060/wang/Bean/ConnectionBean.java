package prog3060.wang.Bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Session Bean implementation class ConnectionBean
 */
@Stateless
public class ConnectionBean implements ConnectionBeanLocal
{

    static final String PERSISTENCE_UNIT_NAME = "Assignment3";
    EntityManagerFactory entityManagerFactory;

    public ConnectionBean() throws ClassNotFoundException
    {

        Class.forName("org.apache.derby.jdbc.ClientDriver");

    }

    @PostConstruct
    public void initialize()
    {

        if (!isEntityManagerFactoryReady())
        {

            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        }

    }

    @PreDestroy
    public void close()
    {

        if (isEntityManagerFactoryReady())
        {

            entityManagerFactory.close();
            entityManagerFactory = null;

        }

    }

    @Override
    public boolean isEntityManagerFactoryReady()
    {

        return entityManagerFactory != null && entityManagerFactory.isOpen();

    }

    @Override
    public EntityManager createEntityManager()
    {

        if (!isEntityManagerFactoryReady())
        {

            throw new IllegalStateException("Entity manager factory has not been initialized");

        }

        return entityManagerFactory.createEntityManager();

    }

}



