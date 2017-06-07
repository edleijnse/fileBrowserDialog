package sample;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FileBrowserDialogMain extends Application {

    private HostServices hostServices ;

    public HostServices getMyHostServices() {
        return hostServices ;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices ;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.setHostServices(hostServices);
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("resources/fileBrowserDialog.fxml"));
        Parent root = loader.load();
        FileBrowserDialogController controller = loader.getController();
        controller.setHostServices(getHostServices());
        this.setHostServices(controller.getHostServices());

        primaryStage.setTitle("Filebrowser Dialog");
        primaryStage.setScene(new Scene(root, 500, 375));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
