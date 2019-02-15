package pers.lin.spring.cloud.service.operation.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pers.lin.spring.cloud.service.operation.entity.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2019/2/13
 *  
 * @name: Zuul全局过滤器
 *
 * @Description: 
 */
@Component
public class PreZuulFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(PreZuulFilter.class);
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if (request.getRequestURL().toString().indexOf("/api-substract") > 0) {
            // 只对substract服务的请求进行过滤
            return true;
        }
        else {
            return false;

        }
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        if(accessToken == null) {
            logger.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(200);
            ctx.getResponse().setContentType("application/json;charset=UTF-8");
            try {
                String rsp = JSON.toJSONString(new ResponseEntity("0", "token is empty"));
                ctx.getResponse().getWriter().write(rsp);
            }catch (Exception e){
                logger.error(e.toString(),e);
            }

            return null;
        }
        logger.info("ok");
        return null;
    }
}
