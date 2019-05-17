package prog3060.wang.Bean;

import javax.ejb.Local;
import javax.persistence.EntityManager;

import prog3060.wang.CanadaCensusDB.AgeGroup;

@Local
public interface AgeBeanLocal {
	
	public String GetAgeResult(EntityManager entityManager, AgeGroup ageGroup);
}
