<template>
  <div>
    <div class="jumbotron jumbotron-fluid bg-light p-3 mt-1">
      <div class="container text-center">
        <b-dropdown id="dropdown-1" text="카테고리를 선택해주세요" variant="light" class="m-md-2">
          <b-dropdown-item v-for="item in getCategories" :key="item.id">
            <router-link tag="b-dropdown-item" :to="{ name: 'categories',params:{ id:item.id }}">{{ item.name }}</router-link>
          </b-dropdown-item>
        </b-dropdown>
      </div>
    </div>
    <hr class="my-4">
    <div class="container">
      <ul class="list-group col-md-2">
        <li class="list-group-item cursor-pointer" v-for="item in getCategory" :key="item.id">
          {{ item.id }}
        </li>
      </ul>
    </div>
    <div class="col-10">
      <div class="row" id="card-row">
        <div v-for="item in getCategory" :key="item.id" class="col-md-4 mb-4 card-list">
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
                <a :href="item.url" class="link-title">{{ item.title }}</a>
              <p class="m-2" style="font-size:80%;">{{ item.regDate }}</p>
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
            links : []
          }
        },
        computed:{
          getCategories(){
            return this.$store.getters.getCategories;
          },
          getCategory(){
            return this.$store.getters.getCategory.links;
          }
        },
        watch:{
          '$route': 'fetchData'
        },
        methods:{
          fetchData(){
            this.$store.state.paramsId = this.$router.history.current.params.id;
            this.$http.get('/categories/'+this.$store.state.paramsId)
              .then((result) => {
                this.$store.state.category = result;
              })
          }
        },
        mounted() {
          this.fetchData();
        }
    }
</script>

<style scoped>

</style>
