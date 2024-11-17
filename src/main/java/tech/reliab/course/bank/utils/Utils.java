package tech.reliab.course.bank.utils;

public class Utils {
    /* Получение случайного числа типа Integer на отрезке [a, b) */
    public static int randomFromAToB(Integer a, Integer b) {
        return (int) (Math.random() * (b - a)) + (a);
    }
}
