import kotlin.random.Random

fun main()
{
    //функция для безопасного ввода количества философов
    fun getNumberOfPhilosophers(): Int {

        while (true)
        {
            println("Введите количество философов: ")
            val input = readLine()
            val number = input?.toIntOrNull()
            if (number != null && number > 0)
            {
                return number
            } else
            {
                println("Введите корректное положительное число.")
            }
        }
    }

    //получаем количество философов
    val numberOfPhilosophers = getNumberOfPhilosophers()

    //ввод имен философов
    val philosophers = mutableListOf<String>()
    repeat(numberOfPhilosophers)
    {
        index -> println("Введите имя философа ${index + 1}: ")
        //удаляет лишние пробелы в начале и в конце строки
        //если пользователь ничего не ввел, ничего не происходит
        val inputName = readLine()?.trim()

        val name =
            if (inputName.isNullOrEmpty()) "Философ_$index"//если ввод пользователя равно 0 или это пустая строка
        else inputName

        philosophers.add(name)
    }


    // Инициализация палочек
    val chopsticks = MutableList(numberOfPhilosophers) { true } //true - палочка свободна
    val states = MutableList(numberOfPhilosophers) { "размышляет" } //состояние философов
    val results = MutableList(numberOfPhilosophers) { "" } //описание действий философов

    //перемешиваем индексы философов для случайного порядка
    val shuffledIndexes = (philosophers.indices).shuffled()

    //попытка философов взять палочки
    for (index in shuffledIndexes)
    {
        val left = index
        val right = (index + 1) % numberOfPhilosophers//если философ находится в конце стола, следующая палочка возвращается к началу

        if (chopsticks[left] && chopsticks[right])
        {
            chopsticks[left] = false//палочка слева занята
            chopsticks[right] = false//палочка справа занята
            states[index] = "обедает"//обновляет состояние философа
            results[index] = "${philosophers[index]} обедает, взяв палочки слева и справа от себя."
        } else
        {
            results[index] = "${philosophers[index]} размышляет."
        }
    }

    //вывод результатов в случайном порядке
    println("\nРезультаты:")
    for (index in shuffledIndexes)
    {
        println(results[index])
    }
}
