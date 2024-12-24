<script lang="ts" setup>

import {Edit} from "@element-plus/icons-vue";
import { reactive, ref } from 'vue'
import {ElNotification, FormInstance, FormRules} from 'element-plus'
import axios from "axios";
import {useRoute} from "vue-router";
import router from "@/router";
const route = useRoute()
//定义类型选项
const typeSelect = ref<any>([])
axios.get("/select/getAssetClassOptions")
    .then((res) => {
      if (res.data.code === 1){
        for (let i = 0; i < res.data.assetClassOptions.length; i++) {
          typeSelect.value[i] = {
            label: res.data.assetClassOptions[i].CLASS_NAME,
            value: res.data.assetClassOptions[i].CLASS_ID.toString()
          }
        }
      }
    })
//定义供应商选项
const supplierSelect = ref<any>([])
axios.get("/select/getSupplierOptions")
    .then((res) => {
      if (res.data.code === 1){
        for (let i = 0; i < res.data.supplierOptions.length; i++) {
          supplierSelect.value[i] = {
            label: res.data.supplierOptions[i].SUPPLIER_NAME,
            value: res.data.supplierOptions[i].SUPPLIER_ID.toString()
          }
        }
      }
    })
//定义品牌选项
const brandSelect = ref<any>([])
axios.get("/select/getBrandOptions")
    .then((res) => {
      if (res.data.code === 1){
        for (let i = 0; i < res.data.brandOptions.length; i++) {
          brandSelect.value[i] = {
            label: res.data.brandOptions[i].BRAND_NAME,
            value: res.data.brandOptions[i].BRAND_ID.toString()
          }
        }
      }
    })
let userId = localStorage.getItem('userId')
//定义用户名
const userName = ref('')
// 获取用户身份
axios.get("/index/getUser",{
  params:{
    userId:userId
  }
}).then(res => {
  userName.value = res.data.user
})
// 定义RuleForm接口规范表单的数据结构
interface FormINF {
  assetName: string,
  assetClass: string,
  supplier: string,
  brand: string,
  cause: string,
  budget: string,
  result: string,
}
// 定义响应式变量formSize，用于设置表单大小
const formSize = ref('default')
//定义响应式变量ruleFromRef，存储From实例，用于获取表单实例
const formRef = ref<FormInstance>()
// 定义响应式变量ruleForm，存储表单数据
const form = reactive<FormINF>({
  assetName: '',
  assetClass: '',
  supplier: '',
  brand: '',
  cause: '',
  budget: '',
  result: '待审批',
})
console.log("测试"+route.query.name)

// 定义响应式变量rules，存储表单验证规则
const rules = reactive<FormRules<FormINF>>({
  assetName: [
    { required: true, message: '请输入资产名称', trigger: 'blur' },
    { min: 1, max: 10, message: '请输入1-10个汉字', trigger: 'blur' },
  ],
  assetClass: [
    {
      required: true, message: '请选择资产类型', trigger: 'blur'},
  ],
  supplier: [
    {required:true,message:'请选择供应商',trigger:'blur'}
  ],
  brand: [
    {required:true,message:'请选择品牌',trigger:'blur'}
  ],
  cause: [
    {required:true,message:'请输入申购理由',trigger:'blur'}
  ],
  budget: [
    {required:true,message:'请输入预算金额',trigger:'blur'}
  ],
})
// 定义函数submitForm，用于提交表单数据
const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      axios.post('/insert/insertAssetRT',{
          assetName:form.assetName,
          classId:form.assetClass,
          supplierId:form.supplier,
          brandId:form.brand,
          argument:form.cause,
          budget:form.budget,
          result:form.result,
          applicant:userName.value
      }).then(res=>{
        if (res.data.code === 1){
          Notification('success','Success','提交成功')
          router.push("/assetRequisition_list")
        } else{
          Notification('error','Error','提交失败')
        }
      }).catch(error=>{
        console.log(error)
      })
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
// 定义函数resetForm，用于重置表单数据
const options = Array.from({ length: 10000 }).map((_, idx) => ({
  value: `${idx + 1}`,
  label: `${idx + 1}`,
}))
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
          <el-form-item  label="资产名称：" prop="assetName">
            <el-input v-model="form.assetName" placeholder="Asset name"/>
          </el-form-item>
          <el-form-item label="资产类别：" prop="assetClass">
            <el-select v-model="form.assetClass" placeholder="Asset type">
              <el-option v-for="(item, index) in typeSelect" :key="index" :label="item.label" :value="item.value" />
            </el-select>
          </el-form-item>
          <el-form-item label="供应商：" prop="supplier">
            <el-select v-model="form.supplier" placeholder="Supplier" :options="options">
              <el-option v-for="(item, index) in supplierSelect" :key="index" :label="item.label" :value="item.value" />
            </el-select>
          </el-form-item>
          <el-form-item label="品牌：" prop="brand">
            <el-select v-model="form.brand" placeholder="Brand">
              <el-option v-for="(item, index) in brandSelect" :key="index" :label="item.label" :value="item.value" />
            </el-select>
          </el-form-item>
          <el-form-item label="申购理由：" prop="cause">
            <el-input v-model="form.cause" type="textarea" placeholder="Cause"/>
          </el-form-item>
          <el-form-item  label="预算金额：" prop="budget">
            <el-input v-model="form.budget" type="text"  placeholder="Budget"/>
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
