package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SCORECALCULATE database table.
 * 
 */
@Entity
@Table(name="SCORECALCULATE", schema="GUITARSHOP")
@NamedQuery(name="Scorecalculate.findAll", query="SELECT s FROM Scorecalculate s")
public class Scorecalculate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private long scoreid;

	@Temporal(TemporalType.DATE)
	private Date scoredate;

	private BigDecimal testscore;

	public Scorecalculate() {
	}

	public long getScoreid() {
		return this.scoreid;
	}

	public void setScoreid(long scoreid) {
		this.scoreid = scoreid;
	}

	public Date getScoredate() {
		return this.scoredate;
	}

	public void setScoredate(Date scoredate) {
		this.scoredate = scoredate;
	}

	public BigDecimal getTestscore() {
		return this.testscore;
	}

	public void setTestscore(BigDecimal testscore) {
		this.testscore = testscore;
	}

}