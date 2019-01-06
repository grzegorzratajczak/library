package pl.grzex.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.grzex.library.dao.BookRepository;
import pl.grzex.library.models.Book;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryService {

    private BookRepository bookRepository;

    @Autowired
    public LibraryService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks(){
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(book -> books.add(book));
        return books;
    }

    public Book findBookById(int id){

        Book book = bookRepository.findById(id).get();
        return book;
    }

    public void saveBook(Book book){
        bookRepository.save(book);
    }

    public void deleteById(Integer id){
        bookRepository.deleteById(id);
    }

    public void deleteAll(){
        bookRepository.deleteAll();
    }
}
