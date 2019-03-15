package com.javarush.task.task34.task3404;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/*
Рекурсия для мат. выражения
*/
public class Solution {
    public Solution() {
        //don't delete
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6

    }

    public void recurse(String expression, int countOperation) {
        expression = expression.replaceAll("\\s+", "").toLowerCase();
        //implement

        // Подсчёт знаков
        if (countOperation == 0) {
            String test = expression;
            while (test.contains("sin") || test.contains("cos") || test.contains("tan")) {
                test = test.replace("sin", "s");
                test = test.replace("cos", "c");
                test = test.replace("tan", "t");
            }
            for (char d : test.toCharArray()) {
                switch (d) {
                    case 'c':
                        countOperation++;
                        break;
                    case 's':
                        countOperation++;
                        break;
                    case 't':
                        countOperation++;
                        break;
                    case '*':
                        countOperation++;
                        break;
                    case '/':
                        countOperation++;
                        break;
                    case '^':
                        countOperation++;
                        break;
                    case '+':
                        countOperation++;
                        break;
                    case '-':
                        countOperation++;
                        break;
                }
            }
        }


        List<String> commonArr = new ArrayList<>();
        String s = expression;

        String[] numbers;

        if (expression.contains("(") && expression.contains(")")) {
            s = expression.substring(expression.lastIndexOf("(") + 1);
            s = s.substring(0, s.indexOf(")"));
        }
        String s2 = s;

        while (s.contains("sin") || s.contains("cos") || s.contains("tan")) {
            if (s.contains("sin-")) {
                s = s.replace("sin-", "s-");
            } else if (s.contains("sin")) {
                s = s.replace("sin", "s");
            }
            if (s.contains("cos-")) {
                s = s.replace("cos-", "c-");
            } else if (s.contains("cos")) {
                s = s.replace("cos", "c");
            }
            if (s.contains("tan-")) {
                s = s.replace("tan-", "t-");
            } else if (s.contains("tan")) {
                s = s.replace("tan", "t");
            }
        }
        String s1 = s;

        //"разбиение" строки на элементы (числа и мат. действия) и помещение их в commonArr
        s = s.startsWith("-") ? s.substring(1, s.length()) : s;
        numbers = s.split("[s,c,t/^,/*,//,+,/-]");
        int i = 0;
        for (Character ch : s1.toCharArray()) {
            if (ch == '^' || ch == '*' || ch == '/' || ch == '+' || ch == '-') {
                if (s1.startsWith("-")) {
                    commonArr.add(String.valueOf(ch));
                    commonArr.add(numbers[i]);
                } else {
                    commonArr.add(numbers[i]);
                    commonArr.add(String.valueOf(ch));
                }
                i++;
            }
            if (ch == 's' || ch == 'c' || ch == 't') {
                commonArr.add(String.valueOf(ch));
                commonArr.add(numbers[i]);
                i++;
            }

            if (i == numbers.length - 1 && !s1.startsWith("-")) {
                commonArr.add(numbers[i]);
                break;
            }
        }

        // для случая, когда подряю идут два знака
        if (commonArr.contains("")) {
            for (int j = 0; j < commonArr.size(); j++) {
                if (commonArr.get(j).equals("")) {
                    if (commonArr.get(j + 1).equals("-")) {
                        double r = -Double.valueOf(commonArr.get(j + 2));
                        commonArr.set(j + 1, String.valueOf(r));
                        commonArr.remove(j + 2);
                    }
                    commonArr.remove(j);
                    j = 0;
                }
            }
        }


        // вычисление sin, cos, tan
        if (commonArr.contains("s") || commonArr.contains("c") || commonArr.contains("t")) {
            double x;
            for (int j = 0; j < commonArr.size(); j++) {
                switch (commonArr.get(j)) {
                    case "s":
                        if (j > 0 && commonArr.get(j - 1).equals("-")) {
                            x = -Math.sin(Math.toRadians(Double.valueOf(commonArr.get(j + 1))));
                        } else x = Math.sin(Math.toRadians(Double.valueOf(commonArr.get(j + 1))));
                        commonArr.set(j, String.valueOf(x));
                        commonArr.remove(j + 1);
                        commonArr.set(j, String.valueOf(x));
                        if (j > 0 && commonArr.get(j - 1).equals("-")) commonArr.remove(j - 1);
                        break;
                    case "c":
                        if (j > 0 && commonArr.get(j - 1).equals("-")) {
                            x = -Math.cos(Math.toRadians(Double.valueOf(commonArr.get(j + 1))));
                        } else x = Math.cos(Math.toRadians(Double.valueOf(commonArr.get(j + 1))));
                        commonArr.set(j, String.valueOf(x));
                        commonArr.remove(j + 1);
                        commonArr.set(j, String.valueOf(x));
                        if (j > 0 && commonArr.get(j - 1).equals("-")) commonArr.remove(j - 1);
                        break;
                    case "t":
                        if (j > 0 && commonArr.get(j - 1).equals("-")) {
                            x = -Math.tan(Math.toRadians(Double.valueOf(commonArr.get(j + 1))));
                        } else x = Math.tan(Math.toRadians(Double.valueOf(commonArr.get(j + 1))));
                        commonArr.set(j, String.valueOf(x));
                        commonArr.remove(j + 1);
                        commonArr.set(j, String.valueOf(x));
                        if (j > 0 && commonArr.get(j - 1).equals("-")) commonArr.remove(j - 1);
                        break;
                }
            }
        }

        // возведение в степень
        if (commonArr.contains("^")) {
            for (int j = 0; j < commonArr.size(); j++) {
                if (commonArr.get(j).equals("^")) {
                    commonArr.set(j - 1, String.valueOf(Math.pow(Double.valueOf(commonArr.get(j - 1)), Double.valueOf(commonArr.get(j + 1)))));
                    commonArr.remove(j + 1);
                    commonArr.remove(j);
                    j = 0;
                }
            }
        }

        // перемножение и деление элементов commonArr
        if (commonArr.contains("*") || commonArr.contains("/")) {
            for (int j = 0; j < commonArr.size(); j++) {
                double z;
                switch (commonArr.get(j)) {
                    case "*":
                        z = Double.valueOf(commonArr.get(j - 1)) * Double.valueOf(commonArr.get(j + 1));
                        commonArr.set(j - 1, String.valueOf(z));
                        commonArr.remove(j + 1);
                        commonArr.remove(j);
                        j = 0;
                        break;
                    case "/":
                        z = Double.valueOf(commonArr.get(j - 1)) / Double.valueOf(commonArr.get(j + 1));
                        commonArr.set(j - 1, String.valueOf(z));
                        commonArr.remove(j + 1);
                        commonArr.remove(j);
                        j = 0;
                        break;
                }
            }
        }

        // Расстановка "минусов"
        if (commonArr.contains("-") || commonArr.contains("+")) {
            for (int j = 0; j < commonArr.size(); j++) {
                double x;
                switch (commonArr.get(j)) {
                    case "-":
                        x = Double.valueOf(commonArr.get(j + 1)) * (-1);
                        commonArr.set(j + 1, String.valueOf(x));
                        commonArr.remove(j);
                        j = 0;
                        break;
                    case "+":
                        x = Double.valueOf(commonArr.get(j + 1));
                        commonArr.set(j + 1, String.valueOf(x));
                        commonArr.remove(j);
                        j = 0;
                        break;
                }
            }
        }

        double res = 0.0;

        // Сложение всех элементов commonArr
        for (String numb : commonArr) res += Double.valueOf(numb);

        if (expression.contains("(") && expression.contains(")")) {
            String jp = expression.replace("(" + s2 + ")", String.valueOf(res));
            recurse(jp, countOperation);
        } else {
            String k = new BigDecimal(res).setScale(2, RoundingMode.HALF_UP).toString();
            k = new DecimalFormat("#.##").format(Double.valueOf(k));
            System.out.println(k.replace(",", ".") + " " + countOperation);
            return;
        }
    }
}


/*
package com.javarush.task.task34.task3404;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

*/
/*
Рекурсия для мат. выражения
*//*

public class Solution {
    public Solution() {
        //don't delete
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse("sin(2*(-5+ 1.5*4)+28)", 0); //expected output 0.5 6
        solution.recurse("tan(45)", 0); //expected output 1 1
        solution.recurse("tan(-45)", 0); //expected output -1 2
        solution.recurse("0.305", 0); //expected output 0.3 0
        solution.recurse("0.3051", 0); //expected output 0.31
        solution.recurse("(0.3051)", 0); //expected output 0.31
        solution.recurse("1 + (1 + (1 + 1) * (1 + 1)) * (1 + 1) + 1", 0); //expected output 12 8
        solution.recurse("tan(44 + sin(89 - cos(180) ^ 2))", 0); //expected output 1 6!!
        solution.recurse("- 2 + (-2 + (-2) - 2 * (2 + 2))", 0); //expected output -14 8
        solution.recurse("sin(80 + (2 + (1 + 1)) * (1 + 1) + 2)", 0); //expected output 1 7
        solution.recurse("1 + 4 / 2 / 2 + 2 ^ 2 + 2 * 2 - 2 ^ (2 - 1 + 1)", 0); //expected output 6 11
        solution.recurse("10 - 2 ^ (2 - 1 + 1)", 0); //expected output 6 4
        solution.recurse("2 ^ 10 + 2 ^ (5 + 5)", 0); //expected output 2048 4
        solution.recurse("1.01 + (2.02 - 1 + 1 / 0.5 * 1.02) / 0.1 + 0.25 + 41.1", 0); //expected output 72.96 8
        solution.recurse("0.000025 + 0.000012", 0); //expected output 0 1
        solution.recurse("-2 - (-2 - 1 - (-2) - (-2) - (-2 - 2 - (-2) - 2) - 2 - 2)", 0); //expected output -3 16
        solution.recurse("cos(3 + 19 * 3)", 0); //expected output 0.5 3
        solution.recurse("2 * (589 + ((2454 * 0.1548 / 0.01 * (-2 + 9 ^ 2)) + ((25 * 123.12 + 45877 * 25) + 25)) - 547)", 0); //expected output 8302231.36 14
        solution.recurse("(-1 + (-2))", 0); //expected output -3 3
        solution.recurse("-sin(2 * (-5 + 1.5 * 4) + 28)", 0); //expected output -0.5 7
        solution.recurse("sin(100) - sin(100)", 0); //expected output 0 3
    }

    public void recurse(final String expression, int countOperation) {
        String expr = expression.replaceAll("\\s+", "").toLowerCase();
        String[] operators = expr.split("\\d+\\.?\\d*|\\(|\\)");
        int count = 0;
        for (String operator : operators) {
            if(!operator.isEmpty())
                count++;
        }
        if (count > countOperation)
            countOperation = count;

        if (expr.matches("-?\\d+\\.?\\d*") || expr.matches("-?\\d+\\.?\\d*e-?\\d+")) {
            NumberFormat format = new DecimalFormat("#.##");
            System.out.println(String.format("%s %d", format.format(Double.parseDouble(expr)), countOperation));
            return;
        }

//        System.out.println(expr);

        String subExp = null;

        if (expr.contains("(")) {
            int open = expr.lastIndexOf('(');
            int close = expr.indexOf(')', expr.lastIndexOf('('));
            subExp = expr.substring(open + 1, close);

        } else subExp = expr;

//        System.out.println(subExp);

        char[] signs = {'^', '/', '*', '-', '+'};
        String tmp = subExp;

        for (char c : signs) {
            String pattern = String.format("-?\\d+\\.?\\d*\\%s-?\\d+\\.?\\d*", c);

            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(subExp);
            while (m.find()) {
                String exp = m.group();
                double a = Double.parseDouble(exp.substring(0, exp.lastIndexOf(c)));
                double b = Double.parseDouble(exp.substring(exp.lastIndexOf(c) + 1, exp.length()));
                double result = 0;

                switch (c) {
                    case '^':
                        result = new BigDecimal(Math.pow(Math.abs(a), b)).setScale(4, RoundingMode.HALF_UP).doubleValue();
                        if (a < 0) {
                            result = -result;
                        }
                        break;
                    case '/':
                        result = new BigDecimal(a / b).setScale(4, RoundingMode.HALF_UP).doubleValue();
                        break;
                    case '*':
                        result = new BigDecimal(a * b).setScale(4, RoundingMode.HALF_UP).doubleValue();
                        break;
                    case '-':
                        result = new BigDecimal(a - b).setScale(4, RoundingMode.HALF_UP).doubleValue();
                        break;
                    case '+':
                        result = new BigDecimal(a + b).setScale(4, RoundingMode.HALF_UP).doubleValue();
                        break;
                }

                subExp = m.replaceFirst(result + "");
                m.reset(subExp);
            }
        }
//        System.out.println(subExp);

        expr = expr.replace(tmp, subExp);

//        System.out.println(expr);

        Pattern p = Pattern.compile("(sin|cos|tan)\\((-?\\d+\\.?\\d*\\))");
        Matcher m = p.matcher(expr);
        if (m.find()) {
            String exp = m.group();
            String foo = exp.substring(0, exp.lastIndexOf('('));
            double b = Double.parseDouble(exp.substring(exp.lastIndexOf("(") + 1, exp.lastIndexOf(')')));
            double result = 0;

            switch (foo) {
                case "sin":
                    result = Math.sin(Math.toRadians(b));
                    break;
                case "cos":
                    result = Math.cos(Math.toRadians(b));
                    break;
                case "tan":
                    result = Math.tan(Math.toRadians(b));
                    break;
            }
            result = new BigDecimal(result).setScale(4, RoundingMode.HALF_UP).doubleValue();
            expr = expr.replace(exp, String.valueOf(result));
        }

        p = Pattern.compile("([-+])\\(((\\1)\\d+\\.?\\d*\\))");
        m = p.matcher(expr);
        if (m.find()) {
            String exp = m.group();
            String result = "+" + exp.substring(3, exp.length() - 1);
            expr = expr.replace(exp, result);
        }

        p = Pattern.compile("(?<!\\d)\\(([-+]?\\d+\\.?\\d*\\))");
        m = p.matcher(expr);
        if (m.find()) {
            String exp = m.group();
            String result = exp.replaceAll("[\\(\\)]", "");
            expr = expr.replace(exp, result);
        }


        expr = expr.replaceAll("\\+-|--|-\\+", "-");


//        System.out.println(expr);

        recurse(expr, countOperation);
    }
}
*/
