package ask.Board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardDTO, Long> {

    @Query(value = "SELECT * FROM BOARD", nativeQuery = true)
    List<BoardDTO> jpafindAll();

    @Query(value = "SELECT * FROM BOARD WHERE id= :id", nativeQuery = true)
    BoardDTO jpafindOne(Long id);

    @Query(value = "UPDATE BOARD SET title= :#{#target.title}, contents= :#{#target.contents} WHERE id= :#{#target.id}", nativeQuery = true)
    @Modifying
    @Transactional
    void jpaupdate(@Param("target") BoardDTO boardDTO);


    /*@Query(value = "SELECT * FROM comment WHERE nickname = :nickname", nativeQuery = true)
    List<Comment> findByNickname(@Param("nickname") String nickname);
*/
}
