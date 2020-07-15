package es.urjc.code.daw;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import es.urjc.code.daw.bets.BetRepository;
import es.urjc.code.daw.bets.Bets;
import es.urjc.code.daw.team.Team;
import es.urjc.code.daw.team.TeamRepository;
import es.urjc.code.daw.user.User;
import es.urjc.code.daw.user.UserRepository;

@Service
public class MatchService {
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BetRepository betRepository;
	
	private ArrayList<Match> betMatches = new ArrayList<Match>();

	
	public ArrayList<Match> getBetMatches() {
		return betMatches;
	}

	public void setBetMatches(ArrayList<Match> betMatches) {
		this.betMatches = betMatches;
	}
	public boolean clearBetMatches() {
		betMatches.clear();
		return betMatches.isEmpty();
	}

	public String apostar(String id, String id2, String id3, String id4) {
		Optional<Team> teamAux = teamRepository.findByName(id);

		Team local;

		if (teamAux.isPresent()) {
			local = teamAux.get();
		} else {
			local = new Team();
		}
		teamAux = teamRepository.findByName(id2);

		Team visit;

		if (teamAux.isPresent()) {
			visit = teamAux.get();
		} else {
			visit = new Team();
		}
		Match m1 = new Match(local, visit);

		switch (id4) {
		case "betLocal":
			m1.setBetLocal(id3);
			m1.setBetSelected(id3);
			break;
		case "betTied":
			m1.setBetTied(id3);
			m1.setBetSelected(id3);
			break;
		default:
			m1.setBetVisit(id3);
			m1.setBetSelected(id3);

		}
		boolean repeat = false;
		boolean changeBet = false;
		for (Match b : betMatches) {
			if (b.localTeam.getName().equals(id)) {
				if (b.betSelected.equals(m1.betSelected)) {
					repeat = true;
				} else {
					repeat = false;
					changeBet = true;
					b.setBetSelected(m1.getBetSelected());
				}
			}
		}
		if (!repeat && !changeBet) {
			betMatches.add(m1);
		}

		String totalBet = calculateBetCombinated(betMatches);
		
		return totalBet;

	}
	
	public List<Match> controlNextMatches() {
		List<Team> allTeams = (List<Team>) teamRepository.findAll();
		List<Match> matches = new ArrayList<Match>();

		for (Team t : allTeams) {
				String visitName = t.getMatches().get(0);

				Optional<Team> teamAux = teamRepository.findByName(visitName);

				Team visit;

				if (teamAux.isPresent()) {
					visit = teamAux.get();
				} else {
					visit = new Team();
				}

				Team local = t;

				String horario = generateRandomDate();

				// Generar fecha actual + numero aleatorio
				Match m = new Match(local, visit, horario);

				ArrayList<String> betAvanced = calculateBetAvanced(m);

				boolean search = false;
				for (Match mAux : matches) {
					if (mAux.getLocalTeam().getName().equals(visit.getName())) {
						search = true;

						break;
					}
				}
				if (!search) {
					if (betAvanced.get(0).length() > 5) {
						betAvanced.set(0, betAvanced.get(0).substring(0, 4));
					} else {
						betAvanced.set(0, betAvanced.get(0));
					}
					if (betAvanced.get(1).length() > 5) {
						betAvanced.set(1, betAvanced.get(1).substring(0, 4));
					} else {
						betAvanced.set(1, betAvanced.get(1));
					}
					if (betAvanced.get(2).length() > 5) {
						betAvanced.set(2, betAvanced.get(2).substring(0, 4));
					} else {
						betAvanced.set(2, betAvanced.get(2));
					}
					m.setBetLocal(betAvanced.get(0));
					m.setBetVisit(betAvanced.get(1));
					m.setBetTied(betAvanced.get(2));

					matches.add(m);
				}
		}
		return matches;
	}
	public String generateRandomDate() {

		Calendar c = new GregorianCalendar();

		Integer diaAux = c.get(Calendar.DATE);
		Integer mesAux = c.get(Calendar.MONTH) + 1;
		Integer horaAux = c.get(Calendar.HOUR_OF_DAY);

		Random aleatorio;
		aleatorio = new Random();

		diaAux = aleatorio.nextInt(5) + diaAux;
		horaAux = aleatorio.nextInt(2) + horaAux;

		String dia = Integer.toString(diaAux);
		String mes = Integer.toString(mesAux);
		String hora = Integer.toString(horaAux);
		String año = Integer.toString(c.get(Calendar.YEAR));

		String horario = dia + "/" + mes + "/" + año + " " + hora + ":" + "00";

		return horario;
	}
	
	public ArrayList<String> calculateBetAvanced(Match m1) {
		int pointsLocal = 1;
		int pointsVisit = 1;

		float betLocal;
		float betVisit;
		float betTied;

		Optional<Team> teamAux = teamRepository.findByName(m1.getLocalTeam().getName());
		Optional<Team> teamAux2 = teamRepository.findByName(m1.getVisitantTeam().getName());

		Team local;
		Team visit;

		if (teamAux.isPresent()) {
			local = teamAux.get();
		} else {
			local = new Team();
		}
		if (teamAux2.isPresent()) {
			visit = teamAux2.get();
		} else {
			visit = new Team();
		}
		/*
		 * En caso de empate siempre gana el local Victoria = 10 puntos Menos perdidas =
		 * 7 puntos Más empatadas = 3 puntos Puntos = 7 puntos Goles a favor = 6 puntos
		 * Goles en contra el que menos = 5 puntos Posicion = 9 puntos Local = 3 puntos
		 * Total 50 puntos
		 */
		if (local.getWinners() >= visit.getWinners())
			pointsLocal = pointsLocal + 10;
		else
			pointsVisit = pointsVisit + 10;
		if (local.getLossers() < visit.getLossers())
			pointsLocal = pointsLocal + 6;
		else
			pointsVisit = pointsVisit + 7;
		if (local.getTied() >= visit.getTied())
			pointsLocal = pointsLocal + 3;
		else
			pointsVisit = pointsVisit + 3;
		if (local.getPoints() >= visit.getPoints())
			pointsLocal = pointsLocal + 7;
		else
			pointsVisit = pointsVisit + 7;
		if (local.getGoalsInFavor() >= visit.getGoalsInFavor())
			pointsLocal = pointsLocal + 6;
		else
			pointsVisit = pointsVisit + 6;
		if (local.getGoalsAgainst() < visit.getGoalsAgainst())
			pointsLocal = pointsLocal + 5;
		else
			pointsVisit = pointsVisit + 5;
		if (local.getPosition() < visit.getPosition())
			pointsLocal = pointsLocal + 9;
		else
			pointsVisit = pointsVisit + 10;

		/* Condicion de local */
		pointsLocal = pointsLocal + 3;

		betLocal = 3 - (float) ((float) pointsLocal / 50) * 3;
		betVisit = 3 - (float) ((float) pointsVisit / 50) * 3;
		betTied = (betLocal + betVisit) / 2;

		if (pointsLocal - pointsVisit >= 10) {
			if (pointsLocal >= pointsVisit * 2) {
				betVisit = betVisit + 3;
			} else {
				betVisit = betVisit + 1;
			}
		}
		if (pointsVisit - pointsLocal >= 10) {
			if (pointsLocal >= pointsVisit * 2) {
				betLocal = betLocal + 3;
			} else {
				betLocal = betLocal + 1;
			}
		}

		if (Math.abs(pointsLocal - pointsVisit) < 10) {
			betLocal = betLocal + 1;
			betVisit = betVisit + 1;
		}

		if (pointsLocal > 30) {
			betLocal = betLocal + 1;
			betTied = (betLocal + betVisit) / 2;
		}
		if (pointsVisit > 30) {
			betVisit = betVisit + 1;
			betTied = (betLocal + betVisit) / 2;
		}

		ArrayList<String> bets = new ArrayList<String>();

		bets.add(betLocal + "");
		bets.add(betVisit + "");
		bets.add(betTied + "");

		return bets;
	}

	
	public String calculateBetCombinated(ArrayList<Match> betMatches2) {
		float total = 0;
		float totalAux = 0;
		for (Match b : betMatches2) {
			totalAux = Float.parseFloat(b.getBetSelected());
			if (total == 0) {
				total = totalAux;
			} else {
				total = total * totalAux;
			}
		}
		return total + "";
	}

	public boolean doBet(String totalBet,String name) {
		List<Match> matches;

		matches = controlNextMatches();

		User u = userRepository.findByName(name);

		Optional<ArrayList<Bets>> betList;

		betList = betRepository.findByUser(u);

		boolean result = generateRandomResult();

		Integer auxMoney = u.getAcc_balance();
		Integer totalBetAux = Math.round(Float.parseFloat(totalBet));

		if (result) {
			auxMoney = auxMoney + totalBetAux;
			userRepository.updateMoneyUser(auxMoney, name);
		} else {
			auxMoney = auxMoney - totalBetAux;
			userRepository.updateMoneyUser(auxMoney, name);
		}

		Bets b = new Bets(u);

		ArrayList<String> auxMatches = new ArrayList<String>();

		for (Match m : betMatches) {
			if (result) {
				auxMatches.add(
						m.localTeam.getName() + " vs " + m.visitantTeam.getName() + " Ganado: " + totalBetAux + "€");
			} else {
				auxMatches.add(
						m.localTeam.getName() + " vs " + m.visitantTeam.getName() + " Perdido: " + totalBetAux + "€");
			}
		}

		b.setMatches(auxMatches);

		betRepository.save(b);

		for (Match bAux : betMatches) {
			Optional<Team> teamAux = teamRepository.findByName(bAux.getLocalTeam().getName());

			Team team;
			if (teamAux.isPresent()) {
				team = teamAux.get();
			} else {
				team = new Team();
			}
			team.removeMatch(bAux.getVisitantTeam().getName());
			teamRepository.save(team);

			Optional<Team> teamAux2 = teamRepository.findByName(bAux.getVisitantTeam().getName());

			Team team2;
			if (teamAux2.isPresent()) {
				team2 = teamAux2.get();
			} else {
				team2 = new Team();
			}
			team2.removeMatch(bAux.getLocalTeam().getName());
			teamRepository.save(team2);

		}

		for (Match bAux : betMatches) {
			bAux.getLocalTeam().getMatches().clear();
			bAux.getVisitantTeam().getMatches().clear();
		}

		betMatches.clear();
		
		return result;
	}
	
	private boolean generateRandomResult() {
		boolean aux = true;

		Random aleatorio = new Random();

		int auxInt;

		for (Match m : getBetMatches()) {
			auxInt = aleatorio.nextInt(3);
			if (auxInt == 0) {
				if (m.betLocal == null) {
					aux = false;
				} else {
					Optional<Team> teamAux = teamRepository.findByName(m.getLocalTeam().getName());

					Team team;
					if (teamAux.isPresent()) {
						team = teamAux.get();
					} else {
						team = new Team();
					}

					teamRepository.updatePoint(team.getPoints() + 3, team.getName());
				}
			} else if (auxInt == 1) {
				if (m.betTied == null) {
					aux = false;
				} else {
					Optional<Team> teamAux = teamRepository.findByName(m.getLocalTeam().getName());

					Team team;
					if (teamAux.isPresent()) {
						team = teamAux.get();
					} else {
						team = new Team();
					}

					teamRepository.updatePoint(team.getPoints() + 1, team.getName());

					Optional<Team> teamAux2 = teamRepository.findByName(m.getVisitantTeam().getName());

					Team team2;
					if (teamAux2.isPresent()) {
						team2 = teamAux2.get();
					} else {
						team2 = new Team();
					}

					teamRepository.updatePoint(team2.getPoints() + 1, team2.getName());
				}

			} else {
				if (m.betVisit == null) {
					aux = false;
				} else {
					Optional<Team> teamAux2 = teamRepository.findByName(m.getVisitantTeam().getName());

					Team team2;
					if (teamAux2.isPresent()) {
						team2 = teamAux2.get();
					} else {
						team2 = new Team();
					}

					teamRepository.updatePoint(team2.getPoints() + 3, team2.getName());
				}
			}
		}

		return aux;
	}


}
