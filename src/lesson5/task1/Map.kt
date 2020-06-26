@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
    shoppingList: List<String>,
    costs: Map<String, Double>
): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
    phoneBook: MutableMap<String, String>,
    countryCode: String
) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
    text: List<String>,
    vararg fillerWords: String
): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}


/**
 * Простая
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> {
    var ans = mutableMapOf<Int, List<String>>()
    var names = mutableListOf<String>()
    var cnt = mutableSetOf<Int>()
    for (grade in grades.values) {
        cnt.add(grade)
    }
    for (i in cnt) {
        for ((name, grade) in grades) {
            if (grade == i) {
                names.add(name)
            }
        }
        if (names.isNotEmpty()) {
            ans[i] = names.toList()
        }
        names.clear()
    }
    return ans
}

/**
 * Простая
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean {
    for (i in a.keys) {
        if (!b.containsKey(i))
            return false
    }
    for (i in a.values) {
        if (!b.containsValue(i))
            return false
    }
    return true
}

/**
 * Простая
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>): Unit {
    for (i in b.keys) {
        if (a.containsKey(i) && a[i] == b[i])
            a.remove(i)
    }
}

/**
 * Простая
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках.
 * В выходном списке не должно быть повторяюихся элементов,
 * т. е. whoAreInBoth(listOf("Марат", "Семён, "Марат"), listOf("Марат", "Марат")) == listOf("Марат")
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> {
    var inBoth = mutableListOf<String>()
    for (i in b) {
        if (a.contains(i) && !inBoth.contains(i))
            inBoth.add(i)
    }
    return inBoth
}

/**
 * Средняя
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {
    var mapBoth = mutableMapOf<String, String>()
    mapBoth = mapA .toMutableMap()
    var str: String
    for (i in mapB.keys) {
        if (mapA.containsKey(i) && mapA[i] != mapB[i]) {
            str = mapB[i].toString()
            mapBoth[i] += ", $str"
        }
        if (!mapA.containsKey(i)) {
            str = mapB[i].toString()
            mapBoth[i] = str
        }
    }
    return mapBoth
}

/**
 * Средняя
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    var allPrice = mutableMapOf<String, MutableList<Double>>()
    var count = mutableMapOf<String, Double>()
    for ((item, price) in stockPrices) {
        if (allPrice[item] == null) {
            allPrice[item] = mutableListOf<Double>()
        }
        allPrice[item]!!.add(price)
    }
    for ((item, prices) in allPrice) {
        count[item] = prices.average()
    }
    return count
}

/**
 * Средняя
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
    var minPrice = 10000000.0
    var ans = ""
    for ((i,j) in stuff) {
        if (j.first == kind && j.second < minPrice) {
            minPrice = j.second
            ans = i
        }
    }
    if (ans == "")
        return null
    return ans
}

/**
 * Средняя
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean {
    var word_ = word.toLowerCase().toSet()
    var chars_ = chars.joinToString(separator = "").toLowerCase().toSet()
    for (letter in word_) {
        if (!chars_.contains(letter)) {
            return false
        }
    }
    return true
}

/**
 * Средняя
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int> {
    var chars = mutableMapOf<String, Int>()
    var letter: String
    var cnt = 0
    for (i in list) {
        letter = i
        for (str in list) {
            if (str == i) {
                cnt++
            }
        }
        if (cnt > 1) {
            chars[letter] = cnt
        }
        cnt = 0
    }
    return chars
}

/**
 * Средняя
 *
 * Для заданного списка слов определить, содержит ли он анаграммы
 * (два слова являются анаграммами, если одно можно составить из второго)
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean {
    for (i in 0..words.count() - 1) {
        for (j in (i + 1)..words.count() - 1) {
            if (words[i].toList().sorted() == words[j].toList().sorted()) {
                return true
            }
        }
    }
    return false
}

/**
 * Сложная
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta")
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat")
 *        )
 */
fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
    var ans = mutableMapOf<String, Set<String>>()
    for ((name, friendName) in friends) {
        ans[name] = friendName.toMutableSet()
    }
    while (true) {
        var tmp = ans.toMap()
        for ((name, friendName) in tmp) {
            for (friend in friendName) {
                if (ans[friend] == null) {
                    ans[friend] = setOf<String>()
                }
                val friendsSet = tmp[friend]
                if (friendsSet != null) {
                    friendsSet!!.forEach {
                        if (it != name && ans[name] != null) {
                            var allFriends = ans[name]!!.toMutableSet()
                            ans.remove(name)
                            allFriends.add(it)
                            ans[name] = allFriends
                        }
                    }
                }
            }
        }
        if (tmp == ans) {
            break
        }
    }
    return ans
}

/**
 * Сложная
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
    for (i in list.indices) {
        for (j in list.indices) {
            if (i != j) {
                if (list[i] + list[j] == number)
                    return Pair<Int, Int>(i, j)
            }
        }
    }
    return Pair<Int, Int>(-1, -1)
}

/**
 * Очень сложная
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Перед решением этой задачи лучше прочитать статью Википедии "Динамическое программирование".
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */
fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> {
    var ans = mutableMapOf<Int, Set<String>>()
    for (i in 0..capacity) {
        ans[i] = mutableSetOf<String>()
    }
    val mas = Array<Int>((capacity + 1), { 0 })
    for (i in 0..mas.count() - 1) {
        var lastIt = ""
        var lastVal = Pair(0, 0)
        for ((name, value) in treasures) {
            if (i - value.first >= 0) {
                var prevVal = mas[i - value.first]
                if ((mas[i] <= value.second + prevVal) &&
                    (mas[i - 1] <= value.second + prevVal) &&
                    (!ans[i - value.first]!!.contains(name))
                ) {
                    mas[i] = value.second + prevVal
                    lastIt = name
                    lastVal = value
                }
            }
        }
        if (lastIt != "") {
            var set = ans[i]!!.toMutableSet().union(ans[i - lastVal.first]!!.toMutableSet())
            set = set.toMutableSet()
            ans.remove(i)
            set.add(lastIt)
            ans[i] = set
        } else {
            if (ans[i - 1] != null) {
                mas[i] = mas[i - 1]
                ans[i] = ans[i - 1]!!.toMutableSet()
            }
        }
    }
    return ans[capacity]!!
}
