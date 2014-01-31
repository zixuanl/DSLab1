package lab0.ds;

import java.util.ArrayList;

public class LogicalClock extends ClockService {

	public static TimeStamp timeStamp = new TimeStamp(0, 0,0);
	
	public LogicalClock() {
		super(ClockType.LOGICAL);
	}

	@Override
	protected void incrementTimeStamp(TimeStampedMessage message) {
		synchronized (this) {
			  timeStamp.setLog_timeStamp(Math.max(timeStamp.getLog_timeStamp(), (Integer)message.getLogTimeStamp()) + 1);
		}		
	}
}
