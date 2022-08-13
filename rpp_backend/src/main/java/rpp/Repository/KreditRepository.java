package rpp.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import rpp.jpa.Kredit;

public interface KreditRepository extends JpaRepository<Kredit, Integer>{

	List<Kredit> findByNazivContainingIgnoreCase(String naziv);
}
