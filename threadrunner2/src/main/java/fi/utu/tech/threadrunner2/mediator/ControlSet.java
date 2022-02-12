package fi.utu.tech.threadrunner2.mediator;

public class ControlSet {

	private final int threadCount;
	private final int blockSize;

	public ControlSet(Integer threadCount, Integer blockSize) {
		this.threadCount = threadCount.intValue();
		this.blockSize = blockSize.intValue();
	}

	
	//Use this method to get the ThreadCount
	public int getThreadCount() {
		return this.threadCount;
	}

	//Use this method to get the BlockSize
	public int getBlockSize() {
		return this.blockSize;
	}

}
