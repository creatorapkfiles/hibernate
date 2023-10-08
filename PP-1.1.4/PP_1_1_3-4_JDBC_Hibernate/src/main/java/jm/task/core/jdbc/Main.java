package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        User[] users = {
                new User("Alex", "Ivanov", (byte) 52),
                new User("Ivan", "Alekseev", (byte) 42),
                new User("Jon", "Washington", (byte) 23),
                new User("John", "Green", (byte) 13)
        };
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

        System.out.println(userDaoHibernate.getAllUsers());

//        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//        userDaoJDBC.createUsersTable();
        for (int i = 0; i < users.length; i++) {
            userDaoHibernate.saveUser(users[i].getName(), users[i].getLastName(), users[i].getAge());
            System.out.println("User с именем – " + users[i].getName() + " добавлен в базу данных");
        }
        //userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
        userDaoHibernate.createUsersTable();
        for (int i = 0; i < users.length; i++) {
            userDaoHibernate.saveUser(users[i].getName(), users[i].getLastName(), users[i].getAge());
            System.out.println("User с именем – " + users[i].getName() + " добавлен в базу данных");
        }
        userDaoHibernate.removeUserById(4);
        System.out.println(userDaoHibernate.getAllUsers());
        userDaoHibernate.cleanUsersTable();
        System.out.println(userDaoHibernate.getAllUsers());
//        System.out.println(userDaoJDBC.getAllUsers());
//        userDaoJDBC.cleanUsersTable();
//        userDaoJDBC.dropUsersTable();
    }
}
