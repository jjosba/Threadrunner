package fi.utu.tech.threadrunner2.works;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.binding.ObjectBinding;

abstract class AbstractWork implements Work {

	protected final static String[] options = { "Light", "Medium", "Heavy" };
	protected String type;
	protected String load;
	protected SimpleStringProperty taskTypeProperty = new SimpleStringProperty();
	protected SimpleStringProperty loadProperty = new SimpleStringProperty();
	protected SimpleStringProperty statusProperty = new SimpleStringProperty();
	protected SimpleIntegerProperty idNumberProperty = new SimpleIntegerProperty();
	protected ObjectBinding<String> statusBinding;

	abstract public int work();

	public String getType() {
		return this.type;
	}

	public String getLoad() {
		return this.load;
	}

	public String getTaskTypeProperty() {
		return taskTypeProperty.get();
	}

	public void setTaskTypeProperty(String inTaskType) {
		taskTypeProperty.set(inTaskType);
	}

	public String getLoadProperty() {
		return loadProperty.get();
	}

	public void setLoadProperty(String inLoad) {
		loadProperty.set(inLoad);
	}

	public SimpleStringProperty statusProperty() {
		return statusProperty;
	}

	public String getStatusProperty() {
		return statusProperty.get();
	}

	public void setStatusProperty(String inStatus) {
		statusProperty.set(inStatus);
	}

	public Integer getIdNumberProperty() {
		return idNumberProperty.get();
	}

	public void setIdNumberProperty(Integer inIdNumber) {
		idNumberProperty.set(inIdNumber);
	}

	public final ObjectBinding<String> statusBinding() {
		if (statusBinding == null) {
			statusBinding = new ObjectBinding<String>() {
				{
					bind(statusProperty());
				}

				@Override
				protected String computeValue() {
					if (getStatusProperty() == null) {
						return null;
					}
					return getStatusProperty();
				}
			};
		}
		return statusBinding;
	}
}
