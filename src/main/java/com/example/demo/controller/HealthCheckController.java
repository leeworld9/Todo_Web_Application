package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    /*

    AWS 로드 밸런서는 기본 경로인 "/"에 HTTP 요청을 보내 애플리케이션이 동작하는지 확인한다.
    일라스틱 빈스톡은 이를 기반으로 애플리케이션이 실행 중인 상태인지, 주의가 필요한 상태인지 확인해 준다.
    또 이 상태를 AWS 콘솔 화면에 표시 해준다. 이를 위해 "/"에 간단한 API를 만들어 주자.

    "/"는 WebSecurityConfig에서 인증하지 않아도 접근가능하도록 설정되어 있음.
    이 설정이 없다면 "/" 접근은 HTTP 403을 리턴하고 로드 밸런서는 애 플리케이션에 에러가 있다고 판단한다.

     */

    @GetMapping("/")
    public String healthCheck() {
        return "The service is up and running...";
    }
}
