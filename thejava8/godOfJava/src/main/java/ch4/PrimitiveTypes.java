package ch4;

public class PrimitiveTypes {



    /* byte : -2^7 ~ 2^7-1 */
    public void checkByte() {
        byte byteMin = -128;
        byte byteMax = 127;
        System.out.println("byteMin == " + byteMin); // -128 1,0000000
        System.out.println("byteMax == " + byteMax); // 127  0,1111111

        // java8 : ()없으면 컴파일 오류
        byteMin = (byte) (byteMin - 1);
        byteMax = (byte) (byteMax + 1);

        // overflow
        System.out.println("byteMin - 1 == " + byteMin); // 127
        System.out.println("byteMax + 1 == " + byteMax); // -128

    }

    // 0 ~ 2^16
    public void checkChar() {
        char charMin = '\u0000'; // 16진수
        char charMax = '\uffff';
        int intVal = 'a'; // 형변환(char<int)

        System.out.println("charMin == [" + charMin + "]"); // 공백
        System.out.println("charMax == [" + charMax + "]"); // ?
        System.out.println("intVal == [" + intVal + "]"); // 97

    }


    public static void main(String[] args) {
        PrimitiveTypes primitiveTypes = new PrimitiveTypes();
        primitiveTypes.checkByte();

        primitiveTypes.checkChar();
    }
}
