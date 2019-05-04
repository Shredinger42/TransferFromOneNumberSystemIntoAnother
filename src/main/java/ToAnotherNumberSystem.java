import java.util.HashMap;
import java.util.Map;

class ToAnotherNumberSystem {
    private short originNumberSystem;
    private short resultNumberSystem;
    private String number;
    private Map<Character, Integer> decimalNumberSystem;
    private Map<Long, Character> rezNums;

    ToAnotherNumberSystem(short originNumberSystem, short resultNumberSystem, String number) {
        this.originNumberSystem = originNumberSystem;
        this.resultNumberSystem = resultNumberSystem;
        this.number = number;
        decimalNumberSystem = new HashMap<Character, Integer>();
        rezNums = new HashMap<Long, Character>();
        fillingDecimalSystem();
        fillingRezNums();
    }

    String transfer() {
        long decimal = transferIntoDecimalSystem();
        if(resultNumberSystem == 10)
            return Long.toString(decimal);
        if(resultNumberSystem > decimal)
            return Character.toString(rezNums.get(decimal));
        return transferIntoResultSystem();
    }

    private void fillingDecimalSystem() {
        for(char num : number.toCharArray())
            if(num < 65)
                decimalNumberSystem.put(num, num - '0');
            else
                decimalNumberSystem.put(num, num - 55);
    }

    private void fillingRezNums() {
        for(long i = 0; i < resultNumberSystem; i++)
            if(i < 10)
                rezNums.put(i, (char)(i + '0'));
            else {
                char symbol = (char)('A' + i - 10); //Для вычесления кода A, B, C...
                rezNums.put(i, symbol);
            }
    }

    private long transferIntoDecimalSystem() {
        long rez = 0L;
        long exponent = 1L;
        char[] numberCharArray = number.toCharArray();
        for (char num: numberCharArray) {
            int digit = decimalNumberSystem.get(num);
            if(digit == 0) {
                ++exponent;
                continue;
            }
            long pow = numberCharArray.length - exponent;
            rez += digit*Math.pow(originNumberSystem, pow);
            ++exponent;
        }
        return rez;
    }

    private String transferIntoResultSystem() {
        long inDecimal = transferIntoDecimalSystem();
        StringBuilder rezNumber = new StringBuilder();
        do {
            long temp = inDecimal;
            inDecimal = inDecimal/resultNumberSystem;
            rezNumber.append(rezNums.get(temp%resultNumberSystem));
        } while(inDecimal >= resultNumberSystem);
        rezNumber.append(rezNums.get(inDecimal%resultNumberSystem));
        return rezNumber.reverse().toString();
    }
}