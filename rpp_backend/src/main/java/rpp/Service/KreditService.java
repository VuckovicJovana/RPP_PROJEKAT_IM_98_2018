package rpp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rpp.Repository.KreditRepository;
import rpp.jpa.Kredit;

@Service
public class KreditService {

	@Autowired
	private KreditRepository kreditRepository;
	
	public List<Kredit> getAll(){
		return kreditRepository.findAll();
	}
	
	public Optional<Kredit> findById(Integer id){
		return kreditRepository.findById(id);
	}
	
	public List<Kredit> findByNazivContainingIgnoreCase(String naziv){
		return kreditRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	public Kredit save(Kredit kredit) {
		return kreditRepository.save(kredit);
	}
	
	public boolean existsById(Integer id) {
		return kreditRepository.existsById(id);
	}
	
	public void deleteById(Integer id) {
		kreditRepository.deleteById(id);
	}
}
