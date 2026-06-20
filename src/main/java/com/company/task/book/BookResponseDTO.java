package com.company.task.book;

public record BookResponseDTO(
        Long id,
        String title,
        String author
) {}