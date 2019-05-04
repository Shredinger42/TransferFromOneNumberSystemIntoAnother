import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Program {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите основание исходной системы счисления");
        short originNumberSystem = in.nextShort();
        if(originNumberSystem < 2 || originNumberSystem > 36)
            throw new IllegalArgumentException("Основание исходной системы счисления должно быть больше 1 и меньше 37");

        System.out.println("Введите основание целевой системы счисления:");
        short resultNumberSystem = in.nextShort();
        if(resultNumberSystem < 2 || resultNumberSystem > 36)
            throw new IllegalArgumentException("Основание целевой системы счисления должно быть больше 1 и меньше 37");

        System.out.println("Введите число:");
        String number = in.next();
        if(!isRightNumber(number, originNumberSystem))
            throw new IllegalArgumentException("Число невозможно записать в исходной системе счисления.");

        ToAnotherNumberSystem operation = new ToAnotherNumberSystem(originNumberSystem, resultNumberSystem, number);
        System.out.println(operation.transfer());

        in.close();
    }

    private static void fillingWithValidSymbols(Set<Character> validSymbols, short originNumberSystem) {
        for(int i = 0; i < originNumberSystem; i++)
            if(i < 10)
                validSymbols.add((char)(i + '0'));
            else {
                char symbol = (char)('A' + i - 10); //Для вычесления кода A, B, C... в таблице юникода
                validSymbols.add(symbol);
            }
    }

    private static boolean isRightNumber(String number, short originNumberSystem) {
        Set<Character> validSymbols = new HashSet<Character>();
        fillingWithValidSymbols(validSymbols, originNumberSystem);
        for(char num : number.toCharArray())
            if(!validSymbols.contains(num))
                return false;
        return true;
    }
}
