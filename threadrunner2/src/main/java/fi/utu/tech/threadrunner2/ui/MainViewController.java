package fi.utu.tech.threadrunner2.ui;

import java.util.ArrayList;

import fi.utu.tech.threadrunner2.assignment.Distributor;
import fi.utu.tech.threadrunner2.assignment.ExampleSingleThreadDistributor;
import fi.utu.tech.threadrunner2.assignment.Task2UsingExecutorDistributor;
import fi.utu.tech.threadrunner2.assignment.Task1UsingThreadDistributor;
import fi.utu.tech.threadrunner2.mediator.ControlSet;
import fi.utu.tech.threadrunner2.mediator.Mediator;
import fi.utu.tech.threadrunner2.works.Work;
import fi.utu.tech.threadrunner2.works.WorkFactory;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class MainViewController {

	@FXML
	private Button generate;
	@FXML
	private Button execute;
	@FXML
	private Button clearList;

	@FXML
	private TableView<ThreadView> threadList;
	@FXML
	private TableColumn<ThreadView, Integer> threadId;
	@FXML
	private TableColumn<ThreadView, String> threadTypeColumn;
	@FXML
	private TableColumn<ThreadView, String> threadStatus;
	@FXML
	private TableColumn<ThreadView, Integer> itemsCalculated;

	@FXML
	private TableView<Work> jobTable;
	@FXML
	private TableColumn<Work, Integer> idNumberColumn;
	@FXML
	private TableColumn<Work, String> taskTypeColumn;
	@FXML
	private TableColumn<Work, String> loadColumn;
	@FXML
	private TableColumn<Work, String> statusColumn;
	@FXML
	private ComboBox<String> taskTypeComboBox;
	@FXML
	private ComboBox<String> loadComboBox;
	@FXML
	private ComboBox<String> threadTypeComboBox;
	@FXML
	private Spinner<Integer> threadCountSpinner;
	@FXML
	private Spinner<Integer> numberOfJobsSpinner;
	@FXML
	private Spinner<Integer> blockSizeSpinner;

	Callback<Work, Observable[]> extractor = work -> {
		return new Observable[] { work.statusProperty() };
	};
	private final ObservableList<Work> jobData = FXCollections.observableArrayList(extractor);

	Callback<ThreadView, Observable[]> extractor2 = thread -> {
		return new Observable[] { thread.statusProperty(), thread.calculatedProperty() };
	};
	private final ObservableList<ThreadView> threadData = FXCollections.observableArrayList(extractor2);

	private Mediator mediator;

	@FXML
	public void initialize() {
		threadCountSpinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Runtime.getRuntime().availableProcessors(),2));
		numberOfJobsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 10, 10));
		blockSizeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 2));
		initTable();
	}

	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}

	private void initTable() {
		// idNumberColumn.setCellValueFactory(new PropertyValueFactory<Work,
		// Integer>("idNumberProperty"));

		idNumberColumn.setCellValueFactory(new Callback<CellDataFeatures<Work, Integer>, ObservableValue<Integer>>() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public ObservableValue<Integer> call(CellDataFeatures<Work, Integer> p) {
				return new ReadOnlyObjectWrapper(jobTable.getItems().indexOf(p.getValue()) + "");
			}
		});

		taskTypeColumn.setCellValueFactory(new PropertyValueFactory<Work, String>("taskTypeProperty"));
		loadColumn.setCellValueFactory(new PropertyValueFactory<Work, String>("loadProperty"));
		statusColumn.setCellValueFactory(i -> i.getValue().statusBinding());
		jobTable.setItems(jobData);

		threadId.setCellValueFactory(new PropertyValueFactory<ThreadView, Integer>("idNumberProperty"));
		threadTypeColumn.setCellValueFactory(new PropertyValueFactory<ThreadView, String>("threadTypeProperty"));
		threadStatus.setCellValueFactory(i -> i.getValue().statusBinding());
		itemsCalculated.setCellValueFactory(i -> i.getValue().calculatedBinding());

		threadList.setItems(threadData);
	}

	@FXML
	public void fillComboBoxes(String[] list, String[] list2, String[] list3) {
		taskTypeComboBox.getItems().addAll(list);
		taskTypeComboBox.getSelectionModel().selectFirst();
		threadTypeComboBox.getItems().addAll(list2);
		threadTypeComboBox.getSelectionModel().selectFirst();
		loadComboBox.getItems().addAll(list3);
		loadComboBox.getSelectionModel().selectFirst();
	}

	@FXML
	private void generateButtonPressed(ActionEvent event) {
		ArrayList<Work> works = WorkFactory.generate(taskTypeComboBox.getValue(), numberOfJobsSpinner.getValue(),
				loadComboBox.getValue());
		mediator.addWork(works);
		jobData.addAll(works);
	}

	@FXML
	private void clearListPressed(ActionEvent event) {

		mediator.clearWork();
		jobData.clear();
	}

	@FXML
	private void executeButtonPressed(ActionEvent event) {
		ControlSet control = new ControlSet(threadCountSpinner.getValue(), blockSizeSpinner.getValue());
		Distributor dist;
		switch (threadTypeComboBox.getValue()) {
		case "Single":
			dist = new ExampleSingleThreadDistributor(mediator, control);
			break;
		case "Thread":
			dist = new Task1UsingThreadDistributor(mediator, control);
			break;
		case "Executor":
			dist = new Task2UsingExecutorDistributor(mediator, control);
			break;
		default:
			dist = new ExampleSingleThreadDistributor(mediator, control);
		}
		dist.execute();
	}

	public void setWorkStatus(String status, Work work) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					jobData.stream().filter(item -> item.equals(work)).findFirst().get().setStatusProperty(status);
				} catch (java.util.NoSuchElementException ex) {
				}
			}
		});
	}

	public void registerThread(int worker, String type) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				threadData.add(new ThreadView(worker, type));
			}
		});
	}

	public void increaseCalculated(int worker) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				threadData.stream().filter(item -> item.getId() == worker).findFirst().get().increaseCalculated();
			}
		});

	}

	public void setRunStatus(String status, int worker) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				threadData.stream().filter(item -> item.getId() == worker).findFirst().get().setStatusProperty(status);
			}
		});

	}
}
