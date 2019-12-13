<template>
  <q-page>
    <div class="row justify-center">
      <!-- Post input card -->
      <div class="col-md-8 col-sm-12 col-xs-12 q-ma-sm">
        <q-card flat bordered class="my-card bg-grey-1">
          <q-card-section>
            <div class="row items-center no-wrap">
              <div class="col">
                <div class="text-h6">Create Post</div>
              </div>

              <div class="col-auto">
                <q-btn color="grey-7" round flat icon="more_vert">
                  <q-menu cover auto-close>
                    <q-list>
                      <q-item clickable>
                        <q-item-section>Remove Card</q-item-section>
                      </q-item>
                      <q-item clickable>
                        <q-item-section>Send Feedback</q-item-section>
                      </q-item>
                      <q-item clickable>
                        <q-item-section>Share</q-item-section>
                      </q-item>
                    </q-list>
                  </q-menu>
                </q-btn>
              </div>
            </div>
          </q-card-section>

          <q-card-section>
            <q-input
              v-model="postText"
              filled
              type="textarea"
              placeholder="What are you thinking about?"
            />
          </q-card-section>

          <q-separator />

          <q-card-actions align="right">
            <q-btn icon="send" flat>Post </q-btn>
          </q-card-actions>
        </q-card>
      </div>

      <!-- Post list cards -->
      <div class="col-md-8 col-sm-12 col-xs-12 q-ma-sm">
        <q-card class="my-card">
          <q-item>
            <q-item-section avatar>
              <q-avatar color="grey" text-color="white">UN</q-avatar>
            </q-item-section>

            <q-item-section>
              <q-item-label>Username</q-item-label>
              <q-item-label caption>Date</q-item-label>
            </q-item-section>
          </q-item>

          <!-- Post content -->
          <q-card-section>
            <p>Post content</p>
          </q-card-section>
          <!-- Likes -->
          <q-card-section>
            <q-btn icon="thumb_up" color="primary">Like</q-btn>
          </q-card-section>
          <q-separator />
          <!-- Comments -->
          <q-card-section>
            <Comment/>
          </q-card-section>
          <q-separator />
          <!-- Comment input -->
          <q-card-section>
            <q-input
              filled
              placeholder="Write a comment..."
            >
              <template v-slot:after>
                <q-icon name="send"/>
              </template>
            </q-input>
          </q-card-section>

        </q-card>
      </div>


    </div>

  </q-page>
</template>

<script>
  import Comment from "../components/Comment";

  export default {
    name: "Home",
    components: {Comment},
    beforeCreate: function () {
      document.body.className += ' gradient';
    },
    data() {
      return {
        postText: ''
      }
    }
  }
</script>

<style scoped>

</style>
