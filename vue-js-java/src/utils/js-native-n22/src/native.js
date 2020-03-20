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
