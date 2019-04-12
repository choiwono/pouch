<template>
  <div>
    <div class="container main m-5">
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
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      q : ''
    }
  },
  methods: {
    googleSearch(e){
      this.$refs.form.submit()
    }
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
