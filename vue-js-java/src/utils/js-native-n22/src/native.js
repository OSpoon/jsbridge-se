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
  openContacts(data, success, fail) {
    bridge.callhandler('openContacts', data, (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  openWeChat(data, success, fail) {
    bridge.callhandler('openWeChat', data, (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  switchScreen(data, success, fail) {
    bridge.callhandler('switchScreen', data, (result) => {
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
