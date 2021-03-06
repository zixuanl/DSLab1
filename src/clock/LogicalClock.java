package clock;

import lab0.ds.TimeStamp;
import lab0.ds.TimeStampedMessage;

public class LogicalClock extends ClockService {

	static TimeStamp timeStamp;
	
	public LogicalClock() {
		super(ClockType.LOGICAL);
		timeStamp = new TimeStamp(0, new int[0]);
	}
	
	@Override
	public void incrementTimeStamp() {
		synchronized (timeStamp) {
			timeStamp.setLog_timeStamp(timeStamp.getLog_timeStamp() + 1);
		}
	}

	@Override
	public void incrementTimeStamp(TimeStampedMessage message) {
		synchronized (timeStamp) {
			if (message.getTimeStamp() == null) {
				timeStamp.setLog_timeStamp(timeStamp.getLog_timeStamp() + 1);
			} else {
			  timeStamp.setLog_timeStamp(Math.max(timeStamp.getLog_timeStamp(), message.getTimeStamp().getLog_timeStamp()) + 1);
			}
		}		
	}
}
