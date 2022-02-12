package fi.utu.tech.threadrunner2.works;

import java.util.ArrayList;

public class WorkFactory {

	private static final int defaultAmount = 10;
	private static final String defaultType = "dummy";
	private static final String defaultLoad = "Light";

	public static ArrayList<Work> generate() {
		ArrayList<Work> list = new ArrayList<Work>();
		for (int i = 0; i < defaultAmount; i++) {
			list.add(create(defaultType, defaultLoad));
		}
		return list;
	}

	public static ArrayList<Work> generate(String type, int amount, String load) {
		ArrayList<Work> list = new ArrayList<Work>();
		for (int i = 0; i < amount; i++) {
			list.add(create(type, load));
		}
		return list;

	}

	private static Work create(String type, String load) {
		switch (type) {
		case "Dummy":
			return new DummyWork(load);
		case "ForLoop":
			return new ForLoopWork(load);
		default:
			return new DummyWork(load);
		}
	}

	public static String[] getTaskTypes() {
		return new String[] { "DummyWork", "ForLoop" };
	}
}
