<template>
  <div>
    <b-navbar class="p-3 border-bottom pl-5 pr-5" fixed="top" toggleable="lg" type="light" variant="white">
      <b-navbar-brand><router-link to="/home">Pouch</router-link></b-navbar-brand>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">

          <b-nav-item-dropdown no-caret dropleft>
            <b-dropdown-form @submit.prevent="onSubmit">
                <b-form-select
                  :value="null"
                  :options="{ '1': '카테고리', '2': '태그' }"
                  id="searchType"
                  v-model="searchType"
                >
                  <option slot="first" :value="null" disabled="true">구분</option> </b-form-select>

                <b-form-input id="searchStr2" v-model="searchStr"></b-form-input>
                <b-button type="submit" variant="secondary" size="sm" >검색</b-button>
              <!--@click="onClick"-->
            </b-dropdown-form>
            <template slot="button-content"><icon name="search"></icon></template>

          </b-nav-item-dropdown >

          <b-nav-item href="#"><icon name="envelope"></icon></b-nav-item>
          <b-nav-item v-b-modal.modal-link><icon name="plus"></icon></b-nav-item>

          <b-nav-item-dropdown right>
            <!-- Using 'button-content' slot -->
            <template slot="button-content"><icon name="user"></icon></template>
              <router-link to="/login" tag="b-dropdown-item">로그인</router-link>
              <b-dropdown-item @click="logout">로그아웃</b-dropdown-item>
              <router-link to="/join" tag="b-dropdown-item">회원가입</router-link>
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
            categories : [],
            selected: null,
            options: [
              { value: null, text: '구분', disabled:true},
              { value: 'category', text: '카테고리' },
              { value: 'tag', text: '태그' }],
            searchType: '',
            searchStr: '',
            list: []
          }
        },
        methods: {
          logout(){
            console.log("로그아웃");
            this.$http.get('/logout')
              .then((result) => {
              this.$cookies.remove('Token');
            })
            this.$router.push('/login');
          },
          clearName() {
            this.url = '',
            this.categoryId = ''
          },

          handleOk(bvModalEvt) {
            // Prevent modal from closing
            bvModalEvt.preventDefault()
            if (!this.url && !this.categoryId) {
              alert("url와 카테고리를 반드시 선택해주세요.");
            } else {
              this.handleSubmit()
            }
          },

          clearSearch(){
            this.searchType= null,
              this.searchStr = ''
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
          },
          onSubmit() {

            let searchType = this.searchType;
            let searchStr = this.searchStr;
            //this.dropdown.hide(true),
            //this.clearSearch(),
            this.$http.get('/categories/search?searchType='+searchType+'&searchStr='+searchStr).
            then((response) => {
              searchType = '';
              searchStr = '';
              console.log(response);
              this.$router.push({name:'search'});
            }, (err) => {
              console.log('err', err)
            })
            this.$nextTick(() => {
              // Wrapped in $nextTick to ensure DOM is rendered before closing
              //this.$refs.modal.hide()
            })
          }
        },
        mounted(){
          if(this.$cookies.isKey('Token')){
            this.$http.get('/categories/?email='+this.$cookies.get('Token'))
              .then((result) => {
                this.categories = result;
            })
          }
        },
    }
</script>

<style scoped>

</style>
