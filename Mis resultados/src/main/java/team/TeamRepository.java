package team;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
	public Optional<Team> findById(Long id);
	public Optional<Team> findByName(String name);
	//Metodo para insertar en bbdd proximos partidos(admin)
	
	//Metodo para sacar la clasificacion por orden	
	public List<Team> findByOrderPosition(String liga);
}
