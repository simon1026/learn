<template>
  <div>
    <template>
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="申请信息" name="first">
          <!-- 请假申请 -->
          <!--          <leave v-if="applyType === 'leave'"></leave>-->
          <component v-bind:is="applyType"></component>
        </el-tab-pane>
        <el-tab-pane label="申请流程" name="second" :lazy="true">
          <view-process-img></view-process-img>
        </el-tab-pane>
        <el-tab-pane v-if="optionType !== 'add'" label="批注" name="third" :lazy="true">
          <comment-list></comment-list>
        </el-tab-pane>
      </el-tabs>
    </template>
  </div>
</template>

<script>
  import leave from '../leave';
  import CommentList from './commentList';
  import ViewProcessImg from '../../ViewProcessImg';

  export default {
    name: "apply",
    components: {
      CommentList,
      ViewProcessImg,
      CommentList,
      leave
    },
    data() {
      return {
        activeName: 'first',
        applyType: '',
        optionType: null,
      };
    },
    created() {
      this.applyType = this.$route.query.applyType;
      this.optionType = this.$route.query.optionType;
    },
    methods: {
      handleClick(tab, event) {
        // console.log(tab, event);
      }
    }
  }
</script>

<style>

</style>
