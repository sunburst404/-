<script>
export default {
  name: "Person",
  data(){
    return {
      tableData: [],
      total:0,
      pageNum: 1,
      pageSize: 2,
      username : "",
      age : 18,
      sex : "1",
      no :"",
      password:"",
      multipleSelection:[],
      form : {},
      dialogVisible : false,
      headerBg:'headerBg'
    }
  },
  created(){
    this.load()
  },
  methods:{
    load(){
      this.request.get("/user/page", {
            params: {
              pageNum: this.pageNum,
              pageSize: this.pageSize,
              username: this.username,
              no : this.no

            }
          }
      ).then(res =>{
        console.log(res)
        this.tableData = res.records
        this.total = res.total
      })

    },
    save(){
      this.request.post("/user",this.form).then(res => {
        if(res){
          this.$message.success("保存成功")
          this.dialogVisible = false
          this.load()
        }else {
          this.$message.error("保存失败")
        }
      })

    },
    handleSelectionChange(val){
      this.multipleSelection = val
    },
    delBatch(){
      let ids= this.multipleSelection.map(v =>v.id)
      this.request.post("/user/del/batch" , ids).then(res =>{
        if(res){
          this.$message.success("批量删除成功")
          this.load()
        }else {
          this.$message.error("批量删除失败")
        }
      })
    },

    handleEdit(row){
      this.form = row,
          this.dialogVisible = true
    },
    handleDelete(id){
      this.request.delete("/user/" + id).then(res =>{
        if(res){
          this.$message.success("删除成功")
          this.load()
        }else {
          this.$message.error("删除失败")
        }
      })
    },
    reset(){
      this.username =  "",
          this.no = "",
          this.load()

    },

    handleSizeChange(pageSize){
      this.pageSize = pageSize
      console.log(pageSize)
      this.load()
    },
    handleCurrentChange(pageNum){
      this.pageNum = pageNum
      console.log(pageNum)
      this.load()
    },
  }
}
</script>

<template>
  <div>
    <div style="margin: 10px 0">
      <el-breadcrumb seperator="/" style ="margin-bottom: 20px;font-size: 16px;">
        <el-breadcrumb-item>个人空间</el-breadcrumb-item>
        <el-breadcrumb-item>个人信息管理</el-breadcrumb-item>

      </el-breadcrumb>

        </div>

    <!--          表格-->

    <!--          分页栏-->


 <div>

   <el-button type="primary" @click="save">确 定</el-button>
 </div>

  </div>
</template>

<style>
.headerBg{
  background: #eee !important;
}
</style>