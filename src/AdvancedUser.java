import java.text.SimpleDateFormat;
import java.util.Date;

class AdvancedUser extends User implements Statisticable {
    private String role;
    private int permissionsLevel;
    private static final String DEFAULT_ROLE = "Player";

    // Конструктор с вызовом конструктора базового класса
    public AdvancedUser(String name, int id_b, String role) {
        super(name, id_b); // Вызов конструктора базового класса
        this.role = (role != null && !role.isEmpty()) ? role : DEFAULT_ROLE;
        this.permissionsLevel = calculatePermissionLevel();
    }

    // Конструктор копирования
    protected AdvancedUser(AdvancedUser other) {
        super(other);
        this.role = other.role;
        this.permissionsLevel = other.permissionsLevel;
    }

    // Перегрузка без вызова метода базового класса
    @Override
    public String getType() {
        // Не вызываем super.getType()
        return "Advanced User";
    }

    // Перегрузка с вызовом метода базового класса
    @Override
    public String getInfo() {
        // Теперь можем вызвать super.getInfo(), так как User реализовал этот метод
        return super.getInfo() + ", Role: " + role + ", Permissions: " + permissionsLevel;
    }

    // Перегрузка метода интерфейса
    @Override
    public String getStatistics() {
        String baseStats = super.getStatistics();
        return baseStats + ", Role: " + role + ", Level: " + permissionsLevel;
    }

    // Собственные методы
    public String getRole() { return role; }
    public int getPermissionsLevel() { return permissionsLevel; }

    public void promote() {
        permissionsLevel++;
        if (permissionsLevel > 5) permissionsLevel = 5;
    }

    public void demote() {
        permissionsLevel--;
        if (permissionsLevel < 0) permissionsLevel = 0;
    }

    public void changeRole(String newRole) {
        this.role = newRole;
        this.permissionsLevel = calculatePermissionLevel();
    }

    private int calculatePermissionLevel() {
        if (role == null) return 0;
        switch (role.toLowerCase()) {
            case "admin": return 5;
            case "moderator": return 4;
            case "vip": return 3;
            case "premium": return 2;
            case "player": return 1;
            default: return 0;
        }
    }

    // Реализация глубокого клонирования
    @Override
    public Entity deepClone() {
        return new AdvancedUser(this);
    }

    @Override
    public Entity shallowClone() {
        try {
            AdvancedUser clone = (AdvancedUser) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}