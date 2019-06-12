<template>
  <div>
    <b-navbar class="p-3 border-bottom pl-3 pr-3" fixed="top" toggleable="lg" type="light" variant="white">
      <b-navbar-brand>
        <router-link to="/home">POUCH</router-link>
      </b-navbar-brand>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="ml-auto">
          <b-nav-item-dropdown no-caret right ref="dropdown">
            <b-dropdown-form style="display:flex; min-width:380px;" @submit.prevent="onSubmit">
              <b-form-select
                :value="null"
                :options="{ '1': '카테고리', '2': '태그' }"
                :rules="[v => !!v || 'Item is required']"
                id="searchType"
                v-model="searchType"
                required class="col-lg-5 col-md-5">
                <option slot="first" value="" disabled style="width: 80px">구분</option>
              </b-form-select>
              <b-form-input :rules="nameRules" id="searchStr" v-model="searchStr" style="min-width: 100px"
                            required></b-form-input>
              <b-button type="submit" variant="secondary" size="sm" style="min-width: 50px">검색</b-button>
            </b-dropdown-form>
            <template v-if="$store.getters.getAuth" slot="button-content">
              <icon name="search"></icon>
            </template>
          </b-nav-item-dropdown>
          <b-nav-item v-if="$store.getters.getAuth" to="/messages">
            <icon name="envelope"></icon>
            <b-badge v-if="$store.messageFlag">New</b-badge>
          </b-nav-item>
          <b-nav-item v-if="$store.getters.getAuth" @click="showModalLink">
            <icon name="plus"></icon>
          </b-nav-item>
          <b-nav-item-dropdown right>
            <!-- Using 'button-content' slot -->
            <template slot="button-content">
              <icon name="user"></icon>
            </template>
            <router-link v-if="!$store.getters.getAuth" to="/login" tag="b-dropdown-item">로그인</router-link>
            <b-dropdown-item v-if="$store.getters.getAuth" @click="logout">로그아웃</b-dropdown-item>
            <router-link v-if="!$store.getters.getAuth" to="/join" tag="b-dropdown-item">회원가입</router-link>
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
    data() {
      return {
        //categories : [],
        messageFlag:false,
        selected: null,
        options: [
          {value: null, text: '구분', disabled: true},
          {value: 'category', text: '카테고리2'},
          {value: 'tag', text: '태그'}],
        searchType: '',
        searchStr: '',
        list: [],
        valid: true,
        name: '',
        nameRules: [
          v => !!v || 'Name is required',
          v => (v && v.length >= 2) || 'Keyword must be more than 2 characters'
        ]
      }
    },

    methods: {
      logout() {
        this.$http.get('/logout')
          .then((result) => {
            localStorage.removeItem('pouch_user');
            this.$notify({
              group: 'notify',
              title: '로그아웃',
              text: '성공했습니다',
              type: 'success',
              width: '100%'
            });
            this.$store.commit('changeAuth', {
              value: false
            });
          });
        this.$router.push('/login');
      },
      clearName() {
        this.$store.state.url = '',
          this.$store.state.categoryId = ''
      },

      handleOk(bvModalEvt) {
        bvModalEvt.preventDefault()
        if (!this.$store.state.url || !this.$store.state.categoryId) {
          this.$notify({
            group: 'notify',
            title: '입력 실패',
            text: 'URL과 카테고리값을 반드시 넣어주세요',
            type: 'error'
          });
        } else {
          this.handleSubmit()
        }
      },
      handleSubmit() {
        let data = new FormData();
        let categoryId = this.$store.state.categoryId;
        data.append('categoryId', categoryId);
        data.append('url', this.$store.state.url);
        this.clearName(),
          this.$http.post('/crawling/save', data).then((response) => {
            this.$notify({group: 'notify', title: '성공', text: '데이터가 저장되었습니다.', type: 'success'});
            //this.$store.commit('getCategoryById',{id:categoryId});
            this.$store.commit('addLinkList', {id: categoryId, link: response});
          }, (err) => {
            console.log('err', err)
          })
        this.$nextTick(() => {
          this.$refs.modal.hide();
        })
      },
      onSubmit() {
        let searchType = this.searchType;
        let searchStr = this.searchStr;

        this.$http.get('/categories/search?searchType=' + searchType + '&searchStr=' + searchStr).then((response) => {
          this.searchType = '';
          this.searchStr = '';
          this.$refs.dropdown.hide(true)
          this.$store.state.searchCategory = response;
          this.$router.push({name: 'search'});
        }, (err) => {
          console.log('err', err)
        })
      },
      showModalLink() {
        const email = JSON.parse(localStorage.getItem('pouch_user'));
        if (email != null) {
          this.$http.get('/categories/?email=' + email)
            .then((result) => {
              this.$store.commit('changeCategories', {
                arr: result
              });
              this.$refs['modal'].show();
            })
        } else {
          status.code(401);
        }
      }
    },


    //TODO 화면이 전환될때 메서드를 실행시켜서 새 메세지를 확인하고 싶은데
    // 지금은 홈 화면으로 돌아가야만 실행되는듯 하다
    mounted() {
      this.$http.get("/messages").then((result)=>{
            this.$store.messageFlag=true;})

    }
  }
</script>

<style scoped>
  .navbar-brand > a {
    color: #00bfa5;
    font-weight: 700;
    text-decoration: none;
  }
</style>
