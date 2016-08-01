package com.bms;

import com.bms.RawData;

/**
 * Created by xu on 2016/7/31.
 */
public class DataFactory {
    private static RawBatteryData rbd = new RawBatteryData();
    private static RawCmdData rcd = new RawCmdData();

    public static RawData createRawData(int cmdType){
        if(cmdType == 0){
            return rbd;
        }else {
            return rcd;
        }

    }
}
