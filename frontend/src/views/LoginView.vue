<template>
    <div class="q-gutter-md" style="max-width: 300px">
        <h1>Login</h1>

        <q-form
                @submit="onSubmit"
                class="q-gutter-md"
        >
            <q-input
                    filled
                    v-model="email"
                    label="Email"
                    hint="Email"
                    lazy-rules
                    :rules="[ val => val && val.length > 0 || 'Please type something' ]"
            />

            <q-input
                    filled
                    type="password"
                    v-model="password"
                    label="Password"
                    hint="Password"
                    lazy-rules
                    :rules="[ val => val && val.length > 0 || 'Please type something' ]"
            />

            <div>
                <q-btn label="Submit" type="submit" color="primary"/>
            </div>
        </q-form>

        <q-circular-progress
                v-if="authenticating"
                indeterminate
                size="50px"
                color="lime"
                class="q-ma-md"
        />

        <p v-if="authenticationError">{{ authenticationError }}</p>


    </div>
</template>

<script>
    import {mapActions, mapGetters} from "vuex";

    export default {
        name: "LoginView",
        data() {
            return {
                email: "",
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
                if (this.email != '' && this.password != '') {
                    this.login({email: this.email, password: this.password})
                    this.password = ""
                }
            }
        }
    };
</script>

<style scoped>

</style>
