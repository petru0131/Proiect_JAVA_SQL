/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bdproiect;

/**
 *
 * @author Petru
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionJDBC {

    public static final String DBURL = "jdbc:mysql://localhost:3306";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DBNAME = "/schimbval";
    

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(DBURL + DBNAME, USER, PASSWORD);
        return conn;

    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {

                System.out.println(e.getMessage());

            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {

                System.out.println(e.getMessage());

            }
        }
    }

    public static void closePreparedStatement(Statement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {

                System.out.println(e.getMessage());

            }
        }
    }

    public static void closeAll(Connection conn, Statement pstmt, ResultSet rs) {

        closeResultSet(rs);
        closePreparedStatement(pstmt);
        closeConnection(conn);

    }
}
