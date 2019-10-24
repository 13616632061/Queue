package yzx.com.queue.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2019/10/23.
 */

@Entity
public class OrderInfo {

    @Id(autoincrement = true)
    private Long id;
    private String number;
    private String phone;
    private Integer personNum = 0;
    private Integer status = 0;
    private String time;
    private Boolean isHasMsg = false;
    private Integer callNum = 0;
    @Generated(hash = 1654003287)
    public OrderInfo(Long id, String number, String phone, Integer personNum,
            Integer status, String time, Boolean isHasMsg, Integer callNum) {
        this.id = id;
        this.number = number;
        this.phone = phone;
        this.personNum = personNum;
        this.status = status;
        this.time = time;
        this.isHasMsg = isHasMsg;
        this.callNum = callNum;
    }
    @Generated(hash = 1695813404)
    public OrderInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNumber() {
        return this.number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Integer getPersonNum() {
        return this.personNum;
    }
    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }
    public Integer getStatus() {
        return this.status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public Boolean getIsHasMsg() {
        return this.isHasMsg;
    }
    public void setIsHasMsg(Boolean isHasMsg) {
        this.isHasMsg = isHasMsg;
    }
    public Integer getCallNum() {
        return this.callNum;
    }
    public void setCallNum(Integer callNum) {
        this.callNum = callNum;
    }

    


}
