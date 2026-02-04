# Create: Infinite Water（机械动力：无限水）


<img src="src/main/resources/assets/infinitewater/logo/logo.png" width="200" />

一个基于 **Create（机械动力）** 的附属模组：新增一台可被动能网络驱动的机器，用于在满足应力条件时提供“可抽取的水源”。这个模组几乎可以给所有的其他模组进行供水，满足了那些既想追求数值上的平衡，又想寻求简洁性的玩家。

> 适用环境：Minecraft **1.21.1**（NeoForge）  
> 依赖：Create / Flywheel / Ponder

---

## 功能简介

### 无限水机器（Infinite Water Block）
- 需要应力源提供应力，并且**达到应力阈值后** 才能供水。
- **应力输入方向：上 / 下**
- **水输出方向：四侧（前后左右）可抽取**
- 支持 Create 护目镜信息显示（可展示运行状态、阈值等）
- 支持 Ponder 思索教程（演示接入方式与阈值条件）

###  Customization | 自定义

#### Configuration | 配置文件
Edit `config/infinitewater-server.toml` to adjust:

编辑配置文件可调整：


- **转速阈值** - 转速阈值（默认值：200 RPM）
- **应力影响** - 1RPM所消耗的应力（默认值：5000 SU/RPM）



###  KubeJS 支持

您可以修改：
- 所有的制作配方
- 应力阈值


**注意：** 转速阈值只能通过配置文件修改，KubeJS 无法修改。


---

## 安装与依赖

### 必装依赖
- NeoForge（MC 1.21.1）
- Create（机械动力）
- Flywheel
- Ponder

---

## 许可与致谢

- 本模组为 Create 的附属模组，感谢 Create / Flywheel / Ponder 的作者与社区贡献。

---

## 联系方式 / 反馈

- 在 Issues / 反馈渠道提交：
    - 崩溃报告（Crash Report）
    - latest.log 中的报错片段

- QQ：1376982098
- 邮箱：137698298@qq.com