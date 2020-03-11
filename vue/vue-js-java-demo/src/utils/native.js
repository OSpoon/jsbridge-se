import bridge from './bridge'

const native = {
  toast(message, success, fail) {
    bridge.callhandler('toast', { 'message': message }, (result) => {
      if (!result.error) {
        success(result.content)
      } else {
        fail(result.error)
      }
    })
  }
}

export default native
