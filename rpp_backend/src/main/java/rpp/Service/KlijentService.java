package rpp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rpp.Repository.KlijentRepository;
import rpp.jpa.Klijent;

@Service
public class KlijentService {

	@Autowired
	private KlijentRepository klijentRepository;
	
	public List<Klijent> getAll(){
		return klijentRepository.findAll();
	}
	
	public Optional<Klijent> findById(Integer id){
		return klijentRepository.findById(id);
	}
	
	public List<Klijent> findByImeContainingIgnoreCase(String ime){
		return klijentRepository.findByImeContainingIgnoreCase(ime);
	}
	
	public Klijent save(Klijent klijent) {
		return klijentRepository.save(klijent);
	}
	
	public boolean existsById(Integer id) {
		return klijentRepository.existsById(id);
	}
	
	public void deleteById(Integer id) {
		klijentRepository.deleteById(id);
	}
}
