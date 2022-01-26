package ch5;

public class OperatorCasting {

    public static void main(String[] args) {
        OperatorCasting operator = new OperatorCasting();
        operator.casting();
        operator.casting2();
    }

    public void casting() {
        byte byteVal = 127;
        short shortVal = byteVal; // upCasting
        shortVal++;

        System.out.println(shortVal); // 128 : 0000_0000_1000_0000
        byteVal = (byte) shortVal; // downCasting
        System.out.println(byteVal); // -128 : 1000_0000 => 앞에 1byte(8bit)를 그냥 버림
    }

    public void casting2() {
        short shortVal = 256;
        byte byteVal = (byte) shortVal;
        System.out.println(byteVal); // 0

        shortVal = 255;
        byteVal = (byte) shortVal;
        System.out.println(byteVal); // -1 ??

    }
}
