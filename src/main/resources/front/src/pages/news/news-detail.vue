<template>
	<div class="news-detail-box">
		<div class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="news-detail-view">
			<div class="title-box">
				<div>公告资讯</div>
			</div>
			<div class="news-detail">
				<div class="detail-title">{{detail.title}}</div>
				<div class="infoBox">
					<div class="time_item">
						<span class="icon iconfont icon-shijian21"></span>
						<span class="label">发布时间：</span>
						<span class="text">{{detail.addtime.split(' ')[0]}}</span>
					</div>
					<div class="publisher_item">
						<span class="icon iconfont icon-geren16"></span>
						<span class="label">发布人：</span>
						<span class="text">{{detail.name}}</span>
					</div>
					<div class="collect_item">
						<span class="icon iconfont icon-shoucang10"></span>
						<span class="label">收藏量：</span>
						<span class="text">{{detail.storeupnum}}</span>
					</div>
				</div>
				<div class="operate">
					<div class="collect-btn" @click="collectClick" >
						<span class="icon iconfont" :class="collectType?'icon-shoucang10':'icon-shoucang10'" ></span>
						<span class="text" >{{collectType?'取消收藏':'收藏'}}</span>
					</div>
				</div>
				<div style="display: flex;flex-wrap: wrap;">
					<div style="width: 40%;margin: 0 10px 0 0;"><img style="width: 100%" :src="baseUrl + (detail.picture?detail.picture.split(',')[0]:'')" ></div>
					<div class="content-detail ql-snow ql-editor" v-html="detail.content" style="flex: 1;"></div>
				</div>
			</div>
			<!-- option -->
			<div class="option-box">
				<div class="prev-btn" @click="prepDetailClick">
					<span class="text">上一篇：{{currentIndex==0?'没有了':allList[currentIndex - 1].title}}</span>
					<span class="icon iconfont icon-jiantou08"></span>
				</div>
				<div class="next-btn" @click="nextDetailClick">
					<span class="text">下一篇：{{currentIndex==allList.length - 1?'没有了':allList[currentIndex + 1].title}}</span>
					<span class="icon iconfont icon-jiantou09"></span>
				</div>
			</div>

			<!-- 联系我们 -->
			<div class="contactus">
				<div class="contactus-title">联系我们</div>
				<div class="contactus-list">
					<div class="phone_item">
						<div class="label">手机：</div>
						<div class="text">13823888888</div>
					</div>
					<div class="mobile_item">
						<div class="label">电话：</div>
						<div class="text">0753-1234567</div>
					</div>
					<div class="email_item">
						<div class="label">邮箱：</div>
						<div class="text">13823888888@qq.com</div>
					</div>
					<div class="addr_item">
						<div class="label">地址：</div>
						<div class="text">广东省xxx市</div>
					</div>
				</div>
				<span class="icon iconfont icon-xiugai6"></span>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		//数据集合
		data() {
			return {
				id: 0,
				detail: {},
				collectType:false,
				collectForm: {},
				baseUrl:'',
				currentIndex: 0,
				allList: [],
				storeupType: false,
			}
		},
		created() {
			if(this.$route.query.storeupType&&this.$route.query.storeupType!=0) {
				this.storeupType = true
			}
			this.id = this.$route.query.id
			this.baseUrl = this.$config.baseUrl;
			this.getDetail()
			this.getNewsList()
		},
		watch:{
			$route(newValue) {
				this.id = this.$route.query.id
			    this.getDetail()
			}
		},
		methods: {
			backClick() {
				if(this.storeupType){
					history.back()
				}else {
					this.$router.push({path: '/index/news'});
				}
			},
			getNewsList() {
				let params = {page:1, limit: 100,sort:'addtime',order:'desc'};
				this.$http.get('news/list', {params: params}).then(res => {
					if (res.data.code == 0) {
						for(let x in res.data.data.list){
							if(res.data.data.list[x].id == this.id){
								this.currentIndex = Number(x)
								break
							}
						}
						this.allList = res.data.data.list
					}
				});
			},
			// 上一篇
			prepDetailClick(){
				if(this.currentIndex == 0){
					this.$message.error('已经是第一篇了')
					return false
				}
				this.currentIndex--
				this.$router.push({path: '/index/newsDetail', query: {id: this.allList[this.currentIndex].id}});
			},
			// 下一篇
			nextDetailClick(){
				if(this.currentIndex == this.allList.length - 1){
					this.$message.error('已经是最后一篇了')
					return false
				}
				this.currentIndex++
				this.$router.push({path: '/index/newsDetail', query: {id: this.allList[this.currentIndex].id}});
			},
			// 跳转详情
			toDetail(id){
				this.$router.push({path: '/index/newsDetail', query: {id: id}});
			},
			getDetail(){
				this.$http.get(`news/detail/${this.id}`,{}).then(res=>{
					if(res.data&&res.data.code==0){
						this.detail = res.data.data
						window.scrollTo(0, 100)
						this.getCollect()
					}
				})
			},
			getCollect(){
				this.$http.get('storeup/list', {
					params: {
						page: 1,
						limit: 1,
						type: '1',
						userid: Number(localStorage.getItem('frontUserid')),
						tablename: 'news',
						refid: this.id
					}
				}).then(res=>{
					if(res.data&&res.data.code==0){
						if(res.data.data.list.length){
							this.collectType = true
							this.collectForm = res.data.data.list[0]
						}else{
							this.collectType = false
						}
					}
				})
			},
			collectClick(){
				if(this.collectType){
					this.$http.post('storeup/delete', [this.collectForm.id]).then(res => {
						if (res.data && res.data.code == 0) {
							this.$message.success('取消成功')
							this.detail.storeupnum--
							this.$http.post('news/update',this.detail).then(obj=>{})
							this.getCollect()
						}
					})
				}else{
					let data = {
						name: this.detail.title,
						picture: this.detail.picture?this.detail.picture.split(',')[0]:'',
						refid: this.detail.id,
						type: '1',
						tablename: 'news',
						userid: Number(localStorage.getItem('frontUserid')),
					}
					this.$http.post('storeup/add', data).then(res => {
						if (res.data && res.data.code == 0) {
							this.detail.storeupnum++
							this.$http.post('news/update',this.detail).then(obj=>{})
							this.$message.success('收藏成功')
							this.getCollect()
						}
					})
				}
			},
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	
	.news-detail-box {
				padding: 0 7%;
				margin: 10px auto;
				background: none;
				width: 100%;
				position: relative;
				.news-detail-view {
						width: 100%;
						.title-box {
								background: none;
								display: none;
								width: 100%;
								height: auto;
								div {
										padding: 10px;
										color: #333;
										background: rgba(255,255,255,.1);
										display: block;
										width: 100%;
										font-size: 32px;
										border-color: #76d5ee60;
										border-width: 4px 0;
										line-height: 1.5;
										border-style: solid;
										text-align: center;
									}
			}
			.news-detail {
								padding: 20px;
								background: #fff;
								width: 100%;
								position: relative;
								.detail-title {
										color: #333;
										font-weight: 500;
										font-size: 24px;
										line-height: 60px;
										text-align: left;
									}
				.infoBox {
										padding: 0 0 10px;
										color: #999;
										display: flex;
										width: 100%;
										font-size: 15px;
										border-color: #e6e6e6;
										border-width: 0 0 1px;
										justify-content: flex-start;
										align-items: center;
										border-style: solid;
										.time_item {
												padding: 0;
												margin: 0 20px 0 0;
												.icon {
														margin: 0 2px 0 0;
														line-height: 1.5;
													}
						.label {
														line-height: 1.5;
													}
						.text {
														line-height: 1.5;
													}
					}
					.publisher_item {
												padding: 0;
												margin: 0 20px 0 0;
												.icon {
														margin: 0 2px 0 0;
														line-height: 1.5;
													}
						.label {
														line-height: 1.5;
													}
						.text {
														line-height: 1.5;
													}
					}
					.collect_item {
												padding: 0;
												margin: 0 20px 0 0;
												.icon {
														margin: 0 2px 0 0;
														line-height: 1.5;
													}
						.label {
														line-height: 1.5;
													}
						.text {
														line-height: 1.5;
													}
					}
					.view_item {
												padding: 0;
												.icon {
														margin: 0 2px 0 0;
														line-height: 1.5;
													}
						.label {
														line-height: 1.5;
													}
						.text {
														line-height: 1.5;
													}
					}
				}
				.operate {
										padding: 10px 20px;
										display: flex;
										width: 100%;
										justify-content: flex-end;
										align-items: center;
										.collect-btn {
												cursor: pointer;
												padding: 0 15px;
												background: none;
												display: flex;
												justify-content: center;
												align-items: center;
												.icon {
														margin: 0 2px 0 0;
														color: #333;
														font-size: 16px;
														line-height: 40px;
													}
						.text {
														color: #333;
														font-size: 16px;
														line-height: 40px;
													}
					}
					.collect-btn:hover {
												border-radius: 4px;
												background: #009899;
												.icon {
														color: #fff;
													}
						.text {
														color: #fff;
													}
					}
				}
				.content-detail {
										padding: 0 0 10px;
										color: #666;
										width: 100%;
										font-size: 16px;
										line-height: 1.8;
										height: auto;
									}
			}
			.option-box {
								padding: 0;
								margin: 20px auto;
								display: flex;
								width: 100%;
								justify-content: space-between;
								height: auto;
								.prev-btn {
										cursor: pointer;
										padding: 0 10px;
										background: none;
										.text {
												color: #fff;
												font-size: 16px;
												line-height: 40px;
											}
					.icon {
												color: #fff;
												font-size: 16px;
												line-height: 40px;
											}
				}
				.prev-btn:hover {
										background: #007aff;
										.text {
												color: #fff;
											}
					.icon {
												color: #fff;
											}
				}
				.next-btn {
										cursor: pointer;
										padding: 0 10px;
										background: none;
										.text {
												color: #fff;
												font-size: 16px;
												line-height: 40px;
											}
					.icon {
												color: #fff;
												font-size: 16px;
												line-height: 40px;
											}
				}
				.next-btn:hover {
										background: #007aff;
										.text {
												color: #fff;
											}
					.icon {
												color: #fff;
											}
				}
			}
			.contactus {
								box-shadow: 0 1px 6px rgba(0,0,0,.1);
								margin: 0 0 0 -210px;
								top: 250px;
								left: 0;
								background: #fff;
								width: 200px;
								position: absolute;
								height: auto;
								.contactus-title {
										color: #fff;
										background: red;
										width: 100%;
										font-size: 14px;
										line-height: 44px;
										text-align: center;
									}
				.contactus-list {
										padding: 5px 5px 20px;
										width: 100%;
										height: auto;
										.phone_item {
												display: flex;
												width: 100%;
												height: auto;
												.label {
														color: #333;
														font-size: 14px;
														line-height: 30px;
													}
						.text {
														color: #333;
														flex: 1;
														font-size: 14px;
														line-height: 30px;
													}
					}
					.mobile_item {
												display: flex;
												width: 100%;
												height: auto;
												.label {
														color: #333;
														font-size: 14px;
														line-height: 30px;
													}
						.text {
														color: #333;
														flex: 1;
														font-size: 14px;
														line-height: 30px;
													}
					}
					.email_item {
												display: flex;
												width: 100%;
												height: auto;
												.label {
														color: #333;
														font-size: 14px;
														line-height: 30px;
													}
						.text {
														color: #333;
														flex: 1;
														font-size: 14px;
														line-height: 30px;
													}
					}
					.addr_item {
												display: flex;
												width: 100%;
												height: auto;
												.label {
														color: #333;
														font-size: 14px;
														line-height: 30px;
													}
						.text {
														color: #333;
														flex: 1;
														font-size: 14px;
														line-height: 30px;
													}
					}
				}
				.icon {
										border-radius: 100%;
										margin: 0 0 0 -15px;
										color: #fff;
										left: 50%;
										bottom: -15px;
										background: red;
										width: 32px;
										font-size: 14px;
										line-height: 32px;
										position: absolute;
										text-align: center;
									}
			}
		}
	}
</style>