package yzx.com.queue.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2019/10/21.
 */

@Entity
public class OrderType {

    @Id(autoincrement = true)
    private Long id;
    private String typeName;
    private Integer minNum=0;
    private Integer maxNum=0;
    private Boolean isSelect=false;
    private Integer orderNum=0;
    private String number;
    @Generated(hash = 1220704035)
    public OrderType(Long id, String typeName, Integer minNum, Integer maxNum,
            Boolean isSelect, Integer orderNum, String number) {
        this.id = id;
        this.typeName = typeName;
        this.minNum = minNum;
        this.maxNum = maxNum;
        this.isSelect = isSelect;
        this.orderNum = orderNum;
        this.number = number;
    }
    @Generated(hash = 40568524)
    public OrderType() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTypeName() {
        return this.typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public Integer getMinNum() {
        return this.minNum;
    }
    public void setMinNum(Integer minNum) {
        this.minNum = minNum;
    }
    public Integer getMaxNum() {
        return this.maxNum;
    }
    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }
    public Boolean getIsSelect() {
        return this.isSelect;
    }
    public void setIsSelect(Boolean isSelect) {
        this.isSelect = isSelect;
    }
    public Integer getOrderNum() {
        return this.orderNum;
    }
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
    public String getNumber() {
        return this.number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
  
}
