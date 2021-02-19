package com.ssafy.homfit.model;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Point : 포인트 정보", description = "모든 포인트 정보를 나타낸다")
public class Point {
    @ApiModelProperty(value = "포인트 식별번호")
    private int point_id;
    @ApiModelProperty(value = "포인트 값")
    private int point;
    @ApiModelProperty(value = "포인트 지급일자")
    private Timestamp point_date;
    @ApiModelProperty(value = "포인트 획득 회원 번호")
    private String uid;
    @ApiModelProperty(value = "포인트 타입")
    private String title;
    @ApiModelProperty(value = "포인트 내용")
    private String content;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPoint_id() {
        return this.point_id;
    }

    public void setPoint_id(int point_id) {
        this.point_id = point_id;
    }

    public int getPoint() {
        return this.point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Timestamp getPoint_date() {
        return this.point_date;
    }

    public void setPoint_date(Timestamp point_date) {
        this.point_date = point_date;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    @Override
    public String toString() {
        return "Point {" +
            " point_id='" + getPoint_id() + "'" +
            ", point='" + getPoint() + "'" +
            ", point_date='" + getPoint_date() + "'" +
            ", uid='" + getUid() + "'" +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            "}";
    }

}
