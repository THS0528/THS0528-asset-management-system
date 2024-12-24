<script setup lang="ts">
//定义表单接口规范数据类型
import {reactive, ref} from "vue";
import {FormInstance, FormRules} from "element-plus";
import axios from "axios";
import {useStore} from 'vuex'
import { ElNotification } from 'element-plus'
import {useRouter} from "vue-router";
const router = useRouter()
const store = useStore()
interface formIFN {
  username: string,
  password: string
}
//定义表单实例
const fromRef = ref<FormInstance>()
//定义表单实例（响应式）
const form = reactive<formIFN>({
  username: '',
  password: ''
})
//定义表单校验规则
const rules = reactive<FormRules<formIFN>>({
  username: [
    {required: true, message: '请输入用户名', trigger: 'blur'},
    {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur'}
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'}
  ]
})
//定义登录方法
const login = (formEl:FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate((valid) => {
    if (valid) {
      axios.post('/login', {
        //表单数据
        username:form.username,
        password:form.password,
      }).then(res => {
        if (res.data.code === 1){
          //弹出msg
          Notification("success","Success",res.data.msg)
          //保存用户信息
          store.commit("setUser",res.data.data)
          //刷新页面
          router.go(0)
        }else {
          //弹出msg
          Notification("error","Error",res.data.msg)
        }
      })
    } else {
      //错误信息
      Notification("error","Error",'登录失败')
    }
  })
}
//弹出提示的方法
const Notification = (type: string,title: string, msg: string) => {
  ElNotification({
    title: title,
    message: msg,
    type: type,
    showClose: false,
    position: 'bottom-right',
  })
}
</script>

<template>
  <div class="body">
    <h1>资产管理系统</h1>
    <div class="box">
      <h1>welcome</h1>
      <div class="container">
        <el-form ref="fromRef" :model="form" :rules="rules" label-width="120px" class="demo-form-inline">
          <el-form-item label="用户名：" prop="username">
            <el-input v-model="form.username" placeholder="Please enter username"/>
          </el-form-item>
          <el-form-item label="密码：" prop="password">
            <el-input v-model="form.password" placeholder="Please enter password"/>
          </el-form-item>
          <el-form-item>
            <el-button @click="login(fromRef)">登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<style scoped>
.body{
  width: 100%;
  height: 100%;
  .box{
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    margin: auto;
    padding: 25px;
    width: 26%;
    min-width: 460px;
    height: 30%;
    min-height: 300px;
    background: rgba(255, 255, 255, 0.6);
    border-radius: 4px;
    box-shadow: 2px 2px 10px #ddd;
    .container{
      display: flex;
      flex-direction: column;
      align-items: center;
      .demo-form-inline{
        width: 80%;
      }
      .demo-form-inline .el-input{
        --el-input-width: 200px;
      }
      h1{

        font-size: 24px;
        color: #333;
        margin-bottom: 20px;
      }
    }
  }
}

</style>
