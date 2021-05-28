package gui;
    
import gui.Login_jframe;
import static gui.Login_jframe.fileName;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelToDb {
    
      public static void ExcelToDatab() {  
//        String fileName = "D:\\20E.xlsx";
//          String fileName = Login_jframe. selectedFile.getAbsolutePath();
        Vector dataHolder=read(Login_jframe.fileName);
        saveToDatabase(dataHolder);
    }
    public static Vector read(String fileName)    {
        Vector cellVectorHolder = new Vector();
        try{
            FileInputStream myInput = new FileInputStream(fileName);
            XSSFWorkbook myWorkBook = new XSSFWorkbook(myInput);
            XSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator rowIter = mySheet.rowIterator();
            while(rowIter.hasNext()){
                XSSFRow myRow = (XSSFRow) rowIter.next();
                Iterator cellIter = myRow.cellIterator();
                List list = new ArrayList();
            
                Cell a = myRow.getCell(0);
                Cell b = myRow.getCell(1);
                Cell c = myRow.getCell(2);
                if (a == null || a.getCellType() == Cell.CELL_TYPE_BLANK){     }  else  {
                list.add(a);
                }
                if (b == null || b.getCellType() == Cell.CELL_TYPE_BLANK){     }  else  {
                list.add(b);}
                if (c == null || c.getCellType() == Cell.CELL_TYPE_BLANK){     }  else  {
                list.add(c);}
                cellVectorHolder.addElement(list);
            }
        }catch (Exception e){e.printStackTrace(); }
        return cellVectorHolder;
    }
    private static void saveToDatabase(Vector dataHolder) {
        String vcod_angr = "";
        String vcod_sect = "";
        String vcod_ind = "";
        String vcod_ang = "";  
        String vcod_rd = "";
        System.out.println(dataHolder);

//        for(Iterator iterator = dataHolder.iterator();iterator.hasNext();) {
//            List list = (List) iterator.next();
//            Integer lungime = list.size();
            
//            vcod_angr = list.get(0).toString();
//            vcod_sect = list.get(1).toString();
//            vcod_ind = list.get(2).toString();
//        }
            try { 
                File workingDirectory = new File(System.getProperty("user.dir").toString() + "\\database\\opbug.db");
                String adresa1 = workingDirectory.toString();
                adresa1 = adresa1.replace("\\", "\\\\");
                String adresa2 = "jdbc:sqlite:";
                String adresa = adresa2 + adresa1;
                Class.forName("org.sqlite.JDBC");
//                Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Bretan\\Dropbox\\opbug\\database\\opbug.db");
                Connection conn = DriverManager.getConnection(adresa);
                System.out.println("connection made...");
                String sterg = "Delete from angaj";
                PreparedStatement pst = conn.prepareStatement(sterg);
                pst.execute();
                PreparedStatement stmt=conn.prepareStatement("INSERT INTO angaj(cod_angr,cod_sect,cod_ind,cod_ang,cod_rd) VALUES(?,?,?,?,?)");
                
                for(Iterator iterator = dataHolder.iterator();iterator.hasNext();) {
                 List lista = (List) iterator.next();
                 Integer lungime = lista.size();
//              System.out.println("lista e :"+lista);
                 vcod_angr = lista.get(0).toString();
                 vcod_sect = lista.get(1).toString();
                 vcod_ind = lista.get(2).toString();
                 
                 if (vcod_angr.length() == 17) {
                 vcod_ang = vcod_angr.substring(0, 11);
                 vcod_rd = vcod_angr.substring(14, 17);
                 }
                 
                 stmt.setString(1, vcod_angr);
                 stmt.setString(2, vcod_sect);
                 stmt.setString(3, vcod_ind);
                 stmt.setString(4, vcod_ang);
                 stmt.setString(5, vcod_rd);
                 stmt.executeUpdate();
                }    
                System.out.println("Data is inserted");
                String stergdupa = "Delete from angaj where rowid < 4";
                PreparedStatement pstdupa = conn.prepareStatement(stergdupa);
//                pstdupa.execute();
                stmt.close();
                conn.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } 
//            catch (InstantiationException e) {
//            e.printStackTrace();
//            } 
//        catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
        }
        }
//    }