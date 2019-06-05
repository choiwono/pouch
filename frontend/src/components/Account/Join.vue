<template>
  <div class="container">
    <div class="row justify-content-center mt-5 mb-5">
      <div class="container text-center">
        <h3>회원가입</h3>
      </div>
      <v-app style="width:30%; background: #ffffff;" id="inspire">
        <v-form
          ref="form"
          v-model="valid"
        >
          <v-text-field
            v-model="name"
            :counter="10"
            :rules="nameRules"
            label="이름"
            required
          ></v-text-field>

          <v-text-field
            v-model="nickName"
            :counter="10"
            :rules="nicknameRules"
            label="닉네임"
            required
          ></v-text-field>

          <div class="row">
            <v-text-field
              ref="email"
              v-model="email"
              :rules="[emailRules.rule1, emailRules.rule2]"
              label="이메일"
              required
              success-messages
            ></v-text-field>

            <v-btn
              color="success"
              @click="emailCheck"
              :rules="emailDuplicateCheck"
              :disabled="emailCheckFlag"
            >
              <span v-if="emailCheckFlag" >체크완료</span>
              <span v-else>중복 확인</span>
            </v-btn>
          </div>


          <v-text-field
            v-model="password"
            :type="'password'"
            :counter="10"
            :rules="[rules.required, rules.min]"
            label="비밀번호"
          ></v-text-field>

          <v-text-field
            v-model="passwordCheck"
            :type="'password'"
            :counter="10"
            :rules="[rules.required, rules.min, comparePasswords]"
            label="비밀번호 확인"
            required
          ></v-text-field>

          <v-btn
            :disabled="!emailFlag || !valid"
            color="success"
            @click="submit()"
          >
            등록
          </v-btn>

        </v-form>
      </v-app>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Join",
    computed: {
      comparePasswords() {
        return this.password === this.passwordCheck || '비밀번호가 일치하지 않습니다';
      },
      emailError() {
        return this.emailFlag === true || '이미 사용중인 이메일입니다.';
      },
      emailDuplicateCheck(){
        return this.emailCheckFlag === true || '이메일 중복 확인을 해주세요.';
      }
    },
    data: () => ({
      valid: true,

      name: '',
      nickName: '',
      nameRules: [
        v => !!v || '이름을 입력하세요',
        v => (v && 2 <= v.length && v.length <= 10) || '최소 2자, 최대 10자로 입력하세요'
      ],
      nicknameRules: [
        v => !!v || '닉네임을 입력하세요',
        v => (v && 2 <= v.length && v.length <= 10) || '최소 2자, 최대 10자로 입력하세요'
      ],
      email: '',
      emailFlag: false,
      emailCheckFlag : false,
      emailRules: {
        rule1: v => !!v || '이메일을 입력하세요',
        rule2: v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || '이메일 양식에 맞춰 입력하세요'
      },

      password: '',
      passwordCheck: '',
      rules: {
        required: value => !!value || '비밀번호를 입력하세요',
        min: v => v.length >= 4 || '최소 4자, 최대 10자로 입력하세요',
      },

    }),
    methods: {
      emailCheck() {
        if(this.$refs.email.validate()){
          let data = new FormData();
          data.append('email', this.email)
          this.$http.post('/accounts/emailcheck', data).then((data) => {
            this.emailFlag = true;
            this.emailCheckFlag = true;
            this.$notify({
              group:'notify',
              title:'성공',
              text:'사용 가능한 이메일입니다.',
              type:'success'
            });
          }).catch((error)=>{
            this.emailFlag = false;
            this.email='';
            this.$notify({
              group:'notify',
              title:'중복된 이메일입니다',
              text:'다른 이메일을 입력해주세요.',
              type:'error'
            });
          })
        }
      },

      submit() {
        if (this.$refs.form.validate()) {
          this.$refs.form.validate()
          let data = new FormData();
          data.append('name', this.name)
          data.append('nickname', this.nickName)
          data.append('email', this.email)
          data.append('passwd', this.password)

          this.$http.post('/accounts/join', data).then((response) => {
            this.$notify({
              group:'notify',
              title:'성공',
              text:'회원가입이 완료되었습니다.',
              type:'success',
              width:'300px'
            });
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
