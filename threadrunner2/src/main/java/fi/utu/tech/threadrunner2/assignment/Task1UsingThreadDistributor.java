package fi.utu.tech.threadrunner2.assignment;

import fi.utu.tech.threadrunner2.mediator.ControlSet;
import fi.utu.tech.threadrunner2.mediator.Mediator;

public class Task1UsingThreadDistributor implements Distributor {
	private Mediator mediator;
	private ControlSet control;

	public Task1UsingThreadDistributor(Mediator mediator, ControlSet control) {
		this.mediator = mediator;
		this.control = control;

	}

	public void execute() {
		for (int i = 0; i < control.getThreadCount(); i++) {
			T1Thread t = new T1Thread(mediator, control);
			t.start();
			mediator.setRunStatus("Created", t.hashCode());
		}
	}

}
