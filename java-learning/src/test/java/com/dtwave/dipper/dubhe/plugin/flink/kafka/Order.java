package com.dtwave.dipper.dubhe.plugin.flink.kafka;

/**
 * @author hulb
 * @date 2017/12/11 下午2:44
 */

import java.io.Serializable;

public  class Order implements Serializable {
    private Long _id;
    private Long orderId;
    private String proName;
    private Integer amount;
    private Long orderTime;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Long orderTime) {
        this.orderTime = orderTime;
    }


}
