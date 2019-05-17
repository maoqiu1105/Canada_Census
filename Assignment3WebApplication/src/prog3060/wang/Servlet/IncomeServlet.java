package prog3060.wang.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import prog3060.wang.Bean.ConnectionBeanLocal;
import prog3060.wang.Bean.IncomeBeanLocal;
import prog3060.wang.CanadaCensusDB.*;

/**
 * Servlet implementation class IncomeServlet
 */
@WebServlet("/IncomeServlet")
public class IncomeServlet extends HttpServlet {
	/**
     *  Generated serial version ID
     */
    private static final long serialVersionUID = -5149297429245784091L;

    @EJB
    ConnectionBeanLocal connectionBean;
    @EJB
    IncomeBeanLocal incomeBean;
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncomeServlet()
    {

        super();

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	String tempOutput = null;

        HttpSession session = request.getSession();
        EntityManager tempEntityManager = null;
        int geoLocationID = Integer.parseInt(request.getParameter("id")) ;
        int censusYearID = Integer.parseInt(request.getParameter("ceneusYear"));
        
        try
        {

            tempEntityManager = connectionBean.createEntityManager();
            
// Start a new transaction
            tempEntityManager.getTransaction().begin();
            
            GeographicArea tempGeographicArea = tempEntityManager.find(GeographicArea.class, geoLocationID);
            CensusYear tempCensusYear = tempEntityManager.find(CensusYear.class, censusYearID);
            
           tempOutput = incomeBean.GetIncoemResult(
            		tempEntityManager,
            		tempGeographicArea,
            		tempCensusYear
            		);

// Handle the transaction


        }
        catch (Exception e)
        {

// Handle the exception


            e.printStackTrace();

            throw new ServletException(e);

        }
        finally
        {

// Free any application managed resources


        }

// Store the output in the location that finalExamOutput.jsp will look for it

        session.setAttribute("IncomeReport", tempOutput);
        request.getRequestDispatcher("/Income.jsp").forward(request, response);

    }

}




