package com.bms;

import com.bms.RawData;

/**
 * Created by xu on 2016/7/31.
 */
public class DataFactory {
    private static RawBatteryData rbd = new RawBatteryData();
    private static RawCmdData rcd = new RawCmdData();

    public static RawData createRawData(int serialDataType) {
        if (serialDataType == 0x0F) {
            return rbd;
        } else {
            return rcd;
        }

    }
}
