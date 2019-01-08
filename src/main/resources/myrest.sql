-- phpMyAdmin SQL Dump
-- version 4.7.3
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Дек 25 2018 г., 22:45
-- Версия сервера: 5.6.37
-- Версия PHP: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `myrest`
--
CREATE DATABASE IF NOT EXISTS `myrest` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `myrest`;

-- --------------------------------------------------------

--
-- Структура таблицы `app_role`
--

CREATE TABLE `app_role` (
  `role_id` bigint(20) NOT NULL,
  `role_name` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `app_role`
--

INSERT INTO `app_role` (`role_id`, `role_name`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Структура таблицы `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(2, 'Advanced'),
(3, 'Top'),
(1, 'Elementary');

-- --------------------------------------------------------

--
-- Структура таблицы `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(3);

-- --------------------------------------------------------

--
-- Структура таблицы `question`
--

CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `correct_answer_number` int(11) NOT NULL,
  `first_answer` varchar(255) NOT NULL,
  `forth_answer` varchar(255) NOT NULL,
  `question_text` text NOT NULL,
  `second_answer` varchar(255) NOT NULL,
  `third_answer` varchar(255) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `question`
--

INSERT INTO `question` (`id`, `correct_answer_number`, `first_answer`, `forth_answer`, `question_text`, `second_answer`, `third_answer`, `category_id`) VALUES
(1, 0, 'programming language', 'coffee', 'What is Java', 'tea', 'island', 2),
(2, 3, 'first', 'fourth', 'Super question: correct answer is fourth:)', 'second', 'third', 3),
(3, 2, '1 and 2', '1 and 4', 'Which two statements are equivalent?\r\n\r\n    3/2\r\n    3<2\r\n    3*4\r\n    3<<2', '2 and 3', '3 and 4', 1),
(4, 3, 'Zero', 'Compilation fails', 'Float f = new Float(\"12\"); \r\nswitch (f) \r\n{\r\n    case 12: System.out.println(\"Twelve\"); \r\n    case 0: System.out.println(\"Zero\"); \r\n    default: System.out.println(\"Default\"); \r\n}', 'Twelve', 'Default', 1),
(5, 0, 'The elements in the collection are ordered.', 'The elements in the collection are accessed using a unique key.', 'Which statement is true for the class java.util.ArrayList?', 'The collection is guaranteed to be immutable.', 'The elements in the collection are guaranteed to be unique.', 1),
(6, 1, ' 	\r\nIt must be marked final.', 'It can be marked static.', 'Which is true about a method-local inner class?', 'It can be marked abstract.', 'It can be marked public.', 1),
(7, 2, 'Thread t = new Thread(X);', 'Thread t = new Thread(); x.run();', 'class X implements Runnable \r\n{ \r\n    public static void main(String args[]) \r\n    {\r\n        /* Missing code? */\r\n    } \r\n    public void run() {} \r\n}\r\nWhich of the following line of code is suitable to start a thread ?', 'Thread t = new Thread(X); t.start();', 'X run = new X(); Thread t = new Thread(run); t.start();', 1),
(8, 1, 'In an assert statement, the expression after the colon ( : ) can be any Java expression.', 'It is appropriate to handle assertion failures using a catch clause.', 'Which of the following statements is true?', 'If a switch block has no default, adding an assert default is considered appropriate.', 'In an assert statement, if the expression after the colon ( : ) does not have a value, the assert\'s error message will be empty.', 1),
(9, 2, 'protected int a;', 'volatile int d;', 'Which of the following class level (nonlocal) variable declarations will not compile?', 'transient int b = 3;', 'private synchronized int e;', 1),
(10, 0, 'It prints f2[0] = 0.0', 'It prints the garbage value.', 'What will be the output of the program?\r\npublic class ArrayTest \r\n{ \r\n    public static void main(String[ ] args)\r\n    { \r\n        float f1[ ], f2[ ]; \r\n        f1 = new float[10]; \r\n        f2 = f1; \r\n        System.out.println(\"f2[0] = \" + f2[0]); \r\n   ', 'It prints f2[0] = NaN', 'An error at f2 = f1; causes compile to fail.', 1),
(11, 1, 'Class B\'S constructor is public.', 'None of these.', 'class A \r\n{ \r\n    A( ) { } \r\n} \r\n\r\nclass B extends A \r\n{ }\r\n\r\nWhich statement is true?', 'Class B\'S constructor has no arguments.', 'Class B\'S constructor includes a call to this( ).', 1),
(12, 3, 'i = 0', 'Compilation fails.', 'What will be the output of the program?\r\n\r\nint i = 0; \r\nwhile(1) \r\n{\r\n    if(i == 4) \r\n    {\r\n        break;\r\n    } \r\n    ++i; \r\n} \r\nSystem.out.println(\"i = \" + i);\r\n', 'i = 3', 'i = 4', 1),
(13, 2, 'I is 0', 'None of the above', 'What will be the output of the program?\r\n\r\nint I = 0;\r\nlabel:\r\n    if (I < 2) {\r\n    System.out.print(\"I is \" + I);\r\n    I++;\r\n    continue label;\r\n}\r\n', 'I is 0 I is 1', 'Compilation fails.', 1),
(14, 3, 'A try statement must have at least one corresponding catch block.', 'Except in case of VM shutdown, if a try block starts to execute, a corresponding finally block will always start to execute.', 'Which statement is true?', 'Multiple catch statements can catch the same class of exception more than once.', 'An Error that might be thrown in a method must be declared as thrown by that method, or be handled within that method.', 1),
(15, 2, '3.0', 'Compilation fails.', 'What will be the output of the program?\r\n\r\npublic class SqrtExample \r\n{\r\n    public static void main(String [] args) \r\n    {\r\n        double value = -9.0;\r\n        System.out.println( Math.sqrt(value));\r\n    }\r\n}\r\n', '-3.0', 'NaN', 1),
(16, 1, 'apa', 'apep', 'What will be the output of the program?\r\n\r\nString a = \"newspaper\";\r\na = a.substring(5,7);\r\nchar b = a.charAt(1);\r\na = a + b;\r\nSystem.out.println(a);\r\n', 'app', 'apea', 1),
(17, 2, 'final', 'protected', 'Given a method in a protected class, what access modifier do you use to restrict access to that method to only the other members of the same class?', 'static', 'private', 2),
(18, 1, 'small', 'Compilation fails', 'What will be the output of the program?\r\n\r\nclass Test \r\n{\r\n    public static void main(String [] args) \r\n    {\r\n        int x=20;\r\n        String sup = (x < 15) ? \"small\" : (x < 22)? \"tiny\" : \"huge\";\r\n        System.out.println(sup);\r\n    }\r\n}', 'tiny', 'huge', 2),
(19, 3, '1 and 2', 'All statements are correct.', 'Which of the following are legal lines of code?\r\n\r\n    int w = (int)888.8;\r\n    byte x = (byte)1000L;\r\n    long y = (byte)100;\r\n    byte z = (byte)100L;\r\n', '2 and 3', '3 and 4', 2),
(20, 1, '1 and 2', '1 and 3', 'Which two statements are equivalent?\r\n\r\n    16*4\r\n    16>>2\r\n    16/2^2\r\n    16>>>2\r\n', '2 and 4', '3 and 4', 2),
(21, 2, 'I is 1', 'Compilation error', 'What will be the output of the program?\r\n\r\npublic class Test \r\n{\r\n    public static void main(String [] args) \r\n    {\r\n        int I = 1;\r\n        do while ( I < 1 )\r\n        System.out.print(\"I is \" + I);\r\n        while ( I > 1 ) ;\r\n    }\r\n}', 'I is 1 I is 1', 'No output is produced.', 2),
(22, 1, '1 and 2', '2 and 4', 'Which of the following are Java reserved words?\r\n\r\n    run\r\n    import\r\n    default\r\n    implement\r\n', '2 and 3', '3 and 4', 2),
(23, 1, 'A static method cannot be synchronized.', 'When a thread sleeps, it releases its locks.', 'Which statement is true?', 'If a class has synchronized code, multiple threads can still access the nonsynchronized code.', 'Variables can be protected from concurrent access problems by marking them with the synchronized keyword.', 2),
(24, 2, 'Calling Runtime.gc() will cause eligible objects to be garbage collected.', 'If object 1 refers to object 2, then object 2 can\'t be garbage collected.', 'Which statement is true?', 'The garbage collector uses a mark and sweep algorithm.', 'If an object can be accessed from a live thread, it can\'t be garbage collected.', 2),
(25, 0, 'Assertion expressions should not contain side effects.', 'An AssertionError thrown as a result of a failed assertion should always be handled by the enclosing method.', 'Which statement is true about assertions in the Java programming language?', 'Assertion expression values can be any primitive type.', 'Assertions should be used for enforcing preconditions on public methods.', 2),
(26, 0, ' 	\r\nint [ ][ ] scores = {2,7,6}, {9,3,45};', 'Integer results[ ] = {new Integer(3), new Integer(5), new Integer(8)};', 'Which cause a compiler error?', 'String cats[ ] = {\"Fluffy\", \"Spot\", \"Zeus\"};', 'boolean results[ ] = new boolean [] {true, false, true};', 2),
(27, 3, 'j = -1', 'Compilation fails.', 'What will be the output of the program?\r\n\r\nint i = l, j = -1; \r\nswitch (i) \r\n{\r\n    case 0, 1: j = 1;\r\n    case 2: j = 2; \r\n    default: j = 0; \r\n} \r\nSystem.out.println(\"j = \" + j); \r\n', 'j = 0', 'j = 1', 2),
(28, 0, 'Java.util.Map', 'Java.util.Collection', 'Which interface provides the capability to store objects using a key-value pair?', 'Java.util.Set', 'Java.util.List', 2),
(29, 0, 'wait()', 'exits synchronized code', 'Which of the following will directly stop the execution of a Thread?', 'notify()', 'notifyall()', 2),
(30, 0, 'Object', 'Class', 'Which class or interface defines the wait(), notify(),and notifyAll() methods?', 'Thread', 'Runnable', 2),
(31, 2, 'public class MyRunnable extends Runnable{public void run(){}}', 'public class MyRunnable implements Runnable{void run(){}}', 'The following block of code creates a Thread using a Runnable target:\r\nRunnable target = new MyRunnable();\r\nThread myThread = new Thread(target);\r\nWhich of the following classes can be used to create the target, so that the preceding code compiles correct', 'public class MyRunnable extends Object{public void run(){}}', 'public class MyRunnable implements Runnable{public void run(){}}', 2),
(32, 0, 'i = 0', 'Statement causes a compile error', 'What will be the output of the program?\r\n\r\nint i = (int) Math.random();\r\n', 'i = 1', 'value of i is undetermined', 2),
(33, 2, 'public', 'transient', 'You want subclasses in any package to have access to members of a superclass. Which is the most restrictive access that accomplishes this objective?', 'private', 'protected', 2),
(34, 0, 'public int method1(int a, int b) {return 0; }', 'static protected int method1(int a, int b) { return 0; }', 'class A \r\n{  \r\n    protected int method1(int a, int b) \r\n    {\r\n        return 0; \r\n    } \r\n}\r\n\r\nWhich is valid in a class that extends class A?', 'private int method1(int a, int b) { return 0; }', 'public short method1(int a, int b) { return 0; }', 3),
(35, 0, 'Compilation fails.', '\"odd\" will be output for odd values of x, and \"even\" for even values.', 'public void test(int x) \r\n{ \r\n    int odd = 1; \r\n    if(odd) /* Line 4 */\r\n    {\r\n        System.out.println(\"odd\"); \r\n    } \r\n    else \r\n    {\r\n        System.out.println(\"even\"); \r\n    } \r\n}\r\nWhich statement is true?', '\"odd\" will always be output.', '\"even\" will always be output.', 3),
(36, 3, 'i = 6 and j = 5', 'i = 5 and j = 6', 'What will be the output of the program?\r\nint i = 1, j = 10; \r\ndo \r\n{\r\n    if(i > j) \r\n    {\r\n        break; \r\n    } \r\n    j--; \r\n} while (++i < 5); \r\nSystem.out.println(\"i = \" + i + \" and j = \" + j);', 'i = 5 and j = 5', 'i = 6 and j = 4', 3),
(37, 0, 'catch(X x) can catch subclasses of X where X is a subclass of Exception.', 'Any statement that can throw an Exception must be enclosed in a try block.', 'Which statement is true?', 'The Error class is a RuntimeException.', 'Any statement that can throw an Error must be enclosed in a try block.', 3),
(38, 0, 'float f = 1F;', 'float f = 1.0d;', 'Which is valid declaration of a float?', 'float f = 1.0;', ' 	\r\nfloat f = \"1\";', 3),
(39, 1, '1, 2 and 4', '4, 5 and 7', 'Which three guarantee that a thread will leave the running state?\r\n    yield()\r\n    wait()\r\n    notify()\r\n    notifyAll()\r\n    sleep(1000)\r\n    aLiveThread.join()\r\n    Thread.killThread()', '2, 5 and 6', '3, 4 and 7', 3),
(40, 0, 'run();', 'main();', 'Which will contain the body of the thread?', 'start();', 'stop();', 3),
(41, 2, 'true', 'An exception is thrown at runtime', ' 	\r\n\r\nWhat will be the output of the program?\r\n\r\nclass Equals \r\n{\r\n    public static void main(String [] args) \r\n    {\r\n        int x = 100;\r\n        double y = 100.1;\r\n        boolean b = (x = y);\r\n        System.out.println(b);\r\n    }\r\n}\r\n', 'false', 'Compilation fails', 3),
(42, 3, '0', '14', 'What will be the output of the program?\r\n\r\nclass Bitwise \r\n{\r\n    public static void main(String [] args) \r\n    {\r\n        int x = 11 & 9;\r\n        int y = x ^ 3;\r\n        System.out.println( y | 12 );\r\n    }\r\n}', '7', '8', 3),
(43, 3, ' 	\r\nx = 6 y = 0', 'Compilation fails.', 'What will be the output of the program?\r\n\r\nint x = l, y = 6; \r\nwhile (y--) \r\n{\r\n    x++; \r\n} \r\nSystem.out.println(\"x = \" + x +\" y = \" + y);', 'x = 7 y = 0', 'x = 6 y = -1', 3),
(44, 3, '0 2 4', 'Compilation fails.', 'What will be the output of the program?\r\n\r\nfor (int i = 0; i < 4; i += 2) \r\n{ \r\n    System.out.print(i + \" \"); \r\n} \r\nSystem.out.println(i);', '0 2 4 5', '0 1 2 3 4', 3),
(45, 2, 'x = 1', 'The code runs with no output.', ' 	\r\n\r\nWhat will be the output of the program?\r\n\r\nint x = 3; \r\nint y = 1; \r\nif (x = y)\r\n{\r\n    System.out.println(\"x =\" + x); \r\n}\r\n', 'x = 3', 'Compilation fails.', 3),
(46, 0, 'Java.util.Map', 'None of the above', 'You need to store elements in a collection that guarantees that no duplicates are stored. Which one of the following interfaces provide that capability?', 'Java.util.List', 'Java.util.Collection', 3),
(47, 3, 'java.util.SortedMap', 'java.util.Hashtable', 'Which collection class allows you to access its elements by associating a key with an element\'s value, and provides synchronization?', 'java.util.TreeMap', 'java.util.TreeSet', 3),
(48, 1, '0 to 32767', '-32768 to 32767', 'What is the numerical range of char?', '0 to 65535', '-256 to 255', 3),
(49, 1, 'void run()', 'void run(int priority)', 'Which method must be defined by a class implementing the java.lang.Runnable interface?', 'public void run()', 'public void start()', 3),
(50, 2, 'If assertions are compiled into a source file, and if no flags are included at runtime, assertions will execute by default.', 'When evaluating command-line arguments, the VM gives -ea flags precedence over -da flags.', 'Which of the following statements is true?', 'As of Java version 1.4, assertion statements are compiled by default.', 'With the proper use of runtime arguments, it is possible to instruct the VM to disable assertions for a certain class, and to enable assertions for a certain package, at the same time.', 3);

-- --------------------------------------------------------

--
-- Структура таблицы `user_account`
--

CREATE TABLE `user_account` (
  `id` bigint(20) NOT NULL,
  `password` varchar(128) NOT NULL,
  `username` varchar(36) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user_account`
--

INSERT INTO `user_account` (`id`, `password`, `username`) VALUES
(1, '$2a$08$RCn6xlCzCJxGPhBQcFCAYudyeHzQh./d3VClcYnbrj7Wy1g/kb/vS', 'User'),
(2, '$2a$08$Uwq3z1oaXKmA3/ShO6q6OeOVNhihOVhQ3oZbATZwojuhqDu/leNaq', 'Admin');

-- --------------------------------------------------------

--
-- Структура таблицы `user_role`
--

CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `username` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user_role`
--

INSERT INTO `user_role` (`id`, `role_id`, `username`) VALUES
(1, 1, 1),
(2, 2, 2);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `app_role`
--
ALTER TABLE `app_role`
  ADD PRIMARY KEY (`role_id`),
  ADD UNIQUE KEY `APP_ROLE_UK` (`role_name`);

--
-- Индексы таблицы `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7jaqbm9p4prg7n91dd1uabrvj` (`category_id`);

--
-- Индексы таблицы `user_account`
--
ALTER TABLE `user_account`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `VK_USER_UK` (`username`);

--
-- Индексы таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `USER_ROLE_UK` (`username`,`role_id`),
  ADD KEY `FKp6m37g6n6c288s096400uw8fw` (`role_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT для таблицы `question`
--
ALTER TABLE `question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;
--
-- AUTO_INCREMENT для таблицы `user_account`
--
ALTER TABLE `user_account`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT для таблицы `user_role`
--
ALTER TABLE `user_role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;COMMIT;


CREATE TABLE `result` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(36) NOT NULL,
  `result_value` INT UNSIGNED NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = MyISAM DEFAULT CHARACTER SET = utf8;


INSERT INTO `result` (`username`, `result_value`, `date`)
VALUES
       ('User', 75, '2019-01-02 20:50:50'),
       ('User', 60, '2019-01-03 20:51:20'),
       ('Admin', 84, '2019-01-04 16:03:40');



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
