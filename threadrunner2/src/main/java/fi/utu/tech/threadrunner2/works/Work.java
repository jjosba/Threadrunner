package fi.utu.tech.threadrunner2.works;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.SimpleStringProperty;

public interface Work {
	public int work();

	public void setStatusProperty(String inStatus);

	public SimpleStringProperty statusProperty();

	public ObjectBinding<String> statusBinding();
}
