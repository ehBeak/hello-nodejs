package com.example.java8to11.functionalInterface;

import java.util.function.Function;

public class Foo {

    public static void main(String[] args) {
        /* [1] 추상 메소드 정의 */

        /* 1. 익명 내부 크래스 */
        RunSomething runSomething = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("Hello");
            }
        };

        /* 2. 익명 내부 클래스 -> 람다 */
        RunSomething runSomething1 = () -> System.out.println("Lamda"); // 특수한 형태의 오브젝트

        /* 3. 반환값이 있는 함수형 인터페이스
        *     -> 입력값이 같으면 출력값이 달라지는 여지 만들지 않기.
        *     -> 여지 : 1) 함수의 바깥의 값을 참조하여 쓰는 경우
        *              2) 외부의 값을 변경하는 경우 */
        ReturnSomething returnSomething = (num) -> {
            return num + 10;
        };

        ReturnSomething returnSomething1 = (num) -> num + 10;

        // 1) 함수의 바깥의 값을 참조하여 쓰는 경우
        ReturnSomething returnSomethingX = new ReturnSomething() {
            int baseNumber = 10;

            @Override
            public int doIt(int num) {
                return num + baseNumber;
            }
        };
        // 2) 외부의 값을 변경하는 경우
        ReturnSomething returnSomethingX2 = new ReturnSomething() {
            int baseNumber = 10;
            @Override
            public int doIt(int num) {
                baseNumber++;
                return baseNumber + num;
            }
        };

    }
}
