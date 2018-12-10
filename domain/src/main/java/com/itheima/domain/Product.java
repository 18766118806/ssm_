package com.itheima.domain;

import com.itheima.untils.Untils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/*
 * @Author:  Yajun_Xu
 * @Create: 2018/12/2 11:16
 **/
public class Product implements Serializable {
    private String id;
    private String productNum;
    private String productName;
    private String cityName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date DepartureTime;
    private String departureTimeStr;
    private Integer productPrice;
    private String productDesc;
    private Integer productStatus;
    private String productStatusStr;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(Date departureTime) {
        DepartureTime = departureTime;
    }

    public String getDepartureTimeStr() throws Exception {
        if (this.DepartureTime!=null) {
            this.departureTimeStr = Untils.DateToStr (this.DepartureTime, "yyyy-MM-dd");
        }else {
            return null;
        }
        return departureTimeStr;
    }

    public void setDepartureTimeStr(String departureTimeStr) {
        this.departureTimeStr = departureTimeStr;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductStatusStr() {
        if (this.productStatus!=null&&this.productStatus==0){
            this.productStatusStr="关闭";
        }else {
            this.productStatusStr="开启";
        }
        return productStatusStr;
    }

    public void setProductStatusSt(String productStatusSt) {
        this.productStatusStr = productStatusSt;
    }
}
