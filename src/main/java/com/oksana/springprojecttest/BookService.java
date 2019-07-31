package com.oksana.springprojecttest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public Book create(String name) {
        Book book = new Book();
        book.setName(name);

        return this.bookRepository.save(book);
    }

    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    public Book findById(Long id) {
        return this.bookRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        //.orElseThrow(() -> {
        //    return new EntityNotFoundException("message");
        //});
    }

    public Book update(Long id, String name) {
        Book book = findById(id);
        book.setName(name);
        return this.bookRepository.save(book);
    }

    public boolean deleteBook(Long id) {
        if (this.bookRepository.existsById(id)) {
            this.bookRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(String.format("Book.id = %d not found", id));
        }
        return !this.bookRepository.existsById(id);
    }
}
