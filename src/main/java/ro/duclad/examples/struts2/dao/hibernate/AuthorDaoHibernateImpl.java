package ro.duclad.examples.struts2.dao.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.transaction.annotation.Transactional;
import ro.duclad.examples.struts2.dao.AuthorDao;
import ro.duclad.examples.struts2.model.Author;

import java.util.List;

/**
 * Created by duclad on 8/23/15.
 */
@Transactional
public class AuthorDaoHibernateImpl implements AuthorDao {

    private SessionFactory sessionFactory;

    public Author get(Long id) {

        return (Author) sessionFactory.getCurrentSession().get(Author.class, id);
    }

    public List<Author> getAll() {
        return sessionFactory.getCurrentSession().createCriteria(Author.class).list();
    }

    public List<Author> getAll(int startFrom, int pageSize) {
        return sessionFactory.getCurrentSession().createCriteria(Author.class).setFirstResult(startFrom).setMaxResults(pageSize).list();

    }

    public void create(Author author) {
        sessionFactory.getCurrentSession().save(author);
    }

    public void update(Author author) {
        sessionFactory.getCurrentSession().update(author);
    }

    public Long count() {
        return (Long) sessionFactory.getCurrentSession().createCriteria(Author.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    public void delete(Long id) {
        sessionFactory.getCurrentSession().delete(get(id));
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
