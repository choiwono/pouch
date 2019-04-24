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
        <li class="list-group-item cursor-pointer" v-for="item in tags" :key="item.id">
          <router-link :to="{ name: 'categoriesByTag',params:{ tagId:item.id }}">
            {{ item.tagName }}
          </router-link>
          <v-badge class="v-badge badge" right color="teal accent-4">
            <span slot="badge">{{ item.cnt }}</span>
          </v-badge>
        </li>
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
              </div>
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
            selectedCategory: ''
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
            console.log(this.$router.history.current.params);
            let id = this.$router.history.current.params.id;
            let tagId = this.$router.history.current.params.tagId;
            this.$http.get('/links/?category-id='+id+'&tag-id='+tagId)
              .then((result) => {
                 this.links = result;
              })
            //console.log('tag 데이터 호출');
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
    text-decoration:none;
  }
</style>
