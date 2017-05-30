/* JDBC 프로그래밍 II : DAO + Connection Pooling */

package step22.ex3;

import java.util.List;

public class Test01_1 {
  
  public static void main(String[] args) throws Exception {
    System.out.println("step22 ex3 Test01_1 실행중");System.out.println();
    
    String jdbcDriver = "com.mysql.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://localhost:3306/webappdb";
    String jdbcUsername = "webapp";
    String jdbcPassword = "1111";
    
    DBConnectionPool conPool = new DBConnectionPool(jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword);
    
    MemberDao memberDao = new MemberDao(conPool);
    
    List<Member> list = memberDao.selectList();
    
    for (Member m : list) {
      System.out.printf("%d, %s, %s, %s\n", m.getNo(), m.getName(), m.getTel(), m.getEmail());
    }
    System.out.println("--------------------------");
    
//    System.out.println(memberDao.delete(51));
//    
//    System.out.println("--------------------------");
//    
//    Member member = new Member();
//    member.setName("홍길동104");
//    member.setTel("1111-111");
//    member.setEmail("hong104@test.com");
//    member.setPassword("1111");
//    
//    System.out.println(memberDao.insert(member));
//    
//    System.out.println("--------------------------");
//    
//    list = memberDao.selectList();
//    
//    for (Member m : list) {
//      System.out.printf("%d, %s, %s, %s\n", m.getNo(), m.getName(), m.getTel(), m.getEmail());
//    }
    
//    Member member = new Member();
//    member.setName("홍길동변경2");
//    member.setTel("1111-111");
//    member.setEmail("hong104@test.com");
//    member.setPassword("1111");
//    member.setNo(53);
//    
//    System.out.println(memberDao.update(member));
//    
//    System.out.println("-----------------------");
//    
//    list = memberDao.selectList();
//    
//    for (Member m : list) {
//      System.out.printf("%d, %s, %s, %s\n", m.getNo(), m.getName(), m.getTel(), m.getEmail());
//    }
    
  } // main

}