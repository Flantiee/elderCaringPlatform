<template>
  <div class="app-container">
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column type="index" align="center" label="紧急事件ID" width="95">
        <!-- <template slot-scope="scope">
          {{ scope.row.communityNewsID }}
        </template> -->
      </el-table-column>
      <el-table-column label="老人姓名" width="140" align="center" :show-overflow-tooltip='true'>
        <template slot-scope="scope">
          {{ scope.row.elderName }}
        </template>
      </el-table-column>
      <el-table-column label="发起时间" width="200" align="center" :show-overflow-tooltip='true'>
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.startTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" width="200" align="center">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.finalTime  }}</span>
        </template>
      </el-table-column>
      <el-table-column label="事件总结" width="220" align="center" :show-overflow-tooltip='true'>
        <template slot-scope="scope">
          {{ scope.row.summary }}
        </template>
      </el-table-column>
      <el-table-column label="事件状态" class-name="status-col"  align="center" >
        <template slot-scope="{row}">
          <el-tag :type="row.status | statusFilter">
            {{ row.status }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="选项" align="center" width="300" >
        <template slot-scope="{row,$index}">
          <el-button v-if="row.status=='开始处理'" size="mini" type="danger" @click="handleConfirm(row,$index)">
             取消处理 
          </el-button>
          <el-button v-if="row.status=='等待处理'" size="mini" type="success" @click="handleConfirm(row,$index)">
             开始处理
          </el-button>
          <el-button type="primary" size="mini" @click="handleUpdate(row)">
             事件总结 
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm"  :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:60px;">
        <el-form-item label="总结  " prop="content">
          <el-input v-model="temp.summary" :autosize="{ minRows: 4, maxRows:7 }" type="textarea" placeholder="Please input" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
           取消 
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
           确认 
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getEmergencyList, createEmergencyConclude, updateEmergencyStatus } from '@/api/affairs.js'

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        '开始处理': 'success',
        '自行取消': '',
        '等待处理': 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      list: null,
      listLoading: true,
      //当前要修改的行
      temp: {
        emergencyEventID:null,
        status:null,
        summary:'',
        startTime:'',
        finalTime:'',
        elderName:'',
        elderID:null
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '修改',
        create: '新增'
      },
      dialogPvVisible: false,
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {//获取数据
      this.listLoading = true
      getEmergencyList().then(response => {
        this.list = response.data
        this.list = this.list.map((item)=>{ //进行过滤事件
          if(item.status==0) item.status = '等待处理'
          else if (item.status==1) item.status = '自行取消'
          else item.status = '开始处理'
          return item
        })
        this.listLoading = false 
      })
    },
    resetTemp() {//重新设置当前选中行
      this.temp = {
        emergencyEventID:null,
        status:null,
        summary:'',
        startTime:'',
        finalTime:'',
        elderName:'',
        elderID:null
      }
    },
 
    //处理修改
    handleUpdate(row) {
      this.temp = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
    },
    updateData() {   
      createEmergencyConclude(this.temp).then(()=>{
            this.dialogFormVisible = false
            this.fetchData() //重新获取数据
            this.$notify({
              title: '成功',
              message: '总结创建成功',
              type: 'success',
              duration: 2000
            })
          })
  
    },
    //删除目标
    handleConfirm(row,index) {     
      updateEmergencyStatus({emergencyEventsID:row.emergencyEventsID}).then(()=>{
            this.dialogFormVisible = false
            this.fetchData() //重新获取数据
            this.$notify({
              title: '成功',
              message: '状态修改成功',
              type: 'success',
              duration: 2000
            })
          })
      
    },
    //处理完成
    handleModifyStatus(row, status) {
      this.$message({
        message: '操作成功',
        type: 'success'
      })
      row.status = status
    },
  }
}
</script>
