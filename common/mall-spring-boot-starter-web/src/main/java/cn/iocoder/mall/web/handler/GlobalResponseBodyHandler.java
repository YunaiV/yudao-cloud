package cn.iocoder.mall.web.handler;

//@ControllerAdvice
//public class GlobalResponseBodyHandler implements ResponseBodyAdvice {
//
//    @Override
//    public boolean supports(MethodParameter returnType, Class converterType) {
//        if (returnType.getMethod() == null) {
//            return false;
//        }
//        return returnType.getMethod().getReturnType() == CommonResult.class;
//    }
//
//    @Override
//    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType,
//                                  ServerHttpRequest request, ServerHttpResponse response) {
//        MallUtil.setCommonResult(((ServletServerHttpRequest) request).getServletRequest(), (CommonResult) body);
//        return body;
//    }
//
//}
