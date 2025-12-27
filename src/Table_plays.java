import java.io.*;
import java.util.*;

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