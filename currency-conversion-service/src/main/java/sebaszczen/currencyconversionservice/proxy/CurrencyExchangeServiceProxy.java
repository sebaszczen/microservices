package sebaszczen.currencyconversionservice.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sebaszczen.currencyconversionservice.domain.CurrencyConversionBean;
//bez ribbona
//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")
//public interface CurrencyExchangeServiceProxy {
//    @GetMapping("currency-exchange-service/currency-exchange/from/{from}/to/{to}")
//    public CurrencyConversionBean retrieveExchangeValue
//    (@PathVariable("from") String from, @PathVariable("to") String to);
//}


//z ribbonem laczenie przez naming service
//@FeignClient(name = "currency-exchange-service")
//@RibbonClient(name = "currency-exchange-service")


@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient
public interface CurrencyExchangeServiceProxy {

//    metoda z currency exchange controller
//    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    @GetMapping("currency-exchange-service/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionBean retrieveExchangeValue
            (@PathVariable("from") String from, @PathVariable("to") String to);
}
