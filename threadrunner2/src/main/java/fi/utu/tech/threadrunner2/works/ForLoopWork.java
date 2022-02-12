package fi.utu.tech.threadrunner2.works;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ForLoopWork extends AbstractWork {

	public ForLoopWork(String load) {
		if (load.equals("Mixed")) {
			int rnd = new Random().nextInt(3);
			this.load = options[rnd];
		} else {
			this.load = load;
		}
		this.type = "ForLoop";
		this.setTaskTypeProperty(this.type);
		this.setLoadProperty(this.load);
		this.setIdNumberProperty(1);
	}

	public int work() {

		int max = 0;
		switch (load) {
		case "Light":
			max = 100;
			break;
		case "Medium":
			max = 200;
			break;
		case "Heavy":
			max = 400;
			break;
		default:
			max = 10;
		}
		try {
			for (int i = 0; i < max; i++) {
				for (int j = 0; j < max; j++) {
					TimeUnit.NANOSECONDS.sleep(1);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return 1;
	}
}
