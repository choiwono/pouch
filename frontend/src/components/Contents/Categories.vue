<template>
  <div>
    <div class="jumbotron jumbotron-fluid bg-light p-3 mt-1">
      <div class="container text-center">
        <div class="form-group d-flex justify-content-center">
          <select id="selectCategory" class="form-control" style="max-width:300px;">
          </select>
        </div>
      </div>
    </div>
    <hr class="my-4">
    <div class="container">
      <ul class="list-group col-md-2">
        <li class="list-group-item cursor-pointer">{{ $route.params.id }}</li>
      </ul>
    </div>
  </div>
</template>

<script>
    export default {
        name: "Categories",
        props: ['id'],
        data(){
          return {
            tags : [],
            links : []
          }
        },
        mounted() {
          let categoryId = this.$router.history.current.params.id;
          this.$http.get('/categories/'+categoryId)
            .then((result) => {
                console.log(result);
            })
        }
    }
</script>

<style scoped>

</style>
