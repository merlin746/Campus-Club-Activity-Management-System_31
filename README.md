# Campus-Club-Activity-Management-System_31

## 项目简介

`Campus-Club-Activity-Management-System_31` 是一个基于 C语言、Java 和数据库技术开发的校园社团活动管理系统。该系统旨在为学校社团提供一个便捷、高效的活动管理平台，涵盖从活动发起、审批、报名到数据统计的全流程，以数字化方式提升社团管理效率。

## 项目背景

随着校园社团数量和活动规模的不断扩大，传统的线下或分散式管理方式（如Excel记录、人工统计）已难以满足需求。本项目旨在通过技术手段，解决社团活动管理中流程不规范、信息不透明、数据难追踪等问题，为社团成员和管理者提供一站式解决方案。

## 技术栈

*   **编程语言**:
    *   **Java**: 主要用于实现图形用户界面（GUI）和业务逻辑处理。
    *   **C语言**: 可用于实现部分核心算法或与硬件交互（如有需要）。
*   **数据库**:
    *   **MySQL**: 用于存储用户信息、社团信息、活动信息等核心数据。
*   **开发工具与环境**:
    *   **IDE**: IntelliJ IDEA (Java 开发)、VS Code (C语言开发)
    *   **数据库管理工具**: Navicat / MySQL Workbench
    *   **JDK 版本**: JDK 8 或以上
    *   **MySQL 版本**: MySQL 5.7 或以上

## 功能模块

### 1. 用户登录与注册模块
*   用户可注册为社团成员或管理员。
*   支持用户登录验证，并根据角色跳转到相应功能界面。

### 2. 社团管理模块
*   **管理员视角**: 创建/解散社团、审批新社团申请、管理社团信息。
*   **成员视角**: 查看社团列表、申请加入/退出社团。

### 3. 活动管理模块
*   **发起活动**: 社团管理员可发起新活动，并填写活动详情（时间、地点、描述等）。
*   **活动审批**: 校级管理员可对发起的活动进行审批。
*   **活动报名**: 社团成员可浏览并报名参加已审批的活动。
*   **活动签到**: 记录活动参与情况。

### 4. 数据查询与统计模块
*   查询社团历史活动记录、成员参与情况。
*   统计社团活跃度、活动参与率等关键指标，为社团评估提供数据支持。

## 数据库设计

列出项目中核心的数据表结构，示例如下：

*   **用户表 (`user`)**
    *   `user_id` (INT, PK): 用户ID
    *   `username` (VARCHAR): 用户名
    *   `password` (VARCHAR): 密码
    *   `role` (ENUM): 角色 (admin/member)
    *   `club_id` (INT, FK): 所属社团ID

*   **社团表 (`club`)**
    *   `club_id` (INT, PK): 社团ID
    *   `club_name` (VARCHAR): 社团名称
    *   `introduction` (TEXT): 社团简介
    *   `advisor_id` (INT, FK): 指导老师ID

*   **活动表 (`activity`)**
    *   `activity_id` (INT, PK): 活动ID
    *   `club_id` (INT, FK): 发起社团ID
    *   `activity_name` (VARCHAR): 活动名称
    *   `activity_time` (DATETIME): 活动时间
    *   `location` (VARCHAR): 活动地点
    *   `status` (ENUM): 活动状态 (pending/approved/canceled)

*   **报名表 (`signup`)**
    *   `signup_id` (INT, PK): 报名ID
    *   `activity_id` (INT, FK): 活动ID
    *   `user_id` (INT, FK): 用户ID
    *   `signup_time` (TIMESTAMP): 报名时间

## 安装与使用

### 1. 环境准备
*   安装 JDK 8+ 并配置环境变量。
*   安装 MySQL 5.7+ 并启动服务。
*   安装对应的 IDE。

### 2. 项目部署
1.  **克隆仓库**:
    ```bash
    git clone https://github.com/merlin746/Campus-Club-Activity-Management-System_31.git
