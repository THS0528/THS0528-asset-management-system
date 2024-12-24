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
  code: string,
  name: string,
  type: string,
  section: string,
}
//定义formRef变量
const formRef = ref<FormInstance>()
/**
 * reactive和ref的区别：
 * reactive：创建的是一个响应式对象，可以用于存储和显示用户输入的数据。
 * ref：创建的是一个响应式引用，用于存储异步处理结果、初始化数据等。
 */
const form = reactive<FormINF>({
  code:'',
  name:'',
  type:'',
  section:'',
})
//定义状态选项
const typeSelect = ref<any>([])
//定义供应商选项
const supplierSelect = ref<any>([])
//定义品牌选项
const brandSelect = ref<any>([])
//定义存放地点选项
const locationSelect = ref<any>([])
//定义状态选项
const statusSelect = [
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
  axios.get("/select/getAssetRMByCondition",{
    params:{
      code:form.code,
      name:form.name,
      type:form.type,
      section:form.section,
    }
  }).then(res =>{
    if (res.data.code === 1){
      Notification("success","Success",res.data.msg)
      tableData.value = []
      for (let i = 0; i < res.data.assetRM.length; i++) {
        tableData.value[i] = {
          index: i + 1,
          borrowedCode:res.data.assetRM[i].BORROWED_CODE,
          assetCode:res.data.assetRM[i].ASSET_CODE,
          assetName:res.data.assetRM[i].ASSET_NAME,
          assetId:res.data.assetRM[i].ASSET_ID,
          type:res.data.assetRM[i].CLASS_NAME,
          loanDate:res.data.assetRM[i].LOAN_DATE,
          section:res.data.assetRM[i].SECTION_NAME,
          cause:res.data.assetRM[i].CAUSE,
          backDate:res.data.assetRM[i].BACK_DATE,
          remark:res.data.assetRM[i].BACK_REMACK,
          status : res.data.assetRM[i].STATUS === 1
        }
      }
    }else {
      Notification("error","Error",res.data.msg)
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
//获取表格数据
axios.get("/select/getAssetRM")
    .then(res =>{
      if (res.data.code === 1){
        for (let i = 0; i < res.data.assetRM.length; i++) {
          tableData.value[i] = {
            index: i + 1,
            borrowedCode:res.data.assetRM[i].BORROWED_CODE,
            assetCode:res.data.assetRM[i].ASSET_CODE,
            assetName:res.data.assetRM[i].ASSET_NAME,
            assetId:res.data.assetRM[i].ASSET_ID,
            type:res.data.assetRM[i].CLASS_NAME,
            loanDate:res.data.assetRM[i].LOAN_DATE,
            section:res.data.assetRM[i].SECTION_NAME,
            cause:res.data.assetRM[i].CAUSE,
            backDate:res.data.assetRM[i].BACK_DATE,
            remark:res.data.assetRM[i].BACK_REMARK,
            status : res.data.assetRM[i].STATUS === 0
          }
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
//表格操作事件
const handleEdit = (index: number, row: any) => {
  console.log(row)
  router.push({name:'资产借还表单',
    query: {
      title:'归还资产',
      assetId:row.assetId,
      borrowedCode:row.borrowedCode,
      assetName:row.assetName,
      assetCode:row.assetCode,
      loanDate:row.loanDate,
      sectionName:row.section,
      cause:row.cause,
      type:"back"
    }
  })
}
const handleShow = (row: any) => {
  console.log(row)
  console.log("表格数据")
  console.log(tableData)
   router.push({name:'资产借还表单',
    query: {
      title:'查看借用详情',
      assetId:row.assetId,
      borrowedCode:row.borrowedCode,
      assetName:row.assetName,
      assetCode:row.assetCode,
      loanDate:row.loanDate,
      sectionName:row.section,
      cause:row.cause,
      backDate:row.backDate,
      backRemark:tableData.value[row.index-1].remark,

      type:"see"
    }
  })
}
//添加跳转
const add = () => {
  router.push({name:'资产借还表单',query: {title:'借用登记',type :'add'}})
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
        <el-text style="font-size: 20px "><el-icon color="#303133" ><management /></el-icon>资产借还管理</el-text>
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
          <el-form-item label="资产编号：" prop="code">
            <el-input v-model="form.code" placeholder="Asset code" clearable />
          </el-form-item>
          <el-form-item label="资产名称：" prop="name">
            <el-input v-model="form.name" placeholder="Asset name" clearable />
          </el-form-item>
          <el-form-item label="资产类别：" prop="type">
            <el-input v-model="form.name" placeholder="Asset type" clearable />
          </el-form-item>
          <el-form-item label="借用部门：" prop="section">
            <el-input v-model="form.name" placeholder="Supplier" clearable />
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
        <el-table-column prop="index" width="50"  />
        <el-table-column prop="borrowedCode" label="借用编码" width="150"/>
        <el-table-column prop="assetCode" label="资产编码"  width="150"/>
        <el-table-column prop="assetName" label="资产名称"  />
        <el-table-column prop="type" label="资产类别"  />
        <el-table-column prop="loanDate" label="借用日期"/>
        <el-table-column prop="section" label="借用部门"  />
        <el-table-column prop="cause" label="借用原因"  />
        <el-table-column prop="backDate" label="归还日期"  />
        <el-table-column label="操作">
          <template #default="scope">
            <el-button icon="Download" circle @click="handleEdit(scope.$index, scope.row)" v-show="scope.row.status"/>
            <el-divider direction="vertical" />
            <el-button icon="View"  circle @click="handleShow(scope.row)" />
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
