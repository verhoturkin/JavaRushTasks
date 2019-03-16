package com.javarush.task.task05.task0510;

/* 
Кошкоинициация
Создать класс Cat (кот) с пятью инициализаторами:
- Имя,
- Имя, вес, возраст
- Имя, возраст (вес стандартный)
- вес, цвет (имя, адрес и возраст неизвестны, это бездомный кот)
- вес, цвет, адрес (чужой домашний кот)

Задача инициализатора - сделать объект валидным.
Например, если вес неизвестен, то нужно указать какой-нибудь средний вес.
Кот не может ничего не весить.
То же касается возраста и цвета.
А вот имени может и не быть (null).
То же касается адреса: null.


Требования:
1. Программа не должна считывать данные с клавиатуры.
2. У класса Cat должна быть переменная name с типом String.
3. У класса Cat должна быть переменная age с типом int.
4. У класса Cat должна быть переменная weight с типом int.
5. У класса Cat должна быть переменная address с типом String.
6. У класса Cat должна быть переменная color с типом String.
7. У класса должен быть метод initialize, принимающий в качестве параметра имя, но инициализирующий все переменные класса, кроме адреса.
8. У класса должен быть метод initialize, принимающий в качестве параметров имя, вес, возраст и инициализирующий все переменные класса, кроме адреса.
9. У класса должен быть метод initialize, принимающий в качестве параметров имя, возраст и инициализирующий все переменные класса, кроме адреса.
10. У класса должен быть метод initialize, принимающий в качестве параметров вес, цвет и инициализирующий все переменные класса, кроме имени и адреса.
11. У класса должен быть метод initialize, принимающий в качестве параметров вес, цвет, адрес и инициализирующий все переменные класса, кроме имени.
*/

public class Cat {
    private String name;
    private int age;
    private int weight;
    private String address;
    private String color;
    private int averageAge = 6;
    private int averageWeight = 3;
    private String averageColor = "gray";

    public static void main(String[] args) {

    }

    public void setAverageAge(int averageAge) {
        this.averageAge = averageAge;
    }

    public void setAverageWeight(int averageWeight) {
        this.averageWeight = averageWeight;
    }

    public void setAverageColor(String averageColor) {
        this.averageColor = averageColor;
    }

    public void initialize(String name) {
        this.name = name;
        this.age = averageAge;
        this.color = averageColor;
        this.weight = averageWeight;
    }

    public void initialize(String name, int weight, int age) {
        this.name = name;
        this.age = age;
        this.color = averageColor;
        this.weight = weight;
    }

    public void initialize(String name, int age) {
        this.name = name;
        this.age = age;
        this.color = averageColor;
        this.weight = averageWeight;
    }

    public void initialize(int weight, String color) {
        this.age = averageAge;
        this.color = color;
        this.weight = weight;
    }

    public void initialize(int weight, String color, String address) {
        this.age = averageAge;
        this.color = color;
        this.weight = weight;
        this.address = address;
    }
}
