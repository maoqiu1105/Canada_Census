package prog3060.wang.Bean;

import javax.ejb.Local;
import javax.persistence.EntityManager;

@Local
public interface ConnectionBeanLocal
{

    boolean isEntityManagerFactoryReady();
    public EntityManager createEntityManager();

}