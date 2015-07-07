package Test;


import static org.junit.Assert.*;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.junit.Test;

import model.Scorecalculate;
import mytools.DBUtil;


public class getNumberTest {

	@Test
	public void testGetNumbers() {
		BigDecimal bd = new BigDecimal(8);
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		//String firstName = "Fiorello";
		try {
			Scorecalculate sc = em.find(Scorecalculate.class, (long)2151);
			assertEquals(sc.getTestscore(), bd);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		} finally
		{
			em.close();
			
		}
		
	}

}
