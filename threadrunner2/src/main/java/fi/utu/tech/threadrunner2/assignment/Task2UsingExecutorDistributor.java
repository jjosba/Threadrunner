package fi.utu.tech.threadrunner2.assignment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fi.utu.tech.threadrunner2.mediator.ControlSet;
import fi.utu.tech.threadrunner2.mediator.Mediator;

public class Task2UsingExecutorDistributor implements Distributor {
	private Mediator mediator;
	private ControlSet control;

	public Task2UsingExecutorDistributor(Mediator mediator, ControlSet control) {
		this.mediator = mediator;
		this.control = control;

	}

	public void execute() {
		final ExecutorService executor = Executors.newFixedThreadPool(control.getThreadCount());

		for (int i = 0; i < control.getThreadCount(); i++) {
			Runnable runnable = new T2Runnable(mediator, control);
			executor.execute(runnable);
		}
		executor.shutdown();

	}
}
