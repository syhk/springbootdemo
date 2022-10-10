import { createRouter, createWebHashHistory, RouteRecordRaw, createWebHistory } from 'vue-router'
const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'home',
        component: () => import('../components/HelloWorld.vue')
    }, {
        path: '/login',
        name: 'login',
        component: () => import('../components/loginbox.vue')
    },
    {
        path: '/nwfbox',
        name: 'nwfbox',
        component: () => import('../components/nwfbox.vue')
    },
    {
        path: '/test_EP',
        name: 'test_EP',
        component: () => import("../components/element_test.vue")
    }
]

const router = createRouter({
    // createWebHashHistory() 路由模式路径带 # 号
    // createWebHistory 路由模式路径不带 # 号
    // history 模式可以使用 history.state 获取完整的路由信息，后端可以获取完整的路由信
    // hash 模式：改变 # 后的路径不会自动刷新页面
    history: createWebHistory(),
    routes
})

export default router