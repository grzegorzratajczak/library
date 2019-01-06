package pl.grzex.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.grzex.library.dao.AuthorRepository;
import pl.grzex.library.models.Author;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> findAllAuthors(){
        List<Author> authors = new ArrayList<>();
        authorRepository.findAll().forEach(author -> authors.add(author));
        return authors;
    }

    public Author findAuthorById(Long id){

        Author author = authorRepository.findById(id).get();
        return author;
    }

    public void saveAuthor(Author author){
        authorRepository.save(author);
    }

    public void deleteById(Long id){
        authorRepository.deleteById(id);
    }

    public void deleteAll(){
        authorRepository.deleteAll();
    }
}
