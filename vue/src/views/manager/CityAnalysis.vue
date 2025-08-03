<template>
  <div>

    <div style="font-size: 24px; font-weight: bold; margin-bottom: 10px; color: #2c334c" class="card">城市分析</div>
    <div style="font-size: 18px; font-weight: bold; color: #2c334c" class="card">
      <el-select v-model="value" placeholder="Select">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </div>
    <div style="display: flex; padding-top: 10px">
      <div style="flex: 1; padding-right: 10px">
        <div id="line" style="height: 450px" class="card"></div>
      </div>
      <div style="flex: 1;">
        <div id="pie" style="height: 450px" class="card"></div>
      </div>
    </div>
  </div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  name: 'CityAnalysis',
  data() {
    return {
      // 下拉框的值
      options: [],
      value: '',
    }
  },
  created() {
    this.getCitySalaryCategoryCount();
    this.getCompanyPeopleCategory();
  },
  methods:{
    // 薪资区间count
    getCitySalaryCategoryCount() {
      this.$request.get('/jobData/citySalary').then(res => {
        if (res.code === '200') {
          const data = res.data;

          // 获取所有城市
          const allCities = [...new Set(data.map(item => item.city))];
          // 设置下拉框的选项
          this.options = allCities.map(city => ({ value: city, label: city }));

          const selectedCity = this.value || allCities[0];
          const filteredData = data.filter(item => item.city === selectedCity);

          const categories = [...new Set(filteredData.map(item => item.salaryCategory))].sort((a, b) => {
            const getLow = s => s.includes('以上') ? Infinity : parseInt(s.split('-')[0], 10);
            return getLow(a) - getLow(b);
          });

          const cityData = categories.map(cat => {
            const found = filteredData.find(item => item.salaryCategory === cat);
            return found ? found.count : 0;
          });
          const series = [{
            name: selectedCity,
            type: 'line',
            stack: 'Total',
            smooth: true,
            lineStyle: { width: 0 },
            showSymbol: false,
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                { offset: 0, color: 'rgb(0, 221, 255)' },
                { offset: 1, color: 'rgb(77, 119, 255)' }
              ])
            },
            emphasis: { focus: 'series' },
            data: cityData
          }];

          const option = {
            color: ['#00DDFF'],
            title: { text: '城市薪资区间分布图' },
            tooltip: {
              trigger: 'axis',
              axisPointer: {
                type: 'cross',
                label: { backgroundColor: '#6a7985' }
              }
            },
            legend: { data: [selectedCity] },
            toolbox: {
              feature: { saveAsImage: {} }
            },
            grid: {
              left: '3%',
              right: '4%',
              bottom: '3%',
              containLabel: true
            },
            xAxis: [
              {
                type: 'category',
                boundaryGap: false,
                data: categories
              }
            ],
            yAxis: [
              {
                type: 'value'
              }
            ],
            series
          };

          let chartDom = document.getElementById('line');
          let myChart = echarts.init(chartDom);
          myChart.setOption(option);
        } else {
          this.$message.error(res.message);
        }
      });
    },
    // 公司规模
    getCompanyPeopleCategory() {
      this.$request.get('/jobData/companyPeopleCategory').then(res => {
        if (res.code === '200') {
          const data = res.data;

          // 获取所有城市
          const allCities = [...new Set(data.map(item => item.city))];
          // 设置下拉框的选项
          this.options = allCities.map(city => ({ value: city, label: city }));

          // 默认选择第一个城市
          const selectedCity = this.value || allCities[0];
          this.value = selectedCity;

          // 渲染饼图
          this.renderPieChart(selectedCity, data);
        } else {
          this.$message.error(res.message);
        }
      });
    },
    renderPieChart(selectedCity, data) {
      // 筛选选中城市的数据
      const filteredData = data.filter(item => item.city === selectedCity);

      // 转换为饼图需要的格式
      const pieData = filteredData.map(item => ({
        name: item.companyPeople,
        value: item.count
      }));

      // 配置饼图
      const option = {
        title: { text: '公司规模区间分布图' },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          bottom: '5%',
          left: 'center'
        },
        series: [
          {
            name: '公司人数分布',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 40,
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: pieData
          }
        ]
      };

      // 渲染饼图
      let chartDom = document.getElementById('pie');
      let myChart = echarts.init(chartDom);
      myChart.setOption(option);
    }




  },
  watch: {
    value() {
      this.getCitySalaryCategoryCount();
      this.getCompanyPeopleCategory();
    }
  }
}
</script>
