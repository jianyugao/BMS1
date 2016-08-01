package com.bms;

import com.bms.DataHandler;

/**
 * Created by xuxu on 7/31/16.
 */
public class CmdDataHandle implements DataHandler {
    @Override
    public void handleData(RawData rd) {
        System.out.println("get cmd data!");

    }
}
