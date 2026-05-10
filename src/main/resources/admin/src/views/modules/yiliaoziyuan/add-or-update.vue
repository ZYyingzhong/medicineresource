


























<template>
	<div class="addEdit-block">
		<el-form
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="180px"
		>
			<template >
				<el-form-item class="input" v-if="type!='info'" label="资源编号" prop="ziyuanbianhao" >
					<el-input v-model="ruleForm.ziyuanbianhao" placeholder="资源编号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.ziyuanbianhao" label="资源编号" prop="ziyuanbianhao" >
					<el-input v-model="ruleForm.ziyuanbianhao" placeholder="资源编号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="资源名称" prop="ziyuanmingcheng" >
					<el-input v-model="ruleForm.ziyuanmingcheng" placeholder="资源名称" clearable  :readonly="ro.ziyuanmingcheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="资源名称" prop="ziyuanmingcheng" >
					<el-input v-model="ruleForm.ziyuanmingcheng" placeholder="资源名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.ziyuantupian" label="资源图片" prop="ziyuantupian" >
					<file-upload
						tip="点击上传资源图片"
						action="file/upload"
						:limit="3"
						:disabled="ro.ziyuantupian"
						:multiple="true"
						:fileUrls="ruleForm.ziyuantupian?ruleForm.ziyuantupian:''"
						@change="ziyuantupianUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.ziyuantupian" label="资源图片" prop="ziyuantupian" >
					<img v-if="ruleForm.ziyuantupian.substring(0,4)=='http'&&ruleForm.ziyuantupian.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.ziyuantupian" width="100" height="100" @click="imgPreView(ruleForm.tupian)">
					<img v-else-if="ruleForm.ziyuantupian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.ziyuantupian.split(',')[0]" width="100" height="100" @click="imgPreView(ruleForm.tupian.split(',')[0])">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.ziyuantupian.split(',')" :src="$base.url+item" width="100" height="100" @click="imgPreView($base.url+item)">
				</el-form-item>
				<el-form-item class="select" v-if="type!='info'"  label="资源分类" prop="ziyuanfenlei" >
					<el-select :disabled="ro.ziyuanfenlei" v-model="ruleForm.ziyuanfenlei" placeholder="请选择资源分类"  filterable>
						<el-option
							v-for="(item,index) in ziyuanfenleiOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item v-else class="input" label="资源分类" prop="ziyuanfenlei" >
					<el-input v-model="ruleForm.ziyuanfenlei"
						placeholder="资源分类" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="注意事项" prop="zhuyishixiang" >
					<el-input v-model="ruleForm.zhuyishixiang" placeholder="注意事项" clearable  :readonly="ro.zhuyishixiang"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="注意事项" prop="zhuyishixiang" >
					<el-input v-model="ruleForm.zhuyishixiang" placeholder="注意事项" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="资源价格" prop="ziyuanjiage" >
					<el-input-number v-model="ruleForm.ziyuanjiage" placeholder="资源价格" :disabled="ro.ziyuanjiage"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="资源价格" prop="ziyuanjiage" >
					<el-input v-model="ruleForm.ziyuanjiage" placeholder="资源价格" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="资源数量" prop="ziyuanshuliang" >
					<el-input v-model.number="ruleForm.ziyuanshuliang" placeholder="资源数量" clearable  :readonly="ro.ziyuanshuliang"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="资源数量" prop="ziyuanshuliang" >
					<el-input v-model="ruleForm.ziyuanshuliang" placeholder="资源数量" readonly></el-input>
				</el-form-item>
			</template>
			<el-form-item class="textarea" v-if="type!='info'" label="资源用途" prop="ziyuanyongtu" >
				<el-input
					style="min-width: 200px; max-width: 600px;"
					type="textarea"
					:rows="8"
					placeholder="资源用途"
					v-model="ruleForm.ziyuanyongtu" >
				</el-input>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.ziyuanyongtu" label="资源用途" prop="ziyuanyongtu"  class="textBox">
				<span class="text">{{ruleForm.ziyuanyongtu}}</span>
			</el-form-item>
			<el-form-item v-if="type!='info'" class="editorBox" label="资源简介" prop="ziyuanjianjie" >
				<editor 
					style="min-width: 200px; max-width: 600px;"
					v-model="ruleForm.ziyuanjianjie" 
					class="editor"
					myQuillEditor="ziyuanjianjie"
					action="file/upload">
				</editor>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.ziyuanjianjie" label="资源简介" prop="ziyuanjianjie"  class="textBox">
				<span class="text ql-snow ql-editor" v-html="ruleForm.ziyuanjianjie"></span>
			</el-form-item>
			<el-form-item class="btn">
				<el-button class="btn3"  v-if="type!='info'" type="success" @click="onSubmit">
					<span class="icon iconfont icon-queren15"></span>
					确定
				</el-button>
				<el-button class="btn4" v-if="type!='info'" type="success" @click="back()">
					<span class="icon iconfont icon-guanbi2"></span>
					撤销
				</el-button>
				<el-button class="btn5" v-if="type=='info'" type="success" @click="back()">
					<span class="icon iconfont icon-fanhui13"></span>
					返回
				</el-button>
			</el-form-item>
		</el-form>
    

	</div>
</template>
<script>
	import { 
		isNumber,
		isIntNumer,
	} from "@/utils/validate";
	export default {
		data() {
			var validateNumber = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isNumber(value)) {
					callback(new Error("请输入数字"));
				} else {
					callback();
				}
			};
			var validateIntNumber = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isIntNumer(value)) {
					callback(new Error("请输入整数"));
				} else {
					callback();
				}
			};
			return {
				id: '',
				type: '',
			
			
				ro:{
					ziyuanbianhao : false,
					ziyuanmingcheng : false,
					ziyuantupian : false,
					ziyuanfenlei : false,
					zhuyishixiang : false,
					ziyuanjiage : false,
					ziyuanshuliang : false,
					ziyuanyongtu : false,
					ziyuanjianjie : false,
					clicktime : false,
					clicknum : false,
					discussnum : false,
					storeupnum : false,
				},
			
				ruleForm: {
					ziyuanbianhao: this.getUUID(),
					ziyuanmingcheng: '',
					ziyuantupian: '',
					ziyuanfenlei: '',
					zhuyishixiang: '',
					ziyuanjiage: '',
					ziyuanshuliang: '',
					ziyuanyongtu: '',
					ziyuanjianjie: '',
					clicktime: '',
					clicknum: Number('0'),
					discussnum: Number('0'),
					storeupnum: Number('0'),
				},
				ziyuanfenleiOptions: [],

				rules: {
					ziyuanbianhao: [
						{ required: true, message: '资源编号不能为空', trigger: 'blur' },
					],
					ziyuanmingcheng: [
						{ required: true, message: '资源名称不能为空', trigger: 'blur' },
					],
					ziyuantupian: [
					],
					ziyuanfenlei: [
					],
					zhuyishixiang: [
					],
					ziyuanjiage: [
						{ required: true, message: '资源价格不能为空', trigger: 'blur' },
						{ validator: validateNumber, trigger: 'blur' },
					],
					ziyuanshuliang: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					ziyuanyongtu: [
					],
					ziyuanjianjie: [
					],
					clicktime: [
					],
					clicknum: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					discussnum: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					storeupnum: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
				},
			};
		},
		props: ["parent"],
		computed: {
			sessionForm() {
				return JSON.parse(this.$storage.getObj('userForm'))
			},
			sessionTable() {
				return this.$storage.get('sessionTable')
			},



		},
		components: {
		},
		created() {
		},
		methods: {
			imgPreView(url){
				this.$parent.imgPreView(url)
			},
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(id,type ) {
				if (id) {
					this.id = id;
					this.type = type;
				}
				if(this.type=='info'||this.type=='else'||this.type=='msg'){
					this.info(id);
				}else if(this.type=='logistics'){
					for(let x in this.ro) {
						this.ro[x] = true
					}
					this.logistics=false;
					this.info(id);
				}else if(this.type=='cross'){
					var obj = this.$storage.getObj('crossObj');
					for (var o in obj){
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
							this.ruleForm.ziyuantupian = obj[o];
							this.ro.ziyuantupian = true;
							continue;
						}
						if(o=='ziyuanfenlei'){
							this.ruleForm.ziyuanfenlei = obj[o];
							this.ro.ziyuanfenlei = true;
							continue;
						}
						if(o=='zhuyishixiang'){
							this.ruleForm.zhuyishixiang = obj[o];
							this.ro.zhuyishixiang = true;
							continue;
						}
						if(o=='ziyuanjiage'){
							this.ruleForm.ziyuanjiage = obj[o];
							this.ro.ziyuanjiage = true;
							continue;
						}
						if(o=='ziyuanshuliang'){
							this.ruleForm.ziyuanshuliang = obj[o];
							this.ro.ziyuanshuliang = true;
							continue;
						}
						if(o=='ziyuanyongtu'){
							this.ruleForm.ziyuanyongtu = obj[o];
							this.ro.ziyuanyongtu = true;
							continue;
						}
						if(o=='ziyuanjianjie'){
							this.ruleForm.ziyuanjianjie = obj[o];
							this.ro.ziyuanjianjie = true;
							continue;
						}
						if(o=='clicktime'){
							this.ruleForm.clicktime = obj[o];
							this.ro.clicktime = true;
							continue;
						}
						if(o=='clicknum'){
							this.ruleForm.clicknum = obj[o];
							this.ro.clicknum = true;
							continue;
						}
						if(o=='discussnum'){
							this.ruleForm.discussnum = obj[o];
							this.ro.discussnum = true;
							continue;
						}
						if(o=='storeupnum'){
							this.ruleForm.storeupnum = obj[o];
							this.ro.storeupnum = true;
							continue;
						}
					}
				}

				// 获取用户信息
				this.$http({
					url: `${this.sessionTable}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
					} else {
						this.$message.error(data.msg);
					}
				});
				this.$http({
					url: `option/ziyuanfenlei/ziyuanfenlei`,
					method: "get",
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ziyuanfenleiOptions = data.data;
					} else {
						this.$message.error(data.msg);
					}
				});
			
			},
			// 多级联动参数

			async info(id) {
				await this.$http({
					url: `yiliaoziyuan/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
						this.ruleForm.ziyuanjianjie = this.ruleForm.ziyuanjianjie.replace(reg,'../../../medicine/upload');
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					await this.$refs["ruleForm"].validate(async valid => {
						if (valid) {
							if(this.ruleForm.ziyuanbianhao) {
								this.ruleForm.ziyuanbianhao = String(this.ruleForm.ziyuanbianhao)
							}
							if(this.ruleForm.ziyuantupian!=null) {
								this.ruleForm.ziyuantupian = this.ruleForm.ziyuantupian.replace(new RegExp(this.$base.url,"g"),"");
							}
							var objcross = this.$storage.getObj('crossObj');
							if(!this.ruleForm.id) {
								delete this.ruleForm.userid
							}
							if(this.type=='cross'){
								var statusColumnName = this.$storage.get('statusColumnName');
								var statusColumnValue = this.$storage.get('statusColumnValue');
								if(statusColumnName!='') {
									var obj = this.$storage.getObj('crossObj');
									if(statusColumnName && !statusColumnName.startsWith("[")) {
										for (var o in obj){
											if(o==statusColumnName){
												obj[o] = statusColumnValue;
											}
										}
										var table = this.$storage.get('crossTable');
										await this.$http({
											url: `${table}/update`,
											method: "post",
											data: obj
										}).then(({ data }) => {});
									}
								}
							}
							await this.$http({
								url: `yiliaoziyuan/${!this.ruleForm.id ? "save" : "update"}`,
								method: "post",
								data: this.ruleForm
							}).then(async ({ data }) => {
								if (data && data.code === 0) {
									this.$message({
										message: "操作成功",
										type: "success",
										duration: 1500,
										onClose: () => {
											this.parent.showFlag = true;
											this.parent.addOrUpdateFlag = false;
											this.parent.yiliaoziyuanCrossAddOrUpdateFlag = false;
											this.parent.search();
										}
									});
								} else {
									this.$message.error(data.msg);
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
				this.parent.showFlag = true;
				this.parent.addOrUpdateFlag = false;
				this.parent.yiliaoziyuanCrossAddOrUpdateFlag = false;
			},
			ziyuantupianUploadChange(fileUrls) {
				this.ruleForm.ziyuantupian = fileUrls;
			},
		}
	};
</script>
<style lang="scss" scoped>
	.addEdit-block {
		padding: 30px;
	}
	.add-update-preview {
		border: 4px solid #8ebc74;
		border-radius: 12px;
		padding: 40px 30px;
		box-shadow: none;
		background: #ffffff;
		display: flex;
		flex-wrap: wrap;
	}
	.amap-wrapper {
		width: 100%;
		height: 500px;
	}
	
	.search-box {
		position: absolute;
	}
	
	.el-date-editor.el-input {
		width: auto;
	}
	.add-update-preview ::v-deep .el-form-item {
		margin: 0 0 20px 0;
		width: 100%;
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
	.add-update-preview ::v-deep .el-form-item.editorBox {
		margin: 0 0 20px 0;
		width: 100%;
	}
	.add-update-preview .el-form-item.editorBox ::v-deep .el-form-item__label {
		padding: 0 10px 0 0;
		color: #666;
		font-weight: 500;
		width: 180px;
		font-size: 14px;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item.editorBox ::v-deep .el-form-item__content {
		margin: 0;
		display: flex;
		align-items: center;
	}
	.add-update-preview ::v-deep.el-form-item.editorBox .editor {
		box-shadow: none;
		max-width: 100% !important;
		width: 100%;
		height: auto;
	}
	.add-update-preview ::v-deep.el-form-item.editorBox .editor .ql-toolbar {
		border: 1px solid #8ebc74;
		background: none;
		border-width: 1px 1px 0;
	}
	.add-update-preview ::v-deep.el-form-item.editorBox .editor .ql-container {
		border: 1px solid #8ebc74;
		background: none;
		min-height: 200px;
	}
	.add-update-preview ::v-deep.el-form-item.editorBox .editor .ql-container .ql-blank::before {
	}
	
	.add-update-preview ::v-deep .el-form-item.textBox {
		margin: 0 0 20px 0;
		width: 100%;
	}
	.add-update-preview .el-form-item.textBox ::v-deep .el-form-item__label {
		padding: 0 10px 0 0;
		color: #666;
		font-weight: 500;
		width: 180px;
		font-size: 16px;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item.textBox ::v-deep .el-form-item__content {
		margin: 0 0 0 80px;
		display: flex;
		align-items: center;
	}
	.add-update-preview ::v-deep.el-form-item.textBox span.text {
		padding: 0;
		color: #666;
		font-weight: 500;
		display: inline-block;
		font-size: 16px;
		line-height: 40px;
	}
	
	.add-update-preview .el-input {
		width: 100%;
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
	.add-update-preview .el-input ::v-deep .el-input__inner[readonly="readonly"] {
		border: 0px solid #ddd;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 12px;
		box-shadow: none;
		outline: none;
		color: #999;
		width: auto;
		font-size: 16px;
		min-width: 35%;
		height: 40px;
	}
	.add-update-preview .el-input-number {
		text-align: left;
		width: 100%;
	}
	.add-update-preview .el-input-number ::v-deep .el-input__inner {
		text-align: left;
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
	.add-update-preview .el-input-number ::v-deep .is-disabled .el-input__inner {
		text-align: left;
		border: 0px solid #ddd;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 12px;
		box-shadow: none;
		outline: none;
		color: #999;
		width: auto;
		font-size: 16px;
		min-width: 35%;
		height: 40px;
	}
	.add-update-preview .el-input-number ::v-deep .el-input-number__decrease {
		display: none;
	}
	.add-update-preview .el-input-number ::v-deep .el-input-number__increase {
		display: none;
	}
	.add-update-preview .el-select {
		width: auto;
		min-width: 35%;
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
	.add-update-preview .el-select ::v-deep .is-disabled .el-input__inner {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 10px;
		box-shadow: none;
		outline: none;
		color: #999;
		background: #f8f8f8;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-date-editor {
		width: auto;
		min-width: 35%;
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
	.add-update-preview .el-date-editor ::v-deep .el-input__inner[readonly="readonly"] {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 10px 0 30px;
		box-shadow: none;
		outline: none;
		color: #999;
		background: #f8f8f8;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .viewBtn {
		border: 0px solid #ff7f0050;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		outline: none;
		color: #fff;
		background: #66d3c7;
		width: auto;
		font-size: 14px;
		line-height: 40px;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 40px;
		}
	}
	.add-update-preview .viewBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .downBtn {
		border: 0px solid #ff7f0050;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		outline: none;
		color: #fff;
		background: #66d3c7;
		width: auto;
		font-size: 14px;
		line-height: 40px;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 40px;
		}
	}
	.add-update-preview .downBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .unBtn {
		border: 0px solid #ff7f0050;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		outline: none;
		color: #333;
		background: #eee;
		width: auto;
		font-size: 14px;
		line-height: 40px;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 40px;
		}
	}
	.add-update-preview .unBtn:hover {
		opacity: 0.8;
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
	
	.add-update-preview ::v-deep .upload .upload-img {
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
	.add-update-preview ::v-deep .el-upload__tip {
		color: #666;
		font-size: 15px;
	}
	.add-update-preview ::v-deep .el-form-item.fileupload {
	}
	.add-update-preview .el-form-item.fileupload ::v-deep .el-form-item__label {
	}
	
	.add-update-preview .el-form-item.fileupload ::v-deep .el-form-item__content {
	}
	.add-update-preview .el-form-item.fileupload ::v-deep .el-upload-dragger {
	}
	.add-update-preview .el-form-item.fileupload .el-upload-dragger ::v-deep .el-icon-upload {
	}
	.add-update-preview .el-form-item.fileupload .el-upload-dragger ::v-deep .el-upload__text {
	}
	.add-update-preview .el-form-item.fileupload .el-upload-dragger ::v-deep .el-upload__text em {
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
	.add-update-preview .el-textarea ::v-deep .el-textarea__inner[readonly="readonly"] {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 12px;
		box-shadow: none;
		outline: none;
		color: #666;
		width: auto;
		font-size: 16px;
		min-width: 80%;
		height: auto;
	}
	.add-update-preview ::v-deep .el-form-item.btn {
		padding: 0 50px;
		margin: 30px auto 0;
		display: flex;
		width: 100%;
		justify-content: flex-end;
		.btn1 {
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
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 16px;
				height: 40px;
			}
		}
		.btn1:hover {
			opacity: 0.8;
		}
		.btn2 {
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
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 16px;
				height: 40px;
			}
		}
		.btn2:hover {
			opacity: 0.8;
		}
		.btn3 {
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
		.btn3:hover {
			opacity: 0.8;
		}
		.btn4 {
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
		.btn4:hover {
			opacity: 0.8;
		}
		.btn5 {
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
		.btn5:hover {
			opacity: 0.8;
		}
	}
	.add-update-preview .el-form-item.btn ::v-deep .el-form-item__label {
		padding: 0 10px 0 0;
		color: #666;
		white-space: nowrap;
		font-weight: 500;
		width: 180px;
		font-size: 16px;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item.btn ::v-deep .el-form-item__content {
	}
</style>
