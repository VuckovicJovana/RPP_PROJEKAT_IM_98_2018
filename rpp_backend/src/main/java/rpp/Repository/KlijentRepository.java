package rpp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rpp.jpa.Klijent;

public interface KlijentRepository extends JpaRepository<Klijent, Integer> {
	
	List<Klijent> findByImeContainingIgnoreCase(String ime);

}
