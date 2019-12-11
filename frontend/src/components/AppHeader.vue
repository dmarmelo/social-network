<template>
  <q-header elevated>
    <q-toolbar>
      <q-avatar style="font-size: 50px;">
        <q-icon name="fas fa-dove" color="white"/>
      </q-avatar>

      <q-toolbar-title>Social Network</q-toolbar-title>

      <div v-if="loggedIn">
        <q-btn flat icon-right="fas fa-sign-out-alt" label="Logout" @click="logout"/>
      </div>

    </q-toolbar>
  </q-header>
</template>

<script>
  import {mapActions, mapGetters} from "vuex";

  export default {
    name: "AppHeader",
    data() {
      return {
        usernameOrPassword: '',
        password: ''
      }
    },
    computed: {
      ...mapGetters('auth', [
        'loggedIn',
        'authenticating',
        'authenticationError',
        'authenticationErrorCode'
      ])
    },
    methods: {
      ...mapActions('auth', [
        'login',
        'logout'
      ]),
      onSubmit() {
        // Perform a simple validation that email and password have been typed in
        if (this.usernameOrPassword !== '' && this.password !== '') {
          if (this.email !== '' && this.password !== '') {
            this.login({email: this.usernameOrPassword, password: this.password})
            this.password = ""
          }
        }
      }

    }
  }
</script>

<style scoped>

</style>
