package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import app.entity.Task;

//@RepositoryRestResource(path = "public/taasks")
@RepositoryRestResource()
public interface TaskRestRepository extends JpaRepository<Task, Long> {

}
