<template>
    <div class="challenge-title-background">
        <div class="row col-11 col-md-9 mx-auto">
        <div class="col-12 col-md-6">

        <img id="challenge-img" src="https://picsum.photos/600/300/?image=25">
        </div>
        <div class="col-12 col-md-6 my-auto">
            <div class="challenge-info">
                <span id="title-check-date">{{check_dates[check_date]}}</span>
                <span>{{fitList[fit_id]}}</span>
                <h2>{{challenge_title}}</h2>
                <span>{{nick_name}}</span>
                <br>
                <span>{{GetDayList()}}</span>
                <br>
                <span>하루 인증{{day_certify_count}}회</span>
                <br>
                <span>{{start_date}}~{{end_date}}</span>
                <br>
                <span>{{people}}명 참여중</span>
                <br>
                <span v-for="(tag,index) in tagList"
                :key="`${index}_tagList`"
                >{{tag}} </span>
            </div>
        </div>

        </div>
    </div>
</template>
<script>
import '@/assets/css/ChallengeMoreInfo/challengeTitle.css';
const dayList = ['', '월', '화', '수', '목', '금', '토', '일'];

export default {
    name:'ChallengeTitle',
    props:{
        challenge_title:String,
        challenge_img:String,
        day_certify_count:Number,
        start_date:String,
        end_date:String,
        nick_name:String,
        period:Number,
        fit_id:Number,
        people:Number,
        kind:Number,
        daylist_string:String,
        tagList:Array,
        bodyList:Array,
        check_date:Number,
    },
    data() {
        return {
            check_dates : ["시작전","진행중","완료"],
            fitList : ['','요가','필라테스','유산소','댄스','스트레칭','근력운동','키즈','복싱','식단','기타'],
        }
    },
    created() {
        
    },
    methods: {
        // 날짜 숫자 -> 요일로 변경
    GetDayList: function() {
      if (this.daylist_string != null) {
        let list = this.daylist_string
          .substring(1, this.daylist_string.length - 1)
          .split(',');
        let temp = new Array(list.length);
        for (let index = 0; index < list.length; index++) {
          temp[index] = dayList[parseInt(list[index].replace(' ', ''))];
        }
        return temp.join('/');
      }
      return '';
    },
    },
}


</script>