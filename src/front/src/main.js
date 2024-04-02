import { createApp } from 'vue';
import App from './App.vue';
import Axios from 'axios';
import Router from './router';

createApp(App)
    .use(Axios)
    .use(Router)
    .mount('#app');
