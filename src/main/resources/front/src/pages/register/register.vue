<template>
	<div>

		<div class="container" :style="{'backgroundImage': indexBgUrl?`url(${$config.baseUrl + indexBgUrl})`:''}">
			<el-form class='rgs-form animate__animated animate__fadeInUp' v-if="pageFlag=='security1'">
				<div class="rgs-form2">
					<div class="title">找回密码</p></div>
					<el-form-item class="list-item" prop="username">
						<div class="label">账号：</div>
						<el-input v-model="forgetForm.username" placeholder="请您输入账号" />
					</el-form-item>
					<el-form-item class="list-item" prop="tableName">
						<div class="label">角色：</div>
						<el-select v-model="tableName" placeholder="请您选择角色" >
							<el-option label="患者" value="huanzhe"></el-option>
							<el-option label="医生" value="yisheng"></el-option>
						</el-select>
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<el-button class="register_btn" type="primary" @click="getSecurity()">下一步</el-button>
						</div>
						<div class="register-btn2">
							<router-link class="has_btn" to="/login">已有账号，直接登录</router-link>
						</div>
					</div>
				</div>
				<div class="idea1"></div>
				<div class="idea2"></div>
			</el-form>
			<el-form class='rgs-form animate__animated animate__fadeInUp' v-if="pageFlag=='security2'">
				<div class="rgs-form2">
					<div class="title">找回密码</p></div>
					<el-form-item class="list-item" prop="pquestion">
						<div class="label">密保问题：</div>
						<el-input readonly v-model="registerForm.pquestion" placeholder="密保问题" />
					</el-form-item>
					<el-form-item class="list-item" prop="myanswer">
						<div class="label">密保答案：</div>
						<el-input v-model="registerForm.myanswer" placeholder="请您输入密保答案" />
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<el-button class="register_btn" type="primary" @click="validateSecurity()">下一步</el-button>
						</div>
						<div class="register-btn2">
							<router-link class="has_btn" to="/login">已有账号，直接登录</router-link>
						</div>
					</div>
				</div>
				<div class="idea1"></div>
				<div class="idea2"></div>
			</el-form>
			<el-form class='rgs-form animate__animated animate__fadeInUp' v-if="pageFlag=='security3'">
				<div class="rgs-form2">
					<div class="title">找回密码</p></div>
					<el-form-item class="list-item" prop="newpassword">
						<div class="label">新密码：</div>
						<el-input v-model="registerForm.newpassword" type="password" placeholder="请您输入新密码" />
					</el-form-item>
					<el-form-item class="list-item" prop="repassword">
						<div class="label">确认密码：</div>
						<el-input v-model="registerForm.repassword" type="password" placeholder="请您确认密码" />
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<el-button class="register_btn" type="primary" @click="updatePassword()">修改密码</el-button>
						</div>
						<div class="register-btn2">
							<router-link class="has_btn" to="/login">已有账号，直接登录</router-link>
						</div>
					</div>
				</div>
				<div class="idea1"></div>
				<div class="idea2"></div>
			</el-form>
			<el-form class='rgs-form animate__animated animate__fadeInUp' v-if="pageFlag=='register'" ref="registerForm" :model="registerForm" :rules="rules">
				<div class="rgs-form2">
					<div class="title">基于springboot的医疗资源分享平台的设计与实现注册</p></div>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'" prop="yonghuzhanghao">
						<div class="label" :class="changeRules('yonghuzhanghao')?'required':''">用户账号：</div>
						<el-input v-model="registerForm.yonghuzhanghao" :readonly="ro.yonghuzhanghao" placeholder="请输入用户账号" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'" prop="mima">
						<div class="label" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input v-model="registerForm.mima" type="password" placeholder="请输入密码"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'" prop="mima2">
						<div class="label" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input v-model="registerForm.mima2" type="password" placeholder="请再次输入密码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'" prop="yonghuxingming">
						<div class="label" :class="changeRules('yonghuxingming')?'required':''">用户姓名：</div>
						<el-input v-model="registerForm.yonghuxingming" :readonly="ro.yonghuxingming" placeholder="请输入用户姓名" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'" prop="touxiang">
						<div class="label" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="1"
							:multiple="true"
							:fileUrls="registerForm.touxiang?registerForm.touxiang:''"
							@change="huanzhetouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'" prop="xingbie">
						<div class="label" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select filterable v-model="registerForm.xingbie" placeholder="请选择性别" :disabled="ro.xingbie">
							<el-option
								v-for="(item,index) in huanzhexingbieOptions"
								:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'" prop="nianling">
						<div class="label" :class="changeRules('nianling')?'required':''">年龄：</div>
						<el-input v-model.number="registerForm.nianling" :readonly="ro.nianling" placeholder="请输入年龄" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'" prop="lianxifangshi">
						<div class="label" :class="changeRules('lianxifangshi')?'required':''">联系方式：</div>
						<el-input v-model="registerForm.lianxifangshi" :readonly="ro.lianxifangshi" placeholder="请输入联系方式" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'" prop="jiatingzhuzhi">
						<div class="label" :class="changeRules('jiatingzhuzhi')?'required':''">家庭住址：</div>
						<el-input v-model="registerForm.jiatingzhuzhi" :readonly="ro.jiatingzhuzhi" placeholder="请输入家庭住址" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'" prop="pquestion">
						<div class="label" :class="changeRules('pquestion')?'required':''">密保问题：</div>
						<el-input v-model="registerForm.pquestion" :readonly="ro.pquestion" placeholder="请输入密保问题" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'" prop="panswer">
						<div class="label" :class="changeRules('panswer')?'required':''">密保答案：</div>
						<el-input v-model="registerForm.panswer" :readonly="ro.panswer" placeholder="请输入密保答案" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='huanzhe'" prop="bingshi">
						<div class="label" :class="changeRules('bingshi')?'required':''">病史：</div>
						<editor
							:disabled="ro.bingshi"
							myQuillEditor="bingshi"
							v-model="registerForm.bingshi" 
							class="editor" 
							action="file/upload">
						</editor>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'" prop="yishenggonghao">
						<div class="label" :class="changeRules('yishenggonghao')?'required':''">医生工号：</div>
						<el-input v-model="registerForm.yishenggonghao" :readonly="ro.yishenggonghao" placeholder="请输入医生工号" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'" prop="mima">
						<div class="label" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input v-model="registerForm.mima" type="password" placeholder="请输入密码"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'" prop="mima2">
						<div class="label" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input v-model="registerForm.mima2" type="password" placeholder="请再次输入密码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'" prop="yishengxingming">
						<div class="label" :class="changeRules('yishengxingming')?'required':''">医生姓名：</div>
						<el-input v-model="registerForm.yishengxingming" :readonly="ro.yishengxingming" placeholder="请输入医生姓名" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'" prop="touxiang">
						<div class="label" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="1"
							:multiple="true"
							:fileUrls="registerForm.touxiang?registerForm.touxiang:''"
							@change="yishengtouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'" prop="zhixingzigezheng">
						<div class="label" :class="changeRules('zhixingzigezheng')?'required':''">执行资格证：</div>
						<file-upload
							tip="点击上传执行资格证"
							action="file/upload"
							:limit="1"
							:type="3"
							:multiple="true"
							:fileUrls="registerForm.zhixingzigezheng?registerForm.zhixingzigezheng:''"
							@change="yishengzhixingzigezhengUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'" prop="keshimingcheng">
						<div class="label" :class="changeRules('keshimingcheng')?'required':''">科室名称：</div>
						<el-input v-model="registerForm.keshimingcheng" :readonly="ro.keshimingcheng" placeholder="请输入科室名称" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'" prop="zhicheng">
						<div class="label" :class="changeRules('zhicheng')?'required':''">职称：</div>
						<el-select filterable v-model="registerForm.zhicheng" placeholder="请选择职称" :disabled="ro.zhicheng">
							<el-option
								v-for="(item,index) in yishengzhichengOptions"
								:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'" prop="fuwushijian">
						<div class="label" :class="changeRules('fuwushijian')?'required':''">服务时间：</div>
						<el-input v-model="registerForm.fuwushijian" :readonly="ro.fuwushijian" placeholder="请输入服务时间" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'" prop="pquestion">
						<div class="label" :class="changeRules('pquestion')?'required':''">密保问题：</div>
						<el-input v-model="registerForm.pquestion" :readonly="ro.pquestion" placeholder="请输入密保问题" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yisheng'" prop="panswer">
						<div class="label" :class="changeRules('panswer')?'required':''">密保答案：</div>
						<el-input v-model="registerForm.panswer" :readonly="ro.panswer" placeholder="请输入密保答案" />
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<el-button class="register_btn" type="primary" @click="submitForm('registerForm')">注册</el-button>
						</div>
						<div class="register-btn2">
							<router-link class="has_btn" to="/login">已有账号，直接登录</router-link>
						</div>
					</div>
				</div>
				<div class="idea1"></div>
				<div class="idea2"></div>
			</el-form>
		</div>
	</div>
</div>
</template>

<script>
	import 'animate.css';

export default {
    //数据集合
    data() {
		return {
            pageFlag : '',
			tableName: '',
			registerForm: {},
			forgetForm: {},
			rules: {},
			ro: {},
			requiredRules: {},
            huanzhexingbieOptions: [],
            yishengzhichengOptions: [],
			indexBgUrl: '',
		}
    },
	mounted() {
		if(this.$route.query.pageFlag=='register'){
			this.tableName = this.$route.query.role;
			if(this.tableName=='huanzhe'){
				this.registerForm = {
					yonghuzhanghao: '',
					mima: '',
					mima2: '',
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
				this.registerForm = {
					yishenggonghao: '',
					mima: '',
					mima2: '',
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
				this.rules.yonghuzhanghao = [{ required: true, message: '请输入用户账号', trigger: 'blur' }];
				this.requiredRules.yonghuzhanghao = [{ required: true, message: '请输入用户账号', trigger: 'blur' }]
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }];
				this.requiredRules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
				this.rules.yonghuxingming = [{ required: true, message: '请输入用户姓名', trigger: 'blur' }];
				this.requiredRules.yonghuxingming = [{ required: true, message: '请输入用户姓名', trigger: 'blur' }]
				this.huanzhexingbieOptions = "男,女".split(',');
				this.rules.nianling = [{ required: true, validator: this.$validate.isIntNumer, trigger: 'blur' }];
				this.rules.lianxifangshi = [{ required: true, validator: this.$validate.isMobile, trigger: 'blur' }];
				this.rules.pquestion = [{ required: true, message: '请输入密保问题', trigger: 'blur' }];
				this.requiredRules.pquestion = [{ required: true, message: '请输入密保问题', trigger: 'blur' }]
				this.rules.panswer = [{ required: true, message: '请输入密保答案', trigger: 'blur' }];
				this.requiredRules.panswer = [{ required: true, message: '请输入密保答案', trigger: 'blur' }]
			}
			if ('yisheng' == this.tableName) {
				this.yishengzhichengOptions = "专家,主任医师,副主任医师,主治医师".split(',');
				this.rules.pquestion = [{ required: true, message: '请输入密保问题', trigger: 'blur' }];
				this.requiredRules.pquestion = [{ required: true, message: '请输入密保问题', trigger: 'blur' }]
				this.rules.panswer = [{ required: true, message: '请输入密保答案', trigger: 'blur' }];
				this.requiredRules.panswer = [{ required: true, message: '请输入密保答案', trigger: 'blur' }]
			}
		}
	},
    created() {
		this.$http.get('config/info?name=fRegisterBackgroudImg',).then(rs=>{this.indexBgUrl = rs.data.data?rs.data.data.value:''})
		this.pageFlag = this.$route.query.pageFlag;
    },
    //方法集合
    methods: {
		changeRules(name){
			if(this.requiredRules[name]){
				return true
			}
			return false
		},
		// 获取uuid
		getUUID () {
			return new Date().getTime();
		},
        // 下二随
		huanzhetouxiangUploadChange(fileUrls) {
			this.registerForm.touxiang = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
		},
		yishengtouxiangUploadChange(fileUrls) {
			this.registerForm.touxiang = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
		},
		yishengzhixingzigezhengUploadChange(fileUrls) {
			this.registerForm.zhixingzigezheng = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
		},

		// 多级联动参数


//获取密保问题
		getSecurity(){
			if(!this.tableName) {
				this.$message.error("请选择角色");
				return false
			}
			if(!this.forgetForm.username){
				this.$message.error("请输入账号");
				return false
			}
			this.$http({
				url: this.tableName+`/security?username=` + this.forgetForm.username,
				method: "get",
			}).then(({
				data
			}) => {
				if (data.data && data.code === 0) {
					this.registerForm = data.data;
					this.pageFlag="security2"
				} else {
					if(!data.data) {
						this.$message.error("用户不存在");
					} else {
						this.$message.error(data.msg);
					}
				}
			});
		},
		//验证密保问题
		validateSecurity(){
			let that = this
			if(this.registerForm.panswer!=this.registerForm.myanswer){
				this.$message.error("答案输入不正确");
				return false
			}
			this.$message.success("答案正确");
			setTimeout(()=>{
				this.pageFlag="security3";
			},1000)
		},
		// 修改密码
		updatePassword() {
			if (!this.registerForm.newpassword) {
				this.$message.error("请输入新密码");
				return;
			}
			if (this.registerForm.newpassword != this.registerForm.repassword) {
				this.$message.error("两次密码输入不一致");
				return;
			}
			this.registerForm.mima = this.registerForm.newpassword;
			this.registerForm.password = this.registerForm.newpassword;
			this.$http.post(this.tableName+`/update`, this.registerForm).then(res => {
			  if (res.data.code === 0) {
				this.$message({
					message: "密码修改成功",
					type: "success",
					duration: 1500,
					onClose: () => {
					  this.$router.push('/login');
					}
				});
			  } else {
				this.$message.error(res.data.msg);
			  }
			});
		},
		submitForm(formName) {
			this.$refs[formName].validate((valid) => {
				if (valid) {
					var url=this.tableName+"/register";
					if(`huanzhe` == this.tableName && (this.registerForm.yonghuzhanghao).toString().length<3){
						this.$message.error('用户账号长度不能小于3');
						return
					}
					if(`huanzhe` == this.tableName && (this.registerForm.yonghuzhanghao).toString().length>16){
						this.$message.error('用户账号长度不能大于16');
						return
					}
					if(`huanzhe` == this.tableName && this.registerForm.mima!=this.registerForm.mima2) {
						this.$message.error(`两次密码输入不一致`);
						return
					}
					if(`huanzhe` == this.tableName && (this.registerForm.mima).toString().length<3){
						this.$message.error('密码长度不能小于3');
						return
					}
					if(this.tableName=='yisheng'){
						this.registerForm.sfsh = '待审核'
					}
					if(`yisheng` == this.tableName && this.registerForm.mima!=this.registerForm.mima2) {
						this.$message.error(`两次密码输入不一致`);
						return
					}
					this.$http.post(url, this.registerForm).then(res => {
						if (res.data.code === 0) {
							this.$message({
								message: '注册成功',
								type: 'success',
								duration: 1500,
								onClose: () => {
									this.$router.push('/login');
								}
							});
						} else {
							this.$message.error(res.data.msg);
						}
					});
				} else {
					return false;
				}
			});
		},
		resetForm(formName) {
			this.$refs[formName].resetFields();
		},
    }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.container {
		background-repeat: no-repeat;
		background-size: cover;
		background: url(http://codegen.caihongy.cn/20250816/38ed21f992dd4087825b67f74e71b53e.webp) 100%;
		display: flex;
		width: 100%;
		min-height: 100vh;
		justify-content: center;
		align-items: center;
		background-position: center center;
		position: relative;
		background: url(http://codegen.caihongy.cn/20250816/38ed21f992dd4087825b67f74e71b53e.webp) 100%;
		.rgs-form {
			border-radius: 20px;
			padding: 40px 40px;
			box-shadow: 0 0px 0px rgba(0, 0, 0, .5);
			margin: 0 auto 0 260px;
			z-index: 10;
			background: rgba(255,255,255,.5);
			width: 600px;
			height: auto;
			.rgs-form2 {
				width: 100%;
				.title {
					margin: 0 0 20px -30%;
					text-shadow: none;
					color: #000;
					width: 160%;
					font-size: 20px;
					line-height: 44px;
					text-align: center;
				}
				.subtitle {
					margin: 0 0 10px 0;
					text-shadow: 4px 4px 2px rgba(64, 158, 255, .5);
					color: rgba(64, 158, 255, 1);
					width: 100%;
					font-size: 20px;
					line-height: 44px;
					text-align: center;
				}
				.list-item {
					padding: 0;
					margin: 0 auto 15px;
					width: 100%;
					border-color: #ddd;
					border-width: 0 0 1px;
					position: relative;
					border-style: solid;
					height: auto;
					::v-deep.el-form-item__content {
						padding: 0 0 0 140px;
						display: block;
						width: 100%;
						.label {
							padding: 0 5px 0 0;
							z-index: 9;
							color: #333;
							white-space: nowrap;
							left: 0;
							width: 140px;
							font-size: 16px;
							line-height: 40px;
							position: absolute !important;
							text-align: right;
						}
						
						.required {
							position: relative;
						}
						.required::after{
							color: red;
							left: 130px;
							position: absolute;
							content: "*";
						}
						.el-input {
							flex: 1;
							width: 100%;
						}
						.el-input .el-input__inner {
							border: 0;
							border-radius: 0 20px 20px 0;
							padding: 0 10px;
							box-shadow: none;
							outline: none;
							color: #666;
							width: 100%;
							font-size: 14px;
							height: 40px;
						}
						.el-input .el-input__inner:focus {
							border: 0;
							border-radius: 0 20px 20px 0;
							padding: 0 10px;
							box-shadow: none;
							outline: none;
							color: #666;
							width: 100%;
							font-size: 14px;
							height: 40px;
						}
						.el-input-number {
							flex: 1;
							width: 100%;
						}
						.el-input-number .el-input__inner {
							text-align: left;
							border: 0;
							border-radius: 0 20px 20px 0;
							padding: 0 10px;
							box-shadow: none;
							outline: none;
							color: #666;
							width: 100%;
							font-size: 14px;
							height: 40px;
						}
						.el-input-number .el-input-number__decrease {
							display: none;
						}
						.el-input-number .el-input-number__increase {
							display: none;
						}
						.el-select {
							flex: 1;
							width: 100%;
						}
						.el-select .el-input__inner {
							border: 0;
							border-radius: 0 20px 20px 0;
							padding: 0 10px;
							box-shadow: none;
							outline: none;
							color: #666;
							width: 100%;
							font-size: 16px;
							height: 40px;
						}
						.el-select .el-input__inner:focus {
							border: 0;
							border-radius: 0 20px 20px 0;
							padding: 0 10px;
							box-shadow: none;
							outline: none;
							color: #666;
							width: 100%;
							font-size: 16px;
							height: 40px;
						}
						.el-date-editor {
							flex: 1;
							width: 100%;
						}
						.el-date-editor .el-input__inner {
							border: 0;
							border-radius: 0 20px 20px 0;
							padding: 0 10px 0 30px;
							box-shadow: none;
							outline: none;
							color: #666;
							width: 100%;
							font-size: 16px;
							height: 40px;
						}
						.el-date-editor .el-input__inner:focus {
							border: 0;
							border-radius: 0 20px 20px 0;
							padding: 0 10px 0 30px;
							box-shadow: none;
							outline: none;
							color: #666;
							width: 100%;
							font-size: 16px;
							height: 40px;
						}
						.el-upload--picture-card {
							background: transparent;
							border: 0;
							border-radius: 0;
							width: auto;
							height: auto;
							line-height: initial;
							vertical-align: middle;
						}
						.upload .upload-img {
							border: 1px solid #eee;
							cursor: pointer;
							border-radius: 0;
							color: #999;
							background: rgba(255,255,255,.5);
							object-fit: cover;
							width: 60px;
							font-size: 22px;
							line-height: 60px;
							text-align: center;
							height: 60px;
						}
						.el-upload-list .el-upload-list__item {
							border: 1px solid #eee;
							cursor: pointer;
							border-radius: 0;
							color: #999;
							background: rgba(255,255,255,.5);
							object-fit: cover;
							width: 60px;
							font-size: 22px;
							line-height: 60px;
							text-align: center;
							height: 60px;
							font-size: 14px;
							line-height: 1.8;
						}
						.el-upload .el-icon-plus {
							border: 1px solid #eee;
							cursor: pointer;
							border-radius: 0;
							color: #999;
							background: rgba(255,255,255,.5);
							object-fit: cover;
							width: 60px;
							font-size: 22px;
							line-height: 60px;
							text-align: center;
							height: 60px;
						}
						.el-upload__tip {
							color: #333;
							font-size: 14px;
						}
						.emailInput {
							border: 0;
							border-radius: 0 20px 20px 0;
							padding: 0 10px;
							box-shadow: none;
							margin: 0;
							outline: none;
							color: #666;
							background: #fff;
							flex: 1;
							width: 100%;
							font-size: 14px;
							height: 40px;
						}
						.emailInput:focus {
							border: 0;
							border-radius: 0 20px 20px 0;
							padding: 0 10px;
							box-shadow: none;
							outline: none;
							color: #666;
							flex: 1;
							width: 100%;
							font-size: 16px;
							height: 40px;
						}
						.el-btn {
							border: 0;
							cursor: pointer;
							padding: 0 15px;
							margin: 0 0 5px;
							color: #fff;
							font-size: 15px;
							float: right;
							border-radius: 6px;
							box-shadow: 0 0 0px rgba(64, 158, 255, .5);
							outline: none;
							background: rgb(167, 5, 0);
							width: auto;
							height: 40px;
						}
						.el-btn:hover {
							opacity: 0.5;
						}
						
						.el-input__inner::placeholder {
							color: #123;
							font-size: 16px;
						}
						input::placeholder {
							color: #123;
							font-size: 16px;
						}
						.editor {
							margin: 0 0 15px;
							background: #fff;
							width: 100%;
							height: auto;
						}
						.editor .ql-toolbar {
							background: none;
						}
						.editor .ql-container {
							background: none;
						}
						.editor .ql-container .ql-blank::before {
							color: #000;
						}
					}
				}
				.register-btn {
					padding: 0;
					margin: 30px 0 0;
					width: 100%;
				}
				.register-btn1 {
					width: 100%;
					text-align: center;
				}
				.register-btn2 {
					width: 100%;
					text-align: center;
				}
				.register_btn {
					border: 0;
					cursor: pointer;
					padding: 0 24px;
					margin: 0 auto 10px;
					color: #fff;
					font-size: 22px;
					transition: all 0s;
					border-radius: 4px;
					outline: none;
					background: rgb(167, 5, 0);
					width: 100%;
					min-width: 143px;
					height: 50px;
				}
				.register_btn:hover {
					opacity: 0.8;
				}
				.has_btn {
					cursor: pointer;
					padding: 0;
					color: #333;
					display: inline-block;
					text-decoration: none;
					font-size: 16px;
					line-height: 30px;
				}
				.has_btn:hover {
					opacity: 0.5;
				}
			}
			.idea1 {
				background: red;
				display: none;
				width: 100%;
				height: 40px;
			}
			.idea2 {
				background: blue;
				display: none;
				width: 100%;
				height: 40px;
			}
		}
	}
	
	::-webkit-scrollbar {
		display: none;
	}
</style>
