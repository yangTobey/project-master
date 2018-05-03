package com.spring.boot.bean.cluster;

import java.util.Map;

/**
 * Created by xiaoyang on 2018/4/23.
 */
public class CityInfo {
    //城市id
    private  Long cityId;
    //城市名称
    private String cityName;
    //经度
    private String longitude;
    //纬度
    private String latitude;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

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
}
