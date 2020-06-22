<template>
  <div>
    <van-nav-bar
      title="我是测试页面"
    />
    <van-dialog v-model="showDialog" title="图片预览" show-cancel-button>
      <img width="300" height="300" :src="imageBase64">
    </van-dialog>
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
    <van-button type="primary" block @click="openNewBrowser()">打开自定义新的浏览器</van-button>
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
    <van-button type="primary" block @click="faceScan()">人脸识别</van-button>
    <p />
    <van-button type="primary" block @click="openCamera()">打开相机</van-button>
    <p />
    <van-button type="primary" block @click="openAlbum()">打开相册</van-button>
    <p />
    <van-button type="primary" block @click="pushData()">推送数据</van-button>
    <p />
    <van-button type="primary" block @click="dictation()">语音听写</van-button>
    <p />
    <van-button type="primary" block @click="picPreview()">图片预览</van-button>
    <p />
    <van-button type="primary" block @click="getClearData()">获取存储的指定数据</van-button>
    <p />
    <van-button type="primary" block @click="loginOperation()">登录操作</van-button>
    <p />
    <van-button type="primary" block @click="loginOperationOut()">退出登录操作</van-button>
    <p />
    <van-button type="primary" block @click="testNetImage()">TestNetImage</van-button>
    <p />
    <van-button type="primary" block @click="openUrl()">调用原生打开资源链接</van-button>
    <p />
    <van-button type="primary" block @click="openPDF()">打开PDF文件</van-button>
    <p />
    <van-button type="primary" block @click="goHome()">回到首页</van-button>
    <p />
    <van-button type="primary" block @click="showNavigationBar()">显示原生导航栏</van-button>
    <p />
    <van-button type="primary" block @click="showLoading()">显示loading框</van-button>
    <p />
    <van-button type="primary" block @click="showLoading1()">关闭loading框</van-button>
    <p />
    <van-button type="primary" block @click="modifyPwd()">修改密码</van-button>
    <p />
    <van-uploader :after-read="afterRead" />
  </div>
</template>

<script>
import lrz from 'lrz'
import native from '@/utils/js-native-n22/src/native'
// import native from 'js-native-n22'

console.log(native)

export default {
  name: 'Home',
  props: {},
  data() {
    return {
      showDialog: false,
      imageBase64: ''
    }
  },
  created() {
  },
  mounted: function() {
    native.bridge.registerhandler('functionInJs', (data, responseCallback) => {
      alert(JSON.stringify(data))
      responseCallback('JS OK')
    })
    native.bridge.registerhandler('closeCallBack', (data, responseCallback) => {
      alert(JSON.stringify(data))
      responseCallback('CLOSE OK')
    })
    native.bridge.registerhandler('GDINativePushData', (data, responseCallback) => {
      alert(JSON.stringify(data))
      responseCallback('GDINativePushData OK')
    })
    alert(window.localStorage.getItem('productName'))
    alert(window.localStorage.getItem('productCodeDetail'))
  },
  methods: {
    /**
       * 通过Lrz来加载本地图片
       */
    loadImageFile(path, successCallback, errorCallback, alwaysCallback) {
      lrz(path, { quality: 1 })
        .then((rst) => {
          // 处理成功会执行
          successCallback && successCallback(rst)
        })
        .catch((err) => {
          // 处理失败会执行
          errorCallback && errorCallback(err)
        })
        .always(() => {
          alwaysCallback && alwaysCallback()
        })
    },
    afterRead(file) {
      // 此时可以自行将文件上传至服务器
      this.showDialog = !this.showDialog
      this.imageBase64 = file.content
    },
    testNetImage() {
      this.loadImageFile('http://img6.16fan.com/attachments/wenzhang/201805/18/152660818127263ge.jpeg', (rst) => {
        this.showDialog = !this.showDialog
        this.imageBase64 = rst.base64
      }, err => {
        alert(err)
      })
    },
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
    openNewBrowser() {
      native.openBrowser({
        mode: 2,
        url: 'http://xrkj.gitee.io/jsbridge-n22/#/',
        isCustom: true,
        isShowClose: true,
        isShowBack: true,
        isShowShare: true,
        toolbar: {
          height: 44,
          color: '#0000CC'
        },
        title: {
          color: '#666633',
          staticText: '自定义Title'
        }
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
        this.loadImageFile(content.paths[0], (rst) => {
          this.showDialog = !this.showDialog
          this.imageBase64 = rst.base64
        }, err => {
          alert(err)
        })
      }, (error) => {
        alert(error)
      })
    },
    shareWeChat() {
      native.shareWeChat({
        platform: '1',
        webPageUrl: 'http://n22.online/',
        iconUrl: 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2841648446,236398816&fm=26&gp=0.jpg',
        title: '测试分享标题',
        desc: '测试分享内容'
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    shareWeChatTimeline() {
      native.shareWeChat({
        platform: '2',
        webPageUrl: 'http://n22.online/',
        iconUrl: 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2841648446,236398816&fm=26&gp=0.jpg',
        title: '测试分享标题',
        desc: '测试分享内容'
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
        openFlag: '1', // 打开相机,1.打开相机,2.是打开相册
        isCompress: 1 // 1.压缩,2.不压缩
      }, (content) => {
        alert(JSON.stringify(content))
        this.loadImageFile(content.paths[0], (rst) => {
          this.showDialog = !this.showDialog
          this.imageBase64 = rst.base64
        }, err => {
          alert(err)
        })
      }, (error) => {
        alert(error)
      })
    },
    openAlbum() {
      native.openCamera({
        openFlag: '2', // 打开相机,1.打开相机,2.是打开相册
        isCompress: 2, // 1.压缩,2.不压缩
        photoNum: 3 // 打开相册选择照片的数量
      }, (content) => {
        alert(JSON.stringify(content))
        this.loadImageFile(content.paths[0], (rst) => {
          this.showDialog = !this.showDialog
          this.imageBase64 = rst.base64
        }, err => {
          alert(err)
        })
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
    getClearData() {
      native.storage({
        mode: 2,
        key: 'userInfo'
      }, (content) => {
        alert(JSON.stringify(content.value))
        try {
          // 将字符串转为对象
          // content.value 的类型为字符串 需通过JSON.parse(json)转为对象使用
          const result = JSON.parse(content.value)
          alert(JSON.stringify(result.appLoginUser))
          alert(JSON.stringify(result.appLoginUser.agentName))
        } catch {
          console.log('value的内容非JSON对象,无法转换')
        }
        // 直接获取对象
        if (content['object'] !== undefined) {
          alert(JSON.stringify(content.object.appLoginUser))
          alert(JSON.stringify(content.object.appLoginUser.agentName))
        }
      }, (error) => {
        alert(error)
      })
    },
    loginOperation() {
      native.loginOperation({
        loginFlag: '1'
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    loginOperationOut() {
      native.loginOperation({
        loginFlag: '2'
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
    },
    openUrl() {
      native.openUrl({
        url: 'https://mitphone.sunlife-everbright.com:8010/com.ifp.ipartner/proposaMsg?uuid=657c1962b5a44b9ebec1ea53b57abd6b'
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    openPDF() {
      native.openUrl({
        title: 'PDF标题',
        url: 'https://xinyidongzhanyeguangsubao-st-1254235118.cos.ap-beijing.myqcloud.com/Default/HCT010-T.pdf'
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    goHome() {
      native.goHome((content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    showNavigationBar() {
      native.showNavigationBar({
        isShowNavigationBar: '0是隐藏，1是显示',
        isShowShare: '0是隐藏，1是显示',
        navigationBar: {
          changeLeftImage: '0代表黄色返回按钮，1代表黑色返回按钮，2代表灰色返回按钮，3代表白色按钮',
          changeRightImage: [
            {
              id: '显示功能的ID',
              image: '0，1，2，3各代表一种图片显示样式',
              method: '前端页面需要做的操作',
              methodDec: '前端方法的描述',
              sort: 0
            },
            {
              id: '显示功能的ID',
              image: '0，1，2，3各代表一种图片显示样式',
              method: '前端页面需要做的操作',
              methodDec: '前端方法的描述',
              sort: 1
            }
          ],
          isShowClose: '是否显示关闭按钮，0是隐藏，1是显示',
          isShowTitle: '是否显示标题 0隐藏1显示',
          navigationBarBackGroundColor: '标题栏的背景颜色',
          title: '标题栏的标题',
          titleColor: '标题显示的颜色',
          titleSize: '标题显示的字体的大小字号'
        },
        shareModel: {
          shareDescription: '分享的内容',
          imageUrl: '分享图标的url',
          shareTitle: '分享标题',
          shareUrl: '分享的跳转链接'
        }
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    showLoading() {
      native.showLoading({
        isShow: '1'
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    showLoading1() {
      native.showLoading({
        isShow: '0'
      }, (content) => {
        alert(JSON.stringify(content))
      }, (error) => {
        alert(error)
      })
    },
    modifyPwd() {
      native.modifyPwd((content) => {
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
