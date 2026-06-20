package com.company.task.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record BookRequestDTO(
        @NotBlank(message = "Title is mandatory!")
        String title,

        @NotBlank(message = "Please, enter an author!")
        @Size(min = 1, message = "Please enter at least 1 character")
        String author,

        @NotNull(message = "Price is mandatory!")
        @Positive(message = "Price must be greater than zero!")
        Double price
) {}