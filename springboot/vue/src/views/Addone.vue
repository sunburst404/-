<script>
export default {
  name: "Addone",
  data(){
    return {
      tableData: [],
      total:0,
      pageNum: 1,
      pageSize: 2,
      id:"",
      description:"",
      multipleSelection:[],
      //need change
      form: {
        id: '',  // Add more properties based on your Detailed model
        description: ''
      },
      dialogVisible : false,
      headerBg:'headerBg',
      formDetail :  {idNoAddress:'',description:'',town:'',village:''},
      provinces : [],
      cities : [],
      counties : [],
      towns : [],
      villages : [],
      selectedDate : null,
      selectedProvince : '',
      selectedCity : '',
      selectedCounty : '',
      selectedTown : '',
      selectedVillage : '',
      selectedOriginCode:null,
      originCode : [
        { value: 100, label: "前方地震应急指挥部" },
        { value: 101, label: "后方地震应急指挥部" },
        { value: 120, label: "应急指挥技术系统" },
        { value: 121, label: "社会服务工程应急救援系统" },
        { value: 140, label: "危险区预评估工作组" },
        { value: 141, label: "地震应急指挥技术协调组" },
        { value: 142, label: "震后政府信息支持工作项目组" },
        { value: 180, label: "灾情快速上报接收处理系统" },
        { value: 181, label: "地方地震局应急信息服务相关技术系统" },
        { value: 199, label: "其他业务报送数据" },
        { value: 200, label: "互联网感知 " },
        { value: 201, label: "通信网感知" },
        { value: 202, label: "舆情网感知" },
        { value: 203, label: "电力系统感知" },
        { value: 204, label: "交通系统感知" },
        { value: 205, label: "其他泛在感知数据" },
        { value: 300, label: "其他数据" },
      ],
      selectedCarrierCode:null,
      carrierCode:[
        { value: 0, label: "文字" },
        { value: 1, label: "图像" },
        { value: 2, label: "音频" },
        { value: 3, label: "视频坏" },
        { value: 4, label: "其他" },
      ],
      selectedCategoryCode:null,
      categoryCode:[
        { value: 101, label: "震情信息" },
        { value: 201, label: "死亡" },
        { value: 202, label: "受伤" },
        { value: 203, label: "失踪" },
        { value: 301, label: "土木" },
        { value: 302, label: "砖木" },
        { value: 303, label: "砖混" },
        { value: 304, label: "框架" },
        { value: 305, label: "其他房屋破坏" },
        { value: 401, label: "交通" },
        { value: 402, label: "供水" },
        { value: 403, label: "输油" },
        { value: 404, label: "燃气" },
        { value: 405, label: "电力" },
        { value: 406, label: "通信" },
        { value: 407, label: "水利" },
        { value: 501, label: "崩塌 " },
        { value: 502, label: "滑坡" },
        { value: 503, label: "泥石流 " },
        { value: 504, label: "岩溶塌陷" },
        { value: 505, label: "地裂缝 " },
        { value: 506, label: "地面沉降" },
        { value: 507, label: "其他（沙土液、化、火灾、毒气、泄露、爆炸、环、境污染、瘟疫、海啸等）" },
      ],
      selectedLabelCode:null,
      labelCode: [
        { value: 101, label: "地理位置" },
        { value: 102, label: "时间" },
        { value: 103, label: "震级" },
        { value: 104, label: "震源深度" },
        { value: 105, label: "烈度" },
        { value: 201, label: "受灾人数" },
        { value: 202, label: "受灾程度" },
        { value: 301, label: "一般损坏面积" },
        { value: 302, label: "严重损坏面积" },
        { value: 303, label: "受灾程度" },
        { value: 401, label: "受灾设施数" },
        { value: 402, label: "受灾范围" },
        { value: 403, label: "受灾程度" },
        { value: 501, label: "灾害损失" },
        { value: 502, label: "灾害范围" },
        { value: 503, label: "受灾程度" }
      ],
      descriptionDetail:'',
    }
  },
  created(){
    this.getProvinces()
  },
  methods:{
    // load(){
    //   this.request.get("/user/page", {
    //         params: {
    //           pageNum: this.pageNum,
    //           pageSize: this.pageSize,
    //           username: this.username,
    //           no : this.no

    //         }
    //       }
    //   ).then(res =>{
    //     console.log(res)
    //     this.tableData = res.records
    //     this.total = res.total
    //   })

    // },

    save(){
      this.request.post("/detailed/add", null, {
            params:this.form,
          }
      ).then(res => {
        if(res){
          this.$message.success("添加成功")
          this.dialogVisible = false
          //   this.load()
        }else {
          this.$message.error("添加失败")
        }
      })

    },

    getProvinces() {
      this.request.get("/chose/getProvinces")
          .then(response => {
            this.provinces = response;
          })
          .catch(error => {
            console.error(error);
            this.selectedProvince='';
          });
    },

    getCities() {
      console.log(this.selectedProvince);
      this.request.get("/chose/getCities",{
        params:{province:this.selectedProvince}
      })
          .then(response => {
            this.cities = response;
            this.selectedCity='';
          })
          .catch(error => {
            console.error(error);
          });
    },
    getCounties() {
      this.request.get("/chose/getCounties",{
        params:{city:this.selectedCity}
      })
          .then(response => {
            this.counties = response;
            this.selectedCounty='';
          })
          .catch(error => {
            console.error(error);
          });
    },
    getTowns() {
      this.request.get("/chose/getTowns",{
        params:{county:this.selectedCounty}
      })
          .then(response => {
            this.towns = response;
            this.selectedTown='';
          })
          .catch(error => {
            console.error(error);
          });
    },
    getVillages() {
      this.request.get("/chose/getVillages",{
        params:{town:this.selectedTown}
      })
          .then(response => {
            this.villages = response;
            this.selectedVillage='';
          })
          .catch(error => {
            console.error(error);
          });
    },
    saveDetail(){
      let date = new Date(this.selectedDate);
      let year = date.getFullYear().toString();
      let month = (date.getMonth() + 1).toString(); // 月份从 0 开始，需要加1
      if(month.length===1){
        month='0'+month;
      }
      let day = date.getDate().toString();
      if(day.length===1){
        day='0'+day;
      }
      let hours = date.getHours().toString();
      if(hours.length===1){
        hours='0'+hours;
      }
      let minutes = date.getMinutes().toString();
      if(minutes.length===1){
        minutes='0'+minutes;
      }
      let seconds = date.getSeconds().toString();
      if(seconds.length===1){
        seconds='0'+seconds;
      }

      this.formDetail.idNoAddress=year+month+day+hours+minutes+seconds+this.selectedOriginCode.toString()+this.selectedCarrierCode.toString()+
          this.selectedCategoryCode.toString()+this.selectedLabelCode.toString();
      this.formDetail.description=this.descriptionDetail;
      this.formDetail.town=this.selectedTown;
      this.formDetail.village=this.selectedVillage;
      console.log(this.formDetail.idNoAddress);
      console.log(this.formDetail.town);
      console.log(this.formDetail.village);
      console.log(this.formDetail.description);

      this.request.post("/detailed/addDetail",null,{
        params:this.formDetail,
      }).then(response => {
        console.log(response);
        this.$message.success("添加成功");
      })
          .catch(error => {
            console.error(error);
          });


    }
  },


}

</script>

<template>
  <div>
    <el-input style="width:200px " placeholder="请输入多源异构地震数据一体化编码" suffix-icon="el-icon-search" v-model="form.id"></el-input>
    <el-input style="width:200px ;margin-left: 5px" placeholder="请输入注释" suffix-icon="el-icon-search" v-model="form.description"></el-input>
    <el-button class="ml-5" @click = "save()">添加</el-button>

    <br><br><br><br><br>
    <!--          表格-->
    <el-form  label-width="80px">
      <!-- 省份 -->
      <el-form-item label="省份">
        <el-select v-model="selectedProvince" placeholder="请选择选项" @change="getCities">
          <el-option v-for="province in provinces" :key="province" :label="province" :value="province"></el-option>
        </el-select>
      </el-form-item>
      <!-- 城市 -->
      <el-form-item label="城市">
        <el-select v-model="selectedCity" placeholder="请选择选项" @change="getCounties">
          <el-option v-for="city in cities" :key="city" :label="city" :value="city"></el-option>
        </el-select>
      </el-form-item>
      <!-- 县区 -->
      <el-form-item label="县区">
        <el-select v-model="selectedCounty" placeholder="请选择选项" @change="getTowns">
          <el-option v-for="county in counties" :key="county" :label="county" :value="county"></el-option>
        </el-select>
      </el-form-item>
      <!-- 乡镇 -->
      <el-form-item label="乡镇">
        <el-select v-model="selectedTown" placeholder="请选择选项" @change="getVillages">
          <el-option v-for="town in towns" :key="town" :label="town" :value="town"></el-option>
        </el-select>
      </el-form-item>
      <!-- 村社 -->
      <el-form-item label="村社">
        <el-select v-model="selectedVillage" placeholder="请选择选项">
          <el-option v-for="village in villages" :key="village" :label="village" :value="village"></el-option>
        </el-select>
      </el-form-item>
      <!-- 日期时间 -->
      <el-form-item>
        <el-date-picker v-model="selectedDate" type="datetime" placeholder="选择日期时间"></el-date-picker>
      </el-form-item>
      <!-- 来源 -->
      <el-form-item label="来源">
        <el-select v-model="selectedOriginCode" placeholder="请选择选项">
          <el-option v-for="option in originCode" :key="option.value" :label="option.label" :value="option.value"></el-option>
        </el-select>
      </el-form-item>
      <!-- 载体 -->
      <el-form-item label="载体">
        <el-select v-model="selectedCarrierCode" placeholder="请选择选项">
          <el-option v-for="option in carrierCode" :key="option.value" :label="option.label" :value="option.value"></el-option>
        </el-select>
      </el-form-item>
      <!-- 灾情信息 -->
      <el-form-item label="灾情信息">
        <el-select v-model="selectedCategoryCode" placeholder="请选择选项">
          <el-option v-for="option in categoryCode" :key="option.value" :label="option.label" :value="option.value"></el-option>
        </el-select>
      </el-form-item>
      <!-- 灾情指标 -->
      <el-form-item label="灾情指标">
        <el-select v-model="selectedLabelCode" placeholder="请选择选项">
          <el-option v-for="option in labelCode" :key="option.value" :label="option.label" :value="option.value"></el-option>
        </el-select>
      </el-form-item>
      <!-- 详述 -->
      <el-form-item label="详述">
        <el-input v-model="descriptionDetail"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer" style=" margin-left: 80px;">
      <el-button type="primary" @click="saveDetail">确定</el-button>
    </div>
  </div>
</template>

<style>
.headerBg{
  background: #eee !important;
}
</style>