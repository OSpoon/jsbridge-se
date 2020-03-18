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
    },
    getLocationInfo(message, success, fail) {
        bridge.callhandler('location', { 'message': message }, (result) => {
            if (!result.error) {
                success(result.content)
            } else {
                fail(result.content)
            }
        })
    },
    getDevice(message, success, fail) {
        bridge.callhandler('device', { 'message': message }, (result) => {
            if (!result.error) {
                success(result.content)
            } else {
                fail(result.content)
            }
        })
    },
    closePage(message, success, fail) {
        bridge.callhandler('close', { 'message': message }, (result) => {
            if (!result.error) {
                success(result.content)
            } else {
                fail(result.content)
            }
        })
    }
}

export default native
