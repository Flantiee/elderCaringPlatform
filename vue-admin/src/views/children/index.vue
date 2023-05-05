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
      <el-table-column type="index" align="center" label="ID" width="95">
        <!-- <template slot-scope="scope">
          {{ scope.row.childrenID }}
        </template> -->
      </el-table-column>
      <el-table-column label="姓名" width="140" align="center">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="性别" width="140" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.sex }}</span>
        </template>
      </el-table-column>
      <el-table-column label="电话" width="150" align="center">
        <template slot-scope="scope">
          {{ scope.row.phone }}
        </template>
      </el-table-column>
      <el-table-column label="密码" width="150" align="center">
        <template slot-scope="scope">
          {{ scope.row.password }}
        </template>
      </el-table-column>
      <el-table-column label="地址" width="200" align="center">
        <template slot-scope="scope">
          {{ scope.row.address }}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="200" align="center">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.createTime  }}</span>
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
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="名字 " prop="name">
          <el-input v-model="temp.name" />
        </el-form-item>
        <el-form-item label="性别  " prop="sex">
          <el-select v-model="temp.sex" class="filter-item" placeholder="" >
            <el-option v-for="item in sexOptions" :key="item" :label="item" :value="item" />
          </el-select>
        </el-form-item>
        <el-form-item label="电话  " prop="phone">
          <el-input v-model="temp.phone" />
        </el-form-item>
        <el-form-item label="密码  " prop="password">
          <el-input v-model="temp.password" />
        </el-form-item>
        <el-form-item label="地址  " prop="address">
          <el-input v-model="temp.address" />
        </el-form-item>
        <el-form-item label="创建时间" prop="moveInTime">
          <el-date-picker v-model="temp.createTime" type="date"   value-format="yyyy-MM-dd" />
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
 import { getChildrenList, createChildren, updateChildren, deleteChildren } from '@/api/table.js'

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
        childrenID: undefined,
        name: '',
        sex: '',
        password:'',
        phone: "",
        address: "",
        createTime: '',//不写
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '修改',
        create: '新增'
      },
      dialogPvVisible: false,
      sexOptions: ['男', '女'], //性别选择
      rules: {
        name: [
          { required: true, message: '请填写名字', trigger: 'blur' },
          { min: 2, max: 8, message: '长度在 2 到 8 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请填写密码', trigger: 'blur' },
          { min: 4, max: 12, message: '长度在 4 到 12 个字符', trigger: 'blur' }
        ],
        sex:[
          { required: true, message: '请填写性别', trigger: 'blur' },
        ],
        address:[
          { required: true, message: '请填写地址', trigger: 'blur' },
        ],
        phone: [
          { required: true, message: '请填写电话', trigger: 'blur' },
          { min: 4, max: 11, message: '长度在 4 到 11 个字符', trigger: 'blur' }
      ]
      },
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {//获取数据
      this.listLoading = true
      getChildrenList().then(response => {
        this.list = response.data
        this.listLoading = false
      })
    },
    resetTemp() {//重新设置当前选中行
      this.temp = {
        childrenID: undefined,
        password:'',
        name: '',
        sex: '',
        phone: "",
        address: "",
        createTime: '',//不写
      }
    },
    //增添目标
    handleCreate(){
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if(valid){               
          //发送请求添加(temp)
          createChildren(this.temp).then(()=>{
            this.dialogFormVisible = false
            this.fetchData() //重新获取数据
            this.$notify({
              title: '成功',
              message: '创建成功',
              type: 'success',
              duration: 2000
            })
          })
      }}
      )
   
    },
    //处理修改
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if(valid){       
          updateChildren(this.temp).then(()=>{
            this.dialogFormVisible = false
            this.fetchData() //重新获取数据
            this.$notify({
              title: '成功',
              message: '创建成功',
              type: 'success',
              duration: 2000
            })
          })
          }   
      })
    },
    //删除目标
    handleDelete(row,index) {

      //发请求删除(childrenId)
      let childrenId = {childrenId:row.childrenId}
      deleteChildren(childrenId).then(()=>{
            this.dialogFormVisible = false
            this.fetchData() //重新获取数据
          this.$notify({
          title: '成功',
          message: '删除成功',
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
