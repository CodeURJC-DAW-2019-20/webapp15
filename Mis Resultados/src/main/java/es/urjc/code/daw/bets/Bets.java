package es.urjc.code.daw.bets;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import es.urjc.code.daw.Match;
import es.urjc.code.daw.user.User;

@Entity
public class Bets {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@OneToOne
	private User user;
	
	private ArrayList<String> matches = new ArrayList<String>();

	public Bets(User user) {
		super();
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArrayList<String> getMatches() {
		return matches;
	}

	public void setMatches(ArrayList<String> matches) {
		this.matches = matches;
	}

	@Override
	public String toString() {
		return "Bets [id=" + id + ", user=" + user + ", matches=" + matches + "]";
	};
	
	
}
