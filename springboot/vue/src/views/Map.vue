<script>
export default {
  data() {
    return {
      SOC: 'CHN',
      colors: {},
      GDPSpeed: {
        // '520000':1,//贵州
        // '540000':0,//西藏
        // '530000':0,//云南
        // '500000':0,//重庆
        // '360000':2,//江西
        // '340000':0,//安徽
        // '510000':0,//四川
        // '350000':0,//福建
        // '430000':0,//湖南
        // '420000':3, //湖北
        // '410000':0,//河南
        // '330000':0,//浙江
        // '640000':0,//宁夏
        // '650000':1,//新疆
        // '440000':0,//广东
        // '370000':1,//山东
        // '450000':1,//广西
        // '630000':0,//青海
        // '320000':0,//江苏
        // '140000':0,//山西
        // '460000':2,// 海南
        // '310000':0,//上海
        // '110000':0, // 北京
        // '130000':1, // 河北
        // '230000':0, // 黑龙江
        // '220000':0,// 吉林
        // '210000':0, //辽宁
        // '150000':1,//内蒙古
        // '120000':0,// 天津
        // '620000':1,// 甘肃
        // '610000':1,// 甘肃
        // '710000':0, //台湾
        // '810000':0, //香港
        // '820000':0 //澳门
        '520000':10,//贵州
        '540000':10,//西藏
        '530000':8.5,//云南
        '500000':8.5,//重庆
        '360000':8.5,//江西
        '340000':8.0,//安徽
        '510000':7.5,//四川
        '350000':8.5,//福建
        '430000':8.0,//湖南
        '420000':7.5, //湖北
        '410000':7.5,//河南
        '330000':7.0,//浙江
        '640000':7.5,//宁夏
        '650000':7.0,//新疆
        '440000':7.0,//广东
        '370000':7.0,//山东
        '450000':7.3,//广西
        '630000':7.0,//青海
        '320000':7.0,//江苏
        '140000':6.5,//山西
        '460000':7,// 海南
        '310000':6.5,//上海
        '110000':6.5, // 北京
        '130000':6.5, // 河北
        '230000':6, // 黑龙江
        '220000':6,// 吉林
        '210000':6.5, //辽宁
        '150000':6.5,//内蒙古
        '120000':5,// 天津
        '620000':6,// 甘肃
        '610000':8.5,// 甘肃
        '710000':6.64, //台湾
        '810000':6.0, //香港
        '820000':6.7 //澳门
      },
    };
  },
  mounted() {
    this.initMap();
  },
  methods: {
    getColorByDGP(adcode) {
      if (!this.colors[adcode]) {
        var gdp = this.GDPSpeed[adcode];
        if (!gdp) {
          this.colors[adcode] = 'rgb(227,227,227)';
        } else {
          var rg = 255 - Math.floor((gdp - 5) / 5 * 255);
          this.colors[adcode] = 'rgb(' + rg + ',' + rg + ',255)';
        }
      }
      return this.colors[adcode];
    },
    initMap() {
      var disCountry = new AMap.DistrictLayer.Country({
        zIndex: 10,
        SOC: 'CHN',
        depth: 2,
        styles: {
          'nation-stroke': '#f09',
          'coastline-stroke': [0.85, 0.63, 0.94, 1],
          'province-stroke': 'white',
          'city-stroke': 'rgba(255,255,255,0.15)',
          'fill': (props) => {
            return this.getColorByDGP(props.adcode_pro);
          }
        }
      });

      var map = new AMap.Map("container", {
        zooms: [4, 8],
        center: [104.188488, 34.302032],
        showIndoorMap: false,
        zoom: 3,
        isHotspot: false,
        defaultCursor: 'pointer',
        touchZoomCenter: 1,
        pitch: 0,
        layers: [
          disCountry,
          new AMap.TileLayer.Satellite()
        ],
        viewMode: '3D',
      });

      map.addControl(new AMap.Scale());
      map.addControl(new AMap.ToolBar({ liteStyle: true }));
      document.getElementsByClassName('amap-mcode')[0].innerHTML = '-GS(2022)200号、GS(2021)648号';
    }
  }
};
</script>



<template>
  <div>
    <div style="margin: 10px 0">
      <el-breadcrumb seperator="/" style ="margin-bottom: 20px;font-size: 16px;">
        <el-breadcrumb-item>数据查看</el-breadcrumb-item>
        <el-breadcrumb-item>地图查看</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div id="container" style="width: 100%;height: calc(100vh - 150px)"></div>
  </div>
</template>

<style>
.headerBg {
  background: #eee !important;
}
html, body, .map {
  width: 100%;
  height: 100vh;
  margin: 0px;
}
.amap-copyright {
  color: grey;
}
</style>
