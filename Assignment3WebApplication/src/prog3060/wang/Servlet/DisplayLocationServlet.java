package prog3060.wang.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import prog3060.wang.Bean.ConnectionBeanLocal;
import prog3060.wang.Bean.IncomeBeanLocal;
import prog3060.wang.CanadaCensusDB.GeographicArea;

/**
 * Servlet implementation class DisplayLocationServlet
 */
@WebServlet("/DisplayLocationServlet")
public class DisplayLocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 @EJB
	 ConnectionBeanLocal connectionBean;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayLocationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<GeographicArea> nameCountry = new ArrayList<GeographicArea>();
		ArrayList<GeographicArea> nameProvince = new ArrayList<GeographicArea>();
		ArrayList<GeographicArea> nameCity = new ArrayList<GeographicArea>();
		ArrayList<GeographicArea> nameArea = new ArrayList<GeographicArea>();
        EntityManager tempEntityManager = null;
        String HQLQuery = "From GeographicArea";
        
        try
        {
            tempEntityManager = connectionBean.createEntityManager();
            
// Start a new transaction
            tempEntityManager.getTransaction().begin();
            
            Query tempQuery = tempEntityManager.createQuery(HQLQuery);
            List<GeographicArea> tempResult = tempQuery.getResultList();
            
// Generate and retrieve the output
            for(GeographicArea tempGeo: tempResult) {
            	switch (tempGeo.getLevel()) {
            	case 0: 
            		nameCountry.add(tempGeo);
            		break;
            	case 1:
            		nameProvince.add(tempGeo);
            		break;
            	case 2:
            		nameCity.add(tempGeo);
            		break;
            	default:
            		nameArea.add(tempGeo);
            		break;
            	}
            }
            
// Handle the transaction
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

// Store the output in the location that index.jsp will look for it
        session.setAttribute("nameCountry", nameCountry);
        session.setAttribute("nameProvince", nameProvince);
        session.setAttribute("nameCity", nameCity);
        session.setAttribute("nameArea", nameArea);
        
		request.getRequestDispatcher("/Income.jsp").forward(request, response);
	}
	
	
}
