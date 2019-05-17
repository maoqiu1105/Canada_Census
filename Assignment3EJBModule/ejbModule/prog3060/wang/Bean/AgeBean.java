package prog3060.wang.Bean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sun.xml.ws.developer.Stateful;

import prog3060.wang.CanadaCensusDB.Age;
import prog3060.wang.CanadaCensusDB.AgeGroup;
import prog3060.wang.CanadaCensusDB.CensusYear;
import prog3060.wang.CanadaCensusDB.GeographicArea;

/**
 * Session Bean implementation class AgeBean
 */
@Stateless
@LocalBean
public class AgeBean implements AgeBeanLocal {

    /**
     * Default constructor. 
     */
    public AgeBean() {
        // TODO Auto-generated constructor stub
    }

    public String GetAgeResult(EntityManager entityManager, AgeGroup ageGroup) {
    	String tempOutput = null;
    	String growth;
    	String tempJPQLSelectQuery = "SELECT a1, a2, ag, c1, c2, g, "
    			+ "(CAST((a1.combined - a2.combined) AS float) / CAST(a2.combined AS float)) AS growth "
    			+ "FROM Age a1, Age a2 "
    			+ "JOIN a1.ageGroup ag "
    			+ "JOIN a1.censusYear c1 "
    			+ "JOIN a2.censusYear c2 "
    			+ "JOIN a1.geographicArea g "
    			+ "WHERE a2.combined>0 "
    			+ "AND ag.ageGroupID=a2.ageGroup.ageGroupID "
    			+ "AND c1.censusYearID=1 "
    			+ "AND c2.censusYearID=2 "
    			+ "AND g.geographicAreaID=a2.geographicArea.geographicAreaID "
    			+ "AND ag.ageGroupID=:ageGroupID "
    			+ "ORDER BY growth DESC";
    	
    	Query tempQuery = entityManager.createQuery(tempJPQLSelectQuery);
    	tempQuery.setParameter("ageGroupID", ageGroup.getAgeGroupID());
    	tempQuery.setMaxResults(1);
    	List<Object[]> tempResult = tempQuery.getResultList();
    	
    	Age tempAge2016 =(Age) tempResult.get(0)[0];
    	Age tempAge2011 =(Age) tempResult.get(0)[1];
    	AgeGroup tempAgeGroup = (AgeGroup) tempResult.get(0)[2];
    	CensusYear tempCensusYear2016 = (CensusYear) tempResult.get(0)[3];
    	CensusYear tempCensusYear2011 = (CensusYear) tempResult.get(0)[4];
    	GeographicArea tempGeographicArea = (GeographicArea) tempResult.get(0)[5];
    	Object tempGrowth = (Float) tempResult.get(0)[6];
    	
    	growth = String.format("%.02f", (Float) tempGrowth * 100);
    	growth += " %";
    	
    	tempOutput = String.format("%1$s had the largest population growth for the "
    			+ "age group described by \"%2$s\". In 2011 the total population was %3$s "
    			+ "and in 2016, the total population was %4$s. This represents a population in- "
    			+ "crease of %5$s.", 
    			tempGeographicArea.getName(),tempAgeGroup.getDescription(),tempAge2011.getCombined(),tempAge2016.getCombined(),growth);
    	
    	return tempOutput;
    }
}
