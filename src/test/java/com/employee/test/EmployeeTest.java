package com.employee.test;

import com.increff.employee.EmployeeApi;
import org.junit.Test;

import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;

public class EmployeeTest {

    @Test
    public void sayHello() throws Exception
    {
        EmployeeApi api = new EmployeeApi();

        api.delete();
        api.insert();
        api.select();
        ResultSet res = api.select();
        int i=0;

        while(res.next())
            i++;
//        Junit method to check result
        assertEquals(3, i);
    }

}
