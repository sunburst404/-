<script>
export default {
  name: "Home",
  data(){
    return {
      tableData: [],
      total:0,
      pageNum: 1,
      pageSize: 2,
      id:"",
      username : "",
      age : 18,
      sex : "1",
      no :"",
      location: "",
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
      this.request.get("/detailed/page", {
            params: {
              pageNum: this.pageNum,
              pageSize: this.pageSize,
              id: this.id,
              location : this.location

            }
          }
      ).then(res =>{
        console.log(res)
        this.tableData = res.records
        this.total = res.total
      })

    },
    save(){
      this.request.post("/detailed",this.form).then(res => {
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
      this.request.post("/detailed/del/batch" , ids).then(res =>{
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
      this.request.delete("/detailed/" + id).then(res =>{
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
        <el-breadcrumb-item>数据查看</el-breadcrumb-item>
        <el-breadcrumb-item>表格显示</el-breadcrumb-item>

      </el-breadcrumb>

      <el-input style="width:200px " placeholder="请输入id" suffix-icon="el-icon-search" v-model="id"></el-input>
      <el-input style="width:200px ;margin-left: 5px" placeholder="请输入地理位置" suffix-icon="el-icon-search" v-model="location"></el-input>

      <el-button class="ml-5" @click = "load()">搜索</el-button>
      <el-button class="ml-5" type="danger" style="margin-left: 50px" @click="delBatch">批量删除<i class="el-icon-remove-outline" style="margin-left: 5px"></i></el-button>
    </div>

    <!--          表格-->
    <el-table :data="tableData" border stripe :header-cell-classs-name="headerBg"  @selection-change="handleSelectionChange">>

      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column prop="id" label="ID" width="120px">
      </el-table-column>
      <el-table-column prop="location" label="地理位置" width="170px">
      </el-table-column>
      <el-table-column prop="date" label="时间" width="95px">
      </el-table-column>
      <el-table-column prop="origin" label="来源" width="120px">
      </el-table-column>
      <el-table-column prop="carrier" label="资料格式" width="50px">
      </el-table-column>
      <el-table-column prop="category" label="灾情情况" width="80px">
      </el-table-column>
      <el-table-column prop="label" label="信息" width="110px">
      </el-table-column>
      <el-table-column prop="description" label="描述" width="100px">
      </el-table-column>


      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">
            修改<i class="el-icon-edit" style="margin-left:5px " ></i>
          </el-button>

          <el-popconfirm
              class="ml-5"
              confirm-button-text='确认'
              cancel-button-text='不了'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="handleDelete(scope.row.id)"
          >
            <el-button type="danger" slot="reference">
              删除<i class="el-icon-remove-outline" style="margin-left:5px"></i>
            </el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!--          分页栏-->
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page = "pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>

    </div>

    <el-dialog title="灾情信息" :visible.sync="dialogVisible" width="30%" >
      <el-form lable-width="80px" size="small">
        <el-form-item label="ID" >
          <el-input v-model="form.id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地理位置" >
          <el-input v-model="form.location" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="来源" >
          <el-input v-model="form.origin" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="资料格式" >
          <el-input v-model="form.carrier" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="灾情信息" >
          <el-input v-model="form.category" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="事件" >
          <el-input v-model="form.label" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="描述" >
          <el-input v-model="form.description" autocomplete="off"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<style>
.headerBg{
  background: #eee !important;
}
</style>