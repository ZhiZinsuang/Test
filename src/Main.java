
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("=== ДЕМОНСТРАЦИЯ ООП В JAVA ===");

            // 1. Демонстрация наследования и полиморфизма
            System.out.println("\n1. НАСЛЕДОВАНИЕ И ПОЛИМОРФИЗМ:");

            Entity user1 = new User("Alice", 1);
            Entity user2 = new AdvancedUser("Bob", 2, "Admin");
            Entity user3 = new GuestUser(3, "Guest");

            // Вызов виртуальных методов через указатели базового класса
            Entity[] entities = {user1, user2, user3};
            for (Entity entity : entities) {
                System.out.println(entity.getType()); // Полиморфизм
                System.out.println(entity.getInfo());
                System.out.println();
            }

            // Вызов через невиртуальную (static) функцию
            Entity.printEntityType(user1);
            Entity.printEntityType(user2);

            // 3. Демонстрация клонирования
            System.out.println("\n2. КЛОНИРОВАНИЕ:");

            AdvancedUser original = new AdvancedUser("Charlie", 4, "Moderator");
            original.userWin();
            original.userWin();
            original.userLose();

            System.out.println("Original: " + original.getStatistics());

            // Поверхностное копирование (в Java обычно через Object.clone())
            AdvancedUser shallowCopy = original; // Это ссылка, а не копирование
            System.out.println("Shallow copy (reference): " + (original == shallowCopy));

            // Глубокое копирование
            Entity deepCopy = original.deepClone();
            System.out.println("Deep copy object: " + deepCopy.getInfo());
            System.out.println("Are they same object? " + (original == deepCopy));

            // 4. Демонстрация интерфейсов
            System.out.println("\n3. ИНТЕРФЕЙСЫ:");

            Statisticable statUser = new User("David", 5);
            ((User)statUser).userWin();
            ((User)statUser).userWin();
            System.out.println("Statistics: " + statUser.getStatistics());

            Auditable auditableUser = new AdvancedUser("Eve", 6, "VIP");
            System.out.println("Created: " + auditableUser.getCreationDate());

            // 5. Демонстрация множественного наследования через интерфейсы
            System.out.println("\n4. МНОЖЕСТВЕННОЕ НАСЛЕДОВАНИЕ:");

            AdvancedUser multiInheritUser = new AdvancedUser("Frank", 7, "Admin");
            // Имеет методы от Entity, User и всех интерфейсов
            System.out.println("From Entity: " + multiInheritUser.getType());
            System.out.println("From User: " + multiInheritUser.getWin());
            System.out.println("From Statisticable: " + multiInheritUser.getStatistics());
            System.out.println("From Auditable: " + multiInheritUser.getCreationDate());

            // 6. Демонстрация разницы виртуальных и невиртуальных методов
            System.out.println("\n5. ВИРТУАЛЬНЫЕ vs НЕВИРТУАЛЬНЫЕ МЕТОДЫ:");

            Entity entityRef = new AdvancedUser("Grace", 8, "Player");
            System.out.println("Виртуальный вызов getType(): " + entityRef.getType());

            // Если бы getType() был невиртуальным (static), 
            // всегда вызывалась бы версия из Entity

            // 7. Работа с Table_users и Table_plays (оригинальная логика)
            System.out.println("\n6. ОРИГИНАЛЬНАЯ ЛОГИКА ИГРЫ:");

            Table_users users = new Table_users("users.txt");
            Table_plays plays = new Table_plays("plays.txt");

            // Добавляем разных типов пользователей
            users.newUser("Player1");
            users.newAdvancedUser("Admin1", "Administrator");

            System.out.println("\nВсе пользователи:");
            users.out();

            // Создаем игры
            plays.newPlay("Player1", "Admin1", users);

            System.out.println("\nПосле игры:");
            users.out();

            System.out.println("\nВсего создано пользователей: " + User.getTotalUsersCreated());

        } catch (Exception e) {
            System.out.println("❌ Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}