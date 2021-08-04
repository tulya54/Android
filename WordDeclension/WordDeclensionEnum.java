public enum WordDeclensionEnum {
    MONTH(new String[]{"месяц", "месяца", "месяцев"}),
    DAY(new String[]{"день", "дня", "дней"}),;

/**
 * Склонения в зависимо от переданного количества единиц сущности.
 * Минимальный размер: 3 склонения;
 * index
 * 0 - 1 единица, 101 единица
 * 1 - 2 единицы, 102 единицы
 * 2 - 5 единиц, 11 единиц, 26 единиц
 */
private String[] declensions;

public String[] getDeclensions() {
    return declensions;
}

    WordDeclensionEnum(String[] strings) {
    declensions = strings;
    }
}
