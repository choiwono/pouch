<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
    <div class="jumbotron jumbotron-fluid bg-light p-3 mt-1">
      <div class="container text-center" v-if="!iconFlag">
        <h3>{{ selectedCategory }}
          <b-button v-b-modal.message id="show-btn1" class="v-btn--round">
            <icon width="20px" height="20px" class="cursor-pointer" name="paper-plane"
            ></icon>
          </b-button>
        </h3>
        <b-modal id="message" title="친구에게 파우치를 보내시겠습니까?" @ok="sendCategory()" centered>
          선택한 파우치: <strong>{{selectedCategory}}</strong>
          <v-form ref="form" v-model="valid">
            <v-text-field ref="email" v-model="email" label="이메일" :rules="emailRules"
                          required @ok="sendCategory()"></v-text-field>
          </v-form>
        </b-modal>
      </div>

      <div class="container text-center" v-else>
        <h5>{{nickName}}의 </h5>
        <h3>{{ selectedCategory }}
          <b-button id="show-btn2" @click="shareModal" class="v-btn--round">
            <icon width="20px" height="20px" class="cursor-pointer" name="share"></icon>
          </b-button>
        </h3>
        <b-modal
          id="share"
          ref="share"
          title="내 파우치에 저장하시겠습니까?"
          @ok="copyCategory()"
          centered>
          선택한 파우치: <strong>{{selectedCategory}}</strong>
        </b-modal>
      </div>
      <b-dropdown id="dropdown-1" text="카테고리를 선택해주세요" variant="light" class="m-md-2" v-if="!iconFlag">
        <b-dropdown-item v-for="item in $store.getters.getCategories" :key="item.id">
          <router-link tag="b-dropdown-item" :to="{ name: 'categories',params:{ id:item.id }}">{{ item.name }}
          </router-link>
        </b-dropdown-item>
      </b-dropdown>
    </div>
    <hr>
    <div class="container">
      <router-link class="cursor-pointer post-tag"
                   v-for="item in tags" :key="item.id" tag="a" :to="{ name: 'categoriesByTag', params:{ tagId:item.id }}">
        {{ item.tagName }}
        <!--<v-badge class="v-badge badge" right color="teal accent-4">
          <span slot="badge">{{ item.cnt }}</span>
        </v-badge>-->
      </router-link>
    </div>
    <div class="container d-flex">
      <!--<ul class="col-sm-12 col-md-2 list-group">
        <router-link v-if="tags.length == 0" class="list-group-item cursor-pointer" tag="li"
                     :to="{ name: 'categories',params:{ id:$store.state.paramsId }}">전체
        </router-link>
      </ul>-->
      <div class="col-md-12">
        <div class="row" id="card-row">
          <div v-for="item in links" :key="item.id" class="col-md-4 mb-4 card-list">
            <div class="card mb-4 shadow-sm links">
              <v-footer>
                <span @click="modifyLink(item.id)" v-if="!iconFlag">
                  <icon name="pen" class="m-2 cursor-pointer"></icon>
                </span>
                <span @click="showModal(item.id)" v-if="!iconFlag">
                   <icon name="tag" class="m-2 cursor-pointer" style="color:#0099cc;"></icon>
                </span>
                <span @click="removeLink(item.id)" v-if="!iconFlag">
                   <icon name="minus-circle" class="m-2 cursor-pointer remove-icon"></icon>
                </span>
                <span @click="linkModal(item.id)"  v-if="iconFlag">
                   <icon name="share" class="m-2 cursor-pointer "></icon>
                </span>
              </v-footer>
              <span v-if="item.src.length === 0">
                <img width="100%" height="120" src="../../../no-image.png"/>
              </span>
              <span v-else>
                <img width="100%" height="120" :src="item.src">
              </span>
              <div class="card-body pb-2">
                <p class="card-title m-2 d-flex">
                  <a target="_blank" :href="item.url" class="link-title">{{ item.title }}</a>
                <p class="m-2">{{ item.regDate.substr(0,10) }}</p>
              </div>
              <b-modal :ref="'title-modal'+item.id" id="category-title" hide-footer lazy centered>
                <div class="d-flex">
                  <v-form ref="title-form">
                    <v-text-field class="sm-10" v-model="linkTitle" autofocus solo required></v-text-field>
                  </v-form>
                  <b-button @click="titleSubmit(item.id)" class="sm-2 modify-button" pill
                            variant="info" type="button">수정</b-button>
                </div>
              </b-modal>
              <b-modal :ref="item.id" title="내 파우치에 저장하시겠습니까?" @ok="saveLink(item.id)" ok-only centered>
                <form @submit.stop.prevent="saveLink()">
                  <p>{{item.title}}</p>
                  <b-form-select id = category variant="light" class="m-sm-2">
                    <option disabled>어떤 카테고리에 저장하시겠습니까?</option>
                    <option v-for="category in $store.getters.getCategories" id="categoryId">
                      {{ category.name }}, {{category.id}}
                    </option>
                  </b-form-select>
                </form>
              </b-modal>
              <b-modal :ref="'tag'+item.id" title="태그편집" @ok="handleOk(item.id)" ok-only centered>
                <form @submit.stop.prevent="handleSubmit()">
                  <ul class="edit-tag-list w-100">
                    <li class="token-input-input-token w-100">
                      <span v-if="linkTags.length > 0">
                        <p v-for="tag in linkTags" class="d-inline-block p-2 border">
                          <span>{{ tag.tagName }}<span type="button" class="close tag-close" @click="removeTagItem($event,tag.id)">x</span>
                          </span>
                        </p>
                      </span>
                      <input autofocus :ref="'tagInput'+item.id" @keyup="tagKeyUp($event,item.id)"
                             type="text" class="border-0" style="outline:none;">
                    </li>
                  </ul>
                </form>
              </b-modal>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import FaIcon from "vue-awesome/components/Icon";

  export default {
    name: "Categories",
    components: {FaIcon},
    options: [],
    props: ['id'],
    data() {
      return {
        valid: true,
        linkTitle:'',
        emailRules: [
          v => !!v || 'E-mail is required',
          v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'E-mail must be valid'
        ],
        tags: [],
        links: [],
        linkTags: [],
        selectedTag: 0,
        selectedCategory: '',
        email: '',
        iconFlag: false
      }
    },
    computed: {


    },
    watch: {
      '$route'(to, from) {
        let id = to.params.id;
        let tagId = to.params.tagId;

        if (id !== '' && id !== undefined && id !== null &&
          tagId !== '' && tagId !== undefined && tagId !== null) {
          this.fetchLinkByTag();
        } else if (id !== '' && id !== undefined && id !== null) {
          this.fetchData();
        }

      }
    },
    methods: {
      linkModal(id) {
        this.$refs[id][0].show();
        this.$http.get("/tags/" + id)
          .then((result) => {
            this.linkTags = result;
          })
      },
      modifyLink(id){
        let links = this.$store.getters.getCategory.links;

        for(let i=0; i<links.length; i++){
          if(links[i].id === id) {
            this.linkTitle = links[i].title;
            break;
          }
        }
        this.$refs['title-modal'+id][0].show();
      },
      saveLink(id, categoryId) {
        let data = new FormData();
        data.append('id', id);
        data.append('categoryId', categoryId);
        this.$http.post('/links/copy', data)
          .then((result) => {
            console.log(result);
          })
      },
      titleSubmit(id){
        let data = new FormData();
        let title = this.linkTitle;
        data.append('name',title);
        this.$http.put("/links/"+id,data).then(() => {
          this.$notify({ group:'notify', title:'수정성공', text:'성공했습니다', type:'success'});
          this.$store.commit('changeLinkTitle',{id:id,title:title});
        })
        this.$nextTick(() => {
          this.$refs['title-modal'+id][0].hide();
        })
      },
      shareModal() {
        this.$refs['share'].show()
      },
      sendCategory() {
        let id = this.$router.history.current.params.id;
        let email = this.email;
        let data = new FormData();
        data.append('id', id);
        data.append('email', email);

        this.$http.post('/accounts/emailcheck', data).then(() => {
          this.$notify({ group:'notify', title:'실패', text:'이메일에 해당하는 계정이 없습니다.', type:'fail' });
          this.email='';
        }).catch(()=>{
          this.$http.post('/categories/send', data)
          this.$notify({ group:'notify', title:'성공', text:'메세지가 전송되었습니다.', type:'success' });
          this.email='';
        })
      },

      copyCategory() {
        let id = this.$router.history.current.params.id;
        let data = new FormData();
        data.append('id', id);
        this.$http.post('/categories/copy', data)
          .then((result) => {
            console.log(result);
            alert("저장되었습니다")
          })
      },
      fetchData() {
        this.$store.state.paramsId = this.$router.history.current.params.id;
        this.$http.get('/categories/' + this.$store.state.paramsId)
          .then((result) => {
            this.$store.state.category = result;
            this.links = this.$store.state.category.links;

            const email = JSON.parse(localStorage.getItem('pouch_user'));
            if (result.email != email) {
              this.iconFlag = true;
            }
            this.selectedCategory = result.name;
            this.nickName = result.nickName;
          });
        this.fetchTag();
      },
      fetchTag() {
        this.$http.get('/tags/?category-id=' + this.$store.state.paramsId)
          .then((result) => {
            this.tags = result;
          })
      },
      fetchCategory(){
        if(this.$store.getters.getCategories.length === 0) {
          const email = JSON.parse(localStorage.getItem('pouch_user'));
          if (email != null) {
            this.$http.get('/categories/?email=' + email)
              .then((result) => {
                this.$store.commit('changeCategories', {
                  arr: result
                });
              })
          }
        }
      },
      fetchLinkByTag() {
        let id = this.$router.history.current.params.id;
        let tagId = this.$router.history.current.params.tagId;

        this.$http.get('/links/?category-id='+id+'&tag-id='+tagId)
          .then((result) => {
            console.log(result);
            this.links = result;
          })
      },
      tagKeyUp(event, id) {
        let key = event.keyCode;
        let keyword = this.$refs['tagInput' + id][0].value;

        if (key === 188 || key === 32 || key === 13) {
          if (keyword.length > 2) {
            this.linkTags.push({id: 0, tagName: keyword.replace(",", "")});
            this.$refs['tagInput' + id][0].value = "";
          }
        }
      },
      removeTagItem(event, id) {
        if (id === 0) {
          event.path[2].remove();
        } else {
          this.$http.delete("/tags/" + id).then((result) => {
            event.path[2].remove();
            this.fetchTag();
          })
        }
      },
      handleOk(id) {
        this.handleSubmit(id);
      },
      handleSubmit(id) {
        let arr = new Array();
        let tags = this.linkTags;
        let data = new FormData();
        for (let key in tags) {
          if (tags[key].id === 0) {
            arr.push(tags[key].tagName);
          }
        }
        data.append('id', id);
        data.append('tags', arr);
        this.$http.post("/tags", data).then((result) => {
          this.fetchTag();
        })
      },
      showModal(id) {
        this.$refs['tag'+id][0].show();
        this.$http.get("/tags/" + id)
          .then((result) => {
            this.linkTags = result;
          })
      },
      removeLink(id) {
        let val = confirm("정말 삭제하시겠습니까?");
        if (val === true) {
          this.$http.delete("/links/"+id).then(() => {
            this.$notify({ group:'notify', title:'성공', text:'삭제 되었습니다', type:'success' });
          });
          this.fetchData();
        }
      }
    },
    mounted() {
      this.fetchCategory();
      this.fetchData();
    }
  }
</script>

<style scoped>
  @media only screen and (max-width:768px) {
    .d-flex {
      display:block !important;
    }
    .list-group {
      margin:0px;
      max-width:500px;
      padding-bottom:35px;
      padding-left:15px;
      padding-right:15px;
    }
  }

  .badge {
    padding-bottom: 2.5px !important;
  }

  .card-title > a {
    color: #00bfa5;
    font-weight: 700;
    text-decoration: none;
    display: block;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    width: 200px;
  }
  .list-group-item {
    background:none;
  }
  .list-group-item > a {
    font-size: 0.9rem;
    color: #161613;
    font-weight: 400;
    text-decoration: none;
  }

  .list-group-item.active {
    background-color: white;
    border-color: #00bfa5;
    color: #161613;
    cursor: pointer;
  }

  .router-link-active {
    border-color : #00bfa5;
  }

  .tag-span {
    border: 1px solid #00bfa5;
    padding: 5px;
    margin: 3px;
    border-radius: 4px;
    background-color: #00bfa5;
    color: white;
    font-weight: 700;
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

  .token-input-input-token {
    float: left;
    margin: 3px 0 3px 5px;
    text-align: left;
  }

  .edit-tag-list {
    border-radius: 4px;
    border: 1px solid #ccc;
    font-size: 14px;
    font-weight: 600;
    float: left;
    padding: 3px 6px 0;
    width: 100%;
    margin: 0;
    min-height: 38px;
    list-style: none;
  }

  .tag-close {
    border: 2px solid #ddd;
    height: 22px;
    font-size: 1.25rem;
    line-height: 0.6;
    padding: 1.5px;
    margin-left: 2px;
  }

  .post-tag {
    display:inline-block;
    background: #dee2e6;
    padding: 0.75rem 1rem 0.75rem 1rem;
    margin-right: 0.5rem;
    border-radius: 10px;
    text-decoration:none;
    color: #505050;
    font-family: "PT Sans",Helvetica,Arial,sans-serif;
    font-size: 90%;
    -webkit-transition: all 0.1s ease-in-out;
    -moz-transition: all 0.1s ease-in-out;
    transition: all 0.1s ease-in-out;
  }
  .post-tag:hover {
    background-color:#00bfa5;
    color:white;
  }
  .router-link-active {
    background: #00bfa5;
    color:white;
  }
</style>
