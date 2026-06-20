package com.company.task.book;

import com.company.task.exceptions.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book addNewBook(Book book) {
        Optional<Book> bookByTitle = bookRepository.findBookByTitle(book.getTitle());
        if(bookByTitle.isPresent()) {
            throw new BookAlreadyExistsException("Book already exists with title: " + book.getTitle());
        }
        return bookRepository.save(book);
    }

    public void deleteBook(long bookId) {
        boolean exists = bookRepository.existsById(bookId);
        if (!exists) {
            throw new BookDoesNotExistException("Book with id " + bookId + " does not exist");
        }
        bookRepository.deleteById(bookId);
    }

    @Transactional
    public void updateBook(long bookId, String author, Double price, Integer quantity) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookDoesNotExistException("Book with id " + bookId + " does not exist"));

        if (author != null && !author.isEmpty() && !author.equals(book.getAuthor())) {
            book.setAuthor(author);
        }

        if (price != null && price > 0 && !price.equals(book.getPrice())) {
            book.setPrice(price);
        }

        if (quantity != null && quantity >= 0 && !quantity.equals(book.getQuantity())) {
            book.setQuantity(quantity);
        }
    }
}