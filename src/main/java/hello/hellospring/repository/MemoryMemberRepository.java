package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// 메모리 기반 회원 리포지토리 구현 클래스
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store =new HashMap<>();        // 회원 정보를 저장할 맵
    private static long sequence = 0L;          // 회원 ID 생성을 위한 시퀀스

    // 회원을 저장하는 메서드 구현
    @Override
    public Member save(Member member) {
        member.setId(++sequence);           // 회원 ID를 시퀀스에서 증가시키며 설정
        store.put(member.getId(), member);          // 맵에 회원 정보를 저장
        return member;
    }

    // ID로 회원을 찾는 메서드 구현
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));      // ID에 해당하는 회원을 Optional로 감싸서 반환
    }

    // 이름으로 회원을 찾는 메서드
    @Override
    public Optional<Member> findByName(String name) {           // 맵에서 값들을 순회하며 이름이 일치하는 회원을 찾아 Optional로 반환
        return store.values(). stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    // 모든 회원을 리스트로 반환하는 메서드
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());     // 맵의 값들을 새로운 리스트로 복사하여 반환
    }
    // 저장된 모든 회원 정보를 삭제하는 메서드
    public void clearStore(){
        store.clear();      // 맵의 모든 요소를 삭제
    }
}
