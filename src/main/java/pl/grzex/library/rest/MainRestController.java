package pl.grzex.library.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.grzex.library.models.Author;
import pl.grzex.library.models.Book;
import pl.grzex.library.services.AuthorService;
import pl.grzex.library.services.LibraryService;

import java.util.List;

@Controller
public class MainRestController {

    LibraryService libraryService;
    AuthorService authorService;

    @Autowired
    public MainRestController(LibraryService libraryService, AuthorService authorService) {
        this.libraryService = libraryService;
        this.authorService = authorService;
    }

    @RequestMapping(value = {"/hello"})
    @ResponseBody
    public String sayHello() {
        return "Hello";
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> getBooks() {
        return libraryService.findAllBooks();
    }

    @RequestMapping(value = "/bookspage", method = RequestMethod.GET)
    public String getBooksPage(Model model) {
        List<Book> list = libraryService.findAllBooks();
        model.addAttribute("books", list);
        return "table";
    }

    @RequestMapping(value = "/authorsPage", method = RequestMethod.GET)
    public String getAuthorsPage(Model model) {
        List<Author> list = authorService.findAllAuthors();
        model.addAttribute("authors", list);
        return "tableauthors";
    }

    @RequestMapping(value = "/deletebook", method = RequestMethod.GET)
    public String deleteBook(@RequestParam(required = true) Long id, Model model) {
        libraryService.deleteById(id);
        model.addAttribute("books", libraryService.findAllBooks());
        return "table";
    }

    @RequestMapping(value = "/deleteauthor", method = RequestMethod.GET)
    public String deleteAuthor(@RequestParam(required = true) Long id, Model model) {
        authorService.deleteById(id);
        model.addAttribute("authors", authorService.findAllAuthors());
        return "tableauthors";
    }

    @RequestMapping(value = "/insertBookPage", method = RequestMethod.GET)
    public String insertBook() {
        return "insertform";
    }

    @RequestMapping(value = "/insertAuthorPage", method = RequestMethod.GET)
    public String insertAuthor() {
        return "insertauthorform";
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
    public String addBookToDB(@ModelAttribute Book book, Model model) {
        libraryService.saveBook(book);
        model.addAttribute("books", libraryService.findAllBooks());
        return "table";
    }

    @RequestMapping(value = "/addauthor", method = RequestMethod.POST)
    public String addAuthorToDB(@ModelAttribute Author author, Model model) {
        authorService.saveAuthor(author);
        model.addAttribute("authors", authorService.findAllAuthors());
        return "tableauthors";
    }

    @RequestMapping(value = "/updatebook", method = RequestMethod.GET)
    public String updateBook(@RequestParam Long id, Model model) {
        Book book = libraryService.findBookById(id);
        model.addAttribute("book", book);
        return "updateform";
    }

    @RequestMapping(value = "/updateauthor", method = RequestMethod.GET)
    public String updateAuthor(@RequestParam Long id, Model model) {
        Author author = authorService.findAuthorById(id);
        model.addAttribute("author", author);
        return "updateauthorform";
    }

    @RequestMapping(value = "/updateExistBook", method = RequestMethod.POST)
    public String updateBook(@ModelAttribute Book book, Model model) {
        libraryService.saveBook(book);
        List<Book> books = libraryService.findAllBooks();
        model.addAttribute("books", books);
        return "table";
    }

    @RequestMapping(value = "/updateExistAuthor", method = RequestMethod.POST)
    public String updateAuthor(@ModelAttribute Author author, Model model) {
        authorService.saveAuthor(author);
        List<Author> authors = authorService.findAllAuthors();
        model.addAttribute("authors", authors);
        return "tableauthors";
    }

}
