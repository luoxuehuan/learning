package com.dtwave.dipper.dubhe.plugin.flink.kafka;

/**
 * @author hulb
 * @date 2017/12/11 下午2:44
 */

import java.io.Serializable;

public  class Click implements Serializable {
    private Long _id;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Long getClickTIme() {
        return clickTIme;
    }

    public void setClickTIme(Long clickTIme) {
        this.clickTIme = clickTIme;
    }

    private Long productId;
    private String proName;
    private Long clickTIme;

}
