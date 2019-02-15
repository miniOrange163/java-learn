package pers.lin.spring.cloud.service.operation.entity;
/**
 * @author: 林嘉宝
 *  
 * @Date: 2019/2/13
 *  
 * @name: 
 *
 * @Description: 
 */
public class ResponseEntity {

    private String code;

    private String message;

    public ResponseEntity(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
