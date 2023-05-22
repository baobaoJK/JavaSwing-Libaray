package com.ksamar.library.entity;

/**
 * 图书种类实体类
 * @author KSaMar
 * @version 1.0
 */
public class BookType {
    private Integer id;
    private String typeName;
    private String typeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}
