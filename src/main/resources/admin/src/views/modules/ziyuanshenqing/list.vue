  
<template>
	<div class="main-content" :style='{"padding":"50px 30px 30px"}'>
		<!-- 列表页 -->
		<template v-if="showFlag ">
			<el-form class="center-form-pv" :style='{"border":"1px solid #e7e9ec","padding":"20px 20px 0","boxShadow":"0 3px 6px rgba(0,0,0,.06)","margin":"0 0 20px","borderRadius":"10px","flexWrap":"wrap","background":"#fff","display":"flex","width":"100%","justifyContent":"space-between"}' :inline="true" :model="searchForm">
				<el-row :style='{"padding":"0px","boxShadow":"none","borderRadius":"10px","flexWrap":"wrap","display":"flex","width":"auto","position":"relative"}' >
					<div  :style='{"margin":"0 10px 20px 0","display":"flex"}'>
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#666","display":"inline-block","lineHeight":"40px","fontSize":"16px","fontWeight":"500","height":"40px"}' class="item-label">资源名称</label>
						<el-input v-model="searchForm.ziyuanmingcheng" placeholder="资源名称" @keydown.enter.native="search()" clearable></el-input>
					</div>
					<div  :style='{"margin":"0 10px 20px 0","display":"flex"}'>
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#666","display":"inline-block","lineHeight":"40px","fontSize":"16px","fontWeight":"500","height":"40px"}' class="item-label">申请原因</label>
						<el-input v-model="searchForm.shenqingyuanyin" placeholder="申请原因" @keydown.enter.native="search()" clearable></el-input>
					</div>
					<div :style='{"margin":"0 10px 20px 0","display":"flex"}' class="select">
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#666","display":"inline-block","lineHeight":"40px","fontSize":"16px","fontWeight":"500","height":"40px"}' class="item-label">是否通过</label>
						<el-select clearable v-model="searchForm.sfsh" placeholder="是否通过">
							<el-option v-for="(item,index) in sfshOptions" v-bind:key="index" :label="item" :value="item"></el-option>
						</el-select>
					</div>
					<div :style='{"margin":"0 10px 20px 0","display":"flex"}' class="select">
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#666","display":"inline-block","lineHeight":"40px","fontSize":"16px","fontWeight":"500","height":"40px"}' class="item-label">是否支付</label>
						<el-select clearable v-model="searchForm.ispay" placeholder="是否支付">
							<el-option v-for="(item,index) in isPayOptions" v-bind:key="index" :label="item" :value="item"></el-option>
						</el-select>
					</div>
					<el-button class="search" type="success" @click="search()">
						<span class="icon iconfont icon-fangdajing01" :style='{"margin":"0 0px","fontSize":"16px","color":"#fff","height":"40px"}'></span>
						查询
					</el-button>
				</el-row>

				<el-row class="actions" :style='{"width":"auto","margin":"0px 0 20px -4px","flexWrap":"wrap","display":"flex"}'>
					<el-button class="btn18" type="success" @click="refreshClick()">
						刷新
					</el-button>
					<el-button class="add" v-if="isAuth('ziyuanshenqing','新增')" type="success" @click="addOrUpdateHandler()">
						<span class="icon iconfont icon-tianjia16" :style='{"margin":"0 0px","fontSize":"16px","color":"#5d4037","height":"auto"}'></span>
						添加
					</el-button>
					<el-button class="del" v-if="isAuth('ziyuanshenqing','删除')" :disabled="dataListSelections.length?false:true" type="danger" @click="deleteHandler()">
						<span class="icon iconfont icon-shanchu6" :style='{"margin":"0 0px","fontSize":"16px","color":"#db562d","height":"auto"}'></span>
						删除
					</el-button>


					<el-button class="btn18" v-if="isAuth('ziyuanshenqing','审核')" :disabled="dataListSelections.length?false:true" type="success" @click="shBatchDialog()">
						<span class="icon iconfont icon-shenhe9" :style='{"margin":"0 0px","fontSize":"16px","color":"#5d4037","height":"auto"}'></span>
						审核
					</el-button>
					<el-button class="btn18" v-if="isAuth('ziyuanshenqing','支付')" :disabled="dataListSelections.length?false:true" type="success" @click="payBatch()">
						<span class="icon iconfont icon-zhifudingjin" :style='{"margin":"0 0px","fontSize":"16px","color":"#5d4037","height":"auto"}'></span>
						批量支付
					</el-button>
				</el-row>
			</el-form>
			<div :style='{"border":"1px solid #e7e9ec","width":"100%","padding":"20px","boxShadow":"0 3px 6px rgba(0,0,0,.06)","borderRadius":"10px","background":"#fff"}'>
				<el-table class="tables"
					:stripe='false'
					:style='{"padding":"0","borderColor":"#e7e8fc","borderRadius":"10px","borderWidth":"0px 0 0 0px","background":"#fff","width":"100%","borderStyle":"solid"}' 
					:border='false'
					v-if="isAuth('ziyuanshenqing','查看')"
					:data="dataList"
					v-loading="dataListLoading"
					@selection-change="selectionChangeHandler">
					<el-table-column :resizable='true' type="selection" align="center" width="50"></el-table-column>
					<el-table-column :resizable='true' :sortable='true' label="序号" type="index" width="50" />
					<el-table-column :resizable='true' :sortable='true'
												prop="shenqingbianhao"
						label="申请编号">
						<template slot-scope="scope">
							{{scope.row.shenqingbianhao}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'
												prop="ziyuanmingcheng"
						label="资源名称">
						<template slot-scope="scope">
							{{scope.row.ziyuanmingcheng}}
						</template>
					</el-table-column>
					<el-table-column  :resizable='true' prop="ziyuantupian" width="200" label="资源图片">
						<template slot-scope="scope">
							<div v-if="scope.row.ziyuantupian">
								<img v-if="scope.row.ziyuantupian.substring(0,4)=='http'&&scope.row.ziyuantupian.split(',w').length>1" :src="scope.row.ziyuantupian" width="100" height="100" style="object-fit: cover" @click="imgPreView(scope.row.ziyuantupian)">
								<img v-else-if="scope.row.ziyuantupian.substring(0,4)=='http'" :src="scope.row.ziyuantupian.split(',')[0]" width="100" height="100" style="object-fit: cover" @click="imgPreView(scope.row.ziyuantupian.split(',')[0])">
								<img v-else :src="$base.url+scope.row.ziyuantupian.split(',')[0]" width="100" height="100" style="object-fit: cover" @click="imgPreView($base.url+scope.row.ziyuantupian.split(',')[0])">
							</div>
							<div v-else>无图片</div>
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'
												prop="ziyuanfenlei"
						label="资源分类">
						<template slot-scope="scope">
							{{scope.row.ziyuanfenlei}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'
												prop="ziyuanjiage"
						label="资源价格">
						<template slot-scope="scope">
							{{scope.row.ziyuanjiage}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'
												prop="ziyuanshuliang"
						label="申请数量">
						<template slot-scope="scope">
							{{scope.row.ziyuanshuliang}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'
												prop="shenqingfeiyong"
						label="申请费用">
						<template slot-scope="scope">
							{{scope.row.shenqingfeiyong}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'
												prop="shenqingshijian"
						label="申请时间">
						<template slot-scope="scope">
							{{scope.row.shenqingshijian}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true' prop="ispay" label="是否支付">
						<template slot-scope="scope">
							<el-tag v-if="scope.row.ispay=='已支付'" type="success">已支付</el-tag>
							<el-tag v-else type="danger">未支付</el-tag>
							<el-button v-if="scope.row.ispay!='已支付' && isAuth('ziyuanshenqing','支付') " type="text" size="small" @click="payHandler(scope.row)">支付</el-button>
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true' prop="shhf" label="审核回复" show-overflow-tooltip>
						<template slot-scope="scope">
							<div style="white-space: nowrap;">{{scope.row.shhf}}</div>
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true' prop="sfsh" label="审核状态">
						<template slot-scope="scope">
							<el-tag v-if="scope.row.sfsh=='否'" type="danger">未通过</el-tag>
							<el-tag v-if="scope.row.sfsh=='待审核'" type="warning">待审核</el-tag>
							<el-tag v-if="scope.row.sfsh=='是'" type="success">通过</el-tag>
						</template>
					</el-table-column>
					
					<el-table-column width="300" label="操作">
						<template slot-scope="scope">
							<el-button class="view" v-if=" isAuth('ziyuanshenqing','查看')" type="success" @click="addOrUpdateHandler(scope.row.id,'info')">
								<span class="icon iconfont icon-chakan2" :style='{"margin":"0 0px","fontSize":"16px","color":"#333","height":"40px"}'></span>
								查看
							</el-button>
							<el-button class="btn8" v-if="isAuth('ziyuanshenqing','取消预约')" @click="yuyuequxiaoCrossAddOrUpdateHandler(scope.row,'cross','是','是','','')" type="success">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 0px","fontSize":"14px","color":"#5d4037","height":"40px"}'></span>
								取消预约
							</el-button>
							<el-button class="edit" v-if=" isAuth('ziyuanshenqing','修改')  && scope.row.sfsh=='待审核' " type="success" @click="addOrUpdateHandler(scope.row.id)">
								<span class="icon iconfont icon-xiugai1" :style='{"margin":"0 0px","fontSize":"16px","color":"#8ebc74","height":"40px"}'></span>
								修改
							</el-button>




							<el-button class="del" v-if="isAuth('ziyuanshenqing','删除')" type="primary" @click="deleteHandler(scope.row.id)">
								<span class="icon iconfont icon-shanchu6" :style='{"margin":"0 0px","fontSize":"14px","color":"#e56060","height":"40px"}'></span>
								删除
							</el-button>
						</template>
					</el-table-column>
				</el-table>
			</div>
			<el-pagination
				@size-change="sizeChangeHandle"
				@current-change="currentChangeHandle"
				:current-page="pageIndex"
				background
				:page-sizes="[10, 50, 100, 200]"
				:page-size="pageSize"
				:layout="layouts.join()"
				:total="totalPage"
				prev-text="< "
				next-text="> "
				:hide-on-single-page="false"
				:style='{"padding":"0","margin":"20px 0 0","whiteSpace":"nowrap","color":"#333","display":"flex","width":"100%","fontWeight":"500","justifyContent":"flex-end"}'
			></el-pagination>
		</template>
		<!-- 添加/修改页面  将父组件的search方法传递给子组件-->
		<add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>

		<yuyuequxiao-cross-add-or-update v-if="yuyuequxiaoCrossAddOrUpdateFlag" :parent="this" ref="yuyuequxiaoCrossaddOrUpdate"></yuyuequxiao-cross-add-or-update>

		
		<el-dialog :title="this.batchIds.length>1?'批量审核':'审核'" :visible.sync="sfshBatchVisiable" width="50%">
			<el-form ref="shBatchForm" :model="shBatchForm" :rules="shRules" label-width="80px">
				<el-form-item label="审核状态" prop="sfsh">
					<el-select v-model="shBatchForm.sfsh" placeholder="审核状态">
						<el-option label="通过" value="是"></el-option>
						<el-option label="不通过" value="否"></el-option>
						<el-option label="待审核" value="待审核"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="内容" prop="shhf">
					<el-input type="textarea" :rows="8" v-model="shBatchForm.shhf"></el-input>
				</el-form-item>
			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button @click="sfshBatchVisiable=false">取 消</el-button>
				<el-button type="primary" @click="shBatchHandler">确 定</el-button>
			</span>
		</el-dialog>



		<el-dialog title="预览图" :visible.sync="previewVisible" width="50%">
			<img :src="previewImg" alt="" style="width: 100%;">
		</el-dialog>
	</div>
</template>

<script>
	import * as echarts from 'echarts'
	import chinaJson from "@/components/echarts/china.json";
	import axios from 'axios';
	import AddOrUpdate from "./add-or-update";
	import yuyuequxiaoCrossAddOrUpdate from "../yuyuequxiao/add-or-update";
	import {
		Loading
	} from 'element-ui';
	export default {
		data() {
			return {
				indexQueryCondition: '',
				searchForm: {
					key: ""
				},
				form:{},
				sfshOptions: [],
				isPayOptions: [],
				dataList: [],
				pageIndex: 1,
				pageSize: 10,
				totalPage: 0,
				dataListLoading: false,
				dataListSelections: [],
				showFlag: true,
				sfshVisiable: false,
				shForm: {},
				sfshBatchVisiable: false,
				shBatchForm: {
					sfsh:'',
					shhf:''
				},
				shRules: {
					sfsh:[{ required: true, message: '审核状态不能为空', trigger: 'blur' },],
				},
				batchIds:[],
				shList: [],
				addOrUpdateFlag:false,
				yuyuequxiaoCrossAddOrUpdateFlag: false,
				layouts: ["total","prev","pager","next","sizes","jumper"],
				previewImg: '',
				previewVisible: false,
			};
		},
		created() {
			if(this.statType) {
				return false
			}
			this.init();
			this.getDataList();
		},
		mounted() {
		},
		watch: {
		},
		filters: {
			htmlfilter: function (val) {
				return val.replace(/<[^>]*>/g).replace(/undefined/g,'');
			}
		},
		computed: {
			tablename(){
				return this.$storage.get('sessionTable')
			},
			role(){
				return this.$storage.get('role')
			},
		},
		components: {
			AddOrUpdate,
			yuyuequxiaoCrossAddOrUpdate,
		},
		methods: {
			queryChange(arr){
				for(let x in arr) {
					if(arr[x] == this.role) {
						return true
					}
				}
				return false
			},
			imgPreView(url){
				this.previewImg = url
				this.previewVisible = true
				
			},
			yuyuequxiaoCrossAddOrUpdateHandler(row,type,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue){
				if(crossOptAudit=='是'&&row.sfsh!='是') {
					this.$message({
						message: "请审核通过后再操作",
						type: "warning",
						duration: 1500,
						onClose: () => {
						}
					});
					return
				}
				if(crossOptPay=='是'&&row.ispay!='已支付'){
					this.$message({
						message: "请支付完成再操作",
						type: "warning",
						duration: 1500,
						onClose: () => {
						}
					});
					return
				}
				this.showFlag = false;
				this.addOrUpdateFlag = false;
				this.yuyuequxiaoCrossAddOrUpdateFlag = true;
				this.$storage.set('crossObj',row);
				this.$storage.set('crossTable','ziyuanshenqing');
				this.$storage.set('statusColumnName',statusColumnName);
				this.$storage.set('statusColumnValue',statusColumnValue);
				this.$storage.set('tips',tips);
				if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
					var obj = this.$storage.getObj('crossObj');
					for (var o in obj){
						if(o==statusColumnName && obj[o]==statusColumnValue){
							this.$message({
								message: tips,
								type: "warning",
								duration: 1500,
								onClose: () => {
									this.getDataList();
								}
							});
							this.showFlag = true;
							this.yuyuequxiaoCrossAddOrUpdateFlag = false;
							return;
						}
					}
				}
				this.$nextTick(() => {
					this.$refs.yuyuequxiaoCrossaddOrUpdate.init(row.id,type);
				});
			},
			payHandler(row){
				if(row.sfsh!='是'){
					this.$message({
						message: "请审核通过后再操作",
						type: "warning",
						duration: 1500,
						onClose: () => {
						}
					});
					return false
				}
				this.$storage.set('paytable','ziyuanshenqing');
				this.$storage.set('payObject',row);
				this.$router.push('pay');
			},
			// 批量支付
			payBatch(){
				for(let x in this.dataListSelections){
					if(this.dataListSelections[x].sfsh!='是'){
						this.$message.error('所选订单存在未审核订单')
						return false
					}
					if(this.dataListSelections[x].ispay=='已支付'){
						this.$message.error('所选订单存在已支付订单')
						return false
					}
				}
				this.$confirm('是否支付所选订单？').then(async _ => {
					let arr = JSON.parse(JSON.stringify(this.dataListSelections))
					for(let i in arr){
						arr[i].ispay = '已支付'
						let changeType = true
						let errMsg = ''
						if(!changeType) {
							this.$message.error(errMsg);
							return false
						}
						this.$http({
							url: 'ziyuanshenqing/update',
							method: "post",
							data: arr[i]
						}).then(res=>{
							
						})
					}
					this.$message({
						message: "支付成功",
						type: "success",
						duration: 1500,
						onClose: () => {
							this.getDataList()
						}
					});
				}).catch(_ => {});
			},
			init () {
				this.sfshOptions = "是,否,待审核".split(',');
				this.isPayOptions = "已支付,未支付".split(',')
			},
			search() {
				this.pageIndex = 1;
				this.getDataList();
			},

			// 获取数据列表
			getDataList() {
				this.dataListLoading = true;
				let params = {
					page: this.pageIndex,
					limit: this.pageSize,
					sort: 'id',
					order: 'desc',
				}
				if(this.searchForm.ziyuanmingcheng!='' && this.searchForm.ziyuanmingcheng!=undefined){
					params['ziyuanmingcheng'] = '%' + this.searchForm.ziyuanmingcheng + '%'
				}
				if(this.searchForm.shenqingyuanyin!='' && this.searchForm.shenqingyuanyin!=undefined){
					params['shenqingyuanyin'] = '%' + this.searchForm.shenqingyuanyin + '%'
				}
				if(this.searchForm.sfsh!='' && this.searchForm.sfsh!=undefined){
					params['sfsh'] = this.searchForm.sfsh
				}
				if(this.searchForm.ispay!='' && this.searchForm.ispay!=undefined){
					params['ispay'] = this.searchForm.ispay
				}
				let user = JSON.parse(this.$storage.getObj('userForm'))
				this.$http({
					url: "ziyuanshenqing/page",
					method: "get",
					params: params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.dataList = data.data.list;
						this.totalPage = data.data.total;
					} else {
						this.dataList = [];
						this.totalPage = 0;
					}
					this.dataListLoading = false;
				});
			},
			// 每页数
			sizeChangeHandle(val) {
				this.pageSize = val;
				this.pageIndex = 1;
				this.getDataList();
			},
			// 当前页
			currentChangeHandle(val) {
				this.pageIndex = val;
				this.getDataList();
			},
			// 多选
			selectionChangeHandler(val) {
				this.dataListSelections = val;
			},
			// 添加/修改
			addOrUpdateHandler(id,type) {
				this.showFlag = false;
				this.addOrUpdateFlag = true;
				this.crossAddOrUpdateFlag = false;
				if(type!='info'&&type!='msg'){
					type = 'else';
				}
				this.$nextTick(() => {
					this.$refs.addOrUpdate.init(id,type );
				});
			},
			//批量审核窗口
			shBatchDialog(){
				for(let x in this.dataListSelections){
					if(this.dataListSelections[x].sfsh&&this.dataListSelections[x].sfsh!='待审核'){
						this.$message.error('存在已审核数据，不能继续操作');
						this.batchIds = []
						return false
					}
					this.batchIds.push(this.dataListSelections[x].id)
				}
				this.shList = this.dataListSelections
				this.sfshBatchVisiable = true
			},
			//批量审核
			shBatchHandler(){
				this.$refs["shBatchForm"].validate(valid => {
					if(valid){
						this.$confirm(`是否${this.batchIds.length>1?'一键审核':'审核'}选中数据?`, "提示", {
							confirmButtonText: "确定",
							cancelButtonText: "取消",
							type: "warning"
						}).then(async() => {
							let changeType = true
							let errMsg = ''
							for(let x in this.shList){
								if(this.shBatchForm.sfsh=='否') {
									await this.$http.post('updateColumn/yiliaoziyuan/2',{
										csuUpdateColumn: 'ziyuanshuliang',
										csuUpdateColumnValue: '+' + this.shList[x].ziyuanshuliang,
										csuConditionColumn: 'ziyuanbianhao',
										csuConditionColumnValue: this.shList[x].ziyuanbianhao
									}).then(rs=>{
									})
								}
							}
							this.$http({
								url: "ziyuanshenqing/shBatch?sfsh="+this.shBatchForm.sfsh+"&shhf="+this.shBatchForm.shhf,
								method: "post",
								data: this.batchIds
							}).then(async ({ data }) => {
								if (data && data.code === 0) {
									this.$message({
										message: "操作成功",
										type: "success",
										duration: 1500,
										onClose: () => {
											this.getDataList();
											this.sfshBatchVisiable = false
											this.batchIds = []
										}
									});
								} else {
									this.$message.error(data.msg);
								}
							});
						});
					}
				})
			},
			// 删除
			async deleteHandler(id ) {
				var ids = id? [Number(id)]: this.dataListSelections.map(item => {
					return Number(item.id);
				});
				await this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
					confirmButtonText: "确定",
					cancelButtonText: "取消",
					type: "warning"
				}).then(async () => {
					await this.$http({
						url: "ziyuanshenqing/delete",
						method: "post",
						data: ids
					}).then(async ({ data }) => {
						if (data && data.code === 0) {
							this.$message({
								message: "操作成功",
								type: "success",
								duration: 1500,
								onClose: () => {
									this.search();
								}
							});
			
						} else {
							this.$message.error(data.msg);
						}
					});
				});
			},


		}

	};
</script>
<style lang="scss" scoped>
	
	.center-form-pv {
		.el-date-editor.el-input {
			width: auto;
		}
	}
	
	.el-input {
		width: auto;
	}
	
	// form
	.center-form-pv .el-input {
		width: 100%;
	}
	.center-form-pv .el-input ::v-deep .el-input__inner {
		border: 1px solid #8ebc74;
		border-radius: 6px;
		padding: 0 12px;
		box-shadow: none;
		outline: none;
		color: #333;
		background: #fffbf0;
		width: 100%;
		font-size: 16px;
		height: 36px;
	}
	.center-form-pv .el-select {
		width: 100%;
	}
	.center-form-pv .el-select ::v-deep .el-input__inner {
		border: 1px solid #8ebc74;
		border-radius: 6px;
		padding: 0 10px;
		box-shadow: none;
		outline: none;
		color: #333;
		background: #fffbf0;
		width: 100%;
		font-size: 16px;
		height: 36px;
	}
	.center-form-pv .el-date-editor {
		width: 100%;
	}
	
	.center-form-pv .el-date-editor ::v-deep .el-input__inner {
		border: 1px solid #8ebc74;
		border-radius: 6px;
		padding: 0 10px 0 30px;
		box-shadow: none;
		outline: none;
		color: #333;
		background: #fffbf0;
		width: 100%;
		font-size: 16px;
		height: 36px;
	}
	
	.center-form-pv .search {
		border: 0;
		cursor: pointer;
		border-radius: 6px;
		padding: 0 12px 0 10px;
		outline: none;
		color: #fff;
		background: #8ebc74;
		width: auto;
		font-size: 16px;
		height: 36px;
	}
	
	.center-form-pv .search:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .add {
		border: 0;
		cursor: pointer;
		border-radius: 6px;
		padding: 0 10px;
		margin: 4px;
		outline: none;
		color: #5d4037;
		background: #fbd35a;
		width: auto;
		font-size: 14px;
		height: 36px;
	}
	
	.center-form-pv .actions .add:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .del {
		border: 0;
		cursor: pointer;
		border-radius: 6px;
		padding: 0 10px;
		margin: 4px;
		outline: none;
		color: #db562d;
		background: #f5c7b3;
		width: auto;
		font-size: 14px;
		height: 36px;
	}
	
	.center-form-pv .actions .del:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .statis {
		border: 0;
		cursor: pointer;
		border-radius: 6px;
		padding: 0 10px;
		margin: 4px;
		outline: none;
		color: #6ba54a;
		background: #8ebc7430;
		width: auto;
		font-size: 14px;
		height: 36px;
	}
	
	.center-form-pv .actions .statis:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .btn18 {
		border: 0;
		cursor: pointer;
		border-radius: 6px;
		padding: 0 10px;
		margin: 4px;
		outline: none;
		color: #5d4037;
		background: #fbf1c4;
		width: auto;
		font-size: 14px;
		height: 36px;
	}
	
	.center-form-pv .actions .btn18:hover {
		opacity: 0.8;
	}
	
	// table
	.el-table ::v-deep .el-table__header-wrapper thead {
		color: #999;
		font-weight: 500;
		width: 100%;
	}
	
	.el-table ::v-deep .el-table__header-wrapper thead tr {
		background: none;
	}
	
	.el-table ::v-deep .el-table__header-wrapper thead tr th {
		padding: 12px 0;
		background: none;
		border-color: #ffefef;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: center;
	}

	.el-table ::v-deep .el-table__header-wrapper thead tr th .cell {
		padding: 0 0 0 5px;
		word-wrap: normal;
		color: #5d4037;
		white-space: normal;
		font-weight: bold;
		display: flex;
		vertical-align: middle;
		font-size: 14px;
		line-height: 24px;
		text-overflow: ellipsis;
		word-break: break-all;
		width: 100%;
		justify-content: flex-start;
		align-items: center;
		position: relative;
		min-width: 110px;
	}

	.el-table ::v-deep .el-table__body-wrapper {
		position: relative;
	}
	.el-table ::v-deep .el-table__body-wrapper tbody {
		width: 100%;
	}

	.el-table ::v-deep .el-table__body-wrapper tbody tr {
		background: #fff;
	}
	
	.el-table ::v-deep .el-table__body-wrapper tbody tr td {
		padding: 4px 0;
		color: #5d4037;
		background: none;
		border-color: #ffefef;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: left;
	}
	
		
	.el-table ::v-deep .el-table__body-wrapper tbody tr:hover td {
		padding: 4px 0;
		color: #5d4037;
		background: #fff;
		border-color: #ffefef;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: left;
	}
	
	.el-table ::v-deep .el-table__body-wrapper tbody tr td {
		padding: 4px 0;
		color: #5d4037;
		background: none;
		border-color: #ffefef;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: left;
	}

	.el-table ::v-deep .el-table__body-wrapper tbody tr td .cell {
		padding: 0 0 0 5px;
		overflow: hidden;
		word-break: break-all;
		white-space: normal;
		font-size: inherit;
		line-height: 24px;
		text-overflow: ellipsis;
	}
	
	.el-table ::v-deep .el-table__body-wrapper tbody tr td .view {
		border: 0;
		cursor: pointer;
		border-radius: 6px;
		padding: 0 0px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #5d4036;
		background: none;
		width: auto;
		font-size: 14px;
		height: 32px;
		order: 3;
	}
	
	.el-table ::v-deep .el-table__body-wrapper tbody tr td .view:hover {
		opacity: 0.8;
	}
	
	.el-table ::v-deep .el-table__body-wrapper tbody tr td .add {
	}
	
	.el-table ::v-deep .el-table__body-wrapper tbody tr td .add:hover {
	}
	
	.el-table ::v-deep .el-table__body-wrapper tbody tr td .edit {
		border: 0;
		cursor: pointer;
		border-radius: 6px;
		padding: 0 0px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #8ebc74;
		background: none;
		width: auto;
		font-size: 14px;
		height: 32px;
		order: -1;
	}
	
	.el-table ::v-deep .el-table__body-wrapper tbody tr td .edit:hover {
		opacity: 0.8;
	}
	
	.el-table ::v-deep .el-table__body-wrapper tbody tr td .del {
		border: 0;
		cursor: pointer;
		border-radius: 6px;
		padding: 0 0px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #e56060;
		background: none;
		width: auto;
		font-size: 14px;
		height: 32px;
	}
	
	.el-table ::v-deep .el-table__body-wrapper tbody tr td .del:hover {
		opacity: 0.8;
	}
	
	.el-table ::v-deep .el-table__body-wrapper tbody tr td .btn8 {
		border: 0;
		cursor: pointer;
		border-radius: 6px;
		padding: 0 0px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #5d4037;
		background: none;
		width: auto;
		font-size: 14px;
		height: 32px;
		order: 5;
	}
	
	.el-table ::v-deep .el-table__body-wrapper tbody tr td .btn8:hover {
		opacity: 0.8;
	}
	
	// pagination
	.main-content .el-pagination ::v-deep .el-pagination__total {
		margin: 0 auto 0 0;
		color: #666;
		font-weight: 400;
		display: inline-block;
		vertical-align: top;
		font-size: 15px;
		line-height: 28px;
		height: 28px;
	}
	
	.main-content .el-pagination ::v-deep .btn-prev {
		border: 1px solid #e7e9ec;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #666;
		background: #fff;
		display: inline-block;
		vertical-align: top;
		font-size: 15px;
		line-height: 28px;
		min-width: 35px;
		height: 28px;
	}
	
	.main-content .el-pagination ::v-deep .btn-next {
		border: 1px solid #e7e9ec;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #666;
		background: #fff;
		display: inline-block;
		vertical-align: top;
		font-size: 15px;
		line-height: 28px;
		min-width: 35px;
		height: 28px;
	}
	
	.main-content .el-pagination ::v-deep .btn-prev:disabled {
		border: 1px solid #e7e9ec;
		cursor: not-allowed;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #666;
		background: #eee;
		display: inline-block;
		vertical-align: top;
		font-size: 15px;
		line-height: 28px;
		height: 28px;
	}
	
	.main-content .el-pagination ::v-deep .btn-next:disabled {
		border: 1px solid #e7e9ec;
		cursor: not-allowed;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #666;
		background: #eee;
		display: inline-block;
		vertical-align: top;
		font-size: 15px;
		line-height: 28px;
		height: 28px;
	}

	.main-content .el-pagination ::v-deep .el-pager {
		padding: 0;
		margin: 0;
		display: inline-block;
		vertical-align: top;
	}

	.main-content .el-pagination ::v-deep .el-pager .number {
		cursor: pointer;
		border: 1px solid #e7e9ec;
		padding: 0 4px;
		margin: 0 5px;
		color: #666;
		display: inline-block;
		vertical-align: top;
		font-size: 15px;
		line-height: 28px;
		border-radius: 2px;
		background: #fff;
		text-align: center;
		min-width: 30px;
		height: 28px;
	}
	
	.main-content .el-pagination ::v-deep .el-pager .number:hover {
		cursor: pointer;
		padding: 0 4px;
		margin: 0 5px;
		color: #fff;
		display: inline-block;
		vertical-align: top;
		font-size: 15px;
		border-color: #fbd35a;
		line-height: 28px;
		border-radius: 2px;
		background: #fbd35a;
		text-align: center;
		min-width: 30px;
		height: 28px;
	}
	
	.main-content .el-pagination ::v-deep .el-pager .number.active {
		cursor: default;
		padding: 0 4px;
		margin: 0 5px;
		color: #fff;
		display: inline-block;
		vertical-align: top;
		font-size: 15px;
		border-color: #fbd35a;
		line-height: 28px;
		border-radius: 2px;
		background: #fbd35a;
		text-align: center;
		min-width: 30px;
		height: 28px;
	}
	
	.main-content .el-pagination ::v-deep .el-pagination__sizes {
		display: inline-block;
		vertical-align: top;
		font-size: 15px;
		line-height: 28px;
		height: 28px;
	}
	
	.main-content .el-pagination ::v-deep .el-pagination__sizes .el-input {
		margin: 0 5px;
		width: 100px;
		position: relative;
	}
	
	.main-content .el-pagination ::v-deep .el-pagination__sizes .el-input .el-input__inner {
		border: 1px solid #e7e9ec;
		cursor: pointer;
		padding: 0 25px 0 8px;
		color: #606266;
		display: inline-block;
		font-size: 15px;
		line-height: 28px;
		border-radius: 3px;
		outline: 0;
		background: #FFF;
		width: 100%;
		text-align: center;
		height: 28px;
	}
	
	.main-content .el-pagination ::v-deep .el-pagination__sizes .el-input span.el-input__suffix {
		top: 0;
		position: absolute;
		right: 0;
		height: 100%;
	}
	
	.main-content .el-pagination ::v-deep .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
		cursor: pointer;
		color: #C0C4CC;
		width: 25px;
		font-size: 15px;
		line-height: 28px;
		text-align: center;
	}
	
	.main-content .el-pagination ::v-deep .el-pagination__jump {
		margin: 0 0 0 24px;
		color: #606266;
		display: inline-block;
		vertical-align: top;
		font-size: 15px;
		line-height: 28px;
		height: 28px;
	}
	
	.main-content .el-pagination ::v-deep .el-pagination__jump .el-input {
		border-radius: 3px;
		padding: 0 2px;
		margin: 0 2px;
		display: inline-block;
		width: 50px;
		font-size: 15px;
		line-height: 18px;
		position: relative;
		text-align: center;
		height: 28px;
	}
	
	.main-content .el-pagination ::v-deep .el-pagination__jump .el-input .el-input__inner {
		border: 1px solid #e7e9ec;
		cursor: pointer;
		padding: 0 3px;
		color: #606266;
		display: inline-block;
		font-size: 15px;
		line-height: 28px;
		border-radius: 3px;
		outline: 0;
		background: #FFF;
		width: 100%;
		text-align: center;
		height: 28px;
	}
	
	// list one
	.one .list1-view {
		border: 0;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 10px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #fff;
		background: #6d96ff;
		width: auto;
		font-size: 14px;
		height: 32px;
		order: 3;
	}
	
	.one .list1-view:hover {
		opacity: 0.8;
	}
	
	.one .list1-edit {
		border: 0;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 10px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #4f7df5;
		background: #edf2ff;
		width: auto;
		font-size: 14px;
		height: 32px;
	}
	
	.one .list1-edit:hover {
		opacity: 0.8;
	}
	
	.one .list1-del {
		border: 0;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 10px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #f00;
		background: #ffefed;
		width: auto;
		font-size: 14px;
		height: 32px;
	}
	
	.one .list1-del:hover {
		opacity: 0.8;
	}
	
	.one .list1-btn8 {
		border: 0;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 10px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #fff;
		background: #3bcfbf;
		width: auto;
		font-size: 14px;
		height: 32px;
		order: 11;
	}
	
	.one .list1-btn8:hover {
		opacity: 0.8;
	}
	
	.main-content .el-table .el-switch {
		display: inline-flex;
		vertical-align: middle;
		line-height: 30px;
		position: relative;
		align-items: center;
		height: 30px;
	}
	.main-content .el-table .el-switch ::v-deep .el-switch__label--left {
		cursor: pointer;
		margin: 0 10px 0 0;
		color: #333;
		font-weight: 500;
		display: none;
		vertical-align: middle;
		font-size: 16px;
		transition: .2s;
		height: 30px;
	}
	.main-content .el-table .el-switch ::v-deep .el-switch__label--right {
		cursor: pointer;
		margin: 0 0 0 10px;
		color: #333;
		font-weight: 500;
		display: none;
		vertical-align: middle;
		font-size: 16px;
		transition: .2s;
		height: 30px;
	}
	.main-content .el-table .el-switch ::v-deep .el-switch__core {
		border: 1px solid #ff8b00;
		cursor: pointer;
		border-radius: 15px;
		margin: 0;
		outline: 0;
		background: #ff8b00;
		display: inline-block;
		width: 36px;
		box-sizing: border-box;
		transition: border-color .3s,background-color .3s;
		height: 18px;
	}
	.main-content .el-table .el-switch ::v-deep .el-switch__core::after {
		border-radius: 100%;
		top: 1px;
		left: 2px;
		background: #FFF;
		width: 14px;
		position: absolute;
		transition: all .3s;
		height: 14px;
	}
	.main-content .el-table .el-switch.is-checked ::v-deep .el-switch__core::after {
		margin: 0 0 0 -16px;
		left: 100%;
	}
	
	.main-content .el-table .el-rate ::v-deep .el-rate__item {
		cursor: pointer;
		display: inline-block;
		vertical-align: middle;
		font-size: 0;
		position: relative;
	}
	.main-content .el-table .el-rate ::v-deep .el-rate__item .el-rate__icon {
		margin: 0 3px;
		display: inline-block;
		font-size: 18px;
		position: relative;
		transition: .3s;
	}

	.chartDialog ::v-deep .el-dialog {
		background: #fff;
	}
</style>
