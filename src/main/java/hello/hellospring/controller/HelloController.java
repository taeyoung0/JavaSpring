package hello.hellospring.controller;
import org.springframework.stereotype.Controller;   // Spring 프레임워크의 Controller 어노테이션을 임포트
import org.springframework.ui.Model;                    // 데이터를 뷰로 전달하기 위한 Model 인터페이스를 임포트
import  org.springframework.web.bind.annotation.GetMapping;         // GET 요청을 처리하기 위한 GetMapping 어노테이션을 임포트
import org.springframework.web.bind.annotation.RequestParam;        // 요청 파라미터를 추출하기 위한 RequestParam 어노테이션을 임포트
import org.springframework.web.bind.annotation.ResponseBody;        // 응답 본문을 직접 전송하기 위한 ResponseBody 어노테이션을 임포트

@Controller         // 이 클래스를 Spring MVC 컨트롤러로 표시하는 어노테이션
public class HelloController {

    @GetMapping("hello")                // /hello 경로에 대한 HTTP GET 요청을 처리
    public String hello(Model model) {
        model.addAttribute("data", "spring" );       // 모델에 "data"라는 이름으로 "spring" 값을 추가
        return "hello";     // "hello"라는 이름의 뷰를 반환
    }

    @GetMapping("hello-mvc")            // /hello-mvc 경로에 대한 HTTP GET 요청을 처리
    public String helloMvc  (@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);        // 모델에 "name"이라는 이름으로 요청 파라미터 값을 추가
        return "hello-template";                // "hello-template"라는 이름의 뷰를 반환
    }

    @GetMapping("hello-string")         // /hello-string 경로에 대한 HTTP GET 요청을 처리
    @ResponseBody                   // 반환 값을 HTTP 응답 본문으로 전송
    public String helloString(@RequestParam("name") String name){
        return  "hello "+ name;             // "hello " 문자열과 요청 파라미터 값을 결합하여 반환
    }

    @GetMapping("hello-api")
    @ResponseBody
    public  Hello helloApi(@RequestParam("name")String name){
        Hello hello = new Hello();          // Hello 객체 생성
        hello.setName(name);            // Hello 객체의 name 속성을 요청 파라미터 값으로 설정
        return hello;               // Hello 객체를 JSON 형태로 반환
    }

    static class Hello{         // 내부 정적 클래스 정의
        private  String name;           // name 속성 정의

        public  String getName(){       // name 속성의 getter 메서드
        return   name;
        }

        public void setName(String name){       // name 속성의 setter 메서드
            this.name = name;
        }

    }
}

