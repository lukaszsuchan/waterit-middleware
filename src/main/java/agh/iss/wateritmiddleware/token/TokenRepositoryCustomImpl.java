package agh.iss.wateritmiddleware.token;

import agh.iss.wateritmiddleware.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class TokenRepositoryCustomImpl implements TokenRepositoryCustom{
    EntityManager entityManager;
    @Override
    public List<Token> findAllValidTokenByUser(Integer id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Token> cq = cb.createQuery(Token.class);
        Root<Token> root = cq.from(Token.class);
//        Join<Token, User> userJoin = root.join()
        return Collections.emptyList();
    }
}
