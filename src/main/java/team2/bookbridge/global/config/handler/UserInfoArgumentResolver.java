//package team2.bookbridge.global.config.handler;
//
//import org.springframework.core.MethodParameter;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//import team2.bookbridge.domain.User.domain.User;
//import team2.bookbridge.global.config.annotation.LoggedInUser;
//
//@Component
//public class UserInfoArgumentResolver implements HandlerMethodArgumentResolver {
//
//    @Override
//    public boolean supportsParameter(MethodParameter parameter) {
//        return parameter.getParameterAnnotation(LoggedInUser.class) != null && parameter.getParameterType() == User.class;
//    }
//
//    @Override
//    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        if (securityContext.getAuthentication().getPrincipal() instanceof User) {
//            return securityContext.getAuthentication().getPrincipal();
//        } else {
//            throw new AssertionError("Authentication.principal is not type of User");
//        }
//    }
//}

