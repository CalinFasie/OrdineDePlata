package gui;
//Opbug
import java.io.File;
import java.sql.*;
import javax.swing.*;
import java.io.FileInputStream;

public class javaconnect {
        
    Connection conn=null;
    public static Connection ConnecrDb(){
 //   File workingDirectory = new File((new StringBuilder()).append(System.getProperty("user.dir").toString()).append("/database").toString());
    File workingDirectory = new File(System.getProperty("user.dir").toString() + "\\database\\opbug.db");
    String adresa1 = workingDirectory.toString();
    adresa1 = adresa1.replace("\\", "\\\\");
    String adresa2 = "jdbc:sqlite:";
    String adresa = adresa2 + adresa1;
        try {
        Class.forName("org.sqlite.JDBC");
//http://stackoverflow.com/questions/17978152/how-to-get-realative-path-for-database        
        Connection conn = DriverManager.getConnection("jdbc:sqlite:/home/bogdanfasie/IdeaProjects/Opbug/database/opbug.db");
//        Connection conn = DriverManager.getConnection(adresa);
        return conn;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;}
    }
    
}
