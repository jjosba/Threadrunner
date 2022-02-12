package fi.utu.tech.threadrunner2.mediator;

/*
 * This class implements the Mediator interface. No editing is needed. 
 */

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import fi.utu.tech.threadrunner2.ui.MainViewController;
import fi.utu.tech.threadrunner2.works.Work;

public class MediatorService implements Mediator {

	public LinkedBlockingQueue<Work> workQueue = new LinkedBlockingQueue<Work>();
	private MainViewController controller;

	public MediatorService(MainViewController controller) {
		this.controller = controller;
	}

	public BlockingQueue<Work> getWorkSlice(int amount) {
		BlockingQueue<Work> inputWork = new LinkedBlockingQueue<Work>();
		workQueue.drainTo(inputWork, amount);
		for (Work item : inputWork) {
			setWorkStatus("Queued", item);
		}
		return inputWork;
	}

	@Override
	public void setWorkStatus(String status, Work work) {
		controller.setWorkStatus(status, work);
	}

	@Override
	public void registerThread(int worker, String type) {
		controller.registerThread(worker, type);
	}

	@Override
	public void increaseCalculated(int worker) {
		controller.increaseCalculated(worker);

	}

	@Override
	public void setRunStatus(String status, int worker) {
		controller.setRunStatus(status, worker);

	}

	public void addWork(ArrayList<Work> works) {
		workQueue.addAll(works);
	}

	public void clearWork() {
		workQueue.clear();
	}

}
