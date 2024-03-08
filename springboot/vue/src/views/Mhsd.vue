<script>

import * as echarts from 'echarts';
export default {
  name: "Mhsd",
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
      multipleSelection:[],
      form : {},
      dialogVisible : false,
      headerBg:'headerBg'
    }
  },
  mounted() {

    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    var option = {
      xAxis: {
        type: 'category',
        data: ["2019","2020","2021","2022","2023"]
      },
      yAxis: {
        type: 'value'
      },
      series: [

        {
          data: [],
          type: 'line'
        }
      ]
    };




    var barDom = document.getElementById('bar');
    var barChart = echarts.init(barDom);
    var baroption = {
      xAxis: {
        type: 'category',
        data: ["2019","2020","2021","2022","2023"]
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          type: 'bar'
        }
      ]
    };

    this.request.get("/echarts/pic").then(res =>{


      option.series[0].data = res.data;  // 使用行数数据作为纵坐标

      baroption.series[0].data = res.data;  // 使用行数数据作为纵坐标

      myChart.setOption(option)
      barChart.setOption(baroption)
    })
  }


}
</script>

<template>
  <div>
    <div style="margin: 10px 0">
      <el-breadcrumb seperator="/" style ="margin-bottom: 20px;font-size: 16px;">
        <el-breadcrumb-item>数据查看</el-breadcrumb-item>
        <el-breadcrumb-item>统计图查看</el-breadcrumb-item>

      </el-breadcrumb>

        </div>

    <!--          表格-->
    <div id="main" style="width: 500px;height: 400px;float: left; margin-right: 5%;"></div>
    <div id="bar" style="width: 500px;height: 400px;float: left;"></div>
  </div>
  </template>

<style>
.headerBg{
  background: #eee !important;
}
</style>