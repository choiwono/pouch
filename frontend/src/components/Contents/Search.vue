<template>
  <!--<div>-->
    <!--<div v-for="category in $store.state.searchCategory">-->
      <!--<h3>{{ category.id }}</h3>-->
    <!--</div>-->
  <!--</div>-->
  <div class="container">
   <div class="jumbotron jumbotron-fluid bg-light p-3 mt-1">
    <div class="container text-center">
      <div v-if="$store.state.searchCategory.length >0" class="form-group d-flex justify-content-center">
        <h5>{{$store.state.searchCategory.length}}건의 검색 결과가 있습니다</h5>
      </div>
      <div v-else class="form-group d-flex justify-content-center">
         <h5>검색결과가 없습니다</h5>
      </div>

    </div>
  </div>


    <div class="row">
      <div v-for="category in $store.state.searchCategory" class="col-md-3 mb-4">
        <div class="card mb-4 shadow-sm">
          <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
               xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false"
               role="img" aria-label="Placeholder: Thumbnail">
            <router-link :to="{ name: 'categories',params:{ id:category.id }}">
              <title>Placeholder</title>
              <rect width="100%" height="100%" fill="#fff"></rect>
              <text x="30%" y="40%" fill="#6c757d" dy=".5em">' {{category.nickName}} '의</text>
              <text x="35%" y="50%" fill="#6c757d" dy=".5em">{{ category.name }}</text>
            </router-link>
          </svg>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
  export default {
    name: 'Search',
    data () {
      return {
        category : '',
        searchCategory: []
      }
    },
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
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
  }
</style>
