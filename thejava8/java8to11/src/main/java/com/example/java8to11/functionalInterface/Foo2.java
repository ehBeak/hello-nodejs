package com.example.java8to11.functionalInterface;

import java.util.function.*;

// 자바에서 제공하는 함수 인터페이스
public class Foo2 {

    public static void main(String[] args) {

        /* 클래스에서 구현 -> 메인 메소드에서 사용 */
        Plus10 plus10 = new Plus10();
        System.out.println(plus10.apply(10));

        /* 인터페이스를 바로 메인 메소드에서 사용 */
        // 1. 클래스 구현
        Function<Integer, Integer> integerFunction =
                new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) {
                        return integer + 10;
                    }
                };
        System.out.println(integerFunction.apply(10));

        // 2. 람다식
        Function<Integer, Integer> integerFunction1 = (num) -> num + 10;
        System.out.println(integerFunction1.apply(10));

        /* 함수를 조합 - 입력값 1개 */

        Function<Integer, Integer> plus1 = (i) -> i + 1;
        Function<Integer, Integer> multiply2 = (i) -> i * 2;
        // 1. andThen(Function<? super R, ? extends V> after)
        Function<Integer, Integer> plus1AndMultiply2 = plus1.andThen(multiply2);// (i + 1) -> * 2
        plus1AndMultiply2.apply(2);

        // 2. compose(Function<? super V, ? extends T> before)
        Function<Integer, Integer> multiply2AndPlus1 = plus1.compose(multiply2);// (i * 2) -> + 1
        multiply2AndPlus1.apply(2);

        // 3. identity()

        /* Functional 외 */

        // 1. biFunction<T,U,R>
        // 2. Consumer<T>
        Consumer<Integer> printT = (i) -> System.out.println(i);
        printT.accept(10);
        // 3. Supplier<T>
        Supplier<Integer> get10 = () -> 10;
        System.out.println(get10);
        // 4. Predicate<T>
        Predicate<String> startWithKeesun = (s) -> s.startsWith("keesun");
        Predicate<Integer> isEven = (i) -> i % 2 == 0;
        // 5. UnaryOperator
        UnaryOperator<Integer> plus2 = (i) -> i + 2;
        // == Function<Integer, Integer> integerFunction1 = (num) -> num + 10;
        // 6. BinaryOperator<T> == biFunction<T,T,T>
    }
}
