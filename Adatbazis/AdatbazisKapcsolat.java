/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adatbazis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ndavid97
 */
public class AdatbazisKapcsolat {
    static Connection con;

    public static Connection getKapcsolat() throws SQLException{
        con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=Készlet_teszt;user=ndavid26;password=bogar97");
        return con;
}
}
