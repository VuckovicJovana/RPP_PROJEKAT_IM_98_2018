package rpp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rpp.jpa.Racun;

public interface RacunRepository extends JpaRepository<Racun, Integer>{
	
	List<Racun> findByNazivContainingIgnoreCase(String naziv);

}
