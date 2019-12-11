<template>
  <div>
    <q-form
      @submit="onSubmit"
      class="q-gutter-md"
    >
      <q-input
        filled
        autofocus
        name="name"
        v-model="name"
        label="Name"
        hint="Name and surname"
        :rules="[ val => val && val.length > 0 || 'Please type your name and surname' ]"
      >
        <template v-slot:prepend>
          <q-icon name="fas fa-user"/>
        </template>
      </q-input>

      <q-input
        filled
        name="username"
        v-model="username"
        label="Username"
        :rules="[ checkUsernameAvailability ]"
      >
        <template v-slot:prepend>
          <q-icon name="fas fa-user-tag"/>
        </template>
      </q-input>

      <q-input
        filled
        type="email"
        name="email"
        v-model="email"
        label="Email"
        lazy-rules
        :rules="[ checkEmailAvailability ]"
      >
        <template v-slot:prepend>
          <q-icon name="mail"/>
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

      <q-input
        filled
        v-model="birthDate"
        label="Birth Date"
        hint="Format: YYYY/MM/DD"
        mask="####/##/##"
        fill-mask
        lazy-rules
        :rules="[ val => val && isValidDate(val) || 'Invalid birth date' ]"
      >
        <template v-slot:prepend>
          <q-icon name="event" class="cursor-pointer">
            <q-popup-proxy ref="qDateProxy" transition-show="scale" transition-hide="scale">
              <q-date v-model="birthDate" @input="() => $refs.qDateProxy.hide()"/>
            </q-popup-proxy>
          </q-icon>
        </template>
      </q-input>

      <q-btn type="submit" class="float-right" icon="fas fa-user-plus" label="SignUp" color="primary" :loading="authenticating"/>

    </q-form>
  </div>
</template>

<script>
  import {mapActions, mapGetters} from "vuex";

  export default {
    name: "SignupForm",
    data() {
      return {
        name: '',
        username: '',
        email: '',
        password: '',
        birthDate: ''
      }
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
      checkUsernameAvailability(val) {
        // simulating a delay

        return new Promise((resolve, reject) => {
          setTimeout(() => {
            // call
            //  resolve(true)
            //     --> content is valid
            //  resolve(false)
            //     --> content is NOT valid, no error message
            //  resolve(error_message)
            //     --> content is NOT valid, we have error message
            resolve(!!val || '* Required')

            // calling reject(...) will also mark the input
            // as having an error, but there will not be any
            // error message displayed below the input
            // (only in browser console)
          }, 1000)
        })
      },
      checkEmailAvailability(val) {

      },
      isValidDate(dateStr) {
        let d = Date.parse(dateStr)
        debugger
        if (isNaN(d)) {
          return false
        }
        d = new Date(d)
        let split = dateStr.split('/')
        let year = +split[0]
        let month = +split[1]
        let day = +split[2]
        return d.getFullYear() === year && d.getMonth() + 1 === month && d.getDate() === day;

      },
      onSubmit() {


      }
    }
  }
</script>

<style scoped>

</style>
