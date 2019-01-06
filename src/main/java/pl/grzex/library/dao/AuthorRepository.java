package pl.grzex.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.grzex.library.models.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
