<template>
	<div class="home-content" :style="{'backgroundImage': indexBgUrl?`url(${$base.url + indexBgUrl})`:''}">
		<!-- title -->
		<div id="home-title" class="home-title animate__animated">
			<div class="titles" >
				<span>欢迎使用</span>
				{{this.$project.projectName}}
			</div>
		</div>
		<!-- statis -->
		<div class="statis-box">
			<div id="statis1" class="statis1 animate__animated" v-if="isAuth('huanzhe','首页总数')">
				<div class="left">
					<span class="icon iconfont icon-shijian16"></span>
				</div>
				<div class="right">
					<div class="num">{{huanzheCount}}</div>
					<div class="name">患者总数</div>
				</div>
			</div>
			<div id="statis2" class="statis2 animate__animated" v-if="isAuth('yisheng','首页总数')">
				<div class="left">
					<span class="icon iconfont icon-liulan12"></span>
				</div>
				<div class="right">
					<div class="num">{{yishengCount}}</div>
					<div class="name">医生总数</div>
				</div>
			</div>
		</div>
		<!-- statis -->
		<div id="news-box" class="news-box animate__animated">
			<div class="news-title">
				公告资讯
			</div>
			<div class="news-list">
				<div class="news-item" v-for="(item,index) in newsList" :index="index" @click="newsDetailClick(item)">
					<div class="news-text">
						{{item.title}}
					</div>
					<div class="news-img-box">
						<img :src="item.picture?($base.url + item.picture.split(',')[0]):''" alt="">
					</div>
					<div class="news-desc">
						{{item.introduction}}
					</div>
					<div class="news-time">
						{{item.addtime}}
					</div>
				</div>
			</div>
		</div>
		<el-dialog :visible.sync="newsVisible" title="公告资讯" :append-to-body="true" width="60%">
			<div style="width: 100%;text-align: center;font-size: 20px;color: #000;padding: 0 0 20px;font-weight: bold;">{{newsDetail.title}}</div>
			<div style="width: 100%;">
				<div class="ql-snow ql-editor" v-html="newsDetail.content"></div>
			</div>
		</el-dialog>
		<!-- 日历 -->
		<div id="calendar" class="calendar animate__animated">
			<div class="option">
				<div class="pyear" @click="pyear"><span class="icon iconfont icon-fanhui"></span>上一年</div>
				<div class="pmonth" @click="pmonth"><span class="icon iconfont icon-fanhui"></span>上一月</div>
				<div class="date-box">
					<div class="year">{{this.year}}年</div>
					<div class="month">{{this.month}}月</div>
				</div>
				<div class="nmonth" @click="nmonth">下一月<span class="icon iconfont icon-jinru"></span></div>
				<div class="nyear" @click="nyear">下一年<span class="icon iconfont icon-jinru"></span></div>
			</div>
			<table v-if="this.weeks.length">
				<thead>
					<tr>
						<th v-for="item,index in this.heads" :key="index">{{item}}</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="week,index in this.weeks" :key="index">
						<td v-for="day,key in week.days" :key="key" :class="{other: day.isOther, festival: day.isFestival, today: day.isToday, rest: day.isRest}">
							<div class="text">
								<div class="new">{{day.day}}</div>
								<div class="old">{{day.text}}</div>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	
		<!-- echarts -->
		<!-- echarts -->
	</div>
</template>
<script>
import 'animate.css'
//0
import router from '@/router/router-static'
import * as echarts from 'echarts'
export default {
	data() {
		return {
			huanzheCount: 0,
			yishengCount: 0,
			// 日历
			year: 2023,
			month: 6,
			heads: ['一', '二', '三', '四', '五', '六', '日'],
			weeks: [],
			now: Solar.fromDate(new Date()),
			newsList: [],
			newsDetail: {},
			newsVisible: false,
			indexBgUrl: '',
			indexLogoUrl: '',
		};
	},
	mounted(){
		this.init();
		this.gethuanzheCount();
		this.getyishengCount();
		window.addEventListener('scroll', this.handleScroll)
		setTimeout(()=>{
			this.handleScroll()
		},100)
	},
	created() {
		this.$http.get('config/info?name=bIndexBackgroundImg',).then(rs=>{this.indexBgUrl = rs.data.data?rs.data.data.value:''})
		this.$http.get('config/info?name=bHomeLogo',).then(rs=>{this.indexLogoUrl = rs.data.data?rs.data.data.value:''})
	},
	computed: {
		sessionForm() {
			return JSON.parse(this.$storage.getObj('userForm'))
		},
		avatar(){
			return this.$storage.get('headportrait')?this.$storage.get('headportrait'):''
		},
	},
	methods:{
		handleScroll() {
			let arr = [
				{id:'home-title',css:'animate__'},
				{id:'statis1',css:'animate__'},
				{id:'statis2',css:'animate__'},
				{id:'news-box',css:'animate__'},
				{id:'calendar',css:'animate__'},
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
		init(){
			if(this.$storage.get('Token')){
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code != 0) {
						router.push({ name: 'login' })
					}
				});
			}else{
				router.push({ name: 'login' })
			}
			this.$nextTick(() => {
				// --- 日历 ---
				this.year = this.now.getYear()
				this.month = this.now.getMonth()
				this.weeks = this.render(this.year, this.month);
			})
			this.getNewsList()
		},
		getNewsList(){
			let params = {
				page: 1,
				limit: 6,
				sort: 'addtime',
				order: 'desc',
			}
			this.$http({
				url: `news/list`,
				method: "get",
				params: params
			}).then(res => {
				if(res.data&&res.data.code==0){
					this.newsList = res.data.data.list
				}
			});
		},
		newsDetailClick(row){
			this.newsDetail = row
			this.newsVisible = true
		},
		// 日历
		nyear() {
			this.year = this.year + 1
			this.weeks = this.render(this.year, this.month)
		},
		pyear() {
			this.year = this.year - 1
			this.weeks = this.render(this.year, this.month)
		},
		nmonth() {
			this.month = this.month + 1
			if (this.month > 12) {
				this.month = 1
				this.year = this.year + 1
			}
			this.weeks = this.render(this.year, this.month)
		},
		pmonth() {
			this.month = this.month - 1
			if (this.month < 1) {
				this.month = 12
				this.year = this.year - 1
			}
			this.weeks = this.render(this.year, this.month)
		},
		// 日历
		render(year, month, weekStart = 1) {
			let months = SolarMonth.fromYm(year, month)
			let weeks = []
		
			months.getWeeks(weekStart).forEach(w => {
				let week = {
					index: 0,
					days: []
				}
				week.index = w.getIndexInYear()
				let days = []
				w.getDays().forEach(d => {
					days.push(this.buildDay(d, month))
				})
				week.days = days
				weeks.push(week)
			})
			return weeks
		},
		buildDay(d, month) {
			let lunar = d.getLunar()
			let day = {
				day: 0,
				text: '',
				isFestival: false,
				isToday: false,
				isOther: false,
				isHoliday: false,
				isRest: false
			}
			day.day = d.getDay()
			let text = lunar.getDayInChinese()
			if (1 === d.getDay()) {
				text = lunar.getMonthInChinese() + '月'
			}
			// let otherFestivals = d.getOtherFestivals()
			// if (otherFestivals.length > 0) {
			// 	text = otherFestivals[0]
			// 	day.isFestival = true
			// }
			// otherFestivals = lunar.getOtherFestivals()
			// if (otherFestivals.length > 0) {
			// 	text = otherFestivals[0]
			// 	day.isFestival = true
			// }
			let festivals = d.getFestivals()
			if (festivals.length > 0) {
				text = festivals[0]
				day.isFestival = true
			}
			festivals = lunar.getFestivals()
			if (festivals.length > 0) {
				text = festivals[0]
				day.isFestival = true
			}
			let jq = lunar.getJieQi()
			if (jq) {
				text = jq
				day.isFestival = true
			}
			day.text = text
			if (d.toYmd() === this.now.toYmd()) {
				day.isToday = true
			}
			if (d.getMonth() !== month) {
				day.isOther = true
			}
			let h = HolidayUtil.getHoliday(d.getYear(), d.getMonth(), d.getDay())
			if (h) {
				day.isHoliday = true
				day.isRest = !h.isWork()
			}
			return day
		},
		// 日历
		gethuanzheCount() {
			this.$http({
				url: `huanzhe/count`,
				method: "get"
			}).then(({
				data
			}) => {
				if (data && data.code == 0) {
					this.huanzheCount = data.data
				}
			})
		},
		getyishengCount() {
			this.$http({
				url: `yisheng/count`,
				method: "get"
			}).then(({
				data
			}) => {
				if (data && data.code == 0) {
					this.yishengCount = data.data
				}
			})
		},
	}
};
</script>
<style lang="scss" scoped>
	.home-content {
		padding: 0 30px 30px;
		background: url(http://codegen.caihongy.cn/20250811/2c2e34a20094462bb849b93bd6bf336e.jpg) no-repeat center top / cover;
		display: flex;
		width: 100%;
		min-height: 100vh;
		flex-wrap: wrap;
		height: auto;
		.home-title {
			padding: 10px 0 0;
			box-shadow: none;
			margin: 10px 0 0;
			display: flex;
			width: 100%;
			justify-content: center;
			align-items: center;
			transition: 0.3s;
			.titles {
				padding: 0;
				color: #333;
				font-size: 30px;
				line-height: 60px;
				span {
				}
			}
		}
		.home-title:hover {
			transform: translate3d(0, 0px, 0);
		}
		.statis-box {
			margin: 20px 0;
			background: none;
			display: flex;
			width: 100%;
			justify-content: center;
			align-items: center;
			flex-wrap: wrap;
			.statis1 {
				border: 1px solid #e7e9ec;
				border-radius: 10px;
				box-shadow: 0 3px 6px rgba(0,0,0,.06);
				padding: 20px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					background: none;
					display: none;
					width: 40px;
					justify-content: center;
					align-items: center;
					height: 40px;
					order: 2;
					.iconfont {
						border-radius: 100%;
						padding: 0;
						color: #3ec6b7;
						background: #edfffd;
						font-weight: 500;
						width: 48px;
						font-size: 32px;
						line-height: 48px;
						text-align: center;
						height: 48px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					.num {
						margin: 5px 0;
						color: #fbd140;
						font-weight: 600;
						font-size: 30px;
						line-height: 24px;
						height: 24px;
						order: 2;
					}
					.name {
						margin: 5px 0;
						color: #816f77;
						font-size: 16px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis1:hover {
				box-shadow: 0 3px 9px rgba(0,0,0,.1);
				transform: translate3d(0, -6px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.statis2 {
				border: 1px solid #e7e9ec;
				border-radius: 10px;
				box-shadow: 0 3px 6px rgba(0,0,0,.06);
				padding: 20px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					background: none;
					display: none;
					width: 48px;
					justify-content: center;
					align-items: center;
					height: 48px;
					order: 2;
					.iconfont {
						border-radius: 100%;
						padding: 0;
						color: #3ec6b7;
						background: #edfffd;
						font-weight: 500;
						width: 48px;
						font-size: 32px;
						line-height: 48px;
						text-align: center;
						height: 48px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					.num {
						margin: 5px 0;
						color: #fbd140;
						font-weight: bold;
						font-size: 30px;
						line-height: 24px;
						height: 24px;
						order: 2;
					}
					.name {
						margin: 5px 0;
						color: #816f77;
						font-size: 16px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis2:hover {
				box-shadow: 0 3px 9px rgba(0,0,0,.1);
				transform: translate3d(0, -6px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.statis3 {
				border: 1px solid #e7e9ec;
				border-radius: 10px;
				box-shadow: 0 3px 6px rgba(0,0,0,.06);
				padding: 20px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					background: none;
					display: none;
					width: 48px;
					justify-content: center;
					align-items: center;
					height: 48px;
					order: 2;
					.iconfont {
						border-radius: 100%;
						padding: 0;
						color: #3ec6b7;
						background: #edfffd;
						font-weight: 500;
						width: 48px;
						font-size: 32px;
						line-height: 48px;
						text-align: center;
						height: 48px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					.num {
						margin: 5px 0;
						color: #fbd140;
						font-weight: bold;
						font-size: 30px;
						line-height: 24px;
						height: 24px;
						order: 2;
					}
					.name {
						margin: 5px 0;
						color: #816f77;
						font-size: 16px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis3:hover {
				box-shadow: 0 3px 9px rgba(0,0,0,.1);
				transform: translate3d(0, -6px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.statis4 {
				border: 1px solid #e7e9ec;
				border-radius: 10px;
				box-shadow: 0 3px 6px rgba(0,0,0,.06);
				padding: 20px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					background: none;
					display: none;
					width: 48px;
					justify-content: center;
					align-items: center;
					height: 48px;
					order: 2;
					.iconfont {
						border-radius: 100%;
						padding: 0;
						color: #3ec6b7;
						background: #edfffd;
						font-weight: 500;
						width: 48px;
						font-size: 32px;
						line-height: 48px;
						text-align: center;
						height: 48px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					.num {
						margin: 5px 0;
						color: #fbd140;
						font-weight: bold;
						font-size: 30px;
						line-height: 24px;
						height: 24px;
						order: 2;
					}
					.name {
						margin: 5px 0;
						color: #816f77;
						font-size: 16px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis4:hover {
				box-shadow: 0 3px 9px rgba(0,0,0,.1);
				transform: translate3d(0, -6px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.statis5 {
				border: 1px solid #e7e9ec;
				border-radius: 10px;
				box-shadow: 0 3px 6px rgba(0,0,0,.06);
				padding: 20px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					background: none;
					display: none;
					width: 48px;
					justify-content: center;
					align-items: center;
					height: 48px;
					order: 2;
					.iconfont {
						border-radius: 100%;
						padding: 0;
						color: #3ec6b7;
						background: #edfffd;
						font-weight: 500;
						width: 48px;
						font-size: 32px;
						line-height: 48px;
						text-align: center;
						height: 48px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					.num {
						margin: 5px 0;
						color: #fbd140;
						font-weight: bold;
						font-size: 30px;
						line-height: 24px;
						height: 24px;
						order: 2;
					}
					.name {
						margin: 5px 0;
						color: #816f77;
						font-size: 16px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis5:hover {
				box-shadow: 0 3px 9px rgba(0,0,0,.1);
				transform: translate3d(0, -6px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.statis6 {
				border: 1px solid #e7e9ec;
				border-radius: 10px;
				box-shadow: 0 3px 6px rgba(0,0,0,.06);
				padding: 20px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					background: none;
					display: none;
					width: 48px;
					justify-content: center;
					align-items: center;
					height: 48px;
					order: 2;
					.iconfont {
						border-radius: 100%;
						padding: 0;
						color: #3ec6b7;
						background: #edfffd;
						font-weight: 500;
						width: 48px;
						font-size: 32px;
						line-height: 48px;
						text-align: center;
						height: 48px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					.num {
						margin: 5px 0;
						color: #fbd140;
						font-weight: bold;
						font-size: 30px;
						line-height: 24px;
						height: 24px;
						order: 2;
					}
					.name {
						margin: 5px 0;
						color: #816f77;
						font-size: 16px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis6:hover {
				box-shadow: 0 3px 9px rgba(0,0,0,.1);
				transform: translate3d(0, -6px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.statis7 {
				border: 1px solid #e7e9ec;
				border-radius: 10px;
				box-shadow: 0 3px 6px rgba(0,0,0,.06);
				padding: 20px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					background: none;
					display: none;
					width: 48px;
					justify-content: center;
					align-items: center;
					height: 48px;
					order: 2;
					.iconfont {
						border-radius: 100%;
						padding: 0;
						color: #3ec6b7;
						background: #edfffd;
						font-weight: 500;
						width: 48px;
						font-size: 32px;
						line-height: 48px;
						text-align: center;
						height: 48px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					.num {
						margin: 5px 0;
						color: #fbd140;
						font-weight: bold;
						font-size: 30px;
						line-height: 24px;
						height: 24px;
						order: 2;
					}
					.name {
						margin: 5px 0;
						color: #816f77;
						font-size: 16px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis7:hover {
				box-shadow: 0 3px 9px rgba(0,0,0,.1);
				transform: translate3d(0, -6px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.statis8 {
				border: 1px solid #e7e9ec;
				border-radius: 10px;
				box-shadow: 0 3px 6px rgba(0,0,0,.06);
				padding: 20px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					background: none;
					display: none;
					width: 48px;
					justify-content: center;
					align-items: center;
					height: 48px;
					order: 2;
					.iconfont {
						border-radius: 100%;
						padding: 0;
						color: #3ec6b7;
						background: #edfffd;
						font-weight: 500;
						width: 48px;
						font-size: 32px;
						line-height: 48px;
						text-align: center;
						height: 48px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					.num {
						margin: 5px 0;
						color: #fbd140;
						font-weight: bold;
						font-size: 30px;
						line-height: 24px;
						height: 24px;
						order: 2;
					}
					.name {
						margin: 5px 0;
						color: #816f77;
						font-size: 16px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis8:hover {
				box-shadow: 0 3px 9px rgba(0,0,0,.1);
				transform: translate3d(0, -6px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.statis9 {
				border: 1px solid #e7e9ec;
				border-radius: 10px;
				box-shadow: 0 3px 6px rgba(0,0,0,.06);
				padding: 20px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					background: none;
					display: none;
					width: 48px;
					justify-content: center;
					align-items: center;
					height: 48px;
					order: 2;
					.iconfont {
						border-radius: 100%;
						padding: 0;
						color: #3ec6b7;
						background: #edfffd;
						font-weight: 500;
						width: 48px;
						font-size: 32px;
						line-height: 48px;
						text-align: center;
						height: 48px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					.num {
						margin: 5px 0;
						color: #fbd140;
						font-weight: bold;
						font-size: 30px;
						line-height: 24px;
						height: 24px;
						order: 2;
					}
					.name {
						margin: 5px 0;
						color: #816f77;
						font-size: 16px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis9:hover {
				box-shadow: 0 3px 9px rgba(0,0,0,.1);
				transform: translate3d(0, -6px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
			.statis10 {
				border: 1px solid #e7e9ec;
				border-radius: 10px;
				box-shadow: 0 3px 6px rgba(0,0,0,.06);
				padding: 20px;
				margin: 10px;
				background: #fff;
				display: flex;
				width: calc(20% - 20px);
				transition: 0.3s;
				.left {
					background: none;
					display: none;
					width: 48px;
					justify-content: center;
					align-items: center;
					height: 48px;
					order: 2;
					.iconfont {
						border-radius: 100%;
						padding: 0;
						color: #3ec6b7;
						background: #edfffd;
						font-weight: 500;
						width: 48px;
						font-size: 32px;
						line-height: 48px;
						text-align: center;
						height: 48px;
					}
				}
				.right {
					flex-direction: column;
					display: flex;
					width: 100%;
					justify-content: center;
					.num {
						margin: 5px 0;
						color: #fbd140;
						font-weight: bold;
						font-size: 30px;
						line-height: 24px;
						height: 24px;
						order: 2;
					}
					.name {
						margin: 5px 0;
						color: #816f77;
						font-size: 16px;
						line-height: 24px;
						height: 24px;
					}
				}
			}
			.statis10:hover {
				box-shadow: 0 3px 9px rgba(0,0,0,.1);
				transform: translate3d(0, -6px, 0);
				z-index: 1;
				background: rgba(255,255,255,1);
			}
		}
		.news-box {
			border: 1px solid #e7e9ec;
			padding: 10px 20px;
			margin: 10px;
			display: flex;
			transition: 0.3s;
			border-radius: 10px;
			box-shadow: 0 3px 6px rgba(0,0,0,.06);
			flex-direction: column;
			flex: 1;
			background: #fff;
			width: calc(50% - 20px);
			justify-content: center;
			align-items: center;
			order: 11;
			.news-title {
				color: #000;
				font-weight: 600;
				width: 100%;
				font-size: 18px;
				line-height: 1;
			}
			.news-list {
				display: flex;
				width: 100%;
				justify-content: space-between;
				align-items: center;
				flex-wrap: wrap;
			}
			.news-item {
				border: 0px solid #8ebc7450;
				cursor: pointer;
				padding: 10px 0 0;
				margin: 20px 0 0;
				display: flex;
				width: calc(100% - 0px);
				border-width: 1px 0 0;
				justify-content: space-between;
				align-items: center;
				.news-text {
					overflow: hidden;
					color: #000;
					white-space: nowrap;
					flex: 1;
					font-weight: 500;
					width: 100%;
					font-size: 15px;
					line-height: 2;
					text-overflow: ellipsis;
				}
				.news-img-box {
					margin: 0 10px 0 0;
					overflow: hidden;
					display: none;
					width: auto;
					height: auto;
					img {
						object-fit: cover;
						width: 60px;
						height: 60px;
					}
				}
				.news-desc {
					overflow: hidden;
					color: #666;
					white-space: nowrap;
					flex: 1;
					display: none;
					width: 100%;
					text-overflow: ellipsis;
				}
				.news-time {
					color: #999;
					font-size: 12px;
				}
			}
		}
		.news-box:hover {
			box-shadow: 0 3px 9px rgba(0,0,0,.1);
			transform: translate3d(0, -6px, 0);
			z-index: 1;
			background: rgba(255,255,255,1);
		}
		.calendar {
			border: 1px solid #e7e9ec;
			padding: 0px;
			margin: 10px;
			display: flex;
			transition: 0.3s;
			border-radius: 10px;
			box-shadow: 0 3px 6px rgba(0,0,0,.06);
			flex-direction: column;
			flex: 1;
			width: calc(50% - 20px);
			justify-content: center;
			align-items: center;
			order: 12;
			.option {
				border-radius: 10px 10px 0 0;
				padding: 20px;
				color: #000;
				background: #fff;
				display: flex;
				width: 100%;
				font-size: 16px;
				justify-content: space-between;
				flex-wrap: wrap;
				height: auto;
				.pyear {
					cursor: pointer;
					border-radius: 4px;
					padding: 0 12px;
					color: inherit;
					background: none;
					display: none;
					width: auto;
					font-size: inherit;
					justify-content: center;
					align-items: center;
					height: 40px;
					.iconfont {
						margin: 0 2px;
						color: inherit;
						font-size: inherit;
					}
				}
				.pyear:hover {
					opacity: 0.8;
				}
				.pmonth {
					cursor: pointer;
					border-radius: 4px;
					padding: 0 12px;
					color: inherit;
					background: none;
					display: flex;
					width: auto;
					font-size: inherit;
					justify-content: center;
					align-items: center;
					height: 40px;
					.iconfont {
						margin: 0 2px;
						color: inherit;
						font-size: inherit;
					}
				}
				.pmonth:hover {
					opacity: 0.8;
				}
				.date-box {
					padding: 0 10px;
					display: flex;
					justify-content: center;
					align-items: center;
					.year {
						margin: 0 2px;
						color: inherit;
						font-size: 24px;
					}
					.month {
						margin: 0 2px;
						color: inherit;
						font-size: 24px;
					}
				}
				.nmonth {
					cursor: pointer;
					border-radius: 4px;
					padding: 0 12px;
					color: inherit;
					background: none;
					display: flex;
					width: auto;
					font-size: inherit;
					justify-content: center;
					align-items: center;
					height: 40px;
					.iconfont {
						margin: 0 2px;
						color: inherit;
						font-size: inherit;
					}
				}
				.nmonth:hover {
					opacity: 0.8;
				}
				.nyear {
					cursor: pointer;
					border-radius: 4px;
					padding: 0 12px;
					color: inherit;
					background: none;
					display: none;
					width: auto;
					font-size: inherit;
					justify-content: center;
					align-items: center;
					height: 40px;
					.iconfont {
						margin: 0 2px;
						color: inherit;
						font-size: inherit;
					}
				}
				.nyear:hover {
					opacity: 0.8;
				}
			}
			table {
				border-radius: 0 0 8px 8px;
				padding: 0 0 10px;
				background: #fff;
				width: 100%;
				height: auto;
				thead {
					width: 100%;
					height: auto;
					tr {
						display: flex;
						width: 100%;
						justify-content: center;
						align-items: center;
						height: auto;
						th {
							color: #666;
							flex: 1;
							display: flex;
							font-size: 16px;
							line-height: 30px;
							justify-content: center;
							align-items: center;
						}
					}
				}
				tbody {
					width: 100%;
					height: auto;
					tr {
						display: flex;
						width: 100%;
						justify-content: center;
						align-items: center;
						height: auto;
						td {
							cursor: pointer;
							padding: 2px 10px;
							flex: 1;
							display: flex;
							height: auto;
							.text {
								border-radius: 12px;
								flex-direction: column;
								background: #8ebc7410;
								display: flex;
								width: 100%;
								justify-content: center;
								align-items: center;
								height: 100%;
							}
							.text:hover {
								background: #8ebc7420;
							}
							.text .new {
								color: #000;
								font-size: 24px;
								line-height: 1.4;
							}
							.text .old {
								color: #666;
								font-size: 16px;
								line-height: 1.4;
							}
						}
						td.festival {
							.text {
								border-radius: 12px;
								flex-direction: column;
								background: #8ebc7430;
								display: flex;
								width: 100%;
								justify-content: center;
								align-items: center;
								height: 100%;
							}
							.text:hover {
								background: #8ebc7450;
							}
							.text .new {
								color: #000;
								font-size: 24px;
								line-height: 1.4;
							}
							.text .old {
								color: #666;
								font-size: 16px;
								line-height: 1.4;
							}
						}
						td.other {
							.text {
								border-radius: 12px;
								flex-direction: column;
								background: #fff;
								display: flex;
								width: 100%;
								justify-content: center;
								align-items: center;
								opacity: 0.3;
								height: 100%;
							}
							.text:hover {
								background: #f4f7fe;
							}
							.text .new {
								color: #000;
								font-size: 24px;
								line-height: 1.4;
							}
							.text .old {
								color: #666;
								font-size: 16px;
								line-height: 1.4;
							}
						}
						td.today {
							.text {
								border-radius: 12px;
								flex-direction: column;
								color: #fff;
								background: #8ebc74;
								display: flex;
								width: 100%;
								justify-content: center;
								align-items: center;
								height: 100%;
							}
							.text:hover {
								background: #8ebc74;
								opacity: 0.8;
							}
							.text .new {
								color: inherit;
								font-size: 24px;
								line-height: 1.4;
							}
							.text .old {
								color: inherit;
								font-size: 16px;
								line-height: 1.4;
							}
						}
					}
				}
			}
		}
		.calendar:hover {
			box-shadow: 0 3px 9px rgba(0,0,0,.1);
			transform: translate3d(0, -6px, 0);
			z-index: 1;
			background: rgba(255,255,255,1);
		}
	}
	
	.echarts-flag-2 {
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
		padding: 10px 20px;
		background: rebeccapurple;
	
		&>div {
			width: 32%;
			height: 300px;
			margin: 10px 0;
			background: rgba(255,255,255,.1);
			border-radius: 8px;
			padding: 10px 20px;
		}
	}
	.animate__animated {
		animation-fill-mode: none;
	}
</style>
