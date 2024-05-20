package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 회원 관리를 담당하는 서비스 클래스
public class MemberService {
    private final MemberRepository memberRepository;        // 회원 저장소를 위한 멤버 변수


    // 생성자를 통해 MemberRepository를 주입받음
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){

        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);  // 회원 저장소에 회원 정보 저장
        return member.getId();      // 저장된 회원의 ID 반환
    }

    // 중복 회원 검증 메서드
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())       // 회원 저장소에서 이름으로 회원을 찾음
                .ifPresent(m -> {       // 만약 회원이 존재하면
                    throw new IllegalStateException("이미 존재하는 회원입니다.");      // 예외를 발생시킴
                });
    }

    /**
     *  전체 회원 조회
     */
    public List<Member> findMembers(){
       return memberRepository.findAll();       // 회원 저장소에서 모든 회원을 조회하여 반환
    }

    public Optional<Member> findOne(Long memberId){
        return  memberRepository.findById(memberId);        // 회원 저장소에서 해당 ID에 해당하는 회원을 조회하여 반환
    }


}
