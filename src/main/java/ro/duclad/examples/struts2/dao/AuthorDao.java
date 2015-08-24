package ro.duclad.examples.struts2.dao;

import ro.duclad.examples.struts2.model.Author;

import java.util.List;

/**
 * Created by duclad on 8/23/15.
 */
public interface AuthorDao {

    /**
     * Get an author by its ID
     * @param id - Unique ID of an author
     * @return The author found by its unique ID or null if nothing found.
     */
    Author get(String id);

    /**
     * Finds all authors from the database
     * @return A list with all authors
     */
    List<Author> getAll();

    /**
     * Finds <i>pageSize</i> numbers of authors starting from <i>startFrom</i> element.
     * @param startFrom Index from where the list will start.
     * @param pageSize Maximum number of records to return
     * @return
     */
    List<Author> getAll(int startFrom, int pageSize);

    /**
     * Will store an author informations in the database. This could yield and update if the author is already present or an insert if the author does not exists.
     * @param author Author information to be saved in database.
     */
    void store(Author author);

    /**
     * Count all authors from the database
     * @return The number of authors in the database.
     */
    Long count();
}
