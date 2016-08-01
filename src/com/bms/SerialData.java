package com.bms;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class SerialData {

    private int[] content = null;
    private RawBatteryData data = new RawBatteryData();
    private RawCmdData cmd = new RawCmdData();

    private BatteryDataHandler bdh = new BatteryDataHandler();
    private CmdDataHandle cdh = new CmdDataHandle();


    private List<DataHandler> list = new ArrayList<DataHandler>();

    public void registerDataHandle(DataHandler dh) {
        list.add(dh);
    }

    public void removeDataHandle(DataHandler dh) {
        list.remove(dh);
    }

    public void setContent(int[] content) {
        this.content = content;
    }

    public SerialData() {
    }

    public SerialData(int[] a) {
        this.content = a;
    }


    public boolean isValid() {

        if (content.length == 0 || content[0] != 0x68 || content[content.length - 1] != 0x88) {
            content = null;
            return false;
        } else {
            return true;
        }
    }

    public int getSerialDataType() {
        return content[1];
    }

    public void setRawData() {
        if (this.isValid() == false) {
            return;
        }
        if (getSerialDataType() == 0x0F) {
            setRawBatteryData();
        } else {
            setRawCmdData();
        }

        RawData data = DataFactory.creatRawData(getSerialDataType());
        data.setData(content);
    }

    private void setRawBatteryData() {
        System.out.println("Set RBD1!");
        data.chCur = (content[2] << 8) + content[3];
        data.disCur = (content[4] << 8) + content[5];
        data.temperature = (content[6] << 8) + content[7];
        data.batteryStatus = content[8];
        data.chargerStatus = content[9];
        System.out.println("Set RBD2!");
        int year = (content[10] << 8) + content[11];
        int month = content[12];
        int day = content[13];
        int hour = content[14];
        int min = content[15];
        int sec = content[16];

        LocalDateTime time = LocalDateTime.of(year, Month.of(month), day, hour, min, sec);
        java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(time);
        //LocalDateTime ldt2 = timestamp.toLocalDateTime();
        //System.out.println(timestamp);
        //System.out.println(ldt2);

        data.timestamp = timestamp;
        notifyDataHandler(bdh, data);
    }

    private void setRawCmdData() {
        //do something here
        notifyDataHandler(cdh, cmd);

    }

    public void notifyDataHandler(DataHandler dh, RawData rd) {
        dh.handleData(rd);
    }


    public class RawCmdData extends RawData {
        private int commandType;
        private int commandAction;
        private int[] data;
        private CmdDataHandle cdh = new CmdDataHandle();


        public RawCmdData(){

        }
        public RawCmdData(int commandType, int commandAction, int[] data) {
            this.commandType = commandType;
            this.commandAction = commandAction;
            this.data = data;
        }

    }

    public class RawBatteryData extends RawData {

        private int batteryId;
        private double chCur;
        private double disCur;
        private double temperature;
        private int batteryStatus;
        private int chargerStatus;
        private Timestamp timestamp;


    }
}

