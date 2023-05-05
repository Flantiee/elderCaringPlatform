<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'

const animationDuration = 2000

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '300px'
    },
    chartData: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      chart: null,
    }
  },
  watch: {
    chartData: {
      deep: true,
      handler(val) {
        this.setOptions(val)
      }
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')
      this.setOptions(this.chartData)
    },
    setOptions({ familyDoc, houseClean, elderService, mentalHealth } = {}) {
      this.chart.setOption({
        tooltip: {
          trigger: 'axis',
          axisPointer: { // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          top: 10,
          left: '2%',
          right: '2%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [{
          type: 'category',
          data: ['周一', '周二', '周三', '周四', '周五', '周六', '周天'],
          axisTick: {
            alignWithLabel: true
          }
        }],
        yAxis: [{
          name:'件数',
          type: 'value',
          axisTick: {
            show: false
          }
        }],
        series: [{
          name: '家庭医生',
          type: 'bar',
          stack: 'vistors',
          barWidth: '60%',
          data: familyDoc,//这些数据后端传
          animationDuration
        }, {
          name: '保洁卫生',
          type: 'bar',
          stack: 'vistors',
          barWidth: '60%',
          data: houseClean,
          animationDuration
        }, {
          name: '助老服务',
          type: 'bar',
          stack: 'vistors',
          barWidth: '60%',
          data: elderService,
          animationDuration
        },
        {
          name: '心理建设',
          type: 'bar',
          stack: 'vistors',
          barWidth: '60%',
          data: mentalHealth,
          animationDuration
        }]
      })
    }
  }
}
</script>
