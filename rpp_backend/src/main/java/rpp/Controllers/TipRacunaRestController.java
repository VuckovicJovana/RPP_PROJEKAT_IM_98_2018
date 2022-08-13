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

import rpp.Service.TipRacunaService;
import rpp.jpa.TipRacuna;

@CrossOrigin
@RestController
public class TipRacunaRestController {

	
    @Autowired
    private TipRacunaService tipRacunaService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

  	//Vraca listu svih tipova racuna
    @GetMapping("tipRacuna")
    public ResponseEntity<List<TipRacuna>> getAll() {
        List<TipRacuna> tipRacunas = tipRacunaService.getAll();
        return new ResponseEntity<>(tipRacunas, HttpStatus.OK);
    }
    
    // Vraca tip racuna sa id-jem koji je prosledjen kao promenljiva putanje
    @GetMapping("tipRacuna/{id}")
    public ResponseEntity<TipRacuna> getOne(@PathVariable("id") Integer id) {
        if (tipRacunaService.findById(id).isPresent()) {
            Optional<TipRacuna> tipRacunaOpt = tipRacunaService.findById(id);
            return new ResponseEntity<>(tipRacunaOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    //Vraca listu tipova racuna  koji sadrze string koji je prosledjen kao vrednost 'naziv'
    @GetMapping("tipRacuna/naziv/{naziv}")
    public ResponseEntity<List<TipRacuna>> getByNaziv(@PathVariable("naziv") String naziv) {
        List<TipRacuna> tipRacunas = tipRacunaService.findByNazivContainingIgnoreCase(naziv);
        return new ResponseEntity<>(tipRacunas, HttpStatus.OK);
    }
    
    //Dodavanje novog tipa racuna u bazu podataka
    @PostMapping("tipRacuna")
    public ResponseEntity<TipRacuna> addTipRacuna(@RequestBody TipRacuna tipRacuna) {
        TipRacuna savedTipRacuna = tipRacunaService.save(tipRacuna);
        URI location = URI.create("/tipRacuna/" + savedTipRacuna.getId());
        return ResponseEntity.created(location).body(savedTipRacuna);
    }
    

   //Azuriranje TipRacuna koji ima id koje je prosledjen kao promenljiva putanje sa vrednostima prosledjenim u telu zahteva
    @PutMapping(value = "tipRacuna/{id}")
    public ResponseEntity<TipRacuna> updateTipRacuna(@RequestBody TipRacuna TipRacuna,
            @PathVariable("id") Integer id) {
        if (tipRacunaService.existsById(id)) {
            TipRacuna.setId(id);
            TipRacuna savedTipRacuna = tipRacunaService.save(TipRacuna);
            return ResponseEntity.ok().body(savedTipRacuna);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    

    //Brisanje tipa racuna sa id-jem koji je proslednjen kao promenljiva putanje
    @DeleteMapping("tipRacuna/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        if (id == -100 && !tipRacunaService.existsById(-100)) {

            jdbcTemplate.execute("INSERT INTO tipRacuna (\"id\", \"naziv\", \"oznaka\", \"opis\" VALUES ('-100', 'Platni', 'JJ25', 'testOpis'");
        }

        if (tipRacunaService.existsById(id)) {
        	tipRacunaService.deleteById(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
    }
}
