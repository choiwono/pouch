<template>
  <div>
    <b-navbar class="p-3 border-bottom pl-5 pr-5" fixed="top" toggleable="lg" type="light" variant="white">
      <b-navbar-brand><router-link to="/home">Pouch</router-link></b-navbar-brand>
      <b-collapse id="nav-collapse" is-nav>
        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-item href="#"><icon name="search"></icon></b-nav-item>
          <b-nav-item href="#"><icon name="envelope"></icon></b-nav-item>
          <b-nav-item v-b-modal.modal-link><icon name="plus"></icon></b-nav-item>
          <b-nav-item-dropdown right>
            <!-- Using 'button-content' slot -->
            <template slot="button-content"><icon name="user"></icon></template>
              <b-dropdown-item><router-link to="/login">로그인</router-link></b-dropdown-item>
              <b-dropdown-item><router-link to="/logout">로그아웃</router-link></b-dropdown-item>
            <b-dropdown-item><router-link to="/join">회원가입</router-link></b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <b-modal
      id="modal-link"
      ref="modal"
      title="링크를 추가해주세요"
      @ok="handleOk"
      @shown="clearName"
    >
      <form @submit.stop.prevent="handleSubmit">
        <b-form-group id="input-group-3" label-for="input-3">
          <b-form-select v-model="categoryId">
            <option value="" disabled>카테고리를 선택해주세요</option>
            <option v-for="category in categories" :value="category.id">{{ category.name }}</option>
          </b-form-select>
        </b-form-group>
        <b-form-input v-model="url" placeholder="https://..."></b-form-input>
      </form>
    </b-modal>
  </div>
</template>

<script>
    export default {
        name: "Header",
        data(){
          return {
            url : '',
            categoryId: '',
            categories : [

            ]
          }
        },
        methods: {
          logout(){
            console.log("로그아웃")
            this.$router.push('/home');
          },
          clearName() {
            this.url = '',
            this.categoryId = ''
          },
          handleOk(bvModalEvt) {
            // Prevent modal from closing
            bvModalEvt.preventDefault()
            if (!this.url && !this.categoryId) {
              alert('URL주소와 카테고리를 입력해주세요')
            } else {
              this.handleSubmit()
            }
          },
          handleSubmit() {
            this.clearName(),
              //var data = new FormData();
              //data.append('categoryId',this.categoryId);
              this.$http.post('/links/1',data).
              then((response) => {
                if(response.status === 200){
                  this.$router.push('home');
                }
              }, (err) => {
                console.log('err', err)
              })
            this.$nextTick(() => {
              // Wrapped in $nextTick to ensure DOM is rendered before closing
              this.$refs.modal.hide()
            })
          }
        },
        created(){
          this.$http.get('/categories/1')
            .then((result) => {
              console.log(result);
              this.categories = result.data;
            })
        }
    }
</script>

<style scoped>

</style>
