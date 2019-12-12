<template>
  <q-page>
    <div class="row justify-center">
      <div class="col-md-4 q-mt-lg gt-sm">
        <h4 class="text-center"><strong>Connect with friends and the world around you on Facehub.</strong></h4>
        <img src="../assets/connectedPeople.png" style="width: 100%">
      </div>
      <div class="col-md-2 gt-sm"></div>
      <div class="col-md-4 col-sm-12 col-xs-12">

        <div :class="{ 'q-mt-xl': $q.screen.gt.sm}">
          <q-tabs
            v-model="tab"
            active-color="primary"
            indicator-color="primary"
            align="justify"
            narrow-indicator
          >
            <q-tab name="login" label="Login" icon="fas fa-sign-in-alt"/>
            <q-tab name="signup" label="Signup" icon="fas fa-user-plus"/>
          </q-tabs>

          <q-separator class="q-mb-lg"/>

          <q-tab-panels v-model="tab" animated style="background: transparent" transition-next="scale" transition-prev="scale">
            <q-tab-panel name="login">
              <LoginForm/>
            </q-tab-panel>

            <q-tab-panel name="signup">
              <SignupForm :callback="signupCallback"/>
            </q-tab-panel>

          </q-tab-panels>
        </div>

<!--        <div v-if="step === 'login'">-->
<!--          <LoginForm/>-->
<!--          <q-btn class="float-right" label="SignUp" color="secondary" icon="fas fa-user-plus" @click="step = 'signup'"/>-->
<!--        </div>-->
<!--        <div v-else-if="step === 'signup'">-->
<!--          <SignupForm/>-->
<!--          <q-btn class="float-right" label="Login" color="secondary" icon="fas fa-sign-in-alt" @click="step = 'login'"/>-->
<!--        </div>-->

      </div>
    </div>
  </q-page>
</template>

<script>
  import SignupForm from "../components/SignupForm";
  import LoginForm from "../components/LoginForm";

  export default {
    name: "LoginView",
    beforeCreate: function () {
      document.body.className += ' gradient';
    },
    components: {
      SignupForm,
      LoginForm
    },
    metaInfo: {
      title: 'Login',
      titleTemplate: '%s | Social Network',
      description: 'Login page for Social Network.'
    },
    data() {
      return {
        tab: 'login'
      }
    },
    methods: {
      signupCallback() {
        this.tab = 'login'
      }
    }
  };
</script>

<style scoped>

</style>
