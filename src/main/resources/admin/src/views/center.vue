<template>
	<div :style='{"padding":"50px 30px 30px"}'>
		<el-form
			:style='{"border":"4px solid #8ebc74","padding":"40px 30px","boxShadow":"none","borderRadius":"12px","flexWrap":"wrap","background":"#ffffff","display":"flex"}'
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			label-width="180px"
		>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}'   v-if="flag=='huanzhe'"  label="用户账号" prop="yonghuzhanghao">
					<el-input v-model="ruleForm.yonghuzhanghao" :readonly="ro.yonghuzhanghao" placeholder="用户账号" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}'   v-if="flag=='huanzhe'"  label="用户姓名" prop="yonghuxingming">
					<el-input v-model="ruleForm.yonghuxingming" :readonly="ro.yonghuxingming" placeholder="用户姓名" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='huanzhe'" label="头像" prop="touxiang">
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="1"
						:multiple="false"
						:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
						@change="huanzhetouxiangUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='huanzhe'"  label="性别" prop="xingbie">
					<el-select filterable v-model="ruleForm.xingbie" :disabled="ro.xingbie" placeholder="请选择性别">
						<el-option
							v-for="(item,index) in huanzhexingbieOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}'   v-if="flag=='huanzhe'"  label="年龄" prop="nianling">
					<el-input v-model="ruleForm.nianling" :readonly="ro.nianling" placeholder="年龄" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}'   v-if="flag=='huanzhe'"  label="联系方式" prop="lianxifangshi">
					<el-input v-model="ruleForm.lianxifangshi" :readonly="ro.lianxifangshi" placeholder="联系方式" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}'   v-if="flag=='huanzhe'"  label="家庭住址" prop="jiatingzhuzhi">
					<el-input v-model="ruleForm.jiatingzhuzhi" :readonly="ro.jiatingzhuzhi" placeholder="家庭住址" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}'   v-if="flag=='huanzhe'"  label="密保问题" prop="pquestion">
					<el-input v-model="ruleForm.pquestion" :readonly="ro.pquestion" placeholder="密保问题" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}'   v-if="flag=='huanzhe'"  label="密保答案" prop="panswer">
					<el-input v-model="ruleForm.panswer" :readonly="ro.panswer" placeholder="密保答案" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='huanzhe'" label="病史" prop="bingshi">
					<editor
						:disabled="ro.bingshi"
						myQuillEditor="bingshi"
						style="min-width: 200px; max-width: 600px;"
						:style='{"width":"100%","boxShadow":"none","height":"auto","maxWidth":"100% !important"}'
						v-model="ruleForm.bingshi" 
						class="editor" 
						action="file/upload">
					</editor>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}'   v-if="flag=='yisheng'"  label="医生工号" prop="yishenggonghao">
					<el-input v-model="ruleForm.yishenggonghao" :readonly="ro.yishenggonghao" placeholder="医生工号" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}'   v-if="flag=='yisheng'"  label="医生姓名" prop="yishengxingming">
					<el-input v-model="ruleForm.yishengxingming" :readonly="ro.yishengxingming" placeholder="医生姓名" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='yisheng'" label="头像" prop="touxiang">
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="1"
						:multiple="false"
						:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
						@change="yishengtouxiangUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='yisheng'" label="执行资格证" prop="zhixingzigezheng">
					<file-upload
						tip="点击上传执行资格证"
						action="file/upload"
						:limit="1"
						:type="3"
						:multiple="false"
						:fileUrls="ruleForm.zhixingzigezheng?ruleForm.zhixingzigezheng:''"
						@change="yishengzhixingzigezhengUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}'   v-if="flag=='yisheng'"  label="科室名称" prop="keshimingcheng">
					<el-input v-model="ruleForm.keshimingcheng" :readonly="ro.keshimingcheng" placeholder="科室名称" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='yisheng'"  label="职称" prop="zhicheng">
					<el-select filterable v-model="ruleForm.zhicheng" :disabled="ro.zhicheng" placeholder="请选择职称">
						<el-option
							v-for="(item,index) in yishengzhichengOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}'   v-if="flag=='yisheng'"  label="服务时间" prop="fuwushijian">
					<el-input v-model="ruleForm.fuwushijian" :readonly="ro.fuwushijian" placeholder="服务时间" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}'   v-if="flag=='yisheng'"  label="擅长领域" prop="shanzhanglingyu">
					<el-input v-model="ruleForm.shanzhanglingyu" :readonly="ro.shanzhanglingyu" placeholder="擅长领域" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}'   v-if="flag=='yisheng'"  label="职业履历" prop="zhiyelvli">
					<el-input v-model="ruleForm.zhiyelvli" :readonly="ro.zhiyelvli" placeholder="职业履历" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}'   v-if="flag=='yisheng'"  label="密保问题" prop="pquestion">
					<el-input v-model="ruleForm.pquestion" :readonly="ro.pquestion" placeholder="密保问题" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}'   v-if="flag=='yisheng'"  label="密保答案" prop="panswer">
					<el-input v-model="ruleForm.panswer" :readonly="ro.panswer" placeholder="密保答案" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='users'" label="用户名" prop="username">
					<el-input v-model="ruleForm.username" placeholder="用户名"></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"100%","margin":"0 0 20px 0"}' v-if="flag=='users'" label="头像" prop="image">
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="1"
						:multiple="false"
						:fileUrls="ruleForm.image?ruleForm.image:''"
						@change="usersimageUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"width":"100%","padding":"0 50px","margin":"30px auto 0","justifyContent":"flex-end","display":"flex"}'>
					<el-button class="btn3" type="primary" @click="onUpdateHandler">
						<span class="icon iconfont icon-queren15"></span>
						确定
					</el-button>
				</el-form-item>
		</el-form>
	</div>
</template>
<script>
// 校验引入
	import { 
		isIntNumer,
		isMobile,
	} from "@/utils/validate";

	export default {
		data() {
			return {
				ruleForm: {},
				ro: {},
				flag: '',
				usersFlag: false,
				huanzhexingbieOptions: [],
				yishengzhichengOptions: [],
			};
		},
		mounted() {
			var table = this.$storage.get("sessionTable");
			this.flag = table;
			this.$http({
				url: `${this.$storage.get("sessionTable")}/session`,
				method: "get"
			}).then(({ data }) => {
				if (data && data.code === 0) {
					if(table == 'huanzhe') {
						this.ro = {
							yonghuzhanghao: false,
							mima: false,
							yonghuxingming: false,
							touxiang: false,
							xingbie: false,
							nianling: false,
							lianxifangshi: false,
							jiatingzhuzhi: false,
							bingshi: false,
							pquestion: false,
							panswer: false,
						}
					}
					if(table == 'yisheng') {
						this.ro = {
							yishenggonghao: false,
							mima: false,
							yishengxingming: false,
							touxiang: false,
							zhixingzigezheng: false,
							keshimingcheng: false,
							zhicheng: false,
							fuwushijian: false,
							shanzhanglingyu: false,
							zhiyelvli: false,
							sfsh: false,
							shhf: false,
							pquestion: false,
							panswer: false,
						}
					}

					this.ruleForm = data.data;
				} else {
					this.$message.error(data.msg);
				}
			});
			this.huanzhexingbieOptions = "男,女".split(',')
			this.yishengzhichengOptions = "专家,主任医师,副主任医师,主治医师".split(',')
		},
		methods: {
			huanzhetouxiangUploadChange(fileUrls) {
				this.ruleForm.touxiang = fileUrls;
			},
			yishengtouxiangUploadChange(fileUrls) {
				this.ruleForm.touxiang = fileUrls;
			},
			yishengzhixingzigezhengUploadChange(fileUrls) {
				this.ruleForm.zhixingzigezheng = fileUrls;
			},
			usersimageUploadChange(fileUrls) {
				this.ruleForm.image = fileUrls;
			},
			onUpdateHandler() {
				if((!this.ruleForm.yonghuzhanghao)&& 'huanzhe'==this.flag){
					this.$message.error('用户账号不能为空');
					return
				}
				if(this.ruleForm.yonghuzhanghao && ((this.ruleForm.yonghuzhanghao).toString().length<3) && `huanzhe` == this.flag){
					this.$message.error(`用户账号长度不能小于3`);
					return
				}
				if(this.ruleForm.yonghuzhanghao && ((this.ruleForm.yonghuzhanghao).toString().length>16) && `huanzhe` == this.flag){
					this.$message.error(`用户账号长度不能大于16`);
					return
				}
				if((!this.ruleForm.mima)&& 'huanzhe'==this.flag){
					this.$message.error('密码不能为空');
					return
				}
				if((!this.ruleForm.yonghuxingming)&& 'huanzhe'==this.flag){
					this.$message.error('用户姓名不能为空');
					return
				}
				if(this.ruleForm.touxiang!=null) {
					this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
				}
				if('huanzhe' ==this.flag && this.ruleForm.nianling&&(!isIntNumer(this.ruleForm.nianling))){
					this.$message.error(`年龄应输入整数`);
					return
				}
				if('huanzhe' ==this.flag && this.ruleForm.lianxifangshi&&(!isMobile(this.ruleForm.lianxifangshi))){
					this.$message.error(`联系方式应输入手机格式`);
					return
				}
				if(this.ruleForm.touxiang!=null) {
					this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
				}
				if(this.ruleForm.zhixingzigezheng!=null) {
					this.ruleForm.zhixingzigezheng = this.ruleForm.zhixingzigezheng.replace(new RegExp(this.$base.url,"g"),"");
				}
				if('users'==this.flag && this.ruleForm.username.trim().length<1) {
					this.$message.error(`用户名不能为空`);
					return	
				}
				if(this.flag=='users'){
					this.ruleForm.image = this.ruleForm.image.replace(new RegExp(this.$base.url,"g"),"")
				}
				this.$http({
					url: `${this.$storage.get("sessionTable")}/update`,
					method: "post",
					data: this.ruleForm
				}).then(({ data }) => {
					if (data && data.code === 0) {
						if(this.flag=='users'){
							this.$storage.set('headportrait',this.ruleForm.image)
						}else {
							if(this.flag == 'huanzhe') {
								this.$storage.set('headportrait',this.ruleForm.touxiang)
							}
							if(this.flag == 'yisheng') {
								this.$storage.set('headportrait',this.ruleForm.touxiang)
							}
						}
						this.$message({
							message: "修改信息成功",
							type: "success",
							duration: 1500,
							onClose: () => {
								window.location.reload();
							}
						});
					} else {
						this.$message.error(data.msg);
					}
				});
			},
		}
	};
</script>
<style lang="scss" scoped>
	.el-date-editor.el-input {
		width: auto;
	}
	
	.add-update-preview .el-form-item ::v-deep .el-form-item__label {
				padding: 0 10px 0 0;
				color: #666;
				white-space: nowrap;
				font-weight: 500;
				width: 180px;
				font-size: 16px;
				line-height: 40px;
				text-align: right;
			}
	
	.add-update-preview .el-form-item ::v-deep .el-form-item__content {
		margin: 0 0 0 80px;
		display: flex;
		align-items: center;
	}
	
	.add-update-preview .el-input ::v-deep .el-input__inner {
				border-radius: 0px;
				padding: 0 12px;
				box-shadow: none;
				outline: none;
				color: #333;
				width: auto;
				font-size: 16px;
				border-color: #8ebc74;
				border-width: 1px;
				border-style: solid;
				min-width: 35%;
				height: 40px;
			}
	
	.add-update-preview .el-select ::v-deep .el-input__inner {
				border-radius: 0px;
				padding: 0 10px;
				box-shadow: none;
				outline: none;
				color: #333;
				width: 100%;
				font-size: 16px;
				border-color: #8ebc74;
				border-width: 1px;
				border-style: solid;
				height: 40px;
			}
	
	.add-update-preview .el-date-editor ::v-deep .el-input__inner {
				border-radius: 0px;
				padding: 0 10px 0 30px;
				box-shadow: none;
				outline: none;
				color: #333;
				width: 100%;
				font-size: 16px;
				border-color: #8ebc74;
				border-width: 1px;
				border-style: solid;
				height: 40px;
			}
	
	.add-update-preview ::v-deep .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview ::v-deep .el-upload-list .el-upload-list__item {
				border: 1px solid #8ebc74;
				cursor: pointer;
				border-radius: 0px;
				color: #8ebc74;
				width: 80px;
				font-size: 26px;
				line-height: 80px;
				text-align: center;
				height: 80px;
			}
	
	.add-update-preview ::v-deep .el-upload .el-icon-plus {
				border: 1px solid #8ebc74;
				cursor: pointer;
				border-radius: 0px;
				color: #8ebc74;
				width: 80px;
				font-size: 26px;
				line-height: 80px;
				text-align: center;
				height: 80px;
			}
	
	.add-update-preview .el-textarea ::v-deep .el-textarea__inner {
				border-radius: 0px;
				padding: 12px;
				box-shadow: none;
				outline: none;
				color: #666;
				width: auto;
				font-size: 16px;
				border-color: #8ebc74;
				border-width: 1px;
				border-style: solid;
				min-width: 80%;
				height: auto;
			}
	
	.add-update-preview .btn3 {
				border: 0;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 24px;
				margin: 4px;
				outline: none;
				color: #fff;
				background: #8ebc74;
				width: auto;
				font-size: 16px;
				min-width: 110px;
				height: 40px;
				.iconfont {
						margin: 0 2px;
						color: #fff;
						display: none;
						font-size: 16px;
						height: 40px;
					}
	}
	
	.add-update-preview .btn3:hover {
				opacity: 0.8;
			}
	.editor>.avatar-uploader {
		line-height: 0;
		height: 0;
	}
</style>
