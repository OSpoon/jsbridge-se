import bridge from './bridge'

const native = {
  bridge,
  toast(data, success, fail) {
    bridge.callhandler('toast', data, (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  device(success, fail) {
    bridge.callhandler('device', '', (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  close(success, fail) {
    bridge.callhandler('close', '', (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  scanQRCode(success, fail) {
    bridge.callhandler('scanQRCode', '', (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  location(data, success, fail) {
    bridge.callhandler('location', data, (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  storage(data, success, fail) {
    bridge.callhandler('storage', data, (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  openBrowser(data, success, fail) {
    bridge.callhandler('openBrowser', data, (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  security(data, success, fail) {
    bridge.callhandler('security', data, (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  callPhone(data, success, fail) {
    bridge.callhandler('callPhone', data, (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  sendMsg(data, success, fail) {
    bridge.callhandler('sendMsg', data, (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  openContacts(success, fail) {
    bridge.callhandler('openContacts', '', (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  openWeChat(success, fail) {
    bridge.callhandler('openWeChat', '', (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  switchScreen(success, fail) {
    bridge.callhandler('switchScreen', '', (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  imageSelect(data, success, fail) {
    bridge.callhandler('imageSelect', data, (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  shareWeChat(data, success, fail) {
    bridge.callhandler('shareWeChat', data, (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  idCardScan(data, success, fail) {
    bridge.callhandler('IDCardScan', data, (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  faceScan(success, fail) {
    bridge.callhandler('faceScan', '', (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  openCamera(data, success, fail) {
    bridge.callhandler('openCamera', data, (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  openOther(data, success, fail) {
    bridge.callhandler('openOther', data, (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  }
}

export default native
