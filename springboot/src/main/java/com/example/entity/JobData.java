package com.example.entity;


import java.io.Serializable;

/**
 * 职位信息表
 */
public class JobData implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** 职位类型 */
    private String type;
    /** 工作名称 */
    private String title;
    /** 公司名称 */
    private String companyTitle;
    /** 最低薪资 */
    private Integer minSalary;
    /** 最高薪资 */
    private Integer maxSalary;
    /** 工作经验 */
    private String workExperience;
    /** 学历要求 */
    private String education;
    /** 公司描述 */
    private String totalTag;
    /** 公司人数 */
    private String companyPeople;
    /** 工作要求 */
    private String workTag;
    /** 公司福利 */
    private String welfare;
    /** 图片链接 */
    private String imgSrc;
    /** 工作地点 */
    private String city;

    /** 平均薪资 */
    private Double averageSalary;
    /** 工资区间分类 */
    private String salaryCategory;
    /** 工资区间分类统计 */
    private Integer count;
    /** 最大平均薪资 */
    private Double AvgMaxSalary;
    /** 最小平均薪资 */
    private Double AvgMinSalary;

    /** 公司人员分类 */
    private String peopleCategory;
    private Integer jobCount;

    /** 经验分析计数 */
    private Integer experienceCount;

    public Integer getExperienceCount() {
        return experienceCount;
    }

    public void setExperienceCount(Integer experienceCount) {
        this.experienceCount = experienceCount;
    }

    public String getPeopleCategory() {
        return peopleCategory;
    }

    public void setPeopleCategory(String peopleCategory) {
        this.peopleCategory = peopleCategory;
    }

    public Integer getJobCount() {
        return jobCount;
    }

    public void setJobCount(Integer jobCount) {
        this.jobCount = jobCount;
    }

    public Double getAvgMaxSalary() {
        return AvgMaxSalary;
    }

    public void setAvgMaxSalary(Double avgMaxSalary) {
        AvgMaxSalary = avgMaxSalary;
    }

    public Double getAvgMinSalary() {
        return AvgMinSalary;
    }

    public void setAvgMinSalary(Double avgMinSalary) {
        AvgMinSalary = avgMinSalary;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSalaryCategory() {
        return salaryCategory;
    }

    public void setSalaryCategory(String salaryCategory) {
        this.salaryCategory = salaryCategory;
    }

    public Double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(Double averageSalary) {
        this.averageSalary = averageSalary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyTitle() {
        return companyTitle;
    }

    public void setCompanyTitle(String companyTitle) {
        this.companyTitle = companyTitle;
    }

    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getTotalTag() {
        return totalTag;
    }

    public void setTotalTag(String totalTag) {
        this.totalTag = totalTag;
    }

    public String getCompanyPeople() {
        return companyPeople;
    }

    public void setCompanyPeople(String companyPeople) {
        this.companyPeople = companyPeople;
    }

    public String getWorkTag() {
        return workTag;
    }

    public void setWorkTag(String workTag) {
        this.workTag = workTag;
    }

    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}