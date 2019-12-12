<template>
  <q-header elevated height-hint="61.59">
    <q-toolbar class="q-py-sm q-px-md">

      <router-link to="/">
        <q-btn round dense flat :ripple="false" icon="fas fa-dove" size="19px" color="white" class="q-mr-sm" no-caps/>
      </router-link>

      <router-link to="/" v-if="$q.screen.gt.sm" class="header-link text-white text-body2 text-weight-bold q-mr-xl">
        Social Network
      </router-link>

      <div v-if="loggedIn" class="row">

        <q-select
          ref="search" dark dense standout use-input hide-selected
          class="toolbar-select"
          color="black" :stack-label="false" label="Search..."
          v-model="userSearch" :options="options" @filter="filter"
          style="width: 300px"
        >
          <template v-slot:no-option>
            <q-item class="spinner-bg">
              <q-item-section>
                <div class="text-center">
                  <q-spinner-pie
                    color="blue-8"
                    size="24px"
                  />
                </div>
              </q-item-section>
            </q-item>
          </template>

          <template v-slot:option="scope">
            <q-item
              v-bind="scope.itemProps"
              v-on="scope.itemEvents"
              class="menu-link"
            >
              <q-item-section side>
                <q-icon name="fas fa-user" color="grey-7"/>
              </q-item-section>
              <q-item-section>
                <q-item-label v-html="scope.opt.label"/>
              </q-item-section>
            </q-item>
          </template>
        </q-select>

        <div class="toolbar-link q-ml-xs q-gutter-md text-body2 text-weight-bold row items-center no-wrap">
          <router-link to="/" class="text-white">
            Home
          </router-link>
          <router-link to="/profile" class="text-white">
            Profile
          </router-link>
          <router-link to="/chat" class="text-white">
            Chat
          </router-link>
        </div>

      </div>
      <q-space/>
      <q-btn flat icon-right="fas fa-sign-out-alt" label="Logout" @click="logout"/>
    </q-toolbar>
  </q-header>
</template>

<script>
  import {mapActions, mapGetters} from "vuex";
  import {UserService} from "../services/user.service";

  export default {
    name: "AppHeader",
    data() {
      return {
        userSearch: '',
        options: [],
      }
    },
    computed: {
      ...mapGetters('auth', [
        'loggedIn'
      ])
    },
    methods: {
      ...mapActions('auth', [
        'logout'
      ]),
      filter (val, update) {
        if (val !== '') {
          this.options = []
          UserService.search(val).then(
            value => {
              value.forEach(v => this.options.push({ label: v.name }))
            }
          )
        }
        update()
      }
    }
  }
</script>

<style scoped>
  .header-link {
    font-size: 1.5em;
    color: white;
    text-decoration: none;
  }

  .header-link:hover {
    opacity: 0.7;
  }

  .toolbar-link a {
    color: white;
    text-decoration: none;
  }

  .toolbar-link a:hover {
    opacity: 0.7;
  }

  .toolbar-select.q-field {
    width: 450px !important;
  }

  .spinner-bg {
    background: white;
  }

  .menu-link {
    background: white;
    color: black;
  }

  .menu-link:hover {
    background: #0366d6;
    color: white;
  }

</style>
