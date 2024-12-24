<script lang="ts">
import index from "./views/index.vue";
import login from "./views/login.vue";
import {ref} from "vue";
const userStatus = localStorage.getItem("isLogin");
let page = ref<any>()
if (userStatus != null && userStatus === "true"){
  console.log("首页")
  page.value = "index"
}else {
  console.log("登录")
  page.value = "login"
}
export default {
  name: "App",
  components: {
    index,
    login
  },
  data() {
      return{
        page: page
      }
  },
}

</script>
<template>
  <component :is='page'/>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
