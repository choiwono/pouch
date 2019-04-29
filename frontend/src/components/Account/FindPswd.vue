<template>
  <div class="container">
    <div class="row justify-content-center mt-5 mb-5">
      <div class="container text-center">
        <h3>비밀번호 찾기</h3>
        <h6>회원가입시 등록한 이메일로 비밀번호를 보낸다</h6>
      </div>
      <v-app style="width:30%; background: #ffffff;" id="inspire">
        <v-form
          ref="form"
          v-model="valid"

        >
            <v-text-field
              ref="email"
              v-model="email"
              label="이메일"
              :rules="[emailRules.rule2()]"
              required
            ></v-text-field>

          <v-btn
            color="success"
            @click="send()"
          >
            email 보내기
          </v-btn>
        </v-form>
      </v-app>
    </div>
  </div>
</template>

<script>
  export default {
    name: "FindPswd",
    data: () => ({
      valid: true,
      email: '',
      emailRules: {
        rule1: v => !!v || 'E-mail is required',
        rule2: v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'E-mail must be valid'
      }
    }),
    methods: {
      send() {
        if (this.$refs.form.validate()) {
          this.$refs.form.validate()
          let data = new FormData();
          data.append('email', this.email)

          this.$http.put('/accounts/findpswd', data).then((response) => {
            alert('메일이 발송되었습니다');
            this.$router.push('login');
          }, (err) => {
            console.log('err', err)
          })
          this.$nextTick(() => {
            // Wrapped in $nextTick to ensure DOM is rendered before closing
          })
        }
      }
    }
  }
</script>

<style scoped>

</style>
