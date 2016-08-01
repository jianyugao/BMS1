package com.bms;

import java.util.List;

/**
 * Created by xu on 2016/7/31.
 */
public class RawCmdData extends RawData {
    private int commandType;
    private int commandAction;
    private int[] data;
    private CmdDataHandle cdh = new CmdDataHandle();


    public RawCmdData() {
    }

    public RawCmdData(int commandType, int commandAction, int[] data) {
        this.commandType = commandType;
        this.commandAction = commandAction;
        this.data = data;
    }

    @Override
    public void setData(List<Integer> list) {

    }

    @Override
    public void notifyDataHandler(DataHandler dh, RawData rd) {
        cdh.handleData(this);
    }
}