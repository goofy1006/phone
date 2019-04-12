package com.shopping.model.jsonbean;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by zhangas on 2018/7/26.
 */
public class RegionJsonBean {

    private String id;

    private String name;

    private List<RegionJsonBean> child;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RegionJsonBean> getChild() {
        return child;
    }

    public void setChild(List<RegionJsonBean> child) {
        this.child = child;
    }
}
