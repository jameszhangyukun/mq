package com.zyk.mq.common.rpc;

import com.github.houbb.load.balance.support.server.IServer;

public class RpcAddress  implements IServer {

    private String address;

    private int port;

    private int weight;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String url() {
        return this.address + ":" + port;
    }

    @Override
    public int weight() {
        return this.weight;
    }
}
