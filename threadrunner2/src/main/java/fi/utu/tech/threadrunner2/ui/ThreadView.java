package fi.utu.tech.threadrunner2.ui;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ThreadView {

	private SimpleIntegerProperty idNumberProperty = new SimpleIntegerProperty();
	private SimpleStringProperty threadTypeProperty = new SimpleStringProperty();
	private SimpleStringProperty statusProperty = new SimpleStringProperty();
	private SimpleIntegerProperty calculatedProperty = new SimpleIntegerProperty();

	private ObjectBinding<String> statusBinding;
	private ObjectBinding<Integer> calculatedBinding;

	public ThreadView(int id, String type) {
		setIdNumberProperty(id);
		setThreadTypeProperty(type);
	}

	
	
	public int getId() {
		return getIdNumberProperty();
	}

	public Integer getIdNumberProperty() {
		return idNumberProperty.get();
	}

	public void setIdNumberProperty(Integer inIdNumber) {
		idNumberProperty.set(inIdNumber);
	}

	public String getThreadTypeProperty() {
		return threadTypeProperty.get();
	}

	public void setThreadTypeProperty(String inStatus) {
		threadTypeProperty.set(inStatus);
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

	public SimpleIntegerProperty calculatedProperty() {
		return calculatedProperty;
	}

	public Integer getCalculatedProperty() {
		return calculatedProperty.get();
	}

	public void setCalculatedProperty(Integer inNumber) {
		calculatedProperty.set(inNumber);
	}

	public void increaseCalculated() {
		setCalculatedProperty(calculatedProperty.get() + 1);
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

	public final ObjectBinding<Integer> calculatedBinding() {
		if (calculatedBinding == null) {
			calculatedBinding = new ObjectBinding<Integer>() {
				{
					bind(calculatedProperty());
				}

				@Override
				protected Integer computeValue() {
					if (getCalculatedProperty() == null) {
						return null;
					}
					return getCalculatedProperty();
				}
			};
		}
		return calculatedBinding;
	}
}
