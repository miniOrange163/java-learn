package pers.lin.spring.cloud.service.operation.entity;
/**
 * @author: 林嘉宝
 *  
 * @Date: 2019/1/16
 *  
 * @name: 
 *
 * @Description: 
 */
public class OperEntity {

    private String operation;

    private String a;

    private String b;

    public OperEntity() {
    }

    public OperEntity(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }



    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
