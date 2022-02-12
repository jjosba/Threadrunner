package fi.utu.tech.threadrunner2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import fi.utu.tech.threadrunner2.mediator.Mediator;
import fi.utu.tech.threadrunner2.mediator.MediatorService;
import fi.utu.tech.threadrunner2.ui.MainViewController;
import fi.utu.tech.threadrunner2.works.WorkFactory;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private Mediator mediator;
    private MainViewController controller;

    @Override
    public void start(Stage stage) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
    	 Parent root = loader.load();
         scene = new Scene( root, 1024, 768 );
         controller = loader.getController();
        stage.setScene(scene);
        initialize();
        stage.show();
    }

    private void initialize() {
    	controller.fillComboBoxes(WorkFactory.getTaskTypes(), Mediator.getThreadTypes(),  new String[]{"Light", "Medium", "Heavy", "Mixed"});
    	mediator = new MediatorService((MainViewController)controller);
    	controller.setMediator(mediator);
    }
    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) { 
        launch();
    }

    
}