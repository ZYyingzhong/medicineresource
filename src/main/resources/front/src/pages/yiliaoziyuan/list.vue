<template>
	<div>
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="'→'">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index"><a>{{item.name}}</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div v-if="centerType " class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="list-preview">
			<div class="category-3">
				<div class="item" :class="swiperIndex == '-1' ? 'active' : ''" @click="getList(1, '全部')" :plain="isPlain">
					<div class="text">全部</div>
				</div>
				<div class="item" :class="swiperIndex == index ? 'active' : ''" v-for="(item, index) in fenlei" :key="index" @click="getList(1, item[feileiColumn], 'btn' + index)" :ref="'btn' + index" plain>
					<img v-if="item.image" :src="baseUrl + (item.image?item.image.split(',')[0]:'')">
					<div class="text">{{item[feileiColumn]}}</div>
				</div>
			</div>
			<el-form :inline="true" :model="formSearch" class="list-form-pv">
				<el-form-item class="list-item" >
					<div class="lable">资源名称：</div>
					<el-input v-model="formSearch.ziyuanmingcheng" placeholder="资源名称" @keydown.enter.native="getList(1, curFenlei)" clearable></el-input>
				</el-form-item>
				<el-form-item class="list-item" >
					<div class="lable">资源用途：</div>
					<el-input v-model="formSearch.ziyuanyongtu" placeholder="资源用途" @keydown.enter.native="getList(1, curFenlei)" clearable></el-input>
				</el-form-item>
				<div class="list-btn-box">
					<el-button class="list-search-btn" v-if=" true " type="primary" @click="getList(1, curFenlei)">
						<span class="icon iconfont icon-fangdajing07"></span>
						搜索
					</el-button>
					<el-button class="list-add-btn" v-if="btnAuth('yiliaoziyuan','新增')" type="primary" @click="add('/index/yiliaoziyuanAdd')">
						<span class="icon iconfont icon-tianjia7"></span>
						新增
					</el-button>
  
				</div>
			</el-form>
			<div class="sort_view">
				<el-button class="click-sort-btn" @click="sortClick('clicknum')">
					<span class="icon iconfont icon-liulan13" v-if="sortType!='clicknum'"></span>
					<span class="icon iconfont icon-jiantou35" v-else-if="sortType=='clicknum'&&sortOrder=='desc'"></span>
					<span class="icon iconfont icon-jiantou36" v-else-if="sortType=='clicknum'&&sortOrder=='asc'"></span>
					<span class="text">点击量</span>
				</el-button>
				<el-button class="collect-sort-btn" @click="sortClick('storeupnum')">
					<span class="icon iconfont icon-shoucang10" v-if="sortType!='storeupnum'"></span>
					<span class="icon iconfont icon-jiantou35" v-else-if="sortType=='storeupnum'&&sortOrder=='desc'"></span>
					<span class="icon iconfont icon-jiantou36" v-else-if="sortType=='storeupnum'&&sortOrder=='asc'"></span>
					<span class="text">收藏数</span>
				</el-button>
			</div>
			<div class="list">
				<div class="list5">
					<div v-for="(item,index) in dataList" :key="index" class="list-item" @click.stop="toDetail(item)" >
						<div class="imgbox">
							<img @click.stop="imgPreView(item.ziyuantupian)" v-if="item.ziyuantupian && item.ziyuantupian.substr(0,4)=='http'&&item.ziyuantupian.split(',w').length>1" :src="item.ziyuantupian" class="image" />
							<img @click.stop="imgPreView(item.ziyuantupian.split(',')[0])" v-else-if="item.ziyuantupian && item.ziyuantupian.substr(0,4)=='http'" :src="item.ziyuantupian.split(',')[0]" class="image" />
							<img @click.stop="imgPreView(baseUrl + (item.ziyuantupian?item.ziyuantupian.split(',')[0]:''))" v-else :src="baseUrl + (item.ziyuantupian?item.ziyuantupian.split(',')[0]:'')" class="image" />
						</div>
						<div class="infoBox">
							<div class="name">{{item.ziyuanmingcheng}}</div>
							<div class="name">资源价格:{{item.ziyuanjiage}}</div>
							<div class="bottomInfo">
								<div class="time_item">
									<span class="icon iconfont icon-shijian21"></span>
									<span class="label">发布时间：</span>
									<span class="text">{{item.addtime.split(' ')[0]}}</span>
								</div>
								<div class="collect_item">
									<span class="icon iconfont icon-shoucang10"></span>
									<span class="label">收藏量：</span>
									<span class="text">{{item.storeupnum}}</span>
								</div>
								<div class="view_item">
									<span class="icon iconfont icon-chakan2"></span>
									<span class="label">点击量：</span>
									<span class="text">{{item.clicknum}}</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

	
			<el-pagination
				background
				id="pagination"
				class="pagination"
				:pager-count="7"
				:page-size="pageSize"
				prev-text="<"
				next-text=">"
				:hide-on-single-page="true"
				:layout='["total","prev","pager","next","sizes","jumper"].join()'
				:total="total"
				:page-sizes="pageSizes"
				@current-change="curChange"
				@size-change="sizeChange"
				@prev-click="prevClick"
				@next-click="nextClick"
				></el-pagination>
		</div>
		<el-dialog title="预览图" :visible.sync="previewVisible" width="50%">
			<img :src="previewImg" alt="" style="width: 100%;">
		</el-dialog>
	</div>
</template>
<script>
	import axios from 'axios';
	export default {
		//数据集合
		data() {
			return {
				layouts: '',
				swiperIndex: -1,
				baseUrl: '',
				breadcrumbItem: [
					{
						name: '医疗资源'
					}
				],
				formSearch: {
					ziyuanmingcheng: '',
					ziyuanyongtu: '',
				},
				fenlei: [],
				feileiColumn: '',
				dataList: [],
				total: 1,
				pageSize: 9,
				pageSizes: [],
				totalPage: 1,
				curFenlei: '全部',
				isPlain: false,
				indexQueryCondition: '',
				timeRange: [],
				centerType:false,
				previewImg: '',
				previewVisible: false,
				sortType: 'id',
				sortOrder: 'desc',
			}
		},
		async created() {
			if(this.$route.query.centerType&&this.$route.query.centerType!=0){
				this.centerType = true
			}
			this.baseUrl = this.$config.baseUrl;
			if(this.centerType) {
				let urlziyuanshuliang = 'yiliaoziyuan/remind/ziyuanshuliang/1';
				urlziyuanshuliang+=`?remindend=3`,


				this.$http.get(urlziyuanshuliang).then(({ data }) => {
					if (data && data.code == 0 && data.count>0 ) {
						const h = this.$createElement;
						var name = data.data.join(',')
						this.ziyuanshuliangRemind = data.data
						this.$msgbox({
							message: h('p', null, [
								h('div', { style: 'text-align: center;font-weight: bold;font-size: 20px;padding: 20px 0' }, `${data.count}条资源数量预警`),
								h('p', null, [
									h('div', { style: 'text-align: center;font-weight: bold;padding: 0 0 10px;font-size: 16px' }, `资料紧缺，请及时补仓！`),
									h('div', { style: 'text-align: center' }, [
										h('span', null, `包含：`),
										h('span', { style: 'color: teal;font-weight: bold' }, `${name}`)
									])
								])
							]),
							showCancelButton: false,
						})
					}
				});
			}
			await this.getFenlei();
			let fenlei = '全部'
			if(this.$route.query.homeFenlei){
				fenlei = this.$route.query.homeFenlei
			}
			this.getList(1, fenlei);
		},
		watch:{
			$route(newValue){
				this.getList(1, newValue.query.homeFenlei);
			}
		},
		computed: {
			role(){
				return localStorage.getItem('frontRole');
			},
			username() {
				return localStorage.getItem('username')
			}
		},
		//方法集合
		methods: {
			queryChange(arr){
				for(let x in arr) {
					if(arr[x] == this.role) {
						return true
					}
				}
				return false
			},
			add(path) {
				let query = {}
				if(this.centerType){
					query.centerType = 1
				}
				this.$router.push({path: path,query:query});
			},
			async getFenlei() {
				await this.$http.get('ziyuanfenlei/list',{params: {sort: 'ziyuanfenlei',order: 'desc'}}).then(res => {
					if (res.data.code == 0) {
						this.fenlei = res.data.data.list
					}
				});
				this.feileiColumn = 'ziyuanfenlei'
			},
			getList(page, fenlei, ref = '') {
				if(fenlei == '全部') this.swiperIndex = -1;
				for(let i=0;i<this.fenlei.length;i++) {
					if(fenlei == this.fenlei[i][this.feileiColumn]) {
						this.swiperIndex = i;
						break;
					}
				}
				if(fenlei){
					this.curFenlei = fenlei;
				}
				let params = {
					page,
					limit: this.pageSize,
				};
				let searchWhere = {};
				if (this.formSearch.ziyuanmingcheng != ''&&this.formSearch.ziyuanmingcheng != undefined) searchWhere.ziyuanmingcheng = '%' + this.formSearch.ziyuanmingcheng + '%';
				if (this.formSearch.ziyuanyongtu != ''&&this.formSearch.ziyuanyongtu != undefined) searchWhere.ziyuanyongtu = '%' + this.formSearch.ziyuanyongtu + '%';
				if (this.curFenlei != '全部') searchWhere.ziyuanfenlei = this.curFenlei;
				let user = JSON.parse(localStorage.getItem('sessionForm'))
				if (this.sortType) searchWhere.sort = this.sortType
				if (this.sortOrder) searchWhere.order = this.sortOrder
				this.$http.get(`yiliaoziyuan/${this.centerType?'page':'list'}`, {params: Object.assign(params, searchWhere)}).then(async res => {
					if (res.data.code == 0) {
						this.dataList = res.data.data.list;
						this.total = Number(res.data.data.total);
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(this.pageSizes.length==0){
							this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
						}
					}
				});
			},
			sortClick(type){
				if(this.sortType==type){
					if(this.sortOrder == 'desc'){
						this.sortOrder = 'asc'
					}else{
						this.sortOrder = ''
						this.sortType = ''
					}
				}else{
					this.sortType = type
					this.sortOrder = 'desc'
				}
				this.getList(1, '全部')
			},
			curChange(page) {
				this.getList(page,this.curFenlei);
			},
			prevClick(page) {
				this.getList(page,this.curFenlei);
			},
			sizeChange(size){
				this.pageSize = size
				this.getList(1,this.curFenlei);
			},
			nextClick(page) {
				this.getList(page,this.curFenlei);
			},
			imgPreView(url){
				this.previewImg = url
				this.previewVisible = true
			},
			toDetail(item) {
				let params = {
					id: item.id
				}
				if(this.centerType){
					params.centerType = 1
				}
				this.$router.push({path: '/index/yiliaoziyuanDetail', query: params});
			},
			btnAuth(tableName,key){
				if(this.centerType){
					return this.isBackAuth(tableName,key)
				}else{
					return this.isAuth(tableName,key)
				}
			},
			backClick() {
				this.$router.push({path: '/index/center'});
			},

			discussClick (row){
				let params = {
					refid: row.id
				}
				if(this.centerType){
					params.centerType = 1
				}
				this.$router.push({path: '/index/discussyiliaoziyuan', query: params});
			},
			chapterClick (row){
				let params = {
					refid: row.id
				}
				if(this.centerType){
					params.centerType = 1
				}
				this.$router.push({path: '/index/chapteryiliaoziyuan', query: params});
			},
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.list-preview {
		padding: 0 10%;
		margin: 0px auto;
		align-content: flex-start;
		background: none;
		display: flex;
		width: 100%;
		align-items: flex-start;
		position: relative;
		flex-wrap: wrap;
		.category-3 {
			padding: 10px;
			background: #fff;
			display: flex;
			width: 100%;
			height: auto;
			order: 2;
			.item {
				cursor: pointer;
				border-radius: 4px;
				padding: 5px 10px;
				margin: 0 10px 0 0;
				color: #333;
				background: #efefef;
				display: flex;
				align-items: center;
				img {
					margin: 0 5px 0 0;
					object-fit: cover;
					display: block;
					width: 34px;
					height: 34px;
				}
				.text {
					color: inherit;
					font-size: 14px;
				}
			}
			.item:hover {
				color: #fff;
				background: rgb(167, 5, 0);
			}
			.item.active {
				color: #fff;
				background: rgb(167, 5, 0);
			}
		}
		.list-form-pv {
			padding: 0;
			margin: 30px auto;
			background: none;
			display: flex;
			gap: 10px;
			width: 100%;
			justify-content: center;
			align-items: center;
			flex-wrap: wrap;
			height: auto;
			.list-item {
				margin: 0 5px 0 0;
				::v-deep.el-form-item__content {
					display: flex;
				}
				.lable {
					padding: 0;
					color: #333;
					white-space: nowrap;
					display: inline-block;
					width: auto;
					line-height: 42px;
				}
				.el-input {
					width: 100%;
				}
				.datetimerange {
					border: 1px solid #ddd;
					border-radius: 2px;
					padding: 3px 0px;
					outline: none;
					background: #fff;
					width: auto;
					justify-content: center;
				}
				.el-input ::v-deep .el-input__inner {
					border: 1px solid #ddd;
					border-radius: 2px;
					padding: 0 10px;
					margin: 0;
					outline: none;
					color: #333;
					width: 140px;
					font-size: 14px;
					line-height: 42px;
					height: 42px;
				}
				.el-select {
					width: 100%;
				}
				.el-select ::v-deep .el-input__inner {
					border: 1px solid #ddd;
					border-radius: 2px;
					padding: 0 10px;
					margin: 0;
					outline: none;
					color: #333;
					width: 140px;
					font-size: 14px;
					line-height: 42px;
					height: 42px;
				}
				.el-date-editor {
					width: 100%;
				}
				.el-date-editor ::v-deep .el-input__inner {
					border: 1px solid #ddd;
					border-radius: 2px;
					padding: 0 30px;
					margin: 0;
					outline: none;
					color: #333;
					width: 140px;
					font-size: 14px;
					line-height: 42px;
					height: 42px;
				}
			}
			.list-btn-box {
				display: flex;
			}
			.list-search-btn {
				cursor: pointer;
				border: 0;
				border-radius: 4px;
				padding: 0px 10px;
				margin: 0 10px 0 0;
				outline: none;
				color: #fff;
				background: rgb(167, 5, 0);
				width: auto;
				font-size: 14px;
				line-height: 42px;
				height: 42px;
				.icon {
					margin: 0 3px 0 0;
					color: #fff;
					font-size: 14px;
				}
			}
			.list-add-btn {
				cursor: pointer;
				border: 0;
				border-radius: 4px;
				padding: 0px 10px;
				margin: 0 10px 0 0;
				outline: none;
				color: #fff;
				background: rgb(167, 5, 0);
				width: auto;
				font-size: 14px;
				line-height: 42px;
				height: 42px;
				.icon {
					margin: 0 3px 0 0;
					color: #fff;
					font-size: 14px;
				}
			}
		}
		.sort_view {
			border: 1px solid #ddd;
			padding: 10px;
			margin: 10px auto;
			color: #333;
			background: rgba(200,200,200,.1);
			width: 100%;
			font-size: 16px;
			text-align: center;
			order: 2;
			.click-sort-btn {
				border: 0;
				border-radius: 8px;
				padding: 0 10px;
				margin: 0 5px;
				color: inherit;
				background: none;
				font-size: inherit;
				.icon {
					margin: 0 2px 0 0;
					line-height: 40px;
				}
				.text {
					line-height: 40px;
				}
			}
			.collect-sort-btn {
				border: 0;
				border-radius: 8px;
				padding: 0 10px;
				margin: 0 5px;
				color: inherit;
				background: none;
				font-size: inherit;
				.icon {
					margin: 0 2px 0 0;
					line-height: 40px;
				}
				.text {
					line-height: 40px;
				}
			}
		}
		.list {
			margin: 20px 0 0;
			background: none;
			flex: 1;
			width: calc(100% - 350px);
			order: 4;
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
				
			.index-pv1 .animation-box:hover {
				transform: rotate(0) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
				
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list5 {
				margin: 0px auto 20px -10px;
				display: flex;
				width: calc(100% + 20px);
				flex-wrap: wrap;
				.list-item {
					border: 1px solid #e8e8e8;
					border-radius: 0px;
					padding: 0px;
					margin: 0 10px 20px;
					overflow: hidden;
					background: #fff;
					display: block;
					width: calc(33.33% - 20px);
					position: relative;
					.imgbox {
						overflow: hidden;
						width: auto;
						.image {
							filter: saturate(150%);
							transform: rotate(0deg);
							margin: 0 0 5px;
							object-fit: contain;
							display: block;
							width: 100%;
							opacity: 0.9;
							height: 480px;
						}
					}
					.infoBox {
						padding: 20px;
						left: 0px;
						bottom: 0;
						background: none;
						width: 100%;
						font-size: 14px;
						position: inherit;
						transition: all 0.5s;
						.name {
							overflow: hidden;
							color: #333;
							white-space: nowrap;
							width: 100%;
							font-size: 16px;
							text-overflow: ellipsis;
						}
						.price {
							font-size: 14px;
							.price_text {
								color: #f00;
								font-size: 18px;
							}
						}
						.bottomInfo {
							display: flex;
							flex-wrap: wrap;
							.time_item {
								padding: 0;
								margin: 0 10px 0 0;
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
								padding: 0;
								margin: 0 10px 0 0;
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
								padding: 0;
								margin: 0 10px 0 0;
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
								padding: 0;
								margin: 0 10px 0 0;
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
								padding: 0;
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
						}
					}
				}
				.list-item:hover {
					cursor: pointer;
					border: 4px solid rgb(167, 5, 0);
					background: none;
					.imgbox {
						.image {
							transform: scale(1.05);
							ilter: saturate(100%);
							opacity: 1;
							transition: all 200ms linear;
						}
					}
					.infoBox {
						bottom: 0px;
						background: none;
						font-size: 14px;
						.name {
						}
						.price {
							.price_text {
							}
						}
						.bottomInfo {
							display: flex;
							flex-wrap: wrap;
							.time_item {
								.icon {
									color: #666;
								}
								.label {
									color: #666;
								}
								.text {
									color: #666;
								}
							}
							.publisher_item {
								.icon {
									color: #666;
								}
								.label {
									color: #666;
								}
								.text {
									color: #666;
								}
							}
							.like_item {
								.icon {
									color: #666;
								}
								.label {
									color: #666;
								}
								.text {
									color: #666;
								}
							}
							.collect_item {
								.icon {
									color: #666;
								}
								.label {
									color: #666;
								}
								.text {
									color: #666;
								}
							}
							.view_item {
								.icon {
									color: #666;
								}
								.label {
									color: #666;
								}
								.text {
									color: #666;
								}
							}
						}
					}
				}
			}
		}
	}
</style>
