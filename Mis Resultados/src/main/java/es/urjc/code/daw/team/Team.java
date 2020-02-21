package es.urjc.code.daw.team;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Team {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	//Aqui todos los elementos de la tabla del modelo
	private String name;
	private Integer winners;
	private Integer lossers;
	private Integer tied;
	private Integer points;
	private Integer goalsInFavor;
	private Integer goalsAgainst;
	private Integer position;
	private String league;
	
	//Necesario para la funcionalidad externa de GoogleMaps
	private String direction;
	
	/*
	 * Nombre de los equipos a los que se enfrenta proximamente
	 * Controlado por el administrador
	*/
	private ArrayList<String> matches = new ArrayList<String>();
	
	public  Team() {
	}
	
	
	public Team(String name, Integer winners, Integer lossers, Integer tied, Integer points,
			Integer goalsInFavor, Integer goalsAgainst, Integer position, String league, String direction,
			ArrayList<String> matches) {
		super();
		this.name = name;
		this.winners = winners;
		this.lossers = lossers;
		this.tied = tied;
		this.points = points;
		this.goalsInFavor = goalsInFavor;
		this.goalsAgainst = goalsAgainst;
		this.position = position;
		this.league = league;
		this.direction = direction;
		this.matches = matches;
	}


	public Integer getWinners() {
		return winners;
	}
	public void setWinners(Integer winners) {
		this.winners = winners;
	}
	public Integer getLossers() {
		return lossers;
	}
	public void setLossers(Integer lossers) {
		this.lossers = lossers;
	}
	public Integer getTied() {
		return tied;
	}
	public void setTied(Integer tied) {
		this.tied = tied;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public Integer getGoalsInFavor() {
		return goalsInFavor;
	}
	public void setGoalsInFavor(Integer goalsInFavor) {
		this.goalsInFavor = goalsInFavor;
	}
	public Integer getGoalsAgainst() {
		return goalsAgainst;
	}
	public void setGoalsAgainst(Integer goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public String getLeague() {
		return league;
	}
	public void setLeague(String league) {
		this.league = league;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public ArrayList<String> getMatches() {
		return matches;
	}
	public void setMatches(ArrayList<String> matches) {
		this.matches = matches;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void addMatchByAdmin(String match) {
		this.matches.add(match);
	}

	public void removeMatch(String match) {
		this.matches.remove(match);
	}
	
	public String getMatch(int n) {
		return this.matches.get(n);
	}
	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", winners=" + winners + ", lossers=" + lossers + ", tied=" + tied
				+ ", points=" + points + ", goalsInFavor=" + goalsInFavor + ", goalsAgainst=" + goalsAgainst
				+ ", position=" + position + ", league=" + league + ", direction=" + direction + ", matches=" + matches
				+ "]";
	}

}
