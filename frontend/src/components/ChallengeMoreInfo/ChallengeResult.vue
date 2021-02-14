<template>
  <div class="mb-3">
    <p class="challenge-result-info"><b-icon icon="exclamation-circle-fill"></b-icon> 챌린지 달성률은 하루 한번 업데이트됩니다.</p>
    <div class="row">
      <div class="col-12 mx-auto challenge-result-rate">
        <Progress
          strokeColor="#EAC03A"
          :radius="50"
          :strokeWidth="10"
          :value="average_rate"
        >
          <div class="content">{{ average_rate }}%</div>
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
    average_rate:Number
  },
};
</script>
