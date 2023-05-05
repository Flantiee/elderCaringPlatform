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
      <el-table-column type="index" align="center" label="NewsID" width="95">
        <!-- <template slot-scope="scope">
          {{ scope.row.communityNewsID }}
        </template> -->
      </el-table-column>
      <el-table-column label="新闻标题" width="250" align="center" :show-overflow-tooltip='true'>
        <template slot-scope="scope">
          {{ scope.row.title }}
        </template>
      </el-table-column>
      <el-table-column label="新闻内容" width="300" align="center" :show-overflow-tooltip='true'>
        <template slot-scope="scope">
          <span>{{ scope.row.content }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发布时间" width="170" align="center">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.addTime  }}</span>
        </template>
      </el-table-column>
      <el-table-column label="新闻图片" width="170" align="center">
        <template slot-scope="scope">
          {{ scope.row.img }}
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
        <el-form-item label="标题 " prop="title">
          <el-input v-model="temp.title" />
        </el-form-item>
        <el-form-item label="图片" prop="img">
          <el-select v-model="temp.img" class="filter-item" placeholder="">
            <el-option v-for="item in imgOptions" :key="item" :label="item" :value="item" />
          </el-select>
        </el-form-item>
        <el-form-item label="时间" prop="addTime">
          <el-date-picker v-model="temp.addTime" type="date"  placeholder="" value-format="yyyy-MM-dd" />
        </el-form-item>
        <el-form-item label="内容  " prop="content">
          <el-input v-model="temp.content" :autosize="{ minRows: 2, maxRows:7 }" type="textarea" placeholder="" />
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
import { getNewsList, createNews, updateNews, deleteNews } from '@/api/affairs.js'

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
        communityNewsID: undefined,
        title: '',
        content: '',
        addTime: "",
        img: "",
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '修改',
        create: '新增'
      },
      dialogPvVisible: false,
      imgOptions: ['nature2.img', 'nature3.img','nature4.img','nature5.img'], //图片选择
      rules: {
        title: [
          { required: true, message: '请填写标题', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
        ],
        img:[
          { required: true, message: '选择图片', trigger: 'blur' },
        ],
        content:[
          { required: true, message: '请填写新闻内容', trigger: 'blur' },
        ],
        addTime:[
          { required: true, message: '请选择添加时间', trigger: 'blur' },
        ],
      },
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {//获取数据
      this.listLoading = true
      getNewsList().then(response => {
        this.list = response.data
        this.listLoading = false
      })
    },
    resetTemp() {//重新设置当前选中行
      this.temp = {
        communityNewsID: undefined,
        title: '',
        content: '',
        addTime: "",
        img: "",
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
            createNews(this.temp).then(()=>{
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
          updateNews(this.temp).then(()=>{
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
        //发请求删除(communityNewsId)
      let communityNewsID = {communityNewsID:row.communityNewsID}
      deleteNews(communityNewsID).then(()=>{
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
