package pl.grzex.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.grzex.library.models.Book;


@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

}
