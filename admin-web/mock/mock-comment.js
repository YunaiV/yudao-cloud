// 常用，公共 mock

export function resultBody(result, code, message) {
  return {
    code: code || 0,
    message: message || '',
    ...(result || null),
  };
}

export default {
  resultBody,
};
