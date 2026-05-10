




























<template>
	<div class="add-update-preview">
		<el-form
			class="add-update-form"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="200px"
			>
			<el-form-item class="add-item" label="就诊卡号" prop="jiuzhenkahao">
				<el-input v-model="ruleForm.jiuzhenkahao" placeholder="就诊卡号" disabled></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="用户账号" prop="yonghuzhanghao">
				<el-input v-model="ruleForm.yonghuzhanghao" 
					placeholder="用户账号" clearable :readonly="ro.yonghuzhanghao"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="用户姓名" prop="yonghuxingming">
				<el-input v-model="ruleForm.yonghuxingming" 
					placeholder="用户姓名" clearable :readonly="ro.yonghuxingming"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="医生工号" prop="yishenggonghao">
				<el-input v-model="ruleForm.yishenggonghao" 
					placeholder="医生工号" clearable :readonly="ro.yishenggonghao"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="医生姓名" prop="yishengxingming">
				<el-input v-model="ruleForm.yishengxingming" 
					placeholder="医生姓名" clearable :readonly="ro.yishengxingming"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="头像" v-if="type!='cross' || (type=='cross' && !ro.touxiang)" prop="touxiang">
				<file-upload
					tip="点击上传头像"
					action="file/upload"
					:limit="3"
					:multiple="true"
					:disabled="ro.touxiang"
					:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
					@change="touxiangUploadChange"
					></file-upload>
			</el-form-item>
			<el-form-item class="add-item" v-else label="头像" prop="touxiang">
				<img v-if="ruleForm.touxiang.substring(0,4)=='http'" class="upload-img" v-bind:key="index" :src="ruleForm.touxiang.split(',')[0]">
				<img v-else class="upload-img" v-bind:key="index" v-for="(item,index) in ruleForm.touxiang.split(',')" :src="baseUrl+item">
			</el-form-item>
			<el-form-item class="add-item" label="病人主诉" prop="bingrenzhusu">
				<el-input
					type="textarea"
					:rows="8"
					:disabled="ro.bingrenzhusu"
					placeholder="病人主诉"
					v-model="ruleForm.bingrenzhusu">
					</el-input>
			</el-form-item>
			<el-form-item class="add-item" label="既往史" prop="jiwangshi">
				<el-input
					type="textarea"
					:rows="8"
					:disabled="ro.jiwangshi"
					placeholder="既往史"
					v-model="ruleForm.jiwangshi">
					</el-input>
			</el-form-item>
			<el-form-item class="add-item" label="药敏史" prop="yaominshi">
				<el-input
					type="textarea"
					:rows="8"
					:disabled="ro.yaominshi"
					placeholder="药敏史"
					v-model="ruleForm.yaominshi">
					</el-input>
			</el-form-item>
			<el-form-item class="add-item" label="诊断结果" prop="zhenduanjieguo">
				<el-input
					type="textarea"
					:rows="8"
					:disabled="ro.zhenduanjieguo"
					placeholder="诊断结果"
					v-model="ruleForm.zhenduanjieguo">
					</el-input>
			</el-form-item>
			<el-form-item class="add-item" label="治疗方案" prop="zhiliaofangan">
				<el-input
					type="textarea"
					:rows="8"
					:disabled="ro.zhiliaofangan"
					placeholder="治疗方案"
					v-model="ruleForm.zhiliaofangan">
					</el-input>
			</el-form-item>
			<el-form-item class="add-item" label="健康建议" prop="jiankangjianyi">
				<el-input
					type="textarea"
					:rows="8"
					:disabled="ro.jiankangjianyi"
					placeholder="健康建议"
					v-model="ruleForm.jiankangjianyi">
					</el-input>
			</el-form-item>
			<el-form-item class="add-item" label="处方信息" prop="chufangxinxi">
				<el-input
					type="textarea"
					:rows="8"
					:disabled="ro.chufangxinxi"
					placeholder="处方信息"
					v-model="ruleForm.chufangxinxi">
					</el-input>
			</el-form-item>
			<el-form-item class="add-item" label="医嘱" prop="yizhu">
				<el-input
					type="textarea"
					:rows="8"
					:disabled="ro.yizhu"
					placeholder="医嘱"
					v-model="ruleForm.yizhu">
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
					jiuzhenkahao : false,
					yonghuzhanghao : false,
					yonghuxingming : false,
					bingrenzhusu : false,
					jiwangshi : false,
					yaominshi : false,
					zhenduanjieguo : false,
					zhiliaofangan : false,
					jiankangjianyi : false,
					chufangxinxi : false,
					yizhu : false,
					yishenggonghao : false,
					yishengxingming : false,
					touxiang : false,
				},
				type: '',
				userTableName: localStorage.getItem('UserTableName'),
				ruleForm: {
					jiuzhenkahao: this.getUUID(),
					yonghuzhanghao: '',
					yonghuxingming: '',
					bingrenzhusu: '',
					jiwangshi: '',
					yaominshi: '',
					zhenduanjieguo: '',
					zhiliaofangan: '',
					jiankangjianyi: '',
					chufangxinxi: '',
					yizhu: '',
					yishenggonghao: '',
					yishengxingming: '',
					touxiang: '',
				},

				rules: {
					jiuzhenkahao: [
					],
					yonghuzhanghao: [
					],
					yonghuxingming: [
					],
					bingrenzhusu: [
					],
					jiwangshi: [
					],
					yaominshi: [
					],
					zhenduanjieguo: [
					],
					zhiliaofangan: [
					],
					jiankangjianyi: [
					],
					chufangxinxi: [
					],
					yizhu: [
					],
					yishenggonghao: [
					],
					yishengxingming: [
					],
					touxiang: [
					],
				},
				centerType: false,
			};
		},
		computed: {
			sessionForm() {
				return JSON.parse(localStorage.getItem('sessionForm'))
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
						if(o=='jiuzhenkahao'){
							this.ruleForm.jiuzhenkahao = obj[o];
							this.ro.jiuzhenkahao = true;
							continue;
						}
						if(o=='yonghuzhanghao'){
							this.ruleForm.yonghuzhanghao = obj[o];
							this.ro.yonghuzhanghao = true;
							continue;
						}
						if(o=='yonghuxingming'){
							this.ruleForm.yonghuxingming = obj[o];
							this.ro.yonghuxingming = true;
							continue;
						}
						if(o=='bingrenzhusu'){
							this.ruleForm.bingrenzhusu = obj[o];
							this.ro.bingrenzhusu = true;
							continue;
						}
						if(o=='jiwangshi'){
							this.ruleForm.jiwangshi = obj[o];
							this.ro.jiwangshi = true;
							continue;
						}
						if(o=='yaominshi'){
							this.ruleForm.yaominshi = obj[o];
							this.ro.yaominshi = true;
							continue;
						}
						if(o=='zhenduanjieguo'){
							this.ruleForm.zhenduanjieguo = obj[o];
							this.ro.zhenduanjieguo = true;
							continue;
						}
						if(o=='zhiliaofangan'){
							this.ruleForm.zhiliaofangan = obj[o];
							this.ro.zhiliaofangan = true;
							continue;
						}
						if(o=='jiankangjianyi'){
							this.ruleForm.jiankangjianyi = obj[o];
							this.ro.jiankangjianyi = true;
							continue;
						}
						if(o=='chufangxinxi'){
							this.ruleForm.chufangxinxi = obj[o];
							this.ro.chufangxinxi = true;
							continue;
						}
						if(o=='yizhu'){
							this.ruleForm.yizhu = obj[o];
							this.ro.yizhu = true;
							continue;
						}
						if(o=='yishenggonghao'){
							this.ruleForm.yishenggonghao = obj[o];
							this.ro.yishenggonghao = true;
							continue;
						}
						if(o=='yishengxingming'){
							this.ruleForm.yishengxingming = obj[o];
							this.ro.yishengxingming = true;
							continue;
						}
						if(o=='touxiang'){
							this.ruleForm.touxiang = obj[o]?obj[o].split(",")[0]:'';
							this.ro.touxiang = true;
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
						if((json.yishenggonghao!=''&&json.yishenggonghao) || json.yishenggonghao==0){
							this.ruleForm.yishenggonghao = json.yishenggonghao;
							this.ro.yishenggonghao = true;
						}
						if((json.yishengxingming!=''&&json.yishengxingming) || json.yishengxingming==0){
							this.ruleForm.yishengxingming = json.yishengxingming;
							this.ro.yishengxingming = true;
						}
						if((json.touxiang!=''&&json.touxiang) || json.touxiang==0){
							this.ruleForm.touxiang = json.touxiang;
							this.ro.touxiang = true;
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
				await this.$http.get(`zhenliaofangan/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.ruleForm = res.data.data;
					}
				});
			},
			// 提交
			async onSubmit(subMitType=null) {
				await this.$refs["ruleForm"].validate(async valid => {
					if(valid) {
						if(this.ruleForm.jiuzhenkahao){
							this.ruleForm.jiuzhenkahao = String(this.ruleForm.jiuzhenkahao)
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

						await this.$http.post(`zhenliaofangan/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(async res => {
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
			touxiangUploadChange(fileUrls) {
				this.ruleForm.touxiang = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
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
