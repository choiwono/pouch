<template>
  <div class="container">
    <div class="row justify-content-center mt-5 mb-5">
      <div class="container text-center">
        <h3>비밀번호 찾기</h3>
        <h6>회원가입시 등록한 이메일로 임시 비밀번호를 보내드립니다</h6>
      </div>
      <v-app id="inspire">
        <v-form
          ref="form"
          v-model="valid"

        >
            <v-text-field
              ref="email"
              v-model="email"
              label="이메일"
              :rules="emailRules"
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
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'E-mail must be valid'
      ]
    }),
    methods: {
      send() {
        if (this.$refs.form.validate()) {
          this.$refs.form.validate()
          let data = new FormData();
          data.append('email', this.email)

          this.$http.put('/accounts/findpswd', data).then((data) => {
            this.$router.push('login');
            this.$notify({
              group:'notify',
              title:'성공',
              text:'메일이 발송되었습니다.',
              type:'success'
            });
          }).catch((error)=>{
            this.$notify({
              group:'notify',
              title:'실패',
              text:'이메일에 해당하는 계정이 없습니다.',
              type:'error'
            });
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
#inspire {
  width:30%; background: #ffffff;
}
</style>
