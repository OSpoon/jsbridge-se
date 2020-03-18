import bridge from './bridge'

const native = {
  toast(message, success, fail) {
    bridge.callhandler('toast', { 'message': message }, (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  openOther(message, success, fail) {
    bridge.callhandler('openOther', { 'message': message }, (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  },
  qrCodeScan(message, success, fail) {
    bridge.callhandler('qrscan', { 'message': message }, (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.content)
      }
    })
  }
}

export default native
