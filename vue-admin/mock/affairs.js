const Mock = require('mockjs')

const newsData = [
    {
        communityNewsID:3,
        title:'幸福小区:组建社区老年艺术团',
        content:'社区于2022年10月6日在社区活动中心选拔老年艺术团成员，请有兴趣的老人前来参加，镇南社区文化艺术队副团长王晓红表示艺术团的成员们经常聚在一起排练、表演节目，现在有了社区的大力支持，成立了艺术团，大家的热情更加高涨，纷纷表示今后不仅会为社区群众提供丰富多彩的文艺节目，也会参加肥东县乃至全市、全省的文艺表演和比赛。',        addTime:'2022-12-01',
        addTime:'2022-12-01',
        img:'nature2.png'
    },
    {
        communityNewsID:6,
        title:'幸福小区：发放核酸自测盒',
        content:'街道资产办认真谋划、积极对接，拓展畅通物资保障渠道。目前周边医疗物资市场货源充足，综合考虑新增核酸检测点、隔离点需求和各社区、各部门需求变化，合理把握采购节奏，分时段、定短单，及时补充库存。',
        addTime:'2022-10-21',
        img:'nature3.png'
    },
]

const emergencyData = [
    {
        emergencyEventID:1,
        status:2,
        summary:'社区工作人员及时处理，老人最终脱困',
        startTime:'2023-01-13 23:22:51',
        finalTime:'2023-02-09 16:07:45',
        elderName:'王奶奶',
        elderID:1
    },
    {
        emergencyEventID:1624689724009078786,
        status:1,
        summary:'',
        startTime:'2023-01-12 16:39:50',
        finalTime:'2023-01-12 16:45:36',
        elderName:'王奶奶',
        elderID:1
    },
    {
      emergencyEventID:1624689724009078786,
      status:0,
      summary:'',
      startTime:'2023-02-11 16:39:50',
      finalTime:'2023-02-11 16:45:36',
      elderName:'王奶奶',
      elderID:1
  },
]

module.exports = [
  {
    url: '/api/affairs/news',
    type: 'get',
    response: config => {
      const items = newsData
      return {
        code: 200,
        data: {
          total: items.length,
          items: items
        }
      }
    }
  },
  {
    url: '/api/affairs/emergency',
    type: 'get',
    response: config => {
      const items = emergencyData
      return {
        code: 200,
        data: {
          total: items.length,
          items: items
        }
      }
    }
  },
  {
    url: '/api/table/relationslist',
    type: 'get',
    response: config => {
      const items = relationsData
      return {
        code: 200,
        data: {
          total: items.length,
          items: items,
        }
      }
    }
  },
]
