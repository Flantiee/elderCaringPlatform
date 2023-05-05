<template>
  <div class="dashboard-editor-container">
    <panel-group @handleSetLineChartData="handleSetLineChartData" />

    <el-row style="background: #fff; padding: 16px 16px 0; margin-bottom: 32px">
      <line-chart :chart-data="lineChartData" />
    </el-row>

    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <raddar-chart :chart-data="raddarChartData" />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <pie-chart :chart-data="pieChartData" />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <bar-chart :chart-data="barChartData" />
        </div>
      </el-col>
    </el-row>

    <!-- <el-row :gutter="8">
      <el-col :xs="{span: 24}" :sm="{span: 24}" :md="{span: 24}" :lg="{span: 12}" :xl="{span: 12}" style="padding-right:8px;margin-bottom:30px;">
        <transaction-table />
      </el-col>
      <el-col :xs="{span: 24}" :sm="{span: 12}" :md="{span: 12}" :lg="{span: 6}" :xl="{span: 6}" style="margin-bottom:30px;">
        <todo-list />
      </el-col>
      <el-col :xs="{span: 24}" :sm="{span: 12}" :md="{span: 12}" :lg="{span: 6}" :xl="{span: 6}" style="margin-bottom:30px;">
        <box-card />
      </el-col>
    </el-row> -->
  </div>
</template>

<script>
import PanelGroup from "./components/PanelGroup";
import LineChart from "./components/LineChart";
import RaddarChart from "./components/RaddarChart";
import PieChart from "./components/PieChart";
import BarChart from "./components/BarChart";
import {getLineChartList,getBarChartList,getPieChartList,getRaddarChartList} from '@/api/dashBord'


const lineChartData = {
  //后端传送给前端该数据
  commityNews: { //通过传的adminID，就可以知道该用户是哪个社区的管理者
    //本社区发的新闻数量 communityNews表
    previousMonth: [10, 12, 1, 13, 10, 16, 16], //后端获取时间，比如说现在是12月, 则presentMonth是12月的数据,previousMonth就是上个月数据
    presentMonth: [1, 8, 9, 15, 16, 14, 14], //这里面七个元素是分别是代表本月周一-到周天的请求数量
  },
  interactions: {
    //本社区发起的邻里互动数量 neighbor_interaction表
    previousMonth: [17, 19, 12, 14, 16, 13, 14], //这个表里面没有时间，那就直接发这个假数据,
    presentMonth: [18, 16, 15, 10, 14, 15, 13],
  },
  imergencys: {
    //本社区老人发起的紧急请求, emergencyevent表
    previousMonth: [8, 10, 12, 10, 10, 9, 10],
    presentMonth: [12, 9, 10, 13, 14, 13, 13],
  },
  requests: {
    //本社区老人发起的各种服务请求, request表
    previousMonth: [10, 14, 14, 12, 15, 20, 10],
    presentMonth: [12, 8, 1, 14, 12, 10, 10],
  },
};

const barChartData = { //当前社区，对应request表中的数据(不分年月，所有现有数据分周一-周天)
  familyDoc: [79, 52, 200, 334, 290, 330, 220], //这里面七个元素是分别是代表本月周一-到周天的请求数量
  houseClean: [80, 52, 200, 334, 390, 130, 220],
  elderService: [30, 52, 200, 334, 190, 330, 220],
  mentalHealth: [66, 52, 150, 334, 390, 330, 130],
};

const pieChartData = [//当前社区的老人,neighbor_interaction表中，value代表 当前老人receiverID出现的次数，
  //修改
  { value: 12, name: "王奶奶" },
  { value: 7, name: "陈奶奶" },
  { value: 7, name: "熊爷爷" },
  { value: 6, name: "李奶奶" },
  { value: 5, name: "杨奶奶" },
];

const raddarChartData = [
  //修改
  {
    value: [14, 14, 21, 17], //当前管理者所在小区，四种不同的服务出现的次数
    name: "幸福小区",         //数组里的元素分别为, 家庭医生、保洁卫生、助老服务、心理建设
  },
];

export default {
  name: "DashboardAdmin",
  components: {
    PanelGroup,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart,
    // TransactionTable,
    // TodoList,
    // BoxCard
  },
  data() {
    return {
      lineChartDataWrap:null,
      lineChartData: lineChartData.commityNews,
      barChartData: barChartData,
      pieChartData: pieChartData,
      raddarChartData: raddarChartData
    };
  },
  mounted() {
    this.getLineChartData()
    this.getPieChartData()
    this.getRaddarChartData()
    this.getBarChartData()
  },
  methods: {
    handleSetLineChartData(type) {
      this.lineChartData = this.lineChartDataWrap[type];
    },
    async getLineChartData(){
      let result = await getLineChartList()
      if(result.code==200){
        this.lineChartDataWrap = result.data
        this.handleSetLineChartData('commityNews')

      }else{
        throw new Error('线图数据出错')
      }
    },
    async getPieChartData(){
      let result = await getPieChartList()
      if(result.code==200){
        this.pieChartData = result.data
      }else{
        throw new Error('饼数据出错')
      }
    },
    async getRaddarChartData(){
      let result = await getRaddarChartList()
      if(result.code==200){
        this.raddarChartData = result.data
      }else{
        throw new Error('雷达数据出错')
      }
    },
    async getBarChartData(){
      let result = await getBarChartList()
      if(result.code==200){
        this.barChartData = result.data
      }else{
        throw new Error('条型图数据出错')
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .github-corner {
    position: absolute;
    top: 0px;
    border: 0;
    right: 0;
  }

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width: 1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
