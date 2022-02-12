package fi.utu.tech.threadrunner2.mediator;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import fi.utu.tech.threadrunner2.works.Work;

public interface Mediator {
	
	//This method returns a queue that includes a number of work items. The parameter amount is the block size
	public BlockingQueue<Work> getWorkSlice(int amount);

	
	/* Adds a new thread to the Threads working table. 
	 * Parameter worker is the hash value of a thread object.
	 * Parameter status is one of the values describes in the assignment.
	 */
	public void registerThread(int worker, String type);
	
	/* Sets the status of a tread. 
	 * Parameter worker is the hash value of a thread object.
	 * Parameter status is one of the values describes in the assignment.
	 */
	public void setRunStatus(String status, int worker);
	
	/* Increases the  items calculated value of a tread by one. 
	 * Parameter worker is the hash value of a thread object.
	 */
	public void increaseCalculated(int worker);

	/* Sets the status of a job in the Job queue table. 
	 * Parameter work is the hash value of a work object.
	 * Parameter status is one of the values describes in the assignment.
	 */
	public void setWorkStatus(String status, Work work);
	
	
	//Methods below this line are used only internally. These are not used in the exercises.
	public void addWork(ArrayList<Work> works);
	public void clearWork();
	static String[] getThreadTypes() {
		return new String[] { "Single", "Thread","Executor"};
	}
	
}
