import dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Alex", "Kotov", (byte) 25);
        userDao.saveUser("Anna", "Gromova", (byte) 41);
        userDao.saveUser("Petr", "Popovich", (byte) 20);
        userDao.saveUser("Elena", "Titova", (byte) 30);

        userDao.getAllUsers();

//        userDao.cleanUsersTable();
//
//        userDao.dropUsersTable();
    }
}
