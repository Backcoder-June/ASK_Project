package ask.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberDTO, Long> {
    @Query(value = "SELECT * FROM MEMBER WHERE email= :#{#email}", nativeQuery = true)
    Optional<MemberDTO> findByEmail(@Param("email") String email);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO MEMBER (email, pw, nickname) " +
            "VALUES( :#{#MemberEntity.email}, :#{#MemberEntity.pw}, :#{#MemberEntity.nickname})", nativeQuery = true)
    void registerMember(@Param("MemberEntity") MemberDTO memberDTO);



}
