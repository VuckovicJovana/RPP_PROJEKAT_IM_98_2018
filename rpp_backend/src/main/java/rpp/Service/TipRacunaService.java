package rpp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rpp.Repository.TipRacunaRepository;
import rpp.jpa.TipRacuna;

@Service
public class TipRacunaService {
	
		@Autowired
		private TipRacunaRepository tipRacunaRepository;
		
		public List<TipRacuna> getAll(){
			return tipRacunaRepository.findAll();
		}
		
		public Optional<TipRacuna> findById(Integer id) {
			return tipRacunaRepository.findById(id);
		}
		
		public List<TipRacuna> findByNazivContainingIgnoreCase(String naziv){
	        return tipRacunaRepository.findByNazivContainingIgnoreCase(naziv);
	    }
		
		public TipRacuna save(TipRacuna tipRacuna) {
			return tipRacunaRepository.save(tipRacuna);
		}
		
		public boolean existsById(Integer id) {
			return tipRacunaRepository.existsById(id);
		}
		
		public void deleteById(Integer id) {
			tipRacunaRepository.deleteById(id);
		}
}
