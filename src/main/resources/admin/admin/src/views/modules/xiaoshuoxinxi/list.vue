<template>
  <div class="main-content">
    <!-- 列表页 -->
    <div v-if="showFlag">
      <el-form :inline="true" :model="searchForm" class="form-content">
        <el-row  :gutter="20" class="slt" :style="{justifyContent:contents.searchBoxPosition=='1'?'flex-start':contents.searchBoxPosition=='2'?'center':'flex-end'}">
                <el-form-item :label="contents.inputTitle == 1 ? '小说名称' : ''">
                  <el-input v-if="contents.inputIcon == 1 && contents.inputIconPosition == 1" prefix-icon="el-icon-search" v-model="searchForm.xiaoshuomingcheng" placeholder="小说名称" clearable></el-input>
                  <el-input v-if="contents.inputIcon == 1 && contents.inputIconPosition == 2" suffix-icon="el-icon-search" v-model="searchForm.xiaoshuomingcheng" placeholder="小说名称" clearable></el-input>
                  <el-input v-if="contents.inputIcon == 0" v-model="searchForm.xiaoshuomingcheng" placeholder="小说名称" clearable></el-input>
                </el-form-item>
		<el-form-item class="select" label="小说分类" prop="xiaoshuofenlei">
		  <el-select  @change="xiaoshuofenleiChange" clearable v-model="searchForm.xiaoshuofenlei" placeholder="请选择小说分类">
		    <el-option
			v-for="(item,index) in xiaoshuofenleiOptions"
			v-bind:key="index"
			:label="item"
			:value="item">
		    </el-option>
		  </el-select>
		</el-form-item>
          <el-form-item>
            <el-button v-if="contents.searchBtnIcon == 1 && contents.searchBtnIconPosition == 1" icon="el-icon-search" type="success" @click="search()">{{ contents.searchBtnFont == 1?'查询':'' }}</el-button>
            <el-button v-if="contents.searchBtnIcon == 1 && contents.searchBtnIconPosition == 2" type="success" @click="search()">{{ contents.searchBtnFont == 1?'查询':'' }}<i class="el-icon-search el-icon--right"/></el-button>
            <el-button v-if="contents.searchBtnIcon == 0" type="success" @click="search()">{{ contents.searchBtnFont == 1?'查询':'' }}</el-button>
          </el-form-item>
        </el-row>

        <el-row class="ad" :style="{justifyContent:contents.btnAdAllBoxPosition=='1'?'flex-start':contents.btnAdAllBoxPosition=='2'?'center':'flex-end'}">
          <el-form-item>
            <el-button
              v-if="isAuth('xiaoshuoxinxi','新增') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 1"
              type="success"
              icon="el-icon-plus"
              @click="addOrUpdateHandler()"
            >{{ contents.btnAdAllFont == 1?'新增':'' }}</el-button>
            <el-button
              v-if="isAuth('xiaoshuoxinxi','新增') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 2"
              type="success"
              @click="addOrUpdateHandler()"
            >{{ contents.btnAdAllFont == 1?'新增':'' }}<i class="el-icon-plus el-icon--right" /></el-button>
            <el-button
              v-if="isAuth('xiaoshuoxinxi','新增') && contents.btnAdAllIcon == 0"
              type="success"
              @click="addOrUpdateHandler()"
            >{{ contents.btnAdAllFont == 1?'新增':'' }}</el-button>
            <el-button
              v-if="isAuth('xiaoshuoxinxi','删除') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 1 && contents.tableSelection"
              :disabled="dataListSelections.length <= 0"
              type="danger"
              icon="el-icon-delete"
              @click="deleteHandler()"
            >{{ contents.btnAdAllFont == 1?'删除':'' }}</el-button>
            <el-button
              v-if="isAuth('xiaoshuoxinxi','删除') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 2 && contents.tableSelection"
              :disabled="dataListSelections.length <= 0"
              type="danger"
              @click="deleteHandler()"
            >{{ contents.btnAdAllFont == 1?'删除':'' }}<i class="el-icon-delete el-icon--right" /></el-button>
            <el-button
              v-if="isAuth('xiaoshuoxinxi','删除') && contents.btnAdAllIcon == 0 && contents.tableSelection"
              :disabled="dataListSelections.length <= 0"
              type="danger"
              @click="deleteHandler()"
            >{{ contents.btnAdAllFont == 1?'删除':'' }}</el-button>





          </el-form-item>
        </el-row>
      </el-form>
      <div class="table-content">
        <el-table class="tables" :size="contents.tableSize" :show-header="contents.tableShowHeader"
            :header-row-style="headerRowStyle" :header-cell-style="headerCellStyle"
            :border="contents.tableBorder"
            :fit="contents.tableFit"
            :stripe="contents.tableStripe"
            :style="{width: '100%',fontSize:contents.tableContentFontSize,color:contents.tableContentFontColor}"
            v-if="isAuth('xiaoshuoxinxi','查看')"
            :data="dataList"
            v-loading="dataListLoading"
            @selection-change="selectionChangeHandler">
            <el-table-column  v-if="contents.tableSelection"
                type="selection"
                :header-align="contents.tableAlign"
                align="center"
                width="50">
            </el-table-column>
            <el-table-column label="索引" :align="contents.tableAlign"  v-if="contents.tableIndex" type="index" width="50" />
                <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign" 
                    prop="xiaoshuobianhao"
                   :header-align="contents.tableAlign"
		    label="小说编号">
		     <template slot-scope="scope">
                       {{scope.row.xiaoshuobianhao}}
                     </template>
                </el-table-column>
                <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign" 
                    prop="xiaoshuomingcheng"
                   :header-align="contents.tableAlign"
		    label="小说名称">
		     <template slot-scope="scope">
                       {{scope.row.xiaoshuomingcheng}}
                     </template>
                </el-table-column>
                <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign" 
                    prop="xiaoshuofenlei"
                   :header-align="contents.tableAlign"
		    label="小说分类">
		     <template slot-scope="scope">
                       {{scope.row.xiaoshuofenlei}}
                     </template>
                </el-table-column>
                  <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign"  prop="xiaoshuofengmian"
                   :header-align="contents.tableAlign"
                    width="200"
                    label="小说封面">
                    <template slot-scope="scope">
                      <div v-if="scope.row.xiaoshuofengmian">
                        <img :src="$base.url+scope.row.xiaoshuofengmian.split(',')[0]" width="100" height="100">
                      </div>
                      <div v-else>无图片</div>
                    </template>
                  </el-table-column>
                <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign" 
                    prop="xiaoshuozhangjie"
                   :header-align="contents.tableAlign"
		    label="小说章节">
		     <template slot-scope="scope">
                       {{scope.row.xiaoshuozhangjie}}
                     </template>
                </el-table-column>
                <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign" 
                    prop="xiaoshuolianjie"
                   :header-align="contents.tableAlign"
		    label="小说链接">
		     <template slot-scope="scope">
                       <span @click="linkOthers(scope.row.xiaoshuolianjie)">{{scope.row.xiaoshuolianjie}}</span>
                     </template>
                </el-table-column>
                  <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign"  prop="xiaoshuoneirong"
                   :header-align="contents.tableAlign"
                    label="小说内容">
                    <template slot-scope="scope">
                      <el-button type="text" size="small" @click="download($base.url+scope.row.xiaoshuoneirong)">下载</el-button>
                    </template>
                  </el-table-column>
                <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign" 
                    prop="pingfen"
                   :header-align="contents.tableAlign"
		    label="评分">
		     <template slot-scope="scope">
                       {{scope.row.pingfen}}
                     </template>
                </el-table-column>
            <el-table-column width="300" :align="contents.tableAlign" 
               :header-align="contents.tableAlign"
                label="操作">
                <template slot-scope="scope">
                <el-button v-if="isAuth('xiaoshuoxinxi','查看') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 1" type="success" icon="el-icon-tickets" size="mini" @click="addOrUpdateHandler(scope.row.id,'info')">{{ contents.tableBtnFont == 1?'详情':'' }}</el-button>
                <el-button v-if="isAuth('xiaoshuoxinxi','查看') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 2" type="success" size="mini" @click="addOrUpdateHandler(scope.row.id,'info')">{{ contents.tableBtnFont == 1?'详情':'' }}<i class="el-icon-tickets el-icon--right" /></el-button>
                <el-button v-if="isAuth('xiaoshuoxinxi','查看') && contents.tableBtnIcon == 0" type="success" size="mini" @click="addOrUpdateHandler(scope.row.id,'info')">{{ contents.tableBtnFont == 1?'详情':'' }}</el-button>
                <el-button v-if="isAuth('xiaoshuoxinxi','评分') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 1" type="success" icon="el-icon-tickets" size="mini" @click="xiaoshuopingfenCrossAddOrUpdateHandler(scope.row,'cross','','[1]','已评分')">{{ contents.tableBtnFont == 1?'评分':'' }}</el-button>
                <el-button v-if="isAuth('xiaoshuoxinxi','评分') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 2" type="success" size="mini" @click="xiaoshuopingfenCrossAddOrUpdateHandler(scope.row,'cross','','[1]','已评分')">{{ contents.tableBtnFont == 1?'评分':'' }}<i class="el-icon-tickets el-icon--right" /></el-button>
                <el-button v-if="isAuth('xiaoshuoxinxi','评分') && contents.tableBtnIcon == 0" type="success" size="mini" @click="xiaoshuopingfenCrossAddOrUpdateHandler(scope.row,'cross','','[1]','已评分')">{{ contents.tableBtnFont == 1?'评分':'' }}</el-button>
                <el-button v-if=" isAuth('xiaoshuoxinxi','修改') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 1" type="primary" icon="el-icon-edit" size="mini" @click="addOrUpdateHandler(scope.row.id)">{{ contents.tableBtnFont == 1?'修改':'' }}</el-button>
                <el-button v-if=" isAuth('xiaoshuoxinxi','修改') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 2" type="primary" size="mini" @click="addOrUpdateHandler(scope.row.id)">{{ contents.tableBtnFont == 1?'修改':'' }}<i class="el-icon-edit el-icon--right" /></el-button>
                <el-button v-if=" isAuth('xiaoshuoxinxi','修改') && contents.tableBtnIcon == 0" type="primary" size="mini" @click="addOrUpdateHandler(scope.row.id)">{{ contents.tableBtnFont == 1?'修改':'' }}</el-button>


                <el-button v-if="isAuth('xiaoshuoxinxi','查看评论') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 1" type="primary" icon="el-icon-edit" size="mini" @click="disscussListHandler(scope.row.id)">{{ contents.tableBtnFont == 1?'查看评论':'' }}</el-button>
                <el-button v-if="isAuth('xiaoshuoxinxi','查看评论') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 2" type="primary" size="mini" @click="disscussListHandler(scope.row.id)">{{ contents.tableBtnFont == 1?'查看评论':'' }}<i class="el-icon-edit el-icon--right" /></el-button>
                <el-button v-if="isAuth('xiaoshuoxinxi','查看评论') && contents.tableBtnIcon == 0" type="primary" size="mini" @click="disscussListHandler(scope.row.id)">{{ contents.tableBtnFont == 1?'查看评论':'' }}</el-button>


                <el-button v-if="isAuth('xiaoshuoxinxi','删除') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 1" type="danger" icon="el-icon-delete" size="mini" @click="deleteHandler(scope.row.id)">{{ contents.tableBtnFont == 1?'删除':'' }}</el-button>
                <el-button v-if="isAuth('xiaoshuoxinxi','删除') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 2" type="danger" size="mini" @click="deleteHandler(scope.row.id)">{{ contents.tableBtnFont == 1?'删除':'' }}<i class="el-icon-delete el-icon--right" /></el-button>
                <el-button v-if="isAuth('xiaoshuoxinxi','删除') && contents.tableBtnIcon == 0" type="danger" size="mini" @click="deleteHandler(scope.row.id)">{{ contents.tableBtnFont == 1?'删除':'' }}</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
          clsss="pages"
          :layout="layouts"
          @size-change="sizeChangeHandle"
          @current-change="currentChangeHandle"
          :current-page="pageIndex"
          :page-sizes="[10, 20, 50, 100]"
          :page-size="Number(contents.pageEachNum)"
          :total="totalPage"
          :small="contents.pageStyle"
          class="pagination-content"
          :background="contents.pageBtnBG"
          :style="{textAlign:contents.pagePosition==1?'left':contents.pagePosition==2?'center':'right'}"
        ></el-pagination>
      </div>
    </div>
    <!-- 添加/修改页面  将父组件的search方法传递给子组件-->
    <add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>

    <xiaoshuopingfen-cross-add-or-update v-if="xiaoshuopingfenCrossAddOrUpdateFlag" :parent="this" ref="xiaoshuopingfenCrossaddOrUpdate"></xiaoshuopingfen-cross-add-or-update>




  </div>
</template>
<script>
import axios from 'axios'
import AddOrUpdate from "./add-or-update";
import xiaoshuopingfenCrossAddOrUpdate from "../xiaoshuopingfen/add-or-update";
export default {
  data() {
    return {
      xiaoshuofenleiOptions: [],
      searchForm: {
        key: ""
      },
      form:{},
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      showFlag: true,
      sfshVisiable: false,
      shForm: {},
      chartVisiable: false,
      addOrUpdateFlag:false,
      xiaoshuopingfenCrossAddOrUpdateFlag: false,
      contents:{"searchBtnFontColor":"#333","pagePosition":"2","inputFontSize":"14px","inputBorderRadius":"40px","tableBtnDelFontColor":"#333","tableBtnIconPosition":"1","searchBtnHeight":"40px","tableBgColor":"rgba(245, 245, 245, 0)","inputIconColor":"rgba(255, 255, 255, 1)","searchBtnBorderRadius":"40px","tableStripe":false,"btnAdAllWarnFontColor":"rgba(111, 111, 151, 1)","tableBtnDelBgColor":"#fff","searchBtnIcon":"0","tableSize":"medium","searchBtnBorderStyle":"solid","text":{"padding":"20px 100px","boxShadow":"0 0 0px rgba(0,0,0,.1)","margin":"13% auto 0","borderColor":"rgba(0,0,0,.3)","backgroundColor":"rgba(255, 255, 255, 1)","color":"rgba(111, 111, 151, 1)","borderRadius":"40px","borderWidth":"0","width":"auto","lineHeight":"auto","fontSize":"50px","borderStyle":"solid"},"tableSelection":true,"searchBtnBorderWidth":"3px","tableContentFontSize":"14px","searchBtnBgColor":"#fff","inputTitleSize":"14px","btnAdAllBorderColor":"rgba(111, 111, 151, 1)","pageJumper":true,"btnAdAllIconPosition":"1","searchBoxPosition":"1","tableBtnDetailFontColor":"#333","tableBtnHeight":"40px","pagePager":true,"searchBtnBorderColor":"rgba(111, 111, 151, 1)","tableHeaderFontColor":"rgba(255, 255, 255, 1)","inputTitle":"1","tableBtnBorderRadius":"40px","btnAdAllFont":"1","btnAdAllDelFontColor":"rgba(111, 111, 151, 1)","tableBtnIcon":"1","btnAdAllHeight":"40px","btnAdAllWarnBgColor":"#fff","btnAdAllBorderWidth":"3px","tableStripeFontColor":"#606266","tableBtnBorderStyle":"solid","inputHeight":"40px","btnAdAllBorderRadius":"40px","btnAdAllDelBgColor":"#fff","pagePrevNext":true,"btnAdAllAddBgColor":"#fff","searchBtnFont":"1","tableIndex":true,"btnAdAllIcon":"1","tableSortable":true,"pageSizes":true,"tableFit":true,"pageBtnBG":false,"searchBtnFontSize":"14px","tableBtnEditBgColor":"#fff","box":{"padding":"10px 20px","boxShadow":"0 0 6px rgba(0,0,0,0)","flag":"1","backgroundImage":"http://codegen.caihongy.cn/20220121/5bd1186d18ef4a289229043ae4e85ded.png","background":"#fff"},"inputBorderWidth":"1px","inputFontPosition":"2","inputFontColor":"rgba(255, 255, 255, 1)","pageEachNum":10,"tableHeaderBgColor":"rgba(111, 111, 151, 1)","inputTitleColor":"rgba(111, 111, 151, 1)","btnAdAllBoxPosition":"1","tableBtnDetailBgColor":"#fff","inputIcon":"1","searchBtnIconPosition":"1","btnAdAllFontSize":"14px","inputBorderStyle":"solid","tableHoverFontColor":"#333","inputBgColor":"rgba(111, 111, 151, 1)","pageStyle":true,"pageTotal":true,"btnAdAllAddFontColor":"rgba(111, 111, 151, 1)","tableBtnFont":"1","tableContentFontColor":"#606266","inputBorderColor":"#DCDFE6","tableShowHeader":true,"tableHoverBgColor":"rgba(111, 111, 151, 0.33)","tableBtnFontSize":"14px","tableBtnBorderColor":"rgba(111, 111, 151, 1)","inputIconPosition":"2","tableBorder":true,"btnAdAllBorderStyle":"solid","tableBtnBorderWidth":"3px","tableStripeBgColor":"#F5F7FA","tableBtnEditFontColor":"#333","tableAlign":"center"},
      layouts: '',


    };
  },
  created() {
    this.init();
    this.getDataList();
    this.contentStyleChange()
  },
  mounted() {

  },
  filters: {
    htmlfilter: function (val) {
      return val.replace(/<[^>]*>/g).replace(/undefined/g,'');
    }
  },
  components: {
    AddOrUpdate,
    xiaoshuopingfenCrossAddOrUpdate,
  },
  methods: {
    linkOthers(url) {
        window.open(url);//打开一个新的标签页
    },

    contentStyleChange() {
      this.contentSearchStyleChange()
      this.contentBtnAdAllStyleChange()
      this.contentSearchBtnStyleChange()
      this.contentTableBtnStyleChange()
      this.contentPageStyleChange()
    },
    contentSearchStyleChange() {
      this.$nextTick(()=>{
        document.querySelectorAll('.form-content .slt .el-input__inner').forEach(el=>{
          let textAlign = 'left'
          if(this.contents.inputFontPosition == 2) textAlign = 'center'
          if(this.contents.inputFontPosition == 3) textAlign = 'right'
          el.style.textAlign = textAlign
          el.style.height = this.contents.inputHeight
          el.style.lineHeight = this.contents.inputHeight
          el.style.color = this.contents.inputFontColor
          el.style.fontSize = this.contents.inputFontSize
          el.style.borderWidth = this.contents.inputBorderWidth
          el.style.borderStyle = this.contents.inputBorderStyle
          el.style.borderColor = this.contents.inputBorderColor
          el.style.borderRadius = this.contents.inputBorderRadius
          el.style.backgroundColor = this.contents.inputBgColor
        })
        if(this.contents.inputTitle) {
          document.querySelectorAll('.form-content .slt .el-form-item__label').forEach(el=>{
            el.style.color = this.contents.inputTitleColor
            el.style.fontSize = this.contents.inputTitleSize
            el.style.lineHeight = this.contents.inputHeight
          })
        }
        setTimeout(()=>{
          document.querySelectorAll('.form-content .slt .el-input__prefix').forEach(el=>{
            el.style.color = this.contents.inputIconColor
            el.style.lineHeight = this.contents.inputHeight
          })
          document.querySelectorAll('.form-content .slt .el-input__suffix').forEach(el=>{
            el.style.color = this.contents.inputIconColor
            el.style.lineHeight = this.contents.inputHeight
          })
          document.querySelectorAll('.form-content .slt .el-input__icon').forEach(el=>{
            el.style.lineHeight = this.contents.inputHeight
          })
        },10)

      })
    },
    // 搜索按钮
    contentSearchBtnStyleChange() {
      this.$nextTick(()=>{
        document.querySelectorAll('.form-content .slt .el-button--success').forEach(el=>{
          el.style.height = this.contents.searchBtnHeight
          el.style.color = this.contents.searchBtnFontColor
          el.style.fontSize = this.contents.searchBtnFontSize
          el.style.borderWidth = this.contents.searchBtnBorderWidth
          el.style.borderStyle = this.contents.searchBtnBorderStyle
          el.style.borderColor = this.contents.searchBtnBorderColor
          el.style.borderRadius = this.contents.searchBtnBorderRadius
          el.style.backgroundColor = this.contents.searchBtnBgColor
        })
      })
    },
    // 新增、批量删除
    contentBtnAdAllStyleChange() {
      this.$nextTick(()=>{
        document.querySelectorAll('.form-content .ad .el-button--success').forEach(el=>{
          el.style.height = this.contents.btnAdAllHeight
          el.style.color = this.contents.btnAdAllAddFontColor
          el.style.fontSize = this.contents.btnAdAllFontSize
          el.style.borderWidth = this.contents.btnAdAllBorderWidth
          el.style.borderStyle = this.contents.btnAdAllBorderStyle
          el.style.borderColor = this.contents.btnAdAllBorderColor
          el.style.borderRadius = this.contents.btnAdAllBorderRadius
          el.style.backgroundColor = this.contents.btnAdAllAddBgColor
        })
        document.querySelectorAll('.form-content .ad .el-button--danger').forEach(el=>{
          el.style.height = this.contents.btnAdAllHeight
          el.style.color = this.contents.btnAdAllDelFontColor
          el.style.fontSize = this.contents.btnAdAllFontSize
          el.style.borderWidth = this.contents.btnAdAllBorderWidth
          el.style.borderStyle = this.contents.btnAdAllBorderStyle
          el.style.borderColor = this.contents.btnAdAllBorderColor
          el.style.borderRadius = this.contents.btnAdAllBorderRadius
          el.style.backgroundColor = this.contents.btnAdAllDelBgColor
        })
        document.querySelectorAll('.form-content .ad .el-button--warning').forEach(el=>{
          el.style.height = this.contents.btnAdAllHeight
          el.style.color = this.contents.btnAdAllWarnFontColor
          el.style.fontSize = this.contents.btnAdAllFontSize
          el.style.borderWidth = this.contents.btnAdAllBorderWidth
          el.style.borderStyle = this.contents.btnAdAllBorderStyle
          el.style.borderColor = this.contents.btnAdAllBorderColor
          el.style.borderRadius = this.contents.btnAdAllBorderRadius
          el.style.backgroundColor = this.contents.btnAdAllWarnBgColor
        })
      })
    },

    headerRowStyle({ row, rowIndex}){
      return {color: this.contents.tableHeaderFontColor}
    },
    headerCellStyle({ row, rowIndex}){
      return {backgroundColor: this.contents.tableHeaderBgColor}
    },
    // 表格按钮
    contentTableBtnStyleChange(){

    },
    // 分页
    contentPageStyleChange(){
      let arr = []

      if(this.contents.pageTotal) arr.push('total')
      if(this.contents.pageSizes) arr.push('sizes')
      if(this.contents.pagePrevNext){
        arr.push('prev')
        if(this.contents.pagePager) arr.push('pager')
        arr.push('next')
      }
      if(this.contents.pageJumper) arr.push('jumper')
      this.layouts = arr.join()
      this.contents.pageEachNum = 10
    },

    xiaoshuopingfenCrossAddOrUpdateHandler(row,type,crossOptAudit,statusColumnName,tips,statusColumnValue){
      this.showFlag = false;
      this.addOrUpdateFlag = false;
      this.xiaoshuopingfenCrossAddOrUpdateFlag = true;
      this.$storage.set('crossObj',row);
      this.$storage.set('crossTable','xiaoshuoxinxi');
      this.$storage.set('statusColumnName',statusColumnName);
      this.$storage.set('statusColumnValue',statusColumnValue);
      this.$storage.set('tips',tips);
	if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
		var obj = this.$storage.getObj('crossObj');
		for (var o in obj){
		  if(o==statusColumnName && obj[o]==statusColumnValue){
		    this.$message({
		      message: tips,
		      type: "success",
		      duration: 1500,
		      onClose: () => {
			this.getDataList();
		      }
		    });
		      this.showFlag = true;
		      this.xiaoshuopingfenCrossAddOrUpdateFlag = false;
			return;
		  }
		}
	}
      this.$nextTick(() => {
      this.$refs.xiaoshuopingfenCrossaddOrUpdate.init(row.id,type);
      });
    },
    init () {
          this.$http({
            url: `option/xiaoshuofenlei/xiaoshuofenlei`,
            method: "get"
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.xiaoshuofenleiOptions = data.data;
            } else {
              this.$message.error(data.msg);
            }
          });
    },
    search() {
      this.pageIndex = 1;
      this.getDataList();
    },

    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      let params = {
        page: this.pageIndex,
        limit: this.pageSize,
        sort: 'id',
      }
          if(this.searchForm.xiaoshuomingcheng!='' && this.searchForm.xiaoshuomingcheng!=undefined){
            params['xiaoshuomingcheng'] = '%' + this.searchForm.xiaoshuomingcheng + '%'
          }
          if(this.searchForm.xiaoshuofenlei!='' && this.searchForm.xiaoshuofenlei!=undefined){
            params['xiaoshuofenlei'] = this.searchForm.xiaoshuofenlei
          }
      this.$http({
        url: "xiaoshuoxinxi/page",
        method: "get",
        params: params
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.data.list;
          this.totalPage = data.data.total;
        } else {
          this.dataList = [];
          this.totalPage = 0;
        }
        this.dataListLoading = false;
      });
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    // 多选
    selectionChangeHandler(val) {
      this.dataListSelections = val;
    },
    // 添加/修改
    addOrUpdateHandler(id,type) {
      this.showFlag = false;
      this.addOrUpdateFlag = true;
      this.crossAddOrUpdateFlag = false;
      if(type!='info'){
        type = 'else';
      }
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id,type);
      });
    },
    // 查看评论
    disscussListHandler(id,type) {
	this.$router.push({path:'/discussxiaoshuoxinxi',query:{refid:id}});
    },
    // 下载
    download(file){
      window.open(`${file}`)
    },
    // 删除
    deleteHandler(id) {
      var ids = id
        ? [Number(id)]
        : this.dataListSelections.map(item => {
            return Number(item.id);
          });
      this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$http({
          url: "xiaoshuoxinxi/delete",
          method: "post",
          data: ids
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.search();
              }
            });
          } else {
            this.$message.error(data.msg);
          }
        });
      });
    },


  }

};
</script>
<style lang="scss" scoped>
  .slt {
    margin: 0 !important;
    display: flex;
  }

  .ad {
    margin: 0 !important;
    display: flex;
  }

  .pages {
    & /deep/ el-pagination__sizes{
      & /deep/ el-input__inner {
        height: 22px;
        line-height: 22px;
      }
    }
  }
  

  .el-button+.el-button {
    margin:0;
  } 

  .tables {
	& /deep/ .el-button--success {
		height: 40px;
		color: #333;
		font-size: 14px;
		border-width: 3px;
		border-style: solid;
		border-color: rgba(111, 111, 151, 1);
		border-radius: 40px;
		background-color: #fff;
	}
	
	& /deep/ .el-button--primary {
		height: 40px;
		color: #333;
		font-size: 14px;
		border-width: 3px;
		border-style: solid;
		border-color: rgba(111, 111, 151, 1);
		border-radius: 40px;
		background-color: #fff;
	}
	
	& /deep/ .el-button--danger {
		height: 40px;
		color: #333;
		font-size: 14px;
		border-width: 3px;
		border-style: solid;
		border-color: rgba(111, 111, 151, 1);
		border-radius: 40px;
		background-color: #fff;
	}

    & /deep/ .el-button {
      margin: 4px;
    }
  }
	.form-content {
		background: transparent;
	}
	.table-content {
		background: transparent;
	}
	
	.tables /deep/ .el-table__body tr {
				background-color: rgba(245, 245, 245, 0) !important;
				color: #606266 !important;
	 }
	.tables /deep/ .el-table__body tr.el-table__row--striped td {
	    background: transparent;
	}
	.tables /deep/ .el-table__body tr.el-table__row--striped {
		background-color: #F5F7FA !important;
		color: #606266 !important;
	}
	
	 .tables /deep/ .el-table__body tr:hover>td {
	   	   background-color: rgba(111, 111, 151, 0.33) !important;
	   	   	   color: #333 !important;
	   	 }
	 
</style>
