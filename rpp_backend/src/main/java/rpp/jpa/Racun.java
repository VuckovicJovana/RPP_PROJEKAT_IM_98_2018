package rpp.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 * The persistent class for the racun database table.
 * 
 */
@Entity
@NamedQuery(name="Racun.findAll", query="SELECT r FROM Racun r")
public class Racun implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RACUN_ID_GENERATOR")
	@SequenceGenerator(name="RACUN_ID_GENERATOR", sequenceName="RACUN_SEQ", allocationSize = 1)
	
	private Integer id;

	private String naziv;

	private String opis;

	private String oznaka;

	//bi-directional many-to-one association to Klijent
	@ManyToOne
	@JoinColumn(name="klijent")
	private Klijent klijentBean;

	//bi-directional many-to-one association to TipRacuna
	@ManyToOne
	@JoinColumn(name="tip_racuna")
	private TipRacuna tipRacunaBean;

	public Racun() {
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

	public Klijent getKlijentBean() {
		return this.klijentBean;
	}

	public void setKlijentBean(Klijent klijentBean) {
		this.klijentBean = klijentBean;
	}

	public TipRacuna getTipRacunaBean() {
		return this.tipRacunaBean;
	}

	public void setTipRacunaBean(TipRacuna tipRacunaBean) {
		this.tipRacunaBean = tipRacunaBean;
	}

}