<template>
  <div>
    <b-navbar class="p-3 border-bottom pl-5 pr-5" fixed="top" toggleable="lg" type="light" variant="white">
      <b-navbar-brand href="/home">Pouch</b-navbar-brand>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-item-dropdown right>
            <!-- Using 'button-content' slot -->
            <template slot="button-content"><icon name="user"></icon></template>
            <b-dropdown-item href="/login">로그인</b-dropdown-item>
            <b-dropdown-item href="/join">회원가입</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
    export default {
        name: "Header"
    }
</script>

<style scoped>

</style>
