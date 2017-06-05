package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class FileBrowserDialogController implements Initializable{

    @FXML
    private Label label;

    @FXML
    private ListView directoriesList = new ListView();

    FileChooser fileChooser = new FileChooser();
    DirectoryChooser directoryChooser = new DirectoryChooser();
    ObservableList dataDirectories =
            FXCollections.observableArrayList();

    @FXML
    private void handleButtonDirectoryChooserAction(ActionEvent event) {
        System.out.println("Directory Chooser activated");
        label.setText("Directory Chooser");
        configuringFileChooser(fileChooser);
        configuringDirectoryChooser(directoryChooser);




        File choosedDirectory = directoryChooser.showDialog(new Stage());

        String myChoosenDirectory = choosedDirectory.toPath().toString();
        System.out.println("choosedDirectory: " + myChoosenDirectory);

        dataDirectories.addAll(myChoosenDirectory);
        //System.out.println("choosen directory: " + directoryChooser.showDialog(new Stage()));
        directoriesList.setItems(dataDirectories);
    }

    @FXML
    private void handleDirectoriesListAction(MouseEvent event){
        System.out.println("clicked on " + directoriesList.getSelectionModel().getSelectedItem());

    }

    private void configuringFileChooser(FileChooser fileChooser) {
        // Set title for FileChooser
        fileChooser.setTitle("Select Some Files");

        // Set Initial Directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }

    private void configuringDirectoryChooser(DirectoryChooser directoryChooser) {
        // Set title for FileChooser
        directoryChooser.setTitle("Select Directory");

        // Set Initial Directory
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataDirectories.clear();dataDirectories.clear();
    }
}
