<template>
  <div class="container">
    <div class="row justify-content-center mt-5 mb-5">
      <div class="card" style="min-width:450px;">
        <div class="card-header">
          <h4>로그인</h4>
        </div>
        <div class="card-body">
          <form @submit.prevent="Login" class="form-group">
            <input type="text" class="form-control mb-2" v-model="loginId" name="loginId" placeholder="이메일">
            <input type="password" class="form-control mb-2" v-model="loginPassword" name="loginPassword" placeholder="패스워드">
            <button type="submit" class="btn btn-lg btn-primary btn-block">로그인</button>
            <router-link to="/join" class="btn btn-md btn-secondary btn-block">회원가입</router-link>
            <router-link to="/findpswd" class="btn btn-md btn-outline-secondary btn-block">비밀번호 찾기</router-link>
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
        var data = new FormData();
        data.append('loginId',this.loginId);
        data.append('loginPassword',this.loginPassword);
        this.$http.post('/login',data).
        then((response) => {
          this.$http.post('/accounts/auth')
            .then((result) => {
              localStorage.setItem('pouch_user',JSON.stringify(result.email));
              this.$router.push({name:'home'});
          })
        }, (err) => {
          console.log('err', err)
        })
      }
    }
}
</script>

<style scoped>

</style>
