package sebaszczen.controller;

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
}