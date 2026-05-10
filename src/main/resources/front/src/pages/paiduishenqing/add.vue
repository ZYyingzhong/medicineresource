
























<template>
	<div class="add-update-preview">
		<el-form
			class="add-update-form"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="200px"
			>
			<el-form-item class="add-item" label="申请编号" prop="shenqingbianhao">
				<el-input v-model="ruleForm.shenqingbianhao" placeholder="申请编号" disabled></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="资源名称" prop="ziyuanmingcheng">
				<el-input v-model="ruleForm.ziyuanmingcheng" 
					placeholder="资源名称" clearable :readonly="ro.ziyuanmingcheng"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="资源图片" v-if="type!='cross' || (type=='cross' && !ro.ziyuantupian)" prop="ziyuantupian">
				<file-upload
					tip="点击上传资源图片"
					action="file/upload"
					:limit="3"
					:multiple="true"
					:disabled="ro.ziyuantupian"
					:fileUrls="ruleForm.ziyuantupian?ruleForm.ziyuantupian:''"
					@change="ziyuantupianUploadChange"
					></file-upload>
			</el-form-item>
			<el-form-item class="add-item" v-else label="资源图片" prop="ziyuantupian">
				<img v-if="ruleForm.ziyuantupian.substring(0,4)=='http'" class="upload-img" v-bind:key="index" :src="ruleForm.ziyuantupian.split(',')[0]">
				<img v-else class="upload-img" v-bind:key="index" v-for="(item,index) in ruleForm.ziyuantupian.split(',')" :src="baseUrl+item">
			</el-form-item>
			<el-form-item class="add-item" label="资源分类" prop="ziyuanfenlei">
				<el-input v-model="ruleForm.ziyuanfenlei" 
					placeholder="资源分类" clearable :readonly="ro.ziyuanfenlei"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="资源价格" prop="ziyuanjiage">
				<el-input-number v-model="ruleForm.ziyuanjiage" placeholder="资源价格" :disabled="ro.ziyuanjiage"></el-input-number>
			</el-form-item>
			<el-form-item class="add-item" label="排队人数" prop="paiduirenshu">
				<el-input v-model.number="ruleForm.paiduirenshu" 
					placeholder="排队人数" clearable :readonly="ro.paiduirenshu"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="排队序号" prop="paiduixuhao">
				<el-input v-model="paiduixuhao" placeholder="排队序号" disabled></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="申请时间" prop="shenqingshijian">
				<el-date-picker
					:disabled="ro.shenqingshijian"
					value-format="yyyy-MM-dd HH:mm:ss"
					v-model="ruleForm.shenqingshijian" 
					type="datetime"
					placeholder="申请时间">
				</el-date-picker>
			</el-form-item>
			<el-form-item class="add-item" label="申请原因" prop="shenqingyuanyin">
				<el-input
					type="textarea"
					:rows="8"
					:disabled="ro.shenqingyuanyin"
					placeholder="申请原因"
					v-model="ruleForm.shenqingyuanyin">
					</el-input>
			</el-form-item>

			<el-form-item class="add-btn-item">
				<el-button class="submitBtn"  type="primary" @click="onSubmit(null)">
					<span class="icon iconfont icon-xiugai2"></span>
					<span class="text">提交信息</span>
				</el-button>
				<el-button class="closeBtn" @click="back()">
					<span class="icon iconfont icon-shanchu7"></span>
					<span class="text">取消</span>
				</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				id: '',
				baseUrl: '',
				ro:{
					shenqingbianhao : false,
					ziyuanbianhao : false,
					ziyuanmingcheng : false,
					ziyuantupian : false,
					ziyuanfenlei : false,
					ziyuanjiage : false,
					paiduirenshu : false,
					paiduixuhao : false,
					shenqingyuanyin : false,
					yonghuzhanghao : false,
					yishenggonghao : false,
					shenqingshijian : false,
				},
				type: '',
				userTableName: localStorage.getItem('UserTableName'),
				ruleForm: {
					shenqingbianhao: this.getUUID(),
					ziyuanbianhao: '',
					ziyuanmingcheng: '',
					ziyuantupian: '',
					ziyuanfenlei: '',
					ziyuanjiage: '',
					paiduirenshu: '',
					paiduixuhao: '',
					shenqingyuanyin: '',
					yonghuzhanghao: '',
					yishenggonghao: '',
					shenqingshijian: '',
				},

				rules: {
					shenqingbianhao: [
					],
					ziyuanbianhao: [
					],
					ziyuanmingcheng: [
					],
					ziyuantupian: [
					],
					ziyuanfenlei: [
					],
					ziyuanjiage: [
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					paiduirenshu: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
						{ validator: (rule, val, cb) => 
							val >= 0 ? cb() : cb('范围需大于等于0')
						},
					],
					paiduixuhao: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
						{ validator: (rule, val, cb) => 
							val >= 0 ? cb() : cb('范围需大于等于0')
						},
					],
					shenqingyuanyin: [
						{ required: true, message: '申请原因不能为空', trigger: 'blur' },
					],
					yonghuzhanghao: [
					],
					yishenggonghao: [
					],
					shenqingshijian: [
					],
				},
				centerType: false,
			};
		},
		computed: {
			sessionForm() {
				return JSON.parse(localStorage.getItem('sessionForm'))
			},
			paiduixuhao : {
				get: function () {
					let c = this.ruleForm
					let a = c.paiduirenshu+1
					this.ruleForm.paiduixuhao = a?Number(Number(a).toFixed(2)):0
					return a?Number(Number(a).toFixed(2)):0
				}
				
			},



		},
		components: {
		},
		created() {
			if(this.$route.query.centerType){
				this.centerType = true
			}
			//this.bg();
			let type = this.$route.query.type ? this.$route.query.type : '';
			this.init(type);
			this.baseUrl = this.$config.baseUrl;
			this.ruleForm.shenqingshijian = this.getCurDateTime()
		},
		methods: {
			getMakeZero(s) {
				return s < 10 ? '0' + s : s;
			},
			// 下载
			download(file ){
				window.open(`${file}`)
			},
			// 初始化
			init(type) {
				this.type = type;
				if(type=='cross'){
					var obj = JSON.parse(localStorage.getItem('crossObj'));
					for (var o in obj){
						if(o=='shenqingbianhao'){
							this.ruleForm.shenqingbianhao = obj[o];
							this.ro.shenqingbianhao = true;
							continue;
						}
						if(o=='ziyuanbianhao'){
							this.ruleForm.ziyuanbianhao = obj[o];
							this.ro.ziyuanbianhao = true;
							continue;
						}
						if(o=='ziyuanmingcheng'){
							this.ruleForm.ziyuanmingcheng = obj[o];
							this.ro.ziyuanmingcheng = true;
							continue;
						}
						if(o=='ziyuantupian'){
							this.ruleForm.ziyuantupian = obj[o]?obj[o].split(",")[0]:'';
							this.ro.ziyuantupian = true;
							continue;
						}
						if(o=='ziyuanfenlei'){
							this.ruleForm.ziyuanfenlei = obj[o];
							this.ro.ziyuanfenlei = true;
							continue;
						}
						if(o=='ziyuanjiage'){
							this.ruleForm.ziyuanjiage = obj[o];
							this.ro.ziyuanjiage = true;
							continue;
						}
						if(o=='paiduirenshu'){
							this.ruleForm.paiduirenshu = obj[o];
							this.ro.paiduirenshu = true;
							continue;
						}
						if(o=='paiduixuhao'){
							this.ruleForm.paiduixuhao = obj[o];
							this.ro.paiduixuhao = true;
							continue;
						}
						if(o=='shenqingyuanyin'){
							this.ruleForm.shenqingyuanyin = obj[o];
							this.ro.shenqingyuanyin = true;
							continue;
						}
						if(o=='yonghuzhanghao'){
							this.ruleForm.yonghuzhanghao = obj[o];
							this.ro.yonghuzhanghao = true;
							continue;
						}
						if(o=='yishenggonghao'){
							this.ruleForm.yishenggonghao = obj[o];
							this.ro.yishenggonghao = true;
							continue;
						}
						if(o=='shenqingshijian'){
							this.ruleForm.shenqingshijian = obj[o];
							this.ro.shenqingshijian = true;
							continue;
						}
					}
				}else if(type=='edit'){
					this.info()
				}
				// 获取用户信息
				this.$http.get(this.userTableName + '/session', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						var json = res.data.data;
						if((json.yonghuzhanghao!=''&&json.yonghuzhanghao) || json.yonghuzhanghao==0){
							this.ruleForm.yonghuzhanghao = json.yonghuzhanghao;
							this.ro.yonghuzhanghao = true;
						}
						if((json.yishenggonghao!=''&&json.yishenggonghao) || json.yishenggonghao==0){
							this.ruleForm.yishenggonghao = json.yishenggonghao;
							this.ro.yishenggonghao = true;
						}
					}
				});

				if (localStorage.getItem('raffleType') && localStorage.getItem('raffleType') != null) {
					localStorage.removeItem('raffleType')
					setTimeout(() => {
						this.onSubmit(null)
					}, 300)
				}
			},

			// 多级联动参数
			// 多级联动参数
			async info() {
				await this.$http.get(`paiduishenqing/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.ruleForm = res.data.data;
					}
				});
			},
			// 提交
			async onSubmit(subMitType=null) {
				await this.$refs["ruleForm"].validate(async valid => {
					if(valid) {
						if(this.ruleForm.paiduixuhao==0){
							this.$message.error('排队序号不能为空')
							return false
						}
						if(this.ruleForm.shenqingbianhao){
							this.ruleForm.shenqingbianhao = String(this.ruleForm.shenqingbianhao)
						}
						if(!this.ruleForm.id) {
							delete this.ruleForm.userid
						}
						if(this.type=='cross'){
							var statusColumnName = localStorage.getItem('statusColumnName');
							var statusColumnValue = localStorage.getItem('statusColumnValue');
							if(statusColumnName && statusColumnName!='') {
								var obj = JSON.parse(localStorage.getItem('crossObj'));
								if(!statusColumnName.startsWith("[")) {
									for (var o in obj){
										if(o==statusColumnName){
											obj[o] = statusColumnValue;
										}
									}
									var table = localStorage.getItem('crossTable');
									await this.$http.post(table+'/update', obj).then(res => {});
								}
							}
						}

						let changeType = true
						let errMsg = ''
						let hasBackType = false
						if(this.type=='cross') {
							await this.$http.post('updateColumn/jinqueziyuan/2',{
								csuUpdateColumn: 'paiduirenshu',
								csuUpdateColumnValue: '+1',
								csuConditionColumn: 'ziyuanbianhao',
								csuConditionColumnValue: this.ruleForm.ziyuanbianhao
							}).then(rs=>{
								if(rs.data.code!=0) {
									changeType = false
									errMsg = '排队失败'
								}
							},err=>{
								changeType = false
								errMsg = '排队失败'
							})
							if(!changeType) {
								this.$message.error(errMsg);
								return false
							}
						}
						await this.$http.post(`paiduishenqing/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(async res => {
							if (res.data.code == 0) {
								await this.$message({
									message: '操作成功',
									type: 'success',
									duration: 1500,
									onClose: () => {
										this.$router.go(-1);
										
									}
								});
							} else {
								this.$message({
									message: res.data.msg,
									type: 'error',
									duration: 1500
								});
							}
						});
					}
				});
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 返回
			back() {
				this.$router.go(-1);
			},
			ziyuantupianUploadChange(fileUrls) {
				this.ruleForm.ziyuantupian = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
			},
		}
	};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.add-update-preview {
		padding: 20px 7%;
		margin: 10px auto;
		background: none;
		width: 100%;
		position: relative;
		.add-update-form {
			border-radius: 10px;
			padding: 40px 20% 20px 10%;
			background: #fff;
			width: 100%;
			position: relative;
			.add-item.el-form-item {
				padding: 0;
				margin: 0 0 20px;
				background: none;
				::v-deep .el-form-item__label {
					padding: 0 10px 0 0;
					color: #333;
					font-weight: 500;
					width: 200px;
					font-size: 16px;
					line-height: 40px;
					text-align: right;
				}
				::v-deep .el-form-item__content {
					margin-left: 200px;
				}
				.el-input {
					width: 100%;
				}
				.el-input ::v-deep .el-input__inner {
					border: 1px solid #ddd;
					border-radius: 4px;
					padding: 0 12px;
					box-shadow: none;
					outline: none;
					color: #666;
					width: 100%;
					font-size: 16px;
					height: 40px;
				}
				.el-input ::v-deep .el-input__inner[readonly="readonly"] {
					border: 0;
					cursor: not-allowed;
					border-radius: 4px;
					padding: 0 12px;
					box-shadow: none;
					outline: none;
					color: rgba(85, 85, 127, 1.0);
					width: 100%;
					font-size: 16px;
					height: 40px;
				}
				.el-input-number ::v-deep .el-input__inner {
					text-align: left;
					border: 1px solid #ddd;
					border-radius: 4px;
					padding: 0 12px;
					box-shadow: none;
					outline: none;
					color: #666;
					width: 100%;
					font-size: 16px;
					height: 40px;
				}
				.el-input-number ::v-deep .is-disabled .el-input__inner {
					text-align: left;
					border: 0;
					cursor: not-allowed;
					border-radius: 4px;
					padding: 0 12px;
					box-shadow: none;
					outline: none;
					color: rgba(85, 85, 127, 1.0);
					width: 100%;
					font-size: 16px;
					height: 40px;
				}
				.el-input-number ::v-deep .el-input-number__decrease {
					display: none;
				}
				.el-input-number ::v-deep .el-input-number__increase {
					display: none;
				}
				.el-select {
					width: 100%;
				}
				.el-select ::v-deep .el-input__inner {
					border: 1px solid #ddd;
					border-radius: 4px;
					padding: 0 10px;
					box-shadow: none;
					outline: none;
					color: rgba(64, 158, 255, 1);
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				.el-select ::v-deep .is-disabled .el-input__inner {
					border: 0;
					cursor: not-allowed;
					border-radius: 4px;
					padding: 0 10px;
					box-shadow: none;
					outline: none;
					color: rgba(85, 85, 127, 1.0);
					background: #eee;
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				.el-date-editor {
					width: 100%;
				}
				.el-date-editor ::v-deep .el-input__inner {
					border: 1px solid #ddd;
					border-radius: 4px;
					padding: 0 10px 0 30px;
					box-shadow: none;
					outline: none;
					color: #666;
					width: 100%;
					font-size: 16px;
					height: 40px;
				}
				.el-date-editor ::v-deep .el-input__inner[readonly="readonly"] {
					border: 0;
					cursor: not-allowed;
					border-radius: 4px;
					padding: 0 10px 0 30px;
					box-shadow: none;
					outline: none;
					color: #666;
					background: #eee;
					width: 100%;
					font-size: 16px;
					height: 40px;
				}
				::v-deep .el-upload--picture-card {
					background: transparent;
					border: 0;
					border-radius: 0;
					width: auto;
					height: auto;
					line-height: initial;
					vertical-align: middle;
				}
				::v-deep .upload .upload-img {
					border: 1px solid #ddd;
					cursor: pointer;
					border-radius: 6px;
					color: #999;
					width: 100px;
					font-size: 32px;
					line-height: 100px;
					text-align: center;
					height: 100px;
				}
				::v-deep .el-upload-list .el-upload-list__item {
					border: 1px solid #ddd;
					cursor: pointer;
					border-radius: 6px;
					color: #999;
					width: 100px;
					font-size: 32px;
					line-height: 100px;
					text-align: center;
					height: 100px;
					font-size: 14px;
					line-height: 1.8;
				}
				::v-deep .el-upload .el-icon-plus {
					border: 1px solid #ddd;
					cursor: pointer;
					border-radius: 6px;
					color: #999;
					width: 100px;
					font-size: 32px;
					line-height: 100px;
					text-align: center;
					height: 100px;
				}
				::v-deep .el-upload__tip {
					color: #666;
					font-size: 16px;
				}
				.el-textarea ::v-deep .el-textarea__inner {
					border: 1px solid #ddd;
					border-radius: 4px;
					padding: 12px;
					box-shadow: none;
					outline: none;
					color: #666;
					width: 100%;
					font-size: 16px;
					height: auto;
				}
				.el-textarea ::v-deep .el-textarea__inner[readonly="readonly"] {
					border: 0;
					cursor: not-allowed;
					border-radius: 4px;
					padding: 12px;
					box-shadow: none;
					outline: none;
					color: rgba(85, 85, 127, 1.0);
					width: 100%;
					font-size: 16px;
					height: auto;
				}
				::v-deep .el-input__inner::placeholder {
					color: #123;
					font-size: 16px;
				}
				::v-deep textarea::placeholder {
					color: #123;
					font-size: 16px;
				}
				.editor {
					background-color: #fff;
					border-radius: 0;
					padding: 0;
					box-shadow: none;
					margin: 0;
					width: 100%;
					border-color: #ccc;
					border-width: 0;
					border-style: solid;
					height: auto;
				}
				.editor ::v-deep.ql-toolbar {
					background: none;
				}
				.editor ::v-deep.ql-container {
					background: none;
					min-height: 180px;
				}
				.editor ::v-deep.ql-container .ql-blank::before {
					color: #000;
				}
				.upload-img {
					object-fit: cover;
					width: 120px;
					height: 120px;
				}
				.viewBtn {
					border: 0;
					cursor: pointer;
					padding: 0 20px;
					margin: 0;
					color: #fff;
					display: inline-block;
					font-size: 14px;
					line-height: 34px;
					border-radius: 4px;
					outline: none;
					background: rgb(167, 5, 0);
					width: auto;
					height: 34px;
				}
				.viewBtn:hover {
					opacity: 0.7;
				}
				.unviewBtn {
					border: 0;
					cursor: pointer;
					padding: 0 20px;
					margin: 0;
					color: #fff;
					display: inline-block;
					font-size: 14px;
					line-height: 34px;
					border-radius: 4px;
					outline: none;
					background: #999;
					width: auto;
					height: 34px;
				}
				.unviewBtn:hover {
					opacity: 0.8;
				}
			}
			.add-btn-item {
				padding: 0;
				margin: 20px auto;
				width: 100%;
				text-align: center;
				.submitBtn {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 15px;
					margin: 0 20px 0 0;
					outline: none;
					background: rgb(167, 5, 0);
					display: inline-block;
					width: auto;
					font-size: 14px;
					line-height: 40px;
					height: 40px;
					.icon {
						color: rgba(255, 255, 255, 1);
					}
					.text {
						color: rgba(255, 255, 255, 1);
						font-size: 16px;
					}
				}
				.submitBtn:hover {
					opacity: 0.7;
					.icon {
						color: #fff;
					}
					.text {
						color: #fff;
					}
				}
				.closeBtn {
					border: 0;
					cursor: pointer;
					padding: 0 15px;
					margin: 0 20px 0 0;
					display: inline-block;
					font-size: 16px;
					line-height: 40px;
					border-radius: 4px;
					outline: none;
					background: #999;
					width: auto;
					min-width: 100px;
					height: 40px;
					.icon {
						color: #fff;
						font-size: 18px;
					}
					.text {
						color: #fff;
						font-size: 16px;
					}
				}
				.closeBtn:hover {
					opacity: 0.7;
					.icon {
						color: #fff;
					}
					.text {
						color: #fff;
					}
				}
			}
		}
	}
	.el-date-editor.el-input {
		width: auto;
	}
</style>
