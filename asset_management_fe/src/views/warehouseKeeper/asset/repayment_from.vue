<script lang="ts" setup>
import {Edit} from "@element-plus/icons-vue";
import { reactive, ref } from 'vue'
import {ElNotification, FormInstance, FormRules} from 'element-plus'
import axios from "axios";
import {useRoute} from "vue-router";
import router from "@/router";
const route = useRoute()
//归还输入框（归还时显示）
let back = ref(false)
//归还p（查看时显示）
let backP = ref(false)
// 新增输入框（新增时显示，其他时都不显示）
let add = ref(true)
//新增p（归还时、查看时显示）
let addP = ref(false)
//提交按钮（查看时不显示，其他情况都显示）
let submit = ref(true)
//归还显示
if(route.query.type === 'back'){
  add.value = false
  back.value = true
  addP.value = true
}
//查看显示
if(route.query.type === 'see'){
  add.value = false
  back.value = false
  addP.value = true
  backP.value = true
  submit.value = false
}
// 定义RuleForm接口规范表单的数据结构
interface FormINF {
  assetId: string,
  assetCode:string,
  sectionId: string,
  loanDate: Date,
  cause: string,
  backDate: Date,
  remark: string,
}
// 定义响应式变量formSize，用于设置表单大小
const formSize = ref('default')
//定义响应式变量ruleFromRef，存储From实例，用于获取表单实例
const formRef = ref<FormInstance>()
// 定义响应式变量ruleForm，存储表单数据
const form = reactive<FormINF>({
  assetId: '',
  assetCode:'',
  sectionId: '',
  loanDate: new Date(),
  cause: '',
  backDate: new Date(),
  remark: '',

})
//定义响应式变量asset，存储资产数据
let assetSelect = ref<any>([])
//获取资产数据
axios.get('/select/getAssetOption').then(res => {
  for (let i = 0; i < res.data.assetOptions.length; i++) {
    assetSelect.value[i] = {
      label: res.data.assetOptions[i].ASSET_NAME,
      value: res.data.assetOptions[i].ASSET_ID,
      assetCode : res.data.assetOptions[i].ASSET_CODE
    }
  }
})
const assetClick = (assetCode:string) => {
  form.assetCode = assetCode
}
let sectionSelect = ref<any>([])
axios.get('/select/getSectionOption').then(res => {
  for (let i = 0; i < res.data.sectionOptions.length; i++) {
    sectionSelect.value[i] = {
      label: res.data.sectionOptions[i].SECTION_NAME,
      value: res.data.sectionOptions[i].SECTION_ID
    }
  }
})
console.log("zc")
console.log(assetSelect.value)
// 定义响应式变量rules，存储表单验证规则
const rules = reactive<FormRules<FormINF>>({
  assetId: [
    { required: true, message: '请选择资产名称', trigger: 'blur' },
  ],
  assetCode: [
    {
      required: true, message: '请选择资产类型', trigger: 'blur'},
  ],
  sectionId: [
    {required:true,message:'请选择借用部门',trigger:'blur'}
  ],
  loanDate: [
    {required:true,message:'请选择借用日期',trigger:'blur'}
  ],
  cause:[
      {required:true,message:'请填写借用原因',trigger:'blur'}
  ]
})
// 定义函数submitForm，用于提交表单数据
const submitForm = async (formEl: FormInstance | undefined) => {
  if (route.query.type === 'back'){
    axios.post('/update/updateAssetRM',{
      assetId:route.query.assetId,
      borrowedCode:route.query.borrowedCode,
      backDate:form.backDate,
      backRemark:form.remark,
    }).then(res=>{
      if (res.data.code === 1){
        Notification('success','Success',res.data.msg)
        router.push("/assetRepayment_list")
      }
      if (res.data.code === 0){
        Notification('error','Error',res.data.msg)
      }
    }).catch(error=>{
      console.log(error)
    })
  }
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {

     if (route.query.type === 'add'){
       axios.post('/insert/insertAssetRM',{
         assetId:form.assetId,
         sectionId:form.sectionId,
         loanDate:form.loanDate,
         cause:form.cause,
       }).then(res=>{
         if (res.data.code === 1){
           Notification('success','Success',res.data.msg)
           router.push("/assetRepayment_list")
         }
         if (res.data.code === 0){
           Notification('error','Error',res.data.msg)
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
//日期选择框快捷选择
const shortcuts = [
  {
    text: '今天',
    value: new Date(),
  },
  {
    text: '昨天',
    value: () => {
      const date = new Date()
      date.setTime(date.getTime() - 3600 * 1000 * 24)
      return date
    },
  },
  {
    text: '一周前',
    value: () => {
      const date = new Date()
      date.setTime(date.getTime() - 3600 * 1000 * 24 * 7)
      return date
    },
  },
]
//如果参数日期比现在的日期大，则不可选择（日期失效）
const disabledDate = (time: Date) => {
  return time.getTime() > Date.now()
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
console.log("数据")
console.log(route.query
)
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

          <p>资产信息</p>
          <el-divider/>
          <div style="display: flex; justify-content: space-between">
            <el-form-item  label="资产名称：" prop="assetName" v-show="add">
              <el-select v-model="form.assetId" style="width: 200px" placeholder="Select an asset name" >
                <el-option
                  v-for="item in assetSelect"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                  @click="assetClick(item.assetCode)"
                >
                </el-option>
              </el-select>

            </el-form-item>
            <p  v-show="addP">资产名称：<span>{{ route.query.assetName }}</span></p>
            <el-form-item label="资产编码：" prop="assetCode" v-show="add">
              <el-input v-model="form.assetCode" :disabled="true" placeholder="选择资产自动填写" />
            </el-form-item>
            <p v-show="addP">资产编码：<span>{{route.query.assetCode}}</span></p>
          </div>
          <p>借用信息</p>
          <el-divider/>
          <div style="display: flex; justify-content: space-between">
            <el-form-item label="借用部门" prop="sectionId" v-show="add">
              <el-select v-model="form.sectionId" placeholder="Select a section" style="width: 200px" >
                <el-option
                    v-for="item in sectionSelect"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                />
              </el-select>
            </el-form-item>
            <p v-show="addP">借用部门：<span>{{ route.query.sectionName }}</span></p>
            <el-form-item label="借用日期" prop="loanDate" v-show="add">
              <el-date-picker
                  v-model="form.loanDate"
                  type="date"
                  placeholder="Warehousing date"
                  :disabled-date="disabledDate"
                  :shortcuts="shortcuts"
              />
              </el-form-item>
              <p v-show="addP">借用日期：<span>{{route.query.loanDate}}</span></p>
            </div>
            <el-form-item label="借用原因：" prop="cause" v-show="add">
              <el-input v-model="form.cause" type="textarea" />
            </el-form-item>
            <p v-show="addP" style="text-align: left">借用原因：<span>{{route.query.cause}}</span></p><br>
            <el-form-item  label="归还日期：" prop="backDate" v-show="back">
              <el-date-picker
                  v-model="form.backDate"
                  :disabled-date="disabledDate"
              />
            </el-form-item>
          <p v-show="backP" style="text-align: left">归还日期：<span>{{route.query.backDate}}</span></p><br>
            <el-form-item  label="归还备注：" prop="remark" v-show="back">
              <el-input v-model="form.remark" type="textarea" />
            </el-form-item>
          <p v-show="backP" style="text-align: left">归还备注：<span>{{route.query.backRemark}}</span></p><br>
          <el-form-item v-show="submit">
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
.borrowedInformation p{
  display: inline-block;
  text-align: left;
  margin-left: 10px;
}
</style>
