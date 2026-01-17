import java.io.*;
import java.util.*;

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

    public void newAdvancedUser(String name, String role) throws ExistingUserException {
        if (duplicateName(name)) {
            throw new ExistingUserException("User had already created");
        }
        users.add(new AdvancedUser(name, users.size(), role));
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