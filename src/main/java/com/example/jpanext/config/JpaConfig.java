package com.example.jpanext.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 감사(= Auditing)하는 기능은 기본 기능이 아니므로 수동으로 켜줘야 한다.
@Configuration
// Entity Auditing 기능 설정
@EnableJpaAuditing
public class JpaConfig {

}
