/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author daksa
 */
public class Koneksi 
{
    private static Connection mysqlconnect;
    
    public static Connection koneksi() throws SQLException
    {
        if(mysqlconnect == null)
        {
            try
            {
                String db = "jdbc:mysql://localhost:3306/hotel";
                String user = "root";
                String pass = "daksa";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                mysqlconnect = (Connection) DriverManager.getConnection(db, user, pass); 
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "gagal koneksi");
            }
        }
        return mysqlconnect;
    }
    
    public static boolean Executesql(String sql) throws SQLException
    {
        boolean h = false;
        if(mysqlconnect != null)
        {
            PreparedStatement pst = mysqlconnect.prepareStatement(sql);
            h = pst.execute();
        }
        return h;
    }
    
    public static ResultSet QuerySql(String sql) throws SQLException
    {
        ResultSet rs = null;
        if(mysqlconnect != null)
        {
            Statement stm = mysqlconnect.createStatement();
            rs = stm.executeQuery(sql);
        }
        return rs;
    }
}
