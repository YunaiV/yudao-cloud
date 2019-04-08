
// TODO 临时代码
export function formatDate(date, pattern) {
  if (!(date instanceof Date)) {
    date = new Date(date);
  }
  if (pattern === 'yyyy-MM-dd') {
    let d = date.getDate();
    let m = date.getMonth() + 1; //Month from 0 to 11
    let y = date.getFullYear();
    return '' + y + '-' + (m <= 9 ? '0' + m : m) + '-' + (d <= 9 ? '0' + d : d);
  } else {
    return '暂不支持';
  }
}
