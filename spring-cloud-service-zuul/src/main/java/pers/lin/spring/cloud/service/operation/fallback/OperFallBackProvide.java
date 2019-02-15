package pers.lin.spring.cloud.service.operation.fallback;


import com.alibaba.fastjson.JSON;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import pers.lin.spring.cloud.service.operation.entity.GlobalServiceConfig;
import pers.lin.spring.cloud.service.operation.entity.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2019/2/13
 *  
 * @name: add服务不可用情况下的熔断器
 *
 * @Description: 
 */
@Component
public class OperFallBackProvide implements FallbackProvider {

    @Override
    public String getRoute() {
        return GlobalServiceConfig.SERVICE_ADD;
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                String rsp = JSON.toJSONString(new ResponseEntity("0", GlobalServiceConfig.SERVICE_ADD + "服务不可用。"));
                return new ByteArrayInputStream(rsp.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                //和body中的内容编码一致，否则容易乱码
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }
        };
    }
}
