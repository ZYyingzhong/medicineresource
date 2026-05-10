import VueRouter from 'vue-router'
//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Messages from '../pages/messages/list'
import Storeup from '../pages/storeup/list'
import payList from '../pages/pay'

import huanzheList from '../pages/huanzhe/list'
import huanzheDetail from '../pages/huanzhe/detail'
import huanzheAdd from '../pages/huanzhe/add'
import yishengList from '../pages/yisheng/list'
import yishengDetail from '../pages/yisheng/detail'
import yishengAdd from '../pages/yisheng/add'
import zhenliaofanganList from '../pages/zhenliaofangan/list'
import zhenliaofanganDetail from '../pages/zhenliaofangan/detail'
import zhenliaofanganAdd from '../pages/zhenliaofangan/add'
import ziyuanfenleiList from '../pages/ziyuanfenlei/list'
import ziyuanfenleiDetail from '../pages/ziyuanfenlei/detail'
import ziyuanfenleiAdd from '../pages/ziyuanfenlei/add'
import yiliaoziyuanList from '../pages/yiliaoziyuan/list'
import yiliaoziyuanDetail from '../pages/yiliaoziyuan/detail'
import yiliaoziyuanAdd from '../pages/yiliaoziyuan/add'
import ziyuanshenqingList from '../pages/ziyuanshenqing/list'
import ziyuanshenqingDetail from '../pages/ziyuanshenqing/detail'
import ziyuanshenqingAdd from '../pages/ziyuanshenqing/add'
import yuyuequxiaoList from '../pages/yuyuequxiao/list'
import yuyuequxiaoDetail from '../pages/yuyuequxiao/detail'
import yuyuequxiaoAdd from '../pages/yuyuequxiao/add'
import jinqueziyuanList from '../pages/jinqueziyuan/list'
import jinqueziyuanDetail from '../pages/jinqueziyuan/detail'
import jinqueziyuanAdd from '../pages/jinqueziyuan/add'
import paiduishenqingList from '../pages/paiduishenqing/list'
import paiduishenqingDetail from '../pages/paiduishenqing/detail'
import paiduishenqingAdd from '../pages/paiduishenqing/add'
import paiduijinduList from '../pages/paiduijindu/list'
import paiduijinduDetail from '../pages/paiduijindu/detail'
import paiduijinduAdd from '../pages/paiduijindu/add'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import discussyiliaoziyuanList from '../pages/discussyiliaoziyuan/list'
import discussyiliaoziyuanDetail from '../pages/discussyiliaoziyuan/detail'
import discussyiliaoziyuanAdd from '../pages/discussyiliaoziyuan/add'
import discussjinqueziyuanList from '../pages/discussjinqueziyuan/list'
import discussjinqueziyuanDetail from '../pages/discussjinqueziyuan/detail'
import discussjinqueziyuanAdd from '../pages/discussjinqueziyuan/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'messages',
					component: Messages
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'huanzhe',
					component: huanzheList
				},
				{
					path: 'huanzheDetail',
					component: huanzheDetail
				},
				{
					path: 'huanzheAdd',
					component: huanzheAdd
				},
				{
					path: 'yisheng',
					component: yishengList
				},
				{
					path: 'yishengDetail',
					component: yishengDetail
				},
				{
					path: 'yishengAdd',
					component: yishengAdd
				},
				{
					path: 'zhenliaofangan',
					component: zhenliaofanganList
				},
				{
					path: 'zhenliaofanganDetail',
					component: zhenliaofanganDetail
				},
				{
					path: 'zhenliaofanganAdd',
					component: zhenliaofanganAdd
				},
				{
					path: 'ziyuanfenlei',
					component: ziyuanfenleiList
				},
				{
					path: 'ziyuanfenleiDetail',
					component: ziyuanfenleiDetail
				},
				{
					path: 'ziyuanfenleiAdd',
					component: ziyuanfenleiAdd
				},
				{
					path: 'yiliaoziyuan',
					component: yiliaoziyuanList
				},
				{
					path: 'yiliaoziyuanDetail',
					component: yiliaoziyuanDetail
				},
				{
					path: 'yiliaoziyuanAdd',
					component: yiliaoziyuanAdd
				},
				{
					path: 'ziyuanshenqing',
					component: ziyuanshenqingList
				},
				{
					path: 'ziyuanshenqingDetail',
					component: ziyuanshenqingDetail
				},
				{
					path: 'ziyuanshenqingAdd',
					component: ziyuanshenqingAdd
				},
				{
					path: 'yuyuequxiao',
					component: yuyuequxiaoList
				},
				{
					path: 'yuyuequxiaoDetail',
					component: yuyuequxiaoDetail
				},
				{
					path: 'yuyuequxiaoAdd',
					component: yuyuequxiaoAdd
				},
				{
					path: 'jinqueziyuan',
					component: jinqueziyuanList
				},
				{
					path: 'jinqueziyuanDetail',
					component: jinqueziyuanDetail
				},
				{
					path: 'jinqueziyuanAdd',
					component: jinqueziyuanAdd
				},
				{
					path: 'paiduishenqing',
					component: paiduishenqingList
				},
				{
					path: 'paiduishenqingDetail',
					component: paiduishenqingDetail
				},
				{
					path: 'paiduishenqingAdd',
					component: paiduishenqingAdd
				},
				{
					path: 'paiduijindu',
					component: paiduijinduList
				},
				{
					path: 'paiduijinduDetail',
					component: paiduijinduDetail
				},
				{
					path: 'paiduijinduAdd',
					component: paiduijinduAdd
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'discussyiliaoziyuan',
					component: discussyiliaoziyuanList
				},
				{
					path: 'discussyiliaoziyuanDetail',
					component: discussyiliaoziyuanDetail
				},
				{
					path: 'discussyiliaoziyuanAdd',
					component: discussyiliaoziyuanAdd
				},
				{
					path: 'discussjinqueziyuan',
					component: discussjinqueziyuanList
				},
				{
					path: 'discussjinqueziyuanDetail',
					component: discussjinqueziyuanDetail
				},
				{
					path: 'discussjinqueziyuanAdd',
					component: discussjinqueziyuanAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
