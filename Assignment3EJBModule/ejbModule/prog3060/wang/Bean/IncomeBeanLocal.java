package prog3060.wang.Bean;

import javax.ejb.Local;
import javax.persistence.EntityManager;

import prog3060.wang.CanadaCensusDB.CensusYear;
import prog3060.wang.CanadaCensusDB.GeographicArea;

@Local
public interface IncomeBeanLocal {
	public String GetIncoemResult(EntityManager tempEntityManager, GeographicArea geographicArea, CensusYear censusYear);
}
