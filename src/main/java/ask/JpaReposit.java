package ask;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface JpaReposit extends JpaRepository<TestEntity, Long> {

    @Query(value = "SELECT * FROM test_entity", nativeQuery = true)
    List<TestEntity> jpafindAll();

    @Query(value = "SELECT * FROM test_entity WHERE id= :id", nativeQuery = true)
    TestEntity jpafindOne(Long id);

    @Query(value = "UPDATE test_entity SET title= :#{#target.title}, contents= :#{#target.contents} WHERE id= :#{#target.id}", nativeQuery = true)
    @Modifying
    @Transactional
    void jpaupdate(@Param("target")TestEntity testEntity);


    /*@Query(value = "SELECT * FROM comment WHERE nickname = :nickname", nativeQuery = true)
    List<Comment> findByNickname(@Param("nickname") String nickname);
*/
}
