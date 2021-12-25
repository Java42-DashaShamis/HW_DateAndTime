package telran.util.time;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class NextFriday13Adjuster implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		Temporal current = temporal;
		int currentDay = temporal.get(ChronoField.DAY_OF_MONTH);
		current = current.with(ChronoField.DAY_OF_MONTH, 13);
		if(currentDay >= 13) {
			current = current.plus(1, ChronoUnit.MONTHS);
		}
		while(DayOfWeek.from(current).name() != "FRIDAY") {
			current = current.plus(1, ChronoUnit.MONTHS);
		}
		return current;
		
	}

}
