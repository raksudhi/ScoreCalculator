package model;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class addNumber
 */
@WebServlet("/addNumber")
public class addNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addNumber() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String number = request.getParameter("score");
		
		List<Scorecalculate> calculateNumber = getNumbers();

	
		EntityManager em = mytools.DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		
		try
		{
			BigDecimal bd = new BigDecimal(number);
			
			Date date = new Date();
			
			model.Scorecalculate calculate = new model.Scorecalculate();
			
			calculate.setTestscore(bd);
			calculate.setScoredate(date);
			 
			em.persist(calculate);
			
			trans.commit();
			
			request.setAttribute("testScore", calculateNumber);
		}
		catch(Exception e)
		{
			System.out.println(e);
			trans.rollback();
		}
		finally
		{
			em.close();
		}
		
		
		
		
		getServletContext()
		.getRequestDispatcher("/displayResult.jsp")
		.forward(request, response);

		
	}
	
	
	protected static List<Scorecalculate> getNumbers()
	{
		
		EntityManager em = mytools.DBUtil.getEmFactory().createEntityManager();
		String qString = "SELECT p FROM  Scorecalculate p";
		TypedQuery<Scorecalculate> q = em.createQuery(qString, Scorecalculate.class);
	//	q.setParameter("prodID", Long.parseLong(prodID));
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
		System.out.println(i);
		
		return i;
	}
	

}
