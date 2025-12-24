import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
    import tongzhixiaoxi from '@/views/modules/tongzhixiaoxi/list'
    import news from '@/views/modules/news/list'
    import liancheyuyue from '@/views/modules/liancheyuyue/list'
    import jiaolian from '@/views/modules/jiaolian/list'
    import xueyuan from '@/views/modules/xueyuan/list'
    import kebiaoxinix from '@/views/modules/kebiaoxinix/list'
    import storeup from '@/views/modules/storeup/list'
    import kechengxinxi from '@/views/modules/kechengxinxi/list'
    import fuwupingjia from '@/views/modules/fuwupingjia/list'
    import kechengjilu from '@/views/modules/kechengjilu/list'
    import xuexijilu from '@/views/modules/xuexijilu/list'
    import kechengfenlei from '@/views/modules/kechengfenlei/list'
    import chengjixinxi from '@/views/modules/chengjixinxi/list'
    import scheduletime from '@/views/modules/scheduletime/list'
    import xiaoqu from '@/views/modules/xiaoqu/list'
    import kechengyuyue from '@/views/modules/kechengyuyue/list'
    import cheliangxinxi from '@/views/modules/cheliangxinxi/list'
    import config from '@/views/modules/config/list'
    import newstype from '@/views/modules/newstype/list'


//2.配置路由   注意：名字
export const routes = [{
    path: '/',
    name: '系统首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '系统首页',
      component: Home,
      meta: {icon:'', title:'center', affix: true}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    }
      ,{
	path: '/tongzhixiaoxi',
        name: '通知消息',
        component: tongzhixiaoxi
      }
      ,{
	path: '/news',
        name: '公告信息',
        component: news
      }
      ,{
	path: '/liancheyuyue',
        name: '练车预约',
        component: liancheyuyue
      }
      ,{
	path: '/jiaolian',
        name: '教练',
        component: jiaolian
      }
      ,{
	path: '/xueyuan',
        name: '学员',
        component: xueyuan
      }
      ,{
	path: '/kebiaoxinix',
        name: '课表信息',
        component: kebiaoxinix
      }
      ,{
	path: '/storeup',
        name: '我的收藏',
        component: storeup
      }
      ,{
	path: '/kechengxinxi',
        name: '课程信息',
        component: kechengxinxi
      }
      ,{
	path: '/fuwupingjia',
        name: '服务评价',
        component: fuwupingjia
      }
      ,{
	path: '/kechengjilu',
        name: '课程记录',
        component: kechengjilu
      }
      ,{
	path: '/xuexijilu',
        name: '学习记录',
        component: xuexijilu
      }
      ,{
	path: '/kechengfenlei',
        name: '课程分类',
        component: kechengfenlei
      }
      ,{
	path: '/chengjixinxi',
        name: '成绩信息',
        component: chengjixinxi
      }
      ,{
	path: '/scheduletime',
        name: '课表时间',
        component: scheduletime
      }
      ,{
	path: '/xiaoqu',
        name: '校区',
        component: xiaoqu
      }
      ,{
	path: '/kechengyuyue',
        name: '课程预约',
        component: kechengyuyue
      }
      ,{
	path: '/cheliangxinxi',
        name: '车辆信息',
        component: cheliangxinxi
      }
      ,{
	path: '/config',
        name: '轮播图管理',
        component: config
      }
      ,{
	path: '/newstype',
        name: '公告信息分类',
        component: newstype
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
   return originalPush.call(this, location).catch(err => err)
}
export default router;
