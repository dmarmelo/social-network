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
        hint="Format: YYYY-MM-DD"
        mask="####-##-##"
        fill-mask
        lazy-rules
        :rules="[ val => val && isValidDate(val) || 'Invalid birth date' ]"
      >
        <template v-slot:prepend>
          <q-icon name="event" class="cursor-pointer">
            <q-popup-proxy ref="qDateProxy" transition-show="scale" transition-hide="scale">
              <q-date v-model="birthDate" mask="YYYY-MM-DD" @input="() => $refs.qDateProxy.hide()"/>
            </q-popup-proxy>
          </q-icon>
        </template>
      </q-input>

      <q-btn type="submit" class="float-right" icon="fas fa-user-plus" label="SignUp" color="primary"/>

    </q-form>
  </div>
</template>

<script>
  import {mapActions} from "vuex";
  import {UserService} from "../services/user.service";

  export default {
    name: "SignupForm",
    props: {
      callback: Function
    },
    data() {
      return {
        name: '',
        username: '',
        email: '',
        password: '',
        birthDate: ''
      }
    },
    computed: {},
    methods: {
      ...mapActions('auth', [
        'signup'
      ]),
      checkUsernameAvailability(val) {
        if (val !== '') {
          return new Promise((resolve, reject) => {
            UserService.checkUsernameAvailability(val)
              .then(value => resolve(!!value.available || 'Username not available'))
            }
          )
        }
      },
      checkEmailAvailability(val) {
        if (val !== '') {
          return new Promise((resolve, reject) => {
            UserService.checkEmailAvailability(val)
              .then(
                value => resolve(!!value.available || 'Email already in use'),
                reason => resolve('Invalid Email')
              );
          })
        }
      },
      isValidDate(dateStr) {
        let d = Date.parse(dateStr)
        if (isNaN(d)) {
          return false
        }
        d = new Date(d)
        let split = dateStr.split('-')
        let year = +split[0]
        let month = +split[1]
        let day = +split[2]
        return d.getFullYear() === year && d.getMonth() + 1 === month && d.getDate() === day;

      },
      onSubmit() {
        this.signup({name: this.name, username: this.username, email: this.email, password: this.password, birthDateStr: this.birthDate})
        this.callback()
      }
    }
  }
</script>

<style scoped>

</style>
