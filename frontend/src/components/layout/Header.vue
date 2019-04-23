<template>
  <div>
    <b-navbar class="p-3 border-bottom pl-5 pr-5" fixed="top" toggleable="lg" type="light" variant="white">
      <b-navbar-brand><router-link to="/home">Pouch</router-link></b-navbar-brand>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">

          <b-nav-item-dropdown no-caret right>
            <b-dropdown-form style="display:flex" @submit.prevent="onSubmit">
                <b-form-select
                  :value="null"
                  :options="{ '1': '카테고리', '2': '태그' }"
                  id="searchType"
                  v-model="searchType">
                  <option slot="first" value="" disabled style="width: 80px">구분</option>
                  <!--<option  :value="null" disabled="true" style="width: 80px">구분</option>-->
                </b-form-select>
                <b-form-input id="searchStr2" v-model="searchStr" style="width: 100px"></b-form-input>
                <b-button type="submit" variant="secondary" size="sm" style="width: 100px" >검색</b-button>
            </b-dropdown-form>
            <template slot="button-content"><icon name="search"></icon></template>
          </b-nav-item-dropdown>

          <b-nav-item href="#"><icon name="envelope"></icon></b-nav-item>
          <b-nav-item v-b-modal.modal-link><icon name="plus"></icon></b-nav-item>

          <b-nav-item-dropdown right>
            <!-- Using 'button-content' slot -->
            <template slot="button-content"><icon name="user"></icon></template>
              <router-link to="/login" tag="b-dropdown-item" >로그인</router-link>
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
          <b-form-select v-model="$store.state.categoryId">
            <option value="" disabled>카테고리를 선택해주세요</option>
            <option v-for="item in $store.getters.getCategories" :value="item.id">{{ item.name }}</option>
          </b-form-select>
        </b-form-group>
        <b-form-input v-model="$store.state.url" placeholder="https://..."></b-form-input>
      </form>
    </b-modal>
  </div>
</template>

<script>
  export default {
    name: "Header",
    data(){
      return {
        //categories : [],
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
            localStorage.removeItem('pouch_user');
            alert('로그아웃에 성공하셨습니다.');
        })
        this.$router.push('/login');
      },
      clearName() {
        this.$store.state.url = '',
        this.$store.state.categoryId = ''
      },

      handleOk(bvModalEvt) {
        // Prevent modal from closing
        bvModalEvt.preventDefault()
        //console.log(this.$store.state.url);
        //console.log(this.$store.state.categoryId);
        if (!this.$store.state.url || !this.$store.state.categoryId) {
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
        let data = new FormData();
        data.append('categoryId',this.$store.state.categoryId)
        data.append('url',this.$store.state.url)
        this.clearName(),
          this.$http.post('/crawling/save',data).
          then((response) => {
            alert('정상적으로 추가되었습니다.');
            //this.$router.push('home');
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
          this.$store.state.searchCategory = response;
          console.log(this.$store.state.searchCategory);
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
      const user = JSON.parse(localStorage.getItem('pouch_user'));
      if(user != null){
        this.$http.get('/categories/?email=' + user.email)
          .then((result) => {
            this.$store.commit('changeCategories',{
              arr : result
            });
          })
      }

    }
  }
</script>

<style scoped>
  .navbar-brand > a {
    color:#00bfa5;
    font-weight:700;
  }
</style>
