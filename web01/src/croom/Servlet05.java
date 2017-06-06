/* 회원 관리 만들기 : 회원 등록하기 */
package croom;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns="/croom/Servlet05")
public class Servlet05 extends GenericServlet {

  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    Croom c = new Croom();
    
    c.setNo(Integer.parseInt(req.getParameter("no")));
    c.setName(req.getParameter("name"));
    
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>교실관리</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>교실 변경</h1>");

    String jdbcDriver = "com.mysql.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://localhost:3306/webappdb";
    String jdbcUsername = "webapp";
    String jdbcPassword = "1111";

    try {
      DBConnectionPool conPool = new DBConnectionPool(jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword);

      CroomDao croomDao = new CroomDao(conPool);


      int count = croomDao.update(c);
      if (count < 1) {
        throw new Exception(c.getNo() + "번 회원을 찾지 못했습니다.");
      }
      out.println("<p>변경 성공 입니다.</p>");
      out.println("<a href='Servlet02'>목록</a>");
    } catch (Exception e) {
      out.print("오류 발생!");
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