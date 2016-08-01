package com.bms;

import java.util.List;

/**
 * Created by xuxu on 7/31/16.
 */
public abstract class RawData {
    public abstract void setData(List<Integer> list);
    public abstract void notifyDataHandler(DataHandler dh, RawData rd);
}
