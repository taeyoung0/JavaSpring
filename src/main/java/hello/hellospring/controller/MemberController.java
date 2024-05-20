package hello.hellospring.controller;

import hello.hellospring.domain.Member;         // Member 도메인 클래스 임포트
import hello.hellospring.service.MemberService;     // MemberService 클래스 임포트
import org.springframework.beans.factory.annotation.Autowired;       // 의존성 주입을 위한 Autowired 어노테이션 임포트
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;      // List 인터페이스 임포트

@Controller
public class MemberController {

    private MemberService memberService;        // MemberService를 주입받기 위한 변수

    //  setter 주입
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }
    @Autowired           // 생성자 주입을 통해 MemberService 인스턴스를 주입받음
    public MemberController(MemberService memberService){

        this.memberService = memberService;
    }

    // "/members/new" 경로로 GET 요청이 오면 실행, 회원 생성 폼을 보여줌
    @GetMapping("/members/new")
    public  String CreateForm(){

        return "members/CreateMemberForm";      // 회원 생성 폼 뷰를 반환
    }
    // "/members/new" 경로로 POST 요청이 오면 실행, 회원을 생성함
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();            // 새로운 Member 객체 생성
        member.setName(form.getName());     // Member 객체에 폼에서 받은 이름 설정

        memberService.join(member);     // MemberService를 통해 회원 가입
        return "redirect:/";            // 루트 경로로 리다이렉트
    }

    // "/members" 경로로 GET 요청이 오면 실행, 회원 목록을 보여줌
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();     // 모든 회원을 조회
        model.addAttribute( "members", members);     // 모델에 회원 목록을 추가
        return "members/memberList";        // 회원 목록 뷰를 반환
    }
}
