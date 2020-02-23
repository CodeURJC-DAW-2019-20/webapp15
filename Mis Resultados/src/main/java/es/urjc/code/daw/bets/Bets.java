package es.urjc.code.daw.bets;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import es.urjc.code.daw.Match;

@Entity
public class Bets {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String user;
	
	private ArrayList<Match> matches = new ArrayList<Match>();

	public Bets(String user) {
		super();
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public ArrayList<Match> getMatches() {
		return matches;
	}

	public void setMatches(ArrayList<Match> matches) {
		this.matches = matches;
	}

	@Override
	public String toString() {
		return "Bets [id=" + id + ", user=" + user + ", matches=" + matches + "]";
	};
	
	
}
