<template>
  <div>
    <div v-for="category in $store.state.searchCategory">
      <h3>{{ category.id }}</h3>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Search',
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
