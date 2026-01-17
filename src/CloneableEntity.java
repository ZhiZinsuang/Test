// интерфейс для клонирования
interface CloneableEntity {
    Entity shallowClone();
    Entity deepClone();
}