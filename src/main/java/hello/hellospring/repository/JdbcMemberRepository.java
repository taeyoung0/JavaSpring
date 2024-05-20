package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;            // JDBC DataSource 클래스 임포트
import java.sql.*;      // SQL 관련 클래스 임포트
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// JDBC를 이용한 MemberRepository 구현 클래스
public class JdbcMemberRepository implements MemberRepository{

    private final DataSource dataSource;        // 데이터베이스 연결을 위한 DataSource


    // DataSource를 주입받는 생성자
    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 회원 저장 메서드 구현
    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)";  // 회원 이름을 저장하는 SQL 쿼리
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();     // 데이터베이스 연결
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);        // 자동 생성된 키 값을 반환하도록 설정
            pstmt.setString(1, member.getName());   // 첫 번째 파라미터로 회원 이름 설정
            pstmt.executeUpdate();      // SQL 쿼리 실행
            rs = pstmt.getGeneratedKeys();  // 생성된 키 값 반환
            if (rs.next()) {
                member.setId(rs.getLong(1));    // 반환된 키 값을 회원 ID로 설정
            } else {
                throw new SQLException("id 조회 실패"); // 키 값이 없으면 예외 발생
            }
            return member;  // 저장된 회원 반환
        } catch (Exception e) {
            throw new IllegalStateException(e); // 예외 발생 시 IllegalStateException으로 감싸서 던짐
        } finally {
            close(conn, pstmt, rs);     // 자원 해제
        }
    }

    // ID로 회원을 찾는 메서드 구현
    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id = ?";        // ID로 회원을 찾는 SQL 쿼리
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();     // 데이터베이스 연결
            pstmt = conn.prepareStatement(sql);         // SQL 쿼리 준비
            pstmt.setLong(1, id);       // 첫 번째 파라미터로 회원 ID 설정
            rs = pstmt.executeQuery();      // SQL 쿼리 실행

            if(rs.next()) {
                Member member = new Member();                   // 회원 객체 생성
                member.setId(rs.getLong("id"));     // 회원 ID 설정
                member.setName(rs.getString("name"));       // 회원 이름 설정
                return Optional.of(member);         // Optional에 담아 반환
            } else {
                return Optional.empty();             // 회원이 없으면 빈 Optional 반환
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    // 모든 회원을 찾는 메서드 구현
    @Override
    public List<Member> findAll() {
        String sql = "select * from member";            // 모든 회원을 찾는 SQL 쿼리
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();              // 데이터베이스 연결
            pstmt = conn.prepareStatement(sql);     // SQL 쿼리 준비
            rs = pstmt.executeQuery();                  // SQL 쿼리 실행
            List<Member> members = new ArrayList<>();           // 회원 목록을 담을 리스트 생성

            while(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);                // 리스트에 회원 추가
            }
            return members;         // 모든 회원 반환
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }


    // 이름으로 회원을 찾는 메서드 구현
    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name = ?";     // 이름으로 회원을 찾는 SQL 쿼리
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);           // 첫 번째 파라미터로 회원 이름 설정
            rs = pstmt.executeQuery();                  // SQL 쿼리 실행

            if(rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);             // Optional에 담아 반환
            }
            return Optional.empty();                // 회원이 없으면 빈 Optional 반환

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    // 데이터베이스 연결을 가져오는 유틸리티 메서드
    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    // 데이터베이스 자원을 해제하는 유틸리티 메서드
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs)
    {
        try {
            if (rs != null) {
                rs.close();     // ResultSet 닫기
            }
        } catch (SQLException e) {
            e.printStackTrace();        // 예외 발생 시 스택 트레이스 출력
        }
        try {
            if (pstmt != null) {
                pstmt.close();          // PreparedStatement 닫기
            }
        } catch (SQLException e) {
            e.printStackTrace();        // 예외 발생 시 스택 트레이스 출력
        }
        try {
            if (conn != null) {
                close(conn);        // Connection 닫기
            }
        } catch (SQLException e) {
            e.printStackTrace();            // 예외 발생 시 스택 트레이스 출력
        }
    }

    // 데이터베이스 연결을 해제하는 유틸리티 메서드
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}