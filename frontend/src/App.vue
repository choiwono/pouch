<template>
  <div id="app">
    <Header/>
    <router-view/>
    <Footer/>
    <notifications number :max="2" width="50%" position="top center" group="notify"/>
  </div>
</template>

<script>
import Footer from "./components/layout/Footer";
import Header from "./components/layout/Header";

export default {
  name: 'App',
  components: {
    'Header' : Header,
    'Footer' : Footer
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

/*.router-link-active {
  color: red;
}*/
</style>
