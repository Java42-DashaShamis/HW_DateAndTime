package telran.util.time;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class NextFriday13Adjuster implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		/* V.R. The following code does the same but without unnecessary variables
		do {
			temporal = temporal.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
		} while(temporal.get(ChronoField.DAY_OF_MONTH) != 13);
		return temporal;
		*/
		/* V.R. This algorithm isn't the fastest. The step of the circle is 7 days only.
		 * The month (30/31 days) gives better results
		 */
		TemporalAdjuster NextFridayAdjuster = TemporalAdjusters.next(DayOfWeek.FRIDAY); 
		Temporal friday = temporal;
		do {
			 friday = friday.with(NextFridayAdjuster);
		}while(friday.get(ChronoField.DAY_OF_MONTH) != 13);
		
		return friday;
		
	}

}
