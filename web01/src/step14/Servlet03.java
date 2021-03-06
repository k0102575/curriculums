/* 파일 업로드 : apache file-upload 라이브러리를 사용하여 파일 업로드 처리
 * => 라이브러리 준비 작업
 *    1) mvnrepository.com에서 "apache fileupload" 라이브러리 검색
 *    2) build.gradle 파일에 의존 라이브러리 정보를 추가한다.
 *    3) "gradle eclipse"를 실행하여 이클립스 설정 파일을 갱신한다.
 *    4) 이클립스 프로젝트 폴더를 "refresh" 한다.
 *    5) "Referenced Libraries" 노드를 펼쳐서 file-upload 관련 라이브러리가 추가되었는지 확인한다.
 */
package step14;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/step14/Servlet03")
@SuppressWarnings("serial")
public class Servlet03 extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // 멀티파트로 받은 데이터는 아래와 같이 인코딩을 설정해봐야 소용없다.
    //req.setCharacterEncoding("UTF-8");
    
    // 멀티파트로 받은 데이터 처리하기
    // 1) 각 파트에 데이터를 가공할 객체 준비
    DiskFileItemFactory itemFactory = new DiskFileItemFactory();
    
    // 2) 멀티파트로 들어 데이터를 분석하는 객체 준비
    ServletFileUpload multipartDataParser = new ServletFileUpload(itemFactory);
    
    try {
      // 3) 멀티파트 데이터 파서기를 이용하여 요청 데이터 처리하기
      // => itemFactory 객체가 가공한 멀티파트 데이터를 List로 받는다.
      // => 각각의 파트 데이터는 FileItem 객체에 담겨있다.
      List<FileItem> parts = multipartDataParser.parseRequest(req);
    
      // 안타깝게도 특정한 것만 쏙 뽑아낼 수 없다. 
      // 무조건 반복문을 돌려서 찾아야 한다.
      // 이를 좀 편하게 하기 위해 List에 저장된 객체를 맵으로 옮긴다.
      HashMap<String,FileItem> partMap = new HashMap<>();
      for (FileItem part : parts) {
        partMap.put(part.getFieldName(), part);
      }
      
      FileItem part = partMap.get("name");
      
      // 파라미터의 값 꺼내기
      // FileItem에서 UTF-8로 인코딩 된 값을 꺼낼 때는 때 인코딩을 지정해야 한다.
      String name = part.getString("UTF-8"); 
      
      // 파일인 경우, 원래의 이름을 꺼내려면 getName()을 호출해야 한다.
      String file1 = partMap.get("file1").getName();
      String file2 = partMap.get("file2").getName();
      
      resp.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = resp.getWriter();
      out.printf("name=%s\n", name);
      out.printf("file1=%s\n", file1);
      out.printf("file2=%s\n", file2);

    } catch (Exception e) {
      throw new ServletException(e);
    }
    
  }
  
}

/* HTTP POST 요청 프로토콜
POST /web01-bit/step14/Servlet02 HTTP/1.1
Host: localhost:8080
Content-Length: 13653
Pragma: no-cache
Cache-Control: no-cache
Origin: http://localhost:8080
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.86 Safari/537.36
Content-Type: multipart/form-data; boundary=----WebKitFormBoundary4lpFkjYXAQS2YzjE
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,...
Referer: http://localhost:8080/web01-bit/step14/form2.html
Accept-Encoding: gzip, deflate, br
Accept-Language: ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4

------WebKitFormBoundary4lpFkjYXAQS2YzjE
Content-Disposition: form-data; name="name"

홍길동
------WebKitFormBoundary4lpFkjYXAQS2YzjE
Content-Disposition: form-data; name="file1"; filename="images.jpeg"
Content-Type: image/jpeg

���� JFIF      �� �  ( %!1!%)+...383-7(-....
...
------WebKitFormBoundary4lpFkjYXAQS2YzjE
Content-Disposition: form-data; name="file2"; filename="img01.jpeg"
Content-Type: image/jpeg

���� JFIF      �� �  ( %!1!%)+...383-7(-.+...
..
------WebKitFormBoundary4lpFkjYXAQS2YzjE--

 */










