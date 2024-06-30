package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        //        Util.getConnection();
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Petr1", "LastPetr1", ( byte ) 8);
        userService.saveUser("Petr2", "LastPetr2", ( byte ) 16);
        userService.saveUser("Petr3", "LastPetr3", ( byte ) 32);
        userService.saveUser("Petr4", "LastPetr4", ( byte ) 64);

        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}