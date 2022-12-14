import Vue from 'vue'
import 'amfe-flexible'
import App from './App.vue'
import router from './routers'
import store from './store'
import utils from './utils'
import * as filters from './filters' // global filters
import './icons' // icon

import {
  Toast,
  Field,
  NavBar,
  Button,
  Divider,
  DropdownMenu,
  DropdownItem,
  Form,
  Swipe,
  SwipeItem,
  Lazyload,
  NoticeBar,
  Tabbar,
  TabbarItem,
  Tab,
  Tabs,
  Collapse,
  CollapseItem,
  Icon,
  Cell,
  CellGroup,
  Search,
  ActionSheet,
  Dialog,
  Uploader,
  Grid,
  GridItem
} from 'vant'
Vue.use(Toast)
Vue.use(Field)
Vue.use(NavBar)
Vue.use(Button)
Vue.use(Divider)
Vue.use(DropdownMenu)
Vue.use(DropdownItem)
Vue.use(Form)
Vue.use(Swipe)
Vue.use(SwipeItem)
Vue.use(Lazyload)
Vue.use(NoticeBar)
Vue.use(Tabbar)
Vue.use(TabbarItem)
Vue.use(Tab)
Vue.use(Tabs)
Vue.use(Collapse)
Vue.use(CollapseItem)
Vue.use(Icon)
Vue.use(Cell)
Vue.use(CellGroup)
Vue.use(Search)
Vue.use(ActionSheet)
Vue.use(Dialog)
Vue.use(Uploader)
Vue.use(Grid)
Vue.use(GridItem)

import VueWechatTitle from 'vue-wechat-title'
Vue.use(VueWechatTitle)

if (process.env.NODE_ENV === 'development' && process.env.VUE_APP_DEBUG === 'true') {
  import('eruda').then(module => {
    var el = document.createElement('div')
    document.body.appendChild(el)
    module.default.init({
      container: el
    })
  }).catch(err => {
    console.log(err.message)
  })
}

// 注册全局utils
window.utils = utils

// register global utility filters
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
