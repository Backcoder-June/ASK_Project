package ask.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberDTO, Long> {
    @Query(value = "SELECT * FROM MEMBER WHERE email= :#{#email}", nativeQuery = true)
    Optional<MemberDTO> findByEmail(@Param("email") String email);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO MEMBER (email, pw, nickname, role) " +
            "VALUES( :#{#MemberEntity.email}, :#{#MemberEntity.pw}, :#{#MemberEntity.nickname}, :#{#MemberEntity.role})", nativeQuery = true)
    void registerMember(@Param("MemberEntity") MemberDTO memberDTO);


    @Transactional
    @Modifying
    @Query(value = "UPDATE MEMBER SET nickname = :#{#nickname} WHERE id = :#{#id}", nativeQuery = true)
    void setNickname(@Param("nickname") String nickname, @Param("id") Long id);

    @Query(value = "SELECT NICKNAME FROM MEMBER WHERE nickname = :#{#nickname}", nativeQuery = true)
    Optional<String> findByName(@Param("nickname") String nickname);



}
