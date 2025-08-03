<template>
  <div>

    <div style="font-size: 24px; font-weight: bold; margin-bottom: 10px; color: #2c334c" class="card">经历分析</div>
    <div style="font-size: 18px; font-weight: bold; color: #2c334c" class="card">经验&薪资人数分布图</div>
      <div style="display: flex">
        <div id="bar" style="height: 450px; flex: 1" class="card"></div>
      </div>
    <div style="font-size: 18px; font-weight: bold; color: #2c334c" class="card">学历分布图</div>
    <div style="display:flex">
        <div id="pie" style="height: 500px; flex: 1" class="card"></div>
     </div>
  </div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  name: 'ExperienceAnalysis',
  data() {
    return {

    }
  },
  created() {
    this.getExperienceSalaryCount();
    this.getEducationCount();
  },
  methods:{
    // 经验分析
    getExperienceSalaryCount() {
      this.$request.get('/jobData/experienceSalaryCount').then(res => {
        if (res.code === '200') {
          const data = res.data;
          // 手动排序和映射数据
          const workExperience = ['经验在校', '经验1年以下', '经验3-5年', '经验5-10年', '经验10年以上', '经验不限'];
          const mappedData = {};
          data.forEach(item => {
            mappedData[item.workExperience] = {
              averageSalary: item.averageSalary,
              count: item.count,
              resolvedCount: Math.round(item.count * 0.7)
            };
          });
          const xData = workExperience;
          const countData = workExperience.map(item => Math.round(mappedData[item]?.averageSalary || 0));
          const resolvedData = workExperience.map(item => mappedData[item]?.resolvedCount || 0);
          const totalCount = workExperience.reduce((sum, item) => sum + (mappedData[item]?.count || 0), 0);
          const rateData = workExperience.map(item =>
            mappedData[item] && totalCount
              ? ((mappedData[item].count / totalCount) * 100).toFixed(2)
              : '0.00'
          );

          let option = {
            tooltip: {
              trigger: 'axis',
              axisPointer: {type: 'line'},
              backgroundColor: 'rgba(9, 24, 48, 0.5)',
              borderColor: 'rgba(75, 253, 238, 0.4)',
              textStyle: {color: '#CFE3FC'}
            },
            legend: [
              {
                data: ['平均薪资（元）'],
                top: '0',
                x: '0',
                itemWidth: 8,
                borderColor: 'rgba(255, 192, 0, 1)',
                textStyle: {color: 'rgba(0,0,0,.4)', fontSize: 12},
                icon: 'circle'
              },
              {
                data: ['人数'],
                top: '0',
                x: '15%',
                itemWidth: 8,
                textStyle: {color: 'rgba(0,0,0,.4)', fontSize: 12},
                icon: 'circle'
              },
              {
                data: ['人数占比(%)'],
                top: '0',
                x: '90%',
                itemStyle: {borderWidth: 2},
                textStyle: {color: 'rgba(0,0,0,.4)', fontSize: 12},
                itemWidth: 15,
                itemHeight: 8
              }
            ],
            grid: {left: '20px', right: '20px', top: '70px', bottom: '30px', containLabel: true},
            toolbox: {show: true, orient: 'vertical', x: 'right', y: 'center'},
            xAxis: [{
              type: 'category',
              boundaryGap: true,
              axisTick: {show: false},
              data: xData,
              axisLine: {show: false, lineStyle: {color: 'rgba(0, 0, 0, 0.2)'}},
              axisLabel: {interval: 0, color: 'rgba(0, 0, 0, 1)', fontSize: 14}
            }],
            yAxis: [
              {
                type: 'value',
                axisTick: {show: false},
                axisLine: {
                  show: true,
                  lineStyle: {color: 'rgba(0, 0, 0, 0.2)'},
                  symbol: ['none', 'arrow'],
                  symbolSize: [5, 12],
                  symbolOffset: [0, 10]
                },
                // max: 100,
                axisLabel: {interval: 0, color: 'rgba(54,58,68,0.70)', fontSize: 14},
                splitLine: {show: true, lineStyle: {color: 'rgba(0, 0, 0, 0.2)', width: 1, type: 'solid'}}
              },
              {
                type: 'value',
                axisTick: {show: false},
                axisLine: {
                  show: false,
                  lineStyle: {color: 'rgba(207, 227, 252, 1)'},
                  symbol: ['none', 'arrow'],
                  symbolSize: [5, 12],
                  symbolOffset: [0, 10]
                },
                // min: 0,
                // max: 100,
                axisLabel: {interval: 0, color: 'rgba(54,58,68,0.70)', fontSize: 14, formatter: '{value}%'},
                splitLine: {show: false, lineStyle: {color: 'rgba(39, 57, 75, 1)', width: 1, type: 'solid'}}
              }
            ],
            series: [
              {
                name: '人数占比(%)',
                yAxisIndex: 1,
                type: 'line',
                smooth: true,
                showSymbol: false,
                areaStyle: {
                  color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                    {offset: 0, color: 'rgba(32,128,247,.4)'},
                    {offset: 1, color: 'rgba(32,128,247,0)'}
                  ])
                },
                data: rateData,
                symbol: 'circle',
                symbolSize: 8,
                itemStyle: {
                  normal: {
                    color: '#2080F7',
                    borderColor: '#2080F7',
                    lineStyle: {color: '#2080F7'}
                  }
                }
              },
              {
                z: 2,
                type: 'bar',
                yAxisIndex: 0,
                name: '平均薪资（元）',
                itemStyle: {normal: {color: '#2080F7'}},
                barWidth: 24,
                data: countData
              },
              {
                z: 1,
                type: 'bar',
                barGap: '-100%',
                yAxisIndex: 0,
                name: '人数',
                itemStyle: {normal: {color: '#B2D4FF'}},
                barWidth: 24,
                data: resolvedData
              }
            ]
          };

          let chartDom = document.getElementById('bar');
          let myChart = echarts.init(chartDom);
          myChart.setOption(option);
        } else {
          this.$message.error(res.message);
        }
      });
    },
    // 学历分析
    getEducationCount() {
      this.$request.get('/jobData/educationCount').then(res => {
        console.log("bar",res.data);
        const data = res.data;

        // 将后端数据整合为图表数据格式
        const educationMap = {
          '本科': '本科',
          '硕士': '硕士',
          '博士': '博士',
          '大专': '大专',
          '应届': '本科',
          '不限': '其他'
        };

        const chartDataMap = {};
        data.forEach(item => {
          const key = educationMap[item.education] || '其他';
          if (!chartDataMap[key]) {
            chartDataMap[key] = 0;
          }
          chartDataMap[key] += item.count;
        });

        const chartData = Object.keys(chartDataMap).map(key => ({
          name: key,
          value: chartDataMap[key],
          unit: '人'
        }));

        let color = ['#FF8700', '#ffc300', '#00e473', '#009DFF'];
        let dashedPic = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAM8AAAAOBAMAAAB6G1V9AAAAD1BMVEX////Kysrk5OTj4+TJycoJ0iFPAAAAG0lEQVQ4y2MYBaNgGAMTQQVFOiABhlEwCugOAMqzCykGOeENAAAAAElFTkSuQmCC';
        let arrName = [], arrValue = [], pieSeries = [], lineYAxis = [];
        let sum = 0;

        chartData.forEach((v, i) => {
          arrName.push(v.name);
          arrValue.push(v.value);
          sum += v.value;
        });

        chartData.forEach((v, i) => {
          pieSeries.push({
            name: '学历',
            type: 'pie',
            clockWise: false,
            hoverAnimation: false,
            radius: [65 - i * 15 + '%', 57 - i * 15 + '%'],
            center: ["30%", "50%"],
            label: { show: false },
            data: [
              { value: v.value, name: v.name },
              {
                value: sum - v.value,
                name: '',
                itemStyle: { color: "rgba(0,0,0,0)" }
              }
            ]
          });
          pieSeries.push({
            name: '',
            type: 'pie',
            silent: true,
            z: 1,
            clockWise: false,
            hoverAnimation: false,
            radius: [65 - i * 15 + '%', 57 - i * 15 + '%'],
            center: ["30%", "50%"],
            label: { show: false },
            data: [
              {
                value: 7.5,
                itemStyle: { color: "#E3F0FF" }
              },
              {
                value: 2.5,
                name: '',
                itemStyle: { color: "rgba(0,0,0,0)" }
              }
            ]
          });

          v.percent = (v.value / sum * 100).toFixed(1) + "%";
          lineYAxis.push({
            value: i,
            textStyle: {
              rich: {
                circle: {
                  color: color[i],
                  padding: [0, 5]
                }
              }
            }
          });
        });

        const option = {
          backgroundColor: '#fff',
          color: color,
          grid: {
            top: '15%',
            bottom: '54%',
            left: "30%",
            containLabel: false
          },
          yAxis: [{
            type: 'category',
            inverse: true,
            axisLine: { show: false },
            axisTick: { show: false },
            axisLabel: {
              formatter: function(params) {
                let item = chartData[params];
                return '{line|}{circle|●}{name|' + item.name + '}{bd||}{percent|' + item.percent + '}{value|' + item.value + '}{unit|人}';
              },
              interval: 0,
              inside: true,
              textStyle: {
                color: "#333",
                fontSize: 14,
                rich: {
                  line: {
                    width: 170,
                    height: 10,
                    backgroundColor: { image: dashedPic }
                  },
                  name: {
                    color: '#666',
                    fontSize: 14,
                  },
                  bd: {
                    color: '#ccc',
                    padding: [0, 5],
                    fontSize: 14,
                  },
                  percent: {
                    color: '#333',
                    fontSize: 14,
                  },
                  value: {
                    color: '#333',
                    fontSize: 16,
                    fontWeight: 500,
                    padding: [0, 0, 0, 20]
                  },
                  unit: {
                    fontSize: 14
                  }
                }
              },
              show: true
            },
            data: lineYAxis
          }],
          xAxis: [{ show: false }],
          series: pieSeries
        };

        let chartDom = document.getElementById('pie');
        let myChart = echarts.init(chartDom);
        myChart.setOption(option);

      });
    }




  }
}
</script>
