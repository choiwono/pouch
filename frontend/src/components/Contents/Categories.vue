<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
    <div class="jumbotron jumbotron-fluid bg-light p-3 mt-1">
      <div class="container text-center">
        <h3>{{ selectedCategory }}<icon v-b-modal.share class="ml-3 cursor-pointer" name="share"></icon></h3>

        <b-dropdown id="dropdown-1" text="카테고리를 선택해주세요" variant="light" class="m-md-2">
          <b-dropdown-item v-for="item in $store.getters.getCategories" :key="item.id">
            <router-link tag="b-dropdown-item" :to="{ name: 'categories',params:{ id:item.id }}">{{ item.name }}</router-link>
          </b-dropdown-item>
        </b-dropdown>
        <b-modal
          id="share"
          title="내 파우치에 저장하시겠습니까?"
          @ok="shareCategory()"
          centered>
          선택한 파우치: <strong>{{selectedCategory}}</strong>
        </b-modal>
      </div>
    </div>
    <hr>
    <div class="container d-flex">
      <ul class="col-md-2 list-group">
          <router-link class="list-group-item cursor-pointer" tag="li" :to="{ name: 'categories',params:{ id:$store.state.paramsId }}">
            전체
          </router-link>
          <router-link @click="selectedTag = item.id" class="list-group-item cursor-pointer" v-for="item in tags" :key="item.id" tag="li" :to="{ name: 'categoriesByTag',params:{ tagId:item.id }}">
            {{ item.tagName }}
            <v-badge class="v-badge badge" right color="teal accent-4">
              <span slot="badge">{{ item.cnt }}</span>
            </v-badge>
          </router-link>
      </ul>
      <div class="col-md-10">
        <div class="row" id="card-row">
          <div v-for="item in links" :key="item.id" class="col-md-4 mb-4 card-list">
            <div class="card mb-4 shadow-sm links">
              <v-footer>
                <span>
                  <icon name="pen" class="m-2 cursor-pointer"></icon>
                </span>
                <span @click="showModal(item.id)">
                   <icon name="tag" class="m-2 cursor-pointer" style="color:#0099cc;"></icon>
                </span>
                <span @click="removeLink(item.id)">
                   <icon name="minus-circle" class="m-2 cursor-pointer remove-icon"></icon>
                </span>
              </v-footer>
              <span v-if="item.src.length === 0">
                <svg class="bd-placeholder-img card-img-top" width="100%" height="180"
                     xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice"
                     focusable="false" role="img" aria-label="Placeholder: Thumbnail">
                  <title>Placeholder</title>
                  <rect width="100%" height="100%" fill="#333"></rect>
                </svg>
              </span>
              <span v-else>
                <img width="100%" height="180" :src="item.src">
              </span>
              <div class="card-body pb-2">
                <p class="card-title m-2 d-flex">
                  <a target="_blank" :href="item.url" class="link-title">{{ item.title }}</a>
                <p class="m-2">{{ item.regDate.substr(0,10) }}</p>
              </div>
              <b-modal
                :ref="item.id"
                title="태그편집"
                @ok="handleOk(item.id)"
                ok-only
                centered>
                <form @submit.stop.prevent="handleSubmit()">
                  <ul class="edit-tag-list w-100">
                    <li class="token-input-input-token w-100">
                      <span v-if="linkTags.length > 0">
                        <p v-for="tag in linkTags" class="d-inline-block p-2 border">
                          <span>
                            {{ tag.tagName }}
                            <span type="button" class="close tag-close" @click="removeTagItem($event,tag.id)">x</span>
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
        data(){
          return {
            tags : [],
            links : [],
            linkTags : [],
            selectedTag : undefined,
            selectedCategory: '',
          }
        },
        computed:{

        },
        watch:{
          '$route' (to,from){
            let id = to.params.id;
            let tagId = to.params.tagId;

            if(id !== '' && id !== undefined && id !== null &&
                tagId !== '' && tagId !== undefined && tagId !== null){
              this.fetchLinkByTag();
            } else if(id !== '' && id !== undefined && id !== null){
              this.fetchData();
            }

          }
        },
        methods:{
          shareCategory(){
            let id = this.$router.history.current.params.id;
            let data = new FormData();
            data.append('id',id);
            this.$http.post('/categories/share', data)
              .then((result) => {
                console.log(result);
              })

          },
          fetchData(){
            this.$store.state.paramsId = this.$router.history.current.params.id;
            this.$http.get('/categories/'+this.$store.state.paramsId)
            .then((result) => {
              this.$store.state.category = result;
              this.links = this.$store.state.category.links;
              this.selectedCategory = result.name;
            })
            this.fetchTag();
          },
          fetchTag(){
            this.$http.get('/tags/?category-id='+this.$store.state.paramsId)
              .then((result) => {
                this.tags = result;
              })
          },
          fetchCategory(){
            if(this.$store.getters.getCategories.length === 0){
              const email = JSON.parse(localStorage.getItem('pouch_user'));
              if(email != null){
                this.$http.get('/categories/?email=' + email)
                  .then((result) => {
                    this.$store.commit('changeCategories',{
                      arr : result
                    });
                  })
              }
            }
          },
          fetchLinkByTag(){
            let id = this.$router.history.current.params.id;
            let tagId = this.$router.history.current.params.tagId;

            this.$http.get('/links/?category-id='+id+'&tag-id='+tagId)
              .then((result) => {
                 console.log(result);
                 this.links = result;
              })
          },
          tagKeyUp(event,id){
            let key = event.keyCode;
            let keyword = this.$refs['tagInput'+id][0].value;

            if(key === 188 || key === 32 || key === 13){
                if(keyword.length > 2){
                  this.linkTags.push({id:0,tagName:keyword.replace(",","")});
                  this.$refs['tagInput'+id][0].value = "";
                }
            }
          },
          removeTagItem(event,id){
            if(id === 0) {
              event.path[2].remove();
            } else {
              this.$http.delete("/tags/" + id).then((result) => {
                event.path[2].remove();
                this.fetchTag();
              })
            }
          },
          handleOk(id){
            this.handleSubmit(id);
          },
          handleSubmit(id){
            let arr = new Array();
            let tags = this.linkTags;
            let data = new FormData();
            for(let key in tags){
              if(tags[key].id === 0){
                arr.push(tags[key].tagName);
              }
            }
            data.append('id',id);
            data.append('tags',arr);
            this.$http.post("/tags",data).then((result) => {
               this.fetchTag();
            })
          },
          showModal(id){
            this.$refs[id][0].show();
            this.$http.get("/tags/"+id)
              .then((result) => {
                this.linkTags = result;
              })
          },
          removeLink(id){
            let val = confirm("정말 삭제하시겠습니까?");
            if(val === true){
              console.log(id+" : 동의");
            } else {
              console.log(id+" : 거부");
            }
          }
        },
        mounted(){
          this.fetchCategory();
          this.fetchData();
        }
    }
</script>

<style scoped>
  .badge{
    padding-bottom:2.5px !important;
  }
  .card-title > a {
    color:#00bfa5;
    font-weight:700;
    text-decoration:none;
    display:block;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    width: 200px;
  }

  .list-group-item > a {
    font-size:0.9rem;
    color:#161613;
    font-weight:400;
    text-decoration: none;
  }
  .list-group-item.active {
    background-color:white;
    border-color:#00bfa5;
    color:#161613;
    cursor:pointer;
  }
  .router-link-active {
    border-color:#00bfa5;
  }
  .tag-span {
    border:1px solid #00bfa5;
    padding:5px;
    margin:3px;
    border-radius:4px;
    background-color:#00bfa5;
    color:white;
    font-weight:700;
  }
  .cursor-pointer {
    cursor:pointer;
  }
  .edit-icon {
    color:#20B2AA;
  }
  .remove-icon {
    color:#DC143C;
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
    line-height:0.6;
    padding:1.5px;
    margin-left:2px;
  }
</style>
