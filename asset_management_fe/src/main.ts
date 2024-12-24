import { createApp } from 'vue'
import App from './App.vue'
import router from "@/router";
import store from "@/store";
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import axios from "axios";

const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
axios.defaults.baseURL = '/api';
app.use(router).use(store).use(ElementPlus).mount('#app')

