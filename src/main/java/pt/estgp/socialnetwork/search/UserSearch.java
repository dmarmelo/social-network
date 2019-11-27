package pt.estgp.socialnetwork.search;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pt.estgp.socialnetwork.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserSearch {

    @PersistenceContext
    private EntityManager entityManager;


    public List<User> search(String text) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(User.class)
                .get();

        List<User> results = fullTextWithLike(text, queryBuilder, fullTextEntityManager);

        if (results.isEmpty()) {
            results = fullText(text, queryBuilder, fullTextEntityManager);
        }

        return results;
    }

    private List<User> fullTextWithLike(String text, QueryBuilder queryBuilder, FullTextEntityManager fullTextEntityManager) {
        Query query = queryBuilder
                .keyword()
                .wildcard()
                .onFields("name", "username", "email")
                .matching("*" + text + "*")
                .createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, User.class);
        return jpaQuery.setMaxResults(10).getResultList();
    }

    private List<User> fullText(String text, QueryBuilder queryBuilder, FullTextEntityManager fullTextEntityManager) {
        Query query = queryBuilder
                .keyword()
                .onFields("name", "username", "email")
                .matching(text)
                .createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, User.class);
        return jpaQuery.setMaxResults(10).getResultList();
    }

}
