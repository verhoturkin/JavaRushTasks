package com.javarush.task.task33.task3310;

/*
Shortener (1)
        Давай напишем укорачиватель Shortener. Это будет некий аналог укорачивателя
        ссылок Google URL Shortener (https://goo.gl), но мы расширим его функциональность и
        сделаем консольным. Он будет сокращать не только ссылки, но и любые строки.
        Наш Shortener - это класс, который может для любой строки вернуть некий
        уникальный идентификатор и наоборот, по ранее полученному идентификатору
        вернуть строку.

        Два дополнительных требования к Shortener:
        - для двух одинаковых строк должен возвращаться один и тот же идентификатор;
        - он должен поддерживать столько строк, сколько значений может принимать long,
        именно этот тип будет использоваться для идентификатора.
        Первое требование очень сильно влияет на производительность, т.к. при получении
        идентификатора для новой строки мы должны проверить не обрабатывалась ли эта
        строка ранее, чтобы вернуть старый идентификатор.

        1.1. Объяви класс Shortener.
        1.2. Добавь методы заглушки в объявленный класс:
        1.2.1. Long getId(String string) - будет возвращать идентификатор id для заданной
        строки.
        1.2.2. String getString(Long id) - будет возвращать строку для заданного
        идентификатора или null, если передан неверный идентификатор.

        1.3. Создай класс Solution с пустым методом main.

        P.S. Все методы делай публичными, а поля приватными, если нет явных указаний касательно модификаторов доступа.


        Требования:
        1. В классе Shortener должен быть объявлен метод public Long getId(String string).
        2. В классе Shortener должен быть объявлен метод public String getString(Long id).
        3. В классе Solution должен быть объявлен метод public static void main(String[] args).

Shortener (2)
        Укорачиватель Shortener будет поддерживать разные стратегии хранения данных (строк и их идентификаторов). Все эти стратегии будут наследоваться от интерфейса StorageStrategy. Почитай подробнее про паттерн Стратегия на Вики.
        Наше хранилище будет оперировать двумя понятиями: ключ и значение. Ключом будет идентификатор строки, а значением сама строка.

        2.1. Добавь интерфейс StorageStrategy в пакет strategy.
        2.2. Добавь в интерфейс следующие методы:
        2.2.1. boolean containsKey(Long key) - должен вернуть true, если хранилище
        содержит переданный ключ.
        2.2.2. boolean containsValue(String value) - должен вернуть true, если хранилище
        содержит переданное значение.
        2.2.3. void put(Long key, String value) - добавить в хранилище новую пару ключ -
        значение.
        2.2.4. Long getKey(String value) - вернуть ключ для переданного значения.
        2.2.5. String getValue(Long key) - вернуть значение для переданного ключа.


        Требования:
        1. В интерфейсе StorageStrategy должен быть объявлен метод boolean containsKey(Long key).
        2. В интерфейсе StorageStrategy должен быть объявлен метод boolean containsValue(String value).
        3. В интерфейсе StorageStrategy должен быть объявлен метод void put(Long key, String value).
        4. В интерфейсе StorageStrategy должен быть объявлен метод Long getKey(String value).
        5. В интерфейсе StorageStrategy должен быть объявлен метод boolean String getValue(Long key).
        6. Интерфейс StorageStrategy должен быть создан в пакете strategy.

Shortener (3)
        Вернемся к классу Shortener:
        3.1. Добавь в него поле Long lastId. Проинициализируй его нулем. Это поле будет
        отвечать за последнее значение идентификатора, которое было использовано при добавлении новой строки в хранилище.
        3.2. Добавь поле StorageStrategy storageStrategy в котором будет храниться стратегия хранения данных.
        3.3. Добавь конструктор, который принимает StorageStrategy и инициализирует соответствующее поле класса.
        3.4. Реализуй метод getId, он должен:
        3.4.1. Проверить есть ли переданное значение в хранилище, если есть - вернуть его ключ.
        3.4.2. Если преданного значения нет в хранилище, то:
        3.4.2.1. Увеличить значение lastId на единицу;
        3.4.2.2. Добавить в хранилище новую пару ключ-значение (новое значение lastId и переданную строку);
        3.4.2.3. Вернуть новое значение lastId.
        3.5. Реализуй метод getString, он должен вернуть строку по заданному идентификатору (ключу).
        3.6. Предусмотреть возможность вызова методов getId и getString из разных потоков добавив соответствующий модификатор к заголовкам методов.


        Требования:
        1. В классе Shortener должно быть создано приватное поле Long lastId инициализированное нулем.
        2. В классе Shortener должно быть создано приватное поле storageStrategy типа StorageStrategy.
        3. Конструктор класса Shortener должен принимать один параметр типа StorageStrategy и инициализировать им поле storageStrategy.
        4. Метод getId должен быть реализован в соответствии с условием задачи.
        5. Метод getString должен быть реализован в соответствии с условием задачи.

Shortener (4)
        Нам потребуется несколько вспомогательных классов:
        4.1. Создай класс Helper.
        4.1.1. Добавь в него статический метод String generateRandomString(), который будет генерировать случайную строку. Воспользуйся для этого классами SecureRandom и BigInteger. Подсказка: гугли запрос "random string java". Строка может состоять из цифр и любой из 26 маленьких букв английского алфавита.
        4.1.2. Добавь в класс статический метод printMessage(String message). Он должен выводить переданный текст в консоль. Весь дальнейший вывод в программе должен быть реализован через этот метод!
        4.2. Создай класс ExceptionHandler.
        4.2.1. Добавь в него статический метод log(Exception e), который будет выводить краткое описание исключения.


        Требования:
        1. Метод generateRandomString класса Helper должен генерировать случайную строку используя способ описанный в условии задачи.
        2. Метод printMessage класса Helper должен выводить переданный текст на экран.
        3. Метод log класса ExceptionHandler должен выводить на экран краткое описание исключения.

Shortener (5)

        Давай напишем наше первое хранилище (стратегию хранилища). Внутри оно будет содержать обычный HashMap. Все стратегии будем хранить в пакете strategy.
        5.1. Создай класс HashMapStorageStrategy, реализующий интерфейс StorageStrategy.
        5.2. Добавь в класс поле HashMap<Long, String> data. В нем будут храниться наши данные.
        5.3. Реализуй в классе все необходимые методы. Реализации методов должны использовать поле data. Дополнительные поля не создавать.


        Требования:
        1. Класс HashMapStorageStrategy должен поддерживать интерфейс StorageStrategy.
        2. В классе HashMapStorageStrategy должно быть создано и инициализировано поле data типа HashMap.
        3. В классе HashMapStorageStrategy должен быть корректно реализован метод containsKey.
        4. В классе HashMapStorageStrategy должен быть корректно реализован метод containsValue.
        5. В классе HashMapStorageStrategy должен быть корректно реализован метод put.
        6. В классе HashMapStorageStrategy должен быть корректно реализован метод getValue.
        7. В классе HashMapStorageStrategy должен быть корректно реализован метод getKey.

Shortener (6)

        Первая стратегия готова, пришло время ее протестить. Для этого:
        6.1. Создай класс Solution, если ты не сделал это раньше.
        6.2. Добавь в класс Solution реализации вспомогательных статических методов:
        6.2.1. Set<Long> getIds(Shortener shortener, Set<String> strings). Этот метод должен для переданного множества строк возвращать множество идентификаторов. Идентификатор для каждой отдельной строки нужно получить, используя shortener.
        6.2.2. Set<String> getStrings(Shortener shortener, Set<Long> keys). Метод будет возвращать множество строк, которое соответствует переданному множеству идентификаторов.
        При реальном использовании Shortener, задача получить из множества строк множество идентификаторов и наоборот скорее всего не встретится, это нужно исключительно для тестирования.
        6.2.3. testStrategy(StorageStrategy strategy, long elementsNumber). Метод будет тестировать работу переданной стратегии на определенном количестве элементов elementsNumber. Реализация метода должна:
        6.2.3.1. Выводить имя класса стратегии. Имя не должно включать имя пакета.
        6.2.3.2. Генерировать тестовое множество строк, используя Helper и заданное количество элементов elementsNumber.
        6.2.3.3. Создавать объект типа Shortener, используя переданную стратегию.
        6.2.3.4. Замерять и выводить время необходимое для отработки метода getIds для заданной стратегии и заданного множества элементов. Время вывести в миллисекундах. При замере времени работы метода можно пренебречь переключением процессора на другие потоки, временем, которое тратится на сам вызов, возврат значений и вызов методов получения времени (даты). Замер времени произведи с использованием объектов типа Date.
        6.2.3.5. Замерять и выводить время необходимое для отработки метода getStrings для заданной стратегии и полученного в предыдущем пункте множества идентификаторов.
        6.2.3.6. Сравнивать одинаковое ли содержимое множества строк, которое было сгенерировано и множества, которое было возвращено методом getStrings. Если множества одинаковы, то выведи "Тест пройден.", иначе "Тест не пройден.".
        6.2.4. Добавь метод main(). Внутри метода протестируй стратегию HashMapStorageStrategy с помощью 10000 элементов.
        6.3. Проверь, что программа работает и тест пройден.

        Требования:

        1. Метод getIds должен для переданного множества строк возвращать множество идентификаторов.
        2. Метод getStrings должен для переданного множества идентификаторов возвращать множество строк.
        3. Метод testStrategy должен выводить на экран имя класса стратегии.
        4. Метод testStrategy должен генерировать тестовое множество строк используя метод generateRandomString класса Helper (количество элементов должно быть равно параметру elementsNumber).
        5. Метод testStrategy должен создавать объект типа Shortener используя переданную стратегию.
        6. Метод testStrategy должен замерять количество времени выполнения методов getIds и getStrings используя объекты типа Date.
        7. Метод testStrategy должен сравнивать количество сгенерированных строк и строк в множестве полученном в результате работы метода getStrings и выводить на экран результат прохождения теста.
        8. В методе main должен быть вызван метод testStrategy.

Shortener (7)
        Приступим к реализации второй стратегии OurHashMapStorageStrategy. Она не будет использовать готовый HashMap из стандартной библиотеки, а будет сама являться коллекцией.

        7.1. Разберись как работает стандартный HashMap, посмотри его исходники или погугли статьи на эту тему.
        7.2. Если ты честно выполнил предыдущий пункт, то ты знаешь для чего используется класс Node поддерживающий интерфейс Entry внутри HashMap. Создай свой аналог внутри пакета strategy. Это должен быть обычный, не вложенный, не generic класс. Сделай его публичным.
        В отличии от класса Node из HashMap, наш класс будет поддерживать только интерфейс Serializable и будет называться Entry.
        7.3. Добавь в Entry следующие поля: Long key, String value, Entry next, int hash. Как видишь, наша реализация будет поддерживать только тип Long для ключа и только String для значения. Область видимости полей оставь по умолчанию.
        7.4. Добавь и реализуй конструктор Entry(int hash, Long key, String value, Entry next).
        7.5. Добавь и реализуй методы: Long getKey(), String getValue(), int hashCode(), boolean equals() и String toString(). Реализовывать остальные методы оригинального Entry не нужно, мы пишем упрощенную версию.


Требования:
        1. В классе Entry должны быть созданы поля перечисленные в условии задачи.
        2. В классе Entry должен быть реализован конструктор с четырьмя параметрами (int, Long, String, Entry) инициализирующий соответствующие поля класса.
        3. Метод getKey должен возвращать значение поля key.
        4. Метод getValue должен возвращать значение поля value.
        5. Метод toString должен возвращать строку формата key + "=" + value.
        6. Методы hashCode и equals должны быть корректно реализованы используя для сравнения поля key и value.

Shortener (8)
        Добавь и реализуй класс OurHashMapStorageStrategy, используя класс Entry из предыдущей подзадачи. Класс OurHashMapStorageStrategy должен реализовывать интерфейс StorageStrategy.
        8.1. Добавь в класс следующие поля:
        8.1.1. static final int DEFAULT_INITIAL_CAPACITY = 16;
        8.1.2. static final float DEFAULT_LOAD_FACTOR = 0.75f;
        8.1.3. Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
        8.1.4. int size;
        8.1.5. int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
        8.1.6. float loadFactor = DEFAULT_LOAD_FACTOR;
        8.2. Реализуй в классе следующие вспомогательные методы:
        8.2.1. int hash(Long k)
        8.2.2. int indexFor(int hash, int length)
        8.2.3. Entry getEntry(Long key)
        8.2.4. void resize(int newCapacity)
        8.2.5. void transfer(Entry[] newTable)
        8.2.6. void addEntry(int hash, Long key, String value, int bucketIndex)
        8.2.7. void createEntry(int hash, Long key, String value, int bucketIndex)
        8.3. Добавь в класс публичные методы, которые требует интерфейс StorageStrategy.
        Какие-либо дополнительные поля класса не использовать. Методы, не описанные в задании, реализовывать не нужно. Если возникнут вопросы как реализовать какой-то метод или что он должен делать, то ты всегда можешь посмотреть, как работает похожий метод в HashMap.
        Можешь добавить в метод main класса Solution тестирование новой стратегии. Запусти и сравни время работы двух стратегий на одинаковом количестве элементов.


Требования:
        1. Класс OurHashMapStorageStrategy должен поддерживать интерфейс StorageStrategy.
        2. В классе OurHashMapStorageStrategy должны быть созданы все необходимые поля (согласно условию задачи).
        3. Методы интерфейса StorageStrategy должны быть реализованы в OurHashMapStorageStrategy таким образом, чтобы обеспечивать корректную работу Shortener созданного на его основе.
        4. В классе OurHashMapStorageStrategy должны присутствовать все вспомогательные методы перечисленные в условии задачи.

Shortener (9)
        Напишем еще одну стратегию, назовем ее FileStorageStrategy. Она будет очень похожа на стратегию OurHashMapStorageStrategy, но в качестве ведер (англ. buckets) будут файлы. Я знаю, ты знаешь о каких buckets идет речь, если нет - повтори внутреннее устройство HashMap.
        9.1. Создай класс FileBucket в пакете strategy.
        9.2. Добавь в класс поле Path path. Это будет путь к файлу.
        9.3. Добавь в класс конструктор без параметров, он должен:
        9.3.1. Инициализировать path временным файлом. Файл должен быть размещен в директории для временных файлов и иметь случайное имя.

        Подсказка: Files.createTempFile.

        9.3.2. Создавать новый файл, используя path. Если такой файл уже есть, то заменять его.
        9.3.3. Обеспечивать удаление файла при выходе из программы.

        Подсказка: deleteOnExit().

        9.4. Добавь в класс методы:
        9.4.1. long getFileSize(), он должен возвращать размер файла на который указывает path.
        9.4.2. void putEntry(Entry entry) - должен сериализовывать переданный entry в файл. Учти, каждый entry может содержать еще один entry.
        9.4.3. Entry getEntry() - должен забирать entry из файла. Если файл имеет нулевой размер, вернуть null.
        9.4.4. void remove() - удалять файл на который указывает path.
        Конструктор и методы не должны кидать исключения.


Требования:
        1. В классе FileBucket должно быть создано поле path типа Path.
        2. Конструктор без параметров класса FileBucket должен быть реализован в соответствии с условием задачи.
        3. Метод getFileSize должен возвращать размер файла на который указывает path.
        4. Метод putEntry должен сериализовывать полученный объект типа Entry в файл на который указывает path, чтобы получить OutputStream используй метод Files.newOutputStream.
        5. Метод getEntry должен десериализовывать объект типа Entry из файл на который указывает path, чтобы получить InputStream используй метод Files.newInputStream.
        6. Метод remove должен удалять файл на который указывает path с помощью метода Files.delete().

Shortener (10)
        Создай и реализуй класс FileStorageStrategy. Он должен:
        10.1. Реализовывать интерфейс StorageStrategy.
        10.2. Использовать FileBucket в качестве ведер (англ. bucket).

        Подсказка: класс должен содержать поле FileBucket[] table.

        10.3. Работать аналогично тому, как это делает OurHashMapStorageStrategy, но удваивать количество ведер не когда количество элементов size станет больше какого-то порога, а когда размер одного из ведер (файлов) стал больше bucketSizeLimit.
        10.3.1. Добавь в класс поле long bucketSizeLimit.
        10.3.2. Проинициализируй его значением по умолчанию, например, 10000 байт.
        10.3.3. Добавь сеттер и геттер для этого поля.
        10.4. При реализации метода resize(int newCapacity) проследи, чтобы уже не нужные файлы были удалены (вызови метод remove()).
        Проверь новую стратегию в методе main(). Учти, что стратегия FileStorageStrategy гораздо более медленная, чем остальные. Не используй большое количество элементов для теста, это может занять оооочень много времени.
        Запусти программу и сравни скорость работы всех 3х стратегий.

        P.S. Обрати внимание на наличие всех необходимых полей в классе FileStorageStrategy, по аналогии с OurHashMapStorageStrategy:
        static final int DEFAULT_INITIAL_CAPACITY
        static final long DEFAULT_BUCKET_SIZE_LIMIT
        FileBucket[] table
        int size
        private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT
        long maxBucketSize


Требования:
        1. Класс FileStorageStrategy должен поддерживать интерфейс StorageStrategy.
        2. В классе FileStorageStrategy должны быть созданы все необходимые поля (согласно условию задачи).
        3. Методы интерфейса StorageStrategy должны быть реализованы в FileStorageStrategy таким образом, чтобы обеспечивать корректную работу Shortener созданного на его основе.

Shortener (11)
        Как ты заметил, получение идентификатора для строки требует намного больше времени, чем получение строки по идентификатору. Это ожидаемо и следует из реализации HashMap. Давай напишем четвертую стратегию OurHashBiMapStorageStrategy, которая будет лишена этого недостатка.
        11.1. Создай класс OurHashBiMapStorageStrategy, реализующий интерфейс StorageStrategy.
        11.2. Добавь в него два поля HashMap<Long, String> k2v и HashMap<String, Long> v2k.
        Первое будет хранить соответствие ключа и значения, а второе наоборот: значения и ключа.
        11.3. Реализуй методы интерфейса StorageStrategy, обеспечив максимальную скорость.

        Подсказка: при добавлении новой пары ключ-значение необходимо добавлять ее сразу в два поля.

        Проверь новую стратегию в методе main(). Запусти программу и сравни скорость работы всех 4х стратегий. Убедись, что мы значительно увеличили скорость получения идентификатора. Но как ты понимаешь, у этого решения есть не только плюсы, но и минусы.
        Подумай в каких случаях имеет смысл использовать OurHashBiMapStorageStrategy, а в каких HashMapStorageStrategy.


Требования:
        1. Класс OurHashBiMapStorageStrategy должен поддерживать интерфейс StorageStrategy.
        2. В классе OurHashBiMapStorageStrategy должны быть созданы и инициализированы две HashMap (k2v и v2k).
        3. Метод containsKey должен проверять содержится ли полученный параметр в k2v.
        4. Метод containsValue должен проверять содержится ли полученный параметр в v2k.
        5. Метод put должен добавлять пару (key, value) в k2v и пару (value, key) в v2k.
        6. Метод getValue должен возвращать значение полученное из k2v.
        7. Метод getKey должен возвращать значение полученное из v2k.

Shortener (12)
        Задача, когда требуется создать Map, работающий в две стороны (по ключу получать значение, а по значению ключ) не такая уж и редкая. Такие коллекции уже реализованы в различных сторонних библиотеках коллекций. Одна из таких Guava от Google.
        12.1. Скачай и подключи библиотеку guava версии 19.0.
        12.2. Реализуй стратегию HashBiMapStorageStrategy. Она должна:
        12.2.1. Поддерживать интерфейс StorageStrategy.
        12.2.2. Внутри иметь только одно поле data типа HashBiMap.
        12.3. Проверь новую стратегию в методе main(). Запусти программу и сравни скорость работы пяти стратегий.


Требования:
        1. Класс HashBiMapStorageStrategy должен поддерживать интерфейс StorageStrategy.
        2. В классе HashBiMapStorageStrategy должно быть создано только одно поле data типа HashBiMap.
        3. Метод containsKey должен проверять содержится ли ключ в data.
        4. Метод containsValue должен проверять содержится ли значение в data.
        5. Метод put должен добавлять пару (key, value) в data.
        6. Метод getValue должен возвращать значение полученное из data.
        7. Метод getKey должен возвращать значение полученное из data.inverse().

Shortener (13)
        Рассмотрим еще одну реализацию BiMap, на этот раз из Apache Commons Collections.
        13.1. Скачай и подключи Apache Commons Collections 4.0.
        13.2. Реализуй стратегию DualHashBidiMapStorageStrategy. Она должна:
        13.2.1. Поддерживать интерфейс StorageStrategy.
        13.2.2. Внутри иметь только одно поле data с типом DualHashBidiMap.
        13.3. Проверь новую стратегию в методе main(). Запусти программу и сравни скорость работы шести стратегий.


Требования:
        1. Класс DualHashBidiMapStorageStrategy должен поддерживать интерфейс StorageStrategy.
        2. В классе DualHashBidiMapStorageStrategy должно быть создано только одно поле data типа DualHashBidiMap.
        3. Метод containsKey() должен проверять содержится ли ключ в data.
        4. Метод containsValue() должен проверять содержится ли значение в data.
        5. Метод put() должен добавлять пару (key, value) в data.
        6. Метод getValue() должен возвращать значение полученное из data.
        7. Метод getKey() должен возвращать ключ полученный из data.getKey().

Shortener (14)
        Мы много раз тестировали наши стратегии с помощью метода testStrategy() класса Solution. Пришло время написать настоящие юнит тесты с использованием junit.
        14.1. Прочитай что такое юнит тесты.
        14.2. Скачай и подключи библиотеку Junit 4.12. Разберись как ей пользоваться. Библиотека Junit зависит от библиотеки hamcrest-core. Подключи и ее. Используй версию 1.3.
        14.3. Добавь класс FunctionalTest в пакет tests. В этом классе мы проверим функциональность наших стратегий.
        14.4. Добавь в класс FunctionalTest метод testStorage(Shortener shortener). Он должен:
        14.4.1. Создавать три строки. Текст 1 и 3 строк должен быть одинаковым.
        14.4.2. Получать и сохранять идентификаторы для всех трех строк с помощью shortener.
        14.4.3. Проверять, что идентификатор для 2 строки не равен идентификатору для 1 и 3 строк.

        Подсказка: метод Assert.assertNotEquals.

        14.4.4. Проверять, что идентификаторы для 1 и 3 строк равны.

        Подсказка: метод Assert.assertEquals.

        14.4.5. Получать три строки по трем идентификаторам с помощью shortener.
        14.4.6. Проверять, что строки, полученные в предыдущем пункте, эквивалентны оригинальным.

        Подсказка: метод Assert.assertEquals.

        14.5. Добавь в класс FunctionalTest тесты:
        14.5.1. testHashMapStorageStrategy()
        14.5.2. testOurHashMapStorageStrategy()
        14.5.3. testFileStorageStrategy()
        14.5.4. testHashBiMapStorageStrategy()
        14.5.5. testDualHashBidiMapStorageStrategy()
        14.5.6. testOurHashBiMapStorageStrategy()
        Каждый тест должен иметь аннотацию @Test, создавать подходящую стратегию, создавать объект класса Shortener на базе этой стратегии и вызывать метод testStorage для него.
        Запусти и проверь, что все тесты проходят.

Требования:
        1. Класс FunctionalTest должен быть добавлен в созданный пакет tests.
        2. В методе testStorage должны быть трижды вызваны методы getId и getString.
        3. Тестовые методы перечисленные в условии задачи должны быть отмечены только аннотацией @Test.
        4. В каждом тестовом методе должен содержаться вызов метода testStorage

Shortener (15)
        Напишем еще один тест, который проверит, что получить идентификатор для строки используя стратегию HashBiMapStorageStrategy можно быстрее, чем используя стратегию HashMapStorageStrategy.
        15.1. Создай класс SpeedTest в пакете tests.
        15.2. Добавь в класс метод long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids). Он должен возвращать время в миллисекундах необходимое для получения идентификаторов для всех строк из strings. Идентификаторы должны быть записаны в ids.
        15.3. Добавь в класс метод long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings). Он должен возвращать время в миллисекундах необходимое для получения строк для всех идентификаторов из ids. Строки должны быть записаны в strings.
        15.4. Добавь в класс SpeedTest тест testHashMapStorage(). Он должен:
        15.4.1. Создавать два объекта типа Shortener, один на базе HashMapStorageStrategy, второй на базе HashBiMapStorageStrategy. Назовем их shortener1 и shortener2.
        15.4.2. Генерировать с помощью Helper 10000 строк и помещать их в сет со строками, назовем его origStrings.
        15.4.3. Получать время получения идентификаторов для origStrings (вызывать метод getTimeToGetIds для shortener1, а затем для shortener2).
        15.4.4. Проверять с помощью junit, что время, полученное в предыдущем пункте для shortener1 больше, чем для shortener2.
        15.4.5. Получать время получения строк (вызывать метод getTimeToGetStrings для shortener1 и shortener2).
        15.4.6. Проверять с помощью junit, что время, полученное в предыдущем пункте для shortener1 примерно равно времени для shortener2. Используй метод assertEquals(float expected, float actual, float delta). В качестве delta можно использовать 30, этого вполне достаточно для наших экспериментов.


Требования:
        1. Метод getTimeToGetStrings должен возвращать время в миллисекундах необходимое для получения всех строк для множества идентификаторов ids.
        2. Метод getTimeToGetIds должен возвращать время в миллисекундах необходимое для получения всех идентификаторов для множества строк strings.
        3. В методе testHashMapStorage должно быть выполнено сравнение времени получения множества ключей и множества значений для стратегий HashMapStorageStrategy и HashBiMapStorageStrategy.
*/

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Solution {

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        return strings
                .stream()
                .map(shortener::getId)
                .collect(Collectors.toSet());
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        return keys
                .stream()
                .map(shortener::getString)
                .collect(Collectors.toSet());
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> stringSet = LongStream
                .range(0, elementsNumber)
                .mapToObj(i -> Helper.generateRandomString())
                .collect(Collectors.toSet());

        Shortener shortener = new Shortener(strategy);
        Date startIds = new Date();
        Set<Long> testLongs = getIds(shortener, stringSet);
        Date stopIds = new Date();
        long resultIds = stopIds.getTime() - startIds.getTime();

        Helper.printMessage("Ids test result: " + resultIds + "ms");

        Date startStgs = new Date();
        Set<String> testStrings = getStrings(shortener, testLongs);
        Date stopStgs = new Date();
        long resultStgs = stopStgs.getTime() - startStgs.getTime();

        Helper.printMessage("Strings test result: " + resultStgs + "ms");

        if(testLongs.size() == testStrings.size())
            Helper.printMessage("Тест пройден.");
        else
            Helper.printMessage("Тест не пройден.");

    }

    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
        testStrategy(new FileStorageStrategy(), 100);
        testStrategy(new OurHashBiMapStorageStrategy(), 10000);
        testStrategy(new HashBiMapStorageStrategy(), 10000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 10000);
    }
}
