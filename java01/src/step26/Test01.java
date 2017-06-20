package step26;

public class Test01 {
  public static void main(String[] args) throws Exception {
    
    // 1) 인스턴스를 통해서 얻기
    String obj = new String("Hello");
    Class<?> c1 = obj.getClass();
    // 어떤 종류의 클래스든 다룰수있다.
    
    // 2) Class.forName() 메서드를 통해서 얻기
    Class<?> c2 = Class.forName("java.lang.String");
    
    // 3) 모든 클래스에 내장된 class 스태틱 변수를 통해서 얻기
    Class<?> c3 = String.class;
  }
}