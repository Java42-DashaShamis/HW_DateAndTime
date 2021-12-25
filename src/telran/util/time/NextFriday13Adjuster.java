package telran.util.time;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class NextFriday13Adjuster implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		
		TemporalAdjuster NextFridayAdjuster = TemporalAdjusters.next(DayOfWeek.FRIDAY); 
		Temporal friday = temporal;
		do {
			 friday = friday.with(NextFridayAdjuster);
		}while(friday.get(ChronoField.DAY_OF_MONTH) != 13);
		
		return friday;
		
	}

}
