package hello.hellospring.controller;

public class MemberForm {
    private String name;    // 회원 가입 폼 데이터를 담기 위한 클래스

    // name 필드의 값을 반환하는 getter 메서드
    public String getName() {
        return name;
    }

    // name 필드의 값을 설정하는 setter 메서드
    public void setName(String name) {
        this.name = name;
    }
}
