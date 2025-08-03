<template>
  <div>

    <div style="font-size: 24px; font-weight: bold; margin-bottom: 10px; color: #2c334c" class="card">行业分析</div>
    <div style="font-size: 18px; font-weight: bold; color: #2c334c" class="card">行业岗位分布图</div>
      <div style="display: flex">
        <div id="bar" style="height: 450px; flex: 1" class="card"></div>
      </div>
    <div style="font-size: 18px; font-weight: bold; color: #2c334c" class="card">行业薪资分布图</div>
    <div style="display:flex">
        <div id="pie" style="height: 500px; flex: 1" class="card"></div>
     </div>
  </div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  name: 'IndustryAnalysis',
  data() {
    return {

    }
  },
  created() {
    this.getIndustryCount();
    this.getJobTypeMaxSalary();
  },
  methods:{
    // 行业count
    getIndustryCount() {
      this.$request.get('/jobData/industryCount').then(res => {
        if (res.code === '200') {
          let chartDom = document.getElementById('bar');
          let myChart = echarts.init(chartDom);

          const rawData = res.data;
          const data = rawData.map(item => item.count);
          const timeData = rawData.map(item => item.industry);
          let key = 0;
          let colorList = ['#73DDFF', '#73ACFF', '#FDD56A', '#FDB36A', '#FD866A', '#9E87FF', '#00FFFF','#CD5C5C','#F08080'];

          let option = {
            tooltip: {
                show: true
            },
            xAxis: {
                type: 'category',
                boundaryGap: true,
                min: -1,
                max: 9,
                axisLine: {
                    show:false,
                    lineStyle: {
                        color: '#4bbbf8',
                        shadowColor: 'rgba(75, 211, 255, 0.5)',
                        shadowBlur: 5
                    }
                },
                axisTick: {
                    show:false,
                    interval: (index) => {
                        if (index === -1 || index === 9) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                },
                axisLabel: {
                    color: '#fff',
                    fontSize: 12
                },
                splitLine: {
                    show: false
                },
                data: timeData
            },
            yAxis: [{
                max: Math.max(...data) + 100,
                min: 0,
                show:false,
                axisLine: {
                    show:false,
                    lineStyle: {
                        color: '#4bbbf8',
                        shadowColor: 'rgba(75, 211, 255, 0.5)',
                        shadowBlur: 5
                    }
                },
                axisLabel: {
                    color: '#fff',
                    fontSize: 12
                },
                splitLine: {
                    show:false,
                    lineStyle: {
                        color: 'rgba(75, 211, 255, 0.2)',
                        type: 'dashed'
                    }
                }
            }],
            series: [{
                // name: '岗位',
              type: 'bar',
              barCategoryGap: '20%',
              center: ['50%', '50%'],
              label: {
                    show: true,
                    position: 'top',
                    color: '#1798ff',
                    fontSize: 14
                },
                itemStyle: {
                  barBorderRadius: [30, 30, 0, 0],
                    color: function(params){
                        return new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: colorList[params.dataIndex % colorList.length]
                        }, {
                            offset: 1,
                            color: colorList[params.dataIndex % colorList.length] + '00'
                        }], false)
                    }
                },
                data: data
            }]
          };

          myChart.setOption(option);
        } else {
          this.$message.error(res.message);
        }
      });
    },
    // 行业 平均薪资
    getJobTypeMaxSalary() {
      this.$request.get('/jobData/maxSalary').then(res => {
        if (res.code === '200') {
          let chartDom = document.getElementById('pie');
          let myChart = echarts.init(chartDom);

          const rawData = res.data;
          const pieData = rawData.map(item => ({ value: item.maxSalary, name: item.type }));

          let option = {
            tooltip: {
              trigger: 'item',
              show: true,
              formatter: function(params) {
                return `${params.name}<br/>最高薪资: ${params.value}`;
              }
            },
            legend: {
              right: 'right',
              bottom: '100px',

            },
            toolbox: {
              show: true,
              feature: {
                mark: { show: true },
                dataView: { show: true, readOnly: false },
                restore: { show: true },
                saveAsImage: { show: true }
              }
            },
            series: [
              {
                name: 'Nightingale Chart',
                type: 'pie',
                radius: [50, 200],
                center: ['50%', '50%'],
                roseType: 'area',
                itemStyle: {
                  borderRadius: 8
                },
                data: pieData
              }
            ]
          };

          myChart.setOption(option);
        } else {
          this.$message.error(res.message);
        }
      });
    },




  }
}
</script>
