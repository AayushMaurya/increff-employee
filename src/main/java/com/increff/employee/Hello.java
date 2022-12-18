package com.increff.employee;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Hello {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException
    {
        Properties props = new Properties();
        InputStream inStream = new FileInputStream("employee.properties");
        props.load(inStream);

            Class.forName(props.getProperty("jdbc.driver"));
            Connection con = DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.user"),
                    props.getProperty("jdbc.password"));

            insert(con);
            select(con);
            delete(con);

            con.close();
    }

    private static void select(Connection con) throws SQLException
    {
        System.out.println("Selecting Employee");
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery("select * from employees");
        while(res.next())
        {
            System.out.println(res.getInt(1) + "  " + res.getString(2) + "  " + res.getInt(3));
        }
        stmt.close();
    }

    private static void insert(Connection con) throws SQLException
    {
        System.out.println("Inserting into employees");
            Statement stmt = con.createStatement();
            for(int i=5; i<8; i++)
            {
                int id = i;
                int age = 20 + i;
                String name = "user" + i;
                stmt.executeUpdate("insert into employees values ( " + id + ",'" + name + "', " + age + ");");
            }
            stmt.close();
    }

    private static void delete(Connection con) throws SQLException
    {
        System.out.println("Deleting Employee");
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery("select * from employees");

        List<Integer> idList = new ArrayList<Integer>();
        while(res.next())
        {
            idList.add(res.getInt(1));
        }
        for(int i=0; i<idList.size(); i++)
        {
            stmt.executeUpdate("delete from employees where id=" + idList.get(i) + ";");
        }
        stmt.close();
    }
}
