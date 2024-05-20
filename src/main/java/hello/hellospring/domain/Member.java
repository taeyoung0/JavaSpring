package hello.hellospring.domain;

// 회원 정보를 저장하는 도메인 클래스
public class Member {
    private Long id;    // 회원 ID를 저장할 필드
    private String name;     // 회원 이름을 저장할 필드

    // id 필드의 값을 반환하는 getter 메서드
    public Long getId() {
        return id;
    }

    // id 필드의 값을 설정하는 setter 메서드
    public void setId(Long id) {
        this.id = id;
    }

    // name 필드의 값을 반환하는 getter 메서드
    public String getName() {
        return name;
    }

    // name 필드의 값을 설정하는 setter 메서드
    public void setName(String name) {
        this.name = name;
    }
}
