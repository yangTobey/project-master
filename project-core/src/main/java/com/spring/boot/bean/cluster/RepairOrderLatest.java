package com.spring.boot.bean.cluster;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaoyang on 2018/4/23.
 */
public class RepairOrderLatest {
    //城市id
    private  Long cityId;
    //城市名称
    private String cityName;
    //经度
    private String longitude;
    //纬度
    private String latitude;
    //小区id
    private Long communityId;
    //订单状态
    private Integer status;
    //状态名称
    private String statusName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}
