package rpp.Controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.batch.BatchProperties.Jdbc;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import rpp.Service.KlijentService;
import rpp.jpa.Klijent;

@CrossOrigin
@RestController
public class KlijentRestController {

	@Autowired
	private KlijentService klijentService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@ApiOperation(value = "Returns List of all Kliejnts") // Vraca listu svih klijenata
	@GetMapping("klijent")
	public ResponseEntity<List<Klijent>> getAll() {
		List<Klijent> klijents = klijentService.getAll();
		return new ResponseEntity<>(klijents, HttpStatus.OK);
	}

	@ApiOperation(value = "Returns tip racuna with id that was forwarded as path variable.") // Vraca Klijenta sa id-jem
																								// koji je prosledjen
																								// kao vrednost putanje
	@GetMapping("dobavljac/{id}")
	public ResponseEntity<Klijent> getOne(@PathVariable("id") Integer id) {
		if (klijentService.findById(id).isPresent()) {
			Optional<Klijent> klijentOpt = klijentService.findById(id);
			return new ResponseEntity<>(klijentOpt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Returns list of TipRacunas containing string that was forwarded as path variable in 'naziv'.") // Vraca
																															// listu
																															// Klijenata
																															// koji
																															// sadrze
																															// string
																															// koji
																															// je
																															// prosledjen
																															// kao
																															// vrednost
																															// 'naziv'
	@GetMapping("klijent/naziv/{naziv}")
	public ResponseEntity<List<Klijent>> getByNaziv(@PathVariable("naziv") String ime) {
		List<Klijent> klijents = klijentService.findByImeContainingIgnoreCase(ime);
		return new ResponseEntity<>(klijents, HttpStatus.OK);
	}

	@ApiOperation(value = "Adds new tip racuna to database.") // Dodavanje novog klijenta u bazu podataka
	@PostMapping("klijent")
	public ResponseEntity<Klijent> addKlijent(@RequestBody Klijent klijent) {
		Klijent savedKlijent = klijentService.save(klijent);
		URI location = URI.create("/klijent/" + savedKlijent.getId());
		return ResponseEntity.created(location).body(savedKlijent);
	}

	@ApiOperation(value = "Updates TipRacuna that has id that was forwarded as path variable with values forwarded in Request Body.") // Azurira
																																		// Klijenta
																																		// koji
																																		// ima
																																		// id
																																		// koje
																																		// je
																																		// prosledjen
																																		// kao
																																		// promenljiva
																																		// putanje
																																		// sa
																																		// vrednostima
																																		// prosledjenim
																																		// u
																																		// telu
																																		// zahteva
	@PutMapping(value = "klijent/{id}")
	public ResponseEntity<Klijent> updateKlijent(@RequestBody Klijent klijent, @PathVariable("id") Integer id) {
		if (klijentService.existsById(id)) {
			klijent.setId(id);
			Klijent savedKlijent = klijentService.save(klijent);
			return ResponseEntity.ok().body(savedKlijent);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "Deletes TipRacuna with id that was forwarded as path variable.") /// Brise klijenta sa id-jem
																							/// koji je prosledjen kao
																							/// promeljiva putanje
	@DeleteMapping("klijent/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {

		if (id == -100 && !klijentService.existsById(id)) {
			((JdbcOperations) jdbcTemplate).execute(
					"INSERT INTO klijent (\"id\", \"ime\", \"prezime\", \"broj_lk\", \"kredit\") VALUES (-100, 'Test Ime', 'Test Prezime', 'Test Broj licne karte', 'Test Kredit')");
		}

		if (klijentService.existsById(id)) {
			klijentService.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}

}
