package com.ssafy.homfit.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Badge : 회원 뱃지 획득 정보", description = "특정 회원의 뱃지 획득 정보를 보여준다")
public class Badge {
    @ApiModelProperty(value = "회원 번호(32자)")
    private String uid;
    @ApiModelProperty(value = "요가 카테고리 뱃지 획득여부")
    private boolean yoga_count;
    @ApiModelProperty(value = "필라테스 카테고리 뱃지 획득여부")
    private boolean pilates_count;
    @ApiModelProperty(value = "유산소 카테고리 뱃지 획득여부")
    private boolean cardio_count;
    @ApiModelProperty(value = "댄스 카테고리 뱃지 획득여부")
    private boolean dance_count;
    @ApiModelProperty(value = "스트레칭 카테고리 뱃지 획득여부")
    private boolean stretching_count;
    @ApiModelProperty(value = "근력운동 카테고리 뱃지 획득여부")
    private boolean muscle_count;
    @ApiModelProperty(value = "어린이 카테고리 뱃지 획득여부")
    private boolean kids_count;
    @ApiModelProperty(value = "복싱 카테고리 뱃지 획득여부")
    private boolean boxing_count;
    @ApiModelProperty(value = "식단 카테고리 뱃지 획득여부")
    private boolean food_count;
    @ApiModelProperty(value = "첫 챌린지 생성 뱃지 획득여부")
    private boolean add_first_challenge;
    @ApiModelProperty(value = "첫 챌린지 100프로 달성 뱃지 획득여부")
    private boolean first_challenge_complete;
    @ApiModelProperty(value = "첫 인증 뱃지 획득여부")
    private boolean first_certify;
    @ApiModelProperty(value = "인증 100회 뱃지 획득여부")
    private boolean hundred_certify_count;
    @ApiModelProperty(value = "댓글 5회 뱃지 획득여부")
    private boolean five_comment_count;
    @ApiModelProperty(value = "한달 챌린지 100프로 달성 뱃지 획득여부")
    private boolean monthly_challenge_complete;
    @ApiModelProperty(value = "챌린저 달성 뱃지 획득여부")
    private boolean challenger;

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isYoga_count() {
        return this.yoga_count;
    }

    public boolean getYoga_count() {
        return this.yoga_count;
    }

    public void setYoga_count(boolean yoga_count) {
        this.yoga_count = yoga_count;
    }

    public boolean isPilates_count() {
        return this.pilates_count;
    }

    public boolean getPilates_count() {
        return this.pilates_count;
    }

    public void setPilates_count(boolean pilates_count) {
        this.pilates_count = pilates_count;
    }

    public boolean isCardio_count() {
        return this.cardio_count;
    }

    public boolean getCardio_count() {
        return this.cardio_count;
    }

    public void setCardio_count(boolean cardio_count) {
        this.cardio_count = cardio_count;
    }

    public boolean isDance_count() {
        return this.dance_count;
    }

    public boolean getDance_count() {
        return this.dance_count;
    }

    public void setDance_count(boolean dance_count) {
        this.dance_count = dance_count;
    }

    public boolean isStretching_count() {
        return this.stretching_count;
    }

    public boolean getStretching_count() {
        return this.stretching_count;
    }

    public void setStretching_count(boolean stretching_count) {
        this.stretching_count = stretching_count;
    }

    public boolean isMuscle_count() {
        return this.muscle_count;
    }

    public boolean getMuscle_count() {
        return this.muscle_count;
    }

    public void setMuscle_count(boolean muscle_count) {
        this.muscle_count = muscle_count;
    }

    public boolean isKids_count() {
        return this.kids_count;
    }

    public boolean getKids_count() {
        return this.kids_count;
    }

    public void setKids_count(boolean kids_count) {
        this.kids_count = kids_count;
    }

    public boolean isBoxing_count() {
        return this.boxing_count;
    }

    public boolean getBoxing_count() {
        return this.boxing_count;
    }

    public void setBoxing_count(boolean boxing_count) {
        this.boxing_count = boxing_count;
    }

    public boolean isFood_count() {
        return this.food_count;
    }

    public boolean getFood_count() {
        return this.food_count;
    }

    public void setFood_count(boolean food_count) {
        this.food_count = food_count;
    }

    public boolean isAdd_first_challenge() {
        return this.add_first_challenge;
    }

    public boolean getAdd_first_challenge() {
        return this.add_first_challenge;
    }

    public void setAdd_first_challenge(boolean add_first_challenge) {
        this.add_first_challenge = add_first_challenge;
    }

    public boolean isFirst_challenge_complete() {
        return this.first_challenge_complete;
    }

    public boolean getFirst_challenge_complete() {
        return this.first_challenge_complete;
    }

    public void setFirst_challenge_complete(boolean first_challenge_complete) {
        this.first_challenge_complete = first_challenge_complete;
    }

    public boolean isFirst_certify() {
        return this.first_certify;
    }

    public boolean getFirst_certify() {
        return this.first_certify;
    }

    public void setFirst_certify(boolean first_certify) {
        this.first_certify = first_certify;
    }

    public boolean isHundred_certify_count() {
        return this.hundred_certify_count;
    }

    public boolean getHundred_certify_count() {
        return this.hundred_certify_count;
    }

    public void setHundred_certify_count(boolean hundred_certify_count) {
        this.hundred_certify_count = hundred_certify_count;
    }

    public boolean isFive_comment_count() {
        return this.five_comment_count;
    }

    public boolean getFive_comment_count() {
        return this.five_comment_count;
    }

    public void setFive_comment_count(boolean five_comment_count) {
        this.five_comment_count = five_comment_count;
    }

    public boolean isMonthly_challenge_complete() {
        return this.monthly_challenge_complete;
    }

    public boolean getMonthly_challenge_complete() {
        return this.monthly_challenge_complete;
    }

    public void setMonthly_challenge_complete(boolean monthly_challenge_complete) {
        this.monthly_challenge_complete = monthly_challenge_complete;
    }

    public boolean isChallenger() {
        return this.challenger;
    }

    public boolean getChallenger() {
        return this.challenger;
    }

    public void setChallenger(boolean challenger) {
        this.challenger = challenger;
    }
}
