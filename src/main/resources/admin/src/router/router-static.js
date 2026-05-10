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
	import huanzhe from '@/views/modules/huanzhe/list'
	import yisheng from '@/views/modules/yisheng/list'
	import zhenliaofangan from '@/views/modules/zhenliaofangan/list'
	import ziyuanfenlei from '@/views/modules/ziyuanfenlei/list'
	import yiliaoziyuan from '@/views/modules/yiliaoziyuan/list'
	import ziyuanshenqing from '@/views/modules/ziyuanshenqing/list'
	import yuyuequxiao from '@/views/modules/yuyuequxiao/list'
	import jinqueziyuan from '@/views/modules/jinqueziyuan/list'
	import paiduishenqing from '@/views/modules/paiduishenqing/list'
	import paiduijindu from '@/views/modules/paiduijindu/list'
	import news from '@/views/modules/news/list'
	import chat from '@/views/modules/chat/list'
	import syslog from '@/views/modules/syslog/list'
	import messages from '@/views/modules/messages/list'
	import users from '@/views/modules/users/list'
	import discussyiliaoziyuan from '@/views/modules/discussyiliaoziyuan/list'
	import discussjinqueziyuan from '@/views/modules/discussjinqueziyuan/list'
import config from '@/views/modules/config/list'

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
		path: '/huanzhe',
		name: '患者',
		component: huanzhe
	}
	,{
		path: '/yisheng',
		name: '医生',
		component: yisheng
	}
	,{
		path: '/zhenliaofangan',
		name: '诊疗方案',
		component: zhenliaofangan
	}
	,{
		path: '/ziyuanfenlei',
		name: '资源分类',
		component: ziyuanfenlei
	}
	,{
		path: '/yiliaoziyuan',
		name: '医疗资源',
		component: yiliaoziyuan
	}
	,{
		path: '/ziyuanshenqing',
		name: '资源申请',
		component: ziyuanshenqing
	}
	,{
		path: '/yuyuequxiao',
		name: '预约取消',
		component: yuyuequxiao
	}
	,{
		path: '/jinqueziyuan',
		name: '紧缺资源',
		component: jinqueziyuan
	}
	,{
		path: '/paiduishenqing',
		name: '排队申请',
		component: paiduishenqing
	}
	,{
		path: '/paiduijindu',
		name: '排队进度',
		component: paiduijindu
	}
	,{
		path: '/news',
		name: '公告资讯',
		component: news
	}
	,{
		path: '/chat',
		name: '互动咨询',
		component: chat
	}
	,{
		path: '/syslog',
		name: '系统日志',
		component: syslog
	}
	,{
		path: '/messages',
		name: '反馈建议',
		component: messages
	}
	,{
		path: '/users',
		name: '管理员',
		component: users
	}
	,{
		path: '/discussyiliaoziyuan',
		name: '医疗资源评论',
		component: discussyiliaoziyuan
	}
	,{
		path: '/discussjinqueziyuan',
		name: '紧缺资源评论',
		component: discussjinqueziyuan
	}
	,{
		path: '/config/:type',
		name: '配置管理',
		component: config
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
