package com.company.task.book;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/books")
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookResponseDTO> getBooks() {
        log.info("Richiesta di recupero della lista dei libri.");
        return bookService.getBooks()
                .stream()
                .map(book -> new BookResponseDTO(book.getId(), book.getTitle(), book.getAuthor()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public BookResponseDTO createBook(@Valid @RequestBody BookRequestDTO requestDTO) {
        log.info("Richiesta di creazione nuovo libro ricevuta.");
        log.trace("Payload DTO ricevuto: {}", requestDTO);

        // Convert DTO to Entity
        Book bookEntity = new Book();
        bookEntity.setTitle(requestDTO.title());
        bookEntity.setAuthor(requestDTO.author());
        bookEntity.setPrice(requestDTO.price());
        // Default initial values for optional properties not present in this specific DTO
        bookEntity.setQuantity(0);

        Book savedBook = bookService.addNewBook(bookEntity);
        log.info("Libro creato con successo con ID: {}", savedBook.getId());

        // Return Response DTO
        return new BookResponseDTO(savedBook.getId(), savedBook.getTitle(), savedBook.getAuthor());
    }

    @DeleteMapping(path = "{bookId}")
    public void deleteBook(@PathVariable("bookId") Long bookId) {
        log.info("Richiesta di eliminazione per il libro con ID: {}", bookId);
        bookService.deleteBook(bookId);
    }

    @PutMapping(path = "{bookId}")
    public void updateBook(
            @PathVariable("bookId") Long bookId,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) Integer quantity) {
        log.info("Richiesta di aggiornamento per il libro con ID: {}", bookId);
        log.debug("Dati aggiornamento - Autore: {}, Prezzo: {}, Quantità: {}", author, price, quantity);

        bookService.updateBook(bookId, author, price, quantity);
    }
}