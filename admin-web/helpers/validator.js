
// 校验必须是英文或者数字
export function checkTypeWithEnglishAndNumbers (rule, value, callback, text) {
  let char = /^[a-zA-Z0-9]+$/
  if (char.test(value)) {
    callback()
  } else {
    callback(text)
  }
}