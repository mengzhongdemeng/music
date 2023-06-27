import { Message } from 'element-ui'
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const constantRoutes = [
  {
    path: '/Home',
    component: resolve => require(['../pages/Home.vue'], resolve),
    meta: {
      title: '自述文件',
      requireAuth: true
    },
    children: [
      {
        path: '/Info',
        component: resolve => require(['../pages/InfoPage.vue'], resolve),
        meta: {
          title: 'Info',
          requireAuth: true
        }
      },
      {
        path: '/song',
        component: resolve => require(['../pages/SongPage.vue'], resolve),
        meta: {
          title: 'Song',
          requireAuth: true
        }
      },
      {
        path: '/Singer',
        component: resolve => require(['../pages/SingerPage.vue'], resolve),
        meta: {
          title: 'Singer',
          requireAuth: true
        }
      },
      {
        path: '/SongList',
        component: resolve => require(['../pages/SongListPage.vue'], resolve),
        meta: {
          title: 'SongList',
          requireAuth: true
        }
      },
      {
        path: '/ListSong',
        component: resolve => require(['../pages/ListSongPage.vue'], resolve),
        meta: {
          title: 'ListSong',
          requireAuth: true
        }
      },
      {
        path: '/Comment',
        component: resolve => require(['../pages/CommentPage.vue'], resolve),
        meta: {
          title: 'Comment',
          requireAuth: true
        }
      },
      {
        path: '/Consumer',
        component: resolve => require(['../pages/ConsumerPage.vue'], resolve),
        meta: {
          title: 'Consumer',
          requireAuth: true
        }
      },
      {
        path: '/Collect',
        component: resolve => require(['../pages/CollectPage.vue'], resolve),
        meta: {
          title: 'Collect',
          requireAuth: true
        }
      }
    ]
  },
  {
    path: '/',
    name: 'login',
    component: resolve => require(['../pages/Login.vue'], resolve)
  }
]

const router = new Router({
  mode: 'history',
  scrollBehavior: () => ({ x: 0, y: 0 }),
  routes: constantRoutes
})

router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) {
    if (sessionStorage.getItem('token') !== null) {
      next()
    } else {
      next(
        {
          name: 'login'
        }
      )
      Message({
        showClose: true,
        message: '未登录或登录已过期，无法获取管理员权限，请登录！',
        type: 'error'
      })
    }
  } else {
    next()
  }
})

export default router
