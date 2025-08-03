package com.example.service;

import com.example.entity.*;
import com.example.entity.Edge;
import com.example.mapper.JobDataMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Resource;
import java.util.List;

/**
 * 工作信息表业务处理
 **/
@Service
public class JobDataService {

    @Resource
    private JobDataMapper jobDataMapper;

    private List<Edge> edgeCache;

    @PostConstruct
    public void initEdgeTable() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("data/cityEdges.json")) {
            ObjectMapper mapper = new ObjectMapper();
            edgeCache = mapper.readValue(in, new TypeReference<List<Edge>>() {});
            System.out.println("邻接表加载完成，共有边数：" + edgeCache.size());
        } catch (IOException e) {
            throw new RuntimeException("无法加载 cityEdges.json", e);
        }
    }

    public List<Edge> getEdgeCache() {
        return edgeCache;
    }

    /**
     * 新增
     */
    public void add(JobData jobData) {
//        jobData.setTime(DateUtil.today());
//        Account currentUser = TokenUtils.getCurrentUser();
//        jobData.setUser(currentUser.getUsername());
        jobDataMapper.insert(jobData);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        jobDataMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            jobDataMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(JobData jobData) {
        jobDataMapper.updateById(jobData);
    }

    /**
     * 根据ID查询
     */
    public JobData selectById(Integer id) {
        return jobDataMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<JobData> selectAll(JobData jobData) {
        return jobDataMapper.selectAll(jobData);
    }

    /**
     * 分页查询
     */
    public PageInfo<JobData> selectPage(JobData jobData, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JobData> list = jobDataMapper.selectAll(jobData);
        return PageInfo.of(list);
    }
    /**
     * 城市平均薪资top10
     */
    public List<JobData> getTop10AverageSalaries() {
        return jobDataMapper.getTop10AverageSalaries();
    }
    /**
     * 获取工资区间统计
     */
    public List<JobData> getSalaryCategoryCount() {
        return jobDataMapper.getSalaryCategoryCount();
    }
    /**
     * 获取工作经验分析
     */
    public List<JobData> getExperienceAnalysis() {
        return jobDataMapper.getExperienceAnalysis();
    }

    /**
     * 获取城市岗位统计
     */
    public List<JobData> getCityJobCount() {
        return jobDataMapper.getCityJobCount();
    }

    /**
     * 获取公司人数区间统计
     */
    public List<JobData> getCompanyPeopleCategoryCount() {
        return jobDataMapper.getCompanyPeopleCategoryCount();
    }

    /**
     * 获取最高薪资前十
     */
    public List<JobData> getTopSalary() {
        return jobDataMapper.getTopSalary();
    }

    /**
     * 获取行业薪资区间
     */
    public List<JobData> getIndustrySalaryCategoryCount() {
        return jobDataMapper.getIndustrySalaryCategoryCount();
    }

    /**
     * 获取行业平均薪资
     */
    public List<JobData> getIndustryAverageSalary() {
        return jobDataMapper.getIndustryAverageSalary();
    }

    /**
     * 获取不同经验情况下每个平均薪资的个数
     */
    public List<JobData> getExperienceSalaryCount() {
        return jobDataMapper.getExperienceSalaryCount();
    }

    /**
     * 获取学历统计
     */
    public List<JobData> getEducationCount() {
        return jobDataMapper.getEducationCount();
    }

    /**
     * 获取行业个数统计
     */
    public List<JobData> getIndustryCount() {
        return jobDataMapper.getIndustryCount();
    }

    /**
     * 获取各工作类型最大薪资
     */
    public List<JobData> getJobTypeMaxSalary() {
        return jobDataMapper.getJobTypeMaxSalary();
    }

    /**
     * 获取各城市薪资情况
     */
        public List<JobData> getCitySalaryCategoryCount() {
        return jobDataMapper.getCitySalaryCategoryCount();
    }

    /**
     * 获取城市人数
     */
    public List<JobData> getCompanyPeopleCategory() {
        return jobDataMapper.getCompanyPeopleCategory();
    }
    /**
     *获取所有城市
     */
    public List<String> getAllCities(){ return jobDataMapper.getAllCities();}
    /**
     *获取所有工作类型
     */
    public List<String> getAllTypes(){ return jobDataMapper.getAllTypes();}
}


