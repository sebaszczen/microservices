package sebaszczen.netflixzuulapigatewayserver.beans;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ZuulLoggingFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //before: 'pre' the filter is executed or after 'post' or when filter only request that powoduja error 'error'
    @Override
    public String filterType() {
        return "pre";
    }

    //jesli mamy duzo filtrow np. zuulloggingfilter, zullsecurityfilter... mozemy nadac im priorytet 1,2,3,... 1 bedzie pierwszy
    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        logger.info("request -> {} request uri -> {}", request,request.getRequestURI());
        return null;
    }
}
