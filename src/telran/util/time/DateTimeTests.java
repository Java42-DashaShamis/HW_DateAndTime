package telran.util.time;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DateTimeTests {
	LocalDate birthAS = LocalDate.of(1799, 6, 6);
	LocalDate barMizvaAS = birthAS.plusYears(13);
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testDateOperations() {
		
		assertEquals(LocalDate.of(1812, 6, 6), barMizvaAS);
		assertEquals("1812-06-06", barMizvaAS.toString());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy");
		assertEquals("6/6/1812", barMizvaAS.format(dtf));
		System.out.println(barMizvaAS.format(DateTimeFormatter.ofPattern("d MMM E yyyy ")));
		assertEquals(LocalDate.parse("1812-06-06"), barMizvaAS);
		assertEquals(13 * 12, ChronoUnit.MONTHS.between(birthAS, barMizvaAS));
		
	}
	
	@Test
	void testBarMizvaAdjuster() {
		assertEquals(barMizvaAS, birthAS.with(new BarMizvaAdjuster()));
	}
	
	@Test
	void testNextFriday13Adjuster() {
		assertEquals(LocalDate.parse("2022-05-13"), LocalDate.parse("2021-12-22").with(new NextFriday13Adjuster()));
	}
	
	@Test
	void testGetAge() {
		LocalDate ld1 = LocalDate.parse("2021-12-22");
		LocalDate ld2 = LocalDate.parse("2021-01-31");
		LocalDate ld3 = LocalDate.parse("2021-06-05");
		assertEquals(2021 - 1799, getAgeAtDate(birthAS, ld1));
		assertEquals(2020 - 1799, getAgeAtDate(birthAS, ld2));
		assertEquals(2020 - 1799, getAgeAtDate(birthAS, ld3));
	}

	private int getAgeAtDate(LocalDate birthDate, LocalDate atDate) { 
		return (int) ChronoUnit.YEARS.between(birthDate, atDate);
	}
	

}
