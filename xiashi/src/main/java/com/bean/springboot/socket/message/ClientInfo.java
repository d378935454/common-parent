package com.bean.springboot.socket.message;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by duhongda on 2017/5/14.
 */
@Entity
@Table(name="t_clientinfo")
public class ClientInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="clientid")
    private String clientid;
    @Column(name="connected")
    private Short connected;
    @Column(name="mostsignbits")
    private Long mostsignbits;
    @Column(name="leastsignbits")
    private Long leastsignbits;
    @Column(name="lastconnecteddate")
    private Date lastconnecteddate;
    public String getClientid() {
        return clientid;
    }
    public void setClientid(String clientid) {
        this.clientid = clientid;
    }
    public Short getConnected() {
        return connected;
    }
    public void setConnected(Short connected) {
        this.connected = connected;
    }
    public Long getMostsignbits() {
        return mostsignbits;
    }
    public void setMostsignbits(Long mostsignbits) {
        this.mostsignbits = mostsignbits;
    }
    public Long getLeastsignbits() {
        return leastsignbits;
    }
    public void setLeastsignbits(Long leastsignbits) {
        this.leastsignbits = leastsignbits;
    }
    public Date getLastconnecteddate() {
        return lastconnecteddate;
    }
    public void setLastconnecteddate(Date lastconnecteddate) {
        this.lastconnecteddate = lastconnecteddate;
    }
}
