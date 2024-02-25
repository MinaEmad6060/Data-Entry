package databaseaccess;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MyNotePad extends BorderPane {

    protected final MenuBar menuBar;
    protected final Menu menu;
    protected final MenuItem newFile;
    protected final MenuItem openFile;
    protected final MenuItem saveFile;
    protected final MenuItem exitFile;
    protected final Menu menu0;
    protected final MenuItem cutEdit;
    protected final MenuItem copyEdit;
    protected final MenuItem pasteEdit;
    protected final MenuItem deleteEdit;
    protected final MenuItem selectAllEdit;
    protected final Menu menu1;
    protected final MenuItem aboutHelp;
    protected final TextArea textArea;
    protected byte[] tempByteArray;
    protected String tempString;

    public MyNotePad(Stage s) {

        menuBar = new MenuBar();
        menu = new Menu();
        newFile = new MenuItem();
        openFile = new MenuItem();
        saveFile = new MenuItem();
        exitFile = new MenuItem();
        menu0 = new Menu();
        cutEdit = new MenuItem();
        copyEdit = new MenuItem();
        pasteEdit = new MenuItem();
        deleteEdit = new MenuItem();
        selectAllEdit = new MenuItem();
        menu1 = new Menu();
        aboutHelp = new MenuItem();
        textArea = new TextArea();
        tempString = new String();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(menuBar, javafx.geometry.Pos.CENTER);

        menu.setMnemonicParsing(false);
        menu.setText("File");

        newFile.setText("New");
        newFile.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
        newFile.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                textArea.clear();
            }
        });
        openFile.setMnemonicParsing(false);
        openFile.setText("Open");
        openFile.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        openFile.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            
            public void handle(ActionEvent event) {
                FileChooser fc = new FileChooser();
                File choosedFile = fc.showOpenDialog(s);
                //using H.L
                /*try {
                    FileInputStream inFile = new FileInputStream(choosedFile);
                    DataInputStream inData = new DataInputStream(inFile);
                    textArea.setText(inData.readUTF());
                    inFile.close();
                    inData.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MyNotePad.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MyNotePad.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                
                //using L.L
                try {
                    FileInputStream inFile = new FileInputStream(choosedFile);
                    int size = inFile.available();
                    byte[] b = new byte[size];
                    inFile.read(b);
                    String readedFile = new String(b);
                    textArea.setText(readedFile);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MyNotePad.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MyNotePad.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        saveFile.setMnemonicParsing(false);
        saveFile.setText("Save");
        saveFile.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        saveFile.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {

                FileChooser fc = new FileChooser();
                File selectedFile = fc.showSaveDialog(s);
                //using H.L
                /*try {
                    FileOutputStream outFile = new FileOutputStream(selectedFile);//L.L
                    DataOutputStream outData = new DataOutputStream(outFile);//H.L
                    outData.writeUTF(textArea.getText());
                    outData.close();
                    outFile.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MyNotePad.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MyNotePad.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                
                //using L.L
                try {
                    FileOutputStream outFile = new FileOutputStream(selectedFile);
                    int size = textArea.getText().length();
                    byte[] b = new byte[size];
                    b = textArea.getText().getBytes();
                    outFile.write(b);
                    outFile.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MyNotePad.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MyNotePad.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        exitFile.setMnemonicParsing(false);
        exitFile.setText("Exit");
        exitFile.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
        exitFile.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                s.close();
            }
        });

        menu0.setMnemonicParsing(false);
        menu0.setText("Edit");

        cutEdit.setMnemonicParsing(false);
        cutEdit.setText("Cut");
        cutEdit.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
        cutEdit.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.cut();
            }
        });
        

        copyEdit.setMnemonicParsing(false);
        copyEdit.setText("Copy");
        copyEdit.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
        copyEdit.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.copy();
            }
        });

        pasteEdit.setMnemonicParsing(false);
        pasteEdit.setText("Paste");
        pasteEdit.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN));
        pasteEdit.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.paste();
            }
        });

        deleteEdit.setMnemonicParsing(false);
        deleteEdit.setText("Delete");
        deleteEdit.setAccelerator(new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN));
        deleteEdit.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.deleteText(textArea.getSelection().getStart(), textArea.getSelection().getEnd());
            }
        });

        selectAllEdit.setMnemonicParsing(false);
        selectAllEdit.setText("Select All");
        selectAllEdit.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
        selectAllEdit.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.selectRange(0, textArea.getText().length());
            }
        });

        menu1.setMnemonicParsing(false);
        menu1.setText("Help");

        aboutHelp.setMnemonicParsing(false);
        aboutHelp.setText("About");
        aboutHelp.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert about = new Alert(Alert.AlertType.INFORMATION);
                about.setTitle("About");
                about.setHeaderText("Welcome!");
                about.setContentText("Your are using special version of notepad!");
                about.showAndWait();
            }
        });
        
        setTop(menuBar);
        
        BorderPane.setAlignment(textArea, javafx.geometry.Pos.CENTER);
        textArea.setPrefHeight(200.0);
        textArea.setPrefWidth(200.0);
        setCenter(textArea);

        menu.getItems().add(newFile);
        menu.getItems().add(openFile);
        menu.getItems().add(saveFile);
        menu.getItems().add(exitFile);
        menuBar.getMenus().add(menu);
        menu0.getItems().add(cutEdit);
        menu0.getItems().add(copyEdit);
        menu0.getItems().add(pasteEdit);
        menu0.getItems().add(deleteEdit);
        menu0.getItems().add(selectAllEdit);
        menuBar.getMenus().add(menu0);
        menu1.getItems().add(aboutHelp);
        menuBar.getMenus().add(menu1);

    }
}
