<template>
  <div class="mb-3">
    <p class="challenge-result-info"><b-icon icon="exclamation-circle-fill"></b-icon> 챌린지 달성률은 하루 한번 업데이트됩니다.</p>
    <div class="row">
      <div class="col-12 mx-auto challenge-result-rate">
        <Progress
          strokeColor="#EAC03A"
          :radius="graphRadius"
          :strokeWidth="graphStrokeWidth"
          :value="average_rate"
        >
          <div class="content challenge-result-graph-rate">{{ average_rate }}%</div>
        </Progress>
      </div>
    </div>
  </div>
</template>

<script>
import Progress from "easy-circular-progress";
export default {
  name: "ChallengeResult",
  components: {
    Progress,
  },
  props: {
    average_rate:Number,
    isMobile:Boolean,
  },
  data() {
    return {
      graphRadius:80,
      graphStrokeWidth:20,
    }
  },
  watch:{
    isMobile:function(){
      if(this.isMobile){
          this.graphRadius = 50;
          this.graphStrokeWidth= 10;
      }else{
          this.graphRadius = 80;
          this.graphStrokeWidth= 20;
      }
    }
  }
};
</script>
