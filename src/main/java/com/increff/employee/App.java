package com.increff.employee;

public class App {
    public static void main(String[] args) throws Exception
    {
        System.out.println("Hello");
        HibernateUtil.configure();

        EmployeeHibernateApi api = new EmployeeHibernateApi();

        api.deleteAll();

        for (int i=1; i<=5; i++)
        {
            EmployeePojo p = new EmployeePojo();
            p.setId(i);
            p.setName("user" + i);
            p.setAge(20+i);

            api.insert(p);
        }

        api.printAll();
    }
}
