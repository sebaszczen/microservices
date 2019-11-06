package sebaszczen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Hello world!
 *
 */

@SpringBootApplication
//@EnableDiscoveryClient
@EnableHystrix
public class LimitServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(LimitServiceApplication.class);
    }
}
