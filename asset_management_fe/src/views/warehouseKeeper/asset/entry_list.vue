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
  supplier: string,
  brand: string,
  date: Date
  location: string,
  status: string,
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
  supplier:'',
  brand:'',
  date: new Date(''),
  location:'',
  status:''
})
//定义类型选项
const typeSelect = ref<any>([])
axios.get("/select/getAssetClassOptions")
    .then((res) => {
      if (res.data.code === 1){
        for (let i = 0; i < res.data.assetClassOptions.length; i++) {
          typeSelect.value[i] = {
            label: res.data.assetClassOptions[i].CLASS_NAME,
            value: res.data.assetClassOptions[i].CLASS_ID
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
            value: res.data.supplierOptions[i].SUPPLIER_ID
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
            value: res.data.brandOptions[i].BRAND_ID
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
            value: res.data.locationOptions[i].LOCATION_ID
          }
        }
      }
    })
//定义状态选项
const statusSelect = [
  {
    label:'在库',
    value:'在库'
  },
  {
    label:'已借出',
    value:'已借出'
  },
  {
    label:'报废待审批',
    value:'报废待审批'
  },
  {
    label:'已报废',
    value:'已报废'
  },
  {
    label:'维修中',
    value:'维修中'
  }
]

//提交表单
const submitFrom =  (fromEL:FormInstance | undefined) => {
  if (!fromEL) return
  axios.get("/select/getAssetByCondition")
      .then(res => {
        if (res.data.code === 1){
          Notification('success', 'Success', '查询成功')
          for (let i = 0; i < res.data.asset.length; i++) {
            tableData.value[i] = {
              index : i + 1,
              id : res.data.asset[i].ASSET_ID,
              code: res.data.asset[i].ASSET_CODE,
              name: res.data.asset[i].ASSET_NAME,
              type: res.data.asset[i].CLASS_NAME,
              supplier: res.data.asset[i].SUPPLIER_NAME,
              brand: res.data.asset[i].BRAND_NAME,
              date: res.data.asset[i].DATE,
              location: res.data.asset[i].LOCATION_NAME,
              status: res.data.asset[i].status
            }
          }
        }else {
          Notification('error', 'Error', '查询失败')
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
axios.get("/select/getAsset")
    .then(res => {
      for (let i = 0; i < res.data.asset.length; i++) {
        tableData.value[i] = {
          index : i + 1,
          id : res.data.asset[i].ASSET_ID,
          code: res.data.asset[i].ASSET_CODE,
          name: res.data.asset[i].ASSET_NAME,
          assetType: res.data.asset[i].CLASS_NAME,
          typeId: res.data.asset[i].CLASS_ID,
          supplier: res.data.asset[i].SUPPLIER_NAME,
          supplierId: res.data.asset[i].SUPPLIER_ID,
          brand: res.data.asset[i].BRAND_NAME,
          brandId: res.data.asset[i].BRAND_ID,
          date: res.data.asset[i].DATE,
          location: res.data.asset[i].LOCATION_NAME,
          locationId: res.data.asset[i].LOCATION_ID,
          status: res.data.asset[i].STATUS,
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
  router.push({name:'资产入库表单',
    query: {
      title:'新增资产信息',
      id:tableData.value[row.index-1].id,
      name:tableData.value[row.index-1].name,
      AssetType:tableData.value[row.index-1].typeId,
      supplier:tableData.value[row.index-1].supplierId,
      brand:tableData.value[row.index-1].brandId,
      location:tableData.value[row.index-1].locationId,
      type:"edit"
    }})
}
const handleDelete = (row: any) => {
  axios.get("/delete/deleteAsset",{
    params: {
      //参数
      assetId : tableData.value[row.index-1].id
    }
  }).then(res => {
    //回调函数
    if (res.data.code === 1){
     Notification('success','Success','删除成功')
      tableData.value.splice(row.index-1,1)
    }else {
      Notification('error','Error','删除失败')
    }
  })
}
//添加跳转
const add = () => {
  router.push({name:'资产入库表单',query: {title:'新增资产信息',type:"add"}})
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
        <el-text style="font-size: 20px "><el-icon color="#303133" ><management /></el-icon>资产管理</el-text>
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
          <el-form-item label="资产编号：">
            <el-input v-model="form.code" placeholder="Asset type code" clearable />
          </el-form-item>
          <el-form-item label="资产名称：">
            <el-input v-model="form.name" placeholder="Asset type name" clearable />
          </el-form-item>
          <el-form-item label="资产类别：">
            <el-select v-model="form.type" placeholder="Supplier" clearable>
              <el-option v-for="item in typeSelect" :value="item.value" :label="item.label"/>
            </el-select>
          </el-form-item>
          <el-form-item label="供应商：">
            <el-select v-model="form.supplier" placeholder="Supplier" clearable>
              <el-option v-for="item in supplierSelect" :value="item.value" :label="item.label"/>
            </el-select>
          </el-form-item>
          <el-form-item label="品牌：">
            <el-select v-model="form.brand" placeholder="brand" clearable>
              <el-option v-for="item in brandSelect" :value="item.value" :label="item.label"/>
            </el-select>
          </el-form-item>
          <el-form-item label="入库日期">
            <el-date-picker
              v-model="form.date"
              type="date"
              placeholder="Warehousing date"
              :disabled-date="disabledDate"
              :shortcuts="shortcuts"
              />
          </el-form-item>
          <el-form-item label="存放地点：">
            <el-select v-model="form.location" placeholder="Supplier" clearable>
              <el-option v-for="item in locationSelect" :value="item.value" :label="item.label"/>
            </el-select>
          </el-form-item>
          <el-form-item label="状态：">
            <el-select v-model="form.status" placeholder="Supplier" clearable>
              <el-option v-for="item in statusSelect" :value="item.value" :label="item.label"/>
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
        <el-table-column prop="index" width="50"  />
        <el-table-column prop="code" label="资产编码"  width="200"/>
        <el-table-column prop="name" label="资产名称"  />
        <el-table-column prop="assetType" label="资产类别"  />
        <el-table-column prop="supplier" label="供应商"  />
        <el-table-column prop="brand" label="品牌"  />
        <el-table-column prop="date" label="入库日期"  />
        <el-table-column prop="location" label="存放地点"  />
        <el-table-column prop="status" label="状态" />
        <el-table-column label="操作">
          <template #default="scope">
            <el-button icon="Edit" color="#67C23A" circle @click="handleEdit(scope.$index, scope.row)" />
            <el-divider direction="vertical" />
            <el-button icon="delete" color="#c45656" circle @click="handleDelete( scope.row)" />
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
