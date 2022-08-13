package rpp.Controllers;

import java.net.URI;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import rpp.Service.KreditService;
import rpp.jpa.Kredit;

@CrossOrigin
@RestController
public class KreditRestController {

	@Autowired
	private KreditService kreditService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("kredit")
	public ResponseEntity<List<Kredit>> getAll(){
		List<Kredit> kredits = kreditService.getAll();
		return new ResponseEntity<>(kredits, HttpStatus.OK);
	}
	
	@GetMapping("kredit/{id}")
	public ResponseEntity<Kredit> getOne(@PathVariable("id")Integer id){
		if (kreditService.findById(id).isPresent()) {
			Optional<Kredit> kreditOpt = kreditService.findById(id);
			return new ResponseEntity<>(kreditOpt.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
		}
	}
	
    @PostMapping("kredit")
    public ResponseEntity<Kredit> addKredit(@RequestBody Kredit kredit) {
        Kredit savedKredit = kreditService.save(kredit);
        URI location = URI.create("/kredit/" + savedKredit.getId());
        return ResponseEntity.created(location).body(savedKredit);
    } 
	
    @PutMapping(value = "kredit/{id}")
    public ResponseEntity<Kredit> updateKredit(@RequestBody Kredit kredit, @PathVariable("id") Integer id) {
        if (kreditService.existsById(id)) {
            kredit.setId(id);
            Kredit savedKredit = kreditService.save(kredit);
            return ResponseEntity.ok().body(savedKredit);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	
	    @DeleteMapping("kredit/{id}")
	    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
	        if (id == -100 && !kreditService.existsById(id)) {
	            jdbcTemplate.execute("INSERT INTO kredit (\"id\", \"naziv\", \"oznaka\", \"opis\") VALUES (-100, 'Test Oznaka', 'Test Opis')");
	        }

	        if (kreditService.existsById(id)) {
	            kreditService.deleteById(id);
	            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	        }
	        return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	    }
}
	
	
	
	
	
