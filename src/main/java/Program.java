import java.util.Scanner;
import java.util.regex.Pattern;

public class Program {
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Введите основание исходной системы счисления");
        short originNumberSystem = validInputSystem();

        System.out.println("Введите основание целевой системы счисления:");
        short resultNumberSystem = validInputSystem();

        System.out.println("Введите число:");
        String number;
        while (true)
        {
            number = in.next().toUpperCase(); //Вызов функции позволяет вводить как в нижнем, так и в верхнем регистре
            if (isRightNumber(number, originNumberSystem))
                break;
            System.out.println("Вы неправильно ввели число.");
            System.out.println("Убедитесь, что оно правильно записано в исходной системе счисления.");
        }

        ToAnotherNumberSystem operation = new ToAnotherNumberSystem(originNumberSystem, resultNumberSystem, number);
        System.out.println(operation.transfer());

        in.close();
    }

    private static short validInputSystem()
    {
        System.out.println("Оно должно быть от 2 до 36");
        while(true)
        {
            String inputVal = in.next();
            if(Pattern.matches("[0-9][0-9]?", inputVal)) {
                short rezValue = Short.parseShort(inputVal);
                if(rezValue >= 2 && rezValue <= 36)
                    return rezValue;
            }
            System.out.println("Вы ввели неверные данные. Попробуйте еще раз.");
        }
    }

    private static String makingRegExp(short originNumberSystem)
    {
        StringBuilder regExp = new StringBuilder("[");
        for(int i = 0; i < originNumberSystem; i++)
            if (i < 10)
                regExp.append(i);
            else
                regExp.append((char) ('A' + i - 10));
        regExp.append("]+");
        return regExp.toString();
    }

    private static boolean isRightNumber(String number, short originNumberSystem) {
        String regExp = makingRegExp(originNumberSystem);
        return Pattern.matches(regExp, number);
    }
}
