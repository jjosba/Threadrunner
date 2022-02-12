package fi.utu.tech.threadrunner2.works;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DummyWork extends AbstractWork {

	public DummyWork(String load) {
		if (load.equals("Mixed")) {
			int rnd = new Random().nextInt(3);
			this.load = options[rnd];
		} else {
			this.load = load;
		}
		this.type = "Dummy";
		this.setTaskTypeProperty(this.type);
		this.setLoadProperty(this.load);
		this.setStatusProperty("Waiting");
		this.setIdNumberProperty(1);
	}

	public int work() {
		try {
			switch (load) {
			case "Light":
				TimeUnit.MILLISECONDS.sleep(500);
				break;
			case "Medium":
				TimeUnit.MILLISECONDS.sleep(2000);
				break;
			case "Heavy":
				TimeUnit.MILLISECONDS.sleep(8000);
				break;
			default:
				TimeUnit.MILLISECONDS.sleep(100);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 1;
	}

}
