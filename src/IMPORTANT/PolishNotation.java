package IMPORTANT;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;

/**
 * Project name: HomeWork
 * Created by pavel on 29.08.17.
 * Алгоритм обратной польской нотации для вычисления мат. операций
 */
public class PolishNotation {
    public static void main(String[] args) throws IOException {
        Locale.setDefault(Locale.US);
        Scanner in = new Scanner(new File("in.txt"));       // Initializing reading and writing to a file
        PrintWriter out = new PrintWriter(new FileWriter(new File("out.txt")));
        out.print(reversePolishNotation(shuntingYard((in.hasNext()) ? in.nextLine() : ""))); //if we have information - start shunting yard
        out.close();
    }


    /**
     *
     * @param string
     * @return
     */
    public static LinkedQueue shuntingYard(String string){
        String c;
        String p = "";
        LinkedQueue output = new LinkedQueue();
        LinkedStack<String> operator = new LinkedStack();
        string = string.replace(" ", "");


        for (int i = 0; i < string.length(); i++) {
            c ="";
            c += string.charAt(i);
            if (precedence(c) == 0) {
                p += c;
            }else {
                if (!p.equals("")) output.add(p);  // создание числа
                p = "";
            }
            if (precedence(c) > 0){
                while (!operator.isEmpty() && (precedence(operator.peek()) >= precedence(c))){
                    output.add(operator.pop());
                }
                operator.push(c);
            }
            if (c.equals("(")) operator.push(c);
            if (c.equals(")")){
                while (!operator.isEmpty() && !operator.peek().equals("(")){
                    output.add(operator.pop());
                }
                if (operator.peek() != null) {
                    if (!operator.pop().equals("(")) return null; // проверка на скобки
                }else return null; // проверка на скобки
            }
        }
        if (!p.equals("")) output.add(p); // if last token is number we mast add it

        while (!operator.isEmpty()){
            if (operator.peek().equals("(") || operator.peek().equals(")")) return null; // проверка на скобки
            output.add(operator.pop());
        }

        return output;
    }


    /**
     *
     * @param queue
     * @return
     */
    public static String reversePolishNotation(LinkedQueue<String> queue){
        LinkedStack<String> stack = new LinkedStack();
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setMinimumFractionDigits(2);
        double value = 0;

        if (queue == null || queue.size() < 3) return "ERROR"; //minimum we must have 2 operands and 1 operator

        while (!queue.isEmpty()){
            if (precedence(queue.peek()) == 0){
                stack.push(queue.remove());
            }
            if (precedence(queue.peek()) > 0){
                stack.push(calculate(stack.pop(), queue.remove(), stack.pop()));
                if (stack.peek().equals("ERROR")) return "ERROR";
            }

        }
        return (stack.size() == 1)? df.format(Double.parseDouble(stack.pop())).toString() : "ERROR"; // форматирование строки
    }


    /**
     *
     * @param c
     * @return
     */
    public static int precedence(String c){
        if (c.equals("/") || c.equals("*")) return 2;
        if (c.equals("+") || c.equals("-")) return 1;
        if (c.equals("(") || c.equals(")")) return -1;
        return 0;
    }


    /**
     *
     * @param s2
     * @param command
     * @param s1
     * @return
     */
    public static String calculate(String s2, String command, String s1){
        double result = 0;
        double d1 = 0, d2 = 0;
        try {
            d1 = Double.parseDouble(s1);  // проверка на буквы в числе и пустые множества
            d2 = Double.parseDouble(s2);
        }
        catch (java.lang.NumberFormatException e){
            return "ERROR";
        }
        catch (java.lang.NullPointerException e){
            return "ERROR";
        }
        switch (command) {
            case "+":
                result = d1 + d2;
                break;
            case "-":
                result = d1 - d2;
                break;
            case "*":
                result = d1 * d2;
                break;
            case "/":
                if (d2 == 0) return "ERROR";
                result = d1 / d2;
                break;
            default:
                return "ERROR";
        }
        return String.valueOf(result);
    }
}
