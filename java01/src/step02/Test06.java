/* 변수 선언: 배열 변수 */
package step02;

public class Test06 {
  public static void main(String[] args) {
    //1) 배열 레퍼런스 변수 선언하기
    byte[] arr1; // 자바 언어에서 주로 사용하는 스타일
    //byte arr1[]; //C언에서 주로 사용하는 스타일도 지원한다.
    
    /* 설명
     * => byte[] arr1
     *    - byte 배열의 주소를 저장할 변수를 선언한다.
     *    - '레퍼런스 변수' 또는 줄여서 '레퍼런스'라고 부른다.
     *    - 일반적인 값을 저장할 수 없다. 
     */
    //arr1 = 10; // 일반적인 값 저장할 수 없다. 컴파일 오류!
    //arr1[0] = 10; // arr1 변수의 값을 초기화시키지도 않고 사용하면, 컴파일 오류!
    /* 주의!
     * => 자바에서 모든 종류의 변수에 대해 값을 초기화시키지 않고 사용하려 하면,
     *    컴파일 오류가 발생한다. 
     */
    
    arr1 = null; // null은 일반 변수에 사용할 수 없다. 오직 레퍼런스 변수에만 사용할 수 있다.
    //int a = null; // 컴파일 오류!
    // arr1에 명확하게 값을 초기화시켰지만, 그 값은 주소 없음을 의미한다. 

    //arr1[0] = 10; // null 값이라도 일단 변수의 값은 초기화된 것이기 때문에
                  // 컴파일 오류는 발생하지 않는다.
                  // 다만 실행할 때 arr1 변수에 주소가 없기 때문에 
                  // 실행 오류(Runtime Exception)가 발생한다!
                  // 특히 이렇게 주소를 주지 않고 레퍼런스 변수를 사용하려 하면,
                  // NullPointerException 오류가 발생한다.
    
    //2) 배열을 생성하여 그 주소를 레퍼런스에 담기
    arr1 = new byte[3];
    /* 설명
     * => new byte[3]
     *    - byte 타입의 메모리를 연속하여 3개 준비한다.
     *    - 그리고 시작 메모리의 주소를 리턴한다.    
     */
    
    //3) 배열에 값 담기
    arr1[0] = 10; // 메모리의 순서는 0부터 시작한다.
    arr1[1] = 20;
    arr1[2] = 30;
    
    System.out.printf("%d, %d, %d\n", arr1[0], arr1[1], arr1[2]);
   
    arr1[3] = 40; // 존재하지 않는 메모리의 인덱스를 가리키더라도 컴파일 오류는 발생하지 않는다.
      // 그러나 실행할 때 그 인덱스에 해당하는 메모리가 없기 때문에 실행 오류가 발생한다!
  }

}

/* 배열 변수
 * => 같은 타입의 메모리를 연속적으로 만들 때 사용하는 문법
 * 
 */
