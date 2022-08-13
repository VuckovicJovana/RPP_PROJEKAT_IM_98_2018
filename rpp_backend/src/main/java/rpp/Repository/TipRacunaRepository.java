package rpp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rpp.jpa.TipRacuna;

public interface TipRacunaRepository extends JpaRepository<TipRacuna, Integer> {
	
	List<TipRacuna> findByNazivContainingIgnoreCase(String naziv);

}
