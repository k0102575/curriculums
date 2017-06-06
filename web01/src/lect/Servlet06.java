/* 회원 관리 만들기 : 회원 삭제하기 */
package lect;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns="/lect/Servlet06")
public class Servlet06 extends GenericServlet {

  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강의관리</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>강의 삭제</h1>");

    String jdbcDriver = "com.mysql.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://localhost:3306/webappdb";
    String jdbcUsername = "webapp";
    String jdbcPassword = "1111";

    try {
      DBConnectionPool conPool = new DBConnectionPool(jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword);

      LectDao lectDao = new LectDao(conPool);

      int no = Integer.parseInt(req.getParameter("no"));

      int count = lectDao.delete(no);
      if (count < 1) {
        throw new Exception(no + "번 회원을 찾지 못했습니다.");
      }
      out.println("<p>삭제 성공 입니다.</p>");
      out.println("<a href='Servlet02'>목록</a>");
    } catch (Exception e) {
      out.print("오류 발생!");
      // pre 태그를 주면은 엔터를 그래도 살린다.
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
      out.println("<a href='Servlet02'>목록</a>");
    }

    out.println("</body>");
    out.println("</html>");

    System.out.printf("RemoteAddress: %s\n", req.getRemoteAddr());

  } // service()

}