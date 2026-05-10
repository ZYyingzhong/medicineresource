export default {
	baseUrl: 'http://localhost:8080/medicine/',
	name: '/medicine',
	indexNav: [
		// yiliaoziyuan
		{
			name: '医疗资源',
			url: '/index/yiliaoziyuan',
		},
		// jinqueziyuan
		{
			name: '紧缺资源',
			url: '/index/jinqueziyuan',
		},
		// news
		{
			name: '公告资讯',
			url: '/index/news',
		},
		// {
		// 	name: '公告资讯',
		// 	url: '/index/news'
		// },
		{
			name: '反馈建议',
			url: '/index/messages'
		},
	],
	cateList: [
		{
			name: '医疗资源',
			refTable: 'ziyuanfenlei',
			refColumn: 'ziyuanfenlei',
		},
		{
			name: '紧缺资源',
			refTable: 'ziyuanfenlei',
			refColumn: 'ziyuanfenlei',
		},
	],
	payList: [
	],
}
