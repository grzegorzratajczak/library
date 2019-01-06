package pl.grzex.library.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.grzex.library.models.Book;
import pl.grzex.library.services.LibraryService;

import java.util.List;

@Controller
public class MainRestController {

    LibraryService libraryService;

    @Autowired
    public MainRestController(LibraryService libraryService){
        this.libraryService = libraryService;
    }

    @RequestMapping(value = {"/hello"})
    @ResponseBody
    public String sayHello(){
        return "Hello";
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> getBooks(){
        return libraryService.findAllBooks();
    }

    @RequestMapping(value = "/bookspage", method = RequestMethod.GET)
    public String getBooksPage(Model model){
        List<Book> list = libraryService.findAllBooks();
        model.addAttribute("books", list);
        return "table";
    }

    @RequestMapping(value = "/deletebook", method = RequestMethod.GET)
    public String deleteBooks(@RequestParam(required = true) Integer id, Model model){
        libraryService.deleteById(id);
        model.addAttribute("books", libraryService.findAllBooks());
        return "table";
    }

    @RequestMapping(value = "/insertBookPage", method = RequestMethod.GET)
    public String insertBook(){
        return "insertform";
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
    public String addBookToDB(@ModelAttribute Book book, Model model){
        libraryService.saveBook(book);
        model.addAttribute("books", libraryService.findAllBooks());
        return "table";
    }

    @RequestMapping(value = "/updatebook", method = RequestMethod.GET)
    public String updateBook(@RequestParam int id, Model model){
        Book book = libraryService.findBookById(id);
        model.addAttribute("book", book);
        return "updateform";
    }

    @RequestMapping(value = "/updateExistBook", method = RequestMethod.POST)
    public String updateBook(@ModelAttribute Book book, Model model){
        libraryService.saveBook(book);
        List<Book> books = libraryService.findAllBooks();
        model.addAttribute("books",books);
        return "table";
    }


}
