# 홈핏챌린지

---

## 📃프로젝트 개요

---

- **개발 기간** : 2021.01.11 ~ 2021.02.19 (총 7주)
- **개발 환경** : Vue.js, SpringBoot, MySQL, GitLab, Jira
- **프로젝트 이름** : Homfit Challenge(홈핏챌린지)
- **프로젝트 설명** :  챌린지를 통해 함께 운동하고 공유하며, 사용자의 운동 기록을 이용해 스스로 실천 및 개선을 돕는 홈피트니스 동기부여 웹 서비스
- **슬로건** : 챌린지를 통해 함께 운동해요! 건강한 삶 프로젝트

## 🔆팀원

---

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled.png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled.png)

**[황다희](https://github.com/ekgml3765)**

**[김민중](https://github.com/kimmj0920)**

[**남우진**](https://github.com/NamWoojin)

**[박은수](https://github.com/eunsu27)**

[**이종희**](https://github.com/hie6953)

## 📖개발기록

---

- 기획
- Git Commit 규칙
- ERD

    [ERD](https://www.notion.so/ERD-32864fe7a7c3402f9cfba27d592d0405)

- Backend

    [백앤드 기능명세서](https://www.notion.so/5ea0872be4314d8b921352eaea8532cc)

- Frontend

    [프론트 기능명세서](https://www.notion.so/c671c35fb43a42e3b82837969d2d8d13)

- 최종 발표 준비

    [최종발표 스크립트](https://www.notion.so/150c2cf0205445fea3a3d17b28ff65fc)

## 🛠기술스택

---

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%201.png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%201.png)

- frontend 사용 라이브러리
    - [bootstrap-vue](https://bootstrap-vue.org/)
    - [chart.js](https://www.chartjs.org/)
    - [easy-circular-progress](https://www.npmjs.com/package/easy-circular-progress)
    - [sweetalert2](https://sweetalert2.github.io/)
    - [tiptap](https://tiptap.dev/)
    - [vue-flickity](https://github.com/drewjbartlett/vue-flickity)
    - [vue-infinite-loading](https://peachscript.github.io/vue-infinite-loading/guide/)
    - [vue-slider-component](https://nightcatsama.github.io/vue-slider-component/)

## 🎈주요기능

---

### Challenge

- 챌린지 리스트
    - 카테고리, 정렬, 챌린지 기간, 챌린지 인증 요일에 따른 챌린지 조회
- 추천 챌린지
    - 유저 개개인이 선호하는 운동 카테고리, 운동 부위, 인증 요일에 맞는 챌린지 추천
- 챌린지 개설
    - 소개 - 기간 - 인증 - 태그 4단계의 따른 챌린지 개설
- 나의 챌린지
    - 오늘 인증해야할 챌린지, 진행 전인 챌린지, 진행중인 챌린지, 완료된 챌린지, 개설한 챌린지를 확인

### Certification

- 챌린지 인증
    - 참여한 챌린지 인증
    - 해당 챌린지의 인증방식(카메라만 사용 or 카메라&갤러리 사용)에 맞는 인증방식

### Feed

- 피드 리스트
    - 사용자들이 실시간으로 인증한 피드 확인
    - 좋아요 및 댓글, 해당 챌린지 확인, 신고 가능

### **Search**

- 챌린지 & 피드 검색
    - 챌린지 제목 또는 태그에 따른 챌린지와 피드 검색
    - 1시간마다 업데이트되는 인기태그로 검색

### **Search**

- 챌린지 & 피드 검색
    - 챌린지 제목 또는 태그에 따른 챌린지와 피드 검색
    - 1시간마다 업데이트되는 인기태그로 검색

### MyPage

- 기록
    - 그 날의 별점, 기록 남기기

- 통계
    - 월별 참여한 챌린지들의 달성률 확인
    - 지난 달과 이번 달 비교
    - 내가 많이 수행한 운동 카테고리, 운동 부위 확인
    - 내 성별 & 나이대의 선호하는 운동 카테고리, 운동 부위 확인

- 참여한 챌린지
    - 참여한 진행전, 진행중, 완료, 개설 챌린지 개수 한 눈에 확인

- 내가 올린 피드
    - 내가 지금까지 인증을 위해 올린 피드 한 눈에 확인

## 😎 페이지소개

---

**메인페이지**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-recommend_(4).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-recommend_(4).png)

**챌린지 리스트**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-creating_(5).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-creating_(5).png)

**챌린지 상세 페이지**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-more-info_310.png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-more-info_310.png)

**로그인 전**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%202.png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%202.png)

**미참여 챌린지**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%203.png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%203.png)

**참여 챌린지**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%204.png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%204.png)

**참여불가 챌린지**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%205.png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%205.png)

**피드**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-creating_(6).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-creating_(6).png)

**피드 hover시**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/_.png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/_.png)

**피드 상세 페이지**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-more-info_310_(3).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-more-info_310_(3).png)

**좋아요, 댓글**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%206.png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%206.png)

**신고**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%207.png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%207.png)

**챌린지 개설**

**소개**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-creating.png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-creating.png)

**기간**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-creating_(1).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-creating_(1).png)

**인증**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-creating_(2).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-creating_(2).png)

**태그**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-creating_(3).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-creating_(3).png)

**챌린지 개설 로딩**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59.png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59.png)

**챌린지 개설 완료**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-more-info_310_(2).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-more-info_310_(2).png)

**추천챌린지**

**선호도 조사**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-more-info_310_(4).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-more-info_310_(4).png)

**추천 챌린지 리스트**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-creating_(4).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-creating_(4).png)

**나의 챌린지**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-recommend_(1).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-recommend_(1).png)

**참여한 챌린지 페이지**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-recommend_(11).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-recommend_(11).png)

**인증 페이지**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-recommend_(12).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-recommend_(12).png)

**인증샷 리스트 페이지**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-recommend_(10).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-recommend_(10).png)

**대화 페이지**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-recommend_(7).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-recommend_(7).png)

**검색**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-recommend_(5).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-recommend_(5).png)

**로그인페이지**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_login.png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_login.png)

**비밀번호 재설정**

**인증번호**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_login_(1).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_login_(1).png)

**비밀번호 변경**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_login_(2).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_login_(2).png)

**회원가입**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_login_(3).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_login_(3).png)

**마이페이지**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-recommend.png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-recommend.png)

**포인트내역 조회**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%208.png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%208.png)

**개인정보 조회**

**비밀번호 인증**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-recommend_(4).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-recommend_(4).png)

**회원정보 수정**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-recommend_(5).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-recommend_(5).png)

**비밀번호 변경**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-recommend_(6).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-recommend_(6).png)

**기록**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-recommend_(2).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/localhost_8081_challenge-recommend_(2).png)

**북마크 페이지**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-recommend_(1).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-recommend_(1).png)

**알림**

**알림이 있을 경우**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%209.png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%209.png)

**알림이 없을 경우**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%2010.png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%2010.png)

**통계**

**월별통계**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/ddd.png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/ddd.png)

**참여통계**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-recommend_(2).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-recommend_(2).png)

**연령별/성별**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-recommend_(3).png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/i4c107.p.ssafy.io_challenge-recommend_(3).png)

**모바일**

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%2011.png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%2011.png)

![%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%2012.png](%E1%84%92%E1%85%A9%E1%86%B7%E1%84%91%E1%85%B5%E1%86%BA%E1%84%8E%E1%85%A2%E1%86%AF%E1%84%85%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B5%20dfd493b142b842e1b5f3dd0d678abe59/Untitled%2012.png)

## 최종산출물

---

- [와이어프레임](https://drive.google.com/file/d/1yUr9eifAJbWc3SAUMdauceqrAFji9P1a/view?usp=sharing)
- [frontend 기능명세](https://drive.google.com/file/d/18Iw9DFrD5QWSYs7O5VqkNMcMcPSUFr5A/view?usp=sharing)
- [ERD](https://drive.google.com/file/d/1nnx7Ydc3gvJIBk96gWPSJj1WETexUep8/view?usp=sharing)
- [중간발표 pdf](https://drive.google.com/file/d/1JCwQNlw5taa8ZX9fH2uNn4neIREscxSS/view?usp=sharing)
- [중간발표 시연영상](https://youtu.be/60zaC1oXmRU)
- [최종발표 pdf](https://drive.google.com/file/d/17YiYjeejA_-toVgXr2DsbcyCSynYGdOo/view?usp=sharing)
- [최종발표 UCC + 시연영상](https://youtu.be/EO9OBAT2cas)
- [전국발표 pdf](https://drive.google.com/file/d/1rURwcB2_yy04ycofexS3ljddT-pv2STm/view?usp=sharing)