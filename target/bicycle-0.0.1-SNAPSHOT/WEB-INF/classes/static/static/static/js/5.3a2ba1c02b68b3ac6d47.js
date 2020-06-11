webpackJsonp([5],{"+v2j":function(e,t){},g9zM:function(e,t,i){"use strict";Object.defineProperty(t,"__esModule",{value:!0});i("I29D"),i("atmG");var s={name:"home",inject:["reload"],data:function(){return{userInfoList:[],dialogVisible:!1,isView:!0,bicycle:{bicycleNo:"",color:""},rules2:{bicycleNo:[{required:!0,message:"编号不能为空",trigger:"blur"}],color:[{required:!0,message:"颜色不能为空",trigger:"blur"}]},filters:{name:""},token:"",isExists:"",user:{}}},methods:{isExist:function(){var e=this;this.isExists="",this.bicycle.bicycleNo&&this.axios.get("/bicycle/isExist?no="+this.bicycle.bicycleNo).then(function(t){console.log(t),t.data.data&&(e.isExists="该单位编码已存在"),console.log(e.isExists)})},loadData:function(){var e=this;this.axios.get(this.$baseUrl()+"/bicycle/list").then(function(t){console.log(t),e.userInfoList=t.data.data})},tableData:function(){var e={0:"未租用",1:"已租用"};return this.userInfoList.map(function(t){return t.statusName=e[t.isRent],t})},getUsers:function(){var e=this;this.axios.get("bicycle/findByNoLike",{params:{bicycleNo:this.filters.name}}).then(function(t){console.log("res:",t),e.userInfoList=t.data.data})},addUser:function(){this.bicycle={bicycleNo:"",color:""},this.isExists="",this.isView=!0,this.dialogVisible=!0},deleteUser:function(e){var t=this;console.log("id: ",e.id),this.axios.post("bicycle/scrap",this.$qs.stringify({id:e.id})).then(function(e){console.log("res: ",e),200===e.data.code?(t.$message({message:"删除成功",type:"success"}),t.reload()):(t.$message({message:e.data.message,type:"error"}),t.reload())})},addSubmit:function(){var e=this;this.$refs.bicycle.validate(function(t){if(t){console.log(e.bicycle);e.bicycle,e.user.id;e.axios.post("bicycle/add",e.$qs.stringify({bicycleNo:e.bicycle.bicycleNo,color:e.bicycle.color,userId:e.user.id})).then(function(t){console.log(t),200===t.data.code?(e.$message({type:"success",message:"添加成功"}),e.loadData()):e.$message({type:"error",message:t.data.message}),e.dialogVisible=!1})}})}},created:function(){this.user=this.$store.state.user.accessToken,console.log("user: ",this.user),this.loadData()}},l={render:function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",[i("el-col",{staticClass:"toolbar",staticStyle:{"padding-bottom":"0px","margin-top":"15px"},attrs:{span:24}},[i("el-form",{attrs:{inline:!0,model:e.filters}},[i("el-form-item",[i("el-input",{attrs:{placeholder:"编号"},model:{value:e.filters.name,callback:function(t){e.$set(e.filters,"name",t)},expression:"filters.name"}})],1),e._v(" "),i("el-form-item",[i("el-button",{attrs:{type:"primary"},on:{click:e.getUsers}},[e._v("查询")])],1),e._v(" "),i("el-form-item",[i("el-button",{attrs:{type:"primary"},on:{click:e.addUser}},[e._v("新增单车")])],1),e._v(" "),i("router-link",{attrs:{to:"/admin/import"}},[i("el-button",{attrs:{type:"primary"}},[e._v("导入单车")])],1)],1)],1),e._v(" "),i("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData()}},[i("el-table-column",{attrs:{prop:"bicycleNo",label:"编号",width:"90"}}),e._v(" "),i("el-table-column",{attrs:{prop:"color",label:"颜色",width:"180"}}),e._v(" "),i("el-table-column",{attrs:{prop:"statusName",label:"是否租赁",width:"180"}}),e._v(" "),i("el-table-column",{attrs:{prop:"createTime",label:"创建时间",width:"180"}}),e._v(" "),i("el-table-column",{attrs:{prop:"updateTime",label:"修改时间",width:"180"}}),e._v(" "),i("el-table-column",{attrs:{prop:"addUser.name",label:"添加人",width:"100"}}),e._v(" "),i("el-table-column",{attrs:{label:"操作",align:"center","min-width":"100"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("el-button",{attrs:{type:"danger"},on:{click:function(i){return e.deleteUser(t.row)}}},[e._v("报废")])]}}])})],1),e._v(" "),i("el-dialog",{attrs:{title:"记录",visible:e.dialogVisible,width:"500px","close-on-click-modal":!1,"destroy-on-close":!0},on:{"update:visible":function(t){e.dialogVisible=t}}},[i("el-form",{ref:"bicycle",staticClass:"demo-ruleForm",attrs:{model:e.bicycle,rules:e.rules2,"label-width":"0px"}},[i("el-form-item",{attrs:{prop:"bicycleNo",error:e.isExists}},[i("el-input",{attrs:{type:"text","auto-complete":"off",placeholder:"编号"},on:{blur:e.isExist},model:{value:e.bicycle.bicycleNo,callback:function(t){e.$set(e.bicycle,"bicycleNo",t)},expression:"bicycle.bicycleNo"}})],1),e._v(" "),i("el-form-item",{attrs:{prop:"color"}},[i("el-input",{attrs:{type:"text","auto-complete":"off",placeholder:"颜色"},model:{value:e.bicycle.color,callback:function(t){e.$set(e.bicycle,"color",t)},expression:"bicycle.color"}})],1)],1),e._v(" "),i("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{nativeOn:{click:function(t){e.dialogVisible=!1,e.bicycle={bicycleNo:"",color:""}}}},[e._v("取 消")]),e._v(" "),e.isView?i("el-button",{attrs:{type:"primary"},nativeOn:{click:function(t){return e.addSubmit(t)}}},[e._v("确 定")]):e._e()],1)],1)],1)},staticRenderFns:[]};var o=i("C7Lr")(s,l,!1,function(e){i("+v2j")},null,null);t.default=o.exports}});
//# sourceMappingURL=5.3a2ba1c02b68b3ac6d47.js.map