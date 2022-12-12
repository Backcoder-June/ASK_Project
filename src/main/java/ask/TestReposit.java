package ask;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TestReposit extends CrudRepository<TestEntity, Long> {
    @Override
    List<TestEntity> findAll();
}
