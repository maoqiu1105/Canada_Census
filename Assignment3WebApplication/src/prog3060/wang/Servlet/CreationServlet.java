package prog3060.wang.Servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import prog3060.wang.Bean.ConnectionBeanLocal;
import prog3060.wang.CanadaCensusDB.Age;
import prog3060.wang.CanadaCensusDB.AgeGroup;
import prog3060.wang.CanadaCensusDB.CensusYear;
import prog3060.wang.CanadaCensusDB.GeographicArea;
import prog3060.wang.CanadaCensusDB.Household;
import prog3060.wang.CanadaCensusDB.HouseholdEarners;
import prog3060.wang.CanadaCensusDB.HouseholdSize;
import prog3060.wang.CanadaCensusDB.HouseholdType;
import prog3060.wang.CanadaCensusDB.HouseholdsByAgeRange;
import prog3060.wang.CanadaCensusDB.TotalIncome;

/**
 * Servlet implementation class CreationServlet
 */
@WebServlet("/CreationServlet")
public class CreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	ConnectionBeanLocal connectionBean;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager tempEntityManager = null;
		String action = request.getParameter("action");
		
		try {
			 tempEntityManager = connectionBean.createEntityManager();
	            
				// Start a new transaction
			 tempEntityManager.getTransaction().begin();
			 
				if(action.equals("addGeo")) {
					GeographicArea tempGeographicArea = new GeographicArea();
					
					tempGeographicArea.setCode(Integer.parseInt(request.getParameter("code")));
					tempGeographicArea.setLevel(Integer.parseInt(request.getParameter("level")));
					tempGeographicArea.setName(request.getParameter("name"));
					tempGeographicArea.setAlternativeCode(Integer.parseInt(request.getParameter("alternativeCode")));
					
					tempEntityManager.persist(tempGeographicArea);
					
				}
				
				if(action.equals("addAge")) {
					Age tempAge = new Age();
					
					AgeGroup tempAgeGroup = tempEntityManager.find(AgeGroup.class, Integer.parseInt(request.getParameter("ageGroup")));
					CensusYear tempCensusYear = tempEntityManager.find(CensusYear.class, Integer.parseInt(request.getParameter("censusYear")));
					GeographicArea tempGeographicArea = tempEntityManager.find(GeographicArea.class, Integer.parseInt(request.getParameter("geographicArea")));
					int male = Integer.parseInt(request.getParameter("male"));
					int female = Integer.parseInt(request.getParameter("female"));
					int combined = male + female;
					
					tempAge.setAgeGroup(tempAgeGroup);
					tempAge.setCensusYear(tempCensusYear);
					tempAge.setCombined(combined);
					tempAge.setMale(male);
					tempAge.setFemale(female);
					tempAge.setGeographicArea(tempGeographicArea);
					
					tempEntityManager.persist(tempAge);
					
				}

				if(action.equals("addHousehold")) {
					Household tempHousehold = new Household();
					
					CensusYear tempCensusYear = tempEntityManager.find(CensusYear.class, Integer.parseInt(request.getParameter("censusYear")));
					GeographicArea tempGeographicArea = tempEntityManager.find(GeographicArea.class, Integer.parseInt(request.getParameter("geographicArea")));
					HouseholdType tempHouseholdType = tempEntityManager.find(HouseholdType.class, Integer.parseInt(request.getParameter("householdType")));
					HouseholdSize tempHouseholdSize = tempEntityManager.find(HouseholdSize.class, Integer.parseInt(request.getParameter("householdSize")));
					HouseholdsByAgeRange tempHouseholdsByAgeRange = tempEntityManager.find(HouseholdsByAgeRange.class, Integer.parseInt(request.getParameter("householdsByAgeRange")));
					HouseholdEarners tempHouseholdEarners = tempEntityManager.find(HouseholdEarners.class, Integer.parseInt(request.getParameter("householdEarners")));
					TotalIncome tempTotalIncome = tempEntityManager.find(TotalIncome.class, Integer.parseInt(request.getParameter("totalIncome")));
					int tempNumberReported = Integer.parseInt("numberReported");
					
					tempHousehold.setCensusYear(tempCensusYear);
					tempHousehold.setGeographicArea(tempGeographicArea);
					tempHousehold.setHouseholdEarners(tempHouseholdEarners);
					tempHousehold.setHouseholdsByAgeRange(tempHouseholdsByAgeRange);
					tempHousehold.setHouseholdSize(tempHouseholdSize);
					tempHousehold.setHouseholdType(tempHouseholdType);
					tempHousehold.setNumberReported(tempNumberReported);
					tempHousehold.setTotalIncome(tempTotalIncome);
				
					tempEntityManager.persist(tempHousehold);
						
				}
				
				tempEntityManager.getTransaction().rollback();
		}
		catch (Exception e)
        {

// Handle the exception
        	if(tempEntityManager != null) {
        		 tempEntityManager.getTransaction().rollback();
        	}

            e.printStackTrace();

            throw new ServletException(e);

        }
        finally
        {

// Free any application managed resources
        	if (tempEntityManager != null)
            {

                tempEntityManager.close();

            }

        }
		
		
		
		
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
