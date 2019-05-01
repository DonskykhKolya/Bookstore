package ua.donskykh.mybookstore.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.donskykh.mybookstore.domain.Author;
import ua.donskykh.mybookstore.repo.AuthorRepository;

import java.util.ArrayList;
import java.util.List;

@Service("authorService")
public class AuthorServiceImpl implements AuthorService {

    @Qualifier("authorRepository")
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public String saveAuthor(Author author) {
        author.setActive(true);
        authorRepository.saveAndFlush(author);
        return "Save is sucessfully";
    }

    @Override
    @Transactional
    public List<Author> findAllAuthors (){
        return authorRepository.findAll();
    }


    @Override
    @Transactional
    public Author findAuthorByName(String name) {
        Author author = authorRepository.findByName(name);
        if (!author.isActive()) {
            author = null;
        }
        return author;
    }

    @Override
    @Transactional
    public String deleteAuthor(Long id) {
        Author author = authorRepository.findById(id).get();
        if(!author.isActive()) {
            System.out.println("Category not found!");
        }
        author.setActive(false);
        authorRepository.saveAndFlush(author);
        return "Delete successfully";
    }

    @Override
    @Transactional
    public String updateAuthor(Author author) {
        authorRepository.saveAndFlush(author);
        return "Update is successfully";
    }
}
