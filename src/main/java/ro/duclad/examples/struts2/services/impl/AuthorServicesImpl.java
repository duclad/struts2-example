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

    public void setAuthorDao(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    AuthorDao authorDao;


    public List<Author> getAll(){
        return authorDao.getAll();
    }

    public List<Author> getAll(int startFrom, int pageSize) {
        return authorDao.getAll(startFrom, pageSize);
    }

    public void create(Author author) {
        authorDao.store(author);
    }


}
