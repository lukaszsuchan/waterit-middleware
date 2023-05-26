package agh.iss.wateritmiddleware.token;

import agh.iss.wateritmiddleware.user.User;
import agh.iss.wateritmiddleware.user.User_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class TokenRepositoryCustomImpl implements TokenRepositoryCustom {
    EntityManager entityManager;

    @Override
    public List<Token> findAllValidTokenByUser(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Token> cq = cb.createQuery(Token.class);
        Root<Token> root = cq.from(Token.class);
        Join<Token, User> userJoin = root.join(Token_.user);

        cq.select(root);

        Predicate predicateForExpiredOrRevoked = cb.or(cb.isFalse(root.get(Token_.revoked)), cb.isFalse(root.get(Token_.expired)));
        cq.where(cb.and(cb.equal(userJoin.get(User_.id), id), predicateForExpiredOrRevoked));

        return entityManager.createQuery(cq).getResultList();
    }
}
