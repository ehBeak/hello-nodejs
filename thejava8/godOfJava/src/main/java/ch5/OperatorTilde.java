package ch5;

/* ~ : 2진수로 되어있는 비트값을 전부 거꾸로 바꾸는데 이용 */
public class OperatorTilde {
    public static void main(String[] args) {
        OperatorTilde operator = new OperatorTilde();
        byte b = 127;
        operator.printTildeRes(b);
        b = 1;
        operator.printTildeRes(b);
    }

    public void printTildeRes(byte b) {
        System.out.println("original value == " + b);
        System.out.println("tilde value == " + ~b);//??
    }
}
