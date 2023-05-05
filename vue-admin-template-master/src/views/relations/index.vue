<template>
    <div class="app-container">
      <el-button class="filter-item" style="margin-left: 10px; margin-bottom: 15px;" type="primary" icon="el-icon-edit" @click="handleCreate">
          添加
        </el-button>
      <el-table
        v-loading="listLoading"
        :data="list"
        element-loading-text="Loading"
        border
        fit
        highlight-current-row
      >
        <el-table-column type="index" align="center" label="relationid" width="95">
          <!-- <template slot-scope="scope">
            {{ scope.row.relationid }}
          </template> -->
        </el-table-column>
        <el-table-column label="老人姓名" width="140" align="center">
          <template slot-scope="scope">
            {{ scope.row.elderName }}
          </template>
        </el-table-column>
        <el-table-column label="老人性别" width="150" align="center">
          <template slot-scope="scope">
            {{ scope.row.elderSex }}
          </template>
        </el-table-column>
        <el-table-column label="老人住址" width="240" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.elderAddress }}</span>
          </template>
        </el-table-column>
        <el-table-column label="子女姓名" width="150" align="center">
          <template slot-scope="scope">
            {{ scope.row.childrenName }}
          </template>
        </el-table-column>
        <el-table-column label="子女电话" width="200" align="center">
          <template slot-scope="scope">
            {{ scope.row.childrenPhone }}
          </template>
        </el-table-column>
        <el-table-column label="选项" align="center" >
          <template slot-scope="{row,$index}">
            <el-button type="primary" size="mini" @click="handleUpdate(row)">
               修改 
            </el-button>
            <el-button v-if="row.status!='deleted'" size="mini" type="danger" @click="handleDelete(row,$index)">
               删除 
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 对话框 -->
      <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
        <el-form ref="dataForm"  :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
          <el-form-item label="目标老人 "  prop="elder">
            <el-select v-model="temp.elderId" filterable class="filter-item" placeholder="" >
             <el-option v-for="item in elders" :key="item.elderId" :label="item.name" :value="item.elderId" />
            </el-select>
          </el-form-item>
          <el-form-item label="目标子女" prop="child">
            <el-select v-model="temp.childrenId" filterable class="filter-item" placeholder="" >
              <el-option v-for="item in children" :key="item.childrenId" :label="item.name" :value="item.childrenId"  />
            </el-select>
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
   import { getRelationsList,getAvailableList, updateRelation, deleteRelation, createRelation } from '@/api/table.js'
  
  export default {
    filters: {
      statusFilter(status) {
        const statusMap = {
          published: 'success',
          draft: 'gray',
          deleted: 'danger'
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
            elderId:null,
            elderName:null,
            childrenId:null,
            childrenName:null
        },
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: '修改',
          create: '新增'
        },
        dialogPvVisible: false,
        elders:[ //找后端要
        ],
        children:[ //找后端要
        ],
      }
    },
    created() {
      this.fetchData()
    },
    methods: {
      fetchData() {//获取数据
        this.listLoading = true
        getRelationsList().then(response => {
          this.list = response.data
            getAvailableList().then(response=>{
            this.elders = response.data.elders
            this.children = response.data.children
            this.listLoading = false
          })
        })
      },
      resetTemp() {//重新设置当前选中行
        this.temp = {
            elderId:null,
            elderName:null,
            childrenId:null,
            childrenName:null
        }
      },
      //增添目标
      handleCreate(){
        this.resetTemp()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
      },
      createData() {
            // this.listLoading = true
            createRelation(this.temp).then(()=>{
            this.dialogFormVisible = false
            this.fetchData() //重新获取数据
            this.$notify({
              title: '成功',
              message: '创建成功',
              type: 'success',
              duration: 2000
            })
          })         
     
      },
      //处理修改
      handleUpdate(row) {

        this.temp.relationId = row.relationID
        this.temp.elderId = row.elderID 
        this.temp.childrenId = row.childrenID

        this.dialogStatus = 'update'
        this.dialogFormVisible = true
      },
      updateData() {    
                  
            //1. 发送请求添加(temp)
            //2. 并且再次发请求找后端要总表格数据
            updateRelation(this.temp).then(()=>{
            this.dialogFormVisible = false
            this.fetchData() //重新获取数据
            this.$notify({
              title: '成功',
              message: '创建成功',
              type: 'success',
              duration: 2000
            })
          })     
      },
      //删除目标
      handleDelete(row,index) {
            //发请求删除(childrenId)
            let relationId = {relationId:row.relationID}
            deleteRelation(relationId).then(()=>{
            this.dialogFormVisible = false
            this.fetchData() //重新获取数据
            this.$notify({
              title: '成功',
              message: '创建成功',
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
  