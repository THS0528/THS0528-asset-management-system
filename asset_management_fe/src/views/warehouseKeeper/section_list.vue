<script lang="ts" setup>
import {reactive, ref} from 'vue'
import axios from "axios";
import {ElNotification, FormInstance} from "element-plus";
import router from "@/router";
//获取用户id
const userId = localStorage.getItem('userId')
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
//添加跳转
const add = () => {
  router.push({name:'部门表单',query: {title:'新增部门',type:'add'}})
}
//定义FromINF接口，规范表单数据结构
interface FormINF {
  code: string,
  name: string,
  status: string,
}
//定义fromRef变量
const formRef = ref<FormInstance>()
// 定义响应式变量assetTypeForm，存储表单数据
/**
 * reactive和ref的区别：
 * reactive：创建的是一个响应式对象，可以用于存储和显示用户输入的数据。
 * ref：创建的是一个响应式引用，用于存储异步处理结果、初始化数据等。
 */
const form = reactive<FormINF>({
  code:'',
  name:'',
  status:''
})

//定义select数组
const statusSelect=[
  {
    label:'正常',
    value:'1'
  },
  {
    label:'停用',
    value:'0'
  },
]
//提交
const submit =  () => {
  axios.get("/select/getSectionByCondition", {
    params: {
      sectionName : form.name,
      sectionCode : form.code,
      status : form.status
    }
  }).then(res => {
    if (res.data.code === 1){
      Notification('success','Success',res.data.msg)
      tableData.value = []
      for (let i = 0; i < res.data.section.length; i++) {
        tableData.value[i] = {
          index : i+1,
          id : res.data.section[i].SECTION_ID,
          code : res.data.section[i].SECTION_CODE,
          name : res.data.section[i].SECTION_NAME,
          status : res.data.section[i].STATUS === 1?"正常":"停用",
          enable : res.data.section[i].STATUS === 1
        }
      }
    }else {
      Notification('error','Error',res.data.msg)
    }
    console.log(res)
  })
  // }

}
//重置(清空所有输入框)
const reset = (formEl:FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}
//表格数据
let tableData = ref<any>([])
//获取列表数据
//获取数据
axios.get('/select/getSection')
    .then((res) => {
      // 执行操作
      for (let i = 0; i < res.data.section.length; i++) {
        tableData.value[i] = {
          index : i+1,
          id : res.data.section[i].SECTION_ID,
          code : res.data.section[i].SECTION_CODE,
          name : res.data.section[i].SECTION_NAME,
          status : res.data.section[i].STATUS === 1?"正常":"停用",
          enable : res.data.section[i].STATUS === 1
        }
      }
      console.log(tableData.value);
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
//每页数据量
let pageSize = ref(10)
//分页下表
let currentPage = ref(1)
//切换页面执行方法
const alterCurrentPage = (page:any) => {
  currentPage.value = page
}
//表格操作事件
const handleEdit = (row: any) => {
  router.push({name:'部门表单',
    query: {
      title:'编辑部门信息',
      id:tableData.value[row.index-1].id,
      code:row.code,
      name:row.name,
      status:row.status,
      type:'edit'
    }
  })
}
const changeState = ( row: any) => {
  console.log(row);
  tableData.value[row.index-1].status = row.status === '正常'?'停用':'正常'
  axios.post('/update/updateSectionStatus',{
    sectionId : tableData.value[row.index-1].id,
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
        <el-text style="font-size: 20px "><el-icon color="#303133"><Management /></el-icon>部门管理</el-text>
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
          <el-form-item label="编号：" prop="code">
            <el-input v-model="form.code" placeholder="Section type code" clearable />
          </el-form-item>
          <el-form-item label="名称：" prop="name">
            <el-input v-model="form.name" placeholder="Section type name" clearable />
          </el-form-item>
          <el-form-item label="状态：" prop="status">
            <el-select v-model="form.status" placeholder="Status" clearable >
              <el-option v-for="item in statusSelect" :value="item.value" :label="item.label"/>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submit">查询</el-button>
            <el-button @click="reset(formRef)">重置</el-button>
          </el-form-item>
        </el-form>
        <el-divider />
      </div>
      <el-table :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
                style="width: 100%;"
      >
        <el-table-column prop="index" width="50"  />
        <el-table-column prop="code" label="编码"  />
        <el-table-column prop="name" label="名称"  />
        <el-table-column prop="status" label="状态"  />
        <el-table-column label="操作">
          <template #default="scope">
            <el-button icon="Edit" circle @click="handleEdit(scope.row)" />
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
  --el-input-width: 220px;
}

.demo-form-inline .el-select {
  --el-select-width: 220px;
}
</style>
