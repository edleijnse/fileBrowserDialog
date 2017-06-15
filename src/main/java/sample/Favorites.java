package sample;

import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by edleijnse on 15.06.17.
 */
public class Favorites {
    ArrayList<String> directories = new ArrayList<String>();

    public ArrayList<String> getDirectories() {
        return directories;
    }

    public void setDirectories(ArrayList<String> directories) {
        this.directories = directories;
    }

    public void setDirectoriesFromFile(String fileName) {
        System.out.println("Directories from File: " + fileName);
        ArrayList<String> directoriesFromFile = new ArrayList<String>();


        Path path = Paths.get(fileName);
        try {
            try (BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    directoriesFromFile.add(line);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.directories = directoriesFromFile;


    }

    public void writeFavoriteDirectoriesToFile(String fileName) {
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            for (String myDirectory : directories
                    ) {
                System.out.println("myDirectory: " + myDirectory);
                writer.println(myDirectory);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void addDirectory(String newDirectory) {
        directories.add(newDirectory);
    }

    public void removeDirectory(String newDirectory) {
        directories.remove(newDirectory);
    }


    public static void main(String[] args) {

        Favorites favorites = new Favorites();
        System.out.println("Favorites started");
        ArrayList<String> someDirectories = new ArrayList<String>();
        someDirectories.add("Documents");
        someDirectories.add("Downloads");
        favorites.setDirectories(someDirectories);
        favorites.addDirectory("another");

        XStream xstream = new XStream();

        System.out.println("favorites: " + xstream.toXML(favorites.getDirectories()));

        favorites.writeFavoriteDirectoriesToFile("favoriteDirectoriesFile");

        favorites.setDirectoriesFromFile("favoriteDirectoriesFile");
        System.out.println("favorites 2: " + xstream.toXML(favorites.getDirectories()));
        favorites.removeDirectory("Downloads");
        System.out.println("favorites 2: " + xstream.toXML(favorites.getDirectories()));

    }
}
