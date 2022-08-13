package rpp.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;


import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the klijent database table.
 * 
 */
@Entity
@NamedQuery(name="Klijent.findAll", query="SELECT k FROM Klijent k")
public class Klijent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="KLIJENT_ID_GENERATOR")
	@SequenceGenerator(name="KLIJENT_ID_GENERATOR", sequenceName="KLIJENT_SEQ", allocationSize = 1)

	private Integer id;

	private Integer brojLk;

	private String ime;

	private String prezime;

	//bi-directional many-to-one association to Kredit
	@ManyToOne
	@JoinColumn(name="kredit")
	private Kredit kreditBean;

	//bi-directional many-to-one association to Racun
	@JsonIgnore
	@OneToMany(mappedBy="klijentBean")
	private List<Racun> racuns;

	public Klijent() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBrojLk() {
		return this.brojLk;
	}

	public void setBrojLk(Integer brojLk) {
		this.brojLk = brojLk;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Kredit getKreditBean() {
		return this.kreditBean;
	}

	public void setKreditBean(Kredit kreditBean) {
		this.kreditBean = kreditBean;
	}

	public List<Racun> getRacuns() {
		return this.racuns;
	}

	public void setRacuns(List<Racun> racuns) {
		this.racuns = racuns;
	}

	public Racun addRacun(Racun racun) {
		getRacuns().add(racun);
		racun.setKlijentBean(this);

		return racun;
	}

	public Racun removeRacun(Racun racun) {
		getRacuns().remove(racun);
		racun.setKlijentBean(null);

		return racun;
	}

}