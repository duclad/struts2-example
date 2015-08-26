package ro.duclad.examples.struts2.services.impl;

import org.springframework.transaction.annotation.Transactional;
import ro.duclad.examples.struts2.dao.AuthorDao;
import ro.duclad.examples.struts2.model.Author;
import ro.duclad.examples.struts2.services.AuthorServices;

import java.util.List;

/**
 * Created by duclad on 8/23/15.
 */
@Transactional
public class AuthorServicesImpl implements AuthorServices {

    AuthorDao authorDao;

    public void setAuthorDao(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public List<Author> getAll() {
        return authorDao.getAll();
    }

    public List<Author> getAll(int startFrom, int pageSize) {
        return authorDao.getAll(startFrom, pageSize);
    }

    public void create(Author author) {
        authorDao.create(author);
    }

    public void update(Author author) {
        authorDao.update(author);
    }

    public void delete(Long id) {
        authorDao.delete(id);
    }

    public Author get(Long id) {
        return authorDao.get(id);
    }

    public long countAll() {
        return authorDao.count();
    }

}
