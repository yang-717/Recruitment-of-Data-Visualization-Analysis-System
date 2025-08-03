package com.example.controller;

import com.example.common.Result;
import com.example.entity.JobData;
import com.example.service.JobDataService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工作信息表前端操作接口
 **/
@RestController
@RequestMapping("/jobData")
public class JobDataController {

    @Resource
    private JobDataService jobDataService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody JobData jobData) {
        jobDataService.add(jobData);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        jobDataService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        jobDataService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody JobData jobData) {
        jobDataService.updateById(jobData);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        JobData jobData = jobDataService.selectById(id);
        return Result.success(jobData);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(JobData jobData ) {
        List<JobData> list = jobDataService.selectAll(jobData);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(JobData jobData,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<JobData> page = jobDataService.selectPage(jobData, pageNum, pageSize);
        return Result.success(page);
    }
    /**
     * 城市平均薪资top10
     */
    @GetMapping("/top10")
    public Result getTop10AverageSalaries() {
        List<JobData> list = jobDataService.getTop10AverageSalaries();

        // 构建图表数据
        List<String> yAxis = new ArrayList<>();
        List<Double> xAxis = new ArrayList<>();
        for (JobData jobData : list) {
            yAxis.add(jobData.getCity());
            xAxis.add(jobData.getAverageSalary());
        }

        // 构建返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("text", "城市平均薪资柱状图");
        result.put("subtext", "统计维度：城市");
        result.put("xAxis", xAxis);
        result.put("yAxis", yAxis);

        return Result.success(result);
    }
    /**
     * 获取工资区间统计
     */
    @GetMapping("/salaryCategoryCount")
    public Result getSalaryCategoryCount() {
        List<JobData> list = jobDataService.getSalaryCategoryCount();

        // 构建返回给前端的饼图数据
        List<Map<String, Object>> pieData = new ArrayList<>();

        for (JobData jobData : list) {
            Map<String, Object> item = new HashMap<>();
            item.put("name", jobData.getSalaryCategory()); // 名称
            item.put("value", jobData.getCount()); // 数量
            pieData.add(item);
        }

        return Result.success(pieData);
    }
    /**
     * 获取工作经验分析
     */
    @GetMapping("/experienceAnalysis")
    public Result getExperienceAnalysis() {
        List<JobData> list = jobDataService.getExperienceAnalysis();

        // 构建返回给前端的数据
        List<String> xAxis = new ArrayList<>();  // 工作经验
        List<Double> yAxis1 = new ArrayList<>(); // 平均最高薪资
        List<Double> yAxis2 = new ArrayList<>(); // 平均最低薪资

        for (JobData jobData : list) {
            xAxis.add(jobData.getWorkExperience().replace("经验","")); // 工作经验
            yAxis1.add(jobData.getAvgMaxSalary()); // 平均最高薪资
            yAxis2.add(jobData.getAvgMinSalary()); // 平均最低薪资
        }
        // 构建返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("text", "工作经验分析");
        result.put("subtext", "统计维度：工作经验");
        result.put("xAxis", xAxis);
        result.put("yAxis1", yAxis1);
        result.put("yAxis2", yAxis2);

        return Result.success(result);
    }
    /**
     * 获取城市岗位统计
     */
    @GetMapping("/cityJobCount")
    public Result getCityJobCount() {
        List<JobData> list = jobDataService.getCityJobCount();

        // 构建返回给前端的数据
        List<Map<String, Object>> resultData = new ArrayList<>();

        for (JobData jobData : list) {
            Map<String, Object> item = new HashMap<>();
            item.put("city", jobData.getCity()); // 城市
            item.put("jobCount", jobData.getJobCount()); // 岗位数量
            resultData.add(item);
        }

        // 构建返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("text", "城市岗位分布图");
        result.put("subtext", "统计维度：城市");
        result.put("data", resultData);

        return Result.success(result);
    }

    /**
     * 获取城市邻接边数据
     */
    @GetMapping("/cityEdges")
    public Result getCityEdges() {
        return Result.success(jobDataService.getEdgeCache());
    }
    /**
     * 城市分析
     */
    @GetMapping("/companyPeopleCategory")
    public Result getCompanyPeopleCategory() {
        List<JobData> list = jobDataService.getCompanyPeopleCategory();

        // 构建返回给前端的数据
        List<Map<String, Object>> resultData = new ArrayList<>();

        for (JobData jobData : list) {
            Map<String, Object> item = new HashMap<>();
            item.put("companyPeople", jobData.getPeopleCategory()); // 公司人数
            item.put("count", jobData.getJobCount()); // 数量
            item.put("city", jobData.getCity());   // 城市
            resultData.add(item);
        }

        return Result.success(resultData);
    }
    /**
     * 获取最高薪资前十
     */
    @GetMapping("/topSalary")
    public Result getTopSalary() {
        List<JobData> list = jobDataService.getTopSalary();

        // 构建返回给前端的数据
        List<Map<String, Object>> resultData = new ArrayList<>();

        for (JobData jobData : list) {
            Map<String, Object> item = new HashMap<>();
            item.put("imgSrc", jobData.getImgSrc()); // 图片
            item.put("type", jobData.getType()); // 职位名称
            item.put("title", jobData.getTitle()); // 公司名
            item.put("maxSalary",jobData.getMaxSalary()); //薪资
            item.put("city", jobData.getCity()); // 城市

            resultData.add(item);
        }

        return Result.success(resultData);
    }
    /**
     * 获取行业薪资区间
     */
    @GetMapping("/industrySalaryCategoryCount")
    public Result getIndustrySalaryCategoryCount() {
        List<JobData> list = jobDataService.getIndustrySalaryCategoryCount();

        // 构建返回给前端的数据
        List<Map<String, Object>> resultData = new ArrayList<>();

        for (JobData jobData : list) {
            Map<String, Object> item = new HashMap<>();
            item.put("industry", jobData.getType()); // 行业
            item.put("salaryCategory", jobData.getSalaryCategory()); // 工资区间
            item.put("count", jobData.getJobCount()); // 数量
            resultData.add(item);
        }

        return Result.success(resultData);
    }
    /**
     * 获取行业平均薪资
     */
    @GetMapping("/industryAverageSalary")
    public Result getIndustryAverageSalary() {
        List<JobData> list = jobDataService.getIndustryAverageSalary();

        // 构建返回给前端的数据
        List<Map<String, Object>> resultData = new ArrayList<>();

        for (JobData jobData : list) {
            Map<String, Object> item = new HashMap<>();
            item.put("industry", jobData.getType()); // 行业
            item.put("averageSalary", jobData.getAverageSalary()); // 平均薪资
            resultData.add(item);
        }

        return Result.success(resultData);
    }
    /**
     * 获取不同经验情况下每个平均薪资的个数
     */
    @GetMapping("/experienceSalaryCount")
    public Result getExperienceSalaryCount() {
        List<JobData> list = jobDataService.getExperienceSalaryCount();

        // 构建返回给前端的数据
        List<Map<String, Object>> resultData = new ArrayList<>();

        for (JobData jobData : list) {
            Map<String, Object> item = new HashMap<>();
            item.put("workExperience", jobData.getWorkExperience()); // 工作经验
            item.put("averageSalary", jobData.getAverageSalary()); // 平均薪资
            item.put("count", jobData.getExperienceCount()); // 数量
            resultData.add(item);
        }

        return Result.success(resultData);
    }
    /**
     * 学历统计
     */
    @GetMapping("/educationCount")
    public Result getEducationCount() {
        List<JobData> list = jobDataService.getEducationCount();

        // 构建返回给前端的数据
        List<Map<String, Object>> resultData = new ArrayList<>();

        for (JobData jobData : list) {
            Map<String, Object> item = new HashMap<>();
            item.put("education", jobData.getEducation()); // 学历
            item.put("count", jobData.getJobCount()); // 数量
            resultData.add(item);
        }

        return Result.success(resultData);
    }
    /**
     * 行业个数统计
     */
    @GetMapping("/industryCount")
    public Result getIndustryCount() {
        List<JobData> list = jobDataService.getIndustryCount();

        // 构建返回给前端的数据
        List<Map<String, Object>> resultData = new ArrayList<>();

        for (JobData jobData : list) {
            Map<String, Object> item = new HashMap<>();
            item.put("industry", jobData.getType()); // 行业
            item.put("count", jobData.getJobCount()); // 数量
            resultData.add(item);
        }

        return Result.success(resultData);
    }
    /**
     * 各工作类型最大薪资
     */
    @GetMapping("/maxSalary")
    public Result getJobTypeMaxSalary() {
        List<JobData> list = jobDataService.getJobTypeMaxSalary();

        // 构建返回给前端的数据
        List<Map<String, Object>> resultData = new ArrayList<>();

        for (JobData jobData : list) {
            Map<String, Object> item = new HashMap<>();
            item.put("type", jobData.getType()); // 工作类型
            item.put("maxSalary", jobData.getMaxSalary()); // 最大薪资
            resultData.add(item);
        }

        return Result.success(resultData);
    }
    /**
     * 各城市薪资情况
     */
    @GetMapping("/citySalary")
    public Result getCitySalaryCategoryCount() {
        List<JobData> list = jobDataService.getCitySalaryCategoryCount();

        // 构建返回给前端的数据
        List<Map<String, Object>> resultData = new ArrayList<>();

        for (JobData jobData : list) {
            Map<String, Object> item = new HashMap<>();
            item.put("city", jobData.getCity()); // 城市
            item.put("salaryCategory", jobData.getSalaryCategory()); // 工资区间
            item.put("count", jobData.getJobCount()); // 数量
            resultData.add(item);
        }

        return Result.success(resultData);
    }
    /**
     * 大屏分析（公司人数）
     */
    @GetMapping("/cityCount")
    public Result getCompanyPeopleCategoryCount() {
        List<JobData> list = jobDataService.getCompanyPeopleCategory();

        // 聚合相同公司人数的数据
        Map<String, Integer> aggregatedMap = new HashMap<>();
        for (JobData jobData : list) {
            String category = jobData.getPeopleCategory();
            int count = jobData.getJobCount();
            aggregatedMap.put(category, aggregatedMap.getOrDefault(category, 0) + count);
        }

        // 构建图表数据
        List<Map<String, Object>> formattedData = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : aggregatedMap.entrySet()) {
            Map<String, Object> dataItem = new HashMap<>();
            dataItem.put("name", entry.getKey());  // 公司人数区间
            dataItem.put("value", entry.getValue()); // 聚合后职位数量
            formattedData.add(dataItem);
        }

        // 构建返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("text", "公司人数漏斗图");
        result.put("subtext", "统计维度：公司人数");
        result.put("data", formattedData);

        return Result.success(result);
    }
    @GetMapping("/filters")
    public Result getFilterOptions() {
        List<String> cities = jobDataService.getAllCities();
        List<String> types = jobDataService.getAllTypes();

        Map<String, Object> result = new HashMap<>();
        result.put("cityList", cities);
        result.put("typeList", types);

        return Result.success(result);
    }
}