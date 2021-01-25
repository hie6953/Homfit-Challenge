<template>
    <div class="background">
    <div class="container  col-sm-10 col-lg-6">
        <form>
            <h3>비밀번호 변경</h3>
            <input
                id="email"
                placeholder="ssafy@ssafy.com"
                class="sign-in-form__form__input form-control sign-in-form__form__email"
                readonly
            />
            
            <input
                placeholder="변경할 비밀번호를 입력해주세요." 
                autofocus="autofocus" 
                class="sign-in-form__form__input form-control" 
                type="password" 
                id="password"
            >

            <input
                placeholder="변경할 비밀번호를 다시 한 번 입력해주세요." 
                autofocus="autofocus" 
                class="sign-in-form__form__input form-control" 
                type="password"
                id="password-confirm"
            />

            <button
                class="sign-in-form__form__submit btn btn-priority"
                @click="onPassword"
                :disabled="!isSubmit"
            >
                저장
            </button>

        </form>
    </div>
    </div>
</template>

<script>
import "../assets/css/forgotpassword.css";

export default {

    data: () => ({

    })

}

</script>