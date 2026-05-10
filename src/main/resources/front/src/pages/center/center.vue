<template>
	<div class="center-preview">
		<div class="center-title">{{ title }}</div>
		<div class="center-info">
			<div class="center-info-title">个人信息</div>

			<div class="img-box" v-if="userTableName=='huanzhe'">
				<img :src="sessionForm.touxiang?baseUrl + sessionForm.touxiang:require('@/assets/avator.png')">
			</div>
			<div class="img-box" v-if="userTableName=='yisheng'">
				<img :src="sessionForm.touxiang?baseUrl + sessionForm.touxiang:require('@/assets/avator.png')">
			</div>
			<div class="info-item1" v-if="userTableName=='huanzhe'">
				<span class="icon iconfont icon-geren14"></span>
				<div class="label">用户账号：</div>
				<div class="text">{{sessionForm.yonghuzhanghao}}</div>
			</div>
			<div class="info-item2" v-if="userTableName=='huanzhe'">
				<span class="icon iconfont icon-fenxiang"></span>
				<div class="label">用户姓名：</div>
				<div class="text">{{sessionForm.yonghuxingming}}</div>
			</div>
			<div class="info-item3" v-if="userTableName=='huanzhe'">
				<span class="icon iconfont icon-shijian16"></span>
				<div class="label">性别：</div>
				<div class="text">{{sessionForm.xingbie}}</div>
			</div>
			<div class="info-item4" v-if="userTableName=='huanzhe'">
				<span class="icon iconfont icon-qianshuxieyi"></span>
				<div class="label">年龄：</div>
				<div class="text">{{sessionForm.nianling}}</div>
			</div>
			<div class="info-item5" v-if="userTableName=='huanzhe'">
				<span class="icon iconfont icon-touxiang05"></span>
				<div class="label">联系方式：</div>
				<div class="text">{{sessionForm.lianxifangshi}}</div>
			</div>
			<div class="info-item6" v-if="userTableName=='huanzhe'">
				<span class="icon iconfont icon-shijian21"></span>
				<div class="label">家庭住址：</div>
				<div class="text">{{sessionForm.jiatingzhuzhi}}</div>
			</div>
			<div class="info-item1" v-if="userTableName=='yisheng'">
				<span class="icon iconfont icon-geren14"></span>
				<div class="label">医生工号：</div>
				<div class="text">{{sessionForm.yishenggonghao}}</div>
			</div>
			<div class="info-item2" v-if="userTableName=='yisheng'">
				<span class="icon iconfont icon-fenxiang"></span>
				<div class="label">医生姓名：</div>
				<div class="text">{{sessionForm.yishengxingming}}</div>
			</div>
			<div class="info-item3" v-if="userTableName=='yisheng'">
				<span class="icon iconfont icon-shijian16"></span>
				<div class="label">科室名称：</div>
				<div class="text">{{sessionForm.keshimingcheng}}</div>
			</div>
			<div class="info-item4" v-if="userTableName=='yisheng'">
				<span class="icon iconfont icon-qianshuxieyi"></span>
				<div class="label">职称：</div>
				<div class="text">{{sessionForm.zhicheng}}</div>
			</div>
			<div class="info-item5" v-if="userTableName=='yisheng'">
				<span class="icon iconfont icon-touxiang05"></span>
				<div class="label">服务时间：</div>
				<div class="text">{{sessionForm.fuwushijian}}</div>
			</div>
			<div class="info-item6" v-if="userTableName=='yisheng'">
				<span class="icon iconfont icon-shijian21"></span>
				<div class="label">是否审核：</div>
				<div class="text">{{sessionForm.sfsh}}</div>
			</div>
		
		</div>
	
		<div class="center-box">
			<div class="center-tab-view">
				<div class="center-tab" :class="activeName==title2?'is-active':''" @click="handleClick(title2)">{{title2}}</div>
				<div class="center-tab" :class="activeName=='修改密码'?'is-active':''" @click="handleClick('修改密码')">修改密码</div>
				<div class="center-tab" v-if="hasBack(item.menu,item.child[0].tableName)" v-for="(item,index) in menuList" :key="index" @mouseenter="centerTabEnter(item.child[0].tableName)" @mouseleave="centerTabEnter('')" @click="menuClick(item.child[0],item.child.length)">
					<template v-if="item.child.length==1">
						{{item.child[0].menu}}
					</template>
					<template v-else>
						{{item.menu}}
						<transition name="el-fade-in-linear">
							<div class="center-second-tab-view" v-if="showActive=='show' + item.child[0].tableName">
								<div class="center-second-tab" v-for="(items,indexs) in item.child" :key="indexs" @click="menuClick(items)">
									{{items.menu}}
								</div>
							</div>
						</transition>
					</template>
				</div>


			</div>
			<div class="center-content-box">
				<div class="center-content-view" v-show="activeName=='个人中心'">
					<el-form class="center-preview-pv" ref="sessionForm" :model="sessionForm" :rules="rules" label-width="180px">
						<el-form-item class="center-item" v-if="userTableName=='huanzhe'" label="用户账号" prop="yonghuzhanghao">
							<el-input v-model="sessionForm.yonghuzhanghao" placeholder="用户账号" :disabled="ro.yonghuzhanghao"></el-input>
						</el-form-item>
						<el-form-item class="center-item" v-if="userTableName=='huanzhe'" label="用户姓名" prop="yonghuxingming">
							<el-input v-model="sessionForm.yonghuxingming" placeholder="用户姓名" :disabled="ro.yonghuxingming"></el-input>
						</el-form-item>
						<el-form-item class="center-item" v-if="userTableName=='huanzhe'" label="头像" prop="touxiang">
							<file-upload
								tip="点击上传头像"
								action="file/upload"
								:limit="1"
								:multiple="true"
								:fileUrls="sessionForm.touxiang?sessionForm.touxiang:''"
								@change="huanzhetouxiangHandleAvatarSuccess"
								></file-upload>
						</el-form-item>
						<el-form-item class="center-item" v-if="userTableName=='huanzhe'" label="性别" prop="xingbie">
							<el-select filterable v-model="sessionForm.xingbie" placeholder="请选择性别" :disabled="ro.xingbie">
								<el-option v-for="(item, index) in dynamicProp.xingbie" :key="index" :label="item" :value="item"></el-option>
							</el-select>
						</el-form-item>
						<el-form-item class="center-item" v-if="userTableName=='huanzhe'" label="年龄" prop="nianling">
							<el-input v-model="sessionForm.nianling" placeholder="年龄" :disabled="ro.nianling"></el-input>
						</el-form-item>
						<el-form-item class="center-item" v-if="userTableName=='huanzhe'" label="联系方式" prop="lianxifangshi">
							<el-input v-model="sessionForm.lianxifangshi" placeholder="联系方式" :disabled="ro.lianxifangshi"></el-input>
						</el-form-item>
						<el-form-item class="center-item" v-if="userTableName=='huanzhe'" label="家庭住址" prop="jiatingzhuzhi">
							<el-input v-model="sessionForm.jiatingzhuzhi" placeholder="家庭住址" :disabled="ro.jiatingzhuzhi"></el-input>
						</el-form-item>
						<el-form-item class="center-item" v-if="userTableName=='huanzhe'" label="密保问题" prop="pquestion">
							<el-input v-model="sessionForm.pquestion" placeholder="密保问题" :disabled="ro.pquestion"></el-input>
						</el-form-item>
						<el-form-item class="center-item" v-if="userTableName=='huanzhe'" label="密保答案" prop="panswer">
							<el-input v-model="sessionForm.panswer" placeholder="密保答案" :disabled="ro.panswer"></el-input>
						</el-form-item>
						<el-form-item class="center-item" v-if="userTableName=='yisheng'" label="医生工号" prop="yishenggonghao">
							<el-input v-model="sessionForm.yishenggonghao" placeholder="医生工号" :disabled="ro.yishenggonghao"></el-input>
						</el-form-item>
						<el-form-item class="center-item" v-if="userTableName=='yisheng'" label="医生姓名" prop="yishengxingming">
							<el-input v-model="sessionForm.yishengxingming" placeholder="医生姓名" :disabled="ro.yishengxingming"></el-input>
						</el-form-item>
						<el-form-item class="center-item" v-if="userTableName=='yisheng'" label="头像" prop="touxiang">
							<file-upload
								tip="点击上传头像"
								action="file/upload"
								:limit="1"
								:multiple="true"
								:fileUrls="sessionForm.touxiang?sessionForm.touxiang:''"
								@change="yishengtouxiangHandleAvatarSuccess"
								></file-upload>
						</el-form-item>
						<el-form-item class="center-item" v-if="userTableName=='yisheng'" label="执行资格证" prop="zhixingzigezheng">
							<file-upload
								tip="点击上传执行资格证"
								action="file/upload"
								:limit="1"
								:type="3"
								:multiple="true"
								:fileUrls="sessionForm.zhixingzigezheng?sessionForm.zhixingzigezheng:''"
								@change="yishengzhixingzigezhengHandleAvatarSuccess"
								></file-upload>
						</el-form-item>
						<el-form-item class="center-item" v-if="userTableName=='yisheng'" label="科室名称" prop="keshimingcheng">
							<el-input v-model="sessionForm.keshimingcheng" placeholder="科室名称" :disabled="ro.keshimingcheng"></el-input>
						</el-form-item>
						<el-form-item class="center-item" v-if="userTableName=='yisheng'" label="职称" prop="zhicheng">
							<el-select filterable v-model="sessionForm.zhicheng" placeholder="请选择职称" :disabled="ro.zhicheng">
								<el-option v-for="(item, index) in dynamicProp.zhicheng" :key="index" :label="item" :value="item"></el-option>
							</el-select>
						</el-form-item>
						<el-form-item class="center-item" v-if="userTableName=='yisheng'" label="服务时间" prop="fuwushijian">
							<el-input v-model="sessionForm.fuwushijian" placeholder="服务时间" :disabled="ro.fuwushijian"></el-input>
						</el-form-item>
						<el-form-item class="center-item" v-if="userTableName=='yisheng'" label="擅长领域" prop="shanzhanglingyu">
							<el-input v-model="sessionForm.shanzhanglingyu" placeholder="擅长领域" :disabled="ro.shanzhanglingyu"></el-input>
						</el-form-item>
						<el-form-item class="center-item" v-if="userTableName=='yisheng'" label="职业履历" prop="zhiyelvli">
							<el-input v-model="sessionForm.zhiyelvli" placeholder="职业履历" :disabled="ro.zhiyelvli"></el-input>
						</el-form-item>
						<el-form-item class="center-item" v-if="userTableName=='yisheng'" label="密保问题" prop="pquestion">
							<el-input v-model="sessionForm.pquestion" placeholder="密保问题" :disabled="ro.pquestion"></el-input>
						</el-form-item>
						<el-form-item class="center-item" v-if="userTableName=='yisheng'" label="密保答案" prop="panswer">
							<el-input v-model="sessionForm.panswer" placeholder="密保答案" :disabled="ro.panswer"></el-input>
						</el-form-item>
						<el-form-item class="center-item" v-if="userTableName=='huanzhe'" label="病史">
							<editor
								myQuillEditor="bingshi"
								v-model="sessionForm.bingshi" 
								class="editor" 
								action="file/upload">
							</editor>
						</el-form-item>
						<el-form-item class="center-btn-item">
							<div class="updateBtn" type="primary" @click="onSubmit('sessionForm')">
								<span class="icon iconfont icon-kaitongfuwu"></span>
								<span class="text">提交信息</span>
							</div>
							<div class="closeBtn" type="danger" @click="logout">
								<span class="icon iconfont icon-shanchu10"></span>
								<span class="text">退出登录</span>
							</div>
						</el-form-item>
					</el-form>
				</div>
				<div class="center-content-view" v-show="activeName=='修改密码'">
					<el-form v-if="psdType==1" class="center-preview-pv" ref="passwordForm" :model="passwordForm" :rules="passwordRules" label-width="180px">
						<el-form-item class="center-item" label="原密码" prop="password">
							<el-input type="password" v-model="passwordForm.password" placeholder="原密码"></el-input>
						</el-form-item>
						<el-form-item class="center-item" label="新密码" prop="newpassword">
							<el-input type="password" v-model="passwordForm.newpassword" placeholder="新密码"></el-input>
						</el-form-item>
						<el-form-item class="center-item" label="确认密码" prop="repassword">
							<el-input type="password" v-model="passwordForm.repassword" placeholder="确认密码"></el-input>
						</el-form-item>
						<el-form-item class="center-btn-item">
							<div class="updateBtn" type="primary" @click="updatePassword">
								<span class="icon iconfont icon-kaitongfuwu"></span>
								<span class="text">修改密码</span>
							</div>
						</el-form-item>
					</el-form>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import config from '@/config/config';
	import menu from '@/config/menu';
	import Vue from 'vue';
	export default {
		//数据集合
		data() {
			return {
				title: '个人中心',
				title2: '个人中心',
				showActive: '',
				activeName: '个人中心',
				baseUrl: config.baseUrl,
				sessionForm: {},
				ro: {},
				passwordForm: {},
				psdType: '1',
				passwordRules: {
					password: [
						{
							required: true,
							message: "密码不能为空",
							trigger: "blur"
						}
					],
					newpassword: [
						{
							required: true,
							message: "新密码不能为空",
							trigger: "blur"
						}
					],
					repassword: [
						{
							required: true,
							message: "确认密码不能为空",
							trigger: "blur"
						}
					]
				},
				rules: {},
				menuList: [],
				disabled: false,
				uploadUrl: config.baseUrl + 'file/upload',
				imageUrl: '',
				headers: {Token: localStorage.getItem('frontToken')},
				userTableName: localStorage.getItem('UserTableName'),
				dynamicProp: {},
			}
		},
		async created() {
			let menus = menu.list()
			for(let x in menus){
				if(menus[x].tableName == this.userTableName){
					for(let i in menus[x].backMenu){
						if(menus[x].backMenu[i].child&&menus[x].backMenu[i].child.length&&menus[x].backMenu[i].child[0].tableName.indexOf('exam')!=-1){
							menus[x].backMenu.splice(i,1)
						}
					}
					this.menuList = menus[x].backMenu
				}
			}
			if ('huanzhe' == this.userTableName) {
				this.$set(this.sessionForm, 'yonghuzhanghao', null);
			}
			if ('huanzhe' == this.userTableName) {
				this.$set(this.sessionForm, 'mima', null);
			}
			if ('huanzhe' == this.userTableName) {
				this.$set(this.sessionForm, 'yonghuxingming', null);
			}
			if ('huanzhe' == this.userTableName) {
				this.$set(this.sessionForm, 'touxiang', null);
			}
			if ('huanzhe' == this.userTableName) {
				this.$set(this.sessionForm, 'xingbie', null);
			}
			if ('huanzhe' == this.userTableName) {
				this.$set(this.sessionForm, 'nianling', null);
			}
			if ('huanzhe' == this.userTableName) {
				this.$set(this.sessionForm, 'lianxifangshi', null);
			}
			if ('huanzhe' == this.userTableName) {
				this.$set(this.sessionForm, 'jiatingzhuzhi', null);
			}
			if ('huanzhe' == this.userTableName) {
				this.$set(this.sessionForm, 'bingshi', null);
			}
			if ('huanzhe' == this.userTableName) {
				this.$set(this.sessionForm, 'pquestion', null);
			}
			if ('huanzhe' == this.userTableName) {
				this.$set(this.sessionForm, 'panswer', null);
			}
			if ('yisheng' == this.userTableName) {
				this.$set(this.sessionForm, 'yishenggonghao', null);
			}
			if ('yisheng' == this.userTableName) {
				this.$set(this.sessionForm, 'mima', null);
			}
			if ('yisheng' == this.userTableName) {
				this.$set(this.sessionForm, 'yishengxingming', null);
			}
			if ('yisheng' == this.userTableName) {
				this.$set(this.sessionForm, 'touxiang', null);
			}
			if ('yisheng' == this.userTableName) {
				this.$set(this.sessionForm, 'zhixingzigezheng', null);
			}
			if ('yisheng' == this.userTableName) {
				this.$set(this.sessionForm, 'keshimingcheng', null);
			}
			if ('yisheng' == this.userTableName) {
				this.$set(this.sessionForm, 'zhicheng', null);
			}
			if ('yisheng' == this.userTableName) {
				this.$set(this.sessionForm, 'fuwushijian', null);
			}
			if ('yisheng' == this.userTableName) {
				this.$set(this.sessionForm, 'shanzhanglingyu', null);
			}
			if ('yisheng' == this.userTableName) {
				this.$set(this.sessionForm, 'zhiyelvli', null);
			}
			if ('yisheng' == this.userTableName) {
				this.$set(this.sessionForm, 'sfsh', null);
			}
			if ('yisheng' == this.userTableName) {
				this.$set(this.sessionForm, 'shhf', null);
			}
			if ('yisheng' == this.userTableName) {
				this.$set(this.sessionForm, 'pquestion', null);
			}
			if ('yisheng' == this.userTableName) {
				this.$set(this.sessionForm, 'panswer', null);
			}

			if ('huanzhe' == this.userTableName) {
				if (this.rules['yonghuzhanghao']){
					this.rules['yonghuzhanghao'].push({ required: true, message: '请输入用户账号', trigger: 'blur' })
				}else if(!this.rules['yonghuzhanghao']) {
					this.$set(this.rules, 'yonghuzhanghao', [{ required: true, message: '请输入用户账号', trigger: 'blur' }]);
				}
				if (this.rules['mima']){
					this.rules['mima'].push({ required: true, message: '请输入密码', trigger: 'blur' })
				}else if(!this.rules['mima']) {
					this.$set(this.rules, 'mima', [{ required: true, message: '请输入密码', trigger: 'blur' }]);
				}
				if (this.rules['yonghuxingming']){
					this.rules['yonghuxingming'].push({ required: true, message: '请输入用户姓名', trigger: 'blur' })
				}else if(!this.rules['yonghuxingming']) {
					this.$set(this.rules, 'yonghuxingming', [{ required: true, message: '请输入用户姓名', trigger: 'blur' }]);
				}
				this.$set(this.rules, 'nianling', [{ required: false, validator: this.$validate.isIntNumer, trigger: 'blur' }]);
				this.$set(this.rules, 'lianxifangshi', [{ required: false, validator: this.$validate.isMobile, trigger: 'blur' }]);
				this.ro = {
					yonghuzhanghao: true,
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
			if ('yisheng' == this.userTableName) {
				this.ro = {
					yishenggonghao: true,
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

			this.init();
			await this.$http.get(`${localStorage.getItem('UserTableName')}/session`, {emulateJSON: true}).then(async res => {
				if (res.data.code == 0) {
					this.sessionForm = res.data.data
				}
			});
		},
		//方法集合
		methods: {
			init() {
				if ('huanzhe' == this.userTableName) {
					this.dynamicProp.xingbie = '男,女'.split(',');
				}
				if ('yisheng' == this.userTableName) {
					this.dynamicProp.zhicheng = '专家,主任医师,副主任医师,主治医师'.split(',');
				}
			},
			setSession(){
				localStorage.setItem('sessionForm',JSON.stringify(this.sessionForm))
			},
			onSubmit(formName) {
				if(`huanzhe` == this.userTableName && (this.sessionForm.yonghuzhanghao).toString().length<3){
					this.$message.error('用户账号长度不能小于3');
					return
				}
				if(`huanzhe` == this.userTableName && (this.sessionForm.yonghuzhanghao).toString().length>16){
					this.$message.error('用户账号长度不能大于16');
					return
				}
				if(`huanzhe` == this.userTableName && this.sessionForm.touxiang!=null){
					this.sessionForm.touxiang = this.sessionForm.touxiang.replace(new RegExp(this.$config.baseUrl,"g"),"");
				}
				if(`yisheng` == this.userTableName && this.sessionForm.touxiang!=null){
					this.sessionForm.touxiang = this.sessionForm.touxiang.replace(new RegExp(this.$config.baseUrl,"g"),"");
				}
				if(`yisheng` == this.userTableName && this.sessionForm.zhixingzigezheng!=null){
					this.sessionForm.zhixingzigezheng = this.sessionForm.zhixingzigezheng.replace(new RegExp(this.$config.baseUrl,"g"),"");
				}
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.$http.post(this.userTableName + '/update', this.sessionForm).then(res => {
							if (res.data.code == 0) {
								this.setSession()
								this.$message({
									message: '更新成功',
									type: 'success',
									duration: 1500
								});
							}
						});
					} else {
						return false;
					}
				});
			},
			huanzhetouxiangHandleAvatarSuccess(fileUrls) {
				this.sessionForm.touxiang = fileUrls;
			},
			yishengtouxiangHandleAvatarSuccess(fileUrls) {
				this.sessionForm.touxiang = fileUrls;
			},
			yishengzhixingzigezhengHandleAvatarSuccess(fileUrls) {
				this.sessionForm.zhixingzigezheng = fileUrls;
			},
			handleClick(name) {
				switch(name) {
					case '个人中心':
						this.activeName = name
						this.$router.push('/index/center');
						break;
					case '修改密码':
						this.activeName = name
						this.passwordForm = {
							password: '',
							newpassword: '',
							repassword: '',
						}
						this.psdType = '1'
						this.$forceUpdate()
						break;
					case '我的收藏':
						localStorage.setItem('storeupType', 1);
						this.$router.push('/index/storeup');
						break;
					case '我的评论':
						localStorage.setItem('storeupType', 81);
						this.$router.push('/index/storeup');
						break;
				}

				this.title = event.target.outerText;
			},
			async updatePassword(){
				this.$refs["passwordForm"].validate(async valid => {
					if (valid) {
						var password = "";
						this.passwordForm.newpassword = this.passwordForm.newpassword.trim()
						this.passwordForm.repassword = this.passwordForm.repassword.trim()
						if (this.sessionForm.mima) {
							password = this.sessionForm.mima;
						} else if (this.sessionForm.password) {
							password = this.sessionForm.password;
						}
						if (this.userTableName == 'huanzhe') {
							if(this.passwordForm.newpassword && (this.passwordForm.newpassword.length<3)){
								this.$message.error(`新密码长度不能小于3`);
								return
							}
						}
						if (this.userTableName == 'yisheng') {
						}
						var nowpassword = ''
						await this.$http.get('encrypt/md5?text=' + this.passwordForm.password,).then(res=>{
							if(res.data&&res.data.code==0){
								nowpassword = res.data.data
							}
						})
						if(nowpassword!=password){
							this.$message.error("原密码错误");
							return;
						}
						if (this.passwordForm.newpassword != this.passwordForm.repassword) {
							this.$message.error("两次密码输入不一致");
							return;
						}
						if (this.passwordForm.newpassword == this.passwordForm.password) {
							this.$message.error("新密码与原密码相同！");
							return;
						}
						this.sessionForm.password = this.passwordForm.newpassword;
						this.sessionForm.mima = this.passwordForm.newpassword;
						this.$http.post(`${this.userTableName}/update`,this.sessionForm).then(({data})=>{
							if (data && data.code === 0) {
								this.$message({
									message: "修改密码成功,下次登录系统生效",
									type: "success",
									duration: 1500,
									onClose: () => {
									}
								});
								this.setSession()
							} else {
								this.$message.error(data.msg);
							}
						});
					}
				})
			},
			logout() {
				localStorage.clear();
				Vue.http.headers.common['Token'] = "";
				this.$router.push('/index/home');
				this.activeIndex = '0'
				localStorage.setItem('keyPath', this.activeIndex)
				this.$forceUpdate()
				this.$message({
					message: '登出成功',
					type: 'success',
					duration: 1500,
				});
			},
			hasBack(name,tablename){
				if(name.indexOf('章节')!=-1&&tablename.substring(0,7)=='chapter') {
					return false
				}
				return true
			},
			menuClick(row,length=1) {
				if(length==1) {
					if(row.tableName=='storeup') {
						localStorage.setItem('storeupType', row.menuJump);
						this.$router.push('/index/storeup');
						return false
					}
					this.$router.push(`/index/${row.tableName}?centerType=1`);
				}
			},
			centerTabEnter(name){
				this.showActive = name?('show' + name):''
			},
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.center-preview {
		padding: 0 15%;
		margin: 10px auto 30px;
		background: none;
		display: flex;
		width: 100%;
		justify-content: flex-start;
		align-items: flex-start;
		position: relative;
		flex-wrap: wrap;
		.center-title {
			padding: 10px;
			margin: 0 0 20px;
			color: #333;
			font-weight: 550;
			display: block;
			font-size: 28px;
			border-color: #ddd;
			line-height: 1.5;
			background: rgba(255,255,255,.1);
			width: 100%;
			border-width: 1px 0;
			border-style: solid;
			text-align: center;
		}
		.center-info {
			border: 1px solid #999;
			border-radius: 10px;
			padding: 0;
			box-shadow: none;
			margin: 20px 50px 0 0;
			color: #555;
			background: #ffffff;
			width: 280px;
			font-size: 16px;
			float: left;
			height: auto;
			.center-info-title {
				color: #333;
				font-weight: 500;
				width: 100%;
				font-size: 24px;
				border-color: #efefef;
				border-width: 1px 0;
				line-height: 60px;
				border-style: solid;
				text-align: center;
				height: 60px;
			}
			.img-box {
				width: 100%;
				font-size: 0;
				border-color: #efefef;
				border-width: 0 0 1px 0;
				border-style: solid;
				height: auto;
				img {
					border-radius: 100%;
					margin: 10px auto;
					object-fit: cover;
					display: block;
					width: 80px;
					border-color: #efefef;
					border-width: 0 0 1px 0;
					border-style: solid;
					height: 80px;
				}
			}
			.info-item1 {
				padding: 0 0px;
				display: inline-block;
				width: 100%;
				border-color: #efefef;
				border-width: 0 0 1px 0;
				line-height: 40px;
				border-style: solid;
				height: auto;
				.icon {
					padding: 0 5px;
					color: inherit;
					display: inline-block;
					font-size: inherit;
				}
				.label {
					color: inherit;
					display: inline-block;
					font-size: inherit;
				}
				.text {
					color: inherit;
					display: inline-block;
					font-size: inherit;
				}
			}
			.info-item2 {
				padding: 0 0px;
				display: inline-block;
				width: 100%;
				border-color: #efefef;
				border-width: 0 0 1px 0;
				line-height: 40px;
				border-style: solid;
				height: auto;
				.icon {
					padding: 0 5px;
					color: inherit;
					display: inline-block;
					font-size: inherit;
				}
				.label {
					color: inherit;
					display: inline-block;
					font-size: inherit;
				}
				.text {
					color: inherit;
					display: inline-block;
					font-size: inherit;
				}
			}
			.info-item3 {
				padding: 0 0px;
				display: inline-block;
				width: 100%;
				border-color: #efefef;
				border-width: 0 0 1px 0;
				line-height: 40px;
				border-style: solid;
				height: auto;
				.icon {
					padding: 0 5px;
					color: inherit;
					display: inline-block;
					font-size: inherit;
				}
				.label {
					color: inherit;
					display: inline-block;
					font-size: inherit;
				}
				.text {
					color: inherit;
					display: inline-block;
					font-size: inherit;
				}
			}
			.info-item4 {
				padding: 0 0px;
				display: inline-block;
				width: 100%;
				border-color: #efefef;
				border-width: 0 0 1px 0;
				line-height: 40px;
				border-style: solid;
				height: auto;
				.icon {
					padding: 0 5px;
					color: inherit;
					display: inline-block;
					font-size: inherit;
				}
				.label {
					color: inherit;
					display: inline-block;
					font-size: inherit;
				}
				.text {
					color: inherit;
					display: inline-block;
					font-size: inherit;
				}
			}
			.info-item5 {
				padding: 0 0px;
				display: inline-block;
				width: 100%;
				border-color: #efefef;
				border-width: 0 0 1px 0;
				line-height: 40px;
				border-style: solid;
				height: auto;
				.icon {
					padding: 0 5px;
					color: inherit;
					display: inline-block;
					font-size: inherit;
				}
				.label {
					color: inherit;
					display: inline-block;
					font-size: inherit;
				}
				.text {
					color: inherit;
					display: inline-block;
					font-size: inherit;
				}
			}
			.info-item6 {
				padding: 0 0px;
				display: inline-block;
				width: 100%;
				border-color: #efefef;
				border-width: 0 0 1px 0;
				line-height: 40px;
				border-style: solid;
				height: auto;
				.icon {
					padding: 0 5px;
					color: inherit;
					display: inline-block;
					font-size: inherit;
				}
				.label {
					color: inherit;
					display: inline-block;
					font-size: inherit;
				}
				.text {
					color: inherit;
					display: inline-block;
					font-size: inherit;
				}
			}
		}
		.center-box {
			border-radius: 10px;
			padding: 0;
			margin: 20px 0 0;
			background: none;
			flex: 1;
			width: calc(100% - 350px);
			.center-tab-view {
				padding: 10px 20px;
				background: rgba(255,255,255,.1);
				display: block;
				width: 100%;
				min-height: 66px;
				border-color: #e6e6e6;
				border-width: 1px 0 0;
				line-height: 1.5;
				border-style: solid;
				text-align: center;
			}
			.center-tab-view .center-tab {
				border: 0;
				padding: 0 0px;
				margin: 0 10px 0 0;
				color: #333;
				font-weight: 500;
				display: inline-block;
				font-size: 16px;
				line-height: 40px;
				float: left;
				background: none;
				position: relative;
				list-style: none;
				min-width: 100px;
				height: 40px;
				.center-second-tab-view {
					padding: 0 10px;
					z-index: 999;
					background: #fff;
					width: 100%;
					position: relative;
					.center-second-tab {
						color: #666;
						width: 100%;
						font-size: 15px;
						border-color: #eee;
						border-width: 0 0 1px;
						border-style: solid;
					}
					.center-second-tab:hover {
						cursor: pointer;
						color: #009899;
					}
				}
			}
			.center-tab-view .center-tab:hover {
				border-radius: 10px;
				padding: 0 0px;
				color: #fff;
				background: rgb(167, 5, 0);
				font-weight: 500;
				font-size: 16px;
				line-height: 40px;
				position: relative;
				text-align: center;
				height: 40px;
			}
			.center-tab-view .center-tab.is-active {
				padding: 0 0px;
				margin: 0 10px 0 0;
				color: #fff;
				font-weight: 500;
				font-size: 16px;
				line-height: 40px;
				float: left;
				border-radius: 10px;
				background: rgb(167, 5, 0);
				position: relative;
				text-align: center;
				min-width: 100px;
				height: 40px;
			}
			.center-content-box {
				padding: 30px 15% 30px 3%;
				overflow: hidden;
				background: #fff;
				width: 100%;
				clear: both;
			}
			.center-content-view {
				width: 100%;
			}
			.center-preview-pv {
				.center-item.el-form-item {
					padding: 0px;
					margin: 0 0 24px;
					background: none;
					::v-deep .el-form-item__label {
						padding: 0 10px 0 0;
						color: #666;
						white-space: nowrap;
						font-weight: 500;
						width: 180px;
						font-size: 16px;
						line-height: 40px;
						text-align: right;
					}
					.el-form-item__content {
						margin-left: 180px;
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
						color: #333;
						width: 100%;
						font-size: 16px;
						height: 40px;
					}
					.el-input ::v-deep .el-input__inner[readonly="readonly"] {
						border: 0px solid #ddd;
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
					.el-select {
						width: 100%;
					}
					.el-select ::v-deep .el-input__inner {
						border: 1px solid #ddd;
						border-radius: 4px;
						padding: 0 10px;
						box-shadow: none;
						outline: none;
						color: #333;
						width: 100%;
						font-size: 16px;
						height: 40px;
					}
					.el-select ::v-deep .is-disabled .el-input__inner {
						border: 0px solid #ddd;
						cursor: not-allowed;
						border-radius: 4px;
						padding: 0 10px;
						box-shadow: none;
						outline: none;
						color: #333;
						background: #eee;
						width: 100%;
						font-size: 16px;
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
						color: #333;
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
						color: #333;
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
						width: 80px;
						font-size: 26px;
						line-height: 80px;
						text-align: center;
						height: 80px;
					}
					::v-deep .el-upload-list .el-upload-list__item {
						border: 1px solid #ddd;
						cursor: pointer;
						border-radius: 6px;
						color: #999;
						width: 80px;
						font-size: 26px;
						line-height: 80px;
						text-align: center;
						height: 80px;
						font-size: 14px;
						line-height: 1.8;
					}
					::v-deep .el-upload .el-icon-plus {
						border: 1px solid #ddd;
						cursor: pointer;
						border-radius: 6px;
						color: #999;
						width: 80px;
						font-size: 26px;
						line-height: 80px;
						text-align: center;
						height: 80px;
					}
					::v-deep .el-upload__tip {
						color: #666;
						font-size: 15px;
					}
					::v-deep .el-input__inner::placeholder {
						color: #123;
						font-size: 16px;
					}
					.editor {
						border: 0px solid #ddd;
						border-radius: 4px;
						box-shadow: none;
						outline: none;
						color: #333;
						width: 100%;
						font-size: 14px;
						line-height: 32px;
					}
					.editor ::v-deep.ql-toolbar {
						background: none;
					}
					.editor ::v-deep.ql-container {
						background: none;
						min-height: 180px;
					}
					.editor ::v-deep.ql-container .ql-blank::before {
						color: #999;
					}
				}
				.center-btn-item {
					padding: 0;
					margin: 40px auto 0;
					background: none;
					width: 100%;
					.updateBtn {
						border: 0;
						cursor: pointer;
						border-radius: 4px;
						padding: 0 15px;
						margin: 0 20px 0 0;
						outline: none;
						background: rgb(167, 5, 0);
						display: inline-block;
						width: auto;
						font-size: 16px;
						line-height: 40px;
						height: 40px;
						.icon {
							color: rgba(255, 255, 255, 1);
						}
						.text {
							color: rgba(255, 255, 255, 1);
						}
					}
					.updateBtn:hover {
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
						border-radius: 4px;
						padding: 0 15px;
						margin: 0 20px 0 0;
						outline: none;
						background: rgb(231,138,135);
						display: inline-block;
						width: auto;
						font-size: 16px;
						line-height: 40px;
						height: 40px;
						.icon {
							color: #fff;
						}
						.text {
							color: #fff;
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
				.el-date-editor.el-input {
					width: auto;
				}
			}
		}
	}
</style>
