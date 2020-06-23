package com.spoon.app.jsbridge_n22.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gdk on 2020/05/19 21:58
 *
 * @author gdk
 * 用户信息的实体类
 */
public class UserInfoBean implements Serializable {
    private AppLoginUserBean appLoginUser;

    private List<String> bizLaAgentList;
    private String token;

    public AppLoginUserBean getAppLoginUser() {
        return appLoginUser;
    }

    public void setAppLoginUser(AppLoginUserBean appLoginUser) {
        this.appLoginUser = appLoginUser;
    }

    public List<String> getBizLaAgentList() {
        if (bizLaAgentList == null) {
            return new ArrayList<>();
        }
        return bizLaAgentList;
    }

    public void setBizLaAgentList(List<String> bizLaAgentList) {
        this.bizLaAgentList = bizLaAgentList;
    }

    public static class AppLoginUserBean implements Serializable{

        /**
         * agentCode : 1100000006
         * agentName : 孙燕归
         * agenttype : null
         * appntSex : 1
         * cerdNum : 110222198701140825
         * cerdType : 11
         * channel : 01
         * channelName : 个险代理人渠道
         * comName : 东环营销服务部
         * id : 44
         * insureDate : null
         * jobNum : null
         * mobile : 13911994828
         * orgId : 861100001
         * roleCoding : OUTDOOR_STAFF
         */

        private String agentCode;
        private String agentName;
        private String agenttype;
        private String appntSex;
        private String cerdNum;
        private String cerdType;
        private String channel;
        private String channelName;
        private String comName;
        private int id;
        private String insureDate;
        private String jobNum;
        private String mobile;
        private String orgId;
        private String roleCoding;

        public String getAgentCode() {
            return agentCode == null ? "" : agentCode;
        }

        public void setAgentCode(String agentCode) {
            this.agentCode = agentCode;
        }

        public String getAgentName() {
            return agentName == null ? "" : agentName;
        }

        public void setAgentName(String agentName) {
            this.agentName = agentName;
        }

        public String getAgenttype() {
            return agenttype == null ? "" : agenttype;
        }

        public void setAgenttype(String agenttype) {
            this.agenttype = agenttype;
        }

        public String getAppntSex() {
            return appntSex == null ? "" : appntSex;
        }

        public void setAppntSex(String appntSex) {
            this.appntSex = appntSex;
        }

        public String getCerdNum() {
            return cerdNum == null ? "" : cerdNum;
        }

        public void setCerdNum(String cerdNum) {
            this.cerdNum = cerdNum;
        }

        public String getCerdType() {
            return cerdType == null ? "" : cerdType;
        }

        public void setCerdType(String cerdType) {
            this.cerdType = cerdType;
        }

        public String getChannel() {
            return channel == null ? "" : channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getChannelName() {
            return channelName == null ? "" : channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public String getComName() {
            return comName == null ? "" : comName;
        }

        public void setComName(String comName) {
            this.comName = comName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInsureDate() {
            return insureDate == null ? "" : insureDate;
        }

        public void setInsureDate(String insureDate) {
            this.insureDate = insureDate;
        }

        public String getJobNum() {
            return jobNum == null ? "" : jobNum;
        }

        public void setJobNum(String jobNum) {
            this.jobNum = jobNum;
        }

        public String getMobile() {
            return mobile == null ? "" : mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getOrgId() {
            return orgId == null ? "" : orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getRoleCoding() {
            return roleCoding == null ? "" : roleCoding;
        }

        public void setRoleCoding(String roleCoding) {
            this.roleCoding = roleCoding;
        }
    }

    public String getToken() {
        return token == null ? "" : token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginDataResultBean{" +
                "appLoginUser=" + appLoginUser +
                ", bizLaAgentList=" + bizLaAgentList +
                ", token='" + token + '\'' +
                '}';
    }
}
