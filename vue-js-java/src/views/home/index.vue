<template>
  <div>
    <van-nav-bar
      title="JS-JAVA-DEMO"
    />
    <van-button type="primary" block @click="toast()">Toast</van-button>
    <p />
    <van-button type="primary" block @click="openOther()">打开另一个原生页面</van-button>
    <p />
    <van-button type="primary" block @click="qrCodeScan()">识别二维码</van-button>
    <p />
    <van-button type="primary" block @click="getLocationInfo()">获取当前位置</van-button>
    <p />
    <van-button type="primary" block @click="getDevice()">获取设备信息</van-button>
    <p />
    <van-button type="primary" block @click="closePage()">关闭当前页面</van-button>
  </div>
</template>

<script>

import native from '@/utils/js-native-n22/src/native'
// import native from 'js-native-n22'

console.log(native)

export default {
  name: 'Home',
  props: {},
  data() {
    return {}
  },
  created() {},
  mounted() {
    native.bridge.registerhandler('functionInJs', (data, responseCallback) => {
      alert(JSON.stringify(data))
      responseCallback('JS OK')
    })
  },
  methods: {
    toast() {
      native.toast({ text: '你好啊赛利亚', duration: 0 }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    getDevice() {
      native.device((content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    closePage() {
      native.close((content) => {
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
    },
    qrCodeScan() {
      native.qrCodeScan(new Date(), (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    getLocationInfo() {
      native.getLocationInfo(new Date(), (content) => {
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
