package es.urjc.code.daw;

import es.urjc.code.daw.team.Team;

public class Match {
	
	public Team localTeam;
	public Team visitantTeam;
	public String dateMatch;
	public String result;
	public String betSelected;
	public String betLocal;
	public String betVisit;
	public String betTied;
	
	public Match(Team localTeam, Team visitantTeam) {
		super();
		this.localTeam = localTeam;
		this.visitantTeam = visitantTeam;
	}
	
	public Match(Team localTeam, Team visitantTeam, String dateMatch) {
		super();
		this.localTeam = localTeam;
		this.visitantTeam = visitantTeam;
		this.dateMatch = dateMatch;
	}

	public Match(Team localTeam, Team visitantTeam, String dateMatch, String result) {
		super();
		this.localTeam = localTeam;
		this.visitantTeam = visitantTeam;
		this.dateMatch = dateMatch;
		this.result = result;
	}
	
	public Team getLocalTeam() {
		return localTeam;
	}
	public void setLocalTeam(Team localTeam) {
		this.localTeam = localTeam;
	}
	public Team getVisitantTeam() {
		return visitantTeam;
	}
	public void setVisitantTeam(Team visitantTeam) {
		this.visitantTeam = visitantTeam;
	}
	public String getDateMatch() {
		return dateMatch;
	}
	public void setDateMatch(String dateMatch) {
		this.dateMatch = dateMatch;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getBetLocal() {
		return betLocal;
	}

	public void setBetLocal(String betLocal) {
		this.betLocal = betLocal;
	}

	public String getBetVisit() {
		return betVisit;
	}

	public void setBetVisit(String betVisit) {
		this.betVisit = betVisit;
	}

	public String getBetTied() {
		return betTied;
	}

	public void setBetTied(String betTied) {
		this.betTied = betTied;
	}
	
	public String getBetSelected() {
		return betSelected;
	}

	public void setBetSelected(String betSelected) {
		this.betSelected = betSelected;
	}

	@Override
	public String toString() {
		return "Match [localTeam=" + localTeam + ", visitantTeam=" + visitantTeam + ", dateMatch=" + dateMatch
				+ ", result=" + result + ", betLocal=" + betLocal + ", betVisit=" + betVisit + ", betTied=" + betTied
				+ "]";
	}
	
	
}
