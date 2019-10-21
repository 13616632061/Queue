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
    private Integer minNum;
    private Integer maxNum;
    @Generated(hash = 141690519)
    public OrderType(Long id, String typeName, Integer minNum, Integer maxNum) {
        this.id = id;
        this.typeName = typeName;
        this.minNum = minNum;
        this.maxNum = maxNum;
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
}
