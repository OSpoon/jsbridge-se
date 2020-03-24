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
    <van-button type="primary" block @click="getDevice()">获取设备信息</van-button>
    <p />
    <van-button type="primary" block @click="closePage()">关闭当前页面</van-button>
    <p />
    <van-button type="primary" block @click="getLocationInfo()">获取当前位置</van-button>
    <p />
    <van-button type="primary" block @click="putData()">添加数据</van-button>
    <p />
    <van-button type="primary" block @click="getData()">获取数据</van-button>
    <p />
    <van-button type="primary" block @click="containsKey()">查看key是否存在</van-button>
    <p />
    <van-button type="primary" block @click="remoKey()">删除指定key的数据</van-button>
    <p />
    <van-button type="primary" block @click="cleanAll()">清空所有数据</van-button>
    <p />
    <van-button type="primary" block @click="openBrowser()">打开新的浏览器</van-button>
    <p />
    <van-button type="primary" block @click="encode()">加密</van-button>
    <p />
    <van-button type="primary" block @click="decode()">解密</van-button>
    <p />
    <van-button type="primary" block @click="sign()">获取验证签名</van-button>
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
    qrCodeScan() {
      native.scanQRCode((content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    getLocationInfo() {
      native.location({
        needAddress: true,
        mockEnable: false,
        httpTimeOut: 20000 // 建议超时时间不要低于8000毫秒
      }, (content) => {
        alert('纬度 : ' + content.latitude)
        alert('经度 : ' + content.longitude)
        alert('地址 : ' + content.address)
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    putData() {
      native.storage({
        mode: 1,
        key: 'name',
        value: 'zhangxin'
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    getData() {
      native.storage({
        mode: 2,
        key: 'name'
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    containsKey() {
      native.storage({
        mode: 3,
        key: 'name'
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    remoKey() {
      native.storage({
        mode: 4,
        key: 'name'
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    cleanAll() {
      native.storage({
        mode: 5
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    openBrowser() {
      native.openBrowser({
        mode: 1,
        url: 'http://xrkj.gitee.io/jsbridge-n22/#/'
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    sign() {
      native.security({
        mode: 1,
        key: 'MOAPPINTERFACE2017#@!%88',
        content: '1234567890'
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    encode() {
      native.security({
        mode: 2,
        key: 'MOAPPINTERFACE2017#@!%88',
        content: '1234567890'
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    decode() {
      native.security({
        mode: 3,
        key: 'MOAPPINTERFACE2017#@!%88',
        content: 'zWI1jkgPpYUys5c06MYEQQ=='
      }, (content) => {
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
