<template>
  <div class="container">
    <div class="row justify-content-center mt-5 mb-5">
      <div class="card" style="min-width:450px;">
        <div class="card-header">
          회원가입
        </div>
        <div class="card-body">
          <form id="registerForm" class="form-horizontal">
            <div class="form-group">
              <div class="cols-sm-10">
                <div class="input-group">
                  <input type="text" class="form-control mt-1" v-model="name" name="name" id="name" minlength="2" placeholder="이름.."/>
                </div>
              </div>
            </div>
            <div class="form-group">
              <div class="cols-sm-10">
                <div class="input-group">
                  <input type="text" class="form-control mt-1" v-model="nickname" name="nickname" id="nickname" minlength="2" placeholder="닉네임"/>
                </div>
              </div>
            </div>
            <div class="form-group" id="check-email">
              <div class="cols-sm-10">
                <div class="input-group">
                  <input type="text" class="form-control mt-1" v-model="email" name="email" id="email"  placeholder="이메일.."/>
                </div>
                <button type="button" id="check-id" style="margin-top:7.5px;"  class="btn btn-primary">중복체크</button>
              </div>
            </div>

            <div class="form-group">
              <div class="cols-sm-10">
                <div class="input-group">
                  <input type="password" class="form-control mt-1" v-model="passwd" name="passwd" id="passwd"  placeholder="비밀번호"/>
                </div>
              </div>
            </div>
            <div class="form-group">
              <div class="cols-sm-10">
                <div class="input-group">
                  <input type="password" class="form-control mt-1" v-model="passwd2" name="passwd2" id="passwd2"  placeholder="비밀번호 확인"/>
                </div>
              </div>
            </div>
            <!--<input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}"/>-->
            <input type="submit" class="form-control" value="등 록">
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "Join",
      data: () => ({

      }),
      methods: {

      }
    }
</script>

<style scoped>

</style>
