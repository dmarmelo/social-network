<template>
  <div>
    <q-form
      @submit="onSubmit"
      class="q-gutter-md"
    >
      <q-input
        filled
        autofocus
        v-model="usernameOrEmail"
        label="Email or Username"
        lazy-rules
        :rules="[ val => val && val.length > 0 || 'Please type your email address or username' ]"
      >
        <template v-slot:prepend>
          <q-icon name="fas fa-user"/>
        </template>
      </q-input>

      <q-input
        filled
        type="password"
        v-model="password"
        label="Password"
        lazy-rules
        :rules="[ val => val && val.length > 0 || 'Please type you password' ]"
      >
        <template v-slot:prepend>
          <q-icon name="fas fa-key"/>
        </template>
      </q-input>

      <q-btn type="submit" class="float-right" icon="fas fa-sign-in-alt" label="Login" color="primary" :loading="authenticating"/>

    </q-form>

  </div>
</template>

<script>
  import {mapActions, mapGetters} from "vuex";

  export default {
    name: "LoginForm",
    data() {
      return {
        usernameOrEmail: "",
        password: "",
      };
    },
    computed: {
      ...mapGetters('auth', [
        'authenticating',
        'authenticationError',
        'authenticationErrorCode'
      ])
    },
    methods: {
      ...mapActions('auth', [
        'login'
      ]),
      onSubmit() {
        // Perform a simple validation that email and password have been typed in
        if (this.usernameOrEmail !== '' && this.password !== '') {
          this.login({email: this.usernameOrEmail, password: this.password})
          this.password = ""
        }
      }
    }
  }
</script>

<style scoped>

</style>
