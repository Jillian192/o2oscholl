package com.ijava.o2oschool.test;

/**
 * Created by Administrator on 2017/12/13 0013.
 */

public class ParameterSetting {

    /**
     * ackModel : 0
     * cmd : 5
     * msgId : 3665018567067647
     * content : {"powerOffTime":"2:0","powerOnTime":"4:0","screenOffTime":"22:0","screenOnTime":"9:30"}
     */

    private int ackModel;
    private int cmd;
    private long msgId;
    private ContentBean content;

    public int getAckModel() {
        return ackModel;
    }

    public void setAckModel(int ackModel) {
        this.ackModel = ackModel;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * powerOffTime : 2:0
         * powerOnTime : 4:0
         * screenOffTime : 22:0
         * screenOnTime : 9:30
         */

        private long powerOffTime;
        private long powerOnTime;
        private long screenOffTime;
        private long screenOnTime;

        public long getPowerOffTime() {
            return powerOffTime;
        }

        public void setPowerOffTime(long powerOffTime) {
            this.powerOffTime = powerOffTime;
        }

        public long getPowerOnTime() {
            return powerOnTime;
        }

        public void setPowerOnTime(long powerOnTime) {
            this.powerOnTime = powerOnTime;
        }

        public long getScreenOffTime() {
            return screenOffTime;
        }

        public void setScreenOffTime(long screenOffTime) {
            this.screenOffTime = screenOffTime;
        }

        public long getScreenOnTime() {
            return screenOnTime;
        }

        public void setScreenOnTime(long screenOnTime) {
            this.screenOnTime = screenOnTime;
        }
    }
}
