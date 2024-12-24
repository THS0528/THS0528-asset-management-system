<script lang="ts" setup>

import {MoreFilled, User} from "@element-plus/icons-vue";
import {ref} from "vue";
import {useRouter} from "vue-router";
import axios from "axios";
//获取用户id
const userId = localStorage.getItem("userId")
//定义用户名
const username = ref('')
//定义用户身份
const role = ref('')

//获取登录状态
const userStatus = localStorage.getItem("isLogin")
console.log(userStatus)
//退出登录
const exitLogin = () => {
  localStorage.removeItem("userId")
  localStorage.removeItem("isLogin")
  router.go(0)
}
// let children = <any>[]
// // 获取用户身份
// axios.get("/index/getUser",{
//   params:{
//     userId:userId
//   }
// }).then(res => {
//   username.value = res.data.user
//   role.value = res.data.role
//   for (let i = 0; i < res.data.permission.length; i++) {
//     children[i] = {
//       name: res.data.permission[i].PERMISSION_NAME,
//       path: res.data.permission[i].PERMISSION_PATH
//     }
//   }
// })
// axios.get
let routes = ref([
  {
    path: '/',
    name: '资产管理',
    children: [
      {
        path: '',
        name: '',
      },
    ]
  }
  ])
//tabs数组
const tabs = ref([{
  label:'',
  path:'',
  closable:false
}])
const tabsModel = ref<any>()
// 获取用户身份（变成同步请求）
axios.get("/index/getUser",{
  params:{
    userId:userId
  }
}).then(res => {
  username.value = res.data.user
  role.value = res.data.role
  for (let i = 0; i < res.data.permission.length; i++) {
    routes.value[0].children[i] = {
      name: res.data.permission[i].PERMISSION_NAME,
      path: res.data.permission[i].PERMISSION_PATH
    }
  }
    tabs.value = [
      {
        label:'坐标仪',
        path:routes.value[0].children[0].path,
        closable:false
      }
    ]
  router.push(routes.value[0].children[0].path)
})
// 双向绑定(默认为第一个组件)
tabsModel.value = routes.value[0].children[0].path
//tabs数组
// const tabs = ref([
//   {
//     label:'坐标仪',
//     path:routes[0].children[0].path,
//     closable:false
//   }
// ])
//点击菜单添加tab
const menuClick = (path:string,name:string) => {
  router.push(path)
  tabsModel.value = path
  for (let i = 0; i < tabs.value.length; i++){
    if (tabs.value[i].label === name){
      return
    }
  }
  tabs.value.push({
    label:name,
    path: path,
    closable:true
  })
}
const router = useRouter()

const tabClick = (tab:any,event:any) => {
  router.push(tab.props.name)
}
//删除tab
const removeTab = (targetName: string) => {
  for (let i = 0; i < tabs.value.length; i++){
    // 删除当前tab（从tabs数组内删除与删除的tab相同path的元素，但名字不能是坐标仪）
    if (tabs.value[i].path === targetName && tabs.value[i].label !== '坐标仪'){
      tabs.value.splice(i,1)
      if(targetName === tabsModel.value){
        router.push(tabs.value[i-1].path)
        tabsModel.value = tabs.value[i-1].path
      }
      break
    }
  }
}
</script>

<template>
  <div>
    <el-container class="layout-container-demo" style="height: 500px">
      <!--    顶栏  -->
      <el-header style="border-bottom: #dedfe0 1px solid;">
        <div class="toolbar" style="display: flex;justify-content: space-between;">
          <el-text class="mx-1" style="font-size: 35px;font-family: 黑体">资产管理系统</el-text>
          <el-dropdown>
            <el-icon style="margin-right: 8px; margin-top: 1px;font-size: 30px"><User /></el-icon>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item icon="UserFilled">{{username}}</el-dropdown-item>
                <el-dropdown-item icon="List">{{role}}</el-dropdown-item>
                <el-dropdown-item icon="CloseBold" @click="exitLogin">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
            <span style="font-size: 20px">用户</span>
          </el-dropdown>
        </div>
      </el-header>
      <!-- 内容组件 -->
      <el-container style="">
        <!--     侧边栏     -->
        <el-aside >
<!--     菜单栏     -->
          <el-menu  :default-openeds="[0]" >
            <el-sub-menu v-for="(item,index) in routes" :key="index" :default-openeds="['0']" :index="index">
              <template #title><el-icon><MoreFilled /></el-icon>{{item.name}}</template>
              <el-menu-item-group>
                <el-menu-item
                    v-for="(item1,index) in item.children"
                    :key="index"
                    :index="item1.path"
                    :class="$route.path===item1.path?'is-active':''"
                    @click="menuClick(item1.path,item1.name)"
                >{{item1.name}}</el-menu-item>
              </el-menu-item-group>
            </el-sub-menu>
          </el-menu>
        </el-aside>
        <!--   主体栏和页脚   -->
          <!-- 主体栏 -->
          <el-main>
            <el-tabs
                v-model="tabsModel"
                type="card"
                class="demo-tabs"
                @tab-remove="removeTab"
                style=" height: 40px;position: relative; top:25px"
                @tab-click="tabClick"
                default-active="坐标仪"
            >
              <el-tab-pane
              v-for="item in tabs"
                :key="item.path"
                :label="item.label"
                :name="item.path"
                :closable=item.closable
              />
            </el-tabs>
            <router-view/>
          </el-main>
        </el-container>

      </el-container>
  </div>

</template>
<style scoped>
.layout-container-demo .el-header {
  position: relative;
  background-color: var(--el-color-primary-light-7);
  color: var(--el-text-color-primary);
  border-radius: 10px;
}
.layout-container-demo .el-aside {
  color: var(--el-text-color-primary);
}
.layout-container-demo .el-menu {
  border-right: none;
}
.layout-container-demo .el-main {
  padding: 0;
}
.layout-container-demo .toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
}

</style>
