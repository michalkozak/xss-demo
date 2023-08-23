package mk.backbase.xss.domain.repository;

import mk.backbase.xss.domain.model.Demo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends JpaRepository<Demo, Long> {

    Page<Demo> findAll(Pageable pageable);

}
