package es.urjc.code.daw;


public class Match {
	
	public String localTeam;
	public String visitantTeam;
	public String localTeamLogo;
	public String visitantTeamLogo;
	public String dateMatch;
	public String result;
	
	public Match(String localTeam, String visitantTeam) {
		this.localTeam = localTeam;
		this.visitantTeam = visitantTeam;
	}
	
	public Match(String localTeam, String visitantTeam, String dateMatch) {
		this.localTeam = localTeam;
		this.visitantTeam = visitantTeam;
		this.dateMatch = dateMatch;
	}
	
	public Match(String localTeam, String visitantTeam, String dateMatch, String localTeamLogo, String visitantTeamLogo) {
		this.localTeam = localTeam;
		this.visitantTeam = visitantTeam;
		this.dateMatch = dateMatch;
		this.localTeamLogo = localTeamLogo;
		this.visitantTeamLogo = visitantTeamLogo;
	}
	
	public Match(String localTeam, String visitantTeam, String dateMatch, String result) {
		super();
		this.localTeam = localTeam;
		this.visitantTeam = visitantTeam;
		this.dateMatch = dateMatch;
		this.result = result;
	}
	public String getLocalTeam() {
		return localTeam;
	}
	public void setLocalTeam(String localTeam) {
		this.localTeam = localTeam;
	}
	public String getVisitantTeam() {
		return visitantTeam;
	}
	public void setVisitantTeam(String visitantTeam) {
		this.visitantTeam = visitantTeam;
	}
	public String getLocalTeamLogo() {
		return localTeamLogo;
	}
	public void setLocalTeamLogo(String localTeamLogo) {
		this.localTeam = localTeamLogo;
	}
	public String getVisitantTeamLogo() {
		return visitantTeamLogo;
	}
	public void setVisitantTeamLogo(String visitantTeamLogo) {
		this.visitantTeamLogo = visitantTeamLogo;
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
	@Override
	public String toString() {
		return "Matches [localTeam=" + localTeam + ", localTeamLogo=" + localTeamLogo + ", visitantTeam=" + visitantTeam + ", visitantTeamLogo=" + visitantTeamLogo + ", dateMatch=" + dateMatch
				+ ", result=" + result  + "]";
	}
	
	
}
