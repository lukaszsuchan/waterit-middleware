package agh.iss.wateritmiddleware.token;

import java.util.List;

public interface TokenRepositoryCustom {

    List<Token> findAllValidTokenByUser(Long id);
}
