package com.example.mapper;

import com.example.entity.JobData;

import java.util.List;

/**
 * 操作jobData相关数据接口
*/
public interface JobDataMapper {

    /**
      * 新增
    */
    int insert(JobData jobData);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(JobData jobData);

    /**
      * 根据ID查询
    */
    JobData selectById(Integer id);

    /**
      * 查询所有
    */
    List<JobData> selectAll(JobData jobData);

    /**
      * 城市平均薪资top10
    */
    List<JobData> getTop10AverageSalaries();

    /**
      * 获取工资区间统计
    */
    List<JobData> getSalaryCategoryCount();
    /**
      * 获取工作经验分析
    */
    List<JobData> getExperienceAnalysis();
    /**
     * 获取城市岗位统计
     */
    List<JobData> getCityJobCount();
    /**
     * 获取公司人数区间统计
     */
    List<JobData> getCompanyPeopleCategoryCount();
    /**
     * 获取最高薪资
     */
    List<JobData> getTopSalary();
    /**
     * 获取行业薪资区间
     */
    List<JobData> getIndustrySalaryCategoryCount();
    /**
     * 获取行业平均薪资
     */
    List<JobData> getIndustryAverageSalary();
    /**
     * 获取工作经验&薪资分析
     */
    List<JobData> getExperienceSalaryCount();
    /**
     * 获取学历统计
     */
    List<JobData> getEducationCount();
    /**
     * 获取行业个数统计
     */
    List<JobData> getIndustryCount();
    /**
     * 获取各工作类型最大薪资
     */
    List<JobData> getJobTypeMaxSalary();
    /**
     * 获取各城市薪资情况
     */
    List<JobData> getCitySalaryCategoryCount();
    /**
     * 获取公司人数区间统计
     */
    List<JobData> getCompanyPeopleCategory();
    /*** 获取所有城市*/
    List<String> getAllCities();
    /*** 获取所有工作类型*/
    List<String> getAllTypes();
}
