package cz.uhk.fim.student.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

	public class EvidenceStudentu implements Serializable {
		private static final long serialVersionUID = 312558806361381862L;
		public static final int RAZENI_PRIJMENI = 0;
		public static final int RAZENI_ABSENCE = 1;
		public static final int RAZENI_KATALOGOVECISLO = 2;
		
		private List<Student> seznam = new ArrayList<>();

		
		public void vycisti() {
			seznam.clear();
		}

		public boolean obsahuje(Object arg0) {
			return seznam.contains(arg0);
		}

		public Student get(int index) {
			return seznam.get(index);
		}

		public int indexOf(Student index) {
			return seznam.indexOf(index);
		}

		public Student odstran(int index) {
			return seznam.remove(index);
		}

		public boolean remove(Student r) {
			return seznam.remove(r);
		}

		
		public int pocet() {
			return seznam.size();
		}

		public void pridej(Student s) {
			seznam.add(s);
		}
		
		public List<Student> studentiVeTride(int trida) {
			List <Student> zaci = new ArrayList <>();
			for (Student student:seznam){
				if(student.getTrida()==trida){
					zaci.add(student);			
				}	
			}
			return zaci;
		}
	
	
	
	public List<Student> absence(int absence) {
		List <Student> neklasifikace = new ArrayList <>();
		for (Student student:seznam){
			if(student.getAbsence()>absence){
				neklasifikace.add(student);			
			}	
		}
		return neklasifikace;
	}

	public void seradit(final int dleCeho) {
		Comparator<Student> porovnavac = new Comparator<Student>() {
			
			@Override
			public int compare(Student s1, Student s2) {
				switch (dleCeho) {
				case RAZENI_PRIJMENI:
					return s1.getPrijmeni().compareToIgnoreCase(s2.getPrijmeni());				
				case RAZENI_ABSENCE:
					return s1.getAbsence()-s2.getAbsence();
				case RAZENI_KATALOGOVECISLO:
					return s1.getKatalogoveCislo()-s2.getKatalogoveCislo();
				}
				return 0;
			}					
		};
		Collections.sort(seznam,porovnavac);
		
	}



	
	
}

	
	
	
