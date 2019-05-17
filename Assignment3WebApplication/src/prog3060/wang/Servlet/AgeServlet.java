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

import prog3060.wang.Bean.AgeBeanLocal;
import prog3060.wang.Bean.ConnectionBeanLocal;
import prog3060.wang.CanadaCensusDB.Age;
import prog3060.wang.CanadaCensusDB.AgeGroup;
import prog3060.wang.CanadaCensusDB.CensusYear;
import prog3060.wang.CanadaCensusDB.GeographicArea;	

/**
 * Servlet implementation class AgeServlet
 */
@WebServlet("/AgeServlet")
public class AgeServlet extends HttpServlet {
	//private static final long serialVersionUID = 1L;
	/**
     *  Generated serial version ID
     */
    private static final long serialVersionUID = -5149297429245784091L;

    @EJB
    ConnectionBeanLocal connectionBean;
    @EJB
    AgeBeanLocal ageBean;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgeServlet()
    {

        super();

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	HttpSession session = request.getSession();
    	String tempOutput = null;
    	int ageGroupID = Integer.parseInt(request.getParameter("id"));
        EntityManager tempEntityManager = null;

        try
        {

            tempEntityManager = connectionBean.createEntityManager();
            tempEntityManager.getTransaction().begin();

// Start a new transaction
            AgeGroup tempAgeGroup = tempEntityManager.find(AgeGroup.class, ageGroupID);
            
            tempOutput = ageBean.GetAgeResult(tempEntityManager,tempAgeGroup);
            
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

// Store the output in the location that finalExamOutput.jsp will look for it

        session.setAttribute("GrowthStatement", tempOutput);
        request.getRequestDispatcher("/Age.jsp").forward(request, response);

    }

}






