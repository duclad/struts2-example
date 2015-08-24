package ro.duclad.examples.struts2.services;

import ro.duclad.examples.struts2.model.Author;

import java.util.List;

/**
 * Created by duclad on 8/23/15.
 */
public interface AuthorServices {

    List<Author> getAll();

    List<Author> getAll(int startFrom, int pageSize);

    void create(Author author);

    void update(Author author);

    void delete(Long id);

    Author get(Long id);
}
