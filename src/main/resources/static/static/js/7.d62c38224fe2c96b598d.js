webpackJsonp([7],{"1oFJ":function(e,t){},"6WmZ":function(e,t,s){"use strict";Object.defineProperty(t,"__esModule",{value:!0});s("I29D"),s("atmG");var a={name:"home",inject:["reload"],data:function(){return{userInfoList:[],filters:{name:""},token:"",user:{}}},methods:{loadData:function(){var e=this;this.axios.get("record/usageRecord?id="+this.user.id).then(function(t){console.log(t),e.userInfoList=t.data.data})},getUsers:function(){var e=this;this.axios.get("record/findByNoLikeNoRent",{params:{bicycleNo:this.filters.name,userId:this.user.id}}).then(function(t){console.log("res:",t),e.userInfoList=t.data.data})},modifyUser:function(e){var t=this;console.log("id: ",e.id),this.axios.post("bicycle/rent",this.$qs.stringify({id:e.id,userId:this.user.id})).then(function(e){console.log("res: ",e),200===e.data.code?(t.$message({message:"租用成功",type:"success"}),t.reload()):(t.$message({message:e.data.message,type:"error"}),t.reload())})}},created:function(){this.user=this.$store.state.user.accessToken,console.log("user: ",this.user),this.loadData()}},i={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",[s("el-col",{staticClass:"toolbar",staticStyle:{"padding-bottom":"0px","margin-top":"15px"},attrs:{span:24}},[s("el-form",{attrs:{inline:!0,model:e.filters}},[s("el-form-item",[s("el-input",{attrs:{placeholder:"编号"},model:{value:e.filters.name,callback:function(t){e.$set(e.filters,"name",t)},expression:"filters.name"}})],1),e._v(" "),s("el-form-item",[s("el-button",{attrs:{type:"primary"},on:{click:e.getUsers}},[e._v("查询")])],1)],1)],1),e._v(" "),s("el-table",{staticStyle:{width:"100%"},attrs:{data:e.userInfoList}},[s("el-table-column",{attrs:{type:"index",width:"80"}}),e._v(" "),s("el-table-column",{attrs:{prop:"bicycleNo",label:"单车编号",width:"250"}}),e._v(" "),s("el-table-column",{attrs:{prop:"rentTime",label:"租车时间",width:"250"}}),e._v(" "),s("el-table-column",{attrs:{prop:"createTime",label:"创建时间",width:"250"}}),e._v(" "),s("el-table-column",{attrs:{prop:"updateTime",label:"修改时间",width:"250"}})],1)],1)},staticRenderFns:[]};var o=s("C7Lr")(a,i,!1,function(e){s("1oFJ")},null,null);t.default=o.exports}});
//# sourceMappingURL=7.d62c38224fe2c96b598d.js.map