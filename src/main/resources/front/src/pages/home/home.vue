<template>
	<div class="home-preview">




		<!-- 新闻资讯 -->
		<div id="animate_newsnews" class="news animate__animated">
			<div class="news_title_box">
				<span class="news_title">公告资讯</span>
				<span class="news_subhead">{{'news'.toUpperCase()}}</span>
			</div>
			<!-- 样式十 -->
			<div v-if="newsList.length" class="list list10 index-pv1">
				<div v-if="newsList.length>0" @click="toDetail('newsDetail', newsList[0])" class="list-item1 animation-box">
					<img :src="baseUrl + (newsList[0].picture?newsList[0].picture.split(',')[0]:'')" alt="">
					<div class="name line1">{{newsList[0].title}}</div>
					<div class="desc line2">{{newsList[0].introduction}}</div>
					<div class="time_item">
						<span class="icon iconfont icon-shijian21"></span>
						<span class="label">发布时间：</span>
						<span class="text">{{newsList[0].addtime}}</span>
					</div>
					<div class="publisher_item">
						<span class="icon iconfont icon-geren16"></span>
						<span class="label">发布人：</span>
						<span class="text">{{newsList[0].name}}</span>
					</div>
					<div class="collect_item">
						<span class="icon iconfont icon-shoucang10"></span>
						<span class="label">收藏量：</span>
						<span class="text">{{newsList[0].storeupnum}}</span>
					</div>
					<div class="new10-list-item-identification">新闻动态</div>
				</div>
				<div class="right_box" v-if="newsList.length>1">
					<div v-for="(item,index) in newsList" v-if="index<4 && index>0" :key="index" @click="toDetail('newsDetail', item)" class="list-item2 animation-box">
						<div class="name line1">{{ item.title }}</div>
						<div class="desc line2">{{ item.introduction }}</div>
						<div class="time_item">
							<span class="icon iconfont icon-shijian21"></span>
							<span class="label"></span>
							<span class="text">{{item.addtime.split(' ')[0]}}</span>
						</div>
						<div class="publisher_item">
							<span class="icon iconfont icon-geren16"></span>
							<span class="label">发布人：</span>
							<span class="text">{{item.name}}</span>
						</div>
						<div class="collect_item">
							<span class="icon iconfont icon-shoucang10"></span>
							<span class="label">收藏量：</span>
							<span class="text">{{item.storeupnum}}</span>
						</div>
						<div class="new10-list-item-identification">新闻动态</div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('news')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-jiantou09"></i>
			</div>
		</div>
		<!-- 新闻资讯 -->
		<!-- 商品推荐 -->
		<div id="animate_recommendyiliaoziyuan" class="recommend animate__animated">
			<div class="recommend_title_box">
				<span class="recommend_title">医疗资源推荐</span>
				<span class="recommend_subhead">{{'yiliaoziyuan'.toUpperCase()}} RECOMMEND</span>
			</div>
			<div v-if="yiliaoziyuanRecommend.length" class="list list12 index-pv1 list12yiliaoziyuan">
				<div class="list-body">
					<div class="item list-item animation-box" v-for="item,index in yiliaoziyuanRecommend" :key="index" @click="toDetail('yiliaoziyuanDetail', item)">
						<img :name="item.id" v-if="preHttp(item.ziyuantupian)&&preHttp2(item.ziyuantupian)" :src="item.ziyuantupian" alt="" />
						<img :name="item.id" v-else-if="preHttp(item.ziyuantupian)" :src="item.ziyuantupian.split(',')[0]" alt="" />
						<img :name="item.id" v-else :src="baseUrl + (item.ziyuantupian?item.ziyuantupian.split(',')[0]:'')" alt="" />
						<div class="name">{{item.ziyuanmingcheng}}</div>
						<div class="name">资源价格:{{item.ziyuanjiage}}</div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('yiliaoziyuan')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-jiantou09"></i>
			</div>
		</div>
		<!-- 商品推荐 -->
	</div>
</template>

<script>
import 'animate.css'
import Swiper from "swiper";

	export default {
		//数据集合
		data() {
			return {
				baseUrl: '',
				newsList: [],
				yiliaoziyuanRecommend: [],



				recommendIndex12yiliaoziyuan: 0,
				recommendList12yiliaoziyuan: [],
				recommendColumn12yiliaoziyuan: '',


			}
		},
		created() {
			this.baseUrl = this.$config.baseUrl;
			this.getNewsList();
			this.getList();
			this.yiliaoziyuanRecommendCate()
		},
		mounted() {
			window.addEventListener('scroll', this.handleScroll)
			setTimeout(()=>{
				this.handleScroll()
			},100)
			
			this.swiperChanges()
		},
		beforeDestroy() {
			window.removeEventListener('scroll', this.handleScroll)
		},
		computed: {
			username() {
				return localStorage.getItem('username')
			}
		},
		//方法集合
		methods: {
			swiperChanges() {
				setTimeout(()=>{
				},750)
			},
			yiliaoziyuanRecommendCate(){
				this.$http.get('ziyuanfenlei/list',{
					params: {
						limit: Number(6)
					}
				}).then(res => {
					if (res.data.code == 0) {
						this.recommendList12yiliaoziyuan = res.data.data.list
					}
				});
				this.recommendColumn12yiliaoziyuan = 'ziyuanfenlei'
			},
			recommendIndexClick12(index, name) {
				this['recommendIndex12' + name] = index
				this.getList()
				
				document.querySelectorAll('.recommend .list12' + name + ' .list .item').forEach(el => {
					el.classList.remove("active")
				})
				setTimeout(() => {
					document.querySelectorAll('.recommend .list12' + name + ' .list .item').forEach(el => {
						el.classList.add("active")
					})
				}, 1);
			},

			listIndexClick11(index, name) {
				this['listIndex11' + name] = index[this['listColumn11' + name]]
				this.getList()
			},

			handleScroll() {
				let arr = [
					{id:'about',css:'animate__'},
					{id:'system',css:'animate__'},
					{id:'animate_recommendyiliaoziyuan',css:'animate__'},
					{id:'animate_newsnews',css:'animate__'},
				]
			
				for (let i in arr) {
					let doc = document.getElementById(arr[i].id)
					if (doc) {
						let top = doc.offsetTop
						let win_top = window.innerHeight + window.pageYOffset
						// console.log(top,win_top)
						if (win_top > top && doc.classList.value.indexOf(arr[i].css) < 0) {
							// console.log(doc)
							doc.classList.add(arr[i].css)
						}
					}
				}
			},
			preHttp(str) {
				return str && str.substr(0,4)=='http';
			},
			preHttp2(str) {
				return str && str.split(',w').length>1;
			},
			getNewsList() {
				let data = {
					page: 1,
					limit: 3,
					sort: 'addtime',
					order: 'desc'
				}
				this.$http.get('news/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.newsList = res.data.data.list;
					
					}
				});
			},
			getList() {
				let autoSortUrl = "";
				let data = {}
				autoSortUrl = "yiliaoziyuan/autoSort";
				if(localStorage.getItem('frontToken')) {
					autoSortUrl = "yiliaoziyuan/autoSort2";
				}
				data = {
					page: 1,
					limit: 4,
				}
				if(this.recommendIndex12yiliaoziyuan != 0){
					data.ziyuanfenlei = this.recommendList12yiliaoziyuan[this.recommendIndex12yiliaoziyuan - 1].ziyuanfenlei
				}
				this.$http.get(autoSortUrl, {params: data}).then(res => {
					if (res.data.code == 0) {
						this.yiliaoziyuanRecommend = res.data.data.list;
					}
				});
			
			},
			toDetail(path, item) {
				this.$router.push({path: '/index/' + path, query: {id: item.id, storeupType: 1}});
			},
			moreBtn(path) {
				this.$router.push({path: '/index/' + path});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.home-preview {
		margin: 0 auto;
		display: flex;
		width: 100%;
		justify-content: space-between;
		flex-wrap: wrap;
		.news {
			padding: 50px 15% 50px;
			margin: 0 auto;
			background: #f6f6f6;
			width: 100%;
			order: 3;
			.news_title_box {
				margin: 10px auto 20px;
				background: none;
				width: 100%;
				line-height: 54px;
				text-align: center;
				.news_title {
					padding: 10px 10px 20px;
					color: #000;
					background: url(http://codegen.caihongy.cn/20250819/9ab2f069b44b4cf5aca0e1a964a3d2f1.png) no-repeat center bottom;
					font-weight: 500;
					display: block;
					width: 100%;
					font-size: 36px;
					line-height: 1.5;
					text-align: center;
				}
				.news_subhead {
					margin: 0 0 10px;
					z-index: 1;
					color: #666;
					top: -90px;
					font-weight: 700;
					display: block;
					width: 100%;
					font-size: 50px;
					line-height: 36px;
					position: relative;
					opacity: 0.1;
					text-align: center;
				}
			}
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, -4px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list10 {
				padding: 0;
				margin: 10px 0 0;
				background: none;
				display: flex;
				width: 100%;
				height: auto;
				.list-item1 {
					cursor: pointer;
					padding: 20px;
					margin: 0 20px 20px 0;
					display: flex;
					flex-wrap: wrap;
					border-radius: 10px;
					overflow: hidden;
					align-content: flex-start;
					background: #fff;
					width: 50%;
					align-items: center;
					position: relative;
					height: auto;
					img {
						border-radius: 10px;
						margin: 0 0 5px;
						object-fit: cover;
						display: block;
						width: 100%;
						height: 320px;
					}
					.name {
						cursor: pointer;
						padding: 0 10px;
						overflow: hidden;
						color: #333;
						white-space: nowrap;
						background: #fff;
						font-weight: 600;
						width: 100%;
						font-size: 20px;
						line-height: 50px;
						text-overflow: ellipsis;
					}
					.desc {
						cursor: pointer;
						padding: 0 10px;
						margin: 0 0 10px;
						overflow: hidden;
						color: #666;
						white-space: nowrap;
						background: #fff;
						font-weight: normal;
						width: 100%;
						font-size: 14px;
						line-height: 24px;
						text-overflow: ellipsis;
					}
					.time_item {
						padding: 0 10px;
						.icon {
							margin: 0 2px 0 0;
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
					}
					.publisher_item {
						padding: 0 10px;
						.icon {
							margin: 0 2px 0 0;
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
					}
					.like_item {
						padding: 0 10px;
						.icon {
							margin: 0 2px 0 0;
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
					}
					.collect_item {
						padding: 0 10px;
						.icon {
							margin: 0 2px 0 0;
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
					}
					.view_item {
						padding: 0 10px;
						.icon {
							margin: 0 2px 0 0;
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #666;
							font-size: 12px;
							line-height: 1.5;
						}
					}
					.new10-list-item-identification {
						padding: 0 10px;
						margin: 0 10px 10px;
						color: #999;
						background: #fff;
						display: none;
						font-size: 12px;
						line-height: 24px;
					}
				}
				.right_box {
					margin: 0;
					flex-direction: column;
					background: none;
					display: flex;
					width: calc(100% - 600px);
					justify-content: space-between;
					align-items: flex-start;
					position: relative;
					height: auto;
					.list-item2 {
						cursor: pointer;
						border-radius: 10px;
						margin:  0 0 20px 0;
						align-content: center;
						background: #fff;
						display: flex;
						width: 100%;
						min-height: 240px;
						align-items: center;
						position: relative;
						flex-wrap: wrap;
						height: calc(33.33% - 10px);
						.name {
							padding: 0 10px;
							overflow: hidden;
							color: #666;
							white-space: nowrap;
							font-weight: 600;
							width: 100%;
							font-size: 16px;
							line-height: 30px;
							text-overflow: ellipsis;
						}
						.desc {
							cursor: pointer;
							padding: 0 10px;
							margin: 0 0 10px;
							overflow: hidden;
							color: #666;
							background: #fff;
							font-weight: normal;
							width: 100%;
							font-size: 14px;
							line-height: 24px;
							height: 48px;
						}
						.time_item {
							padding: 2px 10px;
							width: 100%;
							order: -1;
							.icon {
								margin: 0 2px 0 0;
								color: #666;
								display: none;
								font-size: 12px;
								line-height: 1.5;
							}
							.label {
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
							.text {
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
						}
						.publisher_item {
							padding: 0 10px;
							.icon {
								margin: 0 2px 0 0;
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
							.label {
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
							.text {
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
						}
						.like_item {
							padding: 0 10px;
							.icon {
								margin: 0 2px 0 0;
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
							.label {
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
							.text {
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
						}
						.collect_item {
							padding: 0 10px;
							.icon {
								margin: 0 2px 0 0;
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
							.label {
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
							.text {
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
						}
						.view_item {
							padding: 0 10px;
							.icon {
								margin: 0 2px 0 0;
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
							.label {
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
							.text {
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
						}
						.new10-list-item-identification {
							padding: 0 10px;
							color: #999;
							background: #fff;
							display: none;
							font-size: 12px;
							line-height: 24px;
						}
					}
				}
			}
			.moreBtn {
				border: 1px solid rgb(167, 5, 0);
				cursor: pointer;
				border-radius: 60px;
				margin: 20px auto;
				background: none;
				display: none;
				width: 200px;
				line-height: 48px;
				text-align: center;
				.text {
					margin: 0 5px 0 0;
					color: rgb(167, 5, 0);
					font-size: 16px;
				}
				.icon {
					color: rgb(167, 5, 0);
					background: none;
					display: inline-block;
					width: 23px;
					font-size: 14px;
					height: 7px;
				}
			}
		}
		.recommend {
			padding: 50px 15% 40px;
			margin: 0 auto;
			background: #fff;
			width: 100%;
			order: 1;
			.recommend_title_box {
				margin: 10px auto 20px;
				background: none;
				width: 100%;
				line-height: 54px;
				text-align: center;
				.recommend_title {
					padding: 10px 10px 20px;
					color: #000;
					background: url(http://codegen.caihongy.cn/20250819/9ab2f069b44b4cf5aca0e1a964a3d2f1.png) no-repeat center bottom;
					font-weight: 500;
					display: block;
					width: 100%;
					font-size: 36px;
					line-height: 1.5;
					text-align: center;
				}
				.recommend_subhead {
					margin: 0 0 10px;
					z-index: 1;
					color: #666;
					top: -90px;
					font-weight: 700;
					display: block;
					width: 100%;
					font-size: 50px;
					line-height: 36px;
					position: relative;
					opacity: 0.1;
					text-align: center;
				}
			}
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				border-color: rgb(167, 5, 0);
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1.1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list12 {
				padding: 0;
				background: #fff;
				width: 100%;
				height: auto;
				.tab_view {
					margin: 0 0 20px;
					display: flex;
					width: 100%;
					justify-content: center;
					height: auto;
					.tab {
						cursor: pointer;
						border: 0;
						border-radius: 4px;
						padding: 0 20px;
						margin: 0 10px;
						color: #333;
						display: flex;
						width: auto;
						font-size: 18px;
						line-height: 44px;
						align-items: center;
						img {
							margin: 0 5px 0 0;
							object-fit: cover;
							display: none;
							width: 34px;
							height: 34px;
						}
						.text {
							color: inherit;
							font-size: 16px;
						}
					}
					.tab:hover {
						color: #fff;
						background: url(http://codegen.caihongy.cn/20250818/180ac20e3b0945eb806bc11b19babbac.png) 100% 100%/100% 100% no-repeat;
						font-size: 18px;
					}
					.tab.active {
						color: #fff;
						background: url(http://codegen.caihongy.cn/20250818/180ac20e3b0945eb806bc11b19babbac.png) 100% 100%/100% 100% no-repeat;
						font-size: 18px;
						border-color: #eee;
						border-width: 0 0 0px 0;
						border-style: solid;
					}
					.more_btn {
						cursor: pointer;
						padding: 5px 10px;
						margin: 0 10px;
						color: #fff;
						background: none;
						display: none;
						line-height: 44px;
						align-items: center;
						.text {
							color: inherit;
							font-size: 14px;
						}
						.icon {
							margin: 0 0 0 2px;
							color: inherit;
							font-size: 14px;
						}
					}
					.more_btn:hover {
						color: red;
					}
					.tab.active {
						animation-name: mymove;
					
						&:nth-of-type(1) {
							animation-duration: 1s;
						}
						&:nth-of-type(2) {
							animation-duration: 1.2s;
						}
						&:nth-of-type(3) {
							animation-duration: 1.4s;
						}
						&:nth-of-type(4) {
							animation-duration: 1.6s;
						}
						&:nth-of-type(5) {
							animation-duration: 1.8s;
						}
						&:nth-of-type(6) {
							animation-duration: 2s;
						}
					}
					
					@keyframes mymove {
						from {top: 320px;}
						to {top: 0;}
					}
				}
				.list-body {
					margin: 0 0 0 -10px;
					overflow: hidden;
					background: none;
					display: flex;
					width: calc(100% + 20px);
					flex-wrap: wrap;
					height: auto;
					.list-item {
						cursor: pointer;
						padding: 0 0 5px;
						margin: 0 10px 20px;
						background: #fff;
						width: calc(25% - 20px);
						border-color: #f6f6f6;
						border-width: 0 0 5px 0;
						position: relative;
						border-style: solid;
						height: auto;
						img {
							border-radius: 10px;
							object-fit: cover;
							display: block;
							width: 100%;
							height: 240px;
						}
						.name {
							padding: 0 10px;
							overflow: hidden;
							color: #333;
							white-space: nowrap;
							width: 100%;
							font-size: 16px;
							line-height: 32px;
							text-overflow: ellipsis;
						}
						.price {
							padding: 0 10px;
							color: #f00;
							font-size: 16px;
							line-height: 1.5;
						}
					}
					.list-item.active {
						animation-name: mymove;
					
						&:nth-of-type(1) {
							animation-duration: 1s;
						}
						&:nth-of-type(2) {
							animation-duration: 1.2s;
						}
						&:nth-of-type(3) {
							animation-duration: 1.4s;
						}
						&:nth-of-type(4) {
							animation-duration: 1.6s;
						}
						&:nth-of-type(5) {
							animation-duration: 1.8s;
						}
						&:nth-of-type(6) {
							animation-duration: 2s;
						}
					}
					
					@keyframes mymove {
						from {top: 320px;}
						to {top: 0;}
					}
				}
			}
			.moreBtn {
				border: 1px solid rgb(167, 5, 0);
				cursor: pointer;
				border-radius: 60px;
				margin: 40px auto 0;
				background: none;
				display: none;
				width: 200px;
				line-height: 48px;
				text-align: center;
				.text {
					margin: 0 5px 0 0;
					color: rgb(167, 5, 0);
					font-size: 16px;
				}
				.icon {
					color: rgb(167, 5, 0);
					display: inline-block;
					width: 23px;
					font-size: 14px;
					height: 7px;
				}
			}
		}
	}
</style>
