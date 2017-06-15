package sample;

import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class FileBrowserDialogController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private ListView directoriesList = new ListView();

    @FXML
    private CheckBox removeChecked = new CheckBox();

    @FXML
    private Button closeButton;

    FileChooser fileChooser = new FileChooser();
    DirectoryChooser directoryChooser = new DirectoryChooser();
    ObservableList dataDirectories =
            FXCollections.observableArrayList();

    Favorites favorites = new Favorites();

    String myFavoriteDirectories = "/Users/Shared/favoriteDirectoriesFile";

    public String getMyFavoriteDirectories() {
        return myFavoriteDirectories;
    }

    public void setMyFavoriteDirectories(String myFavoriteDirectories) {
        this.myFavoriteDirectories = myFavoriteDirectories;
    }

    @FXML
    private void handleButtonDirectoryChooserAction(ActionEvent event) {
        System.out.println("Directory Chooser activated");
        // label.setText("Directory Chooser");
        configuringFileChooser(fileChooser);
        configuringDirectoryChooser(directoryChooser);


        File choosedDirectory = directoryChooser.showDialog(new Stage());

        String myChoosenDirectory = choosedDirectory.toPath().toString();
        System.out.println("choosedDirectory: " + myChoosenDirectory);

        dataDirectories.addAll(myChoosenDirectory);


        favorites.addDirectory(myChoosenDirectory);
        favorites.writeFavoriteDirectoriesToFile(myFavoriteDirectories);
        //System.out.println("choosen directory: " + directoryChooser.showDialog(new Stage()));

        directoriesList.setItems(dataDirectories);
    }

    @FXML
    private void handleClose(){
        System.out.println("Cancelled clicked");
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void handleDirectoriesListAction(MouseEvent event) {
        String myDirectory = directoriesList.getSelectionModel().getSelectedItem().toString();
        System.out.println("clicked on " + myDirectory);
        if (removeChecked.isSelected() == true) {
           System.out.println("Remove file from Directory");
           favorites.removeDirectory(myDirectory);
           favorites.writeFavoriteDirectoriesToFile(myFavoriteDirectories);
           dataDirectories.remove(myDirectory);
           directoriesList.setItems(dataDirectories);
        } else {
            fileChooser.setTitle("Select file to open");
            fileChooser.setInitialDirectory(new File(myDirectory));
            File choosedFile = fileChooser.showOpenDialog(new Stage());
            System.out.println("choosedFile: " + choosedFile);
            if (choosedFile != null) {
                // TODO https://stackoverflow.com/questions/33094981/javafx-8-open-a-link-in-a-browser-without-reference-to-application
                // HostServices hs = new HostServices();
                // hs.showDocument(choosedFile.toURI().toString());

                System.out.println("before showDocument: " + choosedFile.toString());
                try {
                    this.getHostServices().showDocument(choosedFile.toURI().toURL().toExternalForm());
                } catch (Exception e) {
                    System.out.println("Exception: " + e);
                }

                System.out.println("after showDocument: " + choosedFile.toString());

            }
        }
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

    private HostServices hostServices;

    public HostServices getHostServices() {
        return hostServices;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataDirectories.clear();
        favorites.setDirectoriesFromFile(myFavoriteDirectories);
        dataDirectories.addAll(favorites.getDirectories());
        directoriesList.setItems(dataDirectories);
    }
}
