package com.ning.app.domain;

import javax.persistence.*;

/**
 * Created by evancha on 2/4/16.
 */
@Entity
public class UserRel {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private long fromId;
    @Column(nullable = false)
    private long toId;

    protected UserRel() {
    }

    public UserRel(long fromId, long toId) {
        this.fromId = fromId;
        this.toId = toId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFromId() {
        return fromId;
    }

    public void setFromId(long fromId) {
        this.fromId = fromId;
    }

    public long getToId() {
        return toId;
    }

    public void setToId(long toId) {
        this.toId = toId;
    }

    @Override
    public String toString() {
        return "UserRel{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", toId=" + toId +
                '}';
    }
}
