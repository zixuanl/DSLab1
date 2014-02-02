package clock;

import lab0.ds.TimeStamp;
import lab0.ds.TimeStampedMessage;

public class VectorClock extends ClockService {
	
	static TimeStamp timeStamp;
	private int local_id;

	public VectorClock(int numberOfProcesses, int local_id) {
		super(ClockType.VECTOR);
		timeStamp = new TimeStamp(0, new int[numberOfProcesses]);
		this.local_id = local_id;
	}
	
	@Override
	public void incrementTimeStamp() {
		synchronized (timeStamp) {
			timeStamp.setVec_timeStamp(local_id, timeStamp.getVec_timeStamp(local_id) + 1);
		}
	}

	@Override
	public void incrementTimeStamp(TimeStampedMessage message) {
		synchronized (timeStamp) {
			  timeStamp.setVec_timeStamp(local_id, (Math.max(timeStamp.getVec_timeStamp(local_id), 
					  message.getTimeStamp().getVec_timeStamp(local_id)) + 1));
		}
	}

}