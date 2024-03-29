import { createApp } from 'vue'
import App from './App.vue'
import Axios from 'axios'

createApp(App)
    .use(Axios)
    .mount('#app')
