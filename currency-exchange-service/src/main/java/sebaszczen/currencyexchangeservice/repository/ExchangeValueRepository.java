package sebaszczen.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sebaszczen.currencyexchangeservice.domain.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

    ExchangeValue findByFromAndTo(String from, String to);

}
