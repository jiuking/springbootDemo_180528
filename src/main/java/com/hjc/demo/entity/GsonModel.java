package com.hjc.demo.entity;

import java.util.List;

/**
 * @author : Administrator
 * @date : 2018/6/7 0007 14:56
 * @description : gsonformat 测试生成bean
 */
public class GsonModel {

    /**
     * handleType : insert
     * orgEntities : [{"channel":"TUANDAI","mainBody":"HT","nodeSeq":"1","orgId":"test","orgLevel":0,"orgName":"信审一室","signMode":null,"orgNumber":"APPROVAL1","parentId":"4A0BC1B87B581C42E05332C8A8C003DB","serviceLine":"XD","shuttingDown":"1"}]
     */

    private String handleType;
    private List<OrgEntitiesBean> orgEntities;

    public String getHandleType() {
        return handleType;
    }

    public void setHandleType(String handleType) {
        this.handleType = handleType;
    }

    public List<OrgEntitiesBean> getOrgEntities() {
        return orgEntities;
    }

    public void setOrgEntities(List<OrgEntitiesBean> orgEntities) {
        this.orgEntities = orgEntities;
    }

    public static class OrgEntitiesBean {
        /**
         * channel : TUANDAI
         * mainBody : HT
         * nodeSeq : 1
         * orgId : test
         * orgLevel : 0
         * orgName : 信审一室
         * signMode : null
         * orgNumber : APPROVAL1
         * parentId : 4A0BC1B87B581C42E05332C8A8C003DB
         * serviceLine : XD
         * shuttingDown : 1
         */

        private String channel;
        private String mainBody;
        private String nodeSeq;
        private String orgId;
        private int orgLevel;
        private String orgName;
        private Object signMode;
        private String orgNumber;
        private String parentId;
        private String serviceLine;
        private String shuttingDown;

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getMainBody() {
            return mainBody;
        }

        public void setMainBody(String mainBody) {
            this.mainBody = mainBody;
        }

        public String getNodeSeq() {
            return nodeSeq;
        }

        public void setNodeSeq(String nodeSeq) {
            this.nodeSeq = nodeSeq;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public int getOrgLevel() {
            return orgLevel;
        }

        public void setOrgLevel(int orgLevel) {
            this.orgLevel = orgLevel;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public Object getSignMode() {
            return signMode;
        }

        public void setSignMode(Object signMode) {
            this.signMode = signMode;
        }

        public String getOrgNumber() {
            return orgNumber;
        }

        public void setOrgNumber(String orgNumber) {
            this.orgNumber = orgNumber;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getServiceLine() {
            return serviceLine;
        }

        public void setServiceLine(String serviceLine) {
            this.serviceLine = serviceLine;
        }

        public String getShuttingDown() {
            return shuttingDown;
        }

        public void setShuttingDown(String shuttingDown) {
            this.shuttingDown = shuttingDown;
        }
    }
}
