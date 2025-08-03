<!-- src/components/BigScreen.vue -->
<template>
  <div
      class="fill-parent"
      dg-chart-auto-resize="true"
      dg-chart-options="{title:{show:false},legend:{top:0},grid:{top:25}}"
      dg-chart-theme="{color:'#F0F0F0',actualBackgroundColor:'#000c3b'}"
  >
    <div class="head">
      <div class="title">
        <div class="title-left"></div>
        <div class="title-value"><label>招聘数据可视化大屏</label></div>
        <div class="title-right"></div>
      </div>
    </div>

    <div class="content">
      <div class="layout layout-left-top">
        <div class="panel">
          <div class="title"><label>城市平均薪资top10</label></div>
          <div id="bar" style="width: 100%; height: 275px;"></div>
        </div>
      </div>
      <div class="layout layout-left-center">
        <div class="panel">
          <div class="title"><label>工资区间分析</label></div>
          <!-- 饼图容器，添加图标 -->
          <div class="pie-chart-container">
            <div id="pie" style="width: 100%; height: 275px;"></div>
            <img class="pie-center-icon" src='data:image/png;base64,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' />
          </div>
        </div>
      </div>
      <div class="layout layout-left-bottom">
        <div class="panel">
          <div class="title"><label>薪资分析</label></div>
          <div id="line" style="width: 100%; height: 275px;"></div>
        </div>
      </div>

      <div class="layout layout-center-top">
        <div class="panel">
          <div class="chart chart-left" dg-chart-widget="">
            <div style="text-align: center; margin-top: 40px">
              <span style="color: #2a60c9; font-size: 20px">
               <i class="el-icon-money" style="margin-right: 5px;"></i>最高薪资
              </span>
              <div style="color: #2a60c9; font-size: 20px; margin-top: 15px">
                {{ topMaxSalary }}
              </div>
            </div>
          </div>
          <div class="chart chart-center" dg-chart-widget="">
            <div style="text-align: center; margin-top: 40px">
              <span style="color: #2a60c9; font-size: 20px">
               <i class="el-icon-mouse" style="margin-right: 5px;"></i>工作类型
              </span>
              <div style="color: #2a60c9; font-size: 20px; margin-top: 15px">
                {{ topType }}
              </div>
            </div>
          </div>
          <div class="chart chart-right" dg-chart-widget="">
            <div style="text-align: center; margin-top: 40px">
              <span style="color: #2a60c9; font-size: 20px">
               <i class="el-icon-map-location" style="margin-right: 5px;"></i>所在城市
              </span>
              <div style="color: #2a60c9; font-size: 20px; margin-top: 15px">
                {{ topCity }}
              </div>
            </div>
          </div>
        </div>
      </div>

<!--      构建中国地图-->
      <div class="layout layout-center-bottom">
        <div class="panel border-all">
          <div id="map" style="width: 100%; height: 550px;"></div>
          <div class="border-foot"></div>
        </div>
      </div>

      <div class="layout layout-right-top">
        <div class="panel">
          <div class="title"><label>公司人数分析</label></div>
          <div id="funnel" style="width: 100%; height: 275px;"></div>
        </div>
      </div>

      <div class="layout layout-right-center">
        <div class="panel">
          <div class="title"><label>薪资Top10</label></div>
          <div id="list" class="list-container">
            <div
                v-for="(item, index) in listData"
                :key="index"
                class="list-item"
            >
              <img :src="item.imgSrc" alt="公司图片" class="list-img" />
              <div>
                <div><strong>职位：</strong>{{ item.title }}</div>
                <div><strong>类型：</strong>{{ item.type }}</div>
                <div><strong>城市：</strong>{{ item.city }}</div>
                <div><strong>最高薪资：</strong>{{ item.maxSalary }}</div>
              </div>
            </div>
          </div>
        </div>
      </div>


    </div>
  </div>
</template>

<!--渲染图表-->
<script>
import * as echarts from 'echarts';

// 条形图配置
let barOptions = {
  xAxis: {
    type: 'value',
    data: ['5000', '10000', '15000', '20000', '25000', '30000']
  },
  yAxis: {
    type: 'category'
  },
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      type: 'shadow'
    }
  },
  series: [
    {
      data: [],
      type: 'bar',
      itemStyle: {
        borderRadius: [0, 5, 5, 0],
        borderColor: 'rgba(0,0,0,0.1)',
        borderWidth: 1,
        color: '#3275e5'
      }
    }
  ]
};
// 饼图颜色配置
let pieColor = ['#00ffff', '#00cfff', '#006ced', '#ffe000', '#ffa800', '#ff5b00'];
// 饼图基础配置
let pieOption = {
  // backgroundColor: '#0A2E5D',
  color : ['#00ffff','#00cfff','#006ced','#ffe000','#ffa800','#ff5b00','#ff3000'],
  title: {
    // text: '交通方式',
    top: '48%',
    textAlign: "center",
    left: "49%",
    textStyle: {
      color: '#fff',
      fontSize: 22,
      fontWeight: '400'
    }
  },
  graphic: {
    elements: [{
      type: "image",
      z: 3,
      style: {
        image: 'data:image/png;base64,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',
        width: 145,
        height: 145
      },
      left: 'center',
      top:  'center',
      position: [100, 100] //
    }]
  },
  tooltip: {show: false},
  legend: {
    icon: "circle",
    orient: 'horizontal',
    bottom: 20,
    align: 'center',
    textStyle: { color: "#fff" },
    itemGap: 20,
    itemWidth: 50,
  },
  series: [{
    name: '',
    type: 'pie',
    clockwise: false,
    radius: [40, 60],
    emphasis: { scale: true },
    itemStyle: {
      label: {
        show: true,
        position: 'outside',
        color: '#ddd',
      },
    },
    data: [],
  }]
};
// 折线图配置
let lineOptions = {
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      lineStyle: {
        color: {
          type: 'linear',
          x: 0,
          y: 0,
          x2: 0,
          y2: 1,
          colorStops: [
            { offset: 0, color: 'rgba(126,199,255,0)' },
            { offset: 0.5, color: 'rgba(126,199,255,1)' },
            { offset: 1, color: 'rgba(126,199,255,0)' }
          ],
          global: false
        }
      },
    },
    formatter: (p) => {
      return `
    <div style="width: 1px; height: 1px; color:#000c3b ; position: relative;">
        <svg style="position: absolute; top: 50%; left: 75%; transform: translateX(-50%) translateY(-50%);" class="svg" xmlns="http://www.w3.org/2000/svg" width="1" height="71" viewBox="0 0 84 55">
            <path id="矩形_419" data-name="矩形 419" class="cls-1" d="M266,595h195v50H266V624.046L261,620l5-3.984V595Z"
                transform="translate(-258.5 -592.5)" />
        </svg>
        <div style="padding: 4px 8px 4px 14px; display: flex; justify-content: center; align-items: center; flex-direction: column; position: relative; z-index: 1;">
            <div style="margin-bottom: 4px; width:100%; display:${p[0] ? 'flex' : 'none'}; justify-content:space-between; align-items:center;">
                <span style="font-size:14px; color:#7ec7ff;">${p[0] ? p[0].seriesName : ''}</span>
                <span style="font-size:14px; color:#fff;">${p[0] ? p[0].data : ''}</span>
            </div>
        <div style="width:100%; height:100%; display:${p[1] ? 'flex' : 'none'}; justify-content:space-between; align-items:center;">
            <span style="font-size:14px; color:#7ec7ff;">${p[1] ? p[1].seriesName : ''}</span>
            <span style="font-size:14px; color:#fff;">${p[1] ? p[1].data : ''}</span>
        </div>
        </div>
      </div>`;
    }
  },
  legend: {
    align: "left",
    right: '10%',
    top: '10%',
    type: 'plain',
    textStyle: { color: '#7ec7ff', fontSize: 16 },
    itemGap: 25,
    itemWidth: 18,
    icon: 'path://M0 2a2 2 0 0 1 2 -2h14a2 2 0 0 1 2 2v0a2 2 0 0 1 -2 2h-14a2 2 0 0 1 -2 -2z',
    data: [
      { name: '最高薪资' },
      { name: '最低薪资' }
    ]
  },
  grid: {
    top: '30%',
    left: '15%',
    right: '5%',
    bottom: '20%',
  },
  xAxis: [{
    type: 'category',
    boundaryGap: false,
    axisLine: { show: true, lineStyle: { color: '#233653' } },
    axisLabel: { color: '#7ec7ff', padding: 5, fontSize: 12, interval: 0}, //x轴字体样式
    splitLine: { show: true, lineStyle: { color: '#192a44' } },
    axisTick: { show: false },
    data: [] // 绑定后端数据
  }],
  yAxis: [{
    name: '金额',
    nameTextStyle: { color: "#7ec7ff", fontSize: 16, padding: 5 },
    min: 0,
    splitLine: { show: true, lineStyle: { color: '#192a44' } },
    axisLine: { show: true, lineStyle: { color: "#233653" } },
    axisLabel: { color: '#7ec7ff', padding: 16 },
    axisTick: { show: false },
  }],
  series: [{
    name: '最高薪资',
    type: 'line',
    symbol: 'circle',
    showAllSymbol: true,
    symbolSize: 0,
    smooth: true,
    lineStyle: { width: 5, color: "rgba(25,163,223,1)" },
    itemStyle: { color: "rgba(25,163,223,1)", borderColor: "#646ace", borderWidth: 2 },
    areaStyle: {
      color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
        { offset: 0, color: "rgba(25,163,223,.3)" },
        { offset: 1, color: "rgba(25,163,223, 0)" }
      ], false),
      shadowColor: 'rgba(25,163,223, 0.5)',
      shadowBlur: 20
    },
    data: []
  }, {
    name: '最低薪资',
    type: 'line',
    symbol: 'circle',
    showAllSymbol: true,
    symbolSize: 0,
    smooth: true,
    lineStyle: { width: 5, color: "rgba(10,219,250,1)" },
    itemStyle: { color: "rgba(10,219,250,1)", borderColor: "#646ace", borderWidth: 2 },
    areaStyle: {
      color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
        { offset: 0, color: "rgba(10,219,250,.3)" },
        { offset: 1, color: "rgba(10,219,250, 0)" }
      ], false),
      shadowColor: 'rgba(10,219,250, 0.5)',
      shadowBlur: 20
    },
    data: []
  }]
};
let len = 0
// 漏斗图
let funnelOption = {
  // backgroundColor: "#ffffff",
  color: ["#37A2DA", "#32C5E9", "#67E0E3", "#9FE6B8","#FFDB5C",],
  tooltip: {
    trigger: 'item',
    formatter: "{a} <br/>{b} : {c}个"  //
  },

  calculable: true,
  series: [
    {
      name:'公司人数',
      type:'funnel',
      left: '10%',
      top: 60,
      bottom: 60,
      width: '80%',

      min: 0,
      max: 100,
      minSize: '0%',
      maxSize: '100%',
      sort: 'descending',
      gap: 2,
      label: {
        normal: {
          show: true,
          position: 'inside'
        },
        emphasis: {
          textStyle: {
            fontSize: 20
          }
        }
      },
      labelLine: {
        normal: {
          length: 10,
          lineStyle: {
            width: 1,
            type: 'solid'
          }
        }
      },
      itemStyle: {
        normal: {
          borderColor: 'black',
          borderWidth: 1
        }
      },
      data: []
    }
  ]
};
// 整理热力图层级
function toHierarchy(rows) {
  const root = { name: '全国', children: [] };
  const map = {}; // 省级索引
  rows.forEach(r => {
    if (!map[r.province]) {
      const node = { name: r.province, children: [] };
      map[r.province] = node;
      root.children.push(node);
    }
    map[r.province].children.push({
      name: r.city,
      value: r.jobCount          // Treemap / Sunburst 用面积表现
      // 也可以再塞 avgSalary、growthRate 放在 tooltip
    });
  });
  return root;
}


export default {
  name: 'BigScreen',
  data() {
    return {
      chartInstance: null,  // 保存图表实例
      pieOption: null, // 饼图配置
      barOptions: null, // 柱状图配置
      lineOptions: null,// 折线图配置
      listData:[], // 数据列表
      scrollInterval: null, // 定时器
      topMaxSalary: null, // 存储最高薪资
      topType: null, // 存储Type
      topCity: null, // 存储city
      currentStart: 0, // 当前滚动到第几条
      scrollTimer: null, // 定时器
    };
  },
  created() {
    this.getTop10AverageSalaries();
    this.getSalaryCategoryCount();
    this.getExperienceAnalysis();
    this.getCompanyPeopleCategory();
    this.getTopSalary();
  },
  // 在组件挂载后开始滚动
  mounted() {
    this.startScrolling();
    this.initCityDependencyGraph();
  },
  // 在组件销毁时清除定时器
  beforeDestroy() {
    clearInterval(this.scrollInterval);
  },
  methods: {
    // 条形图
    getTop10AverageSalaries() {
      this.$request.get('/jobData/top10').then(res => {
        console.log("条形图数据", res.data)
        if (res.code === '200') {
          let chartDom = document.getElementById('bar');
          let myChart = echarts.init(chartDom);

          const allData = res.data.yAxis.map((city, idx) => ({
            name: city,
            value: res.data.xAxis[idx]
          }));

          // 保存原始数据，供后续滚动
          this.allBarData = allData;

          const showCount = 5; // 每次显示几条，比如5条
          this.currentStart = 0;

          // 初次加载前5条
          const firstShowData = allData.slice(0, showCount);

          barOptions.yAxis.data = firstShowData.map(item => item.name);
          barOptions.series[0].data = firstShowData.map(item => item.value);

          myChart.setOption(barOptions);

          // 开始滚动
          clearInterval(this.scrollTimer);
          this.scrollTimer = setInterval(() => {
            this.currentStart++;
            if (this.currentStart > allData.length - showCount) {
              this.currentStart = 0; // 循环滚动
            }
            const showData = allData.slice(this.currentStart, this.currentStart + showCount);
            barOptions.yAxis.data = showData.map(item => item.name);
            barOptions.series[0].data = showData.map(item => item.value);
            myChart.setOption(barOptions);
          }, 2000); // 每2秒滚动一次
        } else {
          this.$message.error(res.message);
        }
      });
    },
    // 饼图
    getSalaryCategoryCount() {
      this.$request.get('/jobData/salaryCategoryCount').then(res => {
        if (res.code === '200') {
          const salaryData = res.data;

          // 计算总数
          const total = salaryData.reduce((sum, i) => sum + i.value, 0);

          // 组装扇形图数据（包括百分比）
          // 更新饼图配置
          pieOption.series[0].data = salaryData.map((item, index) => {
            return {
              value: item.value, // 使用后端返回的 count
              name: item.name,  // 使用后端返回的 salary_category
              itemStyle: {
                borderWidth: 5,
                shadowBlur: 20,
                borderColor: pieColor[index % pieColor.length],
                shadowColor: pieColor[index % pieColor.length]
              },
              label: {
                show: true,
                formatter: `薪资{b}\n 占比{d}%`, // 显示名称和百分比
                color: '#fff',
                position: 'outside', // 标签位置
                distance: 30 // 调整标签与图形的距离
              },
              labelLine: {
                length: 30,
                length2: 20,
                show: true,
                color: '#00ffff'
              }
            };
          }); // 饼图数据
          pieOption.legend.data = salaryData.map(i => i.name); // 图例数据

          // 渲染图表
          const chartDom = document.getElementById('pie');
          const myChart = echarts.init(chartDom);
          myChart.setOption(pieOption);
        } else {
          this.$message.error(res.message);
        }
      });
    },
    // 折线图
    getExperienceAnalysis() {
      this.$request.get('/jobData/experienceAnalysis').then(res => {
        if (res.code === '200') {
          let chartDom = document.getElementById('line');
          let myChart = echarts.init(chartDom);
          // 对数据进行自定义顺序排序
          const desiredOrder = ["在校", "1年以下", "1-3年", "3-5年", "5-10年", "10年以上", "不限"];
          const reordered = desiredOrder.map(label => {
            const index = res.data.xAxis.indexOf(label);
            return {
              label,
              y1: res.data.yAxis1[index],
              y2: res.data.yAxis2[index]
            };
          });

          this.xLabel = reordered.map(i => i.label);
          // 创建图表实例
          setInterval(()=>{
            if (len === this.xLabel.length) {
              len = 0
            }
            myChart.dispatchAction({
              type: 'showTip',
              seriesIndex: 0,
              dataIndex: len,
            })
            len ++
          }, 1000)
          lineOptions.xAxis[0].data = this.xLabel;
          lineOptions.series[0].data = reordered.map(i => i.y1);
          lineOptions.series[1].data = reordered.map(i => i.y2);
          // 渲染图表
          myChart.setOption(lineOptions);
        } else {
          this.$message.error(res.message);
        }
      });
    },
    //漏斗图
    getCompanyPeopleCategory() {
      this.$request.get('/jobData/cityCount').then(res => {
        if (res.code === '200') {
          let chartDom = document.getElementById('funnel');
          let myChart = echarts.init(chartDom);
          console.log("funnel", res.data)

          // 将后端返回的数据映射到漏斗图的 data 属性
          funnelOption.series[0].data = res.data.data.map(item => ({
            value: item.value, // 漏斗图的值
            name: item.name    // 漏斗图的名称
          }));

          // 渲染漏斗图
          myChart.setOption(funnelOption);
        } else {
          this.$message.error(res.message);
        }
      });
    },
    // 数据列表
    getTopSalary() {
      this.$request.get('/jobData/topSalary').then(res => {
        if (res.code === '200') {
          this.listData = res.data;
          // 提取最高薪资
          this.topMaxSalary = Math.max(...this.listData.map(item => item.maxSalary));
          // 提取第0条数据的title和city
          const firstItem = this.listData[0];
          this.topType = firstItem.type; // 获取type
          this.topCity = firstItem.city;   // 获取city

          // 等页面渲染完成再开始滚动
          this.$nextTick(() => {
            this.startScrolling();
          });
        } else {
          this.$message.error(res.message);
        }
      });
    },
    startScrolling() {
      const list = document.getElementById('list');
      const listItems = list.querySelectorAll('.list-item');
      let currentIndex = 0;
      const showCount = 4; // 每次展示4条
      if (!listItems.length) return;

      this.scrollInterval = setInterval(() => {
        currentIndex++;
        if (currentIndex > listItems.length - showCount) {
          currentIndex = 0; // 循环滚动
        }
        const targetOffset = listItems[currentIndex].offsetTop;
        list.scrollTo({
          top: targetOffset,
          behavior: 'smooth'
        });
      }, 2000); // 每2秒切换一次
    },
    // 构建大屏中心
    initCityDependencyGraph() {
      let chartDom = document.getElementById('map');
      let myChart = echarts.init(chartDom);
      myChart.showLoading();

      Promise.all([
        this.$request.get('/jobData/cityJobCount'),
        this.$request.get('/jobData/cityEdges')
      ]).then(([resNodes, resEdges]) => {
        myChart.hideLoading();

        if (resNodes.code === '200' && resEdges.code === '200') {
          console.log('edge', resEdges);
          console.log('node', resNodes);
          const maxCount = Math.max(...resNodes.data.data.map(i => i.jobCount));

          // 构建城市集合用于过滤
          const citySet = new Set(resNodes.data.data.map(city => city.city));
          // 计算最大jobCount的城市
          const maxJobCountCity = resNodes.data.data.reduce((maxCity, city) =>
              city.jobCount > maxCity.jobCount ? city : maxCity, { jobCount: 0 });

          // 处理节点数据
          const nodes = resNodes.data.data.map(city => ({
            id: city.city,
            name: city.city,
            value: city.jobCount,
            symbolSize: 5 + (city.jobCount / maxCount) * 80, // 映射symbolSize
            itemStyle: {
              color: city.city === maxJobCountCity.city
                  ? '#fff' // 高亮颜色
                  : city.color || ['#36cfc9', '#00ffff', '#00cfff', '#006ced', "#37A2DA", "#32C5E9", "#67E0E3", "#9FE6B8"][Math.floor(Math.random() * 8)]
            }
          }));

          const edges = resEdges.data
            .filter(edge => citySet.has(edge.source) && citySet.has(edge.target))
            .map(edge => ({
              source: edge.source,
              target: edge.target
            }));

          const option = {
            title: {
              text: resNodes.data.text,
              subtext: resNodes.data.subtext,
              left: 'center',
              textStyle: { color: '#fff', fontSize: 20 },
              subtextStyle: { color: '#aaa', fontSize: 14 }
            },
            series: [
              {
                type: 'graph',
                layout: 'force',
                data: nodes,
                edges: edges,
                roam: true,
                force: {
                  repulsion: 400, //引力
                  edgeLength: 80   //边长
                },
                lineStyle: {
                  color: '#fff',
                  curveness: 0.2
                },
                label: {
                  show: true,
                  position: 'right',
                  color: '#aaa',
                  formatter: (params) => `${params.data.name}\n${params.data.value}` // 显示节点名称和jobCount
                },
                emphasis: {
                  focus: 'adjacency'
                }
              }
            ]
          };
          myChart.setOption(option);
        } else {
          this.$message.error("图数据加载失败");
        }
      });
    }
  }
};




</script>

<style scoped>
/* 确保图表容器宽度自适应 */
#chart {
  width: 100%;
}
/* 设置饼图布局 */
.pie-chart-container {
  position: relative;
  width: 100%;
  height: 275px;
}
/* 设置饼图中心图标 */
.pie-center-icon {
  width: 100px;
  height: 100px;
  position: absolute;
  top: calc(50% - 50px); /* 图标垂直居中 */
  left: calc(50% - 50px); /* 图标水平居中 */
  z-index: 10;
  pointer-events: none; /* 防止挡住鼠标事件 */
}
.list-container {
  margin-top: 20px;
  height: 500px;
  overflow: hidden;
  position: relative;
}

.list-item {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 10px 0;
}

.list-img {
  width: 50px;
  height: 50px;
  border-radius: 5px;
}
</style>