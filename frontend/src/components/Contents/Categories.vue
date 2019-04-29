<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
    <div class="jumbotron jumbotron-fluid bg-light p-3 mt-1">
      <div class="container text-center">
        <h3>{{ selectedCategory }}</h3>
        <b-dropdown id="dropdown-1" text="카테고리를 선택해주세요" variant="light" class="m-md-2">
          <b-dropdown-item v-for="item in getCategories" :key="item.id">
            <router-link tag="b-dropdown-item" :to="{ name: 'categories',params:{ id:item.id }}">{{ item.name }}</router-link>
          </b-dropdown-item>
        </b-dropdown>
      </div>
    </div>
    <hr>
    <div class="container d-flex">
      <ul class="col-md-2 list-group">
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
              <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                   xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice"
                   focusable="false" role="img" aria-label="Placeholder: Thumbnail">
                <title>Placeholder</title>
                <rect width="100%" height="100%" fill="#55595c"></rect>
                <text x="36.5%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text>
              </svg>
              <div class="card-body p-2 pl-3 pr-3">
                <p class="card-title m-2 d-flex">
                  <a target="_blank" :href="item.url" class="link-title">{{ item.title }}</a>
                <p class="m-2">{{ item.regDate.substr(0,10) }}</p>
                <div class="mb-3 mt-3" v-if="item.tagName.length > 0">
                  <span class="tag-span" v-for="tag in item.tagName.split(',')">
                    {{ tag }}
                  </span>
                </div>
                <div class="mb-3 mt-3">
                  <span @click="showModal(item.id)">
                    <icon name="tag"></icon>
                  </span>
                </div>
              </div>
              <b-modal
                :ref="item.id"
                title="태그편집"
                ok-only
                centered>
                <form @submit.stop.prevent="handleSubmit()">
                  <ul class="edit-tag-list w-100">
                    <li class="token-input-input-token w-100">
                      <span v-if="item.tagName.length > 0">
                        <p v-for="tag in item.tagName.split(',')" class="d-inline-block p-2 border">
                          <span>
                            {{ tag }}<span type="button" class="close tag-close" @click="removeTagItem($event)">x</span>
                          </span>
                        </p>
                      </span>
                      <input autofocus :ref="'tagInput'+item.id" @keyup="tagKeyUp($event,item.id)"
                           @keydown="tagKeyDown($event,item.id)" type="text" class="border-0" style="outline:none;">
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
    export default {
        name: "Categories",
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
          getCategories(){
            return this.$store.getters.getCategories;
          },
        },
        watch:{
          //'$route.params.id': 'fetchData',
          //'$route.params.tagName': 'fetchLinkByTag'
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
          fetchData(){
            this.$store.state.paramsId = this.$router.history.current.params.id;
            this.$http.get('/categories/'+this.$store.state.paramsId)
            .then((result) => {
              console.log(result);
              this.$store.state.category = result;
              this.links = this.$store.state.category.links;
              this.selectedCategory = result.name;
            })
            this.$http.get('/tags/?category-id='+this.$store.state.paramsId)
            .then((result) => {
              this.tags = result;
            })
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
          tagKeyDown(event,id){

          },
          tagKeyUp(event,id){
            let key = event.keyCode;
            let keyword = this.$refs['tagInput'+id][0].value;
            let tagInput = this.$refs['tagInput'+id][0].previousElementSibling;

            if(key == 188 || key == 32 || key == 13){
                if(keyword.length > 2){
                  //console.log(tagInput.lastElementChild.appendChild('<p>'+'test'+'</p>'));
                  console.log(this.links);
                }
            }
          },
          removeTagItem(event){
            console.log(event.path[2].remove());
          },
          handleSubmit(){
            alert("submit");
          },
          showModal(id){
            //console.log(this.$refs[id][0].focus);

            this.$refs[id][0].show();
            //console.log(this.$refs[id][0]);
          }
        },
        mounted() {
          //this.links = this.fetchLinkByTag();
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
