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
    pageSize.value = 7
  }else
    pageSize.value = 10
}
//定义assetTypeForm接口，规范表单数据结构
interface FormINF {
  name: string,
  type: string,
  instructions : string,
  status: '',
}
//定义formRef变量
const formRef = ref<FormInstance>()
/**
 * reactive和ref的区别：
 * reactive：创建的是一个响应式对象，可以用于存储和显示用户输入的数据。
 * ref：创建的是一个响应式引用，用于存储异步处理结果、初始化数据等。
 */
const form = reactive<FormINF>({
  name:'',
  type:'',
  instructions:'',
  status:''
})
//定义状态选项
const statusSelect = [
  {
    label:'正常',
    value:1
  },
  {
    label:'停用',
    value:0
  }
]

//提交表单
const submitFrom =  (fromEL:FormInstance | undefined) => {
  if (!fromEL) return
  axios.get("/select/getLocationByCondition",{
    params:{
      name:form.name,
      type:form.type,
      instructions:form.instructions,
      status:form.status
    }
  }).then(res => {
    if (res.data.code === 1){
      Notification('Success',"success",res.data.msg)
      for (let i = 0; i < res.data.location.length; i++) {
        tableData.value[i] = {
          index : i+1,
          id : res.data.location[i].LOCATION_ID,
          name : res.data.location[i].LOCATION_NAME,
          type : res.data.location[i].LOCATION_TYPE,
          instructions : res.data.location[i].LOCATION_DESCRIPTION,
          status :  res.data.location[i].STATUS === 1 ? '正常':'停用',
          enable : res.data.location[i].STATUS === 1
        }
      }
    }else {
      Notification('Error',"error",res.data.msg)
    }
  })


}
//重置(清空所有输入框 FromEl：form实例)
const resetForm = (formEL: FormInstance | undefined) => {
  if (!formEL) return
  formEL.resetFields()
}
//表格数据
const tableData=ref<any>([])
axios.get("select/getLocation")
    .then(res => {
      for (let i = 0; i < res.data.location.length; i++) {
        tableData.value[i] = {
          index : i+1,
          id : res.data.location[i].LOCATION_ID,
          name : res.data.location[i].LOCATION_NAME,
          type : res.data.location[i].LOCATION_TYPE,
          instructions : res.data.location[i].LOCATION_DESCRIPTION,
          status :  res.data.location[i].STATUS === 1 ? '正常':'停用',
          enable : res.data.location[i].STATUS === 1
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
  console.log(row);
  router.push({name:'存放地点表单',
    query: {
      title:'修改存放地点',
      id:tableData.value[row.index-1].id,
      name:row.name,
      locationType:row.type,
      instructions:row.instructions,
      type:'edit'
    }})
}
//添加跳转
const add = () => {
  router.push({name:'存放地点表单',query: {title:'新增存放地点',type:'add'}})
}
//修改状态
const changeState = ( row: any) => {
  console.log(row);
  tableData.value[row.index-1].status = row.status === '正常'?'停用':'正常'
  console.log(tableData.value[row.index - 1]);
  axios.post('/update/updateLocationStatus',{
    supplierId : tableData.value[row.index-1].id,
    status : row.status === '正常'?1:0
  }).then(res => {
    if (res.data.code === 1) {
      Notification("success","Success",res.data.msg)
    }else {
      Notification("error","Error",res.data.msg)
    }
  })
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
        <el-text style="font-size: 20px "><el-icon color="#303133" ><management /></el-icon>供应商管理</el-text>
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
          <el-form-item label="名称：">
            <el-input v-model="form.name" placeholder="Name of place" clearable />
          </el-form-item>
          <el-form-item label="存放地点类型：">
            <el-input v-model="form.type" placeholder="Type of place" clearable />
          </el-form-item>
          <el-form-item label="说明：">
            <el-input v-model="form.instructions" placeholder="Instructions" clearable />
          </el-form-item>
          <el-form-item label="状态：">
            <el-select v-model="form.status" placeholder="Status" clearable>
              <el-option
                v-for="item in statusSelect"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
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
        <el-table-column prop="index" width="50"/>
        <el-table-column prop="name" label="存放地点名称"  />
        <el-table-column prop="type" label="存放地点类型"  />
        <el-table-column prop="instructions" label="说明"  />
        <el-table-column prop="status" label="状态"  />
        <el-table-column label="操作" >
          <template #default="scope">
            <el-button icon="Edit" color="#67C23A" circle @click="handleEdit(scope.$index, scope.row)" />
            <el-divider direction="vertical" />
            <el-switch
                v-model="scope.row.enable"
                @click="changeState(scope.row)"
                active-action-icon="Select"
                inactive-action-icon="CloseBold"
                style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949"
            />
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
