<template>
  <div class="container">
    <div class="row justify-content-center mt-5 mb-5">
      <div class="card" style="min-width:450px;">
        <div class="card-header">
          로그인
        </div>
        <div class="card-body">
          <form @submit.prevent="Login" class="form-group">
            <input type="text" class="form-control mb-2" v-model="loginId" name="loginId" placeholder="이메일">
            <input type="password" class="form-control mb-2" v-model="loginPassword" name="loginPassword" placeholder="패스워드">
            <button type="submit" class="btn btn-lg btn-primary btn-block">로그인</button>
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
          if(response.status === 200){
            localStorage.setItem("user","testUser");
            this.$router.push('home');
          }
        }, (err) => {
          console.log('err', err)
        })
      }
    }
}
</script>

<style scoped>

</style>
