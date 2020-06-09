package com.example.stock.Utilis;

import java.math.BigDecimal;
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
	public static Integer getMonth(Date d) {
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		return c.get(Calendar.MONTH);
	}
	public static Integer getDay(Date d) {
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	public static boolean VerifieDateSup(Date date1) {
		Date date2;
		Date date = new Date();
		if((getMonth(date)+1) >= 9)
		{
			date2 = getDateByYearDebut(getYear(date));
		} else {
			 date2 = getDateByYearDebut(getYear(date)-1);
		}
		long milliSeconde1 = date1.getTime();
		long milliSeconde2 = date2.getTime();
		return milliSeconde2 < milliSeconde1 ? true: false;
	}
	public static boolean verifierdateDebutEtFin(Date dateDebut,Integer year) {
		long milliSeconde1 = dateDebut.getTime();
		System.out.println(year-1);
		long milliSeconde2 = getDateByYearDebut(year-1).getTime();
		if (milliSeconde1 < milliSeconde2 && getYear(dateDebut) <= year) {
			return true;
		} else {
			return false;
		}
	}
	public static Date getDateByYearDebut(Integer year) {
		 GregorianCalendar calendar = new GregorianCalendar(year,9,1);
		return calendar.getTime();
	 }
	public static Date getDateDebutOfMonth(Integer year,int month) {
		 GregorianCalendar calendar = new GregorianCalendar(year,month,1);
		 System.out.println("ha date de debut" + calendar.getTime());
		return calendar.getTime();
	 }
	public static Date getDateFin(Date date, Integer periode) {
		long milliSeconde = date.getTime() + periode * 86400000;
		return new Date(milliSeconde);
	 }
	public static Long getDateDiff(Date date1, Date date2) {
		long milliSeconde1 = date1.getTime();
		long milliSeconde2 = date2.getTime();
		return (milliSeconde1 - milliSeconde2);
	}

	public static Date getDateEvaluation(Date date1, BigDecimal nombre) {
		long milliSeconde1 = date1.getTime() + nombre.longValue();
		return new Date(milliSeconde1);
	}
	public static Date getDateEvaluation(Date date1, Long nombre) {
		long milliSeconde1 = date1.getTime() + nombre;
		return new Date(milliSeconde1);
	}
	public static Long getDateDiffEnjour(Date date1, Date date2) {
		long milliSeconde1 = date1.getTime();
		long milliSeconde2 = date2.getTime();
		return ((milliSeconde1 - milliSeconde2) / (1000 * 60 * 60 * 24));
	}
	 public  static Integer getYear(){
		 	Date d=new Date();
			 Calendar c=new GregorianCalendar();
			 c.setTime(d);
			 return c.get(Calendar.YEAR);
		 }
	public static Date getDateEvaluationDeGrade(GradeEmploye grade) {
		String libelleGrade = grade.getGrade().getLibelle();
		Long nombreAnnee = null;
		BigDecimal big = new BigDecimal("31557600000");
		switch (libelleGrade) {
			case "grade1":
				nombreAnnee = 1 * big.longValue() ;
				break;
			case "grade2":
				nombreAnnee =  1 * big.longValue();
				break;
			case "grade3":
				nombreAnnee =  2 * big.longValue();
				break;
			case "grade4":
				nombreAnnee =   2 * big.longValue();
				break;
			case "grade5":
				nombreAnnee =   2 * big.longValue();
				break;
			case "grade6":
				nombreAnnee =   3 * big.longValue();
				break;
			case "grade7":
				nombreAnnee =   3 * big.longValue();
				break;
			case "grade8":
				nombreAnnee =   3 * big.longValue();
				break;
			case "grade9":
				nombreAnnee =   4 * big.longValue();
				break;
			case "grade10":
				nombreAnnee =   2 * big.longValue();
				break;
			}
			return DateUlils.getDateEvaluation(new Date(), nombreAnnee);
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
		BigDecimal big = new BigDecimal("31557600000");
		switch (libelleGrade) {
		case "grade1":
			if (mention.equals("rapide")) {
				nombreAnnee =  1* big.longValue();
				break;
			} else if (mention.equals("moyen")) {
				nombreAnnee =  1* big.longValue();
				break;
			} else if (mention.equals("lent")) {
				nombreAnnee =  1* big.longValue();
				break;
			}
		case "grade2":
			if (mention.equals("rapide")) {
				nombreAnnee =  1* big.longValue();
				break;
			} else if (mention.equals("moyen")) {
				nombreAnnee =  (long) (1.5* big.longValue());
				break;
			} else if (mention.equals("lent")) {
				nombreAnnee =  2* big.longValue();
				break;
			}
		case "grade3":
			if (mention.equals("rapide")) {
				nombreAnnee =  2 * big.longValue();
				break;
			} else if (mention.equals("moyen")) {
				nombreAnnee =  (long) (2.5 * big.longValue());
				break;
			} else if (mention.equals("lent")) {
				nombreAnnee =  3* big.longValue();
				break;
			}
		case "grade4":
			if (mention.equals("rapide")) {
				nombreAnnee =  2* big.longValue();
				break;
			} else if (mention.equals("moyen")) {
				nombreAnnee =  (long) (2.5 * big.longValue());
				break;
			} else if (mention.equals("lent")) {
				nombreAnnee =  (long) (3.5 * big.longValue());
				break;
			}
		case "grade5":
			if (mention.equals("rapide")) {
				nombreAnnee =  2* big.longValue();
				break;
			} else if (mention.equals("moyen")) {
				nombreAnnee =  (long) (2.5 * big.longValue());
				break;
			} else if (mention.equals("lent")) {
				nombreAnnee =  (long) (3.5* big.longValue());
				break;
			}
		case "grade6":
			if (mention.equals("rapide")) {
				nombreAnnee =  3* big.longValue();		
				break;
			} else if (mention.equals("moyen")) {
				nombreAnnee =  (long) (3.5* big.longValue());
				break;
			} else if (mention.equals("lent")) {
				nombreAnnee =  4* big.longValue();
				break;
			}
		case "grade7":
			if (mention.equals("rapide")) {
				nombreAnnee =  3* big.longValue();
				break;
			} else if (mention.equals("moyen")) {
				nombreAnnee =  (long) (3.5* big.longValue());
				break;
			} else if (mention.equals("lent")) {
				nombreAnnee =  4* big.longValue();
				break;
			}
		case "grade8":
			if (mention.equals("rapide")) {
				nombreAnnee =  3* big.longValue();
				break;
			} else if (mention.equals("moyen")) {
				nombreAnnee =  4* big.longValue();
				break;
			} else if (mention.equals("lent")) {
				nombreAnnee =  (long) (4.5 * big.longValue());
				break;
			}
		case "grade9":
			if (mention.equals("rapide")) {
				nombreAnnee =  4* big.longValue();
				break;
			} else if (mention.equals("moyen")) {
				nombreAnnee =  5 * big.longValue();
				break;
			} else if (mention.equals("lent")) {
				nombreAnnee =  (long) (5.5* big.longValue());
				break;
			}
		case "grade10":
			nombreAnnee =  2* big.longValue();
			break;
		}
		return getDateEvaluation(grade.getDateDeAffectation(), nombreAnnee);
	}
	public static Date getDateDeNote(Date date1) {
		BigDecimal big = new BigDecimal("31557600000"); 
		long milliSeconde1 = date1.getTime() +big.longValue();
		return new Date(milliSeconde1);
	}

	public static boolean VerifieDate(Date date1) {
		Date date2 = new Date();
		long milliSeconde1 = date1.getTime();
		long milliSeconde2 = date2.getTime();
		return milliSeconde1 < milliSeconde2 ? true : false;
	}
	public static boolean verifierDateSup(Date date1,Date date2) {
		System.out.println(" ha date 1" + date1);
		System.out.println(" ha date 2" + date2);
		long milliSeconde1 = date1.getTime();
		long milliSeconde2 = date2.getTime();
		return milliSeconde1 <= milliSeconde2 ? true : false;
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
