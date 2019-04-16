<template>
  <div>
    <div class="container main pt-5" style="padding: 24px; width: 100%;">
      <!-- Another variation with a button -->
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
      <div class="col-md-3 mb-4">
        <div class="card mb-4 shadow-sm">
          <div class="card-img-top d-flex align-items-center cursor-pointer"
               style="width:100%; height:225px; opacity:0.5;" >
            <img style="flex:0 !important;" class="rounded mx-auto d-block" src="https://img.icons8.com/ios/50/000000/plus-math.png" >
          </div>
        </div>
      </div>
      <div v-for="category in categories" class="col-md-3 mb-4">
        <div class="card mb-4 shadow-sm">
          <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
               xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false"
               role="img" aria-label="Placeholder: Thumbnail">
              <a href="#">
              <title>Placeholder</title>
              <rect width="100%" height="100%" fill="#55595c"></rect>
              <text x="36.5%" y="50%" fill="#eceeef" dy=".3em">{{ category.name }}</text>
            </a>
          </svg>
        </div>
      </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      q : '',
      categories: []
    }
  },
  methods: {
    googleSearch(e){
      this.$refs.form.submit()
    }
  },
  created(){
    this.$http.get('/categories/'+this.$session.get('account').id)
      .then((result) => {
        this.categories = result.data;
      })
  }
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
