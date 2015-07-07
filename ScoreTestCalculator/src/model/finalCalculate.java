package model;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class finalCalculate
 */
@WebServlet("/finalCalculate")
public class finalCalculate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public finalCalculate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tableInfo = "";
		 List<Scorecalculate> calculateNumber = getNumbers();
		int counter = 1;
		long total = 0;
		
		//	System.out.println(counter);
		try
		{
			//System.out.println(calculateNumber.size());
			tableInfo = "<tr><td>" + "TestScores" + "<th></th>" + "Date" +  "</td></tr>";
			
			int numberOfElements = calculateNumber.size();
			
			for(int i = 0; i < numberOfElements-1 ; i++)
			{	
				tableInfo += "<tr><td>" + calculateNumber.get(i).getTestscore().longValue() + "<th></th>" + calculateNumber.get(i).getScoredate() + "</td></tr>";
			
			}
			
			
			BigDecimal result = addValue(numberOfElements, calculateNumber);
			
			request.setAttribute("tableInfo", tableInfo);
			request.setAttribute("Total", result);
		}
		catch(Exception e)
		{
			request.setAttribute("message", "<div class='alert alert-danger' role='alert'>Error! Danger Will Robinson Danger! " + e + "</div>");
		}
		
		
		
		
		getServletContext()
		.getRequestDispatcher("/displayFinal.jsp")
		.forward(request, response);
	}
	
	protected BigDecimal addValue(int size,  List<Scorecalculate> calculateNumber)
	{
		long total = 0;	
		try
		{
			
			for(int i = 0; i < size-1 ; i++)
			{
				total +=  calculateNumber.get(i).getTestscore().longValue();
			}
		}
		catch(Exception e)
		{
			e.getMessage();
		}
			
			long average = total / size;
			BigDecimal avgBD = new BigDecimal(average);
		return avgBD;
	}
		
		
	protected static List<Scorecalculate> getNumbers()
	{
		
		EntityManager em = mytools.DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT p FROM  Scorecalculate p";
		TypedQuery<Scorecalculate> q = em.createQuery(qString, Scorecalculate.class);
		List<Scorecalculate> i = null;
		
		try
		{
		
			i = q.getResultList();
			if(i == null || i.isEmpty())
			{
				i = null;
			}
		}
		catch(NoResultException e)
		{
			System.out.println(e);
		}
		
		finally 
		{
			em.close();
		}
	//	System.out.println(i);
		return i;
	}
	

}
