package ch5;

/* 비교 연산자 : 같은 종류끼리 비교연산자 적용 가능*/
public class OperatorComparison {
    public static void main(String[] args) {
        char charVal = 'a';
        int intVal = 97;
        long logVal = 97L;

        System.out.println("charVal == intVal : " + (charVal == intVal));
        System.out.println("longVal == intVal : " + (logVal == intVal));


        char charVal2 = '\u0000';
        int intVal2 = 0;
        double doubleVal = 0.0;
        boolean bool = false;
        System.out.println("charVal2 == intVal2 : " + (charVal2 == intVal2));
        System.out.println("doubleVal == intVal2 : " + (doubleVal == intVal2));
        //System.out.println("intVal2 == bool : " + (intVal2 == bool)); // 컴파일 오류

    }
}
