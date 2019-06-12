<template>
  <div>
    <div class="container main pt-5" style="padding: 24px; width: 100%;">
      <form action="https://google.com/search"
            target="_blank"
            method="get"
            ref="form" class="mt-3">
        <div class="form-group input-group justify-content-center" id="google_search_form">
          <input type="text" name="q" maxlength="255" v-model="q" class="form-control m-0"
                 style="max-width:500px;" placeholder="Google Search">
          <div class="input-group-append">
            <button class="btn btn-secondary" type="submit" @click="googleSearch">
              <icon name="search"></icon>
            </button>
          </div>
        </div>
      </form>
    </div>
    <hr class="my-4 mt-0">
    <div class="container">
      <div class="row">
        <v-flex md3 sm12 xs12>
          <v-card hover class="first-card">
            <v-card-text v-b-modal.category-link class="vertical-center">
              <img style="opacity:0.5;" class="rounded mx-auto d-block"
                   src="https://img.icons8.com/ios/50/000000/plus-math.png">
            </v-card-text>
          </v-card>
        </v-flex>
        <v-flex md3 sm12 xs12 :key="item.id" v-for="item in $store.getters.getCategories">
          <b-modal
            :ref="'title-modal'+item.id"
            id="category-title" hide-footer lazy centered>
            <div class="d-flex">
              <v-form ref="title-form">
                <v-text-field class="sm-10" v-model="categoryTitle" autofocus solo required>
                </v-text-field>
              </v-form>
              <b-button @click="titleSubmit(item.id)" class="sm-2 modify-button"
                        pill variant="info" type="button">수정
              </b-button>
            </div>
          </b-modal>
          <v-layout column>
            <v-card class="card-title">
              <router-link :to="{ name: 'categories',params:{ id:item.id }}">
                <v-card-text class="vertical-center">
                  <h4 class="mx-auto d-block">{{ item.name }}</h4>
                </v-card-text>
              </router-link>
              <v-footer>
                  <span @click="modifyCategory(item.id)">
                    <icon name="edit" class="edit-icon ml-2 mr-1 cursor-pointer"></icon>
                  </span>
                <span @click="deleteCategory(item.id)">
                    <icon name="minus-circle" class="remove-icon ml-1 mr-1 cursor-pointer"></icon>
                  </span>
              </v-footer>
            </v-card>
          </v-layout>
        </v-flex>
      </div>
    </div>
    <b-modal ref="modal" id="category-link" title="카테고리를 추가해주세요" @ok="handleOk" ok-only>
      <form @submit.stop.prevent="handleSubmit">
        <b-form-input v-model="categoryName" placeholder="카테고리 이름"></b-form-input>
      </form>
    </b-modal>
  </div>
</template>

<script>
  import Header from './layout/Header'

  export default {
    name: 'HelloWorld',
    data() {
      return {
        boxOne: '',
        q: '',
        categoryName: '',
        categoryTitle: ''
      }
    },
    methods: {
      titleSubmit(id) {
        if (!this.categoryTitle) {
          alert('카테고리 이름을 입력해주세요.');
        } else {
          let data = new FormData();
          data.append('name', this.categoryTitle);
          this.$http.put("/categories/" + id, data).then(() => {
            this.$notify({group: 'notify', title: '수정성공', text: '성공했습니다', type: 'success'});
            this.fetchCategory();
          })
          this.$nextTick(() => {
            this.$refs['title-modal' + id][0].hide();
          })
        }
      },
      deleteCategory(id) {
        let val = confirm("삭제하시겠습니까?");
        if (val === true) {
          this.$http.delete("/categories/" + id)
            .then(() => {
              this.$notify({group: 'notify', title: '삭제성공', text: '성공했습니다', type: 'success', width: '300px'});
              this.fetchCategory();
            });
        }
      },
      modifyCategory(id) {
        for (let i = 0; i < this.$store.getters.getCategories.length; i++) {
          let category = this.$store.getters.getCategories[i];
          if (category.id === id) {
            this.categoryTitle = category.name;
          }
          ;
        }
        this.$refs['title-modal' + id][0].show();
      },
      googleSearch() {
        this.$refs.form.submit()
      },
      handleOk(bvModalEvt) {
        bvModalEvt.preventDefault();
        if (!this.categoryName) {
          alert('카테고리 이름을 입력해주세요.');
        } else {
          this.handleSubmit();
        }
      },
      handleSubmit() {
        let data = new FormData();
        data.append('name', this.categoryName);
        this.categoryName = '';
        this.$http.post('/categories/', data).then(() => {
          this.$notify({
            group: 'notify',
            title: '데이터 저장성공',
            text: '성공했습니다',
            type: 'success',
            width: '300px'
          });
          const user = JSON.parse(localStorage.getItem('pouch_user'));
          if (user != null) {
            this.$http.get('/categories/?email=' + user)
              .then((result) => {
                this.$store.state.categories = result;
              })
          }
        }, (err) => {
          console.log('err', err)
        }),

          this.$nextTick(() => {
            this.$refs.modal.hide();
          })
      },
      fetchCategory() {
        const user = JSON.parse(localStorage.getItem('pouch_user'));
        if (user != null) {
          this.$http.get('/categories/?email=' + user)
            .then((result) => {
              this.$store.commit('changeCategories', {
                arr: result
              });
            })
        }
      },
    },

    computed: {},

    mounted() {
      this.fetchCategory();
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .body {
    padding-right: 0px !important;
  }

  .first-card {
    margin: 10px 15px 10px 15px;
    min-height: 200px;
  }

  .card-title {
    max-height: 200px;
    min-height: 200px;
    margin: 10px 15px 10px 15px;
  }

  .btn-secondary {
    background-color: #00bfa5;
    border-color: #00bfa5;
  }

  .vertical-center {
    margin: 0;
    position: absolute;
    top: 50%;
    -ms-transform: translateY(-50%);
    transform: translateY(-50%);
    color: #00bfa5;
  }

  .mx-auto:hover {
    font-weight: 800;
  }

  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
    text-decoration: none;
  }

  .cursor-pointer {
    cursor: pointer;
  }

  .edit-icon {
    color: #20B2AA;
  }

  .remove-icon {
    color: #DC143C;
  }
</style>
