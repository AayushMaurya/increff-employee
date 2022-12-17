package com.increff.employee;

import java.sql.*;

public class Hello {
    public static void main(String[] args) throws SQLException
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "Aayush", "Aayush10m");
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("select * from employees");
            while(res.next())
            {
                System.out.println(res.getInt(1) + " " + res.getString(2) + " " + res.getInt(3));
            }
            stmt.executeUpdate("delete from employees where id=3");
            res = stmt.executeQuery("select * from employees");
            while(res.next())
            {
                System.out.println(res.getInt(1) + " " + res.getString(2) + " " + res.getInt(3));
            }
            con.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
