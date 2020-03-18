<template>
  <div>
    <van-nav-bar
      title="JS-JAVA-DEMO"
    />
    <van-button type="primary" block @click="toast()">Toast</van-button>
    <p />
    <van-button type="primary" block @click="openOther()">打开另一个原生页面</van-button>
  </div>
</template>

<script>

import native from '@/utils/native'

export default {
  name: 'Home',
  props: {},
  data() {
    return {}
  },
  created() {},
  mounted() {},
  methods: {
    toast() {
      native.toast(new Date(), (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    openOther() {
      native.openOther(new Date(), (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    }
  }
}
</script>

<style lang="less" scoped>

</style>
