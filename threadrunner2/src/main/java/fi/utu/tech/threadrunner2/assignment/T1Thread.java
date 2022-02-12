package fi.utu.tech.threadrunner2.assignment;

import java.util.concurrent.BlockingQueue;

import fi.utu.tech.threadrunner2.mediator.ControlSet;
import fi.utu.tech.threadrunner2.mediator.Mediator;
import fi.utu.tech.threadrunner2.works.Work;

public class T1Thread extends Thread {

	private Mediator mediator;
	private BlockingQueue<Work> workList;
	private ControlSet control;

	public T1Thread(Mediator mediator, ControlSet control) {
		this.mediator = mediator;
		this.control = control;
		mediator.registerThread(this.hashCode(), "Thread");
	}

	public void run() {
		mediator.setRunStatus("Running", this.hashCode());
		while (!((workList = mediator.getWorkSlice(control.getBlockSize())).isEmpty())) {
			for (Work item : workList) {
				mediator.setWorkStatus("Calculating", item);
				item.work();
				mediator.setWorkStatus("Done", item);
				mediator.increaseCalculated(this.hashCode());
			}

		}
		mediator.setRunStatus("Ended", this.hashCode());
	}
}
