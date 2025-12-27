

import java.io.*;
import java.util.*;

class User {
    private int id;
    private String name;
    private int win;
    private int lose;
    private int allplays;

    public User() {
        this.id = 0;
        this.name = "player";
        this.win = 0;
        this.lose = 0;
        this.allplays = 0;
    }

    public User(String name, int id_b) {
        this.id = 0;
        this.name = name;
        this.win = 0;
        this.lose = 0;
        this.allplays = 0;
        if (id_b != 0) {
            this.id = id_b;
        }
    }

    public User(int id, String name, int win, int lose, int allp) {
        this.id = id;
        this.name = name;
        this.win = win;
        this.lose = lose;
        this.allplays = allp;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getWin() { return win; }
    public int getLose() { return lose; }
    public int getAllplays() { return allplays; }

    public void userWin() {
        win++;
        allplays++;
    }

    public void userLose() {
        lose++;
        allplays++;
    }

    public void userDeadHeat() {
        allplays++;
    }
}

class Play {
    private int id;
    private String userWin;
    private String userLose;

    public Play(int id, String userWin, String userLose) {
        this.id = id;
        this.userWin = userWin;
        this.userLose = userLose;
    }

    public int getId() { return id; }
    public String getUserWin() { return userWin; }
    public String getUserLose() { return userLose; }
}

class FalseFormatLineException extends Exception {
    public FalseFormatLineException(String message) {
        super(message);
    }
}

class ExistingUserException extends Exception {
    public ExistingUserException(String message) {
        super(message);
    }
}

class NonExistentUserException extends Exception {
    public NonExistentUserException(String message) {
        super(message);
    }
}

class InvalidReadFileException extends Exception {
    public InvalidReadFileException(String message) {
        super(message);
    }
}

class Table_users {
    private String file_r;
    private List<User> users;

    public Table_users() {
        users = new ArrayList<>();
    }

    public Table_users(String f) {
        this.file_r = f;
        users = new ArrayList<>();
        try {
            readfile();
        }
        catch (InvalidReadFileException | FalseFormatLineException e) {
            System.out.println(e.getMessage());
        }
    }

    public void out() {
        if (users.isEmpty()) {
            System.out.println("Данных нет");
        } else {
            for (User u : users) {
                System.out.println(u.getId() + " " + u.getName() + " " +
                        u.getWin() + " " + u.getLose() + " " + u.getAllplays());
            }
        }
    }

    public void readfile() throws FalseFormatLineException, InvalidReadFileException {
        try (BufferedReader file = new BufferedReader(new FileReader(file_r))) {
            String line;
            while ((line = file.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length != 5) {
                    throw new FalseFormatLineException("Строка в файле неверного формата");
                }
                users.add(new User(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3]),
                        Integer.parseInt(parts[4])
                ));
            }
        } catch (IOException e) {
            throw new InvalidReadFileException("Ошибка чтения/открытия файла для пользователей");
            //System.out.println("Ошибка чтения/открытия файла для пользователей");
        }
    }

    public boolean duplicateName(String name) {
        for (User u : users) {
            if (name.equals(u.getName())) {
                return true;
            }
        }
        return false;
    }

    public void newUser(String name) throws ExistingUserException {
        if (duplicateName(name)) {
            throw new ExistingUserException("User had already created");
        }
        users.add(new User(name, countUsers()));
    }

    public int countUsers() {
        return users.size();
    }

    public void rewrightFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(file_r, false))) {
            for (User u : users) {
                out.println(u.getId() + " " + u.getName() + " " +
                        u.getWin() + " " + u.getLose() + " " + u.getAllplays());
            }
        } catch (IOException e) {
            System.out.println("Ошибка открытия файла на перезапись");
        }
    }

    public User getUser(int id) throws NonExistentUserException {
        try {
            return users.get(id);
        } catch (IndexOutOfBoundsException e) {
            throw new NonExistentUserException("Запрашивается несуществующий пользователь");
        }
    }

    public User getUserByName(String name) throws NonExistentUserException {
        try {
            for (User u : users){
                if (name.equals(u.getName())) {
                    return u;
                }
            }
            throw new NonExistentUserException("Запрашивается несуществующий пользователь");
        } catch (IndexOutOfBoundsException e) {
            throw new NonExistentUserException("Запрашивается несуществующий пользователь");
        }
    }
}

class Table_plays {
    private String file_r;
    private List<Play> plays;

    public Table_plays() {
        plays = new ArrayList<>();
    }

    public Table_plays(String f) {
        this.file_r = f;
        plays = new ArrayList<>();
        try {
            readfile();
        } catch (FalseFormatLineException e) {
            System.out.println(e.getMessage());
        }
    }

    public void outPlays() {
        if (plays.isEmpty()) {
            System.out.println("Данных нет");
        } else {
            for (Play p : plays) {
                System.out.println(p.getId() + " " + p.getUserWin() + " " + p.getUserLose());
            }
        }
    }

    public void readfile() throws FalseFormatLineException {
        try (BufferedReader file = new BufferedReader(new FileReader(file_r))) {
            String line;
            while ((line = file.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length != 3) {
                    throw new FalseFormatLineException("Строка в файле неверного формата");
                }
                plays.add(new Play(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        parts[2]
                ));
            }
        } catch (IOException | NumberFormatException e) {
            //throw new FalseFormatLineException("Ошибка чтения/открытия файла игр");
            System.out.println("Ошибка чтения/открытия файла для пользователей");
        }
    }

    public void rewrightFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(file_r, false))) {
            for (Play p : plays) {
                out.println(p.getId() + " " + p.getUserWin() + " " + p.getUserLose());
            }
        } catch (IOException e) {
            System.out.println("ошибка открытия файла");
        }
    }

    public int countPlays() {
        return plays.size();
    }

    public void newPlay(String uWin, String uLose, Table_users users) throws NonExistentUserException {
        int id = countPlays();
        plays.add(new Play(id, uWin, uLose));

        User user1=users.getUserByName(uWin);
        user1.userWin();
        User user2=users.getUserByName(uLose);
        user2.userLose();
    }
}


/*import java.io.*;
        import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;*/

/*public class GameSystemTest {
    private static final String USERS_FILE = "test_users.txt";
    private static final String PLAYS_FILE = "test_plays.txt";

    public static void main(String[] args) {
        System.out.println("=== Game System Test Suite ===\n");

        try {
            cleanupTestFiles();

            testUserConstructors();
            testUserGameMethods();
            testPlayClass();
            testTableUsersBasic();
            testTableUsersNewUser();
            testTableUsersFileOperations();
            testTableUsersExceptions();
            testTablePlaysBasic();
            testTablePlaysFileOperations();
            testFullIntegration();

            System.out.println("\n✅ ALL TESTS PASSED!");
        } catch (AssertionError e) {
            System.err.println("\n❌ TEST FAILED: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("\n❌ EXCEPTION: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cleanupTestFiles();
        }
    }

    private static void assertEquals(Object expected, Object actual, String message) {
        if (!expected.equals(actual)) {
            throw new AssertionError(message +
                    "\nExpected: " + expected +
                    "\nActual: " + actual);
        }
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private static void assertFalse(boolean condition, String message) {
        if (condition) {
            throw new AssertionError(message);
        }
    }

    private static void cleanupTestFiles() {
        try {
            Files.deleteIfExists(Paths.get(USERS_FILE));
            Files.deleteIfExists(Paths.get(PLAYS_FILE));
        } catch (IOException e) {
            // Ignore cleanup errors
        }
    }

    private static void testUserConstructors() {
        System.out.println("1. Testing User constructors...");

        // Test default constructor
        User defaultUser = new User();
        assertEquals(0, defaultUser.getId(), "Default User ID");
        assertEquals("player", defaultUser.getName(), "Default User name");
        assertEquals(0, defaultUser.getWin(), "Default User wins");
        assertEquals(0, defaultUser.getLose(), "Default User loses");
        assertEquals(0, defaultUser.getAllplays(), "Default User allplays");

        // Test constructor with name and id
        User namedUser = new User("Alice", 2);
        assertEquals(3, namedUser.getId(), "Named User ID (id_b + 1)");
        assertEquals("Alice", namedUser.getName(), "Named User name");

        // Test full constructor
        User fullUser = new User(1, "Bob", 5, 3, 8);
        assertEquals(1, fullUser.getId(), "Full User ID");
        assertEquals("Bob", fullUser.getName(), "Full User name");
        assertEquals(5, fullUser.getWin(), "Full User wins");
        assertEquals(3, fullUser.getLose(), "Full User loses");
        assertEquals(8, fullUser.getAllplays(), "Full User allplays");

        System.out.println("   ✅ User constructors OK\n");
    }

    private static void testUserGameMethods() {
        System.out.println("2. Testing User game methods...");

        User user = new User("TestUser", 0);
        user.userWin();
        assertEquals(1, user.getWin(), "userWin() increments win");
        assertEquals(1, user.getAllplays(), "userWin() increments allplays");

        user.userLose();
        assertEquals(1, user.getWin(), "userLose() doesn't change win");
        assertEquals(1, user.getLose(), "userLose() increments lose");
        assertEquals(2, user.getAllplays(), "userLose() increments allplays");

        user.userDeadHeat();
        assertEquals(1, user.getWin(), "userDeadHeat() doesn't change win");
        assertEquals(1, user.getLose(), "userDeadHeat() doesn't change lose");
        assertEquals(3, user.getAllplays(), "userDeadHeat() increments allplays");

        System.out.println("   ✅ User game methods OK\n");
    }

    private static void testPlayClass() {
        System.out.println("3. Testing Play class...");

        Play play = new Play(1, "Alice", "Bob");
        assertEquals(1, play.getId(), "Play ID");
        assertEquals("Alice", play.getUserWin(), "Play userWin");
        assertEquals("Bob", play.getUserLose(), "Play userLose");

        System.out.println("   ✅ Play class OK\n");
    }

    private static void testTableUsersBasic() {
        System.out.println("4. Testing Table_users basic operations...");

        Table_users table = new Table_users();
        assertEquals(0, table.countUsers(), "Empty table count");

        System.out.println("   ✅ Table_users basic OK\n");
    }

    private static void testTableUsersNewUser() {
        System.out.println("5. Testing Table_users newUser...");

        Table_users table = new Table_users();

        try {
            table.newUser("Alice");
            assertEquals(1, table.countUsers(), "First user added");
            assertEquals("Alice", table.getUser(0).getName(), "First user name");
            assertEquals(1, table.getUser(0).getId(), "First user ID");

            table.newUser("Bob");
            assertEquals(2, table.countUsers(), "Second user added");
            assertEquals(2, table.getUser(1).getId(), "Second user ID");

            System.out.println("   ✅ newUser success OK");
        } catch (ExistingUserException e) {
            throw new RuntimeException("Unexpected exception", e);
        }

        // Test duplicate
        try {
            table.newUser("Alice");
            throw new AssertionError("Should throw ExistingUserException");
        } catch (ExistingUserException e) {
            assertEquals("User had already created", e.getMessage(), "Duplicate name exception message");
            System.out.println("   ✅ Duplicate name exception OK");
        } catch (Exception e) {
            throw new AssertionError("Wrong exception type", e);
        }

        System.out.println("   ✅ Table_users newUser OK\n");
    }

    private static void testTableUsersFileOperations() throws IOException {
        System.out.println("6. Testing Table_users file operations...");

        // Create test file
        try (PrintWriter writer = new PrintWriter(USERS_FILE)) {
            writer.println("1 Alice 5 3 8");
            writer.println("2 Bob 2 4 6");
        }

        Table_users table = new Table_users(USERS_FILE);
        assertEquals(2, table.countUsers(), "File loaded users count");

        User firstUser = table.getUser(0);
        assertEquals(1, firstUser.getId(), "First user from file ID");
        assertEquals("Alice", firstUser.getName(), "First user from file name");
        assertEquals(5, firstUser.getWin(), "First user from file wins");

        // Test rewrightFile
        table.rewrightFile();
        String content = new String(Files.readAllBytes(Paths.get(USERS_FILE)));
        assertTrue(content.contains("1 Alice 5 3 8"), "File rewrite content");

        System.out.println("   ✅ File operations OK\n");
    }

    private static void testTableUsersExceptions() {
        System.out.println("7. Testing Table_users exceptions...");

        Table_users table = new Table_users();
        try {
            table.newUser("Test");
        } catch (ExistingUserException e) {
            throw new RuntimeException("Unexpected exception", e);
        }

        try {
            table.getUser(1);
            throw new AssertionError("Should throw NonExistentUserException");
        } catch (NonExistentUserException e) {
            assertEquals("Запрашивается несуществующий пользователь", e.getMessage(), "Non-existent user exception");
            System.out.println("   ✅ NonExistentUserException OK");
        } catch (Exception e) {
            throw new AssertionError("Wrong exception type", e);
        }

        System.out.println("   ✅ Exceptions OK\n");
    }

    private static void testTablePlaysBasic() {
        System.out.println("8. Testing Table_plays basic operations...");

        Table_plays table = new Table_plays();
        assertEquals(0, table.countPlays(), "Empty plays count");

        table.newPlay("Alice", "Bob");
        assertEquals(1, table.countPlays(), "One play added");

        table.newPlay("Bob", "Charlie");
        assertEquals(2, table.countPlays(), "Two plays added");

        System.out.println("   ✅ Table_plays basic OK\n");
    }

    private static void testTablePlaysFileOperations() throws IOException {
        System.out.println("9. Testing Table_plays file operations...");

        // Create test file
        try (PrintWriter writer = new PrintWriter(PLAYS_FILE)) {
            writer.println("0 Alice Bob");
            writer.println("1 Bob Charlie");
        }

        Table_plays table = new Table_plays(PLAYS_FILE);
        assertEquals(2, table.countPlays(), "Plays file loaded count");

        // Test rewrightFile
        table.rewrightFile();
        String content = new String(Files.readAllBytes(Paths.get(PLAYS_FILE)));
        assertTrue(content.contains("0 Alice Bob"), "Plays file rewrite content");

        System.out.println("   ✅ Table_plays file OK\n");
    }

    private static void testFullIntegration() throws Exception {
        System.out.println("10. Testing full integration...");

        Table_users usersTable = new Table_users();
        Table_plays playsTable = new Table_plays();

        // Add users
        usersTable.newUser("Alice");
        usersTable.newUser("Bob");

        // Add plays
        playsTable.newPlay("Alice", "Bob");
        playsTable.newPlay("Bob", "Alice");

        // Update user stats
        User alice = usersTable.getUser(0);
        User bob = usersTable.getUser(1);
        alice.userWin();
        bob.userLose();

        // Save to files
        usersTable.rewrightFile();
        playsTable.rewrightFile();

        // Verify files created
        assertTrue(Files.exists(Paths.get(USERS_FILE)), "Users file created");
        assertTrue(Files.exists(Paths.get(PLAYS_FILE)), "Plays file created");

        System.out.println("   ✅ Full integration OK\n");
    }
}
*/


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    /*IO.println(String.format("Hello and welcome!"));

    for (int i = 1; i <= 5; i++) {
        //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
        // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
        IO.println("i = " + i);
    }*/

    try {
        Table_users users = new Table_users("users.txt");
        Table_plays plays = new Table_plays("plays.txt");

        System.out.println("=== СТАРТ ИГРЫ ===");

        // Создаем игроков
        users.newUser("Alice");
        users.newUser("Bob");

        System.out.println("\nИгроки:");
        users.out();

        // Создаем игры (статистика обновляется автоматически!)
        plays.newPlay("Alice", "Bob", users);
        plays.newPlay("Bob", "Alice", users);
        plays.newPlay("Alice", "Bob", users);

        System.out.println("\nФинальная статистика:");
        users.out();
        System.out.println("\nИстория игр:");
        plays.outPlays();



    } catch (Exception e) {
        System.out.println("❌ Ошибка: " + e.getMessage());
    }

    /*try {
        // Работа с пользователями
        Table_users users = new Table_users("users.txt");

        System.out.println("\nСодержмое файла users.txt:");
        users.out();

        users.newUser("Sara");

        System.out.println("\nПосле добавления пользователей:");
        users.out();

        // Работа с играми
        Table_plays plays = new Table_plays("plays.txt");
        plays.outPlays();

        // Добавляем новую игру
        plays.newPlay("Player1", "Player");


        System.out.println("\nПосле добавления игры:");
        plays.outPlays();

        //users.rewrightFile();
        //plays.rewrightFile();

    } catch (Exception e) {
        System.out.println("Ошибка: " + e.getMessage());
    }*/

}
