package databaseaccess;

import db.DataAccessObject;
import dto.ContactDTO;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.EventListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
//import org.apache.derby.jdbc.ClientDriver;

public class AccessDB extends BorderPane {

    protected final FlowPane flowPane;
    protected final Button newButton;
    protected final Button updateButton;
    protected final Button deleteButton;
    protected final Button firstButton;
    protected final Button previousButton;
    protected final Button nextButton;
    protected final Button lastButton;
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final RowConstraints rowConstraints2;
    protected final RowConstraints rowConstraints3;
    protected final RowConstraints rowConstraints4;
    protected final Label label;
    protected final Label label0;
    protected final Label label1;
    protected final Label label2;
    protected final Label label3;
    protected final Label label4;
    protected final TextField idArea;
    protected final TextField fnArea;
    protected final TextField midNameArea;
    protected final TextField LnArea;
    protected final TextField emailArea;
    protected final TextField phArea;
    ResultSet resSet;

    
    
    public AccessDB(Stage stage) {

        flowPane = new FlowPane();
        newButton = new Button();
        updateButton = new Button();
        deleteButton = new Button();
        firstButton = new Button();
        previousButton = new Button();
        nextButton = new Button();
        lastButton = new Button();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        rowConstraints2 = new RowConstraints();
        rowConstraints3 = new RowConstraints();
        rowConstraints4 = new RowConstraints();
        label = new Label();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        label4 = new Label();
        idArea = new TextField();
        fnArea = new TextField();
        midNameArea = new TextField();
        LnArea = new TextField();
        emailArea = new TextField();
        phArea = new TextField();

        
        idArea.setEditable(false);
        fnArea.setEditable(false);
        midNameArea.setEditable(false);
        LnArea.setEditable(false);
        emailArea.setEditable(false);
        phArea.setEditable(false);
        
        
        
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        try {
            resSet=DataAccessObject.allContact();
        } catch (SQLException ex) {
            Logger.getLogger(AccessDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        BorderPane.setAlignment(flowPane, javafx.geometry.Pos.CENTER);
        flowPane.setHgap(20.0);
        flowPane.setPrefHeight(60.0);
        flowPane.setPrefWidth(600.0);

        newButton.setMnemonicParsing(false);
        newButton.setText("New...");
        newButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(newButton.getText()=="New..."){
                    idArea.clear();
                    idArea.setEditable(true);
                    fnArea.clear();
                    fnArea.setEditable(true);
                    midNameArea.clear(); 
                    midNameArea.setEditable(true);
                    LnArea.clear();
                    LnArea.setEditable(true);
                    emailArea.clear(); 
                    emailArea.setEditable(true);
                    phArea.clear();
                    phArea.setEditable(true);
                    newButton.setText("Save");
                }else if(newButton.getText()=="Save" && !idArea.getText().isEmpty()){
                    ContactDTO contact = new ContactDTO(
                        Integer.parseInt(idArea.getText()), 
                        fnArea.getText(), 
                        midNameArea.getText(), 
                        LnArea.getText(), 
                        emailArea.getText(), 
                        phArea.getText());
                        newButton.setText("New...");
                    try {
                        if(DataAccessObject.addContact(contact)<=0){
                            System.out.println("Not Added!");
                        }else{
                            System.out.println("Added!");
                            idArea.clear();
                            fnArea.clear();
                            midNameArea.clear(); 
                            LnArea.clear();
                            emailArea.clear(); 
                            phArea.clear();
                            
                        }
                    } catch (SQLException ex) {
                        Alert about = new Alert(Alert.AlertType.INFORMATION);
                        about.setTitle("Warnnig");
                        about.setHeaderText("You have entered unexpected value!");
                        about.showAndWait();
                    }
                }
                else{
                    System.out.println("Error!");
                    Alert about = new Alert(Alert.AlertType.INFORMATION);
                    about.setTitle("Warnnig");
                    about.setHeaderText("You have entered unexpected value!");
                    about.showAndWait();
                }
                try {
                    resSet=DataAccessObject.allContact();
                } catch (SQLException ex) {
                    Logger.getLogger(AccessDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });      

        updateButton.setMnemonicParsing(false);
        updateButton.setText("Update");
        updateButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try {
                    if((updateButton.getText()=="Update")){
                    
                        //DataAccessObject.deleteContact(Integer.parseInt(idArea.getText()));
                        updateButton.setText("Save");
                        //idArea.setEditable(true);
                        fnArea.setEditable(true);
                        midNameArea.setEditable(true);
                        LnArea.setEditable(true);
                        emailArea.setEditable(true);
                        phArea.setEditable(true);
                    }else if(updateButton.getText()=="Save"){
                        ContactDTO contact = new ContactDTO(
                                Integer.parseInt(idArea.getText()),
                                fnArea.getText(),
                                midNameArea.getText(),
                                LnArea.getText(),
                                emailArea.getText(),
                                phArea.getText());
                                updateButton.setText("Update");
                        
                            if(DataAccessObject.updateContact(contact,
                                    Integer.parseInt(idArea.getText()))<=0){
                                System.out.println("Not Updated!");
                            }else{
                                System.out.println("Updated!");
                                idArea.clear();
                                fnArea.clear();
                                midNameArea.clear();
                                LnArea.clear();
                                emailArea.clear();
                                phArea.clear();
                            }
                        }
                        resSet=DataAccessObject.allContact();
                } catch (SQLException ex) {
                            Alert about = new Alert(Alert.AlertType.INFORMATION);
                            about.setTitle("Warnnig");
                            about.setHeaderText("You have entered duplicated value!");
                            about.showAndWait();
                        }                
                           
            } });
        
        deleteButton.setMnemonicParsing(false);
        deleteButton.setText("Delete");
        deleteButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try {
                    int i=DataAccessObject.deleteContact(Integer.parseInt(idArea.getText()));
                    if(i<=0){
                        System.out.println("Not Deleted!");
                    }else{
                        System.out.println("Deleted!");
                        idArea.clear();
                        idArea.setEditable(false);
                        fnArea.clear();
                        fnArea.setEditable(false);
                        midNameArea.clear();
                        midNameArea.setEditable(false);
                        LnArea.clear();
                        LnArea.setEditable(false);
                        emailArea.clear();
                        emailArea.setEditable(false);
                        phArea.clear();
                        phArea.setEditable(false);
                    }
                    resSet=DataAccessObject.allContact();
                   
                } catch (SQLException ex) {
                    System.out.println("Can't Delete!");
                }
            }
        });

        firstButton.setMnemonicParsing(false);
        firstButton.setText("First");
        firstButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try {
                    
                    //resSet=DataAccessObject.allContact();
                    if(resSet.first()){
                        idArea.setText(resSet.getString(1));
                        idArea.setEditable(false);
                        fnArea.setText(resSet.getString(2));
                        fnArea.setEditable(false);
                        midNameArea.setText(resSet.getString(3));
                        midNameArea.setEditable(false);
                        LnArea.setText(resSet.getString(4));
                        LnArea.setEditable(false);
                        emailArea.setText(resSet.getString(5));
                        emailArea.setEditable(false);
                        phArea.setText(resSet.getString(6));
                        phArea.setEditable(false);
                    }
                    
                    /*
                    resSet=DataAccessObject.firstContact();
                    while(resSet.next()){
                        idArea.setText(resSet.getString(1));
                        idArea.setEditable(false);
                        fnArea.setText(resSet.getString(2));
                        fnArea.setEditable(false);
                        midNameArea.setText(resSet.getString(3));
                        midNameArea.setEditable(false);
                        LnArea.setText(resSet.getString(4));
                        LnArea.setEditable(false);
                        emailArea.setText(resSet.getString(5));
                        emailArea.setEditable(false);
                        phArea.setText(resSet.getString(6));
                        phArea.setEditable(false);
                    }
                    */

                    //sqlStatement.close();
                    //connectToDB.close();
  
                } catch (SQLException ex) {
                    System.out.println("Can't reach first record!");
                }
            }
        });

        

        nextButton.setMnemonicParsing(false);
        nextButton.setText("Next");
        nextButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try {
                    //resSet=DataAccessObject.allContact();
                    if(resSet.next()){
                        idArea.setText(resSet.getString(1));
                        idArea.setEditable(false);
                        fnArea.setText(resSet.getString(2));
                        fnArea.setEditable(false);
                        midNameArea.setText(resSet.getString(3));
                        midNameArea.setEditable(false);
                        LnArea.setText(resSet.getString(4));
                        LnArea.setEditable(false);
                        emailArea.setText(resSet.getString(5));
                        emailArea.setEditable(false);
                        phArea.setText(resSet.getString(6));
                        phArea.setEditable(false);
                    }
                    
                    /*resSet=DataAccessObject.nextContact();
                    resSet.next();
                    idArea.setText(resSet.getString(1));
                    idArea.setEditable(false);
                    fnArea.setText(resSet.getString(2));
                    fnArea.setEditable(false);
                    midNameArea.setText(resSet.getString(3));
                    midNameArea.setEditable(false);
                    LnArea.setText(resSet.getString(4));
                    LnArea.setEditable(false);
                    emailArea.setText(resSet.getString(5));
                    emailArea.setEditable(false);
                    phArea.setText(resSet.getString(6));
                    phArea.setEditable(false);*/

                    //sqlStatement.close();
                    //connectToDB.close();
  
                } catch (SQLException ex) {
                    System.out.println("Can't reach next record!");
                    //Logger.getLogger(AccessDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        previousButton.setMnemonicParsing(false);
        previousButton.setText("Previous");
        previousButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try {
                    //resSet=DataAccessObject.allContact();
                    if(resSet.previous()){
                        idArea.setText(resSet.getString(1));
                        idArea.setEditable(false);
                        fnArea.setText(resSet.getString(2));
                        fnArea.setEditable(false);
                        midNameArea.setText(resSet.getString(3));
                        midNameArea.setEditable(false);
                        LnArea.setText(resSet.getString(4));
                        LnArea.setEditable(false);
                        emailArea.setText(resSet.getString(5));
                        emailArea.setEditable(false);
                        phArea.setText(resSet.getString(6));
                        phArea.setEditable(false);
                    }
                    /*
                    resSet=DataAccessObject.previousContact(Integer.parseInt(idArea.getText()));
                    resSet.next();
                    idArea.setText(resSet.getString(1));
                    idArea.setEditable(false);
                    fnArea.setText(resSet.getString(2));
                    fnArea.setEditable(false);
                    midNameArea.setText(resSet.getString(3));
                    midNameArea.setEditable(false);
                    LnArea.setText(resSet.getString(4));
                    LnArea.setEditable(false);
                    emailArea.setText(resSet.getString(5));
                    emailArea.setEditable(false);
                    phArea.setText(resSet.getString(6));
                    phArea.setEditable(false);
                    */

                    //sqlStatement.close();
                    //connectToDB.close();
  
                } catch (SQLException ex) {
                    System.out.println("Can't reach previous record!");
                }
            }
        });
        
        

        lastButton.setMnemonicParsing(false);
        lastButton.setText("Last");
        
        lastButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                /*Parent root1 = new MyNotePad(stage);

                Scene scene1 = new Scene(root1);

                stage.setScene(scene1);*/
                    
                try {

                    //resSet=DataAccessObject.allContact();
                    if(resSet.last()){
                        idArea.setText(resSet.getString(1));
                        idArea.setEditable(false);
                        fnArea.setText(resSet.getString(2));
                        fnArea.setEditable(false);
                        midNameArea.setText(resSet.getString(3));
                        midNameArea.setEditable(false);
                        LnArea.setText(resSet.getString(4));
                        LnArea.setEditable(false);
                        emailArea.setText(resSet.getString(5));
                        emailArea.setEditable(false);
                        phArea.setText(resSet.getString(6));
                        phArea.setEditable(false);
                    }
                    /*
                    resSet=DataAccessObject.lastContact();
                    while(resSet.next()){
                        idArea.setText(resSet.getString(1));
                        idArea.setEditable(false);
                        fnArea.setText(resSet.getString(2));
                        fnArea.setEditable(false);
                        midNameArea.setText(resSet.getString(3));
                        midNameArea.setEditable(false);
                        LnArea.setText(resSet.getString(4));
                        LnArea.setEditable(false);
                        emailArea.setText(resSet.getString(5));
                        emailArea.setEditable(false);
                        phArea.setText(resSet.getString(6));
                        phArea.setEditable(false);
                    }*/
                } catch (SQLException ex) {
                    System.out.println("Can't reach first record!");
                }
            }
        });
        

        flowPane.setPadding(new Insets(13.0, 10.0, 10.0, 30.0));
        setBottom(flowPane);

        BorderPane.setAlignment(gridPane, javafx.geometry.Pos.CENTER);

        columnConstraints.setHalignment(javafx.geometry.HPos.CENTER);
        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMaxWidth(294.0);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(145.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMaxWidth(471.0);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(455.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(30.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(30.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints2.setMinHeight(10.0);
        rowConstraints2.setPrefHeight(30.0);
        rowConstraints2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints3.setMinHeight(10.0);
        rowConstraints3.setPrefHeight(30.0);
        rowConstraints3.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints4.setMinHeight(10.0);
        rowConstraints4.setPrefHeight(30.0);
        rowConstraints4.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        label.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label.setText("ID");

        GridPane.setRowIndex(label0, 1);
        label0.setText("First Name");

        GridPane.setRowIndex(label1, 2);
        label1.setText("Middle Name");

        GridPane.setRowIndex(label2, 3);
        label2.setText("Last Name");

        GridPane.setRowIndex(label3, 4);
        label3.setText("E-mail");

        GridPane.setRowIndex(label4, 5);
        label4.setText("Phone");

        GridPane.setColumnIndex(idArea, 1);
        idArea.setMaxWidth(USE_PREF_SIZE);
        idArea.setPrefHeight(31.0);
        idArea.setPrefWidth(186.0);

        GridPane.setColumnIndex(fnArea, 1);
        GridPane.setRowIndex(fnArea, 1);
        fnArea.setMaxWidth(USE_PREF_SIZE);
        fnArea.setPrefHeight(31.0);
        fnArea.setPrefWidth(294.0);

        GridPane.setColumnIndex(midNameArea, 1);
        GridPane.setRowIndex(midNameArea, 2);
        midNameArea.setMaxWidth(USE_PREF_SIZE);

        GridPane.setColumnIndex(LnArea, 1);
        GridPane.setRowIndex(LnArea, 3);
        LnArea.setMaxWidth(USE_PREF_SIZE);
        LnArea.setPrefHeight(31.0);
        LnArea.setPrefWidth(293.0);

        GridPane.setColumnIndex(emailArea, 1);
        GridPane.setRowIndex(emailArea, 4);

        GridPane.setColumnIndex(phArea, 1);
        GridPane.setRowIndex(phArea, 5);
        phArea.setMaxWidth(USE_PREF_SIZE);
        phArea.setPrefHeight(31.0);
        phArea.setPrefWidth(184.0);
        setCenter(gridPane);

        flowPane.getChildren().add(newButton);
        flowPane.getChildren().add(updateButton);
        flowPane.getChildren().add(deleteButton);
        flowPane.getChildren().add(firstButton);
        flowPane.getChildren().add(previousButton);
        flowPane.getChildren().add(nextButton);
        flowPane.getChildren().add(lastButton);
        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getRowConstraints().add(rowConstraints2);
        gridPane.getRowConstraints().add(rowConstraints3);
        gridPane.getRowConstraints().add(rowConstraints4);
        gridPane.getChildren().add(label);
        gridPane.getChildren().add(label0);
        gridPane.getChildren().add(label1);
        gridPane.getChildren().add(label2);
        gridPane.getChildren().add(label3);
        gridPane.getChildren().add(label4);
        gridPane.getChildren().add(idArea);
        gridPane.getChildren().add(fnArea);
        gridPane.getChildren().add(midNameArea);
        gridPane.getChildren().add(LnArea);
        gridPane.getChildren().add(emailArea);
        gridPane.getChildren().add(phArea);

    }
}
