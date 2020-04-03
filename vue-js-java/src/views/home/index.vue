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
    <p />
    <van-button type="primary" block @click="callPhone()">打电话</van-button>
    <p />
    <van-button type="primary" block @click="sendMsg()">发送短信</van-button>
    <p />
    <van-button type="primary" block @click="openContacts()">获取联系人列表信息</van-button>
    <p />
    <van-button type="primary" block @click="openContact()">获取单个联系人信息</van-button>
    <p />
    <van-button type="primary" block @click="openWeChat()">打开微信</van-button>
    <p />
    <van-button type="primary" block @click="switchScreen()">切换横竖屏</van-button>
    <p />
    <van-button type="primary" block @click="imageSelect()">选择图片</van-button>
    <p />
    <van-button type="primary" block @click="idCardScan()">身份证识别</van-button>
    <p />
    <van-button type="primary" block @click="shareWeChat()">分享到微信好友</van-button>
    <p />
    <van-button type="primary" block @click="shareWeChatTimeline()">分享到微信朋友圈</van-button>
    <p />
    <van-button type="primary" block @click="shareWeChatFavorite()">分享到微信收藏</van-button>
    <p />
    <van-button type="primary" block @click="faceScan()">人脸识别</van-button>
    <p />
    <van-button type="primary" block @click="openCamera()">打开相机</van-button>
    <p />
    <van-button type="primary" block @click="pushData()">推送数据</van-button>
    <p />
    <van-button type="primary" block @click="dictation()">语音听写</van-button>
    <p />
    <van-button type="primary" block @click="picPreview()">图片预览</van-button>
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
        key: 'default',
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
        key: 'default',
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
        key: 'default',
        content: 'zWI1jkgPpYUys5c06MYEQQ=='
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    callPhone() {
      native.callPhone({
        phoneNumber: '15617883302',
        extensionNumber: ',1'
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    sendMsg() {
      native.sendMsg({
        phoneNumber: '15617883302',
        msgInfo: '我是测试信息'
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    openContacts() {
      native.openContacts((content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    openContact() {
      native.openContact((content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    openWeChat() {
      native.openWeChat((content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    switchScreen() {
      native.switchScreen((content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    imageSelect() {
      native.imageSelect({
        'limit': 2
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    shareWeChat() {
      native.shareWeChat({
        platform: '1',
        webPageUrl: 'https://www.baidu.com',
        imgUrl: 'https://www.zhihu.com',
        title: '测试分享标题',
        dec: '测试分享内容'
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    shareWeChatTimeline() {
      native.shareWeChat({
        platform: '2',
        webPageUrl: 'https://www.baidu.com',
        imgUrl: 'https://www.zhihu.com',
        title: '测试分享标题',
        dec: '测试分享内容'
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    shareWeChatFavorite() {
      native.shareWeChat({
        platform: '3',
        webPageUrl: 'https://www.zhihu.com',
        imgUrl: 'https://www.baidu.com',
        title: '测试分享标题',
        dec: '测试分享内容'
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    idCardScan() {
      native.idCardScan({
        'isVertical': true, // 是否竖屏识别
        'cardType': 1 // 1:人像面，2:国徽面
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    faceScan() {
      native.faceScan((content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    openCamera() {
      native.openCamera({
        openFlag: '2', // 打开相机,1.打开相机,2.是打开相册
        isCompress: 1 // 1.压缩,2.不压缩
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    dictation() {
      native.dictation((content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    pushData() {
      native.pushData({
        event: 'push_agent_data',
        data: {
          agentName: 'zhangxin',
          agentCode: '1100011010',
          orgCode: '0001'
        }
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    picPreview() {
      native.picPreview({
        images: [
          'http://img6.16fan.com/201510/11/005258wdngg6rv0tpn8z9z.jpg',
          'http://img6.16fan.com/201510/11/013553aj3kp9u6iuz6k9uj.jpg',
          'http://img6.16fan.com/201510/11/011753fnanichdca0wbhxc.jpg',
          'http://img6.16fan.com/201510/11/011819zbzbciir9ctn295o.jpg',
          'http://img6.16fan.com/201510/11/004847l7w568jc5n5wn385.jpg',
          'http://img6.16fan.com/201510/11/004906z0a0a0e0hs56ce0t.jpg',
          'http://img6.16fan.com/201510/11/004937pwttwjt0bgtoton7.jpg',
          'http://img6.16fan.com/201510/11/004946t38ybzt8bq8c838y.jpg',
          'http://img6.16fan.com/201510/11/004955d8ftz3t1sttt7ft7.jpg',
          'http://img6.16fan.com/201510/11/005027qy2g55yyglb59zdu.jpg',
          'http://img6.16fan.com/201510/11/005229bbtxkczcl0btmw8e.jpg',
          'http://img6.16fan.com/attachments/wenzhang/201805/18/152660818127263ge.jpeg',
          'http://img6.16fan.com/attachments/wenzhang/201805/18/152660818716180ge.jpeg'
        ]
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
