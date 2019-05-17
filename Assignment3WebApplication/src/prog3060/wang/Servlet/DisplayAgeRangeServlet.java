package prog3060.wang.Servlet;

import java.io.IOException;
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
import prog3060.wang.CanadaCensusDB.AgeGroup;
import prog3060.wang.CanadaCensusDB.GeographicArea;

/**
 * Servlet implementation class DisplayAgeRangeServlet
 */
@WebServlet("/DisplayAgeRangeServlet")
public class DisplayAgeRangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	ConnectionBeanLocal connectionBean;
    public DisplayAgeRangeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<AgeGroup> tempOutput = null;
		EntityManager tempEntityManager = null;
		String HQLQuery = "FROM AgeGroup";
		
		try
	        {
	            tempEntityManager = connectionBean.createEntityManager();
	            
	// Start a new transaction
	            tempEntityManager.getTransaction().begin();
	            
	            Query tempQuery = tempEntityManager.createQuery(HQLQuery);
	            List<AgeGroup> tempResult = tempQuery.getResultList();
	            tempOutput = tempResult;
	            
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
		session.setAttribute("AgeRange", tempOutput);
		request.getRequestDispatcher("/Age.jsp").forward(request, response);
	}

	
}
