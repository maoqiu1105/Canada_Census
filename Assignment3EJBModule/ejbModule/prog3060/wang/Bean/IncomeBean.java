package prog3060.wang.Bean;

import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import prog3060.wang.CanadaCensusDB.CensusYear;
import prog3060.wang.CanadaCensusDB.GeographicArea;
import prog3060.wang.CanadaCensusDB.Household;
import prog3060.wang.CanadaCensusDB.TotalIncome;

/**
 * Session Bean implementation class IncomeBean
 */
@Stateless
@LocalBean
public class IncomeBean implements IncomeBeanLocal {

    /**
     * Default constructor. 
     */
    public IncomeBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String GetIncoemResult(EntityManager tempEntityManager, GeographicArea geographicArea, CensusYear censusYear) {
		 String HQLQuery = "SELECT tt,h FROM Household h "
 				+ "JOIN h.totalIncome tt "
 				+ "WHERE h.householdType = 7 "
 				+ "AND h.householdSize = 3 "
 				+ "AND h.householdEarners = 3 "
 				+ "AND h.totalIncome <> 22 "
 				+ "AND h.geographicArea = :geo "
 				+ "AND h.censusYear = :year "
 				+ "ORDER BY h.numberReported DESC ";
 		
 		Query tempQuery = tempEntityManager.createQuery(HQLQuery);
 		tempQuery.setParameter("geo", geographicArea) ;
 		tempQuery.setParameter("year", censusYear);
 		tempQuery.setMaxResults(1);
 		List<Object[]> tempResult = tempQuery.getResultList();
 		
 		TotalIncome tempTotalIncome = (TotalIncome) tempResult.get(0)[0];
 		Household tempHousehold = (Household) tempResult.get(0)[1];
		
		String tempOutput = null;
		String geoLocation = geographicArea.getName();
		String year = Integer.toString(censusYear.getCensusYear());
		
		
		
		tempOutput = String.format("The total income group with the highest number of households reported by %1$s in %2$s was the one described by \"%3$s\" with %4$s.", 
				geoLocation,year,tempTotalIncome.getDescription(),tempHousehold.getNumberReported()) ;
		
		return tempOutput;
	}

}
