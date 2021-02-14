<template>
<div>
  <div class="challenge-title-background">
    <!-- 설정버튼 -->
    <div v-if="equalNickName" class="col-12 col-md-9 mx-auto edit-menu">
      <button id="go-challenge-edit" @click="GoChallengeEdit"><b-icon icon="gear" scale="1.2"></b-icon></button>
      <b-tooltip target="go-challenge-edit" triggers="hover" placement="bottomleft">
       챌린지 수정하기
      </b-tooltip>
    </div>
    <!-- 챌린지 타이틀 -->
    <div class="row col-12 col-md-8 mx-auto">
      <div class="col-12 col-md-6">
        <img id="challenge-img" :src="challenge_img" />
      </div>
      <div class="col-12 col-md-6 my-auto">
        <div class="challenge-info">
          <span id="title-fit-category" class="ml-1">
            <span v-if="fit_id != 9">{{ fitKind[kind] }}</span>
            <span v-if="fit_id != 9" id="fit-category-arrow"> > </span>
            <span>{{ fitList[fit_id] }}</span>
          </span>
          <br />
          <span id="title-main-title"
            >{{ challenge_title }}
            <span
              id="title-check-date"
              :class="{
                'check-date-todo': check_date == 0,
                'check-date-doing': check_date == 1,
                'check-date-done': check_date == 2,
              }"
              >{{ checkDateList[check_date] }}</span
            ></span
          >
          <br />

          <ul id="title-info-ul">
            <li>
              <span class="text-emphasize">{{ period }}일간</span>
              <span class="text-not-emphasize ml-1"
                >({{ start_date }}~{{ end_date }})</span
              >
            </li>
            <li>
              <span class="text-emphasize">{{ GetDayList() }}요일</span>
              <span> 하루 </span>
              <span class="text-emphasize"> {{ day_certify_count }}회</span>
              <span> 인증해주세요!</span>
            </li>
          </ul>
          <div v-if="tagList != null && tagList.length > 0">
            <span
              ><b-icon icon="hash" variant="warning" scale="1.5"></b-icon
            ></span>
            <span
              v-for="(tag, index) in tagList"
              :key="`${index}_tagList`"
              class="title-tag"
              >{{ tag }}
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>
   <div class="challenge-people-background">
      <table class="col-11 col-md-3 mx-auto">
        <tr>
          <td class="font-wight-600"><b-icon icon="dot"/>참가인원</td>
          <td class="align-center">
            <span>현재 </span>
            <span class="text-emphasize">{{ people }}명</span>
            <span> 참여중</span>
          </td>
        </tr>
        <tr>
          <td class="font-wight-600"><b-icon icon="dot"/>개설자</td>
          <td class="align-center">
            <span
              ><img
                id="title-user-image"
                src="@/assets/NavBar/anonimous_user.png"
            /></span>
            <span class="ml-1">{{ nick_name }}</span>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>
<script>
import "@/assets/css/ChallengeMoreInfo/challengeTitle.css";
const dayList = ["", "월", "화", "수", "목", "금", "토", "일"];

export default {
  name: "ChallengeTitle",
  props: {
    equalNickName:Boolean,
    challenge_title: String,
    challenge_img: String,
    day_certify_count: Number,
    start_date: String,
    end_date: String,
    period: Number,
    fit_id: Number,
    kind: Number,
    daylist_string: String,
    people: Number,
    nick_name: String,
    tagList: Array,
    bodyList: Array,
    check_date: Number,
  },
  data() {
    return {
      checkDateList: ["진행전", "진행중", "완료"],
      fitKind: ["운동", "식단"],
      fitList: [
        "",
        "요가",
        "필라테스",
        "유산소",
        "댄스",
        "스트레칭",
        "근력운동",
        "키즈",
        "복싱",
        "식단",
        "기타",
      ],
      fitCategory: "",
      titleImage:'',
    };
  },
  methods: {
    // 날짜 숫자 -> 요일로 변경
    GetDayList: function() {
      if (this.daylist_string != null) {
        let list = this.daylist_string
          .substring(1, this.daylist_string.length - 1)
          .split(",");
        let temp = new Array(list.length);
        for (let index = 0; index < list.length; index++) {
          temp[index] = dayList[parseInt(list[index].replace(" ", ""))];
        }
        return temp.join(",");
      }
      return "";
    },
  GoChallengeEdit:function(){
    this.$emit("challengeEdit");
  },
  },
};
</script>
