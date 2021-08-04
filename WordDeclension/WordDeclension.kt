class WordDeclension {
    /**
     * Получить слово в склонении в зависимости от переданного числа, например, для WordDeclensionEnum.MONTH
     * 11 месяцEВ
     * 21 месяц
     */
    companion object {
        fun getWordInDeclension(wordType: WordEnum, n: Int): String? {
            // смотрим две последние цифры
            var result = n % 100
            if (result >= 10 && result <= 20) {
                // если окончание 11 - 20
                return wordType.declensions[2]
            }
            // смотрим одну последнюю цифру
            result = n % 10
            if (result == 0 || result > 4) {
                return wordType.declensions[2]
            }
            if (result > 1) {
                return wordType.declensions[1]
            }
            return if (result == 1) {
                wordType.declensions[0]
            } else null
        }
    }
}
