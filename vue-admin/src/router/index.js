import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '社区数据可视化', icon: 'dashboard' }
    }]
  },

  {
    path: '/manage',
    component: Layout,
    redirect: '/manage/elder',
    name: 'Manage',
    meta: { title: '社区人员管理', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'elder',
        name: 'Elder',
        component: () => import('@/views/elder/index'),
        meta: { title: '老人管理', icon: 'table' }
      },
      {
        path: 'children',
        name: 'Children',
        component: () => import('@/views/children/index'),
        meta: { title: '子女管理', icon: 'tree' }
      },
      {
        path: 'relations',
        name: 'Relations',
        component: () => import('@/views/relations/index'),
        meta: { title: '老人子女关系', icon: 'excel' }
      }
    ]
  },

  {
    path: '/affaires',
    component: Layout,
    redirect: '/affairs/news',
    name: 'Affirs',
    meta: { title: '事务处理', icon: 'el-icon-document' },
    children: [
      {
        path: 'news',
        name: 'News',
        component: () => import('@/views/news/index'),
        meta: { title: '社区新闻', icon: 'form' }
      },
      {
        path: 'emergency',
        name: 'Emergency',
        component: () => import('@/views/emergency/index'),
        meta: { title: '紧急事件', icon: 'international' }
      },
    ]
  },



  {
    path: 'Others Creations',
    component: Layout,
    children: [
      {
        path: 'http://124.222.186.200:7674/',
        meta: { title: 'Other Creations', icon: 'link' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
