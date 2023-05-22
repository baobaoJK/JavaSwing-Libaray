package com.ksamar.library.entity;

import java.util.Date;

/**
 * 日志实体类
 * @author KSaMar
 * @version 1.0
 */
public class Log {
    private Integer id;
    private Date time;
    private String name;
    private String bookName;
    private String info;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
