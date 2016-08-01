package com.bms;

/**
 * Created by xuxu on 7/31/16.
 */
public class BatteryDataHandler implements DataHandler{
    @Override
    public void handleData(RawData rd) {
        System.out.println("get raw battery data!");
    }
}
