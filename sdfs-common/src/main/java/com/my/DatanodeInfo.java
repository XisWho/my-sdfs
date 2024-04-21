package com.my;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatanodeInfo {

    private String ip;

    private String hostname;

    private long latestHeartbeatTime;

}
