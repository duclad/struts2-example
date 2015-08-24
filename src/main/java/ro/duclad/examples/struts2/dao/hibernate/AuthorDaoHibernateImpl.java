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

    public Author get(String id) {

        return (Author) sessionFactory.getCurrentSession().get(Author.class, id);
    }

    public List<Author> getAll() {
        return sessionFactory.getCurrentSession().createCriteria(Author.class).list();
    }

    public List<Author> getAll(int startFrom, int pageSize) {
        return sessionFactory.getCurrentSession().createCriteria(Author.class).setFirstResult(startFrom).setMaxResults(pageSize).list();

    }

    public void store(Author author) {
        sessionFactory.getCurrentSession().saveOrUpdate(author);
    }

    public Long count() {
        return (Long) sessionFactory.getCurrentSession().createCriteria(Author.class).setProjection(Projections.rowCount()).uniqueResult();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
