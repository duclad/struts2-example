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
    Author get(Long id);

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
     * Will create an author informations in the database.
     * @param author Author information to be saved in database.
     */
    void create(Author author);

    /**
     * Will update an author informations in the database.
     *
     * @param author Author information to be saved in database.
     */
    void update(Author author);

    /**
     * Count all authors from the database
     * @return The number of authors in the database.
     */
    Long count();

    /**
     * Deletes a recod from database
     * @param id
     */
    void delete(Long id);
}
