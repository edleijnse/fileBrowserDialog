package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class FileBrowserDialogController implements Initializable{

    @FXML
    private Label label;

    FileChooser fileChooser = new FileChooser();
    DirectoryChooser directoryChooser = new DirectoryChooser();

    @FXML
    private void handleButtonDirectoryChooserAction(ActionEvent event) {
        System.out.println("Directory Chooser activated");
        label.setText("Directory Chooser");
        configuringFileChooser(fileChooser);

        // directoryChooser.setInitialDirectory( fileChooser.showOpenDialog(new Stage()));

        System.out.println("choosen directory: " + directoryChooser.showDialog(new Stage()));
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
        // TODO
    }
}
