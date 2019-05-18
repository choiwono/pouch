<template>
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

      <v-flex md3 sm12 xs12 :key="category.id" v-for="category in $store.state.searchCategory">
        <v-layout column>
          <router-link :to="{ name: 'categories',params:{ id:category.id }}">
          <v-card class="card-title">
              <v-card-text class="vertical-center">
                <h3 class="mx-auto d-block">'{{ category.nickName }}' 의 {{ category.name }}</h3>
              </v-card-text>
          </v-card>
          </router-link>
        </v-layout>
      </v-flex>
    </div>


  </div>

</template>

<script>
  export default {
    name: 'Search',
    data() {
      return {
        category: '',
        searchCategory: []
      }
    },
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .card-title {
    max-height:200px;
    min-height:200px;
    margin:10px;
  }

  .btn-secondary {
    background-color:#00bfa5;
    border-color:#00bfa5;
  }

  .vertical-center {
    margin: 0;
    position: absolute;
    top: 50%;
    -ms-transform: translateY(-50%);
    transform: translateY(-50%);
    color:#00bfa5;
  }
  .mx-auto:hover {
    font-weight:800;
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
    text-decoration:none;
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
</style>
