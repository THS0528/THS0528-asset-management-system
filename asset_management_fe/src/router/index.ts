import { createRouter, createWebHashHistory } from 'vue-router'
const routes = [
    {
        path:'/home',
        name:'首页',
        component:()=>import("@/views/index.vue")
    },
    {
        path: '/login',
        name: '登录',
        component: () =>import("@/views/login.vue")
    },
    {
        path: '/assetClass_list',
        name: '资产类别管理',
        component: () =>import("@/views/warehouseKeeper/asset/class_list.vue")
    },
    {
        path: '/assetClass_from',
        name: '资产类别管理表单',
        component: () =>import("@/views/warehouseKeeper/asset/class_from.vue")
    },
    {
        path: '/brand_list',
        name: '品牌管理',
        component: () =>import("@/views/warehouseKeeper/brand_list.vue")
    },
    {
        path: '/brand_from',
        name: '品牌表单',
        component: () =>import("@/views/warehouseKeeper/brand_from.vue")
    },
    {
        path: '/scrapMode_list',
        name: '报废方式管理',
        component: () => import('@/views/warehouseKeeper/scrapMode_list.vue')
    },
    {
        path: '/scrapMode_from',
        name: '报废方式表单',
        component: () =>import("@/views/warehouseKeeper/scrapMode_from.vue")
    },
    {
        path: '/section_list',
        name: '部门管理',
        component: () => import('@/views/warehouseKeeper/section_list.vue')
    },
    {
        path: '/section_from',
        name: '部门表单',
        component: () =>import("@/views/warehouseKeeper/section_from.vue")
    },
    {
        path: '/supplier_list',
        name: '供应商管理',
        component: () => import('@/views/warehouseKeeper/supplier_list.vue')
    },
    {
        path: '/supplier_from',
        name: '供应商表单',
        component: () =>import("@/views/warehouseKeeper/supplier_from.vue")
    },
    {
        path: '/storagePlace_list',
        name: '存放地点管理',
        component: () => import('@/views/warehouseKeeper/storagePlace_list.vue')
    },
    {
        path: '/storagePlace_from',
        name: '存放地点表单',
        component: () =>import("@/views/warehouseKeeper/storagePlace_from.vue")
    },
    {
        path: '/entry_list',
        name: '资产入库',
        component: () => import('@/views/warehouseKeeper/asset/entry_list.vue')
    },
    {
        path: '/assetEntry_from',
        name: '资产入库表单',
        component: () =>import("@/views/warehouseKeeper/asset/entry_from.vue")
    },
    {
        path: '/assetRepayment_list',
        name: '资产借还',
        component: () => import('@/views/warehouseKeeper/asset/repayment_list.vue')
    },
    {
        path: '/assetRepayment_from',
        name: '资产借还表单',
        component: () =>import("@/views/warehouseKeeper/asset/repayment_from.vue")
    },
    {
        path: '/assetRequisition_list',
        name: '资产申购',
        component: () => import('@/views/warehouseKeeper/asset/requisition_list.vue')
    },
    {
        path: '/assetRequisition_from',
        name: '资产申购表单',
        component: () =>import("@/views/warehouseKeeper/asset/requisition_from.vue")
    },
    {
        path: '/assetRequisition_ld',
        name: '资产申购（领导）',
        component: () =>import("@/views/warehouseKeeper/asset/requisition_ld.vue")
    },
    {
        path: '/assetScrap_list',
        name: '资产报废',
        component: () => import('@/views/warehouseKeeper/asset/scrap_list.vue')
    },
    {
        path: '/assetScrap_from',
        name: '资产报废表单',
        component: () =>import("@/views/warehouseKeeper/asset/scrap_from.vue")
    },
    {
        path: '/assetScrap_ld',
        name: '资产报废（领导）',
        component: () =>import("@/views/warehouseKeeper/asset/scrap_ld.vue")
    }
]
const router = createRouter({
  history: createWebHashHistory(),
  routes,
})

export default router
