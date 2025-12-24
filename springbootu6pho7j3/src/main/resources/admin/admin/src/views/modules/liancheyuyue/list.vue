<template>
	<div class="main-content" :style='{"minHeight":"100vh","padding":"20px 20px 20px","overflow":"hidden","color":"#000","background":"#e9ecf3","fontSize":"16px","position":"relative"}'>
		<!-- 列表页 -->
		<template v-if="showFlag">
			<el-form class="center-form-pv" :style='{"border":"0px solid #ddd","padding":"20px 20px 0","margin":"0px 0 0","overflow":"hidden","borderRadius":"0px","flexWrap":"wrap","background":"#fff","display":"flex","width":"auto","fontSize":"inherit","justifyContent":"space-between"}' :inline="true" :model="searchForm">
				<el-row :style='{"padding":"0px","margin":"0 0 10px","borderRadius":"0px","alignItems":"center","textAlign":"center","flexWrap":"wrap","background":"#fff","flexDirection":"row","display":"flex","width":"auto","fontSize":"inherit","order":"2"}' >
					<div :style='{"margin":"0 10px 0 0","fontSize":"inherit","alignItems":"center","display":"flex"}'>
						<label :style='{"margin":"0 10px 0 0","color":"inherit","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"600","height":"40px"}' class="item-label">汽车名称</label>
						<el-input v-model="searchForm.qichemingcheng" placeholder="汽车名称" @keydown.enter.native="search()" clearable></el-input>
					</div>
					<div :style='{"margin":"0 10px 0 0","fontSize":"inherit","alignItems":"center","display":"flex"}'>
						<label :style='{"margin":"0 10px 0 0","color":"inherit","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"600","height":"40px"}' class="item-label">汽车类型</label>
						<el-input v-model="searchForm.qicheleixing" placeholder="汽车类型" @keydown.enter.native="search()" clearable></el-input>
					</div>
					<div :style='{"margin":"0 10px 0 0","fontSize":"inherit","alignItems":"center","display":"flex"}' class="select">
						<label :style='{"margin":"0 10px 0 0","color":"inherit","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"600","height":"40px"}' class="item-label">是否通过</label>
						<el-select clearable v-model="searchForm.sfsh" placeholder="是否通过">
							<el-option v-for="(item,index) in sfshOptions" v-bind:key="index" :label="item" :value="item"></el-option>
						</el-select>
					</div>
					<el-button class="search" type="success" @click="search()">
						<span class="icon iconfont icon-chakan14" :style='{"margin":"0 0px","fontSize":"inherit","color":"inherit","display":"block","height":"40px"}'></span>
						
					</el-button>
				</el-row>

				<el-row class="actions" :style='{"padding":"0px","margin":"0 0 10px","color":"#fff","flexWrap":"wrap","textAlign":"left","flexDirection":"row","background":"none","display":"flex","width":"auto","fontSize":"14px"}'>
					<el-button class="add" v-if="isAuth('liancheyuyue','新增')" type="success" @click="addOrUpdateHandler()">
						<span class="icon iconfont icon-tianjia1" :style='{"margin":"0 0px","fontSize":"inherit","color":"inherit","display":"inline-block"}'></span>
						新增
					</el-button>
					<el-button class="del" v-if="isAuth('liancheyuyue','删除')" :disabled="dataListSelections.length?false:true" type="danger" @click="deleteHandler()">
						<span class="icon iconfont icon-shanchu15" :style='{"margin":"0 0px","fontSize":"inherit","color":"inherit","display":"inline-block"}'></span>
						删除
					</el-button>


					<el-button class="btn18" v-if="isAuth('liancheyuyue','审核')" :disabled="dataListSelections.length?false:true" type="success" @click="shBatchDialog()">
						<span class="icon iconfont icon-shenhe2" :style='{"margin":"0 0px","fontSize":"inherit","color":"inherit","display":"inline-block"}'></span>
						审核
					</el-button>

				</el-row>
			</el-form>
			<div :style='{"border":"0px solid #bababa","padding":"0","margin":"0 0 0 0","borderRadius":"2px","background":"rgba(255,255,255,1)","width":"calc(100% - 0px)","fontSize":"14px","float":"right"}'>
				<el-table class="tables"
					:stripe='false'
					:style='{"padding":"0","borderColor":"#eee","color":"inherit","borderRadius":"0px","borderWidth":"0px 0px 0 0px","background":"none","width":"100%","fontSize":"inherit","borderStyle":"solid"}' 
					:border='true'
					v-if="isAuth('liancheyuyue','查看')"
					:data="dataList"
					v-loading="dataListLoading"
				@selection-change="selectionChangeHandler">
					<el-table-column :resizable='true' type="selection" align="center" width="50"></el-table-column>
					<el-table-column :resizable='true' :sortable='true' label="序号" type="index" width="50" />
					<el-table-column :resizable='true' :sortable='true'  
						prop="qichemingcheng"
						label="汽车名称">
						<template slot-scope="scope">
							{{scope.row.qichemingcheng}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="qicheleixing"
						label="汽车类型">
						<template slot-scope="scope">
							{{scope.row.qicheleixing}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true' prop="tupian" width="200" label="图片">
						<template slot-scope="scope">
							<div v-if="scope.row.tupian">
								<img v-if="scope.row.tupian.substring(0,4)=='http'" :src="scope.row.tupian.split(',')[0]" width="100" height="100" style="object-fit: cover" @click="imgPreView(scope.row.tupian.split(',')[0])">
								<img v-else :src="$base.url+scope.row.tupian.split(',')[0]" width="100" height="100" style="object-fit: cover" @click="imgPreView($base.url+scope.row.tupian.split(',')[0])">
							</div>
							<div v-else>无图片</div>
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="chepaihao"
						label="车牌号">
						<template slot-scope="scope">
							{{scope.row.chepaihao}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="kemu"
						label="科目">
						<template slot-scope="scope">
							{{scope.row.kemu}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="gonghao"
						label="工号">
						<template slot-scope="scope">
							{{scope.row.gonghao}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="jiaolianxingming"
						label="教练姓名">
						<template slot-scope="scope">
							{{scope.row.jiaolianxingming}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="yuyueshijian"
						label="预约时间">
						<template slot-scope="scope">
							{{scope.row.yuyueshijian}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="yonghuming"
						label="用户名">
						<template slot-scope="scope">
							{{scope.row.yonghuming}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="xingming"
						label="姓名">
						<template slot-scope="scope">
							{{scope.row.xingming}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="shouji"
						label="手机">
						<template slot-scope="scope">
							{{scope.row.shouji}}
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
							<el-button class="view" v-if=" isAuth('liancheyuyue','查看')" type="success" @click="addOrUpdateHandler(scope.row.id,'info')">
								<span class="icon iconfont icon-chakan2" :style='{"margin":"0 0px","fontSize":"inherit","color":"#3bb4f2","display":"inline-block"}'></span>
								查看
							</el-button>
							<el-button class="btn8" v-if="isAuth('liancheyuyue','学习记录')" @click="xuexijiluCrossAddOrUpdateHandler(scope.row,'cross','是','','','')" type="success">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 0px","fontSize":"inherit","color":"inherit","display":"inline-block"}'></span>
								学习记录
							</el-button>
							<el-button class="edit" v-if=" isAuth('liancheyuyue','修改')  && scope.row.sfsh=='待审核' " type="success" @click="addOrUpdateHandler(scope.row.id)">
								<span class="icon iconfont icon-xiugai10" :style='{"margin":"0 0px","fontSize":"inherit","color":"#339933","display":"inline-block"}'></span>
								修改
							</el-button>




							<el-button class="del" v-if="isAuth('liancheyuyue','删除') " type="primary" @click="deleteHandler(scope.row.id )">
								<span class="icon iconfont icon-shanchu6" :style='{"margin":"0 0px","fontSize":"inherit","color":"#dd514c","display":"inline-block"}'></span>
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
				prev-text="上一页 "
				next-text="下一页 "
				:hide-on-single-page="false"
				:style='{"padding":"20px","margin":"0 0 0 0","whiteSpace":"nowrap","color":"#333","textAlign":"right","background":"#fff","width":"100%","clear":"both","fontSize":"inherit","fontWeight":"500"}'
			></el-pagination>
		</template>
		
		<!-- 添加/修改页面  将父组件的search方法传递给子组件-->
		<add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>

		<xuexijilu-cross-add-or-update v-if="xuexijiluCrossAddOrUpdateFlag" :parent="this" ref="xuexijiluCrossaddOrUpdate"></xuexijilu-cross-add-or-update>

		
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
import axios from 'axios'
import AddOrUpdate from "./add-or-update";
import xuexijiluCrossAddOrUpdate from "../xuexijilu/add-or-update";
	export default {
		data() {
			return {
				searchForm: {
					key: ""
				},
				form:{},
				sfshOptions: [],
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
					shhf:[{ required: true, message: '审核回复不能为空', trigger: 'blur' },],
				},
				batchIds:[], 
				addOrUpdateFlag:false,
				xuexijiluCrossAddOrUpdateFlag: false,
				layouts: ["total","prev","pager","next","sizes","jumper"],
				previewImg: '',
				previewVisible: false,
			};
		},
		created() {
			this.init();
			this.getDataList();
			this.contentStyleChange()
		},
		mounted() {
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
		},
		components: {
			AddOrUpdate,
			xuexijiluCrossAddOrUpdate,
		},
		methods: {
			imgPreView(url){
				this.previewImg = url
				this.previewVisible = true
			},
			contentStyleChange() {
				this.contentPageStyleChange()
			},
			// 分页
			contentPageStyleChange(){
				let arr = []

				// if(this.contents.pageTotal) arr.push('total')
				// if(this.contents.pageSizes) arr.push('sizes')
				// if(this.contents.pagePrevNext){
				//   arr.push('prev')
				//   if(this.contents.pagePager) arr.push('pager')
				//   arr.push('next')
				// }
				// if(this.contents.pageJumper) arr.push('jumper')
				// this.layouts = arr.join()
				// this.contents.pageEachNum = 10
			},
			xuexijiluCrossAddOrUpdateHandler(row,type,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue){
				if(crossOptAudit=='是'&&row.sfsh!='是') {
					this.$message({
						message: "请审核通过后再操作",
						type: "success",
						duration: 1500,
						onClose: () => {
						}
					});
					return
				}
				this.showFlag = false;
				this.addOrUpdateFlag = false;
				this.xuexijiluCrossAddOrUpdateFlag = true;
				this.$storage.set('crossObj',row);
				this.$storage.set('crossTable','liancheyuyue');
				this.$storage.set('statusColumnName',statusColumnName);
				this.$storage.set('statusColumnValue',statusColumnValue);
				this.$storage.set('tips',tips);
				if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
					var obj = this.$storage.getObj('crossObj');
					for (var o in obj){
						if(o==statusColumnName && obj[o]==statusColumnValue){
							this.$message({
								message: tips,
								type: "success",
								duration: 1500,
								onClose: () => {
									this.getDataList();
								}
							});
							this.showFlag = true;
							this.xuexijiluCrossAddOrUpdateFlag = false;
							return;
						}
					}
				}
				this.$nextTick(() => {
					this.$refs.xuexijiluCrossaddOrUpdate.init(row.id,type);
				});
			},






		init () {
			this.sfshOptions = "是,否,待审核".split(',');
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
 			if(this.searchForm.qichemingcheng!='' && this.searchForm.qichemingcheng!=undefined){
				params['qichemingcheng'] = '%' + this.searchForm.qichemingcheng + '%'
			}
 			if(this.searchForm.qicheleixing!='' && this.searchForm.qicheleixing!=undefined){
				params['qicheleixing'] = '%' + this.searchForm.qicheleixing + '%'
			}
			if(this.searchForm.kemu!='' && this.searchForm.kemu!=undefined){
				params['kemu'] = this.searchForm.kemu
			}
			if(this.searchForm.sfsh!='' && this.searchForm.sfsh!=undefined){
				params['sfsh'] = this.searchForm.sfsh
			}
			let user = JSON.parse(this.$storage.getObj('userForm'))
			this.$http({
				url: "liancheyuyue/page",
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
			if(type!='info'){
				type = 'else';
			}
			this.$nextTick(() => {
				this.$refs.addOrUpdate.init(id,type);
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
					}).then(() => {
						this.$http({
							url: "liancheyuyue/shBatch?sfsh="+this.shBatchForm.sfsh+"&shhf="+this.shBatchForm.shhf,
							method: "post",
							data: this.batchIds
						}).then(({ data }) => {
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
		liancheyuyuestatusChange(e,row){
			if(row.status==0){
				row.passwordwrongnum = 0
			}
			this.$http({
				url:'liancheyuyue/update',
				method:'post',
				data:row
			}).then(res=>{
				if(row.status==1){
					this.$message.error('该用户已锁定')
				}else{
					this.$message.success('该用户已解除锁定')
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
					url: "liancheyuyue/delete",
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
	.center-form-pv .el-input /deep/ .el-input__inner {
				border: 1px solid #eee;
				border-radius: 2px;
				padding: 0 12px;
				color: inherit;
				background: #fff;
				width: 150px;
				font-size: inherit;
				height: 34px;
			}
	
	.center-form-pv .el-select /deep/ .el-input__inner {
				border: 1px solid #eee;
				border-radius: 2px;
				padding: 0 10px;
				color: inherit;
				background: #fff;
				width: 150px;
				font-size: inherit;
				height: 34px;
			}
	
	.center-form-pv .el-date-editor /deep/ .el-input__inner {
				border: 1px solid #eee;
				border-radius: 2px;
				padding: 0 10px 0 30px;
				color: inherit;
				background: #fff;
				width: 150px;
				font-size: inherit;
				height: 34px;
			}
	
	.center-form-pv .search {
				cursor: pointer;
				padding: 0 10px;
				margin: 0;
				color: #fff;
				font-size: 16px;
				border-color: #1576c2;
				line-height: 34px;
				transition: all 0.3s;
				border-radius: 2px;
				background: #5eb95e;
				width: auto;
				border-width: 0 0 0px;
				border-style: solid;
				min-width: 50px;
				height: 34px;
			}
	
	.center-form-pv .search:hover {
			}
	
	.center-form-pv .actions .add {
				border: 0px solid #4db14d;
				cursor: pointer;
				border-radius: 0px;
				padding: 0 10px;
				margin: 0 0 10px 0;
				color: #fff;
				background: #4db14d;
				width: auto;
				font-size: inherit;
				height: 30px;
			}
	
	.center-form-pv .actions .add:hover {
			}
	
	.center-form-pv .actions .del {
				border: 0px solid rgba(255, 91, 91, 0.1);
				cursor: pointer;
				border-radius: 0px;
				padding: 0 10px;
				margin: 0 0 10px 0;
				color: #fff;
				background: #dd514c;
				width: auto;
				font-size: inherit;
				height: 30px;
			}
	
	.center-form-pv .actions .del:hover {
			}
	
	.center-form-pv .actions .statis {
				border: 0px solid rgba(16, 196, 105, 0.1);
				cursor: pointer;
				border-radius: 0px;
				padding: 0 10px;
				margin: 0 0 10px 0;
				color: #fff;
				background: #3bb4f2;
				width: auto;
				font-size: inherit;
				height: 30px;
			}
	
	.center-form-pv .actions .statis:hover {
			}
	
	.center-form-pv .actions .btn18 {
				border: 1px solid rgba(114, 123, 132, 0.1);
				cursor: pointer;
				border-radius: 0px;
				padding: 0 10px;
				margin: 0 0 10px 0;
				color: #000;
				background: rgba(114, 123, 132, 0.15);
				width: auto;
				font-size: inherit;
				height: 30px;
			}
	
	.center-form-pv .actions .btn18:hover {
			}
	
	// table
	.el-table /deep/ .el-table__header-wrapper thead {
				color: inherit;
				background: #f3f4f6;
				width: 100%;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr {
				background: none;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr th {
				padding: 12px 0;
				color: #93a2a9;
				background: none;
				border-color: #ddd;
				border-width: 0 0px 1px 0;
				border-style: solid;
				text-align: center;
			}

	.el-table /deep/ .el-table__header-wrapper thead tr th .cell {
				padding: 0 10px;
				word-wrap: normal;
				color: #000;
				white-space: normal;
				font-weight: 600;
				display: inline-block;
				vertical-align: middle;
				font-size: 16px;
				line-height: 24px;
				text-overflow: ellipsis;
				word-break: break-all;
				width: 100%;
				position: relative;
			}

	
	.el-table /deep/ .el-table__body-wrapper tbody {
				padding: 0;
				width: 100%;
			}

	.el-table /deep/ .el-table__body-wrapper tbody tr {
				background: none;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding: 2px 0;
				color: inherit;
				background: none;
				font-size: inherit;
				border-color: #ddd;
				border-width: 0 0 0 0;
				border-style: solid;
				text-align: center;
			}
	
		
	.el-table /deep/ .el-table__body-wrapper tbody tr:hover td {
				padding: 2px 0;
				color: inherit;
				background: #f3f4f650;
				font-weight: 500;
				border-color: #ddd;
				border-width: 0 0 0 0;
				border-style: solid;
				text-align: center;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding: 2px 0;
				color: inherit;
				background: none;
				font-size: inherit;
				border-color: #ddd;
				border-width: 0 0 0 0;
				border-style: solid;
				text-align: center;
			}

	.el-table /deep/ .el-table__body-wrapper tbody tr td .cell {
				padding: 0 10px;
				overflow: hidden;
				color: inherit;
				word-break: break-all;
				white-space: normal;
				line-height: 24px;
				text-overflow: ellipsis;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view {
				border: 1px solid #d9d9d9;
				cursor: pointer;
				border-radius: 0px;
				padding: 0 10px;
				margin: 0 0px 5px 0;
				color: #000;
				background: #fff;
				width: auto;
				font-size: 14px;
				height: 26px;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view:hover {
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .add {
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .add:hover {
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit {
				border: 1px solid #d9d9d9;
				cursor: pointer;
				border-radius: 0px;
				padding: 0 10px;
				margin: 0 0px 5px 0;
				color: #000;
				background: #fff;
				width: auto;
				font-size: 14px;
				min-width: 40px;
				height: 26px;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit:hover {
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del {
				border: 1px solid #d9d9d9;
				cursor: pointer;
				border-radius: 0px;
				padding: 0 10px;
				margin: 0 0px 5px 0;
				color: #000;
				background: #fff;
				width: auto;
				font-size: 14px;
				height: 26px;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del:hover {
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .btn8 {
				border: 1px solid #d9d9d9;
				cursor: pointer;
				border-radius: 0px;
				padding: 0 10px;
				margin: 0 0px 5px 0;
				color: #000;
				background: #fff;
				width: auto;
				font-size: 14px;
				height: 26px;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .btn8:hover {
			}
	
	// pagination
	.main-content .el-pagination /deep/ .el-pagination__total {
				margin: 0 10px 0 0;
				color: inherit;
				font-weight: 400;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-prev {
				border: none;
				border-radius: 2px;
				padding: 0 5px;
				margin: 0 5px;
				color: inherit;
				background: none;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 28px;
				min-width: 35px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-next {
				border: none;
				border-radius: 2px;
				padding: 0 5px;
				margin: 0 5px;
				color: inherit;
				background: none;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 28px;
				min-width: 35px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-prev:disabled {
				border: none;
				cursor: not-allowed;
				border-radius: 2px;
				padding: 0 5px;
				margin: 0 5px;
				color: #666;
				background: none;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-next:disabled {
				border: none;
				cursor: not-allowed;
				border-radius: 2px;
				padding: 0 5px;
				margin: 0 5px;
				color: #666;
				background: none;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 28px;
				height: 28px;
			}

	.main-content .el-pagination /deep/ .el-pager {
				padding: 0;
				margin: 0;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
			}

	.main-content .el-pagination /deep/ .el-pager .number {
				cursor: pointer;
				border: 1px solid #ddd;
				padding: 0 4px;
				margin: 0 5px;
				color: #23abf0;
				background: none;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 24px;
				text-align: center;
				height: 24px;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number:hover {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 5px;
				color: #fff;
				background: #23abf0;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 24px;
				text-align: center;
				height: 24px;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number.active {
				cursor: default;
				padding: 0 4px;
				margin: 0 5px;
				color: #fff;
				background: #23abf0;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 24px;
				text-align: center;
				height: 24px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes {
				color: inherit;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input {
				margin: 0 5px;
				color: inherit;
				width: 100px;
				font-size: inherit;
				position: relative;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__inner {
				border: 0px solid #ddd;
				cursor: pointer;
				padding: 0 25px 0 8px;
				color: inherit;
				display: inline-block;
				font-size: inherit;
				line-height: 28px;
				border-radius: 3px;
				outline: 0;
				background: none;
				width: 100%;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input span.el-input__suffix {
				top: 0;
				position: absolute;
				right: 0;
				height: 100%;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
				cursor: pointer;
				color: #C0C4CC;
				width: 25px;
				font-size: 14px;
				line-height: 28px;
				text-align: center;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump {
				margin: 0 0 0 24px;
				color: inherit;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input {
				border-radius: 3px;
				padding: 0 2px;
				margin: 0 2px;
				color: inherit;
				display: inline-block;
				width: 50px;
				font-size: inherit;
				line-height: 18px;
				position: relative;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input .el-input__inner {
				border: 1px solid #eee;
				cursor: pointer;
				padding: 0 0px;
				color: inherit;
				display: inline-block;
				font-size: inherit;
				line-height: 24px;
				border-radius: 3px;
				outline: 0;
				background: none;
				width: auto;
				text-align: center;
				height: 24px;
			}
	
	// list one
	.one .list1-view {
				border: 0px solid #0260ad30;
				cursor: pointer;
				border-radius: 0px;
				padding: 0 6px 0 4px;
				margin: 0 0 5px 0;
				color: #fff;
				background: #5eb95e;
				width: auto;
				font-size: 12px;
				height: 30px;
			}
	
	.one .list1-view:hover {
				transform: rotate(3deg) translate3d(0px, 3px, 0px);
			}
	
	.one .list1-edit {
				border: 0px solid #ddd;
				cursor: pointer;
				border-radius: 0px;
				padding: 0 6px 0 4px;
				margin: 0 0 5px 0;
				color: #fff;
				background: #3bb4f2;
				width: auto;
				font-size: 12px;
				height: 30px;
			}
	
	.one .list1-edit:hover {
				transform: rotate(3deg) translate3d(0px, 3px, 0px);
			}
	
	.one .list1-del {
				border: 0px solid #ddd;
				cursor: pointer;
				border-radius: 0px;
				padding: 0 6px 0 4px;
				margin: 0 0 5px 0;
				color: #fff;
				background: #dd514c;
				width: auto;
				font-size: 12px;
				height: 30px;
			}
	
	.one .list1-del:hover {
				transform: rotate(3deg) translate3d(0px, 3px, 0px);
			}
	
	.one .list1-btn8 {
				border: 1px solid #ddd;
				cursor: pointer;
				border-radius: 0px;
				padding: 0 6px 0 4px;
				margin: 0 0 5px 0;
				color: #333;
				background: rgba(114, 123, 132, 0.15);
				width: auto;
				font-size: 12px;
				height: 30px;
			}
	
	.one .list1-btn8:hover {
				transform: rotate(3deg) translate3d(0px, 3px, 0px);
			}
	
	.main-content .el-table .el-switch {
				display: inline-flex;
				vertical-align: middle;
				line-height: 30px;
				position: relative;
				align-items: center;
				height: 30px;
			}
	.main-content .el-table .el-switch /deep/ .el-switch__label--left {
				cursor: pointer;
				margin: 0 10px 0 0;
				color: #333;
				font-weight: 500;
				display: inline-block;
				vertical-align: middle;
				font-size: 16px;
				transition: .2s;
				height: 30px;
			}
	.main-content .el-table .el-switch /deep/ .el-switch__label--right {
				cursor: pointer;
				margin: 0 0 0 10px;
				color: #333;
				font-weight: 500;
				display: inline-block;
				vertical-align: middle;
				font-size: 16px;
				transition: .2s;
				height: 30px;
			}
	.main-content .el-table .el-switch /deep/ .el-switch__core {
				border: 0px solid #188ae2;
				cursor: pointer;
				border-radius: 20px;
				margin: 0;
				outline: 0;
				background: #b0d5f6;
				display: inline-block;
				width: 40px;
				box-sizing: border-box;
				transition: border-color .3s,background-color .3s;
				height: 18px;
			}
	.main-content .el-table .el-switch /deep/ .el-switch__core::after {
				border-radius: 100%;
				top: 1px;
				left: 1px;
				background: #FFF;
				width: 16px;
				position: absolute;
				transition: all .3s;
				height: 16px;
			}
	.main-content .el-table .el-switch.is-checked /deep/ .el-switch__core::after {
				margin: 0 0 0 -17px;
				left: 100%;
			}
	
	.main-content .el-table .el-rate /deep/ .el-rate__item {
				cursor: pointer;
				display: inline-block;
				vertical-align: middle;
				font-size: 0;
				position: relative;
			}
	.main-content .el-table .el-rate /deep/ .el-rate__item .el-rate__icon {
				margin: 0 3px;
				color: #212529;
				display: inline-block;
				font-size: 18px;
				position: relative;
				transition: .3s;
			}

</style>
