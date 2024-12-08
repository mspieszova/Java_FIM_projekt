package cz.uhk.fim.student.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import fim.utils.Application;
import cz.uhk.fim.student.model.EvidenceStudentu;
import cz.uhk.fim.student.model.Student;


public class StudentApp extends Application {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6293708274574853935L;
	private EvidenceStudentu evidence;
	public StudentApp() {
		vytvorTlacitkaAMenu();
		evidence = new EvidenceStudentu();
	}
	
	private void vytvorTlacitkaAMenu() {
		menu.add("Pridat", 1000);
		buttons.add("Pridat", 1000);
		menu.add("Vypsat vse",1001);
		buttons.add("Vypsat vse",1001);

		//Nacitani + ukladani
		menu.add("Ulozit",1002);
		buttons.add("Ulozit",1002);
		menu.add("Nacist",1003);
		buttons.add("Nacist",1003);
		
		menu.add("Smazat seznam",1004);
		
		menu.add("Radit dle prijmeni",1005);
		menu.add("Radit dle absence",1006);
		menu.add("Radit dle katalogoveho cisla",1007);
		
		menu.add("Zobrazit kmenovou tridu (hledat)",1008);
		menu.add("Zobrazit studenty s absenci  vìtší než",1009);
		menu.setVisible(true);
	}

	@Override
	public void handleMenu(int zkratka) {
		switch (zkratka) {
		case 1000:
			pridat();
			break;
		case 1001:
			vypsatVse();
			break;
		case 1002://ulozit
			ulozit();
			break;
		case 1003:
			nacist();
			break;
		case 1004:
			evidence.vycisti();
			break;
		case 1005:
			evidence.seradit(EvidenceStudentu.RAZENI_PRIJMENI);
			break;
		case 1006:
			evidence.seradit(EvidenceStudentu.RAZENI_ABSENCE);
			break;
		case 1007:
			evidence.seradit(EvidenceStudentu.RAZENI_KATALOGOVECISLO);
			break;
		case 1008:
			najitStudentiVeTride();
			break;
		case 1009:
			najitAbsence();
			break;
		}
	}
	
	private void najitStudentiVeTride() {
		int trida = in.readInt("zadej èíslo kmenové tridy");
		List<Student> seznam = evidence.studentiVeTride(trida);
		out.println("Studenti patøící do kmenové tøídy "+trida);
		for (Student s : seznam) {
			vypisStudenta(s);
		}
	}
	private void najitAbsence() {
		int absence = in.readInt("zadej absenci");
		List<Student> seznam = evidence.absence(absence);
		out.println("Studenti s absenci  vìtší než "+ absence );
		for (Student s : seznam) {
			vypisStudenta(s);
		}
	}

	private void nacist() {
		try {
			ObjectInputStream is = new ObjectInputStream(
				new FileInputStream("studenti.dat")	
			);
			evidence = (EvidenceStudentu)is.readObject();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			out.println("Nacteni se nepodarilo !!! - "+ e.getMessage());
		} 

	}

	private void ulozit() {
		try {
			ObjectOutputStream os = new ObjectOutputStream(
				new FileOutputStream("studenti.dat")	
			);
			os.writeObject(evidence);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
			out.println("Ulozeni se nepodarilo !!!" );
		}
	}

	private void vypsatVse() {
		out.clearScreen();
		out.println("Vypis vsech polozek\n-------------------");
		for (int i = 0; i<evidence.pocet(); i++) {
			Student n = evidence.get(i);
			vypisStudenta(n);
		}
	}

	private void vypisStudenta(Student n) {
		out.println(
				String.format("Katalogové èíslo: %d, Jméno:%s, Pøíjmení: %s, Kmenová tøída: %s, Absence: %d",  
					n.getKatalogoveCislo(), n.getJmeno(),n.getPrijmeni(),n.getTrida(),n.getAbsence()	
				)
		);
	}

	private void pridat() {
		int katalogoveCislo = in.readInt("Zadej katalogove cislo");
		String jmeno = in.readString("Zadej jmeno");
		String prijmeni = in.readString("Zadej prijmeni");
		int trida = in.readInt("Zadej èíslo kmenové tridy");
		int absence = in.readInt("Zadej absenci");
		Student n = new Student(katalogoveCislo, jmeno, prijmeni, trida, absence);
		evidence.pridej(n);
	}


	@Override
	public void start() {
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new StudentApp().start();
	}

}