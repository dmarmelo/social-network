<template>
  <q-list bordered class="rounded-borders">
    <q-item clickable v-ripple>
      <q-item-section avatar>
        <q-avatar>
          <img src="https://cdn.quasar.dev/img/avatar2.jpg">
        </q-avatar>
      </q-item-section>

      <q-item-section>
        <q-item-label lines="1">Brunch this weekend?</q-item-label>
        <q-item-label caption lines="2">
          <span class="text-weight-bold">Janet</span>
          -- I'll be in your neighborhood doing errands this
          weekend. Do you want to grab brunch?
        </q-item-label>
      </q-item-section>

      <q-item-section side top>
        1 min ago
      </q-item-section>
    </q-item>

    <q-separator inset="item" />

    <q-item clickable v-ripple>
      <q-item-section avatar>
        <q-avatar>
          <img src="https://cdn.quasar.dev/img/avatar4.jpg">
        </q-avatar>
      </q-item-section>

      <q-item-section>
        <q-item-label lines="1">Linear Project</q-item-label>
        <q-item-label caption lines="2">
          <span class="text-weight-bold">John</span>
          -- Can we schedule a call for tomorrow?
        </q-item-label>
      </q-item-section>

      <q-item-section side top>
        1 min ago
      </q-item-section>
    </q-item>
  </q-list>
</template>

<script>
    export default {
        name: "Comment"
    }
</script>

<style scoped>

</style>
