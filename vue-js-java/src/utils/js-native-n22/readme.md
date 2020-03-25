### js-native-n22使用指南 ###

[TOC]

[NPM](https://www.npmjs.com/package/js-native-n22)

#### 1. 安装 #### 
```
npm i js-native-n22
```

#### 2. 导入 ####
```
import native from 'js-native-n22'
``` 

#### 3. 使用 ####
```
native.api(data, (content) => {
    ...
}, (error) => {
    ...
})
```

#### API集合 ####

##### 1. toast:信息提示
> 可以通过此API做信息提示

请求参数:
参数 | 类型 | 枚举 | 含义
---|---|---|---
text | String | 无 | 提示信息
duration | int | 1:长,0:短 | 显示时长

响应参数: 无
    
API`toast`示例:

```js
native.toast({ text: '你好啊赛利亚', duration: 0 }, (content) => {
}, (error) => {
})
```

##### 2. device:获取设备信息
> 可以通过此API获取设备的信息

请求参数: 无

响应参数:
参数 | 类型 | 枚举 | 含义
---|---|---|---
isDeviceRooted | boolean | 无 | 判断设备是否 rooted
isAdbEnabled | boolean | 无 | 判断设备 ADB 是否可用
sDKVersionName | String | 无 | 获取设备系统版本号
sDKVersionCode | int | 无 | 获取设备系统版本码
androidID | String | 无 | 获取设备 AndroidID
macAddress | String | 无 | 获取设备 MAC 地址
manufacturer | String | 无 | 获取设备厂商
model | String | 无 | 获取设备型号
aBIs | String[] | 无 | 获取设备 ABIs
isTablet | boolean | 无 | 判断是否是平板
isEmulator | boolean | 无 | 判断是否是模拟器
uniqueDeviceId | String | 无 | 获取唯一设备 ID
isSameDevice | boolean | 无 | 判断是否同一设备

API`device`示例:
```js
native.device((content) => {
    alert(JSON.stringify(content))
}, (error) => {
    alert(error)
})
```

##### 3. close:关闭当前浏览器
> 可以通过此API关闭当前浏览器(相当于PC浏览器的右上角的关闭)

请求参数: 无

响应参数: 无
    
API`close`示例:
    
```js
native.close((content) => {
}, (error) => {
})
```

##### 4. scanQRCode:扫描二维码
> 可以通过此API进行扫描二维码

请求参数: 无

响应参数:
参数 | 类型 | 枚举 | 含义
---|---|---|---
qrcode | String | 无 | 识别到的二维码信息

API`scanQRCode`示例:
```js
native.scanQRCode((content) => {
    alert(JSON.stringify(content))
}, (error) => {
    alert(error)
})
```

##### 5. location:获取当前位置
> 可以通过此API定位到当前的位置信息

请求参数: 

参数 | 类型 | 枚举 | 含义
---|---|---|---
needAddress | boolean | true,false | 是否返回地址信息
mockEnable | boolean | true,false | 是否允许模拟位置
httpTimeOut | String | 单位毫秒不低于8000 | 定位超时时间

响应参数:
参数 | 类型 | 枚举 | 含义
---|---|---|---
latitude | double | 无 | 获取纬度
longitude | double | 无 | 获取经度
accuracy | float | 无 | 获取精度信息
address | String | 无 | 地址
country | String | 无 | 国家信息
province | String | 无 | 省信息
city | String | 无 | 城市信息
district | String | 无 | 城区信息
street | String | 无 | 街道信息
streetNum | String | 无 | 街道门牌号信息
cityCode | String | 无 | 城市编码
adCode | String | 无 | 地区编码
aoiName | String | 无 | 获取当前定位点的AOI信息
buildingId | int | 无 | 获取当前室内定位的建筑物Id
floor | String | 无 | 获取当前室内定位的楼层
gpsAccuracyStatus | int | 无 | 获取GPS的当前状态
time | String | 无 | 获取定位时间

API`location`示例:
```js
native.location({
    needAddress: true,
    mockEnable: false,
    httpTimeOut: 20000
}, (content) => {
    alert('纬度 : ' + content.latitude)
    alert('经度 : ' + content.longitude)
    alert('地址 : ' + content.address)
    alert(JSON.stringify(content))
}, (error) => {
    alert(error)
})
```

##### 6. storage:将key-value形式的数据存储到手机本地
> 可以通过此API保存和获取存储到手机本地的key-value数据

请求参数: 

参数 | 类型 | 枚举 | 含义
---|---|---|---
mode | int | 详见下方补充mode枚举 | 操作模式
key | String | 模式5不传,其他模式必传 | 待存储数据的key
value | String | 模式1必传,其他模式不传 | 待存储的数据

补充mode枚举:
```
1:添加数据
2:获取数据
3:是否存在该 key
4:移除该 key
5:清除所有数据
```

响应参数(mode2):
参数 | 类型 | 枚举 | 含义
---|---|---|---
value | String |  | 获取存储到手机的key对应的value内容

响应参数(mode3):
参数 | 类型 | 枚举 | 含义
---|---|---|---
contains | boolean | true:存在,false不存在 | 传入的key在手机中是否有数据存储

API`storage`示例:
```js
native.storage({
    mode: 1,
    key: 'name',
    value: 'zhangxin'
}, (content) => {
        alert(JSON.stringify(content))
}, (error) => {
        alert(error)
})
```

##### 7. openBrowser:打开一个原生浏览器页面
> 可以通过此API在js中再次打开一个原生浏览器页面

请求参数: 

参数 | 类型 | 枚举 | 含义
---|---|---|---
mode | int | 1:自身内核,2:X5内核 | 内核选择
url | String | 无 | 待打开的页面

响应参数: 无
    
API`openBrowser`示例:

```js
native.openBrowser({
    mode: 1,
    url: 'http://xrkj.gitee.io/jsbridge-n22/#/'
}, (content) => {
    alert(JSON.stringify(content))
}, (error) => {
    alert(error)
})
```

##### 8. security:调用原生3DES加解密和MD5编码
> 可以通过此API调用原生3DES加解密和MD5编码

请求参数: 

参数 | 类型 | 枚举 | 含义
---|---|---|---
mode | int | 1:获取MD5T验签,2:3des加密,3:3des解密 | 内核选择
key | String | 指定长度24位 | 加/解密时的秘钥
content | String | 无 | 待加/解密的内容

响应参数:

参数 | 类型 | 枚举 | 含义
---|---|---|---
encoder | String | 无 | 加密成功后返回
decoder | String | 无 | 解密成功后返回
sign | String | 无 | 成功获取验签返回

    
API`security`示例:

```js
native.security({
    mode: 1,
    key: 'xxx',
    content: '1234567890'
}, (content) => {
    alert(JSON.stringify(content))
}, (error) => {
    alert(error)
})
```
##### 9. callPhone:调用原生打电话功能
> 可以通过此API调用原生打电话功能

请求参数:

参数 | 类型 | 枚举 | 含义
---|---|---|---
phoneNumber | String | 无 | 电话号码
extensionNumber | String | 无 | 可以进行拨打分机号,示例为",1"

响应参数:无

API`callPhone`示例:

```js
 native.callPhone({
         phoneNumber: '15617883302',
         extensionNumber: ',1'
       }, (content) => {
         alert(JSON.stringify(content))
       }, (error) => {
         alert(error)
       })
```

##### 10. sendMsg:调用原生发短信功能
> 可以通过此API调用原生发短信

请求参数:

参数 | 类型 | 枚举 | 含义
---|---|---|---
phoneNumber | String | 无 | 电话号码
msgInfo | String | 无 | 要发送的信息内容

响应参数: 无

API`sendMsg`示例:

```js
 native.sendMsg({
         phoneNumber: '13333333333',
         msgInfo: '我是测试信息'
       }, (content) => {
         alert(JSON.stringify(content))
       }, (error) => {
         alert(error)
       })
```
##### 11. openContacts:调用原生获取联系人
> 可以通过此API调用原生获取联系人

请求参数: 无

响应参数:
参数 | 类型 | 枚举 | 含义
---|---|---|---
displayName | String | 无 | 联系人的名字
number | String | 无 | 联系人的电话

API`openContacts`示例:

```js
 native.openContacts((content) => {
         alert(JSON.stringify(content))
       }, (error) => {
         alert(error)
       })
```
##### 12. openWeChat:调用原生打开微信
> 可以通过此API调用原生打开微信

请求参数: 无

响应参数: 无

API`openWeChat`示例:

```js
 native.openWeChat((content) => {
         alert(JSON.stringify(content))
       }, (error) => {
         alert(error)
       })
```
##### 13. switchScreen:调用原生切换横竖屏
> 可以通过此API调用原生切换横竖屏

请求参数: 无

响应参数: 无

API`switchScreen`示例:

```js
 native.switchScreen((content) => {
         alert(JSON.stringify(content))
       }, (error) => {
         alert(error)
       })
```