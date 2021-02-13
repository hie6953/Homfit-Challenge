<template>
  <div class="mb-3">
    
    <div class="row">
      <div class="col-12 col-md-3 challenge-result-rate">
        <Progress
          strokeColor="#EAC03A"
          :radius="50"
          :strokeWidth="10"
          :value="result"
        >
          <div class="content">{{ result }}%</div>
        </Progress>
      </div>
      <div class="challenge-result-content col-12 col-md-9">
        총 x명중 x명이 챌린지를 달성했어요!
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
  props: {},
  data() {
    return {
      result: 80,
    };
  },
};
</script>
