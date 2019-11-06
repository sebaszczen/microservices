package sebaszczen.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sebaszczen.domain.Configuration;
import sebaszczen.domain.LimitConfiguration;

@RestController
public class LimitConfigurationController {

    private Configuration configuration;

    @Autowired
    public LimitConfigurationController(Configuration configuration) {
        this.configuration = configuration;
    }

    @GetMapping("/limits")
    public LimitConfiguration limitConfiguration() {
        return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum(), configuration.getMiddle());
    }

    @GetMapping("/limits-fault")
    @HystrixCommand(fallbackMethod = "fallBackMethodLimitConfiguration")
    public LimitConfiguration limitConfigurationWhenError() throws RuntimeException {
        throw new RuntimeException();
    }

    public LimitConfiguration fallBackMethodLimitConfiguration() {
        return new LimitConfiguration(1,2,3);
    }
}