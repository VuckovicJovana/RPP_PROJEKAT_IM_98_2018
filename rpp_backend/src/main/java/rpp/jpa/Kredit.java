package rpp.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the kredit database table.
 * 
 */

@Entity
@NamedQuery(name = "Kredit.findAll", query = "SELECT a FROM Kredit a")
public class Kredit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="KREDIT_ID_GENERATOR")
	@SequenceGenerator(name="KREDIT_ID_GENERATOR", sequenceName="KREDIT_SEQ", allocationSize = 1)

	private Integer id;

	private String naziv;

	private String opis;

	private String oznaka;

	//bi-directional many-to-one association to 
	@JsonIgnore
	@OneToMany(mappedBy="kreditBean")
	private List<Klijent> klijents;

	public Kredit() {
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

	public List<Klijent> getKlijents() {
		return this.klijents;
	}

	public void setKlijents(List<Klijent> klijents) {
		this.klijents = klijents;
	}

	public Klijent addKlijent(Klijent klijent) {
		getKlijents().add(klijent);
		klijent.setKreditBean(this);

		return klijent;
	}

	public Klijent removeKlijent(Klijent klijent) {
		getKlijents().remove(klijent);
		klijent.setKreditBean(null);

		return klijent;
	}

}