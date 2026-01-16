import java.io.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

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

        System.out.println("\nВсего создано пользователей: " + User.getTotalUsersCreated());

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
