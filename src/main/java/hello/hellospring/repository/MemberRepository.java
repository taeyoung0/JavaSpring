package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;


// 회원 리포지토리 인터페이스
public interface MemberRepository {

    // 회원 저장 메서드 선언
    Member save (Member member);

    // ID로 회원을 찾는 메서드 선언
    Optional<Member> findById(Long id);

    // 이름으로 회원을 찾는 메서드 선언
    Optional<Member> findByName(String name);

    // 모든 회원을 찾는 메서드 선언
    List<Member> findAll();


}
