package telran.application.dates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class PrintCalendar {
	private static final TextStyle WEEK_DAY_LENGTH = TextStyle.SHORT;
	static DayOfWeek[] daysOfWeek;
	private static Locale locale = Locale.forLanguageTag("en");
	
	public static void main(String[] args) {
		/*
		if(args.length < 2) {
			System.out.println("usage: two numbers for summing");
			return;
		}
		int op1 = Integer.parseInt(args[0]);
		int op2 = Integer.parseInt(args[1]);
		System.out.println(op1 + op2);
		*/
		/************************/
		try {
			//TODO Part for arguments processing
			//java -jar <jar file name> <month number> <year> <full name of week day (SUNDAY upper case)>
			//no arguments - current month, current year, MONDAY
			//no year, no week day - current year, MONDAY
			//no week day - MONDAY
			
			
			setDaysOfWeek(args);
			printCalendar(setMonth(args),setYear(args));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	private static int setYear(String[] args) throws Exception {
		int year = LocalDate.now().get(ChronoField.YEAR);
		if(args.length > 1) {
			
			year = Integer.valueOf(args[1]);
			//[YG] should be exception handling otherwise the user may get unclear message
			//[YG] should be validation (year can't be a negative) with appropriate exception throwing
			
		}
		return year;
	}
//[YG] better method name getMonth rather than setMonth
	private static int setMonth(String[] args) throws Exception {
		int month = LocalDate.now().get(ChronoField.MONTH_OF_YEAR);
		if(args.length > 0) {
			month = Integer.valueOf(args[0]);
			//[YG] should be exception handling otherwise the user may get unclear message
			//[YG] should be validation (month can be neither less than 1 nor greater than 12) with appropriate exception throwing
		}
		return month;
	}

	private static void setDaysOfWeek(String[] args) throws Exception {
		if (args.length > 2) {
			//TODO reordering of days 
			//in case of wrong week day exception should be thrown
			String day = args[2];
			DayOfWeek[] ar = DayOfWeek.values();
			//[YG] better to use DayOfWeek.valueOf method with exception handling
			int index = getIndex(day, ar);
			//[YG] no validation of possible -1 result. 
			int indexGiven = index;
			int indexDisplay = 0;
			do {
				daysOfWeek[indexDisplay++] = ar[indexGiven];
				if(indexGiven == 6) {
					indexGiven = 0;
				}else {
					indexGiven++;
				}
			}while(indexGiven != index);
			
		}else {
			daysOfWeek = DayOfWeek.values();
		}
		
	}

	private static int getIndex(String day, DayOfWeek[] ar) {
		for(int i = 0; i < ar.length; i++) {
			if(ar[i].name().equals(day)) {
				return i;
			}
		}
		return -1;
	}

	private static void printCalendar(int month, int year) {
		printTitle(month, year);
		printWeekDays();
		printDates(month, year);
	}

	private static void printDates(int month, int year) {
		int firstCol = getFirstCol(month, year); //number of column
		printOffset(firstCol);
		int days = getDaysNumber(month, year);
		int columnWidth = getColumnWidth();
		int line = 1;
		for(int i = 1; i <= days; i++) {
			System.out.printf("%" + columnWidth + "d", i);
			if((line + firstCol) % daysOfWeek.length == 0) {
				System.out.println();
				firstCol = 0;
			}else {
				firstCol++;
			}
			
		}
		
	}

	private static void printOffset(int firstCol) {
		System.out.print(" ".repeat(firstCol * getColumnWidth()));
	}

	private static int getColumnWidth() {
		return (daysOfWeek[0].getDisplayName(WEEK_DAY_LENGTH, locale) + " ").length();
	}

	private static int getDaysNumber(int month, int year) {
		return YearMonth.of(year, month).lengthOfMonth();
	}

	private static int getFirstCol(int month, int year) {
		LocalDate firstDateMonth = LocalDate.of(year, month, 1);
		int firstWeekDay = firstDateMonth.getDayOfWeek().getValue();
		int firstValue = daysOfWeek[0].getValue(); //our first day of week
		int delta = firstWeekDay - firstValue;
		
		return delta >= 0 ? delta : delta + daysOfWeek.length;
	}

	private static void printWeekDays() {
		String res = " ".repeat(getColumnWidth()/2);
		for(int i = 0; i < daysOfWeek.length; i++) {
			res += daysOfWeek[i].getDisplayName(WEEK_DAY_LENGTH, locale) + " ";
		}
		System.out.println(res);
	}

	private static void printTitle(int month, int year) {
		Month monthObj = Month.of(month);
		System.out.printf("%s, %d\n", monthObj.getDisplayName(TextStyle.FULL_STANDALONE, locale), year);
	}

}


