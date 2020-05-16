package com.example.stock.Utilis;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.example.stock.bean.Grade;
import com.example.stock.bean.GradeEmploye;

public class DateUlils {
	public static Integer getYear(Date d) {
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		return c.get(Calendar.YEAR);
	}

	public static Long getDateDiff(Date date1, Date date2) {
		long milliSeconde1 = date1.getTime();
		long milliSeconde2 = date2.getTime();
		return (milliSeconde1 - milliSeconde2);
	}

	public static Date getDateEvaluation(Date date1, long nombre) {
		long milliSeconde1 = date1.getTime() + nombre;
		return new Date(milliSeconde1);
	}

	public static Long getDateDiffEnjour(Date date1, Date date2) {
		long milliSeconde1 = date1.getTime();
		long milliSeconde2 = date2.getTime();
		return ((milliSeconde1 - milliSeconde2) / (1000 * 60 * 60 * 24));
	}

	public static Date getDateEvaluationDeGrade(GradeEmploye grade) {
		String libelleGrade = grade.getGrade().getLibelle();
		Long nombreAnnee = null;
		switch (libelleGrade) {
		case "grade1":
			nombreAnnee = (long) (1 * (1000 * 60 * 60 * 24 * 365));
			break;
		case "grade2":
			nombreAnnee = (long) 1 * (1000 * 60 * 60 * 24 * 365);
			break;
		case "grade3":
			nombreAnnee = (long) 2 * (1000 * 60 * 60 * 24 * 365);
			break;
		case "grade4":
			nombreAnnee = (long) 2 * (1000 * 60 * 60 * 24 * 365);
			break;
		case "grade5":
			nombreAnnee = (long) 2 * (1000 * 60 * 60 * 24 * 365);
			break;
		case "grade6":
			nombreAnnee = (long) 3 * (1000 * 60 * 60 * 24 * 365);
			break;
		case "grade7":
			nombreAnnee = (long) 3 * (1000 * 60 * 60 * 24 * 365);
			break;
		case "grade8":
			nombreAnnee = (long) 3 * (1000 * 60 * 60 * 24 * 365);
			break;
		case "grade9":
			nombreAnnee = (long) 4 * (1000 * 60 * 60 * 24 * 365);
			break;
		case "grade10":
			nombreAnnee = (long) 2 * (1000 * 60 * 60 * 24 * 365);
			break;
		}
		return getDateEvaluation(grade.getDateDeAffectation(), nombreAnnee);
	}
	public static String getNouvauGrade(Grade grade) {
		String nouveauGrade = null;
		switch (grade.getLibelle()) {
		case "grade1":
			nouveauGrade = "grade2";
			break;
		case "grade2":
			nouveauGrade = "grade3";
			break;
		case "grade3":
			nouveauGrade = "grade4";
			break;
		case "grade4":
			nouveauGrade = "grade5";
			break;
		case "grade5":
			nouveauGrade = "grade6";
			break;
		case "grade6":
			nouveauGrade = "grade7";
			break;
		case "grade7":
			nouveauGrade = "grade8";
			break;
		case "grade8":
			nouveauGrade = "grade9";
			break;
		case "grade9":
			nouveauGrade = "grade10";
			break;
		case "grade10":
			nouveauGrade = "hors echelle";
			break;
		}
		return nouveauGrade;
	}

	public static Date getDateAvancementnDeGrade(GradeEmploye grade, String mention) {
		String libelleGrade = grade.getGrade().getLibelle();
		Long nombreAnnee = null;
		switch (libelleGrade) {
		case "grade1":
			if (mention.equals("rapide")) {
				nombreAnnee = (long) (1 * (1000 * 60 * 60 * 24 * 365));
				break;
			} else if (mention.equals("moyen")) {
				nombreAnnee = (long) (1 * (1000 * 60 * 60 * 24 * 365));
				break;
			} else if (mention.equals("lent")) {
				nombreAnnee = (long) (1 * (1000 * 60 * 60 * 24 * 365));
				break;
			}
		case "grade2":
			if (mention.equals("rapide")) {
				nombreAnnee = (long) (1 * (1000 * 60 * 60 * 24 * 365));
				break;
			} else if (mention.equals("moyen")) {
				nombreAnnee = (long) ((1.5) * (1000 * 60 * 60 * 24 * 365));
				break;
			} else if (mention.equals("lent")) {
				nombreAnnee = (long) (2 * (1000 * 60 * 60 * 24 * 365));
				break;
			}
		case "grade3":
			if (mention.equals("rapide")) {
				nombreAnnee = (long) (2 * (1000 * 60 * 60 * 24 * 365));
				break;
			} else if (mention.equals("moyen")) {
				nombreAnnee = (long) ((2.5) * (1000 * 60 * 60 * 24 * 365));
				break;
			} else if (mention.equals("lent")) {
				nombreAnnee = (long) (3 * (1000 * 60 * 60 * 24 * 365));
				break;
			}
		case "grade4":
			if (mention.equals("rapide")) {
				nombreAnnee = (long) (2 * (1000 * 60 * 60 * 24 * 365));
				break;
			} else if (mention.equals("moyen")) {
				nombreAnnee = (long) ((2.5) * (1000 * 60 * 60 * 24 * 365));
				break;
			} else if (mention.equals("lent")) {
				nombreAnnee = (long) ((3.5) * (1000 * 60 * 60 * 24 * 365));
				break;
			}
		case "grade5":
			if (mention.equals("rapide")) {
				nombreAnnee = (long) (2 * (1000 * 60 * 60 * 24 * 365));
				break;
			} else if (mention.equals("moyen")) {
				nombreAnnee = (long) ((2.5) * (1000 * 60 * 60 * 24 * 365));
				break;
			} else if (mention.equals("lent")) {
				nombreAnnee = (long) ((3.5) * (1000 * 60 * 60 * 24 * 365));
				break;
			}
		case "grade6":
			if (mention.equals("rapide")) {
				nombreAnnee = (long) (3 * (1000 * 60 * 60 * 24 * 365));
				break;
			} else if (mention.equals("moyen")) {
				nombreAnnee = (long) ((3.5) * (1000 * 60 * 60 * 24 * 365));
				break;
			} else if (mention.equals("lent")) {
				nombreAnnee = (long) ((4) * (1000 * 60 * 60 * 24 * 365));
				break;
			}
		case "grade7":
			if (mention.equals("rapide")) {
				nombreAnnee = (long) (3 * (1000 * 60 * 60 * 24 * 365));
				break;
			} else if (mention.equals("moyen")) {
				nombreAnnee = (long) ((3.5) * (1000 * 60 * 60 * 24 * 365));
				break;
			} else if (mention.equals("lent")) {
				nombreAnnee = (long) ((4) * (1000 * 60 * 60 * 24 * 365));
				break;
			}
		case "grade8":
			if (mention.equals("rapide")) {
				nombreAnnee = (long) (3 * (1000 * 60 * 60 * 24 * 365));
				break;
			} else if (mention.equals("moyen")) {
				nombreAnnee = (long) ((4) * (1000 * 60 * 60 * 24 * 365));
				break;
			} else if (mention.equals("lent")) {
				nombreAnnee = (long) ((4.5) * (1000 * 60 * 60 * 24 * 365));
				break;
			}
		case "grade9":
			if (mention.equals("rapide")) {
				nombreAnnee = (long) (4 * (1000 * 60 * 60 * 24 * 365));
				break;
			} else if (mention.equals("moyen")) {
				nombreAnnee = (long) ((5) * (1000 * 60 * 60 * 24 * 365));
				break;
			} else if (mention.equals("lent")) {
				nombreAnnee = (long) ((5.5) * (1000 * 60 * 60 * 24 * 365));
				break;
			}
		case "grade10":
			nombreAnnee = (long) 2 * (1000 * 60 * 60 * 24 * 365);
			break;
		}
		return getDateEvaluation(grade.getDateDeAffectation(), nombreAnnee);
	}
	public static Date getDateDeNote(Date date1) {
		long milliSeconde1 = date1.getTime() + 365*1000*60*60*24 ;
		return new Date(milliSeconde1);
	}

	public static boolean VerifieDate(Date date1) {
		Date date2 = new Date();
		long milliSeconde1 = date1.getTime();
		long milliSeconde2 = date2.getTime();
		return milliSeconde1 < milliSeconde2 ? true : false;
	}
	public static boolean verifierDateSup(Date date1,Date date2) {
		long milliSeconde1 = date1.getTime();
		long milliSeconde2 = date2.getTime();
		return milliSeconde1 < milliSeconde2 ? true : false;
	}
	public static String GetMention(Double moyen) {
		String resultat = null;
if(moyen<10) {
	resultat = "lent";
}else if(10<=moyen && moyen<16) {
	resultat = "moyen";
}else if(moyen>=16){
	resultat = "rapide";
}
return resultat;
	}
public static boolean debloquer(Date date) {
	Date date1 = new Date();
	long milliSeconde1 = date.getTime();
	long milliSeconde2 = date1.getTime();
	return milliSeconde2 - milliSeconde1 > 15* 60*1000 ? true: false;
	}
}
