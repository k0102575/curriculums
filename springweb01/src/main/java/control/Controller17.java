/* Spring MVC : 같은 이름의 파리미터가 여러 개 넘어 올 때 */
package control;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/control/controller17/")

public class Controller17 {
  @Autowired ServletContext servletContext;

  // 1)
  @RequestMapping("ok1")
  @ResponseBody
  public String ok1(String[] name, int[] age) throws Exception {
    
    for (String str: name) {
      System.out.printf("name: %s\n", str);
    }
    
    for (int a : age) {
      System.out.printf("age: %d\n", a);
    }
    
    return "controller17_ok1";
  }
  
}
