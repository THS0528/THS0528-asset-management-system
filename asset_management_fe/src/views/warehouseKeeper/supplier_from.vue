<script lang="ts" setup>

import {Edit} from "@element-plus/icons-vue";
import {
  AllowedComponentProps, ComponentCustomProperties, ComponentCustomProps,
  ComponentInternalInstance,
  ComponentOptionsBase, ComponentPublicInstance, ComputedRef, ExtractPropTypes, nextTick,
  PropType,
  reactive,
  ref, ShallowUnwrapRef, UnwrapNestedRefs,
  VNodeProps, WatchOptions, WatchStopHandle
} from 'vue'
import {ElNotification, FormInstance, FormRules} from 'element-plus'
import axios from "axios";
import {useRoute} from "vue-router";
import router from "@/router";
const route = useRoute()
// 定义RuleForm接口规范表单的数据结构
interface FormINF {
  id: string,
  name: string,
  type: string,
  linkman: string,
  mp_number : string,
  location: string,
  status: string,
}
// 定义响应式变量formSize，用于设置表单大小
const formSize = ref('default')
//定义响应式变量ruleFromRef，存储From实例，用于获取表单实例
const formRef = ref<FormInstance>()
// 定义响应式变量ruleForm，存储表单数据
const form = reactive<FormINF>({
  id: '',
  name: '',
  type: '',
  linkman: '',
  mp_number : '',
  location: '',
  status: '1',
})
console.log("ceshi")
console.log(route.query)
//若为修改添加数据
if (route.query.id){
  form.id = route.query.id as string
}
if(route.query.name){
  form.name = route.query.name as string
}
if (route.query.supplierType){
  form.type = route.query.supplierType as string
}
if (route.query.linkman){
  form.linkman = route.query.linkman as string
}
if (route.query.mp_number){
  form.mp_number = route.query.mp_number as string
}
if (route.query.location){
  form.location = route.query.location as string
}
const routerType = route.query.type as string
// 定义响应式变量rules，存储表单验证规则
const rules = reactive<FormRules<FormINF>>({
  name: [
    { required: true, message: '请输入资产名称', trigger: 'blur' },
    { min: 1, max: 50, message: '请输入1-10个汉字', trigger: 'blur' },
  ],
  type: [
    {required: true, message: '请输入资产类型', trigger: 'blur'},
    {min: 1, max: 10, message: '请输入1-10个汉字', trigger: 'blur'}
  ],
  linkman: [
    {required:true, message:'请输入供应商',trigger:'blur'},
    {min: 1, max: 10, message: '请输入1-10个汉字', trigger: 'blur'}
  ],
  mp_number: [
    {required:true, message:'请输入联系电话',trigger:'blur'},
    { pattern: /^1[3-9]\d{9}$/, message: '联系电话应为11位数字', trigger: 'blur' }
  ],
  location: [
    {required:true, message:'请输入地点',trigger:'blur'},
    {min:1,max:100, message:'请输入1-100个汉字',trigger:'blur'}
  ]
})
// 定义函数submitForm，用于提交表单数据
const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
 await formEl.validate((valid, fields) => {
    if (valid) {
      console.log(routerType)
      if (routerType === 'edit'){
        //type = add
        axios.post('/update/updateSupplier', {
          supplierId: form.id,
          supplierName: form.name,
          supplierType: form.type,
          linkman: form.linkman,
          mp_number: form.mp_number,
          location: form.location,
        }).then(res => {
          if (res.data.code === 1) {
            Notification('success', 'Success', res.data.msg)
            router.push("/supplier_list")
          } else {
            Notification('error', 'Error', res.data.msg)
          }
        }).catch(error => {
          console.log(error)
        })
      }
      if (routerType === 'add') {
        //type = add
        axios.post('/insert/insertSupplier', {
          supplierName: form.name,
          supplierType: form.type,
          linkman: form.linkman,
          mp_number: form.mp_number,
          location: form.location,
          status: form.status,
        }).then(res => {
          if (res.data.code === 1) {
            Notification('success', 'Success', res.data.msg)
            router.push("/supplier_list")
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
            style="max-width: 600px; "
            :model="form"
            :rules="rules"
            label-width="auto"
            class="demo-ruleForm"
            :size="formSize"
            status-icon
        >
          <el-form-item  label="供应商名称" prop="name">
            <el-input v-model="form.name" placeholder="Supplier name"/>
          </el-form-item>
          <el-form-item label="供应商类别" prop="type">
            <el-input v-model="form.type" placeholder="Supplier type"/>
          </el-form-item>
          <el-form-item label="联系人" prop="linkman">
            <el-input v-model="form.linkman" placeholder="Contact person"/>
          </el-form-item>
          <el-form-item label="联系人电话" prop="mp_number">
            <el-input v-model="form.mp_number" placeholder="Contact the phone number"/>
          </el-form-item>
          <el-form-item label="地点" prop="location">
            <el-input v-model="form.location" placeholder="Location"/>
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
