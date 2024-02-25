/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import dto.ContactDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.derby.jdbc.ClientDriver;
/**
 *
 * @author minae
 */
public class DataAccessObject {
    public static int addContact(ContactDTO contact) throws SQLException{
        int result = 0;
        
        DriverManager.registerDriver(new ClientDriver());
        Connection connectToDB = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/phoneIndex", "root", "root");
        PreparedStatement sqlStatement = connectToDB.prepareStatement (
                "INSERT INTO CONTACTINFO VALUES ( ? , ? , ? , ? , ? , ? )");
        
        sqlStatement.setInt( 1, contact.getId()); 
        sqlStatement.setString( 2 , contact.getFirstName());
        sqlStatement.setString( 3 , contact.getMiddleName());
        sqlStatement.setString( 4 , contact.getLastName());
        sqlStatement.setString( 5 , contact.getEmail());
        sqlStatement.setString( 6 , contact.getPhone());
        
        result=sqlStatement.executeUpdate();
        
        sqlStatement.close();
        connectToDB.close();
        return result;
    }
    
    
    public static int updateContact(ContactDTO contact, int id) throws SQLException{
        int result = 0;
        
        DriverManager.registerDriver(new ClientDriver());
        Connection connectToDB = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/phoneIndex", "root", "root");
        PreparedStatement sqlStatement = connectToDB.prepareStatement (
                "UPDATE CONTACTINFO SET FIRSTNAME = ?, MIDNAME = ?, LASTNAME = ?, EMAIL = ?, PHONE = ? WHERE id = ?");

        sqlStatement.setString( 1 , contact.getFirstName());
        //sqlStatement.addBatch();
        sqlStatement.setString( 2 , contact.getMiddleName());
        //sqlStatement.addBatch();
        sqlStatement.setString( 3 , contact.getLastName());
        //sqlStatement.addBatch();
        sqlStatement.setString( 4 , contact.getEmail());
        //sqlStatement.addBatch();
        sqlStatement.setString( 5 , contact.getPhone());
        //sqlStatement.addBatch();
        sqlStatement.setInt( 6, contact.getId()); 
        //sqlStatement.addBatch();
        
        result=sqlStatement.executeUpdate();
        //connectToDB.commit();
        
        sqlStatement.close();
        connectToDB.close();
        return result;
    }
    
    
     public static int deleteContact(int id) throws SQLException{
        int result = 0;
        DriverManager.registerDriver(new ClientDriver());
        Connection connectToDB = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/phoneIndex", "root", "root");
        PreparedStatement sqlStatement = connectToDB.prepareStatement (
                "DELETE FROM CONTACTINFO WHERE ID = ?");
        
        sqlStatement.setInt( 1, id);
        result=sqlStatement.executeUpdate();
        
        sqlStatement.close();
        connectToDB.close();
        
        return result;
    }
     
    public static ResultSet allContact() throws SQLException{

        DriverManager.registerDriver(new ClientDriver());
        Connection connectToDB = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/phoneIndex", "root", "root");
        PreparedStatement sqlStatement = connectToDB.prepareStatement (
                "SELECT * FROM CONTACTINFO",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resSet = sqlStatement.executeQuery();
        
        return resSet;
    }
    
    /*public static ResultSet firstContact() throws SQLException{

        DriverManager.registerDriver(new ClientDriver());
        Connection connectToDB = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/phoneIndex", "root", "root");
        PreparedStatement sqlStatement = connectToDB.prepareStatement (
                "SELECT * FROM CONTACTINFO FETCH FIRST 1 ROW ONLY");

        ResultSet resSet = sqlStatement.executeQuery();
        
        return resSet;
    }*/
    
    /*
    public static ResultSet nextContact(int id) throws SQLException{

        DriverManager.registerDriver(new ClientDriver());
        Connection connectToDB = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/phoneIndex", "root", "root");
        PreparedStatement sqlStatement = connectToDB.prepareStatement (
                "SELECT * FROM CONTACTINFO where ID > ?");
        
        sqlStatement.setInt( 1, id);
        ResultSet resSet = sqlStatement.executeQuery();
        
        return resSet;
    }*/
    
    
    /*
    public static ResultSet previousContact(int id) throws SQLException{

        DriverManager.registerDriver(new ClientDriver());
        Connection connectToDB = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/phoneIndex", "root", "root");
        PreparedStatement sqlStatement = connectToDB.prepareStatement (
                "SELECT * FROM CONTACTINFO where ID < ? ORDER BY ID DESC");

        sqlStatement.setInt( 1, id);
        ResultSet resSet = sqlStatement.executeQuery();
        
        return resSet;
    }
    */
    
    /*public static ResultSet lastContact() throws SQLException{

        DriverManager.registerDriver(new ClientDriver());
        Connection connectToDB = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/phoneIndex", "root", "root");
        PreparedStatement sqlStatement = connectToDB.prepareStatement (
                "SELECT * FROM CONTACTINFO");
        ResultSet resSet = sqlStatement.executeQuery();
        
        return resSet;
    }*/
    
}
