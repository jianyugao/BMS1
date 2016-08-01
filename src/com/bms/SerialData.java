package com.bms;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class SerialData {

    private List<Integer> content = null;
    private RawBatteryData data = new RawBatteryData();
    private RawCmdData cmd = new RawCmdData();

    private BatteryDataHandler bdh = new BatteryDataHandler();
    private CmdDataHandle cdh = new CmdDataHandle();


//    private List<DataHandler> list = new ArrayList<DataHandler>();
//
//    public void registerDataHandle(DataHandler dh) {
//        list.add(dh);
//    }
//
//    public void removeDataHandle(DataHandler dh) {
//        list.remove(dh);
//    }

    public void setContent(List<Integer> a) {
        this.content = a;
    }

    public SerialData() {
    }

    public SerialData(ArrayList<Integer> a) {
        this.content = a;
    }


    public boolean isValid() {

        if (content.size() == 0 || content.get(0).intValue() != 0x68 || content.get(content.size() - 1).intValue() != 0x88) {
            content = null;
            return false;
        } else {
            return true;
        }
    }

    public int getSerialDataType() {
        return content.get(1).intValue();
    }

    public void setRawData() {
        if (this.isValid() == false) {
            return;
        }

        RawData data = DataFactory.createRawData(getSerialDataType());
        data.setData(content);
    }


    private void setRawCmdData() {
        //do something here
        notifyDataHandler(cdh, cmd);

    }

    public void notifyDataHandler(DataHandler dh, RawData rd) {
        dh.handleData(rd);
    }


}

