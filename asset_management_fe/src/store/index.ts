import { createStore } from 'vuex'
import {createApp} from 'vue'
//状态管理包
export default createStore({
  state: {
    // 定义 state
    user:{
      userId: '',
      isLogin: false,
    }
  },
  mutations: {
    // 定义 mutations
    setUser(state, user) {
      //存储数据
      localStorage.userId = user.userId
      //状态为true
      localStorage.isLogin = true
    }
  },
  actions: {
    // 定义 actions
  },
  modules: {
    // 定义 modules
  }
})
