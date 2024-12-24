<script lang="ts" setup>

import {Edit} from "@element-plus/icons-vue";
import { reactive, ref } from 'vue'
import {ElNotification, FormInstance, FormRules} from 'element-plus'
import axios from "axios";
import {useRoute} from "vue-router";
import router from "@/router";
const route = useRoute()
//获取数据
axios.get('test?name=asd')

    .then((res) => {
      // 执行操作
      console.log("成功");
    })
    .catch((error) => {
      // handle error
      console.log("失败");
      console.log(error)
    })
    .finally(() => {
      // always executed
      console.log("执行")
    });
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
//定义存放地点选项
const locationSelect = ref<any>([])
axios.get("/select/getLocationOptions")
    .then((res) => {
      if (res.data.code === 1){
        for (let i = 0; i < res.data.locationOptions.length; i++) {
          locationSelect.value[i] = {
            label: res.data.locationOptions[i].LOCATION_NAME,
            value: res.data.locationOptions[i].LOCATION_ID.toString()
          }
        }
      }
    })
// 定义RuleForm接口规范表单的数据结构
interface FormINF {
  id: string
  name: string
  type: string
  supplier: string
  brand: string
  location: string
  date:Date
  status:string
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
  supplier: '',
  brand: '',
  location: '',
  date:new Date(''),
  status:'在库'
})
console.log("测试"+route.query.name)
const data = ref<any>({

})
//若为修改添加数据
if(route.query.name){
  form.name = route.query.name as string
}
if (route.query.AssetType){
  form.type = route.query.AssetType as string
}
if (route.query.supplier){
  form.supplier = route.query.supplier as string
}
if (route.query.brand){
  form.brand = route.query.brand as string
}
if (route.query.location){
  form.location = route.query.location as string
}
if (route.query.id){
  form.id = route.query.id as string
}
console.log("canshu")
console.log(route.query)
// 定义响应式变量rules，存储表单验证规则
const rules = reactive<FormRules<FormINF>>({
  name: [
    { required: true, message: '请输入资产名称', trigger: 'blur' },
    { min: 1, max: 10, message: '请输入1-10个汉字', trigger: 'blur' },
  ],
  type: [
    {required: true, message: '请选择资产类型', trigger: 'blur'},
  ],
  supplier: [
      {required:true,message:'请选择供应商',trigger:'blur'}
  ],
  brand: [
    {required:true,message:'请选择品牌',trigger:'blur'}
  ],
  location: [
    {required:true,message:'请选择存放地点',trigger:'blur'}
  ]
})
const type = route.query.type
// 定义函数submitForm，用于提交表单数据
const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      if (type === 'add'){
        form.date=new Date()
        //提交表单数据到后端
        axios.post('/insert/insertAsset',{
          assetName:form.name,
          classId:form.type,
          supplierId:form.supplier,
          brandId:form.brand,
          locationId:form.location,
          date:form.date,
          status:form.status
        }).then(res=>{
          if (res.data.code === 1){
            Notification('success', 'Success',res.data.msg)
            router.push("/entry_list")
          }else {
            Notification('error', 'Error',res.data.msg)
          }
        }).catch(error=>{
          console.log(error)
        })
      }
      if (type === 'edit'){
        axios.post('/update/updateAsset',{
          assetId:form.id,
          assetName:form.name,
          classId:form.type,
          supplierId:form.supplier,
          brandId:form.brand,
          locationId:form.location,
        }).then(res=>{
          if (res.data.code === 1){
            Notification('success', 'Success',res.data.msg)
            router.push("/entry_list")
          }else {
            Notification('error', 'Error',res.data.msg)
          }
        }).catch(error=>{
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
          <el-form-item  label="资产名称" prop="name">
            <el-input v-model="form.name" placeholder="Asset name"/>
          </el-form-item>
          <el-form-item label="资产类别" prop="type">
            <el-select v-model="form.type" placeholder="Asset type">
              <el-option
                  v-for="item in typeSelect"
                  :label="item.label" :value="item.value"
                  :key="item.value"

              />
            </el-select>
          </el-form-item>
          <el-form-item label="供应商" prop="supplier">
            <el-select v-model="form.supplier" placeholder="Supplier" :options="options">
              <el-option
                  v-for="item in supplierSelect"
                  :label="item.label" :value="item.value"
                  :key="item.value"
                  />
            </el-select>
          </el-form-item>
          <el-form-item label="品牌" prop="brand">
            <el-select v-model="form.brand" placeholder="Brand">
              <el-option
                  v-for="item in brandSelect"
                  :label="item.label" :value="item.value"
                  />
            </el-select>
          </el-form-item>
          <el-form-item label="存放地点" prop="location">
            <el-select v-model="form.location" placeholder="Place of storage">
              <el-option v-for="item in locationSelect" :label="item.label" :value="item.value" :selected="true"/>
            </el-select>
          </el-form-item>
          <el-form-item v-show="false" prop="date">
            <el-input v-model="form.date" type="text" />
          </el-form-item>
          <el-form-item v-show="false" prop="status">
            <el-input v-model="form.status" type="text" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm(formRef)">
              提交
            </el-button>
            <el-button @click="resetForm(formRef)">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
      <el-button @click="form.type = '1'"></el-button>
    </el-main>
  </el-container>
</template>

<style scoped>

</style>
