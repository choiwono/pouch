<template>
  <div class="container">
    <div class="row justify-content-center mt-5 mb-5">
      <div class="card border-0" style="min-width:450px;">
        <div class="card-body">
          <form @submit.prevent="Login" class="form-group">
            <input type="text" class="form-control mb-2" v-model="loginId" name="loginId" placeholder="이메일">
            <input type="password" class="form-control mb-2" v-model="loginPassword" name="loginPassword" placeholder="패스워드"><!---->
            <button type="submit" class="btn btn-md btn-green btn-block">로그인</button>
            <router-link to="/join" class="btn-login btn-md btn-block">회원가입</router-link>
            <!--<router-link to="/findpswd" class="btn-login btn-md btn-block">비밀번호 찾기</router-link>-->
          </form>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
export default {
    name: "Login",
    data() {
      return {
        loginId: '',
        loginPassword: ''
      }
    },
    methods:{
      Login(e){
        if(!this.loginId || !this.loginPassword){
          this.$notify({
            group:'notify',
            title:'로그인 실패',
            text:'이메일과 패스워드를 다시 확인해주세요.',
            type:'error'
          });
        } else {
          let data = new FormData();
          data.append('loginId', this.loginId);
          data.append('loginPassword', this.loginPassword);
          this.$http.post('/login', data).then((response) => {
            this.$http.post('/accounts/auth')
              .then((result) => {
                localStorage.setItem('pouch_user', JSON.stringify(result.email));
                this.$router.push({name: 'home'});
                this.$notify({
                  group: 'notify',
                  title: '로그인',
                  text: '성공했습니다',
                  type: 'success'
                });
                this.$store.commit('changeAuth',{
                  value : true
                });
              })
          }, (err) => {
            this.$notify({
              group: 'notify',
              title: '로그인 실패',
              text: '이메일 혹은 비밀번호를 다시 확인해주세요',
              type: 'error'
            });
          })
        }
      }
    }
}
</script>

<style scoped>
.form-control {
  padding:20px;
}

.btn-green {
  background-color:#00bfa5;
  font-weight:700;
  font-size:1.1rem;
  color:#fff;
  padding:10px;
}
.btn-login {
  font-weight:700;
  color:#00bfa5;
  text-decoration: none;
  margin-top:20px;
  text-align:left;
}

</style>
