package hello.hellospring;		// hello.hellospring 패키지에 속한 클래스를 선언

import org.springframework.boot.SpringApplication;			// 스프링 부트의 애플리케이션을 실행하는 기능을 사용하기 위한 import문
import org.springframework.boot.autoconfigure.SpringBootApplication;			// 스프링 부트의 자동 설정을 활성화하는 어노테이션을 사용

@SpringBootApplication
public class HelloSpringApplication {		// HelloSpringApplication 클래스 선언

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}
	// 스프링 부트 애플리케이션을 실행
}
