package sebaszczen.currencyconversionservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import sebaszczen.currencyconversionservice.domain.CurrencyConversionBean;
import sebaszczen.currencyconversionservice.proxy.CurrencyExchangeServiceProxy;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/currency-converter")
public class CurrencyConversionController {

    private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CurrencyConversionController(CurrencyExchangeServiceProxy currencyExchangeServiceProxy) {
        this.currencyExchangeServiceProxy = currencyExchangeServiceProxy;
    }

    @GetMapping("from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to
    , @PathVariable BigDecimal quantity) {

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, uriVariables);
        CurrencyConversionBean response = responseEntity.getBody();
        BigDecimal conversionMultiple = response.getConversionMultiple();
        return new CurrencyConversionBean(response.getId(), from, to, conversionMultiple, quantity, quantity.multiply(conversionMultiple), response.getPort());
    }

    @GetMapping("/feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeeign(@PathVariable String from, @PathVariable String to
    , @PathVariable BigDecimal quantity) {

        CurrencyConversionBean currencyConversionBean = currencyExchangeServiceProxy.retrieveExchangeValue(from, to);
        BigDecimal conversionMultiple = currencyConversionBean.getConversionMultiple();
        logger.info("{}",currencyConversionBean);
        return new CurrencyConversionBean(currencyConversionBean.getId(), from, to, conversionMultiple, quantity, quantity.multiply(conversionMultiple), currencyConversionBean.getPort());
    }
}
