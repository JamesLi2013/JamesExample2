package com.example.james.bean;

import java.util.List;

/**
 * Created by Administrator on 2015/12/1.
 */
public class LocationBean {

    private List<Double> queryLocation;
    /**
     * type : doorPlate
     * status : 0
     * name :
     * admCode :
     * admName :
     * nearestPoint : []
     * distance : -1
     */

    private List<AddrListEntity> addrList;

    public void setQueryLocation(List<Double> queryLocation) {
        this.queryLocation = queryLocation;
    }

    public void setAddrList(List<AddrListEntity> addrList) {
        this.addrList = addrList;
    }

    public List<Double> getQueryLocation() {
        return queryLocation;
    }

    public List<AddrListEntity> getAddrList() {
        return addrList;
    }

    public static class AddrListEntity {
        private String type;
        private int status;
        private String name;
        private String admCode;
        private String admName;
        private int distance;
        private List<?> nearestPoint;

        public void setType(String type) {
            this.type = type;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAdmCode(String admCode) {
            this.admCode = admCode;
        }

        public void setAdmName(String admName) {
            this.admName = admName;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public void setNearestPoint(List<?> nearestPoint) {
            this.nearestPoint = nearestPoint;
        }

        public String getType() {
            return type;
        }

        public int getStatus() {
            return status;
        }

        public String getName() {
            return name;
        }

        public String getAdmCode() {
            return admCode;
        }

        public String getAdmName() {
            return admName;
        }

        public int getDistance() {
            return distance;
        }

        public List<?> getNearestPoint() {
            return nearestPoint;
        }
    }
}
