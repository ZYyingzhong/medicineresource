<template>
	<div>
		<div class="register-container" :style="{'backgroundImage': indexBgUrl?`url(${$base.url + indexBgUrl})`:''}">
			<el-form v-if="pageFlag=='register'" ref="ruleForm" class="rgs-form animate__animated animate__" :model="ruleForm" :rules="rules">
				<div class="rgs-form2">
					<div class="title">基于springboot的医疗资源分享平台的设计与实现</div>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'">
						<div class="lable" :class="changeRules('yonghuzhanghao')?'required':''">用户账号：</div>
						<el-input  v-model="ruleForm.yonghuzhanghao" :readonly="ro.yonghuzhanghao" autocomplete="off" placeholder="用户账号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'">
						<div class="lable" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.mima" :readonly="ro.mima" autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'">
						<div class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" :readonly="ro.mima" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'">
						<div class="lable" :class="changeRules('yonghuxingming')?'required':''">用户姓名：</div>
						<el-input  v-model="ruleForm.yonghuxingming" :readonly="ro.yonghuxingming" autocomplete="off" placeholder="用户姓名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'">
						<div class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
							@change="huanzhetouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'">
						<div class="lable" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select filterable v-model="ruleForm.xingbie" placeholder="请选择性别" :disabled="ro.xingbie">
							<el-option
								v-for="(item,index) in huanzhexingbieOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'">
						<div class="lable" :class="changeRules('nianling')?'required':''">年龄：</div>
						<el-input  v-model.number="ruleForm.nianling" :readonly="ro.nianling" autocomplete="off" placeholder="年龄"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'">
						<div class="lable" :class="changeRules('lianxifangshi')?'required':''">联系方式：</div>
						<el-input  v-model="ruleForm.lianxifangshi" :readonly="ro.lianxifangshi" autocomplete="off" placeholder="联系方式"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'">
						<div class="lable" :class="changeRules('jiatingzhuzhi')?'required':''">家庭住址：</div>
						<el-input  v-model="ruleForm.jiatingzhuzhi" :readonly="ro.jiatingzhuzhi" autocomplete="off" placeholder="家庭住址"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'">
						<div class="lable" :class="changeRules('pquestion')?'required':''">密保问题：</div>
						<el-input  v-model="ruleForm.pquestion" :readonly="ro.pquestion" autocomplete="off" placeholder="密保问题"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'">
						<div class="lable" :class="changeRules('panswer')?'required':''">密保答案：</div>
						<el-input  v-model="ruleForm.panswer" :readonly="ro.panswer" autocomplete="off" placeholder="密保答案"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'">
						<div class="lable" :class="changeRules('bingshi')?'required':''">病史：</div>
						<editor
							:disabled="ro.bingshi"
							myQuillEditor="bingshi"
							v-model="ruleForm.bingshi" 
							class="editor" 
							action="file/upload">
						</editor>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'">
						<div class="lable" :class="changeRules('yishenggonghao')?'required':''">医生工号：</div>
						<el-input  v-model="ruleForm.yishenggonghao" :readonly="ro.yishenggonghao" autocomplete="off" placeholder="医生工号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'">
						<div class="lable" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.mima" :readonly="ro.mima" autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'">
						<div class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" :readonly="ro.mima" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'">
						<div class="lable" :class="changeRules('yishengxingming')?'required':''">医生姓名：</div>
						<el-input  v-model="ruleForm.yishengxingming" :readonly="ro.yishengxingming" autocomplete="off" placeholder="医生姓名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'">
						<div class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
							@change="yishengtouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'">
						<div class="lable" :class="changeRules('zhixingzigezheng')?'required':''">执行资格证：</div>
						<file-upload
							tip="点击上传执行资格证"
							action="file/upload"
							:limit="3"
							:type="3"
							:multiple="true"
							:fileUrls="ruleForm.zhixingzigezheng?ruleForm.zhixingzigezheng:''"
							@change="yishengzhixingzigezhengUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'">
						<div class="lable" :class="changeRules('keshimingcheng')?'required':''">科室名称：</div>
						<el-input  v-model="ruleForm.keshimingcheng" :readonly="ro.keshimingcheng" autocomplete="off" placeholder="科室名称"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'">
						<div class="lable" :class="changeRules('zhicheng')?'required':''">职称：</div>
						<el-select filterable v-model="ruleForm.zhicheng" placeholder="请选择职称" :disabled="ro.zhicheng">
							<el-option
								v-for="(item,index) in yishengzhichengOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'">
						<div class="lable" :class="changeRules('fuwushijian')?'required':''">服务时间：</div>
						<el-input  v-model="ruleForm.fuwushijian" :readonly="ro.fuwushijian" autocomplete="off" placeholder="服务时间"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'">
						<div class="lable" :class="changeRules('pquestion')?'required':''">密保问题：</div>
						<el-input  v-model="ruleForm.pquestion" :readonly="ro.pquestion" autocomplete="off" placeholder="密保问题"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'">
						<div class="lable" :class="changeRules('panswer')?'required':''">密保答案：</div>
						<el-input  v-model="ruleForm.panswer" :readonly="ro.panswer" autocomplete="off" placeholder="密保答案"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'">
						<div class="lable" :class="changeRules('shanzhanglingyu')?'required':''">擅长领域：</div>
						<el-input type="textarea" v-model="ruleForm.shanzhanglingyu" autocomplete="off" placeholder="擅长领域" :readonly="ro.shanzhanglingyu" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'">
						<div class="lable" :class="changeRules('zhiyelvli')?'required':''">职业履历：</div>
						<el-input type="textarea" v-model="ruleForm.zhiyelvli" autocomplete="off" placeholder="职业履历" :readonly="ro.zhiyelvli" />
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<button type="button" class="r-btn" @click="login()">注册</button>
						</div>
						<div class="register-btn2">
							<div class="r-login" @click="close()">已有账号，直接登录</div>
						</div>
					</div>
				</div>
			</el-form>
		</div>
	</div>
</template>

<script>
	import 'animate.css'
export default {
	data() {
		return {
			ruleForm: {
			},
			forgetForm: {},
            pageFlag : '',
			tableName:"",
			rules: {},
			ro: {},
            huanzhexingbieOptions: [],
            yishengzhichengOptions: [],
			indexBgUrl: '',
		};
	},
	mounted(){
		this.pageFlag = this.$route.query.pageFlag
		if(this.$route.query.pageFlag=='register'){
			
			let table = this.$storage.get("loginTable");
			this.tableName = table;
			if(this.tableName=='huanzhe'){
				this.ruleForm = {
					yonghuzhanghao: '',
					mima: '',
					yonghuxingming: '',
					touxiang: '',
					xingbie: '',
					nianling: '',
					lianxifangshi: '',
					jiatingzhuzhi: '',
					bingshi: '',
					pquestion: '',
					panswer: '',
				}
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
			if(this.tableName=='yisheng'){
				this.ruleForm = {
					yishenggonghao: '',
					mima: '',
					yishengxingming: '',
					touxiang: '',
					zhixingzigezheng: '',
					keshimingcheng: '',
					zhicheng: '',
					fuwushijian: '',
					shanzhanglingyu: '',
					zhiyelvli: '',
					sfsh: '',
					shhf: '',
					pquestion: '',
					panswer: '',
				}
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
			if ('huanzhe' == this.tableName) {
				this.rules.yonghuzhanghao = [{ required: true, message: '请输入用户账号', trigger: 'blur' }]
			}
			if ('huanzhe' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('huanzhe' == this.tableName) {
				this.rules.yonghuxingming = [{ required: true, message: '请输入用户姓名', trigger: 'blur' }]
			}
			this.huanzhexingbieOptions = "男,女".split(',')
			this.yishengzhichengOptions = "专家,主任医师,副主任医师,主治医师".split(',')
		}
	},
	created() {
		this.$http.get('config/info?name=bRegisterBackgroundImg',).then(rs=>{this.indexBgUrl = rs.data.data?rs.data.data.value:''})
	},
	destroyed() {
		  	},
	methods: {
		changeRules(name){
			if(this.rules[name]){
				return true
			}
			if(name == 'pquestion'||name=='panswer') {
				return true
			}
			return false
		},
		// 获取uuid
		getUUID () {
			return new Date().getTime();
		},
		close(){
			this.$router.push({ path: "/login" });
		},
        huanzhetouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },
        yishengtouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },
        yishengzhixingzigezhengUploadChange(fileUrls) {
            this.ruleForm.zhixingzigezheng = fileUrls;
        },

        // 多级联动参数


		// 注册
		login() {
			var url=this.tableName+"/register";
			if((!this.ruleForm.yonghuzhanghao) && `huanzhe` == this.tableName){
				this.$message.error(`用户账号不能为空`);
				return
			}
			if(this.ruleForm.yonghuzhanghao && ((this.ruleForm.yonghuzhanghao).toString().length<3) && `huanzhe` == this.tableName){
				this.$message.error(`用户账号长度不能小于3位`);
				return
			}
			if(this.ruleForm.yonghuzhanghao && ((this.ruleForm.yonghuzhanghao).toString().length>16) && `huanzhe` == this.tableName){
				this.$message.error(`用户账号长度不能大于16位`);
				return
			}
			if((!this.ruleForm.mima) && `huanzhe` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if(this.ruleForm.mima && ((this.ruleForm.mima).toString().length<3) && `huanzhe` == this.tableName){
				this.$message.error(`密码长度不能小于3位`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `huanzhe` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
			if((!this.ruleForm.yonghuxingming) && `huanzhe` == this.tableName){
				this.$message.error(`用户姓名不能为空`);
				return
			}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
			if(`huanzhe` == this.tableName && this.ruleForm.nianling &&(!this.$validate.isIntNumer(this.ruleForm.nianling))){
				this.$message.error(`年龄应输入整数`);
				return
			}
			if(`huanzhe` == this.tableName && this.ruleForm.lianxifangshi &&(!this.$validate.isMobile(this.ruleForm.lianxifangshi))){
				this.$message.error(`联系方式应输入手机格式`);
				return
			}
			if(`huanzhe` == this.tableName && this.ruleForm.pquestion==''){
				this.$message.error(`密保问题不能为空`);
				return
			}
			if(`huanzhe` == this.tableName && this.ruleForm.panswer==''){
				this.$message.error(`密保答案不能为空`);
				return
			}
			  if(this.tableName=='yisheng'){
				  this.ruleForm.sfsh = '待审核'
			  }
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `yisheng` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
            if(this.ruleForm.zhixingzigezheng!=null) {
                this.ruleForm.zhixingzigezheng = this.ruleForm.zhixingzigezheng.replace(new RegExp(this.$base.url,"g"),"");
            }
			if(`yisheng` == this.tableName && this.ruleForm.pquestion==''){
				this.$message.error(`密保问题不能为空`);
				return
			}
			if(`yisheng` == this.tableName && this.ruleForm.panswer==''){
				this.$message.error(`密保答案不能为空`);
				return
			}
			this.$http({
				url: url,
				method: "post",
				data:this.ruleForm
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.$message({
						message: "注册成功",
						type: "success",
						duration: 1500,
						onClose: () => {
							this.$router.replace({ path: "/login" });
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
.register-container {
	position: relative;
	background: url(http://codegen.caihongy.cn/20250811/2c2e34a20094462bb849b93bd6bf336e.jpg);
	background-repeat: no-repeat !important;
	background-size: cover !important;
	background: url(http://codegen.caihongy.cn/20250811/2c2e34a20094462bb849b93bd6bf336e.jpg);
	display: flex;
	width: 100%;
	min-height: 100vh;
	justify-content: center;
	align-items: center;
	background-position: center bottom;
	.rgs-form {
		.rgs-form2 {
		border-radius: 30px;
		box-shadow: none;
		padding: 10px 30px;
		backdrop-filter: blur(0px);
		margin: 20px 40px 20px auto;
		align-content: center;
		background: rgba(255,255,255,1);
		display: flex;
		width: 600px;
		min-height: 600px;
		align-items: center;
		flex-wrap: wrap;
		}
		border: 6px solid #87b86c;
		border-radius: 60px;
		padding: 0;
		margin: 30px auto;
		z-index: 1;
		align-content: center;
		background: url(http://codegen.caihongy.cn/20250811/dd86922c9bae49b398ee8f920b905aa8.png) no-repeat 40px center / 50% 94%,linear-gradient(60deg, rgba(255,255,255,.5) 0%, rgba(255,252,234,.5) 100%);
		display: flex;
		width: 80%;
		align-items: center;
		flex-wrap: wrap;
		height: auto;
		.title {
			margin: 10px 0 10px 0;
			text-shadow: none;
			color: #000;
			font-weight: 600;
			width: 100%;
			font-size: 20px;
			line-height: 44px;
			text-align: center;
		}
		.list-item {
			border: 1px solid #d6dadf;
			border-radius: 10px;
			padding: 0 0 0 130px;
			margin: 0 auto 15px;
			background: none;
			width: 100%;
			position: relative;
			height: auto;
			::v-deep .el-form-item__content {
				display: block;
			}
			.lable {
				padding: 0 10px 0 0;
				color: #333;
				left: -130px;
				width: 130px;
				font-size: 16px;
				line-height: 44px;
				position: absolute !important;
				text-align: right;
			}
			.el-input {
				width: 100%;
			}
			.el-input ::v-deep .el-input__inner {
				border-radius: 0px;
				padding: 0 10px;
				color: #333;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #d1d1d1;
				border-width: 0 0 0px;
				border-style: solid;
				height: 44px;
			}
			.el-input ::v-deep .el-input__inner:focus {
				border-radius: 0px;
				padding: 0 10px;
				color: #4c7bf5;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #4c7bf5;
				border-width: 0 0 0px;
				border-style: solid;
				height: 44px;
			}
			.el-input-number {
				width: 100%;
			}
			.el-input-number ::v-deep .el-input__inner {
				text-align: center;
				border-radius: 0px;
				padding: 0 10px;
				color: #333;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #d1d1d1;
				border-width: 0 0 0px;
				border-style: solid;
				height: 44px;
			}
			.el-input-number ::v-deep .el-input__inner:focus {
				border-radius: 0px;
				padding: 0 10px;
				color: #4c7bf5;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #4c7bf5;
				border-width: 0 0 0px;
				border-style: solid;
				height: 44px;
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
				border-radius: 0px;
				padding: 0 10px;
				color: #333;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #d1d1d1;
				border-width: 0 0 0px;
				border-style: solid;
				height: 44px;
			}
			.el-select ::v-deep .el-input__inner:focus {
				border-radius: 0px;
				padding: 0 10px;
				color: #4c7bf5;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #4c7bf5;
				border-width: 0 0 0px;
				border-style: solid;
				height: 44px;
			}
			.el-date-editor {
				width: 100%;
			}
			.el-date-editor ::v-deep .el-input__inner {
				border-radius: 0px;
				padding: 0 10px 0 30px;
				color: #333;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #d1d1d1;
				border-width: 0 0 0px;
				border-style: solid;
				height: 44px;
			}
			.el-date-editor ::v-deep .el-input__inner:focus {
				border-radius: 0px;
				padding: 0 10px 0 30px;
				color: #4c7bf5;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #4c7bf5;
				border-width: 0 0 0px;
				border-style: solid;
				height: 44px;
			}
			.el-date-editor.el-input {
				width: 100%;
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
				border: 2px solid #ddd;
				cursor: pointer;
				border-radius: 4px;
				margin: 5px 0 0;
				color: #999;
				width: 90px;
				font-size: 26px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			::v-deep .el-upload-list .el-upload-list__item {
				border: 2px solid #ddd;
				cursor: pointer;
				border-radius: 4px;
				margin: 5px 0 0;
				color: #999;
				width: 90px;
				font-size: 26px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			::v-deep .el-upload .el-icon-plus {
				border: 2px solid #ddd;
				cursor: pointer;
				border-radius: 4px;
				margin: 5px 0 0;
				color: #999;
				width: 90px;
				font-size: 26px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			::v-deep .el-upload__tip {
				color: #666;
				font-size: 16px;
			}
			::v-deep .el-input__inner::placeholder {
				color: #123;
				font-size: 16px;
			}
			.required {
				position: relative;
			}
			.required::after{
				color: red;
				left: 120px;
				position: absolute;
				content: "*";
			}
			.editor {
				width: 100%;
				height: auto;
			}
			.editor>.avatar-uploader {
				line-height: 0;
				height: 0;
			}
		}
		.list-item.email {
			input {
				border-radius: 0px;
				padding: 0 10px;
				color: #333;
				flex: 1;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #d1d1d1;
				border-width: 0 0 0px;
				border-style: solid;
				height: 44px;
			}
			input:focus {
				border-radius: 0px;
				padding: 0 10px;
				color: #4c7bf5;
				flex: 1;
				background: none;
				width: 100%;
				font-size: 16px;
				border-color: #4c7bf5;
				border-width: 0 0 0px;
				border-style: solid;
				height: 44px;
			}
			input::placeholder {
				color: #123;
				font-size: 16px;
			}
			button {
				border: 0;
				cursor: pointer;
				padding: 0 0px;
				margin: 0;
				color: #333;
				font-size: 16px;
				border-color: #d1d1d1;
				border-radius: 0 0px 0px 0;
				box-shadow: none;
				outline: none;
				background: none;
				width: 130px;
				border-width: 0 0 0px;
				border-style: solid;
				height: 44px;
			}
			button:hover {
				color: #4c7bf5;
				border-color: #4c7bf5;
			}
		}
		.register-btn {
			width: 100%;
		}
		.register-btn1 {
			padding: 0;
			width: 100%;
		}
		.register-btn2 {
			padding: 0;
			margin: 10px 0;
			width: 100%;
			text-align: center;
		}
		.r-btn {
			border: 0;
			cursor: pointer;
			border-radius: 10px;
			padding: 0 24px;
			margin: 0 0 10px;
			outline: none;
			color: #fff;
			background: #8ebc74;
			width: 100%;
			font-size: 22px;
			height: 50px;
		}
		.r-btn:hover {
			opacity: 0.5;
		}
		.r-login {
			cursor: pointer;
			padding: 0 10%;
			color: #333;
			display: inline-block;
			font-size: 16px;
			line-height: 2;
		}
		.r-login:hover {
			opacity: 0.8;
		}
	}
}
	
	::-webkit-scrollbar {
	  display: none;
	}
</style>
