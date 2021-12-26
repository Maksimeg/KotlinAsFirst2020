@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import java.io.File

/**
 * Фабричный метод для создания комплексного числа из строки вида x+yi
 */
fun Complex(s: String): Complex = TODO()

/**
 * Класс "комплексное число".
 *
 * Общая сложность задания -- лёгкая, общая ценность в баллах -- 8.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */
class Complex(val re: Double, val im: Double) {

    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(TODO(), TODO())

    /**
     * Сложение.
     */
    operator fun plus(other: Complex): Complex = TODO()

    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus(): Complex = TODO()

    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex = TODO()

    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex = TODO()

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex = TODO()

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean = TODO()

    /**
     * Преобразование в строку
     */
    override fun toString(): String = TODO()
}

fun main() {
    println(game("input/game2.txt", false))
}

fun game(inputName: String, xOr0: Boolean): String? {
    val inputFile = File(inputName)
    val field: Array<Array<Char>> = Array(15, { Array(15, { '0' }) })
    var simvolInStr = 0
    var countStr = 0
    var kindOfSimbol: Char

    if (xOr0) {
        kindOfSimbol = 'x'
    } else {
        kindOfSimbol = '0'
    }

    for (line in inputFile.readLines()) {
        for (symbol in line) {
            field[countStr][simvolInStr] = symbol
            simvolInStr += 1
        }
        countStr += 1
        simvolInStr = 0
    }

    var provMassRightStr = Array(15, { 0 })
    var provMassLeftStr = Array(15, { 0 })
    var countOfXInStr = 0

    //проверка строк
    for (strOrStlb in 0..14) {
        for (i in 0..14) {
            for (j in i..13) {
                if (field[strOrStlb][j + 1] == kindOfSimbol) {
                    countOfXInStr += 1
                    if (j == 13) {
                        provMassRightStr[i] = countOfXInStr
                        countOfXInStr = 0
                        break
                    }
                } else {
                    provMassRightStr[i] = countOfXInStr
                    countOfXInStr = 0
                    break
                }
            }
        }
        for (i in 14 downTo 1) {
            for (j in i downTo 1) {
                if (field[strOrStlb][j - 1] == kindOfSimbol) {
                    countOfXInStr += 1
                    if (j == 1) {
                        provMassRightStr[i] = countOfXInStr
                        countOfXInStr = 0
                        break
                    }
                } else {
                    provMassLeftStr[i] = countOfXInStr
                    countOfXInStr = 0
                    break
                }
            }
        }
        for (j in 0..14) {
            if (field[strOrStlb][j] == '-' && provMassRightStr[j] + provMassLeftStr[j] > 3) {
                return ((strOrStlb + 1).toString() + " " + (j + 1).toString())
            }
        }

        //проверка столбиков
        countOfXInStr = 0
        for (i in 0..14) {
            for (j in i..13) {
                if (field[j + 1][strOrStlb] == kindOfSimbol) {
                    countOfXInStr += 1
                    if (j == 13) {
                        provMassRightStr[i] = countOfXInStr
                        countOfXInStr = 0
                        break
                    }
                } else {
                    provMassRightStr[i] = countOfXInStr
                    countOfXInStr = 0
                    break
                }
            }
        }
        for (i in 14 downTo 1) {
            for (j in i downTo 1) {
                if (field[j - 1][strOrStlb] == kindOfSimbol) {
                    countOfXInStr += 1
                    if (j == 1) {
                        provMassRightStr[i] = countOfXInStr
                        countOfXInStr = 0
                        break
                    }
                } else {
                    provMassLeftStr[i] = countOfXInStr
                    countOfXInStr = 0
                    break
                }
            }
        }
        for (j in 0..14) {
            if (field[j][strOrStlb] == '-' && provMassRightStr[j] + provMassLeftStr[j] > 3) {
                return ((j + 1).toString() + " " + (strOrStlb + 1).toString())
            }
        }
    }

//проверка диагоналей
    countOfXInStr = 0
    var str = 0
    for (k in 0..10) {
        for (i in 0..(14 - k)) {
            str = i
            for (j in i..(13 - k)) {
                if (field[str + 1][j + 1 + k] == kindOfSimbol) {
                    countOfXInStr += 1
                    if (j == 13 - k) {
                        provMassRightStr[i] = countOfXInStr
                        countOfXInStr = 0
                        break
                    }
                } else if (field[str + 1][j + 1 + k] != kindOfSimbol) {
                    provMassRightStr[i] = countOfXInStr
                    countOfXInStr = 0
                    break
                }
                str += 1
            }
        }
        for (i in (14 - k) downTo 1) {
            str = i
            for (j in (i + k) downTo 1) {
                if (field[str - 1][j - 1] == kindOfSimbol) {
                    countOfXInStr += 1
                    if (j == 1 + k) {
                        provMassLeftStr[i] = countOfXInStr
                        countOfXInStr = 0
                        break
                    }
                } else if (field[str - 1][j - 1] != kindOfSimbol) {
                    provMassLeftStr[i] = countOfXInStr
                    countOfXInStr = 0
                    break
                }
                str -= 1
            }
        }
        str = 0
        var element = 0

        while (element != 14 - k) {
            if (field[str][element + k] == '-' && provMassRightStr[str] + provMassLeftStr[str] > 3) {
                return ((str + 1).toString() + " " + (element + 1 + k).toString())
            }
            str += 1
            element += 1
        }

    }

    for (k in 1..10) {
        for (i in 0..(14 - k)) {
            str = i
            for (j in i..(13 - k)) {
                if (field[str + 1 + k][j + 1] == kindOfSimbol) {
                    countOfXInStr += 1
                    if (j == 13 - k) {
                        provMassRightStr[i] = countOfXInStr
                        countOfXInStr = 0
                        break
                    }
                } else if (field[str + 1 + k][j + 1] != kindOfSimbol) {
                    provMassRightStr[i] = countOfXInStr
                    countOfXInStr = 0
                    break
                }
                str += 1
            }
        }


        for (i in (14 - k) downTo 1) {
            str = i
            for (j in (i - k) downTo 1) {
                if (field[str - 1][j - 1] == kindOfSimbol) {
                    countOfXInStr += 1
                    if (str == 1 + k) {
                        provMassLeftStr[i - k] = countOfXInStr
                        countOfXInStr = 0
                        break
                    }
                } else if (field[str - 1][j - 1] != kindOfSimbol) {
                    provMassLeftStr[i - k] = countOfXInStr
                    countOfXInStr = 0
                    break
                }
                str -= 1
            }
        }
        str = 0
        var element = 0

        while (element != 14 - k) {
            if (field[str][element + k] == '-' && provMassRightStr[str] + provMassLeftStr[str] > 3) {
                return ((str + 1 + k).toString() + " " + (element + 1).toString())
            }
            str += 1
            element += 1
        }
    }

    var count = 0
    countOfXInStr = 0
    for (k in 0..10) {
        count = 0
        for (i in 14 downTo 1) {
            str = i
            for (j in count..13 - k) {
                if (field[str - 1][j + 1 + k] == kindOfSimbol) {
                    countOfXInStr += 1
                    if (j == 13 - k) {
                        provMassRightStr[count] = countOfXInStr
                        countOfXInStr = 0
                        break
                    }
                } else if (field[str - 1][j + 1 + k] != kindOfSimbol) {
                    provMassRightStr[count] = countOfXInStr
                    countOfXInStr = 0
                    break
                }
                str -= 1
            }
            count += 1
        }


        count = 14
        for (i in 0..13 - k) {
            str = i
            for (j in count downTo 1) {
                if (field[str + 1 + k][j - 1] == kindOfSimbol) {
                    countOfXInStr += 1
                    if (j == 1 + k) {
                        provMassLeftStr[count - k] = countOfXInStr
                        countOfXInStr = 0
                        break
                    }
                } else if (field[str + 1 + k][j - 1] != kindOfSimbol) {
                    provMassLeftStr[count - k] = countOfXInStr
                    countOfXInStr = 0
                    break
                }
                str += 1

            }
            count -= 1
        }
        count = 0
        str = 14
        while (count != 14 - k) {
            if (field[str][count + k] == '-' && provMassRightStr[count] + provMassLeftStr[count] > 3) {
                return ((str + 1).toString() + " " + (count + 1 + k).toString())
            }
            str -= 1
            count += 1
        }
    }


    for (k in 1..10) {
        count = 0
        for (i in 14 downTo 1) {
            str = i
            for (j in count..13 - k) {
                if (field[str - 1 - k][j + 1] == kindOfSimbol) {
                    countOfXInStr += 1
                    if (j == 13 - k) {
                        provMassRightStr[count] = countOfXInStr
                        countOfXInStr = 0
                        break
                    }
                } else if (field[str - 1 - k][j + 1] != kindOfSimbol) {
                    provMassRightStr[count] = countOfXInStr
                    countOfXInStr = 0
                    break
                }
                str -= 1
            }
            count += 1
        }

        count = 14
        for (i in 0..13 - k) {
            str = i
            for (j in count downTo 1) {
                if (field[str + 1][j - 1 - k] == kindOfSimbol) {
                    countOfXInStr += 1
                    if (j == 1 + k) {
                        provMassLeftStr[count - k] = countOfXInStr
                        countOfXInStr = 0
                        break
                    }
                } else if (field[str + 1][j - 1 - k] != kindOfSimbol) {
                    provMassLeftStr[count - k] = countOfXInStr
                    countOfXInStr = 0
                    break
                }
                str += 1

            }
            count -= 1
        }

        count = 0
        str = 13
        while (count != 14 - k) {
            if (field[str][count] == '-' && provMassRightStr[count] + provMassLeftStr[count] > 3) {
                return ((str + 2 - k).toString() + " " + (count + 1).toString())
            }
            str -= 1
            count += 1
        }
    }


    /*for (i in 0..14) {
        print(provMassRightStr[i])
    }
    println()*/

    /*for (i in 0..14) {
        for (j in 0..14) {
            print(field[i][j])
        }
        println()
    }*/

    return null
}
