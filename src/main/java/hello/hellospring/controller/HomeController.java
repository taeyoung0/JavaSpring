package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public  String home(){
        return  "home";
    }       // "home"이라는 이름의 뷰 템플릿을 찾고 렌더링
}
