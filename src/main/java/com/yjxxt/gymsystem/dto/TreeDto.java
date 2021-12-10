package com.yjxxt.gymsystem.dto;

/**
 * @Author Student
 * @date 2021/9/17 17:45
 */
public class TreeDto {
    private Integer id;
    private String name;
    private Integer pId;
    private Boolean checked;

    public TreeDto() {
    }

    public TreeDto(Integer id, String name, Integer pId) {
        this.id = id;
        this.name = name;
        this.pId = pId;
    }

    public TreeDto(Integer id, String name, Integer pId, Boolean checked) {
        this.id = id;
        this.name = name;
        this.pId = pId;
        this.checked = checked;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    @Override
    public String toString() {
        return "TreeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pId=" + pId +
                '}';
    }
}
