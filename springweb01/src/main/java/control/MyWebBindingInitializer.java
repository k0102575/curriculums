// 요청 파타미터를 요청 핸들러의 아규먼트 타입으로 바꿔주는 변환기 등록하기
package control;

import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class MyWebBindingInitializer implements WebBindingInitializer{

  @Override
  public void initBinder(WebDataBinder binder, WebRequest request) {
    System.out.println("MyWebBindingInitializer.initBinder()...");

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    dateFormat.setLenient(false);
    binder.registerCustomEditor(java.util.Date.class,new CustomDateEditor(dateFormat,false));
    
  }
}
