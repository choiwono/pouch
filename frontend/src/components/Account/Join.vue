<template>
  <div class="container">
    <div class="row justify-content-center mt-5 mb-5">
      <!--<div class="jumbotron jumbotron-fluid bg-light p-3 mt-1">-->
      <div class="container text-center">
        <h3>회원가입</h3>
      </div>
      <v-app style="width:50%; background: #ffffff;" id="inspire"  >
        <v-form
          ref="form"
          v-model="valid"
          lazy-validation
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
            :rules="nameRules"
            label="닉네임"
            required
          ></v-text-field>

          <div class="row">
            <v-text-field
              v-model="email"
              :rules="emailRules"
              label="이메일"
              required style="width:10%"
            ></v-text-field>

            <v-btn
              color="success"
              @click="validate"
            >
              중복 확인
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
          :counter="10"
          :rules="[rules.required, rules.min, rules.passwordMatch]"
          :type="'password'"
          label="비밀번호 확인"
          required
        ></v-text-field>

          <v-btn
          color="success"
          @click="submit"
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
    data: () => ({
      valid: true,
      name:'',
      nickName: '',
      nameRules: [
        v => !!v || 'NickName is required',
        v => (v && 2 <= v.length && v.length <= 10) || 'Name must be less than 10 characters'
      ],
      email: '',
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+/.test(v) || 'E-mail must be valid'
      ],

      password: '',
      passwordCheck: '',
      rules: {
        required: value => !!value || 'Required.',
        min: v => v.length >= 4 || 'Min 4 characters',
        passwordMatch: v => (this.password === v) || '비밀번호가 일치하지 않습니다'

        // emailMatch: () => ('비밀번호가 일치하지 않습니다')
      }

    }),
    methods: {
      validate() {
        if (this.$refs.form.validate()) {
          this.snackbar = true
        }
      },
      reset() {
        this.$refs.form.reset()
      },
      submit(){
        let data = new FormData();
        data.append('name', this.name)
        data.append('nickname', this.nickName)
        data.append('email', this.email)
        data.append('passwd', this.password)

        this.$http.post('/accounts/join', data).
        then((response) => {
          alert('환영합니다');
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
</script>

<style scoped>

</style>
