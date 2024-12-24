<script lang="ts" setup>
import {Edit} from "@element-plus/icons-vue";
import { reactive, ref } from 'vue'
import {ElNotification, FormInstance, FormRules} from 'element-plus'
import axios from "axios";
import {useRoute} from "vue-router";
import router from "@/router";
const route = useRoute()
// 定义RuleForm接口规范表单的数据结构
interface FormINF {
  assetId: string,
  assetCode:string,
  scrapModeId: string,
  retirementDate: Date,
  cause: string,
}
// 定义响应式变量formSize，用于设置表单大小
const formSize = ref('default')
//定义响应式变量ruleFromRef，存储From实例，用于获取表单实例
const formRef = ref<FormInstance>()
// 定义响应式变量ruleForm，存储表单数据
const form = reactive<FormINF>({
  assetId: '',
  assetCode:'',
  scrapModeId: '',
  retirementDate: new Date(),
  cause: '',
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
let scrapModeSelect = ref<any>([])
axios.get('/select/getScrapModeOptions').then(res => {
  for (let i = 0; i < res.data.scrapModeOptions.length; i++) {
    scrapModeSelect.value[i] = {
      label: res.data.scrapModeOptions[i].SCRAP_NAME,
      value: res.data.scrapModeOptions[i].SCRAP_ID
    }
  }
})
// 定义响应式变量rules，存储表单验证规则
const rules = reactive<FormRules<FormINF>>({
  assetId: [
    { required: true, message: '请选择资产名称', trigger: 'blur' },
  ],
  assetCode: [
    {
      required: true, message: '请选择资产类型', trigger: 'blur'},
  ],
  scrapModeId: [
      { required: true, message: '请选择报废方式', trigger: 'blur' },
  ],
  retirementDate: [
      { required: true, message: '请选择报废日期', trigger: 'blur' },
  ],
  cause: [
      { required: true, message: '请输入报废原因', trigger: 'blur' },
  ]
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
// 定义函数submitForm，用于提交表单数据
const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl)return
  await formEl.validate((valid, fields) => {
    if (valid) {
      axios.post('/insert/insertAssetScrap',{
        assetId:form.assetId,
        scrapId:form.scrapModeId,
        date:form.retirementDate,
        cause:form.cause,
        result:"待审批",
        applicant:userName.value
      }).then(res=>{
        if (res.data.code === 1){
          Notification('success','Success',res.data.msg)
          router.push("/assetScrap_list")
        }
        if (res.data.code === 0){
          Notification('error','Error',res.data.msg)
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
            <el-form-item  label="资产名称：" prop="assetName" >
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
            <el-form-item label="资产编码：" prop="assetCode" >
              <el-input v-model="form.assetCode" :disabled="true" placeholder="选择资产自动填写" />
            </el-form-item>
          </div>
          <p>报废信息</p>
          <el-divider/>
          <div style="display: flex; justify-content: space-between">
            <el-form-item label="报废方式：" prop="sectionId">
              <el-select v-model="form.scrapModeId" placeholder="Select a section" style="width: 200px" >
                <el-option
                    v-for="item in scrapModeSelect"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="报废日期：" prop="loanDate" >
              <el-date-picker
                  v-model="form.retirementDate"
                  type="date"
                  placeholder="Warehousing date"
                  :disabled-date="disabledDate"
                  :shortcuts="shortcuts"
              />
            </el-form-item>
          </div>
          <el-form-item label="报废原因：" prop="cause" >
            <el-input v-model="form.cause" type="textarea" />
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
.borrowedInformation p{
  display: inline-block;
  text-align: left;
  margin-left: 10px;
}
</style>
