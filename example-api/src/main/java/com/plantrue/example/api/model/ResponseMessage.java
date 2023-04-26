package com.plantrue.example.api.model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

public class ResponseMessage {
    private String ver = "0.0.1-SNAPSHOT";

    private LocalDateTime time = LocalDateTime.now();

    private String host;

    private String msg;

    public ResponseMessage() {
    }

    public ResponseMessage(String msg) {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            setHost(localHost.getHostName() + " (" + localHost.getHostAddress() + ")");
        } catch (UnknownHostException e) {
            setHost("");
        }
        setMsg(msg);
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
