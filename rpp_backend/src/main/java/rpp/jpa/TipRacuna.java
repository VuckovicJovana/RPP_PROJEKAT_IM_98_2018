package rpp.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the tip_racuna database table.
 * 
 */
@Entity
@Table(name="tip_racuna")
@NamedQuery(name="TipRacuna.findAll", query="SELECT t FROM TipRacuna t")
public class TipRacuna implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIP_RACUNA_ID_GENERATOR")
	@SequenceGenerator(name="TIP_RACUNA_ID_GENERATOR", sequenceName="TIP_RACUNA_SEQ", allocationSize = 1)
	
	private Integer id;

	private String naziv;

	private String opis;

	private String oznaka;

	//bi-directional many-to-one association to Racun
	@JsonIgnore
	@OneToMany(mappedBy="tipRacunaBean")
	private List<Racun> racuns;

	public TipRacuna() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getOznaka() {
		return this.oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public List<Racun> getRacuns() {
		return this.racuns;
	}

	public void setRacuns(List<Racun> racuns) {
		this.racuns = racuns;
	}

	public Racun addRacun(Racun racun) {
		getRacuns().add(racun);
		racun.setTipRacunaBean(this);

		return racun;
	}

	public Racun removeRacun(Racun racun) {
		getRacuns().remove(racun);
		racun.setTipRacunaBean(null);

		return racun;
	}

}