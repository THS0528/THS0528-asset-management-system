<script lang="ts" setup>
import {reactive, ref} from 'vue'
import axios from "axios";
import router from "@/router";
import index from "@/views/index.vue";
import {ElNotification, FormInstance} from "element-plus";

//表单隐藏属性绑定值
const divFilter = ref(false)
//显示筛选表单按钮绑定值
const active_sw = ref(false)
//显示筛选表单按钮点击事件
const changeFilter = () => {
  divFilter.value = !divFilter.value
  if (pageSize.value === 10){
    pageSize.value = 8
  }else
    pageSize.value = 10
}
//定义assetTypeForm接口，规范表单数据结构
interface FormINF {
  assetName: string,
  assetClass: string,
  supplier: string,
  brand: string,
  cause: string,
  budgetStart: string,
  budgetEnd: string,
  result: string,
}
//定义formRef变量
const formRef = ref<FormInstance>()
/**
 * reactive和ref的区别：
 * reactive：创建的是一个响应式对象，可以用于存储和显示用户输入的数据。
 * ref：创建的是一个响应式引用，用于存储异步处理结果、初始化数据等。
 */
const form = reactive<FormINF>({
  assetName: '',
  assetClass: '',
  supplier: '',
  brand: '',
  cause: '',
  budgetStart: '',
  budgetEnd: '',
  result: '',
})
//定义状态选项
const typeSelect = ref<any>([])
//定义供应商选项
const supplierSelect = ref<any>([])
//定义品牌选项
const brandSelect = ref<any>([])
//定义存放地点选项
const locationSelect = ref<any>([])
//申购结果选项
const resultSelect = [
  {
    label:'待审批',
    value:'待审批'
  },
  {
    label:'审批通过',
    value:'审批通过'
  },
  {
    label:'审批不通过',
    value:'审批不通过'
  }
]

//提交表单
const submitFrom =  (fromEL:FormInstance | undefined) => {
  axios.get("/select/getAssetRTByCondition",{
    params:{
      assetName:form.assetName,
      assetClass:form.assetClass,
      supplierName:form.supplier,
      brandName:form.brand,
      argument:form.cause,
      budgetStart:form.budgetStart,
      budgetEnd:form.budgetEnd,
      result:form.result,
    }
  }).then(res => {
    for (let i = 0; i < res.data.assetRT.length; i++) {
      tableData.value[i] = {
        index : i + 1,
        id : res.data.assetRT[i].id,
        assetName : res.data.assetRT[i].ASSET_NAME,
        assetClass : res.data.assetRT[i].CLASS_NAME,
        supplier : res.data.assetRT[i].SUPPLIER_NAME,
        brand : res.data.assetRT[i].BRAND_NAME,
        cause : res.data.assetRT[i].ARGUMENT,
        applicant : res.data.assetRT[i].APPLICANT,
        budget : res.data.assetRT[i].BUDGET,
        result : res.data.assetRT[i].RESULT
      }
    }
  })
}
//重置(清空所有输入框 FromEl：form实例)
const resetForm = (formEL: FormInstance | undefined) => {
  if (!formEL) return
  formEL.resetFields()
}
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
//日期选择框限制条件
const disabledDate = (time: Date) => {
  return time.getTime() > Date.now()
}
//表格数据
const tableData=ref<any>([])
axios.get("/select/getAssetRT",{
}).then(res => {
  //回调函数
  for (let i = 0; i < res.data.assetRT.length; i++) {
    tableData.value[i] = {
      index : i + 1,
      id : res.data.assetRT[i].id,
      assetName : res.data.assetRT[i].ASSET_NAME,
      assetClass : res.data.assetRT[i].CLASS_NAME,
      supplier : res.data.assetRT[i].SUPPLIER_NAME,
      brand : res.data.assetRT[i].BRAND_NAME,
      cause : res.data.assetRT[i].ARGUMENT,
      applicant : res.data.assetRT[i].APPLICANT,
      budget : res.data.assetRT[i].BUDGET,
      result : res.data.assetRT[i].RESULT
    }
  }
})
//每页数据量
let pageSize = ref(10)
//分页下表
let currentPage = ref(1)
const alterCurrentPage = (page :any) => {
  currentPage.value = page
}
console.log("表格数据")
console.log(tableData.value[0])
//表格操作事件
const handlePass = (row: any) => {
  axios.post("/update/updateAssetRT",{
    id : tableData.value[row.index-1].id,
    result : '审批通过'
  }).then(res => {
    if (res.data.code === 1){
      tableData.value[row.index-1].result = '审批通过'
      Notification('success','Success',res.data.msg)
    }else {
      Notification('error','Error',res.data.msg)
    }
  })
}
const handleFail = (row: any) => {
  axios.post("/update/updateAssetRT",{
    assetRTId : tableData.value[row.index-1].id,
    result : '审批不通过'
  }).then(res => {
    if (res.data.code === 1){
      tableData.value[row.index-1].result = '审批不通过'
      Notification('success','Success','审批成功')
    }else {
      Notification('error','Error','审批失败')
    }
  })
}
//添加跳转
const add = () => {
  router.push({name:'资产申购表单',query: {title:'新增资产申购'}})
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
        <el-text style="font-size: 20px "><el-icon color="#303133" ><management /></el-icon>资产申购</el-text>
        <!--  按钮  -->
        <div>
          <el-switch v-model="active_sw" @click="changeFilter" inline-prompt inactive-text="筛选" active-text="筛选"/>
          <el-divider direction="vertical" />
          <el-button type="info" plain size="small" icon="plus" @click="add"></el-button>
        </div>
      </div>
    </el-header>
    <el-divider />
    <el-main style="margin-top: -25px ;height: 72vh">
      <div v-show="divFilter" >
        <el-form :inline="true" ref="formRef" :model="form" class="demo-form-inline">
          <el-form-item label="资产名称：">
            <el-input v-model="form.assetName" placeholder="Asset type code" clearable />
          </el-form-item>
          <el-form-item label="资产类别：">
            <el-input v-model="form.assetClass" placeholder="Asset type name" clearable />
          </el-form-item>
          <el-form-item label="供应商：">
            <el-input v-model="form.supplier" placeholder="Supplier" clearable/>
          </el-form-item>
          <el-form-item label="品牌：">
            <el-input v-model="form.brand" placeholder="Brand" clearable/>
          </el-form-item>
          <el-form-item label="申购理由：">
            <el-input v-model="form.cause" placeholder="Cause" clearable/>
          </el-form-item>
          <el-form-item label="预算：">
            <el-input v-model="form.budgetStart" style="width: 50px"/> — <el-input v-model="form.budgetEnd" style="width: 50px"/>
          </el-form-item>
          <el-form-item label="申购结果：">
            <el-select v-model="form.result" placeholder="Supplier" clearable>
              <el-option v-for="item in resultSelect" :value="item.value" :label="item.label"/>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitFrom(formRef)">查询</el-button>
            <el-button @click="resetForm(formRef)">重置</el-button>
          </el-form-item>
        </el-form>
        <el-divider />
      </div>
      <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
                style="width: 100%;"
      >
        <el-table-column prop="index" type="index" width="50"  />
        <el-table-column prop="assetName" label="资产名称"  />
        <el-table-column prop="assetClass" label="资产类别"  />
        <el-table-column prop="supplier" label="供应商"  />
        <el-table-column prop="brand" label="品牌"  />
        <el-table-column prop="cause" label="申购理由"  />
        <el-table-column prop="applicant" label="提交人"  />
        <el-table-column prop="budget" label="预算（元）"  />
        <el-table-column prop="result" label="审批结果" />
        <el-table-column label="操作">
          <template #default="scope">
            <el-button icon="Select" color="#00ff00" circle @click="handlePass(scope.row)" />
            <el-divider direction="vertical"/>
            <el-button icon="CloseBold" color="#e30022" circle @click="handleFail(scope.row)" />
          </template>
        </el-table-column>
      </el-table>
    </el-main>
    <el-footer>
      <div class="example-pagination-block">
        <el-pagination
            layout="prev, pager, next"
            @current-change="alterCurrentPage"
            :current-page="currentPage"
            :page-size="pageSize"
            :total="tableData.length" />
      </div>
    </el-footer>
  </el-container>
</template>

<style>
.demo-form-inline .el-input {
  --el-input-width: 150px;
}

.demo-form-inline .el-select {
  --el-select-width: 150px;
}

</style>
