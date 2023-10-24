package com.my;

import java.util.ArrayList;
import java.util.List;

public class DatanodeManager implements LifeCycle {

    private List<DatanodeInfo> datanodes;

    @Override
    public void init() {
        datanodes = new ArrayList<>();
    }

    @Override
    public void start() {

    }

    public void register(DatanodeInfo datanodeInfo) {
        datanodes.add(datanodeInfo);
    }

}
