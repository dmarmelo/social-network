<template>
  <q-layout view="lHh Lpr lFf">

    <AppHeader/>

    <q-page-container>
      <router-view/>
    </q-page-container>

    <AppFooter/>

  </q-layout>
</template>

<script>
  import HelloWorld from './components/HelloWorld.vue'
  import AppHeader from "./components/AppHeader";
  import AppFooter from "./components/AppFooter";

  export default {
    name: 'LayoutDefault',

    components: {
      AppFooter,
      AppHeader,
      HelloWorld
    },

    data() {
      return {
        leftDrawerOpen: false
      }
    }
  }
</script>

<style>
</style>
