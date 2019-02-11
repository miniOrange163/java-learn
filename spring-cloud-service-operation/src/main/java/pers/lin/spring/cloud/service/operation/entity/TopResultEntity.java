package pers.lin.spring.cloud.service.operation.entity;
/**
 * @author: 林嘉宝
 *  
 * @Date: 2019/1/31
 *  
 * @name: 
 *
 * @Description: 
 */
public class TopResultEntity {

    private boolean success;

    private String message;

    private String operation;

    private Double result;

    private String operationName;



    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }


}
