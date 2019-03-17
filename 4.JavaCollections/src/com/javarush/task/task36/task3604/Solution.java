package com.javarush.task.task36.task3604;

/* 
Разбираемся в красно-черном дереве
Дана реализация красно-черного дерева.
Некоторые методы сломаны. Разберись в коде и исправь ошибки.
Метод main не участвует в тестировании.
Все модификаторы правильные.
Имена переменных и методов не изменяйте.


Требования:
1. Исправь ошибку в методе isEmpty в классе RedBlackTree.
2. Исправь ошибку в методе rotateWithRightNode в классе RedBlackTree.
3. Исправь ошибку в методе insert в классе RedBlackTree.
4. Класс RedBlackTree должен реализовывать красно-черное дерево.
*/

public class Solution {
    public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();
        rbt.insert(1);
        rbt.insert(2);
        rbt.insert(3);
        rbt.insert(4);
        rbt.insert(5);
        rbt.insert(6);
        rbt.insert(7);
        rbt.insert(8);
        rbt.insert(9);
        rbt.insert(10);
        rbt.insert(11);
        rbt.insert(12);
    }
}
