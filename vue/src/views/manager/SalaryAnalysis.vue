<template>
  <div>

    <div style="font-size: 24px; font-weight: bold; margin-bottom: 10px; color: #2c334c" class="card">薪资分析</div>
      <div style="display: flex">
        <div id="bar" style="height: 450px; flex: 1" class="card"></div>
      </div>
      <div style="margin-top: 10px; display:flex">
        <div id="pie" style="height: 500px; flex: 1" class="card"></div>
     </div>
  </div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  name: 'SalaryAnalysis',
  data() {
    return {

    }
  },
  created() {
    this.getIndustrySalaryCategoryCount();
    this.getIndustryAverageSalary();
  },
  methods:{
    // 薪资区间柱状图
    getIndustrySalaryCategoryCount() {
      this.$request.get('/jobData/industrySalaryCategoryCount').then(res => {
        if (res.code === '200') {
          let chartDom = document.getElementById('bar');
          let myChart = echarts.init(chartDom);

          const rawData = res.data;

          // 设定薪资区间顺序（手动排序）
          const salaryCategories = ['0-5000', '5000-7000', '7000-10000', '10000-20000', '200000以上'];

          // 构建行业 -> { 薪资区间: count } 的 map
          const industryMap = {};
          rawData.forEach(item => {
            if (!industryMap[item.industry]) {
              industryMap[item.industry] = {};
            }
            industryMap[item.industry][item.salaryCategory] = item.count;
          });

          // 构建 series 数据
          const series = Object.keys(industryMap).map(industry => {
            return {
              name: industry,
              type: 'bar',
              data: salaryCategories.map(cat => industryMap[industry][cat] || 0)
            };
          });

          // 构建 option
          const option = {
            title: {
              text: '行业薪资分布对比图'
            },
            tooltip: {
              trigger: 'axis'
            },
            legend: {
              data: Object.keys(industryMap)
            },
            toolbox: {
              show: true,
              feature: {
                dataView: { show: true, readOnly: false },
                magicType: { show: true, type: ['line', 'bar'] },
                restore: { show: true },
                saveAsImage: { show: true }
              }
            },
            xAxis: {
              type: 'category',
              data: salaryCategories
            },
            yAxis: {
              type: 'value'
            },
            series: series
          };

          myChart.setOption(option);
        } else {
          this.$message.error(res.message);
        }
      });
    },
    getIndustryAverageSalary() {
      this.$request.get('/jobData/industryAverageSalary').then(res => {
        if (res.code === '200') {
          const salaryData = res.data;

          // 动态构建 ECharts 配置
          const colors = [
            '#FF9999', '#FFB03F', '#3DBA2D', '#2BA6FE', '#9B59B6', '#F39C12', '#2ECC71'
          ];
          const borderColors = colors.map(c =>
              c.replace(')', ', 0.4)').replace('rgb', 'rgba')
          );
          const placeHolderStyle = {
            label: { show: false },
            labelLine: { show: false },
            color: 'rgba(0, 0, 0, 0)',
            borderColor: 'rgba(0, 0, 0, 0)',
            borderWidth: 0
          };

          const pieData = [];
          for (let i = 0; i < salaryData.length; i++) {
            pieData.push({
              data: salaryData[i].averageSalary.toFixed(0),
              value: 20,
              name: salaryData[i].industry,
              itemStyle: {
                color: colors[i % colors.length],
                borderWidth: 20,
                borderColor: borderColors[i % borderColors.length]
              }
            }, {
              value: 8,
              name: '',
              itemStyle: placeHolderStyle
            });
          }
          pieData.push({ value: 40, name: '', itemStyle: placeHolderStyle });

          const totalAvg = (
              salaryData.reduce((sum, cur) => sum + cur.averageSalary, 0) / salaryData.length
          ).toFixed(0);

          const option = {
            backgroundColor: '#fff',
            tooltip: { show: false },
            legend: { show: false },
            toolbox: { show: false },
            series: [
              {
                type: 'pie',
                clockwise: false,
                radius: [100, 200],
                emphasis: {
                  scale: false,
                  label: {
                    show: true,
                    position: 'inner',
                    color: '#333',
                    fontSize: 14
                  }
                },
                startAngle: -30,
                label: {
                  show: true,
                  position: 'inner',
                  color: '#333',
                  fontSize: 14,
                  formatter: function (params) {
                    const isChinese = /[\u4e00-\u9fa5]/.test(params.name);
                    if (params.name !== '') {
                      if (isChinese && params.name.length > 4) {
                        return params.name.slice(0, 3) + '\n' + params.name.slice(3);
                      } else {
                        return params.name;
                      }
                    } else {
                      return '';
                    }
                  }
                },
                labelLine: {
                  length: 25,
                  length2: 40,
                  show: true
                },
                itemStyle: {
                  color: colors[0],
                  borderWidth: 20,
                  borderColor: borderColors[0]
                },
                data: pieData
              },
              {
                type: 'pie',
                clockwise: false,
                radius: [100, 200],
                silent: false,
                startAngle: -30,
                label: {
                  show: true,
                  position: 'outside',
                  fontSize: 16,
                  formatter: function (params) {
                    return params.name !== '' ? params.data.data + ' 元' : '';
                  }
                },
                labelLine: {
                  length: 30,
                  length2: 60,
                  show: true
                },
                itemStyle: {
                  color: colors[0],
                  borderWidth: 20,
                  borderColor: borderColors[0]
                },
                data: pieData
              }
            ],
            graphic: [
              {
                type: 'group',
                left: 'center',
                top: 'middle',
                children: [
                  {
                    type: 'text',
                    z: 100,
                    left: 'center',
                    top: '0',
                    style: {
                      fill: '#333',
                      text: ['行业平均薪资'],
                      font: '16px Microsoft YaHei'
                    }
                  },
                  {
                    type: 'text',
                    z: 100,
                    left: 'center',
                    top: '32',
                    style: {
                      fill: 'red',
                      text: [totalAvg + ' 元'],
                      font: '26px Microsoft YaHei'
                    }
                  }
                ]
              }
            ]
          };

          let chartDom = document.getElementById('pie');
          let myChart = echarts.init(chartDom);
          myChart.setOption(option);

        } else {
          this.$message.error(res.message);
        }
      });
    }




  }
}
</script>
