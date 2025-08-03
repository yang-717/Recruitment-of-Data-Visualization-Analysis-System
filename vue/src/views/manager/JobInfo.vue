<template>
  <div>
    <div class="search">
      <el-input placeholder="请输入标题查询" style="width: 200px" v-model="title"></el-input>
      <el-button type="info" plain style="margin-left: 10px" @click="load(1)">查询</el-button>
      <el-button type="warning" plain style="margin-left: 10px" @click="reset">重置</el-button>
    </div>

    <div class="operation" style="display: flex; gap: 10px; margin-top: 4px;">
      <el-select placeholder="请选择城市" style="width: 200px" v-model="city" @change="load(1)">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
        />
      </el-select>
      <el-select placeholder="请选择岗位类型" style="width: 200px" v-model="jobType" @change="load(1)">
        <el-option
            v-for="item in typeOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
        />
      </el-select>
    </div>



    <div class="job-list">
      <el-card
          v-for="job in tableData"
          :key="job.id"
          class="job-card"
          shadow="hover"
          @mouseenter.native="hoveredJobId = job.id"
          @mouseleave.native="hoveredJobId = null"
      >
        <div class="job-header">
          <h3 class="job-title">{{ job.title }}</h3>
          <div class="salary">{{ formatSalaryRange(job) }}</div>
        </div>
        <div class="job-info">
          <p>公司：{{ job.companyTitle }}</p>
          <p>地点：{{ job.city }}</p>
          <p>经验要求：{{ job.workExperience }}</p>
          <p>学历要求：{{ job.education }}</p>
        </div>
        <div class="job-tags">
          <el-tag v-for="tag in parseTags(job.workTag)" :key="tag" size="small">{{ tag }}</el-tag>
        </div>
        <div class="job-welfare">
          <el-tag v-for="tag in parseTags(job.welfare)" :key="tag" type="success" size="small">{{ tag }}</el-tag>
        </div>
        <div v-if="hoveredJobId === job.id" class="job-detail-hover">
          <p><strong>岗位描述：</strong></p>
          <el-tag
              v-for="tag in parseTags(job.totalTag)"
              :key="tag"
              type="info"
              size="mini"
          >
            {{ tag }}
          </el-tag>
        </div>
      </el-card>
    </div>
    <el-pagination
      background
      layout="total, prev, pager, next"
      :current-page="pageNum"
      :page-size="pageSize"
      :total="total"
      @current-change="handleCurrentChange"
      style="margin-top: 20px; text-align: center;"
    ></el-pagination>


  </div>
</template>

<script>
export default {
  name: "JobInfo",
  data() {
    return {
      tableData: [],  // 所有的数据
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      title: null,
      city: null,
      jobType: null,
      fromVisible: false,
      form: {},
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      rules: {
        title: [
          {required: true, message: '请输入标题', trigger: 'blur'},
        ],
        content: [
          {required: true, message: '请输入内容', trigger: 'blur'},
        ]
      },
      ids: [],
      hoveredJobId: null,
      // 下拉框的值
      options: [],
      value: '',
      typeOptions: [],
    }
  },
  created() {
    this.load(1)
    this.selectAll()
  },
  methods: {
    //获取筛选框城市和岗位类型
    selectAll() {
      this.$request.get('/jobData/filters').then(res => {
        console.log('filters接口返回:', res.data)
        const data = res.data || {};

        this.options = (data.cityList || []).map(city => ({
          label: city,
          value: city
        }));
        this.typeOptions = (data.typeList || []).map(type => ({
          label: type,
          value: type
        }));
      });
    },
    load(pageNum) {  // 分页查询
      console.log('当前选择的城市:', this.city); // 打印 city 的值
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/jobData/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          title: this.title,
          city: this.city,
          type: this.jobType,
        }
      }).then(res => {
        console.log('jobList', res.data)
        this.tableData = res.data?.list
        this.total = res.data?.total
      })
    },
    // 重置
    reset() {
      this.title = null
      this.city = null
      this.jobType = null
      this.load(1)
    },
    // 格式化薪资区间
    formatSalaryRange(job) {
      const min = job.minSalary ? Math.round(job.minSalary / 1000) : null;
      const max = job.maxSalary ? Math.round(job.maxSalary / 1000) : null;
      if (min && max) {
        return `${min}k-${max}k`;
      } else if (min) {
        return `${min}k以上`;
      } else if (max) {
        return `${max}k以下`;
      } else {
        return '面议';
      }
    },
    // 解析标签
    parseTags(str) {
      const result = str ? str.split(/[，,、|/]/).filter(Boolean) : [];
      // console.log('parseTags 输入：', str, '输出：', result);
      return result;
    },
    //  处理分页
    handleCurrentChange(newPage) {
      this.load(newPage)
    },
  }
}
</script>

<style scoped>
.job-list {
  display: flex;
  flex-direction: column;
  gap: 16px;
  margin-top: 20px;
}

.job-card {
  cursor: pointer;
  transition: transform 0.2s;
}

.job-card:hover {
  transform: scale(1.01);
}

.job-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.job-title {
  font-size: 18px;
  font-weight: bold;
}

.salary {
  color: #f56c6c;
  font-size: 16px;
  font-weight: bold;
}

.job-tags,
.job-welfare {
  margin-top: 10px;
  display: flex;
  flex-wrap: wrap;
  gap: 6px;
}

.job-detail-hover {
  background-color: #f9f9f9;
  padding: 10px;
  margin-top: 10px;
  border-top: 1px solid #eee;
  font-size: 14px;
  color: #666;
}

.search {
  margin-bottom: 4px;
}
</style>
