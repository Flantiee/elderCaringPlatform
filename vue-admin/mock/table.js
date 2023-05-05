const Mock = require('mockjs')

const elderData = [
  {
    elderID: 1,
    name: "王奶奶",
    sex: "女",
    phone: "123456789",
    address: "幸福小区5栋1单元305",
    moveInTime: "2022-11-12",
    hobby: "跳舞",
  },
  {
    elderID: 2,
    name: "李爷爷",
    sex: "男",
    phone: "12345678",
    address: "花园小区7栋3单元603",
    moveInTime: "2022-10-18",
    hobby: "打麻将",
  },
  {
    elderID: 3,
    name: "陈奶奶",
    sex: "女",
    phone: "1234567",
    address: "花园小区4栋6单元102",
    moveInTime: "2022-07-18",
    hobby: "跳舞",
  },
  {
    elderID: 4,
    name: "熊爷爷",
    sex: "男",
    phone: "987654321",
    address: "花园小区1栋3单元202",
    moveInTime: "2022-09-22",
    hobby: "打麻将",
  },
  {
    elderID: 5,
    name: "杨奶奶",
    sex: "女",
    phone: "98765432",
    address: "幸福小区3栋4单元201",
    moveInTime: "2022-08-12",
    hobby: "跳舞",
  },
  {
    elderID: 6,
    name: "李奶奶",
    sex: "女",
    phone: "123456",
    address: "幸福小区5栋2单元101",
    moveInTime: "2022-07-16",
    hobby: "打麻将",
  }
]

const childrenData = [
  {
    childrenID:1,
    name:'张三',
    sex:'女',//后端自行生成
    phone:'123456789',
    address:'成都市新都区康源大道356号',
    createTime:'2022-12-15'
  },
  {
    childrenID:2,
    name:'王五',
    sex:'男',
    phone:'1234567891',
    address:'成都市新都区桂湖小区',
    createTime:'2022-12-14'
  }
]

const relationsData = [
  {
    relationID:1,
    elderID:1,
    elderName:'王奶奶',
    elderSex:"女",
    elderAddress:'幸福小区5栋1单元305',
    childrenID:2,
    childrenName:'王五',
    childrenPhone:'1234567891'
  },
  {
    relationID:2,
    elderID:3,
    elderName:'陈奶奶',
    elderSex:'女',
    elderAddress:'花园小区4栋6单元1025',
    childrenID:1,
    childrenName:'张三',
    childrenPhone:'123456789'
  },
]

module.exports = [
  {
    url: '/api/table/elder',
    type: 'get',
    response: config => {
      const items = elderData
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
    url: '/api/table/children',
    type: 'get',
    response: config => {
      const items = childrenData
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
    url: '/api/table/relations',
    type: 'get',
    response: config => {
      const items = relationsData
      return {
        code: 200,
        data: {
          total: items.length,
          items: items
        }
      }
    }
  },
]
