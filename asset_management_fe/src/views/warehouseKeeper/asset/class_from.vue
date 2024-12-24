<script lang="ts" setup>

import {Edit} from "@element-plus/icons-vue";
import { reactive, ref } from 'vue'
import {ElNotification, FormInstance, FormRules} from 'element-plus'
import axios from "axios";
import {useRoute} from "vue-router";
import router from "@/router";
const route = useRoute()
// 定义formINF接口规范表单的数据结构
interface FormINF {
  id: string
  name: string
  code: string
  status:string
  userId: any
}
// 定义响应式变量formSize，用于设置表单大小
const formSize = ref('default')
//定义响应式变量ruleFromRef，存储From实例，用于获取表单实例
const formRef = ref<FormInstance>()
// 定义响应式变量ruleForm，存储表单数据
const form = reactive<FormINF>({
  id:'',
  code:'',
  name: '',
  status: '1',
  userId: localStorage.getItem("userId")
})
//编辑自动带入数据
if (route.query.id){
  form.id=route.query.id as string
}
if (route.query.code){
  form.code=route.query.code as string
}
if (route.query.name){
  form.name=route.query.name as string
}
const routerType = route.query.type as string
// 定义响应式变量rules，存储表单验证规则
const rules = reactive<FormRules<FormINF>>({
  code: [
    {required: true, message: '请输入资产类型', trigger: 'blur'},
    { min: 1, max: 10, message: '请输入1-10个字符', trigger: 'blur'},
    { pattern: /^[a-zA-Z0-9]+$/, message: '请输入字母或数字', trigger: 'blur' }
  ],
  name: [
    { required: true, message: '请输入资产类别名称', trigger: 'blur' },
    { min: 1, max: 10, message: '请输入1-10个汉字', trigger: 'blur' },
  ]
})
// 定义函数submitForm，用于提交表单数据
const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      //若位修改提交到修改controller
      //提交表单数据到后端
      if (routerType === 'edit'){
        //type = add
        axios.post('/update/updateAssetClass', {
          classId: form.id,
          className: form.name,
          classCode: form.code,
        }).then(res => {
          if (res.data.code === 1) {
            Notification('success', 'Success', res.data.msg)
            router.push("/assetClass_list")
          } else {
            Notification('error', 'Error', res.data.msg)
          }
        }).catch(error => {
          console.log(error)
        })
      }
      if (routerType === 'add') {
        //type = add
        axios.post('/insert/insertAssetClass', {
          className: form.name,
          classCode: form.code,
          status: form.status,
          userId: form.userId
        }).then(res => {
          if (res.data.code === 1) {
            Notification('success', 'Success', res.data.msg)
            router.push("/assetClass_list")
          } else {
            Notification('error', 'Error', res.data.msg)
          }
        }).catch(error => {
          console.log(error)
        })
      }
    } else {
      console.log('error submit!', fields)
    }
  })
}
// 定义函数resetForm，用于重置表单数据
const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}
// 定义函数Notification，用于显示通知消息
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
  <el-container>
    <el-header style="height: 30px">
      <div style="display: flex; justify-content: space-between ;margin-top: 10px">
        <!--  标题  -->
        <el-text style="font-size: 20px " ><el-icon color="#303133"><edit /></el-icon>{{$route.query.title}}</el-text>
      </div>
    </el-header>
    <el-divider />
    <el-main>
      <div>
        <el-form
            ref="formRef"
            style="max-width: 600px;"
            :model="form"
            :rules="rules"
            label-width="auto"
            class="demo-ruleForm"
            :size="formSize"
            status-icon
        >
          <el-form-item  label="资产类别编码" prop="code">
            <el-input v-model="form.code" placeholder="Asset type code"/>
          </el-form-item>
          <el-form-item label="资产类别名称" prop="name">
            <el-input v-model="form.name" placeholder="Asset type name"/>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm(formRef)">
              提交
            </el-button>
            <el-button @click="resetForm(formRef)">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-main>
  </el-container>
</template>

<style scoped>

</style>
