package xyz.xlong99.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "attention",schema = "wiki")
public class AttentionEntity {

    private  int id;
    private int sender;
    private int receiver;
    private Timestamp attentionDate;
    private String content;


    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Basic
    @Column(name = "sender")
    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }
    @Basic
    @Column(name = "receiver")
    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }
    @Basic
    @Column(name = "attentionDate")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Timestamp getAttentionDate() {
        return attentionDate;
    }

    public void setAttentionDate(Timestamp attentionDate) {
        this.attentionDate = attentionDate;
    }
}
