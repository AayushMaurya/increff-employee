package com.increff.employee;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class EmployeeHibernateApi {
    private static Logger logger = Logger.getLogger(EmployeeJDBCApi.class.getName());
    private HibernateUtil hbu;
    public EmployeeHibernateApi() throws Exception
    {
        hbu = new HibernateUtil();
    }
    public void insert(EmployeePojo p) throws SQLException
    {
        logger.info("inserting employee");
        hbu.createSession();
        hbu.beginTransaction();
        Session s = hbu.getSession();
        s.save(p);

        hbu.commitTransaction();
        hbu.closeSession();
    }
    public void delete(int id) throws Exception
    {
        logger.info("deleting an employee");
        hbu.createSession();
        hbu.beginTransaction();
        Session s = hbu.getSession();
        EmployeePojo p = s.find(EmployeePojo.class, id);
        s.delete(p);

        hbu.commitTransaction();
        hbu.closeSession();
    }

    public void deleteAll() throws Exception
    {
        logger.info("deleting all employees");
        hbu.createSession();
        hbu.beginTransaction();
        Session s = hbu.getSession();

        Query q = s.createQuery("select o from EmployeePojo o");
        List<EmployeePojo> list = q.getResultList();

        for(EmployeePojo p: list)
        {
            s.delete(p);
        }

        hbu.commitTransaction();
        hbu.closeSession();
    }
    public EmployeePojo select(int id) throws SQLException
    {
        logger.info("selecting an employee");
        hbu.createSession();
        hbu.beginTransaction();
        Session s = hbu.getSession();
        EmployeePojo p = s.find(EmployeePojo.class, id);

        hbu.commitTransaction();
        hbu.closeSession();

        return p;
    }

    public List<EmployeePojo> selectAll() throws SQLException
    {
        logger.info("Selecting all employees");
        hbu.createSession();
        hbu.beginTransaction();
        Session s = hbu.getSession();
        Query q = s.createQuery("select o from EmployeePojo o");
        List<EmployeePojo> list = q.getResultList();

        hbu.commitTransaction();
        hbu.closeSession();

        return list;
    }

    public void printAll() throws Exception
    {
        logger.info("printing details ");
        List<EmployeePojo> list = selectAll();
        for(EmployeePojo p: list)
        {
            logger.warn("Employee Id: " + p.getId() + "Employee name : " + p.getName() + "Employee age : " + p.getAge());
        }
    }
}
