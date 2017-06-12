/* 회원 관리 만들기 : 회원 등록하기 */
package bitcamp.java93.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java93.dao.LectDao;
import bitcamp.java93.domain.Lect;

@WebServlet(urlPatterns="/lect/update")
public class LectUpdateServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    Lect l = new Lect();
    
    l.setNo(Integer.parseInt(req.getParameter("no")));
    l.setTitl(req.getParameter("titl"));
    l.setDscp(req.getParameter("dscp"));
    l.setSdt(req.getParameter("sdt"));
    l.setEdt(req.getParameter("edt"));
    l.setQty(Integer.parseInt(req.getParameter("qty")));
    l.setPric(Integer.parseInt(req.getParameter("pric")));
    l.setThrs(Integer.parseInt(req.getParameter("thrs")));
    l.setCrmno(Integer.parseInt(req.getParameter("crmno")));
    l.setMrno(Integer.parseInt(req.getParameter("mrno")));
    
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강의관리</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>강의 변경</h1>");


    try {
      LectDao lectDao = (LectDao) this.getServletContext().getAttribute("lectDao");


      int count = lectDao.update(l);
      if (count < 1) {
        throw new Exception(l.getNo() + "번 회원을 찾지 못했습니다.");
      }
      out.println("<p>변경 성공 입니다.</p>");
      res.setHeader("Refresh", "1;url=list");
      
    } catch (Exception e) {
      out.print("오류 발생!");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
      out.println("<a href='list'>목록</a>");
    }

    out.println("</body>");
    out.println("</html>");


  } // service()

}