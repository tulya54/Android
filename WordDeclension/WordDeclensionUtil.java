public class WordDeclensionUtil {
    /**
     * Получить слово в склонении в зависимости от переданного числа, например, для WordDeclensionEnum.MONTH
     * 11 месяцEВ
     * 21 месяц
     */
    public static String getWordInDeclension(WordDeclensionEnum wordType, int n) {
        // смотрим две последние цифры
        int result = n % 100;
        if (result >=10 && result <= 20) {
            // если окончание 11 - 20
            return wordType.getDeclensions()[2];
        }

        // смотрим одну последнюю цифру
        result = n % 10;
        if (result == 0 || result > 4) {
            return wordType.getDeclensions()[2];
        }
        if (result > 1) {
            return wordType.getDeclensions()[1];
        } if (result == 1) {
            return wordType.getDeclensions()[0];
        }
        return null;
    }
}
