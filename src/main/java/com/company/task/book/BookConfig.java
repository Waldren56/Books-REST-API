package com.company.task.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner commandLineRunner(BookRepository bookRepository) {
        return args -> {
            if (bookRepository.count() == 0) {
                Book whistler = new Book(
                        "Whistler",
                        "Ann Patchett",
                        "Harper",
                        30.00,
                        50,
                        LocalDate.of(2026, Month.JUNE, 2)
                );
                Book obstetrix = new Book(
                        "Obstetrix",
                        "Naomi Kritzer",
                        "Harper",
                        24.99,
                        100,
                        LocalDate.of(2026, Month.JUNE, 9)
                );

                bookRepository.saveAll(List.of(whistler, obstetrix));
            }
        };
    }
}
