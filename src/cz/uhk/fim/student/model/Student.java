package cz.uhk.fim.student.model;
import java.io.Serializable;


public class Student implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 771652260758459933L;
	private int katalogoveCislo;
	private String jmeno;
	private String prijmeni;
	private int kmenova_trida;
	private int absence;
	
	
	
	public Student(int katalogoveCislo, String jmeno, String prijmeni,
			int trida, int absence) {
		super();
		this.katalogoveCislo = katalogoveCislo;
		this.jmeno = jmeno;
		this.prijmeni = prijmeni;
		this.kmenova_trida = trida;
		this.absence = absence;
	}

	public Student() {
		super();
	}

	public int getTrida() {
		return kmenova_trida;
	}

	public void setTrida(int trida) {
		this.kmenova_trida = trida;
	}

	public int getKatalogoveCislo() {
		return katalogoveCislo;
	}

	public void setKatalogoveCislo(int katalogoveCislo) {
		this.katalogoveCislo = katalogoveCislo;
	}

	public String getJmeno() {
		return jmeno;
	}

	public void setJmeno(String jmeno) {
		this.jmeno = jmeno;
	}

	public String getPrijmeni() {
		return prijmeni;
	}

	public void setPrijmeni(String prijmeni) {
		this.prijmeni = prijmeni;
	}

	public int getAbsence() {
		return absence;
	}

	public void setAbsence(int absence) {
		this.absence = absence;
	}
	
	
	
}
