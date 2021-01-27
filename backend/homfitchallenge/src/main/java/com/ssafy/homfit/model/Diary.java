package com.ssafy.homfit.model;

import java.sql.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Diary : 기록", description = "특정일 해당 사용자의 별점과 일기 내용이 들어있다")
public class Diary {
    @ApiModelProperty(value = "등록 일자")
    private Date date;
    @ApiModelProperty(value = "사용자 식별번호")
    private String uid;
    @ApiModelProperty(value = "별점")
    private int star_point;
    @ApiModelProperty(value = "일기 내용")
    private String diary_contents;

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getStar_point() {
        return this.star_point;
    }

    public void setStar_point(int star_point) {
        this.star_point = star_point;
    }

    public String getDiary_contents() {
        return this.diary_contents;
    }

    public void setDiary_contents(String diary_contents) {
        this.diary_contents = diary_contents;
    }
}
