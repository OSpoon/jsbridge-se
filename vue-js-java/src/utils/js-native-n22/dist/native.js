'use strict'

Object.defineProperty(exports, '__esModule', {
  value: true
})

var _bridge = require('./bridge')

var _bridge2 = _interopRequireDefault(_bridge)

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj } }

var native = {
  bridge: _bridge2.default,
  toast: function toast(data, success, fail) {
    _bridge2.default.callhandler('toast', data, function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  device: function device(success, fail) {
    _bridge2.default.callhandler('device', '', function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  close: function close(success, fail) {
    _bridge2.default.callhandler('close', '', function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  reload: function reload(success, fail) {
    _bridge2.default.callhandler('reload', '', function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  closeAndResult: function closeAndResult(data, success, fail) {
    _bridge2.default.callhandler('close', data, function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  scanQRCode: function scanQRCode(success, fail) {
    _bridge2.default.callhandler('scanQRCode', '', function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  location: function location(data, success, fail) {
    _bridge2.default.callhandler('location', data, function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  scanQRCodeGsb: function scanQRCodeGsb(success, fail) {
    _bridge2.default.callhandler('scanQRCodeGsb', '', function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  locationGsb: function locationGsb(data, success, fail) {
    _bridge2.default.callhandler('locationGsb', data, function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  storage: function storage(data, success, fail) {
    _bridge2.default.callhandler('storage', data, function(result) {
      if (!result.error) {
        if (result.content['value'] !== undefined) {
          try {
            // 猜测返回的value(字符串类型)如果可以转为JSON对象就多提前向对象返回
            var pack = {
              value: result.content['value'],
              object: JSON.parse(result.content['value'])
            }
            success(pack)
          } catch (err) {
            success(result.content)
          }
        }
      } else {
        fail(result.content)
      }
    })
  },
  openBrowser: function openBrowser(data, success, fail) {
    _bridge2.default.callhandler('openBrowser', data, function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  security: function security(data, success, fail) {
    _bridge2.default.callhandler('security', data, function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  callPhone: function callPhone(data, success, fail) {
    _bridge2.default.callhandler('callPhone', data, function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  sendMsg: function sendMsg(data, success, fail) {
    _bridge2.default.callhandler('sendMsg', data, function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  openContacts: function openContacts(success, fail) {
    _bridge2.default.callhandler('openContacts', '', function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  openContact: function openContact(success, fail) {
    _bridge2.default.callhandler('openContact', '', function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  openWeChat: function openWeChat(success, fail) {
    _bridge2.default.callhandler('openWeChat', '', function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  switchScreen: function switchScreen(success, fail) {
    _bridge2.default.callhandler('switchScreen', '', function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  imageSelect: function imageSelect(data, success, fail) {
    _bridge2.default.callhandler('imageSelect', data, function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  shareWeChat: function shareWeChat(data, success, fail) {
    _bridge2.default.callhandler('shareWeChat', data, function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  idCardScan: function idCardScan(data, success, fail) {
    _bridge2.default.callhandler('IDCardScan', data, function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  faceScan: function faceScan(success, fail) {
    _bridge2.default.callhandler('faceScan', '', function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  openCamera: function openCamera(data, success, fail) {
    _bridge2.default.callhandler('openCamera', data, function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  dictation: function dictation(success, fail) {
    _bridge2.default.callhandler('dictation', '', function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  pushData: function pushData(data, success, fail) {
    _bridge2.default.callhandler('pushData', data, function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  picPreview: function picPreview(data, success, fail) {
    _bridge2.default.callhandler('picPreview', data, function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  loginOperation: function loginOperation(data, success, fail) {
    _bridge2.default.callhandler('loginOperation', data, function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  openOther: function openOther(data, success, fail) {
    _bridge2.default.callhandler('openOther', data, function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  openUrl: function openUrl(data, success, fail) {
    _bridge2.default.callhandler('openUrl', data, function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  openPDF: function openPDF(data, success, fail) {
    _bridge2.default.callhandler('openPDF', data, function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  goHome: function goHome(success, fail) {
    _bridge2.default.callhandler('goHome', '', function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  showNavigationBar: function showNavigationBar(data, success, fail) {
    _bridge2.default.callhandler('showNavigationBar', data, function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  showLoading: function showLoading(data, success, fail) {
    _bridge2.default.callhandler('showLoading', data, function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  modifyPwd: function modifyPwd(success, fail) {
    _bridge2.default.callhandler('modifyPwd', '', function(result) {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  }
}

exports.default = native
