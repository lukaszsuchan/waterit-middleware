package agh.iss.wateritmiddleware.token;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long>, TokenRepositoryCustom {
}
