package ua.donskykh.mybookstore.service;

import ua.donskykh.mybookstore.domain.Author;

import java.util.List;

public interface AuthorService {

    String saveAuthor(Author author);

    List<Author> findAllAuthors();

    Author findAuthorByName(String name);


    String deleteAuthor(Long id);

    String updateAuthor(Author author);
}
