<template>
  <div class="container">
    <div class="row justify-content-center mt-5 mb-5">

      <v-form
        ref="form"
        v-model="valid"
        lazy-validation
      >
        <v-text-field
          v-model="nickName"
          :counter="10"
          :rules="nameRules"
          label="닉네임"
          required
        ></v-text-field>

        <v-text-field
          v-model="email"
          :rules="emailRules"
          label="이메일"
          required
        ></v-text-field>

        <v-text-field
          v-model="password"
          :type="'password'"
          :counter="10"
          :rules="[rules.required, rules.min]"
          label="비밀번호"
        ></v-text-field>

        <v-text-field
          v-model="password2"
          :counter="10"
          :rules="[rules.required, rules.min]"
          :type="'password'"
          label="비밀번호 확인"
          required
        ></v-text-field>

        <v-btn
          :disabled="!valid"
          color="success"
          @click="validate"
        >
          Validate
        </v-btn>

        <v-btn
          color="error"
        >
          Reset Form
        </v-btn>

        <v-btn
          color="warning"
          @click="resetValidation"
        >
          Reset Validation
        </v-btn>
        <v-btn
          color="error"
        >
          Reset Form
        </v-btn>
      </v-form>

      <!--<div class="card" style="min-width:450px;">-->
        <!--<div class="card-header">-->
          <!--회원가입-->
        <!--</div>-->
        <!--<div class="card-body">-->
          <!--<form id="registerForm" class="form-horizontal">-->
            <!--<div class="form-group">-->
              <!--<div class="cols-sm-10">-->
                <!--<div class="input-group">-->
                  <!--<input type="text" class="form-control mt-1" v-model="name" name="name" id="name" minlength="2" placeholder="이름"/>-->
                <!--</div>-->
              <!--</div>-->
            <!--</div>-->
            <!--<div class="form-group">-->
              <!--<div class="cols-sm-10">-->
                <!--<div class="input-group">-->
                  <!--<input type="text" class="form-control mt-1" v-model="nickname" name="nickname" id="nickname" minlength="2" placeholder="닉네임"/>-->
                <!--</div>-->
              <!--</div>-->
            <!--</div>-->
            <!--<div class="form-group" id="check-email">-->
              <!--<div class="cols-sm-10">-->
                <!--<div class="input-group">-->
                  <!--<input type="text" class="form-control mt-1" v-model="email" name="email" id="email"  placeholder="이메일"/>-->
                <!--</div>-->
                <!--<button type="button" id="check-id" style="margin-top:7.5px;"  class="btn btn-primary">중복 체크</button>-->
              <!--</div>-->
            <!--</div>-->

            <!--<div class="form-group">-->
              <!--<div class="cols-sm-10">-->
                <!--<div class="input-group">-->
                  <!--<input type="password" class="form-control mt-1" v-model="passwd" name="passwd" id="passwd"  placeholder="비밀번호"/>-->
                <!--</div>-->
              <!--</div>-->
            <!--</div>-->
            <!--<div class="form-group">-->
              <!--<div class="cols-sm-10">-->
                <!--<div class="input-group">-->
                  <!--<input type="password" class="form-control mt-1" v-model="passwd2" name="passwd2" id="passwd2"  placeholder="비밀번호 확인"/>-->
                <!--</div>-->
              <!--</div>-->
            <!--</div>-->
            <!--&lt;!&ndash;<input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"/>&ndash;&gt;-->
            <!--<input type="submit" class="form-control btn-primary" value="등 록">-->
          <!--</form>-->
        <!--</div>-->
      <!--</div>-->
    </div>
  </div>
</template>

<script>
    export default {
        name: "Join",
      data: () => ({
        valid: true,
        name: '',
        nameRules: [
          v => !!v || 'NickName is required',
          v => (v && 2<= v.length && v.length <= 10) || 'Name must be less than 10 characters'
        ],
        email: '',
        emailRules: [
          v => !!v || 'E-mail is required',
          v => /.+@.+/.test(v) || 'E-mail must be valid'
        ],

        password: '',
        rules: {
          required: value => !!value || 'Required.',
          min: v => v.length >= 4 || 'Min 4 characters',
          emailMatch: () => ('The email and password you entered don\'t match')
        }

      }),
      methods: {
        validate () {
          if (this.$refs.form.validate()) {
            this.snackbar = true
          }
        },
        reset () {
          this.$refs.form.reset()
        },
        resetValidation () {
          this.$refs.form.resetValidation()
        }

      }
    }
</script>

<style scoped>

</style>
