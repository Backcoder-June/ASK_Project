package ask.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberDTO, Long> {
    @Query(value = "SELECT * FROM MEMEBER WHERE email= :#{#memberDTO.email}", nativeQuery = true)
    Optional<MemberDTO> findByEmail(String email);
}
