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

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO BOARD (title, contents, userid, category, donation, asktime) " +
            "VALUES (:#{#oneBoard.title}, :#{#oneBoard.contents}, :#{#oneBoard.userid}, :#{#oneBoard.category}, :#{#oneBoard.donation}, :#{#oneBoard.asktime})", nativeQuery = true)
    void saveBaord(@Param("oneBoard")BoardDTO oneBoard);

    @Query(value = "SELECT * FROM BOARD ORDER BY id desc", nativeQuery = true)
    List<BoardDTO> jpafindAll();

    @Query(value = "SELECT * FROM BOARD WHERE category= :#{#category}", nativeQuery = true)
    List<BoardDTO> findByCategory(@Param("category") String category);

    @Query(value = "SELECT * FROM BOARD WHERE id= :id", nativeQuery = true)
    BoardDTO jpafindOne(Long id);

    @Query(value = "UPDATE BOARD SET title= :#{#target.title}, contents= :#{#target.contents}, " +
            "category= :#{#target.category}, donation= :#{#target.donation}, asktime= :#{#target.asktime} WHERE id= :#{#target.id}", nativeQuery = true)
    @Modifying
    @Transactional
    void jpaupdate(@Param("target") BoardDTO boardDTO);

//    @Query(value = "SELECT * FROM BOARD WHERE :${searchOption} LIKE :#{#searchKeyword}", nativeQuery = true)

    List<BoardDTO> findBoardDTOByTitleContainingIgnoreCase(@Param("searchKeyword") String searchKeyword);

    List<BoardDTO> findBoardDTOByUseridContainingIgnoreCase(@Param("searchKeyword") String searchKeyword);






    /*@Query(value = "SELECT * FROM comment WHERE nickname = :nickname", nativeQuery = true)
    List<Comment> findByNickname(@Param("nickname") String nickname);
*/
}
