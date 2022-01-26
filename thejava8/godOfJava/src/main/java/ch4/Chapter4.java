package ch4;

public class Chapter4 {
    /* 자바의 변수 : 생명주기의 차이 */
    int instanceVal;// 인스턴스 변수 : 멤버변수(객체 생성과 소멸)
    static int classVal;// 클래스 변수 : 멤버변수(클래스의 생성과 프로그램의 끝)

    public void method(int parameter) {// 매개 변수 : 메소드에 넘겨주는 변수(메소드 호출과 끝)
        int localVal;// 지역 변수 : 중괄호 내에서 선언된 변수(중괄호 내)
    }

    /* 지역 변수의 범위 */

    /* 자바의 두 가지 자료형 */
    // 1. 기본 자료형 : new(X), 정수형(byte/short/int/long/char), 소수형(float/double), boolean
    // 2. 참조 자료형 : new(O)
    // 예외) String

    /* 지역변수는 반드시 초기화 해야한다.(기본 값이 자동으로 적용되지 않는다.)*/

    int intDefault1; // 인스턴스 변수

    public void defaultVal() {
        int intDefault2; // 지역변수(초기화 필수)
        System.out.println(intDefault1);
        //System.out.println(intDefault2); // 컴파일 오류
        //==> 지역 변수를 초기화 하지 않으면 지역변수를 사용할 때 컴파일 오류 발생
    }

}
